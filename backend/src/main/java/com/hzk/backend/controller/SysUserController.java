package com.hzk.backend.controller;

import com.hzk.backend.annotation.AutoLog;
import com.hzk.backend.dto.JsonDto;
import com.hzk.backend.pojo.SysUser;
import com.hzk.backend.services.SysUserService;
import com.hzk.backend.utils.JwtUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // 标识当前类是一个请求处理类
@CrossOrigin
@RequestMapping("/user")
public class SysUserController {

    @Resource // 调用已经实例化好的Service
    SysUserService sysUserService;

    // --- 1. 登录 ---
    @GetMapping("/login") // 标识请求路径
    @AutoLog("登录了系统")
    // 返回值为JsonDto
    // 自动拆包: (@RequestParam String username, @RequestParam String password)
    // 古老派: HttpServletRequest request
    public JsonDto login(@RequestParam String username, @RequestParam String password) {
        // http://localhost:8080/adminLogin?username=hzk&password=1234
        // 协议 :// ip : 端口 / 请求地址 参数名 参数值

        System.out.println("---收到前端请求: " + username + " 尝试登录---");

        // 1. 账号密码去数据库查询
        SysUser user = sysUserService.login(username, password);

        // 2. 快递箱
        JsonDto jsonDto = new JsonDto();

        // 3. 是否查到数据
        if (user != null) {
            // 成功，打包成功信息
            jsonDto.setId(1);
            jsonDto.setMessage("登录成功!");
            // 把查到的结果塞进快递箱的 datas 里
            jsonDto.getDatas().put("user", user);

            // 生成 JWT Token
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", user.getId());
            claims.put("username", user.getUsername());
            claims.put("role", user.getRole()); // 存入角色，方便后续权限校验
            String token = JwtUtils.generateToken(claims);

            // 把生成的 Token 也塞进 datas 返回给前端
            jsonDto.getDatas().put("token", token);
        } else {
            // 失败，打包失败信息
            jsonDto.setId(0);
            jsonDto.setMessage("登录失败，账号或密码错误!");
        }

        // 4. 返回json
        return jsonDto;
    }

    // --- 2. 注册 ---
    @PostMapping("/register")
    public JsonDto register(@RequestParam String username, @RequestParam String password) {
        // http://localhost:8080/adminReg?username=hzk&password=123456

        // 1.
        System.out.println("---收到前端请求：尝试注册---");
        System.out.println("注册账号: " + username + " 密码: " + password);

        // 2. 准备好要返回给前端的数据包 JsonDto
        JsonDto jsonDto = new JsonDto();

        // 3. 把接收到的账号和密码装进 SysUser 实体类里
        SysUser user  = new SysUser();
        user.setUsername(username);
        user.setPassword(password);
        // 只允许注册为普通用户
        user.setRole("user");

        // 4. Service层执行注册
        int i = sysUserService.register(user);

        // 5. 判断是否注册成功
        if (i > 0) {
            jsonDto.setId(1);
            jsonDto.setMessage("注册成功");
        } else {
            jsonDto.setId(0);
            jsonDto.setMessage("注册失败, 可能账号已存在");
        }

        // 6. 返回json
        return jsonDto;
    }

    // --- 3. 更新 ---
    @PutMapping("/update")
    @AutoLog("更新了用户信息")
    public JsonDto update(SysUser user) {
        // 当参数很多时，直接用对象接参数最优雅。前端只要传过来的名字和 SysUser 的属性名一样，Spring 就会自动赋值
        System.out.println("---收到前端请求：尝试修改---");
        JsonDto jsonDto = new JsonDto();

        if (user.getId() == null) {
            jsonDto.setId(0);
            jsonDto.setMessage("错误: 修改必须提供用户ID!");
            return jsonDto;
        }

        int i = sysUserService.update(user);
        if (i > 0) {
            jsonDto.setId(1);
            jsonDto.setMessage("修改成功");
        } else {
            jsonDto.setId(0);
            jsonDto.setMessage("未找到对应ID");
        }

        return jsonDto;

        // http://localhost:8080/adminUpdate?username=hzk
        // http://localhost:8080/adminUpdate?id=16&username=hhh

        // 1. 获取浏览器发来的全部参数，如无则 null 或者 空字符串
        // id name password

        /*String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String stateStr = request.getParameter("state");

        // 字符串转换数字
        Integer id = (idStr != null && !idStr.isEmpty()) ? Integer.parseInt(idStr) : null;
        Integer state = (stateStr != null && !stateStr.isEmpty()) ? Integer.parseInt(stateStr) : null;

        // 增加这一步：如果连 ID 都没有，直接打回！
        if (id == null) {
            JsonDto jsonDto = new JsonDto();
            jsonDto.setId(0);
            jsonDto.setMessage("错误：修改必须提供管理员 ID！");
            return jsonDto;
        }

        System.out.println("---收到前端请求：尝试修改---");

        // 2. 准备好要返回给前端的数据包 JsonDto
        JsonDto jsonDto = new JsonDto();

        SysUser sysUser = new SysUser();
        sysUser.setAdminId(id);
        sysUser.setAdminName(name);
        sysUser.setAdminPassword(password);
        sysUser.setAdminState(state);

        // 3. 把这 4 个参数一股脑全部扔给 Service
        int i = sysUserService.adminUpdate(sysUser);

        // 4. 判断修改结果
        if (i  > 0) {
            jsonDto.setId(1);
            jsonDto.setMessage("修改成功");
        } else {
            jsonDto.setId(0);
            jsonDto.setMessage("未找到对应ID");
        }

        return jsonDto;*/
    }

    // --- 4. 删除 ---
    @DeleteMapping("/delete")
    @AutoLog("删除了系统用户")
    public JsonDto delete(@RequestParam Long id) {
        // http://localhost:8080/adminDelete?id=1

        System.out.println("---收到前端请求：尝试删除---");
        JsonDto jsonDto = new JsonDto();

        // 如果连 ID 都没有，直接打回
        if (id == null) {
            jsonDto.setId(0);
            jsonDto.setMessage("错误：修改必须提供ID！");
            return jsonDto;
        }

        int i = sysUserService.deleteById(id);

        if (i > 0) {
            jsonDto.setId(1);
            jsonDto.setMessage("删除成功");
        } else {
            jsonDto.setId(0);
            jsonDto.setMessage("未找到对应ID");
        }

        return jsonDto;
    }

    // --- 5. 搜索 ---
    @GetMapping("/search")
    // 普通搜索 vs 分页搜素
    public JsonDto search(@RequestParam(required = false) String name,
                          @RequestParam(required = false) String btime,
                          @RequestParam(required = false) String etime,
                          @RequestParam(required = false) String role,
                          @RequestParam(required = false) Integer status,
                          @RequestParam(defaultValue = "0") Integer start,
                          @RequestParam(defaultValue = "10") Integer end) {
        // http://localhost:8080/adminSearch?username=hzk
        // http://localhost:8080/adminSearch?name=l&btime=2025-08-01%2000:00:00&etime=2026-08-01%2000:00:00&state=1&start=0&end=6

        System.out.println("---收到前端请求：尝试搜索---");

        // 准备好要返回给前端的数据包 JsonDto
        JsonDto jsonDto = new JsonDto();

        // 把这些参数一股脑全部扔给 Service（不要在这里写 if 判断，让 XML 去头疼）
        List<SysUser> userList = sysUserService.search(name, btime, etime, role, status, start, end);

        // 判断搜索结果
        if (userList != null && !userList.isEmpty()) {
            jsonDto.setId(1);
            jsonDto.setMessage("查询成功");
            // 把查到的结果塞进快递箱的 datas 里
            jsonDto.getDatas().put("userList", userList); // 返回整个集合
        } else {
            jsonDto.setId(0);
            jsonDto.setMessage("未查到相关用户");
        }

        return jsonDto;
    }
}

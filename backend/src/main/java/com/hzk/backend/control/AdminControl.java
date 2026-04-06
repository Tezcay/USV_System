package com.hzk.backend.control;

import com.hzk.backend.dto.JsonDto;
import com.hzk.backend.pojo.Admin;
import com.hzk.backend.services.AdminService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 标识当前类是一个请求处理类
public class AdminControl {

    @Resource // 调用已经实例化好的Service
    AdminService adminService;

    @GetMapping("/adminLogin") // 标识请求路径
    // 返回值为JsonDto
    public JsonDto login(HttpServletRequest request) {
        // http://localhost:8080/adminLogin?username=hzk&password=1234
        // 协议 :// ip : 端口 / 请求地址 参数名 参数值

        String u = request.getParameter("username");
        String p = request.getParameter("password");

        System.out.println("---收到前端请求：尝试登录---");

        // 1. 账号密码去数据库查询
        Admin admin = adminService.adminLogin(u, p);

        // 2. 快递箱
        JsonDto jsonDto = new JsonDto();

        // 3. 是否查到数据
        if (admin != null) {
            // 成功，打包成功信息
            jsonDto.setId(1);
            jsonDto.setMessage("登录成功!");
            // 把查到的结果塞进快递箱的 datas 里
            jsonDto.getDatas().put("admin", admin);
        } else {
            // 失败，打包失败信息
            jsonDto.setId(0);
            jsonDto.setMessage("登录失败，账号或密码错误!");
        }

        // 4. 返回json
        return jsonDto;
    }

    @PostMapping("/adminRegister")
    public JsonDto register(HttpServletRequest request) {
        // http://localhost:8080/adminReg?username=hzk&password=123456

        // 1. 获取浏览器发来的参数(username, password)
        String u = request.getParameter("username");
        String p = request.getParameter("password");

        System.out.println("---收到前端请求：尝试注册---");
        System.out.println("注册账号: " + u + " 密码: " + p);

        // 2. 准备好要返回给前端的数据包 JsonDto
        JsonDto jsonDto = new JsonDto();

        // 3. 把接收到的账号和密码装进 Admin 实体类里
        Admin admin = new Admin();
        admin.setAdminName(u);
        admin.setAdminPassword(p);

        // 4. Service层执行注册
        int i = adminService.adminRegister(admin);

        // 5. 判断是否注册成功
        if (i > 0) {
            jsonDto.setId(1);
            jsonDto.setMessage("注册成功");
        } else {
            jsonDto.setId(0);
            jsonDto.setMessage("注册失败");
        }

        // 6. 返回json
        return jsonDto;
    }

    @PutMapping("/adminUpdate")
    public JsonDto update(HttpServletRequest request) {
        // http://localhost:8080/adminUpdate?username=hzk
        // http://localhost:8080/adminUpdate?id=16&username=hhh

        // 1. 获取浏览器发来的全部参数，如无则 null 或者 空字符串
        // id name password

        String idStr = request.getParameter("id");
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

        Admin admin = new Admin();
        admin.setAdminId(id);
        admin.setAdminName(name);
        admin.setAdminPassword(password);
        admin.setAdminState(state);

        // 3. 把这 4 个参数一股脑全部扔给 Service
        int i = adminService.adminUpdate(admin);

        // 4. 判断修改结果
        if (i  > 0) {
            jsonDto.setId(1);
            jsonDto.setMessage("修改成功");
        } else {
            jsonDto.setId(0);
            jsonDto.setMessage("未找到对应ID");
        }

        return jsonDto;
    }

    @DeleteMapping("/adminDelete")
    public JsonDto delete(HttpServletRequest request) {
        // http://localhost:8080/adminDelete?id=1

        // 1. 获取浏览器发来的参数
        String idStr = request.getParameter("id");
        Integer id = (idStr != null && !idStr.isEmpty()) ? Integer.parseInt(idStr) : null;

        // 增加这一步：如果连 ID 都没有，直接打回！
        if (id == null) {
            JsonDto jsonDto = new JsonDto();
            jsonDto.setId(0);
            jsonDto.setMessage("错误：修改必须提供管理员 ID！");
            return jsonDto;
        }

        // 2. 打包
        JsonDto jsonDto = new JsonDto();

        int i = adminService.deleteAdminById(id);

        if (i > 0) {
            jsonDto.setId(1);
            jsonDto.setMessage("删除成功");
        } else {
            jsonDto.setId(0);
            jsonDto.setMessage("未找到对应ID");
        }

        return jsonDto;
    }

    @GetMapping("/adminSearch")
    public JsonDto search(HttpServletRequest request) {
        // http://localhost:8080/adminSearch?username=hzk

        // 1. 获取浏览器发来的全部参数，如无则 null 或者 空字符串
        // name btime etime state start end
        // 状态、分页参数传过来是字符串，需要转换成数字 Integer
        // 逻辑：如果前端传了东西，就把他转成数字；如果啥也没传，就让他等于 null
        String name =  request.getParameter("name");
        String btime = request.getParameter("btime");
        String etime = request.getParameter("etime");

        String stateStr = request.getParameter("state");
        Integer state = (stateStr != null && !stateStr.isEmpty()) ? Integer.parseInt(stateStr) : null;

        String startStr = request.getParameter("start");
        Integer start = (startStr != null && !startStr.isEmpty()) ? Integer.parseInt(startStr) : 0;
        // 分页参数如果没传，需要给个默认值（比如从第 0 条开始，每页查 5 条）
        String endStr = request.getParameter("end");
        Integer end = (endStr != null && !endStr.isEmpty()) ? Integer.parseInt(endStr) : 5;

        System.out.println("---收到前端请求：尝试搜索---");

        // 2. 准备好要返回给前端的数据包 JsonDto
        JsonDto jsonDto = new JsonDto();

        // 3. 把这 6 个参数一股脑全部扔给 Service（不要在这里写 if 判断，让 XML 去头疼）
        List<Admin> adminList = adminService.searchAdmin(name, btime, etime, state, start, end);

        // 4. 判断搜索结果
        if (adminList != null && !adminList.isEmpty()) {
            jsonDto.setId(1);
            jsonDto.setMessage("查询成功");
            // 把查到的结果塞进快递箱的 datas 里
            jsonDto.getDatas().put("adminList", adminList.get(0));
        } else {
            jsonDto.setId(0);
            jsonDto.setMessage("未查到相关管理员");
        }

        // 5. 返回json
        return jsonDto;
    }
}

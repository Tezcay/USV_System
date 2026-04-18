<template>
	<div id="app" class="login-container">
		<el-card class="login-box">
			<h2 class="login-title">USV 后台管理系统</h2>

			<el-form>
				<el-form-item>
					<el-input prefix-icon="el-icon-user" placeholder="请输入管理员账号" v-model="loginForm.username"></el-input>
				</el-form-item>

				<el-form-item>
					<el-input prefix-icon="el-icon-lock" type="password" placeholder="请输入密码" show-password
						v-model="loginForm.password"></el-input>
				</el-form-item>

				<el-form-item class="action-buttons">
					<el-button type="primary" @click="doLogin" class="btn">登 录</el-button>
					<el-button @click="goToRegister" class="btn">注 册</el-button>
				</el-form-item>
			</el-form>

		</el-card>
	</div>
</template>

<script>
	export default {
		name: 'App',
		// data 里存放变量
		data() {
			return {
				loginForm: {
					username: '',
					password: ''
				}
			}
		},
		// methods 里存放执行的动作函数
		methods: {
			doLogin() {
				// 1. 检查是否为空
				if (!this.loginForm.username || !this.loginForm.password) {
					this.$message.warning("账号和密码不能为空哦！");
					return;
				}

				// 2. 发送请求给 Java 后端
				this.$axios.get('http://localhost:8080/user/login', {
					params: {
						username: this.loginForm.username,
						password: this.loginForm.password
					}
				}).then(res => {
					// 请求成功到达服务器并返回了
					console.log("后端返回的数据：", res.data);

					if (res.data.id === 1) {
						this.$message.success("登录成功！");
						// 把后端的 Token 保存到浏览器的 sessionStorage
						sessionStorage.setItem("token", res.data.datas.token);
						this.$router.push('/home');
					} else {
						this.$message.error("登录失败：" + res.data.message);
					}
				}).catch(error => {
					// 请求根本没到服务器，或者服务器直接崩了
					console.error("捕获到网络错误：", error);

					if (error.response) {
						// 请求发出去了，但服务器回了一个错误状态码（比如 404, 500）
						this.$message.error(`服务器报错，状态码：${error.response.status}`);
					} else if (error.request) {
						// 请求发了，但服务器没理你（比如你没启动后端，或者断网了）
						this.$message.error("无法连接到服务器，请检查 Java 后台是否已启动！");
					} else {
						// 其他奇奇怪怪的错误
						this.$message.error("发生未知错误：" + error.message);
					}
				});
			},

			goToRegister() {
				this.$message.info("准备跳转到注册页面...");
				// 以后这里写跳转逻辑
			}
		}
	}
</script>

<style>
	/* 消除白边并设置全屏居中背景 */
	html,
	body {
		margin: 0;
		padding: 0;
	}

	.login-container {
		height: 100vh;
		background-color: #2b4b6b;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	/* 登录框的尺寸 */
	.login-box {
		width: 400px;
		border-radius: 10px;
	}

	.login-title {
		text-align: center;
		margin-bottom: 30px;
		color: #333;
	}

	/* 让两个按钮均匀分布在两边 */
	.action-buttons .el-form-item__content {
	  display: flex;
	  justify-content: space-between;
	}
	
	/* 让每个按钮占据差不多一半的宽度 */
	.btn {
	  width: 45%;
	  font-size: 16px;
	}
</style>
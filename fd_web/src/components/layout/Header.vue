<template>
	<div class="header-container">
		<div class="header-left">
			<i class="el-icon-s-fold collapse-btn"></i>
			<el-breadcrumb separator="/">
				<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item v-if="$route.path !== '/home'">
					{{ $route.meta.title }}
				</el-breadcrumb-item>
			</el-breadcrumb>
		</div>

		<div class="header-right">
			<div class="tool-item"><i class="el-icon-bell"></i><el-badge is-dot class="item">通知</el-badge></div>
			<div class="tool-item" @click="$emit('toggle-theme')"><i class="el-icon-magic-stick"></i> 主题切换</div>

			<el-dropdown trigger="click">
				<div class="user-area">
					<el-avatar size="small"
						src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
					<span class="username">管理员 <i class="el-icon-arrow-down"></i></span>
				</div>
				<el-dropdown-menu slot="dropdown">
					<el-dropdown-item icon="el-icon-user">个人中心</el-dropdown-item>
					<el-dropdown-item icon="el-icon-key">修改密码</el-dropdown-item>
					<el-dropdown-item divided icon="el-icon-switch-button"
						@click.native="handleLogout">退出登录</el-dropdown-item>
				</el-dropdown-menu>
			</el-dropdown>
		</div>
	</div>
</template>

<script>
	export default {
		computed: {
			// 监听路由变化，动态显示面包屑文字
			currentPathName() {
				return this.$route.name || '概览';
			}
		},
		methods: {
			handleLogout() {
				this.$router.push('/login');
			}
		}
	}
</script>

<style scoped>
	.header-container {
		height: 60px;
		background: #fff;
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 0 20px;
		box-shadow: 0 1px 4px rgba(0, 21, 41, .08);
	}

	.header-left {
		display: flex;
		align-items: center;
	}

	.collapse-btn {
		font-size: 20px;
		margin-right: 20px;
		cursor: pointer;
		color: #606266;
	}

	.header-right {
		display: flex;
		align-items: center;
	}

	.tool-item {
		margin-right: 25px;
		font-size: 14px;
		color: #606266;
		cursor: pointer;
	}

	.user-area {
		display: flex;
		align-items: center;
		cursor: pointer;
	}

	.username {
		margin-left: 8px;
		font-size: 14px;
		color: #606266;
	}
</style>
<template>
	<el-container class="app-wrapper">
		<el-aside width="200px">
			<Sidebar :theme="currentTheme" />
		</el-aside>

		<el-container class="main-container">
			<el-header height="60px" style="padding: 0;">
				<Header :theme="currentTheme" @toggle-theme="handleThemeToggle" />
			</el-header>

			<el-main :class="{ 'is-dark': currentTheme === 'dark' }">
				<router-view></router-view>
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
	// 1. 导入两个组件
	import Sidebar from '@/components/layout/Sidebar.vue'
	import Header from '@/components/layout/Header.vue'

	export default {
		name: 'Home',
		components: {
			Sidebar,
			Header
		},
		data() {
			return {
				currentTheme: 'light' // 统一在此处管理主题状态
			}
		},
		methods: {
			// 处理 Header 组件传来的切换主题请求
			handleThemeToggle() {
				this.currentTheme = this.currentTheme === 'light' ? 'dark' : 'light';
			}
		}
	}
</script>

<style scoped>
	.app-wrapper {
		height: 100vh;
		width: 100vw;
	}

	.main-container {
		display: flex;
		flex-direction: column;
	}

	.el-main {
		background-color: #f0f2f5;
		padding: 20px;
		transition: background-color 0.3s;
	}

	.el-main.is-dark {
		background-color: #121215;
	}
</style>
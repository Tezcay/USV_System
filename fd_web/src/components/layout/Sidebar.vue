<template>
	<div class="sidebar-aside">
		<div class="sidebar-logo">
			<i class="el-icon-ship"></i>
			<span>USV 巡防系统</span>
		</div>

		<el-menu :default-active="$route.path" router background-color="#304156" text-color="#bfcbd9"
			active-text-color="#409EFF">

			<el-menu-item index="/home"><i class="el-icon-data-board"></i><span>系统首页</span></el-menu-item>

			<el-submenu index="sub1">
				<template slot="title"><i class="el-icon-box"></i><span>资源管理</span></template>
				<el-menu-item index="/device"><i class="el-icon-ship"></i>设备管理</el-menu-item>
				<el-menu-item index="/route"><i class="el-icon-map-location"></i>航线管理</el-menu-item>
			</el-submenu>

			<el-submenu index="sub2">
				<template slot="title"><i class="el-icon-monitor"></i><span>巡防业务</span></template>
				<el-menu-item index="/task"><i class="el-icon-s-promotion"></i>任务执行</el-menu-item>
				<el-menu-item index="/result"><i class="el-icon-document-checked"></i>结果归档</el-menu-item>
			</el-submenu>

			<el-menu-item index="/home/log"><i class="el-icon-notebook-2"></i><span>操作日志</span></el-menu-item>
		</el-menu>
	</div>
</template>

<style scoped>
	.sidebar-aside {
		height: 100%;
		background: #304156;
	}

	.sidebar-logo {
		height: 60px;
		background: #2b2f3a;
		display: flex;
		align-items: center;
		justify-content: center;
		color: #fff;
		font-weight: bold;
	}

	.sidebar-logo i {
		margin-right: 10px;
		color: #409EFF;
		font-size: 20px;
	}

	.el-menu {
		border: none;
	}
</style>
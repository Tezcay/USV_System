import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'
import Dashboard from '../views/Dashboard.vue'
import DeviceList from '../views/DeviceList.vue'
import RouteMap from '../views/RouteMap.vue'
import TaskList from '../views/TaskList.vue'
import ResultList from '../views/ResultList.vue'

Vue.use(VueRouter)

const routes = [{
		path: '/',
		redirect: '/login'
	}, // 默认打开根目录时，强制踢回登录页
	{
		path: '/login',
		component: Login
	}, // 访问 /login 显示登录页
	{
		path: '/home',
		component: Home,
		// 子路由配置：当访问 /home 时，默认把 Dashboard 塞进 Home 的 router-view 里
		children: [{
				path: '', // 默认子路由
				name: 'Dashboard',
				component: Dashboard,
				meta: {
					title: '系统概览'
				}
			},
			{
				path: '/device',
				name: 'DeviceList',
				component: DeviceList,
				meta: {
					title: '无人船设备管理'
				} // 对应需求 3.2
			},
			{
				path: '/route',
				name: 'RouteMap',
				component: RouteMap,
				meta: {
					title: '巡防航线管理'
				} // 对应需求 3.3
			},
			{
				path: '/task',
				name: 'TaskList',
				component: TaskList,
				meta: {
					title: '巡防任务管理'
				} // 对应需求 3.4
			},
			{
				path: '/result',
				name: 'ResultList',
				component: ResultList,
				meta: {
					title: '巡防结果管理'
				} // 对应需求 3.5
			}
		]
	} // 访问 /home 显示主页
]

const router = new VueRouter({
	routes
})

export default router
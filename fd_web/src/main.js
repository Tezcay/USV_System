import Vue from 'vue'
import App from './App.vue'
import router from './router'

// 引入 Element UI
import ElementUI from 'element-ui'
// 引入 Element 样式
import 'element-ui/lib/theme-chalk/index.css'
// 引入 Axios（重点！）
import axios from 'axios';

// 全局注册 Element UI
Vue.use(ElementUI)

Vue.config.productionTip = false

// 把 axios 绑死在 Vue 的原型链上（超级重点！）
// 这样在任何页面写 this.$axios，Vue 都能认出它了
Vue.prototype.$axios = axios;

new Vue({
	router,
	render: h => h(App),
}).$mount('#app')
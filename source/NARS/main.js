import Vue from 'vue'
import App from './App'
import xflSelect from './components/xfl-select/xfl-select.vue';
import commonFunction from '@/static/commonFunction.js'
import webconfig from '@/config/webconfig.js'
// Vue.prototype.$request = request;
// Vue.prototype.$H = request;
Vue.config.productionTip = false
Vue.component('xfl-select', xflSelect);
Vue.prototype.commonFunction = commonFunction//挂载共用函数
Vue.prototype.$cf = commonFunction//挂载共用函数2


App.mpType = 'app'
webconfig().then(() => {
	const app = new Vue({
		    ...App
		})
		app.$mount()
})


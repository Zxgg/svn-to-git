import Vue from 'vue'

import Cookies from 'js-cookie'
// import '@/assets/externalIcons'//引入阿里图标库
import 'normalize.css/normalize.css' // a modern alternative to CSS resets

import Element from 'element-ui'
import './assets/styles/element-variables.scss'
// import './assets/styles/theme/elmentUITheme.css'

import '@/assets/styles/index.scss' // global css
import '@/assets/styles/ruoyi.scss' // ruoyi css
import App from './App'
import store from './store'
import router from './router'
import permission from './directive/permission'
import webconfig from '@/webconfig/webconfig'

import Pagination from "@/components/Pagination";
import Fragment from 'vue-fragment'


import './assets/icons' // icon
import './permission' // permission control
import { getDicts } from "@/api/system/dict/data";
import commonFunction from '@/common/commonFunction.js'
import '@/assets/externalIcons/iconfonts/iconfont.css'
import { getConfigKey } from "@/api/system/config";
import { parseTime, resetForm, addDateRange, selectDictLabel, selectDictLabelByLable, selectDictLabels, download, handleTree, getFirstUrlFromJson, textLimit, unique, unique2, unique3, unique4, unique5, getLastEditUserInfo } from "@/utils/ruoyi";
//自定义表格工具扩展
import RightToolbar from "@/components/RightToolbar"
//视频
import VideoPlayer from 'vue-video-player'
require('video.js/dist/video-js.css')
require('vue-video-player/src/custom-theme.css')
Vue.use(VideoPlayer)
Vue.use(Fragment.Plugin)
Vue.component('Pagination', Pagination)
Vue.component('RightToolbar', RightToolbar)
// import VideoPlayer from 'vue-video-player'
// import 'video.js/dist/video-js.css'
// require('video.js/dist/video-js.css')
// require('vue-video-player/src/custom-theme.css')
// import Video from 'video.js'
// import 'videojs-flash'

// //引入 hls,视频直播(m3u8)必须引入的
// // import 'videojs-contrib-hls'
// //播放rtmp视频
// // import 'videojs-flash'
// Vue.use(VideoPlayer)
// Vue.prototype.$video = Video
//视频



// >>全局方法挂载
Vue.prototype.$iconCSSList = []//挂载icon样式列表
Vue.prototype.$WebScoket = new Vue()//挂载websocket连接器
Vue.prototype.$Notice = new Vue()//挂载事件通知器
Vue.prototype.commonFunction = commonFunction//挂载共用函数
Vue.prototype.$cf = commonFunction//挂载共用函数2
Vue.prototype.$store = store
// <<全局方法挂载

Vue.prototype.getDicts = getDicts
Vue.prototype.getConfigKey = getConfigKey
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.selectDictLabelByLable = selectDictLabelByLable
Vue.prototype.selectDictLabels = selectDictLabels
Vue.prototype.getFirstUrlFromJson = getFirstUrlFromJson
Vue.prototype.download = download
Vue.prototype.handleTree = handleTree
Vue.prototype.textLimit = textLimit//限制字符串最大长度
Vue.prototype.unique = unique//去除数组中的重复项
Vue.prototype.unique2 = unique2//去除数组中的重复项  根据两个属性定义是否重复
Vue.prototype.unique3 = unique3//去除数组中的重复项  根据三个属性定义是否重复
Vue.prototype.unique4 = unique4//去除数组中的重复项  根据四个属性定义是否重复
Vue.prototype.unique5 = unique5//去除数组中的重复项  根据四个属性定义是否重复
Vue.prototype.getLastEditUserInfo = getLastEditUserInfo//获取数据中的最后修改信息
Vue.prototype.msgSuccess = function (msg) {
  this.$message({ showClose: true, message: msg, type: "success" });
}

Vue.prototype.msgError = function (msg) {
  this.$message({ showClose: true, message: msg, type: "error" });
}

Vue.prototype.msgInfo = function (msg) {
  this.$message.info(msg);
}
// Vue.component('Pagination', Pagination)
// Vue.component('RightToolbar', RightToolbar)
Vue.use(permission)

Vue.directive('loadmore', {
  bind (el, binding) {
    const selectWrap = el.querySelector('.el-table__body-wrapper')
    selectWrap.addEventListener('scroll', function () {
      let sign = 10
      const scrollDistance = this.scrollHeight - this.scrollTop - this.clientHeight
      if (scrollDistance <= sign) {
        binding.value()
      }
    })
  }
})

// 全局组件挂载  先加载全局配置
webconfig(store).then(() => {

  /**
   * If you don't want to use mock-server
   * you want to use MockJs for mock api
   * you can execute: mockXHR()
   *
   * Currently MockJs will be used in the production environment,
   * please remove it before going online! ! !
   */

  Vue.use(Element, {
    size: Cookies.get('size') || 'medium' // set element-ui default size
  })

  Vue.config.productionTip = false

  new Vue({
    el: '#app',
    router,
    store,
    render: h => h(App)
  })
})


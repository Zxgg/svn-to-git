import axios from 'axios'
import { Notification, MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'
import errorCode from '@/utils/errorCode'
import webconfig from '@/webconfig/webconfig'
import configInfo from '@/webconfig/configInfo'
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
const configPath = process.env.BASE_URL + 'config.json'
// 创建axios实例
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  // baseURL: configInfo.DBRestAPIEndPointUri,
  // 超时
  timeout: 10000
})
// request拦截器
service.interceptors.request.use(config => {
  if (config.url !== undefined && config.url !== null && config.url.indexOf('http') === 0) {
    //url中直接包含http 不需要再加基路由地址
    config.baseURL = ''
  }
  else {
    config.baseURL = configInfo.DBRestAPIEndPointUri
  }
  if (config.ContentType === 'multipart/form-data') {
    config.headers['Content-Type'] = config.ContentType
  } else if (config.ContentType === 'application/x-www-form-urlencoded') {
    config.headers['Content-Type'] = config.ContentType
  }

  // 是否需要设置 token
  const isToken = (config.headers || {}).isToken === false
  if (getToken() && !isToken) {
    // config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    config.headers['Authorization'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    // console.log('token:', getToken())
  }
  return config
}, error => {
  console.log('err:', error)
  Promise.reject(error)
})

// 响应拦截器
service.interceptors.response.use(res => {
  // 未设置状态码则默认成功状态
  const code = res.data.code || 200;
  // 获取错误信息
  const msg = errorCode[code] || res.data.msg || errorCode['default']
  if (code === 401) {
    MessageBox.confirm('登录状态已过期，您可以继续留在该页面，或者重新登录', '系统提示', {
      confirmButtonText: '重新登录',
      cancelButtonText: '取消',
      type: 'warning'
    }
    ).then(() => {
      store.dispatch('LogOut').then(() => {
        // location.href = '/index';
        location.href = location.pathname
      })
    })
  } else if (code === 500) {
    Message({
      message: msg,
      type: 'error'
    })
    return Promise.reject(new Error(msg))
  } else if (code !== 200) {
    Notification.error({
      title: msg
    })
    return Promise.reject('error')
  } else {
    return res.data
  }
},
  error => {
    // console.log('err:' + error.config.url)
    let { message } = error;
    if (message == "Network Error") {
      message = "后端接口连接异常";
      console.log('err:' + error.config.url + ',' + message)
    }
    else if (message.includes("timeout")) {
      message = "系统接口请求超时";
      console.log('err:' + error.config.url + ',' + message)
    }
    else if (message.includes("Request failed with status code")) {
      message = "系统接口" + message.substr(message.length - 3) + "异常";
      console.log('err:' + error.config.url + ',' + message)
    }
    else {
      console.log('err:' + error.config.url)
    }
    Message({
      message: message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service

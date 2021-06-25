'use strict'
import axios from 'axios'
import $ from 'jquery'
import { Message } from 'element-ui'

axios.defaults.headers.post['Content-Type'] = 'application/json'
axios.defaults.timeout = 15000 * 10
axios.defaults.responseType = 'application/json'
axios.interceptors.request.use(config => {
  // loading
  config.headers['Version'] = 'V1.0.0'

  return config
}, error => {
  return Promise.reject(error)
})

axios.interceptors.response.use(response => {
  return response
}, error => {
  return Promise.resolve(error.response)
})

function checkStatus (response) {
  // loading
  // 如果http状态码正常，则直接返回数据
  if (response && (response.status === 200 || response.status === 304 || response.status === 400)) {
    return response
    // 如果不需要除了data之外的数据，可以直接 return response.data
  }
  // 异常状态下，把错误信息返回去
  return {
    status: -404,
    msg: '网络异常'
  }
}

function checkCode (res) {
  // 如果code异常(这里已经包括网络错误，服务器错误，后端抛出的错误)，可以弹出一个错误提示，告诉用户
  if (res.status === -404) {
    alert(res.msg)
  }
  if (res.data && (!res.data.success)) {
    alert(res.data.error_msg)
  }
  return res
}

export default {
  apiGet (url, data) {
    return new Promise((resolve, reject) => {
      axios.get(url).then((response) => {
        resolve(response)
      }, (error) => {
        reject(error)
        _g.closeGlobalLoading()
        Message({
          message: res.message || 'Error',
          type: 'error',
          duration: 5 * 1000
        })
      })
    })
  },
  apiPost (url, data) {
    return new Promise((resolve, reject) => {
      var jsonData = JSON.stringify(data)

      axios.post(url, jsonData
      ).then((response) => {
        resolve(response)
      }).catch((error) => {
        console.log(typeof error)
        console.log('输出：', error.message)
        reject(error)
        Message({
          message: error || 'Error',
          type: 'error',
          duration: 5 * 1000
        })
      })
    })
  },
  apiPostFile (url, data) {
    let config = {
      // 添加请求头
      headers: { 'Content-Type': 'multipart/form-data' },
      headers: { 'Version': 'V1.0.0' }
      // 添加上传进度监听事件
      /* onUploadProgress: e => {
        var completeProgress = ((e.loaded / e.total * 100) | 0) + "%";
        this.progress = completeProgress;
      } */
    }

    return new Promise((resolve, reject) => {
      axios.post(url, data, config
      ).then((response) => {
        resolve(response)
      }).catch((error) => {
        console.log(typeof error)
        console.log('输出：', error.message)
        reject(error)
        Message({
          message: error || 'Error',
          type: 'error',
          duration: 5 * 1000
        })
      })
    })
  },
  apiDelete (url, id) {
    return new Promise((resolve, reject) => {
      axios.delete(url + id).then((response) => {
        resolve(response)
      }, (error) => {
        reject(error)
        _g.closeGlobalLoading()
        Message({
          message: error || 'Error',
          type: 'error',
          duration: 5 * 1000
        })
      })
    })
  },
  apiDeleteBatch (url, param) {
    return new Promise((resolve, reject) => {
      axios.delete(url, {
        data: param
      }).then((response) => {
        resolve(response)
      }, (error) => {
        reject(error)
        _g.closeGlobalLoading()
        Message({
          message: error || 'Error',
          type: 'error',
          duration: 5 * 1000
        })
      })
    })
  },
  apiPut (url, id, obj) {
    return new Promise((resolve, reject) => {
      if (id != null && id !== undefined) {
        url = url + id
      }
      axios.put(url, obj).then((response) => {
        resolve(response)
      }, (error) => {
        _g.closeGlobalLoading()
        Message({
          message: error || 'Error',
          type: 'error',
          duration: 5 * 1000
        })
        reject(error)
      })
    })
  },

  apiGetOrgPost (url, data) {
    return new Promise((resolve, reject) => {
      var jsonData = JSON.stringify(data)
      let timeout = 600000
      axios.post(url, jsonData, { timeout: timeout }
      ).then((response) => {
        resolve(response)
      }).catch((error) => {
        console.log(typeof error)
        console.log('输出：', error.message)
        reject(error)
        Message({
          message: error || 'Error',
          type: 'error',
          duration: 5 * 1000
        })
      })
    })
  },

  apiAjaxGet (url) {
    return new Promise((resolve, reject) => {
      $.ajax({
        type: 'get',
        headers: {
          Accept: 'text/html,application/xhtml+xml,application/xml,image/webp,image/apng,*/*'
        },
        url: url,
        success: function (data) {
          resolve(data)
        },
        error: function (data) {
          console.log(data)
          // alert('错误')
        }

      }).then(res => {
        // resolve(res)
      })
    })
  }

}

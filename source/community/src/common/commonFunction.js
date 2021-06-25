// 1.引入vue
import Vue from 'vue'
import store from '@/store'
import configInfo from '@/webconfig/configInfo'
// import {
//   getRtsp,
//   deleteHlsStream
// } from '@/api/smartVideo/vcnrtsp'
import {
  getDeviceBySN
} from '@/api/smartVideo/device'
import {
  setStartStream,
  startLiveGBSRecord,
} from '@/api/LiveGBS'
import commonFunction from '@/common/commonFunction'
// import { Callbacks } from 'jquery'
// 2.新创建一个vue实例
let v = new Vue()
// 枚举 示例

export default {
  // 日期转换时间戳
  TimeToTimeStamp: function (time) {
    var date = new Date(time)
    // 有三种方式获取
    var time1 = date.getTime()
    return time1
  },
  // 时间戳转换日期
  getTimeByTimeStamp: function (num) {
    let len = (num + ' ').length // 隐式转换为字符串并拿到该字符串的长度;
    let data = '' // 定义一个空字符串
    if (len === 10) { // 当我传的实参===> 时间戳位数等于10时,单位是秒,我需要乘以1000变为毫秒
      data = num * 1000
    } else { // 否则时间戳位数为13时,单位是毫秒,无需变换
      data = num
    }
    var date = new Date(parseInt(data)) // 格式化时间戳,变为国际标准模式====> 如: Fri Apr 19 2019 13:42:34 GMT+0800 (中国标准时间)
    // 然后直接调用时间对象的各种方法,即可得到相应的年-月-日 时:分:秒
    let Y = date.getFullYear() + '-'
    let M = (date.getMonth() + 1 < 10
      ? '0' + (date.getMonth() + 1)
      : date.getMonth() + 1) + '-'
    let D = date.getDate() + ' '
    let h = date.getHours() + ':'
    let m = date.getMinutes() + ':'
    let s = date.getSeconds()
    return Y + M + D + h + m + s
  },

  Guid: function () {
    function S4 () {
      return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1)
    }
    return (S4() + S4() + '-' + S4() + '-' + S4() + '-' + S4() + '-' + S4() + S4() + S4())
  },

  getGuid: function () {
    var guid = new Guid()
    return guid
  },
  dialogSuccess: function (text) {
    v.$alert(text, '提示', {
      confirmButtonText: '确定'
      // callback: action => {
      //   // v.$message({
      //   //   type: 'success',
      //   //   message: `action: ${action}`
      //   // })
      // }
    })
  },
  dialogError: function (text) {
    v.$alert(text, '提示', {
      confirmButtonText: '确定'
      // callback: action => {
      //   // v.$message({
      //   //   type: 'warning',
      //   //   message: `error: ${action}`
      //   // })
      // }
    })
  },

  getNowTime: function () {
    var now = new Date()
    var year = now.getFullYear() // 得到年份
    var month = now.getMonth() // 得到月份
    var date = now.getDate() // 得到日期
    var hour = now.getHours() // 得到小时
    var minu = now.getMinutes() // 得到分钟
    var sec = now.getSeconds() // 得到秒

    month = month + 1
    if (month < 10) month = '0' + month
    if (date < 10) date = '0' + date
    if (hour < 10) hour = '0' + hour
    if (minu < 10) minu = '0' + minu
    if (sec < 10) sec = '0' + sec

    var time = ''
    time = year + '-' + month + '-' + date + ' ' + hour + ':' + minu + ':' + sec + ' '
    return time
  },
  getLastWeekTime: function (index) {
    var nowdate = new Date()
    // 获取系统前一周的时间
    var oneweekdate = new Date(nowdate - index * 24 * 3600 * 1000)
    var y = oneweekdate.getFullYear()
    var m = oneweekdate.getMonth() + 1
    var d = oneweekdate.getDate()
    var formatwdate = y + '-' + m + '-' + d
    return formatwdate
  },
  getOperationType: function (operationType) {
    let operationTypeName = ''
    switch (operationType) {
      case 1 | '1':
        operationTypeName = '增加'
        break
      case 2 | '2':
        operationTypeName = '删除'
        break
      case 3 | '3':
        operationTypeName = '修改'
        break
      case 4 | '4':
        operationTypeName = '播放'
        break
    }
    return operationTypeName
  },

  getOnlineStateType: function (onlineState) {
    let onlineStateName = ''
    switch (onlineState) {
      case 1 | '1':
        onlineStateName = '在线'
        break
      case 2 | '2':
        onlineStateName = '离线'
        break
    }
    return onlineStateName
  },

  getDataSourceType: function (operationType) {
    let operationTypeName = ''
    switch (operationType) {
      case '1':
        operationTypeName = '上传'
        break
      case '2':
        operationTypeName = '创建'
        break
    }
    return operationTypeName
  },

  getCaptureType: function (CaptureType) {
    let CaptureTypeName = ''
    switch (CaptureType) {
      case 1 | '1':
        CaptureTypeName = '固定枪机'
        break
      case 2 | '2':
        CaptureTypeName = '球机'
        break
      case 3 | '3':
        CaptureTypeName = '筒机'
        break
      case 4 | '4':
        CaptureTypeName = '有云台枪机'
        break
      case 5 | '5':
        CaptureTypeName = '半球固定摄像机'
        break
    }
    return CaptureTypeName
  },

  getMonitorPointType: function (MonitorPointType) {
    let MonitorPointTypeName = ''
    switch (MonitorPointType) {
      case 1 | '1':
        MonitorPointTypeName = '一类监控点'
        break
      case 2 | '2':
        MonitorPointTypeName = '二类监控点'
        break
      case 3 | '3':
        MonitorPointTypeName = '三类监控点'
        break
      case 4 | '4':
        MonitorPointTypeName = '公安内部监控点'
        break
      case 5 | '5':
        MonitorPointTypeName = '其他监控点'
        break
      case 0 | '0':
        MonitorPointTypeName = '所有'
        break
    }
    return MonitorPointTypeName
  },
  getCaptureFunctionType: function (CaptureFunctionType) {
    let CaptureFunctionTypeName = ''
    switch (CaptureFunctionType) {
      case 1 | '1':
        CaptureFunctionTypeName = '车辆卡口'
        break
      case 2 | '2':
        CaptureFunctionTypeName = '人员卡口'
        break
      case 4 | '4':
        CaptureFunctionTypeName = '多功能智能摄像机'
        break
      case 5 | '5':
        CaptureFunctionTypeName = '普通监控'
        break
      case 6 | '6':
        CaptureFunctionTypeName = '电子警察'
        break
      case 7 | '7':
        CaptureFunctionTypeName = '违停摄像机'
        break
      case 8 | '8':
        CaptureFunctionTypeName = '门禁'
        break
      case 99 | '99':
        CaptureFunctionTypeName = '其他'
        break
    }
    return CaptureFunctionTypeName
  },
  getOnlineStatus: function (OnlineStatus) {
    let OnlineStatusName = ''
    switch (OnlineStatus) {
      case 0 | '0':
        OnlineStatusName = '离线'
        break
      case 1 | '1':
        OnlineStatusName = '在线'
        break
      case 3 | '3':
        OnlineStatusName = '休眠'
        break
    }
    return OnlineStatusName
  },
  getYesOrNo: function (flag) {
    let strFlag = ''
    if (this.checkIsNotNull(flag)) {
      strFlag = flag + ''
    }
    let TopstateName = '否'
    switch (strFlag) {
      case 'true':
        TopstateName = '是'
        break
      case 'false':
        TopstateName = '否'
        break
    }
    return TopstateName
  },
  UnicodeToChineseStr: function (unicodeStr) {
    if (unicodeStr === '') {
      return 'Please input hexadecimal Unicode'
    }
    unicodeStr = unicodeStr.split('\\u')
    let chineseStr = ''
    // for (let i = 0, iLength = unicodeStr.length; i < iLength; i++) {
    //   chineseStr += String.fromCharCode(parseInt(unicodeStr[i], 16))
    // }
    chineseStr += String.fromCharCode(parseInt(unicodeStr[1], 16))
    return chineseStr
  },
  // 获取日期字符串 格式为 yyyy-MM-dd
  DataFormat: function (time, format = 'yyyy-MM-dd hh:mm:ss') {
    if (time === null) return ''
    var data = new Date(time)
    var o = {
      'M+': data.getMonth() + 1, // month
      'd+': data.getDate(), // day
      'h+': data.getHours(), // hour
      'm+': data.getMinutes(), // minute
      's+': data.getSeconds(), // second
      'q+': Math.floor((data.getMonth() + 3) / 3), // quarter
      'S': data.getMilliseconds() // millisecond
    }

    if (/(y+)/.test(format)) {
      format = format.replace(RegExp.$1, (data.getFullYear() + '').substr(4 - RegExp.$1.length))
    }
    var week = {
      '0': '一',
      '1': '二',
      '2': '三',
      '3': '四',
      '4': '五',
      '5': '六',
      '6': '日'
    }
    if (/(E+)/.test(format)) {
      format = format.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? '星期' : '周') : '') + week[this.getDay() + ''])
    }

    for (var k in o) {
      if (new RegExp('(' + k + ')').test(format)) {
        format = format.replace(RegExp.$1, RegExp.$1.length === 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length))
      }
    }
    return format
  },
  addThreeS: function (dateStr, number) { // dateStr格式为yyyy-mm-dd hh:mm:ss
    var dt = new Date(dateStr.replace(/-/, '/')) // 将传入的日期格式的字符串转换为date对象 兼容ie
    // var dt=new Date(dateStr);//将传入的日期格式的字符串转换为date对象 非ie
    var now = new Date(dt.getTime() + number * 1000) // 将转换之后的时间减去两秒
    var year = now.getFullYear() // 得到年份
    var month = now.getMonth() // 得到月份
    var date = now.getDate() // 得到日期
    var hour = now.getHours() // 得到小时
    var minu = now.getMinutes() // 得到分钟
    var sec = now.getSeconds() // 得到秒
    month = month + 1
    if (month < 10) month = '0' + month
    if (date < 10) date = '0' + date
    if (hour < 10) hour = '0' + hour
    if (minu < 10) minu = '0' + minu
    if (sec < 10) sec = '0' + sec

    var time = ''
    time = year + '-' + month + '-' + date + ' ' + hour + ':' + minu + ':' + sec + ' '
    return time
  },
  GetDataList: function (startTime, endTime, format = 'yyyy-MM-dd') {
    let timeListStr = []
    if (this.checkIsNotNull(startTime) && this.checkIsNotNull(endTime)) {
      let count = 365
      let startTimeData = new Date(startTime)
      let endTimeData = new Date(endTime)
      let timeTemp = new Date(startTime)

      while (count > 0 && timeTemp <= endTimeData) {
        timeListStr.push(this.DataFormat(timeTemp, format))
        count--
        timeTemp.setTime(timeTemp.getTime() + 1 * 24 * 3600 * 1000)
      }
    } else { return null }
    return timeListStr
  },
  checkIsNotNull: function (checkValue) {
    if (checkValue !== undefined && checkValue !== null) return true
    return false
  },
  getGbCodeBySn: async function (cameraCode) {
    let gbCode = ''
    let deviceEntity = {
      'snList': cameraCode
    }
    let deviceResponse = await getDeviceBySN(deviceEntity)
    if (deviceResponse.status === '200' && deviceResponse.data.length > 0) {
      if (deviceResponse.data[0].uiStatus != 0)
        gbCode = deviceResponse.data[0].gbCode
    }
    return gbCode;
  },
  getHlsStreamByCameraCode: async function (cameraCode) { // 获取实时播放流 此方法返回的参数中多一个streamid  （cameraCode国标设备编号）
    let url = ''
    let streamid = ''
    var videoType = configInfo.videoType
    let gbCode = await commonFunction.getGbCodeBySn(cameraCode)//获取国标编码
    // let code = cameraCode.split('#')
    if (gbCode == '')
      return
    let dataInfo = {
      serial: configInfo.deviceserial,
      code: gbCode
    }
    var response = await setStartStream(dataInfo)


    let LiveGBSURl = configInfo.LiveGBS.split('/api');
    console.log('response:', response)
    if (response) {
      if (videoType === 3) {
        if (response.WS_FLV) {
          url = response.WS_FLV
          // if (LiveGBSURl.length > 1)
          //   url = response.WS_FLV + "&" + LiveGBSURl[0] + response.SnapURL
          // else
          //   url = response.WS_FLV
        }
      }
      if (videoType === 2) {
        if (response.RTMP) {
          url = response.RTMP
        }
      }
      if (videoType === 1) {
        if (response.HLS) {
          url = response.HLS
        }
      }
      streamid = response.StreamID
    }

    let returnData = {
      url: url,
      streamid: streamid
    }

    console.log('url8888:', url)

    return url
  },
  getHlsHistoryStreamByCameraCodeForContorl: async function (cameraCode, startTime, endTime) { // 获取历史视频 流   cameraCode国标设备编号 startTime和endTime 格式为 YYYY-MM-DDTHH:mm:ss
    let urls = ''
    // let code = cameraCode.split('#')
    let gbCode = await commonFunction.getGbCodeBySn(cameraCode)//获取国标编码
    if (gbCode == '')
      return
    let datainfo = {
      serial: configInfo.deviceserial,
      starttime: startTime,
      endtime: endTime,
      // code: code[0]
      code: gbCode
    }
    var response = await startLiveGBSRecord(datainfo)
    let LiveGBSURl = configInfo.LiveGBS.split('/api');
    var videoType = configInfo.videoType
    if (response) {
      if (videoType === 3) {
        if (response.WS_FLV) {
          urls = response.WS_FLV
          // if (LiveGBSURl.length > 1)
          //   urls = response.WS_FLV + "&" + LiveGBSURl[0] + response.SnapURL
          // else
          //   urls = response.WS_FLV
        }
      }
      if (videoType === 2) {
        if (response.RTMP) {
          urls = response.RTMP
        }
      }
      if (videoType === 1) {
        if (response.HLS) {
          urls = response.HLS
        }
      }
    }
    let returnData = {
      url: urls,
      streamid: response.StreamID
    }
    commonFunction.AddVideoPlayLog(cameraCode)
    // url = 'http://ivi.bupt.edu.cn/hls/cctv2hd.m3u8'
    return returnData
  },
  GetImageBase64_AddWaterMarker (url, text = '', font = '16px microsoft yahei', textColor = 'rgba(255, 255, 255, 0.5)', callback) {
    // store.getters.getCurrentOrg.code
    if (text !== 'noText' && this.checkIsNotNull(store.getters.name)) {
      let user = store.getters.name
      text = user.name + ' '
    }

    if (url === '') { return }
    function getBase64Image (img) {
      // 渲染
      var _canvas = document.createElement('canvas')
      console.info("width: " + img.width);
      _canvas.width = img.width
      _canvas.height = img.height
      var context = _canvas.getContext('2d')
      context.drawImage(img, 0, 0, _canvas.width, _canvas.height)
      // 加水印
      context.rotate((-40 * Math.PI) / 180) // 水印初始偏转角度
      context.font = font || '16px microsoft yahei'
      context.fillStyle = textColor || '#FFFFFF'// 'rgba(180,180,180,0.3)'
      for (let i = 1; i < 20; i++) {
        for (let j = 1; j < i + 1; j++) {
          context.fillText(text, -600 + j * 350, i * 100)
        }
      }
      img.setAttribute('crossOrigin', 'Anonymous')
      // 导出base64
      var base64 = _canvas.toDataURL('image/jpg')
      return base64
    }
    return new Promise(function (resolve, reject) {
      var img = new Image()
      img.crossOrigin = 'Anonymous'// 解决canvas.toDataURL图片跨域问题
      img.setAttribute('crossOrigin', 'Anonymous')
      // img.crossOrigin = '*'
      // img.crossOrigin = ''
      img.onload = function () {
        resolve(getBase64Image(img))
      }
      img.src = url
    })
  },
  getListFromString (str, separChar = ',') {
    if (this.checkIsNotNull(str) && str.length > 0) {
      return str.split(separChar)
    } else {
      return []
    }
  },
  getStringFromList (list, separChar = ',') {
    if (this.checkIsNotNull(list) && list.length > 0) {
      return list.join(separChar)
    } else {
      return ''
    }
  },
  /**
* @description 获取当前时间 单位:秒
*/
  getsecond () {
    return parseInt(new Date().getTime() / 1000)
  },
  /**
* 将时间戳或者中国标准时间处理成 2018-05-01 00:00:00
* @param {时间戳或者中国标准时间} timestamp
* @param {一状态 } state   ture要时分秒  false不要时分秒
*/
  timestampToTime (timestamp, state) {
    var date = new Date(timestamp)
    var Y = date.getFullYear() + '-'
    console.info(date.getMonth() + 1)
    var M = (date.getMonth() + 1) + '-'
    var D = date.getDate() + ' '
    var h = date.getHours() + ':'
    var m = date.getMinutes() + ':'
    var s = date.getSeconds() + ''
    return state ? Y + M + D + h + m + s : Y + M + D
  }
}
// 去除收尾指定字符
String.prototype.trim = function (char, type = 'right') {
  if (char) {
    if (type == 'left') {
      return this.replace(new RegExp('^\\' + char + '+', 'g'), '');
    } else if (type == 'right') {
      return this.replace(new RegExp('\\' + char + '+$', 'g'), '');
    }
    return this.replace(new RegExp('^\\' + char + '+|\\' + char + '+$', 'g'), '');
  }
  return this.replace(/^\s+|\s+$/g, '');
};



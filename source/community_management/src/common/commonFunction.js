// 1.引入vue
import Vue from 'vue'
import store from '@/store'
import configInfo from '@/webconfig/configInfo'
import {
  getRtsp,
  deleteHlsStream
} from '@/api/smartVideo/vcnrtsp'
import {
  getDeviceBySN
} from '@/api/smartVideo/device'
import {
  setStartStream,
  startLiveGBSRecord,
} from '@/api/LiveGBS'
import commonFunction from '@/common/commonFunction'
// 2.新创建一个vue实例
let v = new Vue()
// 枚举 示例
var cloneObj = function (obj) {
  var newObj = {};
  if (obj instanceof Array) {
    newObj = [];
  }
  for (var key in obj) {
    var val = obj[key];
    //newObj[key] = typeof val === 'object' ? arguments.callee(val) : val; //arguments.callee 在哪一个函数中运行，它就代表哪个函数, 一般用在匿名函数中。  
    newObj[key] = typeof val === 'object' ? cloneObj(val) : val;
  }
  return newObj;
};
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
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
    let D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + '-'
    let h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':'
    let m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':'
    let s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()) + ''
    return Y + M + D + h + m + s
  },
  // 时间转换成  yyyy-mm-ddThh:mm:ss  格式
  timeToStringT: function (now) {
    // var now = new Date()
    var time = ''
    if (now !== null && now !== undefined) {
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

      time = year.toString() + '-' + month.toString() + '-' + date.toString() + 'T' + hour.toString() + ':' + minu.toString() + ':' + sec.toString()
    }
    return time
  },
  padLeft: function (numOrstr, num, char) {
    if (char === null) char = ' '
    if (char.length === 0) char = ' '
    let result = numOrstr + ''
    if (result != null && result.length > 0 && result.length < num) {
      while (result.length < num) {
        result = char + result
      }
    }
    return result
  },
  returnJson (url) {
    if (url !== '' && url !== null && url !== undefined) {
      return JSON.parse(url)
    }
    else {
      return []
    }
  },
  getNumGuid: function () {
    function S4 () {
      return (((1 + Math.random()) * 0x10000) | 0).toString(10)
    }
    return parseInt((S4() + S4() + S4()).substring(0, 13))
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
  getDateSpan (date1, date2, type = 'day') {
    var dt1 = new Date(date1)
    var dt2 = new Date(date2)
    let conuntValue = 1000 * 60 * 60 * 24
    if (type === 'hour') {
      //小时
      conuntValue = 1000 * 60 * 60
    }
    var result = (dt2.getTime() - dt1.getTime()) / conuntValue;/*不用考虑闰年否*/
    return result
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
  DataFormat: function (time, format = 'yyyy-MM-dd HH:mm:ss') {
    if (time === null) return ''
    var data = new Date(time)
    var o = {
      'M+': data.getMonth() + 1, // month
      'd+': data.getDate(), // day
      'H+': data.getHours(), // hour
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

  // 删除设备转流服务任务
  stopHlsStream (cameraCode) {
    deleteHlsStream(cameraCode).then(res => {
      if (res !== undefined && res.status === '200') {

      }
    })
  },

  getHlsHistoryStreamByCameraCode: async function (cameraCode, startTime, endTime) { // 获取历史视频 流   cameraCode国标设备编号 startTime和endTime 格式为 YYYY-MM-DDTHH:mm:ss
    let gbCode = await commonFunction.getGbCodeBySn(cameraCode)//获取国标编码
    let datainfo = {
      serial: configInfo.deviceserial,
      starttime: startTime,
      endtime: endTime,
      code: gbCode
    }
    let url = ''
    var response = await startLiveGBSRecord(datainfo)
    let LiveGBSURl = configInfo.LiveGBS.split('/api');
    var videoType = configInfo.videoType
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
    }

    // commonFunction.AddVideoPlayLog(cameraCode)
    // url = 'http://ivi.bupt.edu.cn/hls/cctv2hd.m3u8'
    return url
  },
  getWaterTxt () {
    let txt = ''
    if (store.state.user.dept && store.state.user.dept.name) txt = store.state.user.dept.name + ' '
    if (store.state.user.name) txt = txt + ' ' + store.state.user.name
    let time = new Date()
    time = this.DataFormat(time, 'yyyy-MM-dd')
    // let time = this.getNowTime()
    txt = txt + ' ' + time
    return txt
  },
  GetImageBase64_AddWaterMarker (url, text = '', font = '16px microsoft yahei', textColor = 'rgba(255, 255, 255, 0.5)', callback) {
    if (text !== 'noText' && this.checkIsNotNull(store.state.user.name)) {
      // let userName = store.state.user.name
      // if (store.state.user.dept && store.state.user.dept.name) userName = store.state.user.dept.name + ' ' + userName
      // let time = getNowTime()
      // text = userName + ' ' + time
      text = this.getWaterTxt()
    }
    // text = 'admin 2020-12-09'

    if (url === '') { return }
    function getBase64Image (img) {
      // 渲染
      var _canvas = document.createElement('canvas')
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
  /**base64转Blob，用于图片上传时，将base64数据转成文件流存储 */
  dataURItoBlob (base64Data) {
    var byteString;
    if (base64Data.split(',')[0].indexOf('base64') >= 0)
      byteString = atob(base64Data.split(',')[1]);
    else
      byteString = unescape(base64Data.split(',')[1]);
    var mimeString = base64Data.split(',')[0].split(':')[1].split(';')[0];
    var ia = new Uint8Array(byteString.length);
    for (var i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
    }
    return new Blob([ia], { type: mimeString });
  },
  GetLocalRecord: async function (cameraCode, isRecording, id = '', videoType) {
    let entitySN = {
      'snList': cameraCode
    }
    let capInfo = await getDeviceBySN(entitySN)
    let response = await getRtsp(cameraCode) // rtsp流

    // if (capInfo !== null && capInfo !== undefined && capInfo.data.status === '200' && response.data != null && response.data.status === '200') {
    if (capInfo !== null && capInfo !== undefined && capInfo.data.status === '200') {
      let cameraInfo = capInfo.data.data[0]
      if (!isRecording) {
        // 获取设备数据
        var recordModel = {}
        recordModel.rtsp = 'rtsp://admin:admin123@10.16.123.224/cam/realmonitor?channel=1&subtype=0' // rtsp流
        recordModel.cameraId = cameraInfo.sn
        recordModel.cameraName = cameraInfo.captureName
        recordModel.fileName = cameraInfo.captureName + commonFunction.getNowTime()
        // recordModel.storePath = 'C:\\Users\\Public\\Downloads\\DownLoadVideo\\'
        recordModel.storePath = 'E:\\shipinMP4\\'
        recordModel.taskId = commonFunction.Guid()
        recordModel.videoType = videoType
        var messageContent = {
          msgType: 'DownLoadVideo',
          content: JSON.stringify(recordModel)
        }

        WebSocketLocal.sendMessage(messageContent)
        // this.isRecording = true
      } else {
        var recordModel_stop = {}
        recordModel_stop.rtsp = 'rtsp://admin:admin123@10.16.123.224/cam/realmonitor?channel=1&subtype=0' // rtsp流
        recordModel_stop.cameraId = cameraInfo.sn
        recordModel_stop.cameraName = cameraInfo.captureName
        recordModel_stop.fileName = cameraInfo.captureName + commonFunction.getNowTime()
        // recordModel_stop.storePath = 'C:\\Users\\Public\\Downloads\\DownLoadVideo\\'
        recordModel_stop.storePath = 'E:\\shipinMP4\\'
        recordModel_stop.taskId = id
        recordModel_stop.videoType = videoType
        var messageContent_stop = {
          msgType: 'StopDownLoadVideo',
          content: JSON.stringify(recordModel_stop)
        }
        WebSocketLocal.sendMessage(messageContent_stop)
        // this.isRecording = false
      }
    }
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
  /**获取指定最大小数位数的浮点数 */
  getFloatNum (value, length) {
    if (!(value !== undefined && value !== null && length >= 0)) return value
    let unit = 1
    if (length > 0)
      unit = Math.pow(10, length)
    return Math.round(value * unit) / unit //保留两位小数
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

    var M = (date.getMonth() + 1) + '-'
    var D = date.getDate() + ' '
    var h = date.getHours() + ':'
    var m = date.getMinutes() + ':'
    var s = date.getSeconds() + ''
    return state ? Y + M + D + h + m + s : Y + M + D
  },
  upLength (num, length) {
    return (Array(length).join('0') + num).slice(-length);
  },
  //深复制对象方法    
  cloneObj (obj) {
    return cloneObj(obj)
    var newObj = {};
    if (obj instanceof Array) {
      newObj = [];
    }
    for (var key in obj) {
      var val = obj[key];
      //newObj[key] = typeof val === 'object' ? arguments.callee(val) : val; //arguments.callee 在哪一个函数中运行，它就代表哪个函数, 一般用在匿名函数中。  
      newObj[key] = typeof val === 'object' ? cloneObj(val) : val;
    }
    return newObj;
  },
  getTimeSpanStringList (startTime, endTime, type = 'day', format = "yyyy-MM-dd") {
    let timeListStr = []
    if (this.checkIsNotNull(startTime) && this.checkIsNotNull(endTime)) {
      let endTimeData = new Date(endTime)
      let timeTemp = new Date(startTime)
      let interVal = 1 * 24 * 3600 * 1000//默认间隔是1天
      if (type === 'month') {
        format = "yyyy-MM"
      }
      else if (type === 'hour') {
        interVal = 3600 * 1000
        format = "hh"
      }
      while (timeTemp <= endTimeData) {
        timeListStr.push(this.DataFormat(timeTemp, format))
        if (type === 'month') {
          timeTemp.setMonth(timeTemp.getMonth() + 1)
        } else {
          timeTemp.setTime(timeTemp.getTime() + 1 * 24 * 3600 * 1000)
        }
      }
    } else { return null }
    return timeListStr
  },
  getDetailsDocumentType: function (detailsDocumentType) {
    let detailsDocumentTypeName = ''
    switch (detailsDocumentType) {
      case '0':
        detailsDocumentTypeName = '身份证'
        break
      case '1':
        detailsDocumentTypeName = '护照'
        break
      case '2':
        detailsDocumentTypeName = '学生证'
        break
      case '3':
        detailsDocumentTypeName = '军官证'
        break
      case '4':
        detailsDocumentTypeName = '驾照'
        break
      case '5':
        detailsDocumentTypeName = '其他'
        break
    }
    return detailsDocumentTypeName
  },
  getSex: function (Sex) {
    let SexVuel = ''
    switch (Sex) {
      case '-1':
        SexVuel = '不限'
        break
      case '0':
        SexVuel = '未知'
        break
      case '1':
        SexVuel = '男'
        break
      case '2':
        SexVuel = '女'
        break
    }
    return SexVuel
  },
  getObscuredPlate: function (obscuredPlate) {
    let obscuredPlateVule = ''
    switch (obscuredPlate) {
      case '0':
        obscuredPlateVule = '未遮挡车牌'
        break
      case '1':
        obscuredPlateVule = '遮挡车牌'
        break
      case '-1':
        obscuredPlateVule = '未知'
        break
    }
    return obscuredPlateVule
  },
  getSpecialVehicleTypeIdx: function (specialVehicleTypeIdx) {
    let specialVehicleTypeIdxVule = ''
    switch (specialVehicleTypeIdx) {
      case '0':
        specialVehicleTypeIdxVule = '非特殊车辆'
        break
      case '1':
        specialVehicleTypeIdxVule = '校车'
        break
      case '2':
        specialVehicleTypeIdxVule = '泥头车'
        break
      case '3':
        specialVehicleTypeIdxVule = '高危车'
        break
    }
    return specialVehicleTypeIdxVule
  },
  getPlateType: function (PlateType) {
    let PlateTypeVule = ''
    switch (PlateType) {
      case '-1':
        PlateTypeVule = '不限'
        break
      case '0':
        PlateTypeVule = '未知'
        break
      case '1':
        PlateTypeVule = '单层蓝牌'
        break
      case '2':
        PlateTypeVule = '单层黑牌'
        break
      case '3':
        PlateTypeVule = '单层黄牌'
        break
      case '4':
        PlateTypeVule = '双层黄牌'
        break
      case '5':
        PlateTypeVule = '白色警牌'
        break
      case '6':
        PlateTypeVule = '白色武警'
        break
      case '7':
        PlateTypeVule = '个性化车牌'
        break
      case '8':
        PlateTypeVule = '单层军牌'
        break
      case '9':
        PlateTypeVule = '双层军牌'
        break
      case '10':
        PlateTypeVule = '领事馆牌'
        break
      case '11':
        PlateTypeVule = '港牌'
        break
      case '12':
        PlateTypeVule = '农用车牌拖拉机'
        break
      case '13':
        PlateTypeVule = '澳门牌'
        break
      case '14':
        PlateTypeVule = '场内牌'
        break
      case '15':
        PlateTypeVule = '双层白色武警'
        break
      case '16':
        PlateTypeVule = '其他类型'
        break
      case '17':
        PlateTypeVule = '新能源牌'
        break
    }
    return PlateTypeVule
  },
  gatPlateColor: function (plateColor) {
    let plateColorVule = ''
    switch (plateColor) {
      case '-1':
        plateColorVule = '不限'
        break
      case '9':
        plateColorVule = '未知'
        break
      case '0':
        plateColorVule = '白'
        break
      case '2':
        plateColorVule = '黄'
        break
      case '5':
        plateColorVule = '绿'
        break
      case '6':
        plateColorVule = '蓝'
        break
      case '8':
        plateColorVule = '黑'
        break
      case '10':
        plateColorVule = '渐变绿'
        break
      case '11':
        plateColorVule = '黄绿双拼'
        break
    }
    return plateColorVule
  },
  getHeadOutSunroof: function (headOutSunroof) {
    let headOutSunroofVule = ''
    switch (headOutSunroof) {
      case '-1':
        headOutSunroofVule = '未知'
        break
      case '0':
        headOutSunroofVule = '不存在'
        break
      case '1':
        headOutSunroofVule = '存在'
        break
    }
    return headOutSunroofVule
  },
  getOptionsVehicleType: function (optionsVehicleType) {
    let optionsVehicleTypeVule = ''
    switch (optionsVehicleType) {
      case '-1':
        optionsVehicleTypeVule = '不限'
        break
      case '0':
        optionsVehicleTypeVule = '未知'
        break
      case '1':
        optionsVehicleTypeVule = '轿车'
        break
      case '2':
        optionsVehicleTypeVule = '货车'
        break
      case '3':
        optionsVehicleTypeVule = '面包车'
        break
      case '4':
        optionsVehicleTypeVule = '客车'
        break
      case '5':
        optionsVehicleTypeVule = '小货车'
        break
      case '6':
        optionsVehicleTypeVule = 'SUV'
        break
      case '7':
        optionsVehicleTypeVule = '中型客车'
        break
      case '8':
        optionsVehicleTypeVule = '摩托车'
        break
      case '9':
        optionsVehicleTypeVule = '其他'
        break
    }
    return optionsVehicleTypeVule
  },
  getOptionsCarColor: function (optionsCarColor) {
    let optionsCarColorVule = ''
    switch (optionsCarColor) {
      case '-1':
        optionsCarColorVule = '不限'
        break
      case '0':
        optionsCarColorVule = '白'
        break
      case '1':
        optionsCarColorVule = '灰'
        break
      case '2':
        optionsCarColorVule = '黄'
        break
      case '3':
        optionsCarColorVule = '粉'
        break
      case '4':
        optionsCarColorVule = '红'
        break
      case '5':
        optionsCarColorVule = '绿'
        break
      case '6':
        optionsCarColorVule = '蓝'
        break
      case '7':
        optionsCarColorVule = '棕'
        break
      case '8':
        optionsCarColorVule = '黑'
        break
      case '9':
        optionsCarColorVule = '未知'
        break
      case '10':
        optionsCarColorVule = '紫'
        break
      case '11':
        optionsCarColorVule = '橙'
        break
      case '12':
        optionsCarColorVule = '银'
        break
      case '13':
        optionsCarColorVule = '青'
        break
      case '14':
        optionsCarColorVule = '金'
        break
    }
    return optionsCarColorVule
  },
  //获取地址中的省市区三级
  getAddressArea (addressStr) {
    let resule = null
    if (addressStr && addressStr.length > 0) {
      var reg = /.+?(省|市|自治区|自治州|县|区)/g;
      resule = addressStr.match(reg)
    }
    return resule
  },

  getListByTree (obj) {
    let treedata = [];

    let temList = [];
    for (var key in obj) {
      if (key == 'remove') {
        continue;
      }
      let item = obj[key]
      if (temList.indexOf(key) == -1 && item.dictValue <= 10) {
        temList.push(key);
        let itemData = {};
        itemData.id = item.dictValue;
        itemData.label = item.dictLabel;
        itemData.children = [];
        let temList1 = [];
        for (var key1 in obj) {
          if (key1 == 'remove') {
            continue;
          }
          let item1 = obj[key1]
          if (temList1.indexOf(key1) == -1 && item1.dictValue.substring(0, 1) == item.dictValue && item1.dictValue >= 10) {
            temList1.push(key1);
            let itemData1 = {};
            itemData1.id = item1.dictValue;
            itemData1.label = item1.dictLabel;
            itemData.children.push(itemData1);

          }
        }
        treedata.push(itemData);
      }
    }
    return treedata;

  },

  findIndex (list, propName, propValue) {
    let searchIndex = -1
    if (list && list.length > 0 && propName) {
      for (let index = 0; index < list.length; index++) {
        const element = list[index];
        if (element[propName] && element[propName] === propValue) {
          searchIndex = index
          break
        }
      }
    }
    return searchIndex
  },
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
Array.prototype.remove = function (val) {
  var index = this.indexOf(val);
  if (index > -1) {
    this.splice(index, 1);
  }
};




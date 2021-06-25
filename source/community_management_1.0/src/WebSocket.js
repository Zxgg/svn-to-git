import configInfo from '@/webconfig/configInfo'

// 用来获取 调用此 js的vue 组件 实例 （this）
const sendThis = (_this) => {
  vm = _this
}

let vm = null
let lockReconnect = false

function heatBeat () {
  websocketsend({ sendType: 'subscribe', channel: 'PersonAlarmEvent', content: 'hello' }) // 布控告警-人脸
  websocketsend({ sendType: 'subscribe', channel: 'VehicleAlarmEvent', content: 'hello' }) // 布控告警-车辆
  websocketsend({ sendType: 'subscribe', channel: 'PersonAlarmMetadata', content: 'hello' }) // 分析
  websocketsend({ sendType: 'subscribe', channel: 'VehicleAlarmMetadata', content: 'hello' }) // 根据后台要求发送
}
function websocketonopen () { //
  // lockReconnect = true
  heartCheck.reset().start() // 心跳检测重置
  heatBeat()
}
function websocketonerror () { // 连接失败
  reconnect()
}
function websocketclose () { // 各种问题导致的 连接关闭
  reconnect()
}
function websocketonmessage (data) { // 数据接收
  // vm.heatBeat() // 收到消息会刷新心跳检测，如果一直收到消息，就推迟心跳发送
  heartCheck.reset().start() // 心跳检测重置

  let msg = JSON.parse(data.data)
  if (!(msg !== undefined && msg !== null && msg.content !== undefined && msg.content !== null)) return false
  // let romaAlarm = JSON.parse(JSON.parse(data.data).content)
  // 接收人员布控命中告警和不命中告警（两种任务）
  let romaAlarm = JSON.parse(msg.content)
  switch (romaAlarm.channel) {
    case 'PersonAlarmEvent':// 人员
      vm.$WebScoket.$emit('PersonAlarmEvent', AlarmData)// 人员告警 vcm
      break
    case 'VehicleAlarmEvent':// 车辆
      vm.$WebScoket.$emit('VehicleAlarmEvent', AlarmData)// 车辆告警 vcm
      break
    case 'PersonAlarmMetadata':// 获取任务实时抓拍人脸信息

      break
    case 'VehicleAlarmMetadata':// 获取实时抓拍车辆信息

      break
  }

  // let msgData = JSON.parse(data)
}
function websocketsend (data) { // 数据发送
  vm.websock.send(JSON.stringify(data))
}
function reconnect () { // socket重连
  if (!lockReconnect) { // 这里很关键，因为连接失败之后之后会相继触发 连接关闭，不然会连接上两个 WebSocket
    lockReconnect = true

    setTimeout(function () { // 没连接上会一直重连，设置延迟避免请求过多
      initWebSocketFnc()
      lockReconnect = false
    }, 4000)
  }
}

var heartCheck = {
  timeout: 20000, // 9分钟发一次心跳
  timeoutObj: null,
  num: 3, // 3次心跳均未响应重连
  serverTimeoutObj: null,
  reset: function () {
    clearTimeout(this.timeoutObj)
    clearTimeout(this.serverTimeoutObj)
    return this
  },
  start: function () {
    var self = this
    var _num = this.num
    this.timeoutObj = setTimeout(function () {
      // 这里发送一个心跳，后端收到后，返回一个心跳消息，
      // onmessage拿到返回的心跳就说明连接正常
      vm.websock.send('ping')
      console.log('ping!')
      self.serverTimeoutObj = setTimeout(function () { // 如果超过一定时间还没重置，说明后端主动断开了
        console.log('try=close')
        vm.websock.close() //  这里为什么要在send检测消息后，倒计时执行这个代码呢，因为这个代码的目的时为了触发onclose方法，这样才能实现onclose里面的重连方法
      }, self.timeout)
    }, this.timeout)
  }
}

function initWebSocketFnc () { // 初始化weosocket
  if (typeof (WebSocket) === 'undefined') {
    alert('您的浏览器不支持socket')
  } else {
    let wsurl = configInfo.WebSocketIP
    vm.websock = new WebSocket(wsurl)
    vm.websock.onopen = websocketonopen // 连接成功
    vm.websock.onmessage = websocketonmessage // 接收广播成功
    vm.websock.onerror = websocketonerror // 连接断开，失败
    vm.websock.onclose = websocketclose // 连接关闭
  }
}
window.onbeforeunload = function () {
  vm.websock.close()
}
export default {
  sendThis,
  initWebSocket () { // 初始化weosocket
    initWebSocketFnc()
  }
}

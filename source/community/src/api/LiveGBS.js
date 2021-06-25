import configInfo from '@/webconfig/configInfo'
import http from '../libs/http'
export function searchLiveGBSDeviceList () {
  let url = 'device/channellist'
  return http.apiAjaxGet(configInfo.LiveGBS + url)
}
// 开始直播流
export function setStartStream (data) {
  let url = 'stream/start?serial=' + data.serial + '&code=' + data.code + ''
  return http.apiAjaxGet(configInfo.LiveGBS + url)
}

// 停止直播流  check_outputs 是否检查通道在线人数, 默认 false, 表示 停止前不检查通道是否有客户端正在播放 默认值: false  允许值: true, false
export function setStopStream (data) {
  let url = 'stream/stop?serial=' + data.serial + '&code=' + data.code + '&check_outputs=true' + ''
  return http.apiAjaxGet(configInfo.LiveGBS + url)
}
// 通道树
export function searchLiveGBSChanneltree (code) {
  let url = 'device/channeltree?subfetch=false'

  return http.apiAjaxGet(configInfo.LiveGBS + url)
}

// 通道树
export function getImageURl (url) {
  // let url = 'device/channeltree?subfetch=false'

  return http.apiAjaxGet(url)
}

// 前端设备录像 - 查询录像列表
export function getLiveGBSRecordlist (data) {
  let url = 'playback/recordlist'
  let dataInfo = '?serial=' + data.serial + '&code=' + data.code + '&starttime=' + data.starttime + '&endtime=' + data.endtime + ''
  return http.apiAjaxGet(configInfo.LiveGBS + url + dataInfo)
}

// 前端设备录像 - 开始回放
export function startLiveGBSRecord (data) {
  let url = 'playback/start'
  let dataInfo = '?serial=' + data.serial + '&code=' + data.code + '&starttime=' + data.starttime + '&endtime=' + data.endtime + ''
  return http.apiAjaxGet(configInfo.LiveGBS + url + dataInfo)
}

// 前端设备录像 - 下载
export function downloadLiveGBSRecord (data) {
  let url = 'playback/start'
  let dataInfo = '?serial=' + data.serial + '&code=' + data.code + '&starttime=' + data.starttime + '&endtime=' + data.endtime + '&download=true'
  return http.apiAjaxGet(configInfo.LiveGBS + url + dataInfo)
}

// 前端设备录像 - 回放流停止
export function stopLiveGBSRecord (streamid) {
  let url = 'playback/stop'
  let dataInfo = '?streamid=' + streamid + ''
  return http.apiAjaxGet(configInfo.LiveGBS + url + dataInfo)
}

// 前端设备录像 - 回放控制
// command回放控制类型 允许值: play, pause, teardown, scale
export function controlLiveGBSRecord (data) {
  let url = 'playback/control'
  let dataInfo = '?streamid=' + data.streamid + '&command=scale' + '&scale=' + data.scale
  return http.apiAjaxGet(configInfo.LiveGBS + url + dataInfo)
}

// 实时录像 - 实时录像开始
export function RealtimeRecordStart (streamid) {
  let url = 'record/start'
  let dataInfo = '?streamid=' + streamid + ''
  return http.apiAjaxGet(configInfo.LiveGBS + url + dataInfo)
}

// 实时录像 - 实时录像停止
export function RealtimeRecordStop (streamid) {
  let url = 'record/stop'
  let dataInfo = '?streamid=' + streamid + ''
  return http.apiAjaxGet(configInfo.LiveGBS + url + dataInfo)
}

// 实时录像 - 实时录像查询
export function RealtimeRecordquery (streamid) {
  let url = 'record/query'
  let dataInfo = '?streamid=' + streamid + ''
  return http.apiAjaxGet(configInfo.LiveGBS + url + dataInfo)
}


// // 获取webrtc直播流 （测试专用）
// export function getWebStream (code) {
//   let url = '/realplay2?chanpubid=' + code + '&streamtranstype=6&AuthorizationId=admin'
//   return http.apiAjaxGet(commonConst.webRtc + url)
// }

// // webrtc直播流保活 session （测试专用）
// export function keepWebRtcliveFun (sessionId) {
//   let url = '/video_keeplive?SessionId=' + sessionId
//   return http.apiAjaxGet(commonConst.webRtc + url)
// }

// // webrtc用户保活  （测试专用）
// export function keepWebRtcUserliveFun () {
//   let url = '/keeplive?AuthorizationId=admin'
//   return http.apiAjaxGet(commonConst.webRtc + url)
// }

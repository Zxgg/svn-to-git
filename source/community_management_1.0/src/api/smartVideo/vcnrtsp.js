import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'

// 获取设备直播流地址
export function getRtsp (cameraCode) {
  const entity = {
    cameraCode: cameraCode,
    mediaURLParam: {
      broadCastType: 0,
      packProtocolType: 1,
      protocolType: 2,
      serviceType: 1,
      streamType: 1,
      transMode: 0,
      clientType: 5
    }
  }
  return request({
    url: configInfo.DBHuaWeiVcnRestAPIEndPointUri + 'get_rtsp_url',
    method: 'put',
    data: entity
  })
}

// 获取设备历史视频流地址
export function getHistoryRtsp (cameraCode, startTime, endTime) {
  const entity = {
    cameraCode: cameraCode,
    mediaURLParam: {
      broadCastType: 0,
      packProtocolType: 1,
      protocolType: 2,
      serviceType: 4,
      streamType: 1,
      transMode: 0,
      timeSpan: {
        startTime: startTime,
        endTime: endTime
      },
      clientType: 5
    }
  }
  return request({
    url: configInfo.DBHuaWeiVcnRestAPIEndPointUri + 'get_rtsp_url',
    method: 'put',
    data: entity
  })
}

// 获取历史录像列表
export function getRecordList (cameracode, nvrcode, startTime, endTime) {
  return request({
    url: configInfo.DBHuaWeiVcnRestAPIEndPointUri + 'platform/getRecordList' + '?recordmethod=0&cameracode=' + cameracode
      + '&nvrcode=' + nvrcode + '&starttime=' + startTime + '&endtime=' + endTime + '&fromindex=1&toindex=100',
    method: 'get'
  })
}

// 删除指定流服务
export function deleteHlsStream (cameraCode) {
  return request({
    url: configInfo.StreamingMediaUrl + 'hls/delete',
    method: 'post',
    data: cameraCode
  })
}
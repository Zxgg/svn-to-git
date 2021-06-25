import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取摄像头
export function getCaptureNum (id, childId) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countCaptureOnline?communityId=' + id + '&communityChildId=' + childId,
    method: 'get'
  })
}
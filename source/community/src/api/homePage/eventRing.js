import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取事件环比
export function getEventRing (id, childId) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/eventRing?communityId=' + id + '&communityChildId=' + childId,
    method: 'get'
  })
}
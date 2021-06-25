import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取事件数量
export function getEventNum (id, childId) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/eventNumberStatistics?communityId=' + id + '&communityChildId=' + childId,
    method: 'get'
  })
}

export function getEventNumNew () {
  return request({
    url: '/property_company/base_event_info/listStatusCount',
    method: 'get'
  })
}
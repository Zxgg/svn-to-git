import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取关爱提醒
export function getCaringPeopleevent (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countCaringEvent?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
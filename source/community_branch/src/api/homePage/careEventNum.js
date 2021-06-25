import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取特殊人群
export function getCareEventNum (id, childId) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countCaringEvent?communityId=' + id + '&communityChildId=' + childId,
    method: 'get'
  })
}
import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取关爱人员事件
export function getcaringEvent (id, childId) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countCaringEvent?communityId=' + id + '&communityChildId=' + childId,
    method: 'get'
  })
}
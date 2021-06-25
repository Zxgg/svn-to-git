import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取人员占比
export function getPerProportion (id, childId) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countPerProportion?communityId=' + id + '&communityChildId=' + childId,
    method: 'get'
  })
}
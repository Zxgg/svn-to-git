import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取特殊人群，管控人群
export function getSpecialPopulation (id, childId) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/specialPopulation?communityId=' + id + '&communityChildId=' + childId,
    method: 'get'
  })
}
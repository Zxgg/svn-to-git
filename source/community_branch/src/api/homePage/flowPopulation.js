import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取人口流动
export function getFlowPopulation (id, childId) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/flowPopulation?communityId=' + id + '&communityChildId=' + childId,
    method: 'get'
  })
}
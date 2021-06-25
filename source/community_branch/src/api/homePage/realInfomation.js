import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取实有信息
export function getRealInfomation (id, childId) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countActualInformation?communityId=' + id + '&communityChildId=' + childId,
    method: 'get'
  })
}
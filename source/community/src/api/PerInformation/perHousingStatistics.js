import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 人员住房信息
export function getPerHousingStatistics (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/perHouseInformStatistics?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
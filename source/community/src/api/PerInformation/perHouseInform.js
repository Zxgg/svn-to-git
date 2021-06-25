import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取人员占比
export function getPerHouseInform (id, childId) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/perHouseInformStatistics?communityId=' + id + '&communityChildId=' + childId,
    method: 'get'
  })
}
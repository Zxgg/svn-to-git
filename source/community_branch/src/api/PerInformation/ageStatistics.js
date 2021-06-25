import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取人员占比按年龄统计
export function getAgeStatistics (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/ageStatistics?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
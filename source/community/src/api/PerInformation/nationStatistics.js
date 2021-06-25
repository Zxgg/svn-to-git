import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取人员占比按民族
export function getNationStatistics (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/nationStatistics?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取人员占比按性别
export function getPerStatistics (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/perStatistics?communityId=' + id + '&communityChildId=' + childID,
    // url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/perStatistics?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
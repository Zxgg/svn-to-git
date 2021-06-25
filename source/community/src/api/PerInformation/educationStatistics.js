import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取人员占比按文化程度统计
export function getEducationStatistics (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/educationStatistics?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
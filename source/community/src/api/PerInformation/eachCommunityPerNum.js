import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取人员占比
export function getEachCommunityPerNum (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/eachCommunityPerNum?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 按人员分类
export function getPertype (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countNoNormalClass?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
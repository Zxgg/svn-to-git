import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取访客类别
export function getVistorType (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countPass?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
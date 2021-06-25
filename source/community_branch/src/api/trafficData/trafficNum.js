import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取当日通行数量
export function gettrafficdata (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countPass?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
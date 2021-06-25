import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取入住率
export function getOccupancy (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countHouse?communityId=' + id + '&communityChildId' + childID,
    method: 'get'
  })
}
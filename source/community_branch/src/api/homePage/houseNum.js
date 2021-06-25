import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取房屋数量
export function getHouseNum (id, childId) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countHouse?communityId=' + id + '&communityChildId=' + childId,
    method: 'get'
  })
}
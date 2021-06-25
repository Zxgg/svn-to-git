import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取房屋数量
export function getHouseNum (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countHouseholds?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
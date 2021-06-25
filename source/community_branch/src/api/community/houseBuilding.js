import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取房屋建筑面积
export function getHouseBuilding (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countCommunityArea?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
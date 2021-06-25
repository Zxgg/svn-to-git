import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取管控车辆
export function getcontrolVehicle (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/getLimitedByCommunity?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
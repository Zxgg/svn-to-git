import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取车辆类别
export function getVehicleType (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/getVehicleSort?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
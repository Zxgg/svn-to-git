import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取车辆类别
export function getVehicleYears (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countVehicleAge?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
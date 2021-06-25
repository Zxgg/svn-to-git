import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取车辆能源
export function getenergyVehicle (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countVehicleEnergy?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
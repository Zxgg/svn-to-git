import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取车辆活动数量
export function getVehicleTypeNum (id, childId) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/getVehicleSort?communityId=' + id + '&communityChildId=' + childId,
    method: 'get'
  })
}
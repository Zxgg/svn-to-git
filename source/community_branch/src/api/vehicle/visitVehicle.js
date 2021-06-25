
import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取访问车辆类别
export function getVisitVehicle (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/getVisitVehicleType?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
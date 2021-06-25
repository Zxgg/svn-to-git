import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取各个类型车辆流动
export function getVehicleActivity (id, childId) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/getVehicleSort?communityId=' + id + '&communityChildId=' + childId,
    method: 'get'
  })
}
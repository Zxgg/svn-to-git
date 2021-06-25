import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取车辆通行抓拍
export function getcarcremera (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/vehiclePassSnap?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
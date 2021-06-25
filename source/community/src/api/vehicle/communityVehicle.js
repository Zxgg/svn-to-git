import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取小区车辆
export function getCommunityVehicle (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/eachCommunityVehicle?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}

// 获取小区车辆
export function getCommunityVehicleList (query) {
  let url = configInfo.SmartNetworkRestAPIEndPointUri + 'communityUnit/vehicle_information/listAndPerson'
  // if (plate && plate.length > 0 && name && name.length > 0) {
  //   url += '?plate=' + plate + '&name=' + name
  // } else {
  //   if (plate && plate.length > 0) {
  //     url += '?plate=' + plate
  //   } else if (name && name.length > 0) {
  //     url += '?name=' + name
  //   }
  // }
  return request({
    url: url,
    method: 'get',
    params: query
  })
}
// 获取小区车辆
export function getCommunityVehicleList_new (query) {
  let url = configInfo.SmartNetworkRestAPIEndPointUri + 'communityUnit/vehicle_information/list/vehiclePersonHouses'
  // if (plate && plate.length > 0 && name && name.length > 0) {
  //   url += '?plate=' + plate + '&name=' + name
  // } else {
  //   if (plate && plate.length > 0) {
  //     url += '?plate=' + plate
  //   } else if (name && name.length > 0) {
  //     url += '?name=' + name
  //   }
  // }
  return request({
    url: url,
    method: 'get',
    params: query
  })
}
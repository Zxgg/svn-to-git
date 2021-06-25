import request from '@/utils/request'
import store from '@/store'
// 查询车辆信息列表
export function listVehicle_information (query) {
  return request({
    url: '/communityUnit/vehicle_information/list',
    method: 'get',
    params: query
  })
}

// 查询车辆信息详细
export function getVehicle_information (id) {
  return request({
    url: '/communityUnit/vehicle_information/' + id,
    method: 'get'
  })
}

// 新增车辆信息
export function addVehicle_information (data) {
  return request({
    url: '/communityUnit/vehicle_information',
    method: 'post',
    data: data
  })
}

// 修改车辆信息
export function updateVehicle_information (data) {
  return request({
    url: '/communityUnit/vehicle_information',
    method: 'put',
    data: data
  })
}

// 删除车辆信息
export function delVehicle_information (id) {
  return request({
    url: '/communityUnit/vehicle_information/' + id,
    method: 'put'
  })
}

// 导出车辆信息
export function exportVehicle_information (query) {
  return request({
    url: '/communityUnit/vehicle_information/export',
    method: 'get',
    params: query
  })
}
// selectVehicleInformationVoList
// 查询关联车辆信息详细
// 根据（人员ID、社区房屋ID、车牌号码、是否历史）查询所有关联的车辆信息
// 可用的条件 (plate,model,brand,specificBrand,numberOfSeats,parkingSpaceNumber,buyTime,checked,
// communityHousesId,personnelId,isDelFlag)
export function selectVehicleInformationVoList (query) {
  return request({
    url: '/communityUnit/vehicle_information/selectVehicleInformationVoList',
    method: 'get',
    params: query
  })
}


// 修改车辆信息
export function updateVehicleCheck (param) {
  param.checkedPersonId = store.getters.id
  param.checkedPersonName = store.getters.name
  return request({
    url: '/communityUnit/vehicle_information',
    method: 'put',
    data: param
  })
}
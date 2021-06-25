import request from '@/utils/request'

// 查询车辆和社区房屋和人员关联 列表
export function listVehicle_houses_personnel (query) {
  return request({
    url: '/communityUnit/vehicle_houses_personnel/list',
    method: 'get',
    params: query
  })
}

// 查询车辆和社区房屋和人员关联 详细
export function getVehicle_houses_personnel (id) {
  return request({
    url: '/communityUnit/vehicle_houses_personnel/' + id,
    method: 'get'
  })
}

// 新增车辆和社区房屋和人员关联 
export function addVehicle_houses_personnel (data) {
  return request({
    url: '/communityUnit/vehicle_houses_personnel',
    method: 'post',
    data: data
  })
}

// 修改车辆和社区房屋和人员关联 
export function updateVehicle_houses_personnel (data) {
  return request({
    url: '/communityUnit/vehicle_houses_personnel',
    method: 'put',
    data: data
  })
}

// 删除车辆和社区房屋和人员关联 
export function delVehicle_houses_personnel (id) {
  return request({
    url: '/communityUnit/vehicle_houses_personnel/' + id,
    method: 'put'
  })
}

// 导出车辆和社区房屋和人员关联 
export function exportVehicle_houses_personnel (query) {
  return request({
    url: '/communityUnit/vehicle_houses_personnel/export',
    method: 'get',
    params: query
  })
}
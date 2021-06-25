import request from '@/utils/request'

// 查询车辆通行列表
export function listPass_vehicle (query) {
  return request({
    url: '/passManage/pass_vehicle/list',
    method: 'get',
    params: query
  })
}

// 查询车辆通行详细
export function getPass_vehicle (motorVehicleId) {
  return request({
    url: '/passManage/pass_vehicle/' + motorVehicleId,
    method: 'get'
  })
}

// 新增车辆通行
export function addPass_vehicle (data) {
  return request({
    url: '/passManage/pass_vehicle',
    method: 'post',
    data: data
  })
}

// 修改车辆通行
export function updatePass_vehicle (data) {
  return request({
    url: '/passManage/pass_vehicle',
    method: 'put',
    data: data
  })
}

// 删除车辆通行
export function delPass_vehicle (motorVehicleId) {
  return request({
    url: '/passManage/pass_vehicle/' + motorVehicleId,
    method: 'put'
  })
}

// 导出车辆通行
export function exportPass_vehicle (query) {
  return request({
    url: '/passManage/pass_vehicle/export',
    method: 'get',
    params: query
  })
}
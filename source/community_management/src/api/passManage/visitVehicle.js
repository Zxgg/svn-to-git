import request from '@/utils/request'
import store from '@/store'
// 查询访客车辆信息列表
export function listVisitVehicle (query) {
  return request({
    url: '/passManage/visitVehicle/list',
    method: 'get',
    params: query
  })
}

// 查询访客车辆信息详细
export function getVisitVehicle (id) {
  return request({
    url: '/passManage/visitVehicle/' + id,
    method: 'get'
  })
}

// 新增访客车辆信息
export function addVisitVehicle (data) {
  return request({
    url: '/passManage/visitVehicle',
    method: 'post',
    data: data
  })
}

// 修改访客车辆信息
export function updateVisitVehicle (data) {
  return request({
    url: '/passManage/visitVehicle',
    method: 'put',
    data: data
  })
}

// 修改访客车辆审核状态信息
export function updateVisitVehicleCheck (param) {
  param.checkedPersonId = store.getters.id
  param.checkedPersonName = store.getters.name
  return request({
    url: '/passManage/visitVehicle',
    method: 'put',
    data: param
  })
}

// 删除访客车辆信息
export function delVisitVehicle (id) {
  return request({
    url: '/passManage/visitVehicle/' + id,
    method: 'put'
  })
}

// 导出访客车辆信息
export function exportVisitVehicle (query) {
  return request({
    url: '/passManage/visitVehicle/export',
    method: 'get',
    params: query
  })
}
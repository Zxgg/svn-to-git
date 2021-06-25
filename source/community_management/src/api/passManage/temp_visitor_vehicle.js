import request from '@/utils/request'
import store from '@/store'
// 查询临时访客车辆信息列表
export function listTemp_visitor_vehicle (query) {
  return request({
    url: '/passManage/temp_visitor_vehicle/list',
    method: 'get',
    params: query
  })
}

// 查询临时访客车辆信息详细
export function getTemp_visitor_vehicle (id) {
  return request({
    url: '/passManage/temp_visitor_vehicle/' + id,
    method: 'get'
  })
}

// 新增临时访客车辆信息
export function addTemp_visitor_vehicle (data) {
  return request({
    url: '/passManage/temp_visitor_vehicle',
    method: 'post',
    data: data
  })
}

// 修改临时访客车辆信息
export function updateTemp_visitor_vehicle (data) {
  return request({
    url: '/passManage/temp_visitor_vehicle',
    method: 'put',
    data: data
  })
}

// 删除临时访客车辆信息
export function delTemp_visitor_vehicle (id) {
  return request({
    url: '/passManage/temp_visitor_vehicle/' + id,
    method: 'put'
  })
}

// 导出临时访客车辆信息
export function exportTemp_visitor_vehicle (query) {
  return request({
    url: '/passManage/temp_visitor_vehicle/export',
    method: 'get',
    params: query
  })
}

// 修改访客车辆审核状态信息
export function updateTemp_visit_vehicle_check (param) {
  param.checkedPersonId = store.getters.id
  param.checkedPersonName = store.getters.name
  return request({
    url: '/passManage/temp_visitor_vehicle',
    method: 'put',
    data: param
  })
}

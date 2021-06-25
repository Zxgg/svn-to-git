import request from '@/utils/request'

// 查询电梯信息列表
export function listElevator_information(query) {
  return request({
    url: '/property_company/elevator_information/list',
    method: 'get',
    params: query
  })
}

// 查询电梯信息详细
export function getElevator_information(id) {
  return request({
    url: '/property_company/elevator_information/' + id,
    method: 'get'
  })
}

// 新增电梯信息
export function addElevator_information(data) {
  return request({
    url: '/property_company/elevator_information',
    method: 'post',
    data: data
  })
}

// 修改电梯信息
export function updateElevator_information(data) {
  return request({
    url: '/property_company/elevator_information',
    method: 'put',
    data: data
  })
}

// 删除电梯信息
export function delElevator_information(id) {
  return request({
    url: '/property_company/elevator_information/' + id,
    method: 'delete'
  })
}

// 软删除电梯信息
export function softDelElevator_information(id) {
  return request({
    url: '/property_company/elevator_information/' + id,
    method: 'put'
  })
}

// 导出电梯信息
export function exportElevator_information(query) {
  return request({
    url: '/property_company/elevator_information/export',
    method: 'get',
    params: query
  })
}
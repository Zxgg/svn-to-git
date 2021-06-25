import request from '@/utils/request'

// 查询资料领取通知列表
export function listData_request_notification(query) {
  return request({
    url: '/property_company/data_request_notification/list',
    method: 'get',
    params: query
  })
}

// 查询资料领取通知详细
export function getData_request_notification(id) {
  return request({
    url: '/property_company/data_request_notification/' + id,
    method: 'get'
  })
}

// 新增资料领取通知
export function addData_request_notification(data) {
  return request({
    url: '/property_company/data_request_notification',
    method: 'post',
    data: data
  })
}

// 修改资料领取通知
export function updateData_request_notification(data) {
  return request({
    url: '/property_company/data_request_notification',
    method: 'put',
    data: data
  })
}

// 删除资料领取通知
export function delData_request_notification(id) {
  return request({
    url: '/property_company/data_request_notification/' + id,
    method: 'delete'
  })
}

// 软删除资料领取通知
export function softDelData_request_notification(id) {
  return request({
    url: '/property_company/data_request_notification/' + id,
    method: 'put'
  })
}

// 导出资料领取通知
export function exportData_request_notification(query) {
  return request({
    url: '/property_company/data_request_notification/export',
    method: 'get',
    params: query
  })
}
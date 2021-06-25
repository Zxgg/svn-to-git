import request from '@/utils/request'

// 查询事件详情列表
export function listInfo (query) {
  return request({
    url: '/property_company/base_event_info/list',
    method: 'get',
    params: query
  })
}

// 查询事件详情详细
export function getInfo (id) {
  return request({
    url: '/property_company/base_event_info/' + id,
    method: 'get'
  })
}

// 新增事件详情
export function addInfo (data) {
  return request({
    url: '/property_company/base_event_info',
    method: 'post',
    data: data
  })
}

// 修改事件详情
export function updateInfo (data) {
  return request({
    url: '/property_company/base_event_info',
    method: 'put',
    data: data
  })
}

// 删除事件详情
export function delInfo (id) {
  return request({
    url: '/property_company/base_event_info/' + id,
    method: 'delete'
  })
}

// 软删除事件详情
export function softDelInfo (id) {
  return request({
    url: '/property_company/base_event_info/' + id,
    method: 'put'
  })
}

// 导出事件详情
export function exportInfo (query) {
  return request({
    url: '/property_company/base_event_info/export',
    method: 'get',
    params: query
  })
}

//修改事件状态
export function updateEventsStatus (data) {
  return request({
    url: '/property_company/base_event_info/updateEventsStatus',
    method: 'post',
    data: data
  })
}
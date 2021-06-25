import request from '@/utils/request'

// 查询 门禁申请列表
export function listEntrance_guard_apply(query) {
  return request({
    url: '/property_company/entrance_guard_apply/list',
    method: 'get',
    params: query
  })
}

// 查询 门禁申请详细
export function getEntrance_guard_apply(id) {
  return request({
    url: '/property_company/entrance_guard_apply/' + id,
    method: 'get'
  })
}

// 新增 门禁申请
export function addEntrance_guard_apply(data) {
  return request({
    url: '/property_company/entrance_guard_apply',
    method: 'post',
    data: data
  })
}

// 修改 门禁申请
export function updateEntrance_guard_apply(data) {
  return request({
    url: '/property_company/entrance_guard_apply',
    method: 'put',
    data: data
  })
}

// 删除 门禁申请
export function delEntrance_guard_apply(id) {
  return request({
    url: '/property_company/entrance_guard_apply/' + id,
    method: 'delete'
  })
}

// 软删除 门禁申请
export function softDelEntrance_guard_apply(id) {
  return request({
    url: '/property_company/entrance_guard_apply/' + id,
    method: 'put'
  })
}

// 导出 门禁申请
export function exportEntrance_guard_apply(query) {
  return request({
    url: '/property_company/entrance_guard_apply/export',
    method: 'get',
    params: query
  })
}
import request from '@/utils/request'

// 查询艾滋病人 列表
export function listSpecia_aids (query) {
  return request({
    url: '/special_personnel/specia_aids/list',
    method: 'get',
    params: query
  })
}

// 查询艾滋病人 详细
export function getSpecia_aids (id) {
  return request({
    url: '/special_personnel/specia_aids/' + id,
    method: 'get'
  })
}

// 新增艾滋病人 
export function addSpecia_aids (data) {
  return request({
    url: '/special_personnel/specia_aids',
    method: 'post',
    data: data
  })
}

// 修改艾滋病人 
export function updateSpecia_aids (data) {
  return request({
    url: '/special_personnel/specia_aids',
    method: 'put',
    data: data
  })
}

// 删除艾滋病人 
export function delSpecia_aids (id) {
  return request({
    url: '/special_personnel/specia_aids/' + id,
    method: 'put'
  })
}

// 导出艾滋病人 
export function exportSpecia_aids (query) {
  return request({
    url: '/special_personnel/specia_aids/export',
    method: 'get',
    params: query
  })
}
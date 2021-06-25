import request from '@/utils/request'

// 查询社区矫正人员信息 列表
export function listSpecia_correct (query) {
  return request({
    url: '/special_personnel/specia_correct/list',
    method: 'get',
    params: query
  })
}

// 查询社区矫正人员信息 详细
export function getSpecia_correct (id) {
  return request({
    url: '/special_personnel/specia_correct/' + id,
    method: 'get'
  })
}

// 新增社区矫正人员信息 
export function addSpecia_correct (data) {
  return request({
    url: '/special_personnel/specia_correct',
    method: 'post',
    data: data
  })
}

// 修改社区矫正人员信息 
export function updateSpecia_correct (data) {
  return request({
    url: '/special_personnel/specia_correct',
    method: 'put',
    data: data
  })
}

// 删除社区矫正人员信息 
export function delSpecia_correct (id) {
  return request({
    url: '/special_personnel/specia_correct/' + id,
    method: 'put'
  })
}

// 导出社区矫正人员信息 
export function exportSpecia_correct (query) {
  return request({
    url: '/special_personnel/specia_correct/export',
    method: 'get',
    params: query
  })
}
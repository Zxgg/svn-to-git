import request from '@/utils/request'

// 查询闲散流浪人员信息 列表
export function listSpecia_stray (query) {
  return request({
    url: '/special_personnel/specia_stray/list',
    method: 'get',
    params: query
  })
}

// 查询闲散流浪人员信息 详细
export function getSpecia_stray (id) {
  return request({
    url: '/special_personnel/specia_stray/' + id,
    method: 'get'
  })
}

// 新增闲散流浪人员信息 
export function addSpecia_stray (data) {
  return request({
    url: '/special_personnel/specia_stray',
    method: 'post',
    data: data
  })
}

// 修改闲散流浪人员信息 
export function updateSpecia_stray (data) {
  return request({
    url: '/special_personnel/specia_stray',
    method: 'put',
    data: data
  })
}

// 删除闲散流浪人员信息 
export function delSpecia_stray (id) {
  return request({
    url: '/special_personnel/specia_stray/' + id,
    method: 'put'
  })
}

// 导出闲散流浪人员信息 
export function exportSpecia_stray (query) {
  return request({
    url: '/special_personnel/specia_stray/export',
    method: 'get',
    params: query
  })
}
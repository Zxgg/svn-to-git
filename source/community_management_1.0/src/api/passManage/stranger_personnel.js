import request from '@/utils/request'

// 查询陌生人列表
export function listPersonnel(query) {
  return request({
    url: '/communityUnit/personnel/list',
    method: 'get',
    params: query
  })
}

// 查询陌生人详细
export function getPersonnel(id) {
  return request({
    url: '/communityUnit/personnel/' + id,
    method: 'get'
  })
}

// 新增陌生人
export function addPersonnel(data) {
  return request({
    url: '/communityUnit/personnel',
    method: 'post',
    data: data
  })
}

// 修改陌生人
export function updatePersonnel(data) {
  return request({
    url: '/communityUnit/personnel',
    method: 'put',
    data: data
  })
}

// 删除陌生人
export function delPersonnel(id) {
  return request({
    url: '/communityUnit/personnel/' + id,
    method: 'delete'
  })
}

// 导出陌生人
export function exportPersonnel(query) {
  return request({
    url: '/communityUnit/personnel/export',
    method: 'get',
    params: query
  })
}
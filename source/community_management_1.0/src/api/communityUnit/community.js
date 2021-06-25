import request from '@/utils/request'

// 查询社区信息列表
export function listCommunity (query) {
  return request({
    url: '/communityUnit/community/list',
    method: 'get',
    params: query
  })
}

// 查询社区信息详细
export function getCommunity (id) {
  return request({
    url: '/communityUnit/community/' + id,
    method: 'get'
  })
}

// 新增社区信息
export function addCommunity (data) {
  return request({
    url: '/communityUnit/community',
    method: 'post',
    data: data
  })
}

// 修改社区信息
export function updateCommunity (data) {
  return request({
    url: '/communityUnit/community',
    method: 'put',
    data: data
  })
}

// 删除社区信息
export function delCommunity (id) {
  return request({
    url: '/communityUnit/community/' + id,
    method: 'put'
  })
}

// 导出社区信息
export function exportCommunity (query) {
  return request({
    url: '/communityUnit/community/export',
    method: 'get',
    params: query
  })
}
// 查询社区下拉树结构
export function treeselect () {
  return request({
    // url: '/communityUnit/community/treeselect',
    url: '/communityUnit/community/menu',
    method: 'get'
  })
}
import request from '@/utils/request'

// 查询小区信息  列表
export function listCommunity_child (query) {
  return request({
    url: '/communityUnit/community_child/list',
    method: 'get',
    params: query
  })
}

// 查询小区信息  详细
export function getCommunity_child (id) {
  return request({
    url: '/communityUnit/community_child/' + id,
    method: 'get'
  })
}

// 新增小区信息  
export function addCommunity_child (data) {
  return request({
    url: '/communityUnit/community_child',
    method: 'post',
    data: data
  })
}

// 修改小区信息  
export function updateCommunity_child (data) {
  return request({
    url: '/communityUnit/community_child',
    method: 'put',
    data: data
  })
}

// 删除小区信息  
export function delCommunity_child (id) {
  return request({
    url: '/communityUnit/community_child/' + id,
    method: 'put'
  })
}

// 导出小区信息  
export function exportCommunity_child (query) {
  return request({
    url: '/communityUnit/community_child/export',
    method: 'get',
    params: query
  })
}
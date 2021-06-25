import request from '@/utils/request'

// 查询社区房屋信息列表
export function listCommunity_houses (query) {
  return request({
    url: '/communityUnit/community_houses/list',
    method: 'get',
    params: query
  })
}

// 查询社区房屋信息详细
export function getCommunity_houses (id) {
  return request({
    url: '/communityUnit/community_houses/' + id,
    method: 'get'
  })
}

// 新增社区房屋信息
export function addCommunity_houses (data) {
  return request({
    url: '/communityUnit/community_houses',
    method: 'post',
    data: data
  })
}

// 修改社区房屋信息
export function updateCommunity_houses (data) {
  return request({
    url: '/communityUnit/community_houses',
    method: 'put',
    data: data
  })
}

// 删除社区房屋信息
export function delCommunity_houses (id) {
  return request({
    url: '/communityUnit/community_houses/' + id,
    method: 'put'
  })
}

// 导出社区房屋信息
export function exportCommunity_houses (query) {
  return request({
    url: '/communityUnit/community_houses/export',
    method: 'get',
    params: query
  })
}

// 查询关联社区房屋信息列表
// (personnelId,isDelFlag)
export function queryList_houses (query) {
  return request({
    url: '/communityUnit/community_houses/queryList',
    method: 'get',
    params: query
  })
}

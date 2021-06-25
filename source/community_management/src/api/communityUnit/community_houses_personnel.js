import request from '@/utils/request'

// 查询社区房屋和人员关联列表
export function listCommunity_houses_personnel (query) {
  return request({
    url: '/communityUnit/community_houses_personnel/list',
    method: 'get',
    params: query
  })
}

// 查询社区房屋和人员关联详细
export function getCommunity_houses_personnel (id) {
  return request({
    url: '/communityUnit/community_houses_personnel/' + id,
    method: 'get'
  })
}

// 新增社区房屋和人员关联
export function addCommunity_houses_personnel (data) {
  return request({
    url: '/communityUnit/community_houses_personnel',
    method: 'post',
    data: data
  })
}

// 修改社区房屋和人员关联
export function updateCommunity_houses_personnel (data) {
  return request({
    url: '/communityUnit/community_houses_personnel',
    method: 'put',
    data: data
  })
}

// 删除社区房屋和人员关联
export function delCommunity_houses_personnel (id) {
  return request({
    url: '/communityUnit/community_houses_personnel/' + id,
    method: 'put'
  })
}

// 导出社区房屋和人员关联
export function exportCommunity_houses_personnel (query) {
  return request({
    url: '/communityUnit/community_houses_personnel/export',
    method: 'get',
    params: query
  })
}
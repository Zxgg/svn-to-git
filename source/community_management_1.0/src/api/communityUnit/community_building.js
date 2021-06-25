import request from '@/utils/request'

// 查询社区楼栋列表
export function listCommunity_building (query) {
  return request({
    url: '/communityUnit/community_building/list',
    method: 'get',
    params: query
  })
}

// 查询社区楼栋详细
export function getCommunity_building (id) {
  return request({
    url: '/communityUnit/community_building/' + id,
    method: 'get'
  })
}

// 新增社区楼栋
export function addCommunity_building (data) {
  return request({
    url: '/communityUnit/community_building',
    method: 'post',
    data: data
  })
}

// 修改社区楼栋
export function updateCommunity_building (data) {
  return request({
    url: '/communityUnit/community_building',
    method: 'put',
    data: data
  })
}

// 删除社区楼栋
export function delCommunity_building (id) {
  return request({
    url: '/communityUnit/community_building/' + id,
    method: 'put'
  })
}

// 导出社区楼栋
export function exportCommunity_building (query) {
  return request({
    url: '/communityUnit/community_building/export',
    method: 'get',
    params: query
  })
}
import request from '@/utils/request'

// 查询小区用户 列表
export function listCommunity_child_user(query) {
  return request({
    url: '/property_company/community_child_user/list',
    method: 'get',
    params: query
  })
}

// 查询小区用户 详细
export function getCommunity_child_user(id) {
  return request({
    url: '/property_company/community_child_user/' + id,
    method: 'get'
  })
}

// 新增小区用户 
export function addCommunity_child_user(data) {
  return request({
    url: '/property_company/community_child_user',
    method: 'post',
    data: data
  })
}

// 修改小区用户 
export function updateCommunity_child_user(data) {
  return request({
    url: '/property_company/community_child_user',
    method: 'put',
    data: data
  })
}

// 删除小区用户 
export function delCommunity_child_user(id) {
  return request({
    url: '/property_company/community_child_user/' + id,
    method: 'delete'
  })
}

// 软删除小区用户 
export function softDelCommunity_child_user(id) {
  return request({
    url: '/property_company/community_child_user/' + id,
    method: 'put'
  })
}

// 导出小区用户 
export function exportCommunity_child_user(query) {
  return request({
    url: '/property_company/community_child_user/export',
    method: 'get',
    params: query
  })
}
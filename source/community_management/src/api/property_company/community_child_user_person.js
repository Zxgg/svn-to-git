import request from '@/utils/request'

// 查询小区用户和小区人员房屋关联 列表
export function listCommunity_child_user_person(query) {
  return request({
    url: '/property_company/community_child_user_person/list',
    method: 'get',
    params: query
  })
}

// 查询小区用户和小区人员房屋关联 详细
export function getCommunity_child_user_person(id) {
  return request({
    url: '/property_company/community_child_user_person/' + id,
    method: 'get'
  })
}

// 新增小区用户和小区人员房屋关联 
export function addCommunity_child_user_person(data) {
  return request({
    url: '/property_company/community_child_user_person',
    method: 'post',
    data: data
  })
}

// 修改小区用户和小区人员房屋关联 
export function updateCommunity_child_user_person(data) {
  return request({
    url: '/property_company/community_child_user_person',
    method: 'put',
    data: data
  })
}

// 删除小区用户和小区人员房屋关联 
export function delCommunity_child_user_person(id) {
  return request({
    url: '/property_company/community_child_user_person/' + id,
    method: 'delete'
  })
}

// 软删除小区用户和小区人员房屋关联 
export function softDelCommunity_child_user_person(id) {
  return request({
    url: '/property_company/community_child_user_person/' + id,
    method: 'put'
  })
}

// 导出小区用户和小区人员房屋关联 
export function exportCommunity_child_user_person(query) {
  return request({
    url: '/property_company/community_child_user_person/export',
    method: 'get',
    params: query
  })
}
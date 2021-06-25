import request from '@/utils/request'

// 查询社会组织信息列表
export function listSocial_organization (query) {
  return request({
    url: '/communityManage/social_organization/list',
    method: 'get',
    params: query
  })
}

// 查询社会组织信息详细
export function getSocial_organization (id) {
  return request({
    url: '/communityManage/social_organization/' + id,
    method: 'get'
  })
}

// 新增社会组织信息
export function addSocial_organization (data) {
  return request({
    url: '/communityManage/social_organization',
    method: 'post',
    data: data
  })
}

// 修改社会组织信息
export function updateSocial_organization (data) {
  return request({
    url: '/communityManage/social_organization',
    method: 'put',
    data: data
  })
}

// 删除社会组织信息
export function delSocial_organization (id) {
  return request({
    url: '/communityManage/social_organization/' + id,
    method: 'put'
  })
}

// 导出社会组织信息
export function exportSocial_organization (query) {
  return request({
    url: '/communityManage/social_organization/export',
    method: 'get',
    params: query
  })
}
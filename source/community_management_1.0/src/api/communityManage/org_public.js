import request from '@/utils/request'

// 查询群防群治组织列表
export function listOrg_public (query) {
  return request({
    url: '/communityManage/org_public/list',
    method: 'get',
    params: query
  })
}

// 查询群防群治组织详细
export function getOrg_public (id) {
  return request({
    url: '/communityManage/org_public/' + id,
    method: 'get'
  })
}

// 新增群防群治组织
export function addOrg_public (data) {
  return request({
    url: '/communityManage/org_public',
    method: 'post',
    data: data
  })
}

// 修改群防群治组织
export function updateOrg_public (data) {
  return request({
    url: '/communityManage/org_public',
    method: 'put',
    data: data
  })
}

// 删除群防群治组织
export function delOrg_public (id) {
  return request({
    url: '/communityManage/org_public/' + id,
    method: 'put'
  })
}

// 导出群防群治组织
export function exportOrg_public (query) {
  return request({
    url: '/communityManage/org_public/export',
    method: 'get',
    params: query
  })
}
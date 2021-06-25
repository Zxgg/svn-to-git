import request from '@/utils/request'

// 查询综治机构管理列表
export function listOrg_basic (query) {
  return request({
    url: '/communityManage/org_basic/list',
    method: 'get',
    params: query
  })
}

// 查询综治机构管理详细
export function getOrg_basic (id) {
  return request({
    url: '/communityManage/org_basic/' + id,
    method: 'get'
  })
}

// 新增综治机构管理
export function addOrg_basic (data) {
  return request({
    url: '/communityManage/org_basic',
    method: 'post',
    data: data
  })
}

// 修改综治机构管理
export function updateOrg_basic (data) {
  return request({
    url: '/communityManage/org_basic',
    method: 'put',
    data: data
  })
}

// 删除综治机构管理
export function delOrg_basic (id) {
  return request({
    url: '/communityManage/org_basic/' + id,
    method: 'put'
  })
}

// 导出综治机构管理
export function exportOrg_basic (query) {
  return request({
    url: '/communityManage/org_basic/export',
    method: 'get',
    params: query
  })
}












import request from '@/utils/request'

// 查询综治机构和综治队伍关联列表
export function listOrg_basic_org_basic_team(query) {
  return request({
    url: '/communityManage/org_basic_org_basic_team/list',
    method: 'get',
    params: query
  })
}

// 查询综治机构和综治队伍关联详细
export function getOrg_basic_org_basic_team(id) {
  return request({
    url: '/communityManage/org_basic_org_basic_team/' + id,
    method: 'get'
  })
}

// 新增综治机构和综治队伍关联
export function addOrg_basic_org_basic_team(data) {
  return request({
    url: '/communityManage/org_basic_org_basic_team',
    method: 'post',
    data: data
  })
}

// 修改综治机构和综治队伍关联
export function updateOrg_basic_org_basic_team(data) {
  return request({
    url: '/communityManage/org_basic_org_basic_team',
    method: 'put',
    data: data
  })
}

// 删除综治机构和综治队伍关联
export function delOrg_basic_org_basic_team(id) {
  return request({
    url: '/communityManage/org_basic_org_basic_team/' + id,
    method: 'delete'
  })
}

// 导出综治机构和综治队伍关联
export function exportOrg_basic_org_basic_team(query) {
  return request({
    url: '/communityManage/org_basic_org_basic_team/export',
    method: 'get',
    params: query
  })
}
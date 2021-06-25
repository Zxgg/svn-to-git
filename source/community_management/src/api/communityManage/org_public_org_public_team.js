import request from '@/utils/request'

// 查询群防群治组织和队伍关联列表
export function listOrg_public_org_public_team(query) {
  return request({
    url: '/communityManage/org_public_org_public_team/list',
    method: 'get',
    params: query
  })
}

// 查询群防群治组织和队伍关联详细
export function getOrg_public_org_public_team(id) {
  return request({
    url: '/communityManage/org_public_org_public_team/' + id,
    method: 'get'
  })
}

// 新增群防群治组织和队伍关联
export function addOrg_public_org_public_team(data) {
  return request({
    url: '/communityManage/org_public_org_public_team',
    method: 'post',
    data: data
  })
}

// 修改群防群治组织和队伍关联
export function updateOrg_public_org_public_team(data) {
  return request({
    url: '/communityManage/org_public_org_public_team',
    method: 'put',
    data: data
  })
}

// 删除群防群治组织和队伍关联
export function delOrg_public_org_public_team(id) {
  return request({
    url: '/communityManage/org_public_org_public_team/' + id,
    method: 'delete'
  })
}

// 导出群防群治组织和队伍关联
export function exportOrg_public_org_public_team(query) {
  return request({
    url: '/communityManage/org_public_org_public_team/export',
    method: 'get',
    params: query
  })
}
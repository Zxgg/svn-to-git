import request from '@/utils/request'

// 查询群防群治队伍列表
export function listOrg_public_team(query) {
  return request({
    url: '/communityManage/org_public_team/list',
    method: 'get',
    params: query
  })
}

// 查询群防群治队伍详细
export function getOrg_public_team(id) {
  return request({
    url: '/communityManage/org_public_team/' + id,
    method: 'get'
  })
}

// 新增群防群治队伍
export function addOrg_public_team(data) {
  return request({
    url: '/communityManage/org_public_team',
    method: 'post',
    data: data
  })
}

// 修改群防群治队伍
export function updateOrg_public_team(data) {
  return request({
    url: '/communityManage/org_public_team',
    method: 'put',
    data: data
  })
}

// 删除群防群治队伍
export function delOrg_public_team(id) {
  return request({
    url: '/communityManage/org_public_team/' + id,
    method: 'delete'
  })
}

// 导出群防群治队伍
export function exportOrg_public_team(query) {
  return request({
    url: '/communityManage/org_public_team/export',
    method: 'get',
    params: query
  })
}
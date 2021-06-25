import request from '@/utils/request'

// 查询综治队伍列表
export function listOrg_basic_team(query) {
  return request({
    url: '/communityManage/org_basic_team/list',
    method: 'get',
    params: query
  })
}

// 查询综治队伍详细
export function getOrg_basic_team(id) {
  return request({
    url: '/communityManage/org_basic_team/' + id,
    method: 'get'
  })
}

// 新增综治队伍
export function addOrg_basic_team(data) {
  return request({
    url: '/communityManage/org_basic_team',
    method: 'post',
    data: data
  })
}

// 修改综治队伍
export function updateOrg_basic_team(data) {
  return request({
    url: '/communityManage/org_basic_team',
    method: 'put',
    data: data
  })
}

// 删除综治队伍
export function delOrg_basic_team(id) {
  return request({
    url: '/communityManage/org_basic_team/' + id,
    method: 'delete'
  })
}

// 导出综治队伍
export function exportOrg_basic_team(query) {
  return request({
    url: '/communityManage/org_basic_team/export',
    method: 'get',
    params: query
  })
}
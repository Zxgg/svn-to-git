import request from '@/utils/request'

// 查询投票项目信息 列表
export function listVote_project (query) {
  return request({
    url: '/communityServices/vote_project/list',
    method: 'get',
    params: query
  })
}

// 查询投票项目信息 详细
export function getVote_project (id) {
  return request({
    url: '/communityServices/vote_project/' + id,
    method: 'get'
  })
}

// 新增投票项目信息 
export function addVote_project (data) {
  return request({
    url: '/communityServices/vote_project',
    method: 'post',
    data: data
  })
}

// 修改投票项目信息 
export function updateVote_project (data) {
  return request({
    url: '/communityServices/vote_project',
    method: 'put',
    data: data
  })
}

// 删除投票项目信息 
export function delVote_project (id) {
  return request({
    url: '/communityServices/vote_project/' + id,
    method: 'put'
  })
}

// 导出投票项目信息 
export function exportVote_project (query) {
  return request({
    url: '/communityServices/vote_project/export',
    method: 'get',
    params: query
  })
}
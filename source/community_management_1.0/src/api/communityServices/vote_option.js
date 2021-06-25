import request from '@/utils/request'

// 查询投票项的选项 列表
export function listVote_option (query) {
  return request({
    url: '/communityServices/vote_option/list',
    method: 'get',
    params: query
  })
}

// 查询投票项的选项 详细
export function getVote_option (id) {
  return request({
    url: '/communityServices/vote_option/' + id,
    method: 'get'
  })
}

// 新增投票项的选项 
export function addVote_option (data) {
  return request({
    url: '/communityServices/vote_option',
    method: 'post',
    data: data
  })
}

// 修改投票项的选项 
export function updateVote_option (data) {
  return request({
    url: '/communityServices/vote_option',
    method: 'put',
    data: data
  })
}

// 删除投票项的选项 
export function delVote_option (id) {
  return request({
    url: '/communityServices/vote_option/' + id,
    method: 'put'
  })
}

// 导出投票项的选项 
export function exportVote_option (query) {
  return request({
    url: '/communityServices/vote_option/export',
    method: 'get',
    params: query
  })
}
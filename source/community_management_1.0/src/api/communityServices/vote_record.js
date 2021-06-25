import request from '@/utils/request'

// 查询投票记录 列表
export function listVote_record (query) {
  return request({
    url: '/communityServices/vote_record/list',
    method: 'get',
    params: query
  })
}

// 查询投票记录 详细
export function getVote_record (id) {
  return request({
    url: '/communityServices/vote_record/' + id,
    method: 'get'
  })
}

// 新增投票记录 
export function addVote_record (data) {
  return request({
    url: '/communityServices/vote_record',
    method: 'post',
    data: data
  })
}

// 修改投票记录 
export function updateVote_record (data) {
  return request({
    url: '/communityServices/vote_record',
    method: 'put',
    data: data
  })
}

// 删除投票记录 
export function delVote_record (id) {
  return request({
    url: '/communityServices/vote_record/' + id,
    method: 'put'
  })
}

// 导出投票记录 
export function exportVote_record (query) {
  return request({
    url: '/communityServices/vote_record/export',
    method: 'get',
    params: query
  })
}
// 统计投票记录 各个投票选项的得票数量
export function statisticalVote_record (query) {
  return request({
    url: '/communityServices/vote_record/countVoteOption',
    method: 'get',
    params: query
  })
}
import request from '@/utils/request'
import store from '@/store'
// 查询投诉意见记录列表
export function listOpinion_record (query) {
  return request({
    url: '/communityServices/opinion_record/list',
    method: 'get',
    params: query
  })
}

// 查询投诉意见记录详细
export function getOpinion_record (id) {
  return request({
    url: '/communityServices/opinion_record/' + id,
    method: 'get'
  })
}

// 新增投诉意见记录
export function addOpinion_record (data) {
  return request({
    url: '/communityServices/opinion_record',
    method: 'post',
    data: data
  })
}

// 修改投诉意见记录
export function updateOpinion_record (data) {
  return request({
    url: '/communityServices/opinion_record',
    method: 'put',
    data: data
  })
}

// 删除投诉意见记录
export function delOpinion_record (id) {
  return request({
    url: '/communityServices/opinion_record/' + id,
    method: 'put'
  })
}

// 导出投诉意见记录
export function exportOpinion_record (query) {
  return request({
    url: '/communityServices/opinion_record/export',
    method: 'get',
    params: query
  })
}

// 修改处理状态信息
export function updateOpinion_record_deal (param) {
  if (param.deal === '1') {
    param.dealPersonName = store.getters.name
  }
  return request({
    url: '/communityServices/opinion_record',
    method: 'put',
    data: param
  })
}
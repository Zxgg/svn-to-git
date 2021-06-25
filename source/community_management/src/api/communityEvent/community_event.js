import request from '@/utils/request'

// 查询社区事件 列表
export function listCommunity_event (query) {
  return request({
    url: '/communityEvent/community_event/list',
    method: 'get',
    params: query
  })
}

// 查询社区事件 详细
export function getCommunity_event (id) {
  return request({
    url: '/communityEvent/community_event/' + id,
    method: 'get'
  })
}

// 新增社区事件 
export function addCommunity_event (data) {
  return request({
    url: '/communityEvent/community_event',
    method: 'post',
    data: data
  })
}

// 修改社区事件 
export function updateCommunity_event (data) {
  return request({
    url: '/communityEvent/community_event',
    method: 'put',
    data: data
  })
}

// 删除社区事件 
export function delCommunity_event (id) {
  return request({
    url: '/communityEvent/community_event/' + id,
    method: 'put'
  })
}

// 导出社区事件 
export function exportCommunity_event (query) {
  return request({
    url: '/communityEvent/community_event/export',
    method: 'get',
    params: query
  })
}
//新增手工录入事件
export function addCommunity_eventEntry (data) {
  return request({
    url: '/property_company/base_event_info/addEventReportManually',
    method: 'post',
    data: data
  })
}

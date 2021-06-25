import request from '@/utils/request'

// 查询关爱人员事件列表
export function listCaring_event (query) {
  return request({
    url: '/caringPerson/caring_event/list',
    method: 'get',
    params: query
  })
}

// 查询关爱人员事件详细
export function getCaring_event (id) {
  return request({
    url: '/caringPerson/caring_event/' + id,
    method: 'get'
  })
}

// 新增关爱人员事件
export function addCaring_event (data) {
  return request({
    url: '/caringPerson/caring_event',
    method: 'post',
    data: data
  })
}

// 修改关爱人员事件
export function updateCaring_event (data) {
  return request({
    url: '/caringPerson/caring_event',
    method: 'put',
    data: data
  })
}

// 删除关爱人员事件
export function delCaring_event (id) {
  return request({
    url: '/caringPerson/caring_event/' + id,
    method: 'put'
  })
}

// 导出关爱人员事件
export function exportCaring_event (query) {
  return request({
    url: '/caringPerson/caring_event/export',
    method: 'get',
    params: query
  })
}
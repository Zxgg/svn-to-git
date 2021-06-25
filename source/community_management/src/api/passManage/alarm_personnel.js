import request from '@/utils/request'

// 查询告警人员列表
export function listAlarm_personnel(query) {
  return request({
    url: '/isstechadmin/alarm_personnel/list',
    method: 'get',
    params: query
  })
}

// 查询告警人员详细
export function getAlarm_personnel(id) {
  return request({
    url: '/isstechadmin/alarm_personnel/' + id,
    method: 'get'
  })
}

// 新增告警人员
export function addAlarm_personnel(data) {
  return request({
    url: '/isstechadmin/alarm_personnel',
    method: 'post',
    data: data
  })
}

// 修改告警人员
export function updateAlarm_personnel(data) {
  return request({
    url: '/isstechadmin/alarm_personnel',
    method: 'put',
    data: data
  })
}

// 删除告警人员
export function delAlarm_personnel(id) {
  return request({
    url: '/isstechadmin/alarm_personnel/' + id,
    method: 'delete'
  })
}

// 软删除告警人员
export function softDelAlarm_personnel(id) {
  return request({
    url: '/isstechadmin/alarm_personnel/' + id,
    method: 'put'
  })
}

// 导出告警人员
export function exportAlarm_personnel(query) {
  return request({
    url: '/isstechadmin/alarm_personnel/export',
    method: 'get',
    params: query
  })
}
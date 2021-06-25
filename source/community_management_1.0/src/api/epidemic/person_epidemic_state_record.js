import request from '@/utils/request'

// 生成疫情信息收集的二维码
export function generateQRCodeToBase64 (query) {
  return request({
    url: '/generateQRCodeToBase64',
    method: 'get',
    params: query
  })
}

// 查询疫情信息收集列表
export function listPerson_epidemic_state_record (query) {
  return request({
    url: '/epidemic/person_epidemic_state_record/list',
    method: 'get',
    params: query
  })
}

// 查询疫情信息收集详细
export function getPerson_epidemic_state_record (id) {
  return request({
    url: '/epidemic/person_epidemic_state_record/' + id,
    method: 'get'
  })
}

// 新增疫情信息收集
export function addPerson_epidemic_state_record (data) {
  return request({
    url: '/epidemic/person_epidemic_state_record',
    method: 'post',
    data: data
  })
}

// 修改疫情信息收集
export function updatePerson_epidemic_state_record (data) {
  return request({
    url: '/epidemic/person_epidemic_state_record',
    method: 'put',
    data: data
  })
}

// 删除疫情信息收集
export function delPerson_epidemic_state_record (id) {
  return request({
    url: '/epidemic/person_epidemic_state_record/' + id,
    method: 'delete'
  })
}

// 导出疫情信息收集
export function exportPerson_epidemic_state_record (query) {
  return request({
    url: '/epidemic/person_epidemic_state_record/export',
    method: 'get',
    params: query
  })
}
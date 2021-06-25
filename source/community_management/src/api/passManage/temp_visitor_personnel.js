import request from '@/utils/request'
import store from '@/store'
// 查询临时访客人员信息列表
export function listTemp_visitor_personnel (query) {
  return request({
    url: '/passManage/temp_visitor_personnel/list',
    method: 'get',
    params: query
  })
}

// 查询临时访客人员信息详细
export function getTemp_visitor_personnel (id) {
  return request({
    url: '/passManage/temp_visitor_personnel/' + id,
    method: 'get'
  })
}

// 新增临时访客人员信息
export function addTemp_visitor_personnel (data) {
  return request({
    url: '/passManage/temp_visitor_personnel',
    method: 'post',
    data: data
  })
}

// 修改临时访客人员信息
export function updateTemp_visitor_personnel (data) {
  return request({
    url: '/passManage/temp_visitor_personnel',
    method: 'put',
    data: data
  })
}

// 删除临时访客人员信息
export function delTemp_visitor_personnel (id) {
  return request({
    url: '/passManage/temp_visitor_personnel/' + id,
    method: 'put'
  })
}

// 导出临时访客人员信息
export function exportTemp_visitor_personnel (query) {
  return request({
    url: '/passManage/temp_visitor_personnel/export',
    method: 'get',
    params: query
  })
}

// 修改访客人员审核状态信息
export function updateTemp_visit_personnel_check (param) {
  param.checkedPersonId = store.getters.id
  param.checkedPersonName = store.getters.name
  return request({
    url: '/passManage/temp_visitor_personnel',
    method: 'put',
    data: param
  })
}


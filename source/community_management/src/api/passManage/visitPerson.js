import request from '@/utils/request'
import store from '@/store'
// 查询访客人员信息列表
export function listVisitPerson (query) {
  return request({
    url: '/passManage/visitPerson/list',
    method: 'get',
    params: query
  })
}

// 查询访客人员信息详细
export function getVisitPerson (id) {
  return request({
    url: '/passManage/visitPerson/' + id,
    method: 'get'
  })
}

// 新增访客人员信息
export function addVisitPerson (data) {
  return request({
    url: '/passManage/visitPerson',
    method: 'post',
    data: data
  })
}

// 修改访客人员信息
export function updateVisitPerson (data) {
  return request({
    url: '/passManage/visitPerson',
    method: 'put',
    data: data
  })
}

// 修改访客人员审核状态信息
export function updateVisitPersonCheck (param) {
  param.checkedPersonId = store.getters.id
  param.checkedPersonName = store.getters.name
  return request({
    url: '/passManage/visitPerson',
    method: 'put',
    data: param
  })
}
// 删除访客人员信息
export function delVisitPerson (id) {
  return request({
    url: '/passManage/visitPerson/' + id,
    method: 'put'
  })
}

// 导出访客人员信息
export function exportVisitPerson (query) {
  return request({
    url: '/passManage/visitPerson/export',
    method: 'get',
    params: query
  })
}
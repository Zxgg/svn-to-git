import request from '@/utils/request'

// 查询通行策略 列表
export function listPass_stratyge(query) {
  return request({
    url: '/passManage/pass_stratyge/list',
    method: 'get',
    params: query
  })
}

// 查询通行策略 详细
export function getPass_stratyge(id) {
  return request({
    url: '/passManage/pass_stratyge/' + id,
    method: 'get'
  })
}

// 新增通行策略 
export function addPass_stratyge(data) {
  return request({
    url: '/passManage/pass_stratyge',
    method: 'post',
    data: data
  })
}

// 修改通行策略 
export function updatePass_stratyge(data) {
  return request({
    url: '/passManage/pass_stratyge',
    method: 'put',
    data: data
  })
}

// 删除通行策略 
export function delPass_stratyge(id) {
  return request({
    url: '/passManage/pass_stratyge/' + id,
    method: 'delete'
  })
}

// 导出通行策略 
export function exportPass_stratyge(query) {
  return request({
    url: '/passManage/pass_stratyge/export',
    method: 'get',
    params: query
  })
}
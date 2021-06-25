import request from '@/utils/request'

// 查询隔离家庭 列表
export function listEpidemic_isolation_family(query) {
  return request({
    url: '/epidemic/epidemic_isolation_family/list',
    method: 'get',
    params: query
  })
}

// 查询隔离家庭 详细
export function getEpidemic_isolation_family(id) {
  return request({
    url: '/epidemic/epidemic_isolation_family/' + id,
    method: 'get'
  })
}

// 新增隔离家庭 
export function addEpidemic_isolation_family(data) {
  return request({
    url: '/epidemic/epidemic_isolation_family',
    method: 'post',
    data: data
  })
}

// 修改隔离家庭 
export function updateEpidemic_isolation_family(data) {
  return request({
    url: '/epidemic/epidemic_isolation_family',
    method: 'put',
    data: data
  })
}

// 删除隔离家庭 
export function delEpidemic_isolation_family(id) {
  return request({
    url: '/epidemic/epidemic_isolation_family/' + id,
    method: 'delete'
  })
}

// 导出隔离家庭 
export function exportEpidemic_isolation_family(query) {
  return request({
    url: '/epidemic/epidemic_isolation_family/export',
    method: 'get',
    params: query
  })
}
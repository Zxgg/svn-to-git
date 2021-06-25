import request from '@/utils/request'

// 查询关爱人员信息记录 列表
export function listCaring_person (query) {
  return request({
    url: '/caringPerson/caring_person/list',
    method: 'get',
    params: query
  })
}

// 查询关爱人员信息记录 详细
export function getCaring_person (id) {
  return request({
    url: '/caringPerson/caring_person/' + id,
    method: 'get'
  })
}

// 新增关爱人员信息记录 
export function addCaring_person (data) {
  return request({
    url: '/caringPerson/caring_person',
    method: 'post',
    data: data
  })
}

// 修改关爱人员信息记录 
export function updateCaring_person (data) {
  return request({
    url: '/caringPerson/caring_person',
    method: 'put',
    data: data
  })
}

// 删除关爱人员信息记录 
export function delCaring_person (id) {
  return request({
    url: '/caringPerson/caring_person/' + id,
    method: 'put'
  })
}

// 导出关爱人员信息记录 
export function exportCaring_person (query) {
  return request({
    url: '/caringPerson/caring_person/export',
    method: 'get',
    params: query
  })
}
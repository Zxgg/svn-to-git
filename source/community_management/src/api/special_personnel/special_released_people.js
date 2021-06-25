import request from '@/utils/request'

// 查询刑满释放人员 列表
export function listSpecial_released_people (query) {
  return request({
    url: '/special_personnel/special_released_people/list',
    method: 'get',
    params: query
  })
}

// 查询刑满释放人员 详细
export function getSpecial_released_people (id) {
  return request({
    url: '/special_personnel/special_released_people/' + id,
    method: 'get'
  })
}

// 新增刑满释放人员 
export function addSpecial_released_people (data) {
  return request({
    url: '/special_personnel/special_released_people',
    method: 'post',
    data: data
  })
}

// 修改刑满释放人员 
export function updateSpecial_released_people (data) {
  return request({
    url: '/special_personnel/special_released_people',
    method: 'put',
    data: data
  })
}

// 删除刑满释放人员 
export function delSpecial_released_people (id) {
  return request({
    url: '/special_personnel/special_released_people/' + id,
    method: 'put'
  })
}

// 导出刑满释放人员 
export function exportSpecial_released_people (query) {
  return request({
    url: '/special_personnel/special_released_people/export',
    method: 'get',
    params: query
  })
}
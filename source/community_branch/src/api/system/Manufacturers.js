import request from '@/utils/request'

// 查询厂家列表
export function listManufacturers(query) {
  return request({
    url: '/system/Manufacturers/list',
    method: 'get',
    params: query
  })
}

// 查询厂家详细
export function getManufacturers(id) {
  return request({
    url: '/system/Manufacturers/' + id,
    method: 'get'
  })
}

// 新增厂家
export function addManufacturers(data) {
  return request({
    url: '/system/Manufacturers',
    method: 'post',
    data: data
  })
}

// 修改厂家
export function updateManufacturers(data) {
  return request({
    url: '/system/Manufacturers',
    method: 'put',
    data: data
  })
}

// 删除厂家
export function delManufacturers(id) {
  return request({
    url: '/system/Manufacturers/' + id,
    method: 'delete'
  })
}

// 导出厂家
export function exportManufacturers(query) {
  return request({
    url: '/system/Manufacturers/export',
    method: 'get',
    params: query
  })
}
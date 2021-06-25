import request from '@/utils/request'

// 查询重点青少年 列表
export function listSpecial_young (query) {
  return request({
    url: '/special_personnel/special_young/list',
    method: 'get',
    params: query
  })
}

// 查询重点青少年 详细
export function getSpecial_young (id) {
  return request({
    url: '/special_personnel/special_young/' + id,
    method: 'get'
  })
}

// 新增重点青少年 
export function addSpecial_young (data) {
  return request({
    url: '/special_personnel/special_young',
    method: 'post',
    data: data
  })
}

// 修改重点青少年 
export function updateSpecial_young (data) {
  return request({
    url: '/special_personnel/special_young',
    method: 'put',
    data: data
  })
}

// 删除重点青少年 
export function delSpecial_young (id) {
  return request({
    url: '/special_personnel/special_young/' + id,
    method: 'put'
  })
}

// 导出重点青少年 
export function exportSpecial_young (query) {
  return request({
    url: '/special_personnel/special_young/export',
    method: 'get',
    params: query
  })
}
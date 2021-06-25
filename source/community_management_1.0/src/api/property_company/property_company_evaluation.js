import request from '@/utils/request'

// 查询物业评价 列表
export function listProperty_company_evaluation(query) {
  return request({
    url: '/property_company/property_company_evaluation/list',
    method: 'get',
    params: query
  })
}

// 查询物业评价 详细
export function getProperty_company_evaluation(id) {
  return request({
    url: '/property_company/property_company_evaluation/' + id,
    method: 'get'
  })
}

// 新增物业评价 
export function addProperty_company_evaluation(data) {
  return request({
    url: '/property_company/property_company_evaluation',
    method: 'post',
    data: data
  })
}

// 修改物业评价 
export function updateProperty_company_evaluation(data) {
  return request({
    url: '/property_company/property_company_evaluation',
    method: 'put',
    data: data
  })
}

// 删除物业评价 
export function delProperty_company_evaluation(id) {
  return request({
    url: '/property_company/property_company_evaluation/' + id,
    method: 'delete'
  })
}

// 软删除物业评价 
export function softDelProperty_company_evaluation(id) {
  return request({
    url: '/property_company/property_company_evaluation/' + id,
    method: 'put'
  })
}

// 导出物业评价 
export function exportProperty_company_evaluation(query) {
  return request({
    url: '/property_company/property_company_evaluation/export',
    method: 'get',
    params: query
  })
}
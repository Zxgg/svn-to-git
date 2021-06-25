import request from '@/utils/request'

// 查询物业费用收入记录 列表
export function listProperty_management_fee_income(query) {
  return request({
    url: '/property_company/property_management_fee_income/list',
    method: 'get',
    params: query
  })
}

// 查询物业费用收入记录 详细
export function getProperty_management_fee_income(id) {
  return request({
    url: '/property_company/property_management_fee_income/' + id,
    method: 'get'
  })
}

// 新增物业费用收入记录 
export function addProperty_management_fee_income(data) {
  return request({
    url: '/property_company/property_management_fee_income',
    method: 'post',
    data: data
  })
}

// 修改物业费用收入记录 
export function updateProperty_management_fee_income(data) {
  return request({
    url: '/property_company/property_management_fee_income',
    method: 'put',
    data: data
  })
}

// 删除物业费用收入记录 
export function delProperty_management_fee_income(id) {
  return request({
    url: '/property_company/property_management_fee_income/' + id,
    method: 'delete'
  })
}

// 软删除物业费用收入记录 
export function softDelProperty_management_fee_income(id) {
  return request({
    url: '/property_company/property_management_fee_income/' + id,
    method: 'put'
  })
}

// 导出物业费用收入记录 
export function exportProperty_management_fee_income(query) {
  return request({
    url: '/property_company/property_management_fee_income/export',
    method: 'get',
    params: query
  })
}
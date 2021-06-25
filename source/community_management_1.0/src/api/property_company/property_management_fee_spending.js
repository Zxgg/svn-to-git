import request from '@/utils/request'

// 查询物业费用支出记录 列表
export function listProperty_management_fee_spending(query) {
  return request({
    url: '/property_company/property_management_fee_spending/list',
    method: 'get',
    params: query
  })
}

// 查询物业费用支出记录 详细
export function getProperty_management_fee_spending(id) {
  return request({
    url: '/property_company/property_management_fee_spending/' + id,
    method: 'get'
  })
}

// 新增物业费用支出记录 
export function addProperty_management_fee_spending(data) {
  return request({
    url: '/property_company/property_management_fee_spending',
    method: 'post',
    data: data
  })
}

// 修改物业费用支出记录 
export function updateProperty_management_fee_spending(data) {
  return request({
    url: '/property_company/property_management_fee_spending',
    method: 'put',
    data: data
  })
}

// 删除物业费用支出记录 
export function delProperty_management_fee_spending(id) {
  return request({
    url: '/property_company/property_management_fee_spending/' + id,
    method: 'delete'
  })
}

// 软删除物业费用支出记录 
export function softDelProperty_management_fee_spending(id) {
  return request({
    url: '/property_company/property_management_fee_spending/' + id,
    method: 'put'
  })
}

// 导出物业费用支出记录 
export function exportProperty_management_fee_spending(query) {
  return request({
    url: '/property_company/property_management_fee_spending/export',
    method: 'get',
    params: query
  })
}
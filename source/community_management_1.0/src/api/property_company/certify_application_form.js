import request from '@/utils/request'

// 查询证明办理申请单列表
export function listCertify_application_form(query) {
  return request({
    url: '/property_company/certify_application_form/list',
    method: 'get',
    params: query
  })
}

// 查询证明办理申请单详细
export function getCertify_application_form(id) {
  return request({
    url: '/property_company/certify_application_form/' + id,
    method: 'get'
  })
}

// 新增证明办理申请单
export function addCertify_application_form(data) {
  return request({
    url: '/property_company/certify_application_form',
    method: 'post',
    data: data
  })
}

// 修改证明办理申请单
export function updateCertify_application_form(data) {
  return request({
    url: '/property_company/certify_application_form',
    method: 'put',
    data: data
  })
}

// 删除证明办理申请单
export function delCertify_application_form(id) {
  return request({
    url: '/property_company/certify_application_form/' + id,
    method: 'delete'
  })
}

// 软删除证明办理申请单
export function softDelCertify_application_form(id) {
  return request({
    url: '/property_company/certify_application_form/' + id,
    method: 'put'
  })
}

// 导出证明办理申请单
export function exportCertify_application_form(query) {
  return request({
    url: '/property_company/certify_application_form/export',
    method: 'get',
    params: query
  })
}
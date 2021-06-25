import request from '@/utils/request'

// 查询吸毒人员信息 列表
export function listSpecial_drug_addicts (query) {
  return request({
    url: '/special_personnel/special_drug_addicts/list',
    method: 'get',
    params: query
  })
}

// 查询吸毒人员信息 详细
export function getSpecial_drug_addicts (id) {
  return request({
    url: '/special_personnel/special_drug_addicts/' + id,
    method: 'get'
  })
}

// 新增吸毒人员信息 
export function addSpecial_drug_addicts (data) {
  return request({
    url: '/special_personnel/special_drug_addicts',
    method: 'post',
    data: data
  })
}

// 修改吸毒人员信息 
export function updateSpecial_drug_addicts (data) {
  return request({
    url: '/special_personnel/special_drug_addicts',
    method: 'put',
    data: data
  })
}

// 删除吸毒人员信息 
export function delSpecial_drug_addicts (id) {
  return request({
    url: '/special_personnel/special_drug_addicts/' + id,
    method: 'put'
  })
}

// 导出吸毒人员信息 
export function exportSpecial_drug_addicts (query) {
  return request({
    url: '/special_personnel/special_drug_addicts/export',
    method: 'get',
    params: query
  })
}
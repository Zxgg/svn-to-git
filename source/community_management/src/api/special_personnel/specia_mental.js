import request from '@/utils/request'

// 查询易肇事肇祸精神病人 列表
export function listSpecia_mental (query) {
  return request({
    url: '/special_personnel/specia_mental/list',
    method: 'get',
    params: query
  })
}

// 查询易肇事肇祸精神病人 详细
export function getSpecia_mental (id) {
  return request({
    url: '/special_personnel/specia_mental/' + id,
    method: 'get'
  })
}

// 新增易肇事肇祸精神病人 
export function addSpecia_mental (data) {
  return request({
    url: '/special_personnel/specia_mental',
    method: 'post',
    data: data
  })
}

// 修改易肇事肇祸精神病人 
export function updateSpecia_mental (data) {
  return request({
    url: '/special_personnel/specia_mental',
    method: 'put',
    data: data
  })
}

// 删除易肇事肇祸精神病人 
export function delSpecia_mental (id) {
  return request({
    url: '/special_personnel/specia_mental/' + id,
    method: 'put'
  })
}

// 导出易肇事肇祸精神病人 
export function exportSpecia_mental (query) {
  return request({
    url: '/special_personnel/specia_mental/export',
    method: 'get',
    params: query
  })
}
import request from '@/utils/request'

// 查询楼栋单元 只做记录用，房屋数据直接使用单元号列表
export function listBuilding_unit (query) {
  return request({
    url: '/communityUnit/building_unit/list',
    method: 'get',
    params: query
  })
}

// 查询楼栋单元 只做记录用，房屋数据直接使用单元号详细
export function getBuilding_unit (id) {
  return request({
    url: '/communityUnit/building_unit/' + id,
    method: 'get'
  })
}

// 新增楼栋单元 只做记录用，房屋数据直接使用单元号
export function addBuilding_unit (data) {
  return request({
    url: '/communityUnit/building_unit',
    method: 'post',
    data: data
  })
}

// 修改楼栋单元 只做记录用，房屋数据直接使用单元号
export function updateBuilding_unit (data) {
  return request({
    url: '/communityUnit/building_unit',
    method: 'put',
    data: data
  })
}

// 删除楼栋单元 只做记录用，房屋数据直接使用单元号
export function delBuilding_unit (id) {
  return request({
    url: '/communityUnit/building_unit/' + id,
    method: 'put'
  })
}

// 导出楼栋单元 只做记录用，房屋数据直接使用单元号
export function exportBuilding_unit (query) {
  return request({
    url: '/communityUnit/building_unit/export',
    method: 'get',
    params: query
  })
}
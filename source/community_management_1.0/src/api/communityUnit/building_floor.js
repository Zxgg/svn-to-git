import request from '@/utils/request'

// 查询楼栋楼层 只做记录用，房屋数据直接使用楼层号列表
export function listBuilding_floor (query) {
  return request({
    url: '/communityUnit/building_floor/list',
    method: 'get',
    params: query
  })
}

// 查询楼栋楼层 只做记录用，房屋数据直接使用楼层号详细
export function getBuilding_floor (id) {
  return request({
    url: '/communityUnit/building_floor/' + id,
    method: 'get'
  })
}

// 新增楼栋楼层 只做记录用，房屋数据直接使用楼层号
export function addBuilding_floor (data) {
  return request({
    url: '/communityUnit/building_floor',
    method: 'post',
    data: data
  })
}

// 修改楼栋楼层 只做记录用，房屋数据直接使用楼层号
export function updateBuilding_floor (data) {
  return request({
    url: '/communityUnit/building_floor',
    method: 'put',
    data: data
  })
}

// 删除楼栋楼层 只做记录用，房屋数据直接使用楼层号
export function delBuilding_floor (id) {
  return request({
    url: '/communityUnit/building_floor/' + id,
    method: 'put'
  })
}

// 导出楼栋楼层 只做记录用，房屋数据直接使用楼层号
export function exportBuilding_floor (query) {
  return request({
    url: '/communityUnit/building_floor/export',
    method: 'get',
    params: query
  })
}
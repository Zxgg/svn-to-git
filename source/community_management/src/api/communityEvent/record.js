import request from '@/utils/request'

// 查询事件列表
export function listRecord (query) {
  return request({
    url: '/property_company/base_event_record/list',
    method: 'get',
    params: query
  })
}

// 查询事件详细
export function getRecord (id) {
  return request({
    url: '/property_company/base_event_record/' + id,
    method: 'get'
  })
}

// 查询事件详细
export function getRecordGroupBy (id) {
  return request({
    url: '/property_company/base_event_record/getgroupstatus/' + id,
    method: 'get'
  })
}
export function getRecordInfo (id) {
  return request({
    url: '/property_company/base_event_record/getneweventrecord/' + id,
    method: 'get'
  })
}
// 新增事件
export function addRecord (data) {
  return request({
    url: '/property_company/base_event_record',
    method: 'post',
    data: data
  })
}

// 修改事件
export function updateRecord (data) {
  return request({
    url: '/property_company/base_event_record',
    method: 'put',
    data: data
  })
}

// 删除事件
export function delRecord (id) {
  return request({
    url: '/property_company/base_event_record/' + id,
    method: 'delete'
  })
}

// 软删除事件
export function softDelRecord (id) {
  return request({
    url: '/property_company/base_event_record/' + id,
    method: 'put'
  })
}

// 导出事件
export function exportRecord (query) {
  return request({
    url: '/property_company/base_event_record/export',
    method: 'get',
    params: query
  })
}

// 查询处置人员列表 (社区ID,小区ID) 其中小区ID暂时不用
export function listPersonChuzhi (communityId, communityChildId = '') {
  let query = {
    communityId: communityId
  }
  return request({
    url: '/property_company/base_event_info/getSysUserData',
    method: 'get',
    params: query
  })
}
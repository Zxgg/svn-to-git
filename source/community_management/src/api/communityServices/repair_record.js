import request from '@/utils/request'
import store from '@/store'
// 查询报事报修记录列表
export function listRepair_record (query) {
  return request({
    url: '/communityServices/repair_record/list',
    method: 'get',
    params: query
  })
}

// 查询报事报修记录详细
export function getRepair_record (id) {
  return request({
    url: '/communityServices/repair_record/' + id,
    method: 'get'
  })
}

// 新增报事报修记录
export function addRepair_record (data) {
  return request({
    url: '/communityServices/repair_record',
    method: 'post',
    data: data
  })
}

// 修改报事报修记录
export function updateRepair_record (data) {
  return request({
    url: '/communityServices/repair_record',
    method: 'put',
    data: data
  })
}

// 删除报事报修记录
export function delRepair_record (id) {
  return request({
    url: '/communityServices/repair_record/' + id,
    method: 'put'
  })
}

// 导出报事报修记录
export function exportRepair_record (query) {
  return request({
    url: '/communityServices/repair_record/export',
    method: 'get',
    params: query
  })
}
// 修改处理状态信息
export function updateRepair_record_deal (param) {
  if (param.deal === '1') {
    param.dealPersonName = store.getters.name
  }
  return request({
    url: '/communityServices/repair_record',
    method: 'put',
    data: param
  })
}
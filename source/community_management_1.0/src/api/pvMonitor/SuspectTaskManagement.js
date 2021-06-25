import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'

export function findList (entity) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/search_suspect_task',
    method: 'post',
    data: entity
  })
}
// 根据ID更新
export function updateByID (entity) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/edit_suspect_task',
    method: 'post',
    data: entity
  })
}
// 批量删除
export function deleteByIdList (suspectIdList) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/delete_suspect_task',
    method: 'post',
    data: suspectIdList
  })
}
// 新增
export function add (entity) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/add_suspect_task',
    method: 'post',
    data: entity
  })
}
// 启用或停用布控任务
export function enableChange (entity) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/enable_suspect_task',
    method: 'post',
    data: entity
  })
}

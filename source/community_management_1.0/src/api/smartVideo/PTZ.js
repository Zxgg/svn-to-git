import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 云台控制
export function PTZControl (entity) {
  return request({
    url: configInfo.DBHuaWeiVcnRestAPIEndPointUri + 'ptz/control',
    method: 'post',
    data: entity
  })
}

// 添加预置位
export function PTZAddPreset (entity) {
  return request({
    url: configInfo.DBHuaWeiVcnRestAPIEndPointUri + 'ptz/presetposition/add',
    method: 'post',
    data: entity
  })
}
// 查询预置位列表
export function getPresetPosList (objData) {
  return request({
    url: configInfo.DBHuaWeiVcnRestAPIEndPointUri + 'ptz/device/get?cameracode=' + objData.cameracode + '&nvrcode=' + objData.nvrcode,
    method: 'get'
  })
}
// 修改预置位
export function editPresetPosInfo (objData) {
  return request({
    url: configInfo.DBHuaWeiVcnRestAPIEndPointUri + 'ptz/presetposition/put',
    method: 'put',
    data: objData
  })
}
// 删除预置位
export function deletePresetPos (objData) {
  return request({
    url: configInfo.DBHuaWeiVcnRestAPIEndPointUri + 'ptz/presetposition/delete?cameracode=' + objData.cameracode + '&domaincode=' + objData.domaincode + '&presetindex=' + objData.presetindex,
    method: 'put'
  })
}
// 设置看守位
export function setGuardPosition (entity) {
  return request({
    url: configInfo.DBHuaWeiVcnRestAPIEndPointUri + 'ptz/guardposition/set',
    method: 'post',
    data: entity
  })
}
// 查询巡航列表
export function getCruiseList (objData) {
  return request({
    url: configInfo.DBHuaWeiVcnRestAPIEndPointUri + 'video/gruiseTrack/list/get?domainCode=' + objData.domainCode + '&cameraCode=' + objData.cameraCode,
    method: 'get'
  })
}
// 查询巡航轨迹
export function getCruiseTrack (objData) {
  return request({
    url: configInfo.DBHuaWeiVcnRestAPIEndPointUri + 'video/gruiseTrack/get?cameraCode=' + objData.cameraCode + '&domainCode=' + objData.domainCode + '&cruiseNo=' + objData.cruiseNo,
    method: 'get'
  })
}
// 修改巡航轨迹
export function editCruiseTrack (objData) {
  return request({
    url: configInfo.DBHuaWeiVcnRestAPIEndPointUri + 'video/gruiseTrack/put',
    method: 'put',
    data: objData
  })
}
// 新增巡航轨迹
export function addCruiseTrack (entity) {
  return request({
    url: configInfo.DBHuaWeiVcnRestAPIEndPointUri + 'video/gruiseTrack/add',
    method: 'post',
    data: entity
  })
}
// 删除巡航轨迹
export function deleteCruiseTrack (objData) {
  return request({
    url: configInfo.DBHuaWeiVcnRestAPIEndPointUri + 'video/gruiseTrack/delete',
    method: 'put',
    data: objData
  })
}

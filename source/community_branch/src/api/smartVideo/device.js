// import commonConst from '@/common/commonConst'
import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// import { UserOperationEnum } from '@/common/enum/enum.js'
// import { AddUserOperationLog } from '@/api/UserActionLog'

// // 根据ID获取设备
// export function getDeviceByID (queryData) {
//   let Url = 'device/findById?id=' + queryData
//   let objData = {}
//   return http.apiPut(commonConst.DBRestAPIEndPointUri + Url, '', objData)
// }
// // 根据sn获取设备
// export function getDeviceBySN (queryData) {
//   let Url = 'device/findListBySn'
//   return http.apiPut(commonConst.DBRestAPIEndPointUri + Url, '', queryData)
// }
// // 根据ID更新设备
// export function updateDeviceByID (objData) {
//   let Url = 'device/updateById'
//   AddUserOperationLog(Url, UserOperationEnum.Update)
//   return http.apiPut(commonConst.DBRestAPIEndPointUri + Url, '', objData)
// }

// // 根据ID删除设备
// export function deleteDeviceByID (id) {
//   let Url = 'device/delete'
//   AddUserOperationLog(Url, UserOperationEnum.Delete)
//   return http.apiDelete(commonConst.DBRestAPIEndPointUri + Url + '?id=', id)
// }

// // 批量删除设备
// export function deleteDeviceBatch (idList) {
//   let Url = 'device/deleteBatch/v1.1'
//   AddUserOperationLog(Url, UserOperationEnum.Delete)
//   return http.apiDelete(commonConst.DBRestAPIEndPointUri + Url + '?ids=', idList)
// }

// // 设备同步
// export function deviceSynchronization (objData) {
//   let Url = 'inspect/deviceUpdate'
//   AddUserOperationLog(Url, UserOperationEnum.Update)
//   return http.apiPost(commonConst.DBRestAPIEndPointUri + Url, objData)
// }
// // 过滤没有组织机构的设备
// export function getUnorgDevice (queryData) {
//   let Url = 'orgCapRel/filterDevice?currentPage=' + queryData.currentPage + '&pageSize=' + queryData.pageSize
//   return http.apiPut(commonConst.DBRestAPIEndPointUri + Url, '', '')
// }

// // 获取设备故障表
// export function getDeviceFaultList (queryData) {
//   let Url = 'deviceFault/find_list'
//   return http.apiPut(commonConst.DBRestAPIEndPointUri + Url, '', queryData)
// }

// // 更新设备故障
// export function updateDeviceFaultByID (queryData) {
//   let Url = 'deviceFault/updateById'
//   AddUserOperationLog(Url, UserOperationEnum.Update)
//   return http.apiPut(commonConst.DBRestAPIEndPointUri + Url, '', queryData)
// }

// // 批量设备故障
// export function delDeviceFaultByID (id) {
//   let Url = 'deviceFault/delete'
//   AddUserOperationLog(Url, UserOperationEnum.Delete)
//   return http.apiDelete(commonConst.DBRestAPIEndPointUri + Url + '?id=', id)
// }
// export function addDeviceFault (objData) {
//   let Url = 'deviceFault/save'
//   AddUserOperationLog(Url, UserOperationEnum.Add)
//   return http.apiPost(commonConst.DBRestAPIEndPointUri + Url, objData)
// }

// // 过滤出不在关系表中的数据
// export function getNoOrgDevice (queryData) {
//   let Url = 'orgCapRel/filterDevice?currentPage=' + queryData.currentPage + '&pageSize=' + queryData.pageSize +
//     '&deviceName=' + queryData.deviceName + '&captureType=' + queryData.captureType +
//     '&uiStatus=' + queryData.uiStatus + '&analyzeStatus=' + queryData.analyzeStatus
//   return http.apiPut(commonConst.DBRestAPIEndPointUri + Url, '', '')
// }
// // 获取设备列表
// export function getDeviceInfoList (entity) {
//   // console.log('userService:' + commonConst.bussinessService)
//   return http.apiPut(commonConst.DBRestAPIEndPointUri + 'device/queryByPage', '', entity)
// }
// // 根据SN列表获取设备列表
// export function getDeviceInfoListBySNLIst (snList) {
//   // console.log('userService:' + commonConst.bussinessService)
//   return http.apiPut(commonConst.DBRestAPIEndPointUri + 'device/findListBySn', '', snList)
// }

// 获取设备列表
export function getDeviceList (queryData) {
  return request({
    url: configInfo.VideoBasicsRestAPIEndPointUri + 'orgCapRel/find_list_device_web?currentPage=' + queryData.currentPage + '&pageSize=' + queryData.pageSize,
    method: 'put',
    data: queryData.body
  })
}

// 根据设备SN获取设备所在组织机构
export function getDeviceOrg (queryData) {
  return request({
    url: configInfo.VideoBasicsRestAPIEndPointUri + 'orgCapRel/find_list?currentPage=' + queryData.currentPage + '&pageSize=' + queryData.pageSize,
    method: 'put',
    data: queryData.body
  })
}

// 根据sn获取设备
export function getDeviceBySN (queryData) {
  return request({
    url: configInfo.VideoBasicsRestAPIEndPointUri + 'device/findListBySn',
    method: 'put',
    data: queryData
  })
}
//查询VCM设备列表
export function getCameraList(queryData) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'camera_manager/camera_list',
    method: 'post',
    data:queryData
  })
}
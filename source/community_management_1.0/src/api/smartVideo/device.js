import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'

// 设备报障
export function addDeviceFault (objData) {
  return request({
    url: configInfo.VideoBasicsRestAPIEndPointUri + 'deviceFault/save',
    method: 'post',
    data: objData
  })
}
// 查询角色详细
export function getRole (roleId) {
  return request({
    url: configInfo.VideoBasicsRestAPIEndPointUri + 'systemrole/findById?id=' + roleId,
    method: 'put'
  })
}

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
//获取设备列表(设备信息表)
export function getDeviceInfoList (queryData) {
  return request({
    url: configInfo.VideoBasicsRestAPIEndPointUri + 'device/queryByPage',
    method: 'put',
    data: queryData
  })
}
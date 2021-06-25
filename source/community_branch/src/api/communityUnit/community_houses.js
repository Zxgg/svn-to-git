import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 查询社区房屋信息列表
export function listCommunity_houses (query) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'communityUnit/community_houses/list',
    method: 'get',
    params: query
  })
}

// 查询社区房屋信息详细
export function getCommunity_houses (id) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'communityUnit/community_houses/' + id,
    method: 'get'
  })
}

// 新增社区房屋信息
export function addCommunity_houses (data) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'communityUnit/community_houses',
    method: 'post',
    data: data
  })
}

// 修改社区房屋信息
export function updateCommunity_houses (data) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'communityUnit/community_houses',
    method: 'put',
    data: data
  })
}

// 删除社区房屋信息
export function delCommunity_houses (id) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'communityUnit/community_houses/' + id,
    method: 'put'
  })
}

// 导出社区房屋信息
export function exportCommunity_houses (query) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'communityUnit/community_houses/export',
    method: 'get',
    params: query
  })
}

// 查询关联社区房屋信息列表
// (personnelId,isDelFlag)
export function queryList_houses (query) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'communityUnit/community_houses/queryList',
    method: 'get',
    params: query
  })
}
// selectVehicleInformationVoList
// 查询关联车辆信息详细
// 根据（人员ID、社区房屋ID、车牌号码、是否历史）查询所有关联的车辆信息
// 可用的条件 (plate,model,brand,specificBrand,numberOfSeats,parkingSpaceNumber,buyTime,checked,
// communityHousesId,personnelId,isDelFlag)
export function selectVehicleInformationVoList (query) {
  return request({
    url: '/communityUnit/vehicle_information/selectVehicleInformationVoList',
    method: 'get',
    params: query
  })
}

// 根据（房主姓名、房间号）查询房屋信息列表：
//  返回字段包括：户主基本信息、房屋所有信息
export function queryHouseAndPersonList (query) {
  return request({
    url: '/communityUnit/community_houses/queryHouseAndPersonList',
    method: 'get',
    params: query
  })
}

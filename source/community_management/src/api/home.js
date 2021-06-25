import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'

// 返回查询实有信息
export function countActualInformation (entity) {
  return request({
    url: 'count/countActualInformation',
    method: 'get',
    params: entity
  })
}

// 当日通行数量统计结果
export function countPass (entity) {
  return request({
    url: '/count/countPass',
    method: 'get',
    params: entity
  })
}
// 当日通行人员抓拍图片、访客类型、地点以及入场时间
export function personnelPassSnap (entity) {
  return request({
    url: '/count/personnelPassSnap',
    method: 'get',
    params: entity
  })
}
// 当日通行车辆抓拍图片、车牌号、通道名称以及入场时间
export function vehiclePassSnap (entity) {
  return request({
    url: '/count/vehiclePassSnap',
    method: 'get',
    params: entity
  })
}
// 首页 - 社区事件类型统计
export function eventNumberStatistics (entity) {
  return request({
    url: '/count/eventNumberStatistics',
    method: 'get',
    params: entity
  })
}
// 返回各种类型的关爱提醒的数量
export function countCaringEventByType (entity) {
  return request({
    url: '/count/countCaringEventByType',
    method: 'get',
    params: entity
  })
}
// 首页 - 人口流动情况
export function flowPopulation (entity) {
  return request({
    url: '/count/flowPopulation',
    method: 'get',
    params: entity
  })
}

// 返回查询房屋入住率
export function countHouse (entity) {
  return request({
    url: 'count/countHouse?communityId&communityChildId',
    method: 'get'
  })
}

// 返回查询特殊人员各个类型的总人数
export function countSpecial (entity) {
  return request({
    url: 'count/countSpecial?communityId&communityChildId&type=1',
    method: 'get'
  })
}

// 获取社区内组织各个类型的总数
export function getCommunityOrg () {
  return request({
    url: 'count/countOrganization',
    method: 'get'
  })
}

// 获取关爱人员各个类型的总人数
export function getcaringType () {
  return request({
    url: 'count/countCaringType?communityId&communityChildId',
    method: 'get'
  })
}

// 获取人员房屋的居住类型统计，比如 自有住房5人 租房10人 亲友借住2人
export function getPerHouseInformStatisticse () {
  return request({
    url: 'count/perHouseInformStatistics?communityId&communityChildId',
    method: 'get'
  })
}

// 获取入口流动
export function getFlowPopulation () {
  return request({
    url: 'count/flowPopulation?communityId&communityChildId',
    method: 'get'
  })
}
import request from '@/utils/request'

// 查询人员信息列表
export function listPersonnel_information (query) {
  return request({
    url: '/communityUnit/personnel_information/list',
    method: 'get',
    params: query
  })
}

// 查询人员信息详细
export function getPersonnel_information (id) {
  return request({
    url: '/communityUnit/personnel_information/' + id,
    method: 'get'
  })
}

// 新增人员信息
export function addPersonnel_information (data) {
  return request({
    url: '/communityUnit/personnel_information',
    method: 'post',
    data: data
  })
}

// 修改人员信息
export function updatePersonnel_information (data) {
  return request({
    url: '/communityUnit/personnel_information',
    method: 'put',
    data: data
  })
}

// 删除人员信息
export function delPersonnel_information (id) {
  return request({
    url: '/communityUnit/personnel_information/' + id,
    method: 'put'
  })
}

// 导出人员信息
export function exportPersonnel_information (query) {
  return request({
    url: '/communityUnit/personnel_information/export',
    method: 'get',
    params: query
  })
}


// 根据人名查询人员信息列表
export function getInfoByName (param) {
  let url = '/communityUnit/personnel_information/getInfoByName?name=' + param.name
  url += '&communityId='

  if (param.communityId) {
    url += param.communityId
  }
  url += '&communityChildId='
  if (param.communityChildId) {
    url += param.communityChildId
  }
  return request({
    url: url,
    method: 'get'
  })
}

// 根据房屋id查询关联的人员信息
export function selectPerInformByHousesId (query) {
  return request({
    url: '/communityUnit/personnel_information/selectPerInformByHousesId',
    method: 'get',
    params: query
  })
}

// 根据人员ID修改此人员的 是否是特殊人员字段，
export function updatePersonnel_specialType (personId, specialType) {
  let param = {
    id: personId,
    special: '0',
  }
  if (specialType) {
    param.special = specialType
  }
  return request({
    url: '/communityUnit/personnel_information',
    method: 'put',
    data: param
  })
}
// 根据人员ID删除此人员的特殊人员信息，（修改标记+删除关联）
export function updatePersonnel_removeSpecialType (personIds) {
  return request({
    url: '/communityUnit/personnel_information/removeSpecial?id=' + personIds,
    method: 'get'
  })
}
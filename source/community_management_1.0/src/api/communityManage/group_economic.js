import request from '@/utils/request'

// 查询非公经济组织信息列表
export function listGroup_economic (query) {
  return request({
    url: '/communityManage/group_economic/list',
    method: 'get',
    params: query
  })
}

// 查询非公经济组织信息详细
export function getGroup_economic (id) {
  return request({
    url: '/communityManage/group_economic/' + id,
    method: 'get'
  })
}

// 新增非公经济组织信息
export function addGroup_economic (data) {
  return request({
    url: '/communityManage/group_economic',
    method: 'post',
    data: data
  })
}

// 修改非公经济组织信息
export function updateGroup_economic (data) {
  return request({
    url: '/communityManage/group_economic',
    method: 'put',
    data: data
  })
}

// 删除非公经济组织信息
export function delGroup_economic (id) {
  return request({
    url: '/communityManage/group_economic/' + id,
    method: 'put'
  })
}

// 导出非公经济组织信息
export function exportGroup_economic (query) {
  return request({
    url: '/communityManage/group_economic/export',
    method: 'get',
    params: query
  })
}
import request from '@/utils/request'

// 查询境内外非政府组织列表
export function listGroup_abroad (query) {
  return request({
    url: '/communityManage/group_abroad/list',
    method: 'get',
    params: query
  })
}

// 查询境内外非政府组织详细
export function getGroup_abroad (id) {
  return request({
    url: '/communityManage/group_abroad/' + id,
    method: 'get'
  })
}

// 新增境内外非政府组织
export function addGroup_abroad (data) {
  return request({
    url: '/communityManage/group_abroad',
    method: 'post',
    data: data
  })
}

// 修改境内外非政府组织
export function updateGroup_abroad (data) {
  return request({
    url: '/communityManage/group_abroad',
    method: 'put',
    data: data
  })
}

// 删除境内外非政府组织
export function delGroup_abroad (id) {
  return request({
    url: '/communityManage/group_abroad/' + id,
    method: 'put'
  })
}

// 导出境内外非政府组织
export function exportGroup_abroad (query) {
  return request({
    url: '/communityManage/group_abroad/export',
    method: 'get',
    params: query
  })
}
import request from '@/utils/request'

// 查询视频信息库列表
export function listImageVideoLibrary(query) {
  return request({
    url: '/imagesInformationLibrarys/imageVideoLibrary/list',
    method: 'get',
    params: query
  })
}

// 查询视频信息库详细
export function getImageVideoLibrary(id) {
  return request({
    url: '/imagesInformationLibrarys/imageVideoLibrary/' + id,
    method: 'get'
  })
}

// 新增视频信息库
export function addImageVideoLibrary(data) {
  return request({
    url: '/imagesInformationLibrarys/imageVideoLibrary',
    method: 'post',
    data: data
  })
}

// 修改视频信息库
export function updateImageVideoLibrary(data) {
  return request({
    url: '/imagesInformationLibrarys/imageVideoLibrary',
    method: 'put',
    data: data
  })
}

// 删除视频信息库
export function delImageVideoLibrary(id) {
  return request({
    url: '/imagesInformationLibrarys/imageVideoLibrary/' + id,
    method: 'delete'
  })
}

// 导出视频信息库
export function exportImageVideoLibrary(query) {
  return request({
    url: '/imagesInformationLibrarys/imageVideoLibrary/export',
    method: 'get',
    params: query
  })
}
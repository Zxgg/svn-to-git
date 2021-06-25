import request from '@/utils/request'

// 查询人员图像信息库列表
export function listImagePerinforLibrary(query) {
  return request({
    url: '/imagesInformationLibrarys/ImagePerinforLibrary/list',
    method: 'get',
    params: query
  })
}

// 查询人员图像信息库详细
export function getImagePerinforLibrary(id) {
  return request({
    url: '/imagesInformationLibrarys/ImagePerinforLibrary/' + id,
    method: 'get'
  })
}

// 新增人员图像信息库
export function addImagePerinforLibrary(data) {
  return request({
    url: '/imagesInformationLibrarys/ImagePerinforLibrary',
    method: 'post',
    data: data
  })
}

// 修改人员图像信息库
export function updateImagePerinforLibrary(data) {
  return request({
    url: '/imagesInformationLibrarys/ImagePerinforLibrary',
    method: 'put',
    data: data
  })
}

// 删除人员图像信息库
export function delImagePerinforLibrary(id) {
  return request({
    url: '/imagesInformationLibrarys/ImagePerinforLibrary/' + id,
    method: 'delete'
  })
}

// 导出人员图像信息库
export function exportImagePerinforLibrary(query) {
  return request({
    url: '/imagesInformationLibrarys/ImagePerinforLibrary/export',
    method: 'get',
    params: query
  })
}
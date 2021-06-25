import request from '@/utils/request'

// 查询图像信息库列表
export function listImageInformationLibrary(query) {
  return request({
    url: '/imagesInformationLibrarys/image_information_library/list',
    method: 'get',
    params: query
  })
}

// 查询图像信息库详细
export function getImageInformationLibrary(id) {
  return request({
    url: '/imagesInformationLibrarys/image_information_library/' + id,
    method: 'get'
  })
}

// 新增图像信息库
export function addImageInformationLibrary(data) {
  return request({
    url: '/imagesInformationLibrarys/image_information_library',
    method: 'post',
    data: data
  })
}

// 修改图像信息库
export function updateImageInformationLibrary(data) {
  return request({
    url: '/imagesInformationLibrarys/image_information_library',
    method: 'put',
    data: data
  })
}

// 删除图像信息库
export function delImageInformationLibrary(id) {
  return request({
    url: '/imagesInformationLibrarys/image_information_library/' + id,
    method: 'delete'
  })
}

// 导出图像信息库
export function exportImageInformationLibrary(query) {
  return request({
    url: '/imagesInformationLibrarys/image_information_library/export',
    method: 'get',
    params: query
  })
}
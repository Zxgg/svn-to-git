import request from '@/utils/request'

// 查询车辆图像信息库列表
export function listImageVehicleinforLibrary(query) {
  return request({
    url: '/imagesInformationLibrarys/ImageVehicleinforLibrary/list',
    method: 'get',
    params: query
  })
}

// 查询车辆图像信息库详细
export function getImageVehicleinforLibrary(id) {
  return request({
    url: '/imagesInformationLibrarys/ImageVehicleinforLibrary/' + id,
    method: 'get'
  })
}

// 新增车辆图像信息库
export function addImageVehicleinforLibrary(data) {
  return request({
    url: '/imagesInformationLibrarys/ImageVehicleinforLibrary',
    method: 'post',
    data: data
  })
}

// 修改车辆图像信息库
export function updateImageVehicleinforLibrary(data) {
  return request({
    url: '/imagesInformationLibrarys/ImageVehicleinforLibrary',
    method: 'put',
    data: data
  })
}

// 删除车辆图像信息库
export function delImageVehicleinforLibrary(id) {
  return request({
    url: '/imagesInformationLibrarys/ImageVehicleinforLibrary/' + id,
    method: 'delete'
  })
}

// 导出车辆图像信息库
export function exportImageVehicleinforLibrary(query) {
  return request({
    url: '/imagesInformationLibrarys/ImageVehicleinforLibrary/export',
    method: 'get',
    params: query
  })
}
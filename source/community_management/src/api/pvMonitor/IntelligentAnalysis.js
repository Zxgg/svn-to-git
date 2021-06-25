import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'

// 获取人脸数据
export function organizationalstructure_findById (orgCode) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/people_faces',
    method: 'post',
    data: orgCode
  })
}

// 过人过脸接口图片获取专用
export function GetFaceImageBase64RestUri (fileId) {
  let Url = 'intelligent_analysis/people_faces_image'
  let objData = {
    fileid: fileId,
    type: 3
  }
  return http.apiPut(configInfo.DBHuaWeiVcmRestAPIEndPointUri + Url, '', objData)
}
// let config = {
//   // 添加请求头
//   headers: { 'Content-Type': 'multipart/form-data' },
//   headers: { 'Version': 'V1.0.0' }
//   // 添加上传进度监听事件
//   /* onUploadProgress: e => {
//     var completeProgress = ((e.loaded / e.total * 100) | 0) + "%";
//     this.progress = completeProgress;
//   } */
// }
// 图片上传
export function IntelligentAnalysis_uplodeImage (file) {
  return request({
    ContentType: 'multipart/form-data',
    Version: 'V1.0.0',
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'mediaManagement/batch_upload',
    method: 'post',
    data: file
  })
}

// 根据图片查询车辆信息
export function IntelligentAnalysis_getVehicleByFaceId (vehicleImageDate) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/image_search_vehicle',
    method: 'post',
    data: vehicleImageDate
  })
}

// 根据特征查询车辆信息
export function IntelligentAnalysis_getVehicleByParameter (vehicleParameterDate) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/search_vehicle',
    method: 'post',
    data: vehicleParameterDate
  })
}

// 查询静态库人员名单列表
export function GetStaticLibaryPicResultByFileID (entity) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'mediaManagement/staticlibraries_peoples',
    method: 'post',
    data: entity
  })
}

// 人脸库查询名单-图片查询 分页
export function GetFacesLibaryPicResultByFileID (entity) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/query_peoples',
    method: 'post',
    data: entity
  })
}

// 人脸库查询-获取人脸库列表集合
export function GetFaceRepositoriesList (entity) {
  return request({
    // url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/facerepositories_qu',
    url: '/list/library/list',
    method: 'get',
    data: entity
  })
}

// 人脸静态库查询-获取人脸库列表集合
export function GetFaceStaticRepositoriesList (orgCode) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'mediaManagement/get_staticlibraries',
    method: 'post',
    data: orgCode
  })
}

// 车辆黑名单查询-获取车辆黑名单列表集合
export function GetVehicleRepositoriesList (entity) {
  entity.type = '0'
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/search_bwgroup',
    method: 'post',
    data: entity
  })
}

// 图像信息库下载   图片获取专用   （车辆黑名单中的图片获取也用此接口）
export function GetImageBase64 (fileId) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'mediaManagement/download_file?casefileid=' + fileId,
    method: 'get'
  })
}

// 过车接口图片获取专用
export function GetVehicleImageBase64By (fileId) {
  let data = {
    fileId: fileId,
    imageType: 2,
    imageSize: 1
  }
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'mediaManagement/get_image',
    method: 'put',
    data: data
  })
}

// 新增人脸库
export function AddFaceRepositories (data) {
  return request({
    url: '/list/library',
    method: 'post',
    data: data
  })
}

// 删除人脸库，根据ID
export function DeleteFaceRepositories (id) {
  return request({
    url: '/list/library/delete/' + id,
    method: 'delete'
  })
}


//修改人脸库
export function UpdateFaceRepositories (objData) {
  return request({
    url: '/list/library',
    method: 'put',
    data: objData
  })
}

// 修改人员名单信息
export function UpdatePeopleList (objData) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/update_peoples_id?repositorid=' + objData.people.repositorId + '&id=' + objData.people.peopleId,
    method: 'put',
    data: objData
  })
}

// 删除人员名单信息
export function DeletePeopleList (objData) {
  let Url = 'intelligent_analysis/peoples_feature_delete?repositorid=' + objData.repositorId
  let data = {
    credentialnumbers: objData.credentialNumber
  }
  return http.apiPut(configInfo.DBHuaWeiVcmRestAPIEndPointUri + Url, '', data)
}

// 删除静态库名单信息
export function deleteStaticPeopleList (objData) {
  let Url = 'mediaManagement/delete_peoples?library_id=' + objData.library_id + '&ids=' + objData.ids + ''
  return http.apiDelete(configInfo.DBHuaWeiVcmRestAPIEndPointUri + Url, '')
}

// 新增静态人脸名单
export function AddPeopleList (orgCode, listId) {
  return request({
    url: '/intelligentAnalysis/addPeopleFace?listId' + listId,
    method: 'post',
    data: orgCode
  })
}

export function AddStaticPeopleList (orgCode) {
  let Url = 'mediaManagement/add_sync_t_staticlibraries_peoples?libraryId=' + orgCode.peoples[0].people[0].libraryid
  let objDataS = orgCode
  return http.apiPost(configInfo.DBHuaWeiVcmRestAPIEndPointUri + Url, objDataS)
}

export function AddStaticPeopleListS (orgCode) {
  let Url = 'mediaManagement/add_sync_t_staticlibraries_peoples?libraryId=' + orgCode.libraryId
  let objDataS = orgCode
  return http.apiPost(configInfo.DBHuaWeiVcmRestAPIEndPointUri + Url, objDataS)
}

export function UpdateStaticPeopleList (objData) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'mediaManagement/edit_peoples?library_id=' + objData.people.library_id + '&id=' + objData.people.id,
    method: 'put',
    data: objData
  })
}



export function UpdateFaceStaticRepositories (objData) {
  let Url = 'mediaManagement/edit_staticlibraries?id=' + objData.staticLibrary.id
  return http.apiPut(configInfo.DBHuaWeiVcmRestAPIEndPointUri + Url, '', objData)
}

export function GetVehicleBlackList (orgCode) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/search_bwdata',
    method: 'post',
    data: orgCode
  })
}

export function AddVehicleGroup (orgCode) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/add_bwgroup',
    method: 'post',
    data: orgCode
  })
}

export function UpdateVehicleGroup (orgCode) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/update_bwgroup',
    method: 'post',
    data: orgCode
  })
}

export function DeleteVehicleGroupById (orgCode) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/delete_bwgroup',
    method: 'post',
    data: orgCode
  })
}

export function AddVehicle (orgCode) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/addb_wdata',
    method: 'post',
    data: orgCode
  })
}

export function UpdateVehicle (orgCode) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/update_bwdata',
    method: 'post',
    data: orgCode
  })
}

export function DeleteVehicleById (orgCode) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/delete_bwdata',
    method: 'post',
    data: orgCode
  })
}

export function searchDictionaryList (entity) {
  return request({
    url: configInfo.VideoBasicsRestAPIEndPointUri + 'dictionary/find_list',
    method: 'put',
    data: entity
  })
}
export function findVehicleBrandTypeList (param) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'platformManagement/video_analysis_get_datadictionary',
    method: 'post',
    data: param
  })
}
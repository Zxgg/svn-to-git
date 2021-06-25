import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取人脸数据
export function getFaceByParameter (queryData) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/people_faces',
    method: 'post',
    data: queryData
  })
}
export function getUplodeImage (file) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'mediaManagement/batch_upload',
    method: 'post',
    data: file
  })
}
export function GetFaceImageBase64RestUri (fileId) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/people_faces_image',
    method: 'put',
    data: {
      fileId: fileId,
      type: 3
    }
  })
}
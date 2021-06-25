import request from '@/utils/request'

// 文件上传
export function uploadBase64Image (filename, base64) {
  let dataStr = base64
  if (base64 && base64.length > 0) {
    let list = base64.split(',')
    if (list && list.length === 2) {
      dataStr = list[1]
    }
  }
  return request({
    url: '/upload/base64/sample',
    method: 'post',
    // ContentType: 'application/x-www-form-urlencoded',
    data: {
      filename: filename,
      base64: dataStr
    }
  })
}
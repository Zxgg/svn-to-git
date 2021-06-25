import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'

// 分页查询 下载任务
export function searchVideoDownloadTask (data) {
  return request({
    url: configInfo.VideoBasicsRestAPIEndPointUri + 'videoDownLoadTask/find_list',
    method: 'put',
    data: data
  })
}

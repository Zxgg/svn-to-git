import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
import commonFunction from '@/common/commonFunction.js'
// 获取当天人车通行
export function getpeovehiNum (id, childID) {
  // /count/trafficTrends?startTime=2020-12-23 00:00:00&endTime=2020-12-24 22:59:59&type=2
  let endTime = ''
  let startTime = ''
  let currentTime = new Date()
  currentTime.setTime(currentTime.getTime() - 1 * 24 * 3600 * 1000)
  startTime = commonFunction.DataFormat(currentTime, 'yyyy-MM-dd') + ' 00:00:00'
  currentTime.setTime(currentTime.getTime() + 1 * 24 * 3600 * 1000)
  endTime = commonFunction.DataFormat(currentTime, 'yyyy-MM-dd') + ' 01:00:00'
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/trafficTrends?communityId=' + id + '&communityChildId=' + childID,
    method: 'post',
    data: {
      startTime: startTime,
      endTime: endTime,
      type: 2
    }
  })
}

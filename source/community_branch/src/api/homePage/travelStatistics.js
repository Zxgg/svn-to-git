import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
import commonFunction from '@/common/commonFunction.js'
// 出行统计
export function getTravel (id, childID) {
  // let endTime = ''
  // let startTime = ''
  // let currentTime = new Date()
  // currentTime.setTime(currentTime.getTime() - 3600 * 1000)
  // startTime = commonFunction.DataFormat(currentTime, 'yyyy-MM-dd') + ' 00:00:00'
  // // currentTime.setTime(currentTime.getTime() - 3600 * 1000)
  // endTime = commonFunction.DataFormat(currentTime, 'yyyy-MM-dd') + ' 23:59:59'
  // // console.log(startTime, endTime, 'shijian')
  let endTime = ''
  let startTime = ''
  let currentTime = new Date()
  //获取最近一个月的数据
  // currentTime.setTime(currentTime.getTime() - 1 * 24 * 3600 * 1000)
  // endTime = commonFunction.DataFormat(currentTime, 'yyyy-MM-dd') + ' 23:59:59'
  // currentTime.setTime(currentTime.getTime() +30 * 24 * 3600 * 1000)
  // startTime = commonFunction.DataFormat(currentTime, 'yyyy-MM-dd') + ' 00:00:00'


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
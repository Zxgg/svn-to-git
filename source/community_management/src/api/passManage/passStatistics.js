import request from '@/utils/request'

// 查询通行总览
export function passStatisticsOverview (query) {
  return request({
    url: '/count/countPass',
    method: 'get',
    params: query
  })
}

// 查询通行趋势
export function passTrends (query) {
  return request({
    url: '/count/trafficTrends?startTime=2020-12-23 00:00:00&endTime=2020-12-24 22:59:59&type=2',
    method: 'get',
    params: query
  })
}
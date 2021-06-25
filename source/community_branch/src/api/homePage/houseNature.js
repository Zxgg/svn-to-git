import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取房屋属性
export function getHouseNature (id, childId) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countHouseUseNature?communityId=' + id + '&communityChildId=' + childId,
    method: 'get'
  })
}
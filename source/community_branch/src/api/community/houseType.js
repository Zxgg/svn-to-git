import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取房屋类型
export function getHouseType (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countHouseUseNature?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
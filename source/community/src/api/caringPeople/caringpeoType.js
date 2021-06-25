import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取关爱人员类型
export function getCaringPeopleType (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countCaringType?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
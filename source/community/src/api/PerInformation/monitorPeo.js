import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取布控人员信息
export function getBlackPerson (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/getBlackPersonnelByCommunity?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
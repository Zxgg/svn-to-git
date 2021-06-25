import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取关爱人员列表
export function getBottomlist (id, childID, query) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'caringPerson/caring_event/list?communityId=' + id + '&communityChildId=' + childID,
    method: 'get',
    params: query
  })
}
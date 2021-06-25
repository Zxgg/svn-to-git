import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取房屋数量 树状图
export function getHouseTree (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'communityUnit/community/selectTreeInfo?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
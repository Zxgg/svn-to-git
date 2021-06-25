import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取社区内组织
export function getCommunityOrg (id, childId) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/countOrganization?communityId=' + id + '&communityChildId=' + childId,
    method: 'get'
  })
}
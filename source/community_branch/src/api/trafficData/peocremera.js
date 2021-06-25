import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取人员通行抓拍
export function getpeocremera (id, childID, entity) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'count/personnelPassSnap?communityId=' + id + '&communityChildId=' + childID,
    method: 'get',
    // params: entity
  })
}
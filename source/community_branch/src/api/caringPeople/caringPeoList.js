import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取关爱人员列表
export function getCaringPeopleList (id, childID) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'caringPerson/caring_person/getList?communityId=' + id + '&communityChildId=' + childID,
    method: 'get'
  })
}
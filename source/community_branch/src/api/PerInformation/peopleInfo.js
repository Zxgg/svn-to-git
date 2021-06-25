import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 获取人员数据
export function getPeopleInfoApi (id) {
  return request({
    url: configInfo.SmartNetworkRestAPIEndPointUri + 'communityUnit/personnel_information/getByGridId/' + id,
    method: 'get'
  })
}
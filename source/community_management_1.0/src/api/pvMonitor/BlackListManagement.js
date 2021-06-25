import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'

export function findBlackListList (param) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/search_bwdata',
    method: 'post',
    data: param
  })
}

import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'

export function findBayonetList (param) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/bayonet_query',
    method: 'post',
    data: param
  })
}


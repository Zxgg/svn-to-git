import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'

// 查询组织机构列表
export function getOrgList (queryData) {
  return request({
    url: configInfo.VideoBasicsRestAPIEndPointUri + 'organizationalstructure/queryByPage?currentPage=' + queryData.currentPage + '&pageSize=' + queryData.pageSize + '',
    method: 'put'
  })
}

export function getOrgTree (queryData) {
  return request({
    url: configInfo.VideoBasicsRestAPIEndPointUri + 'organizationalstructure/findOrgTreeWeb',
    method: 'post',
    data: queryData
  })
}
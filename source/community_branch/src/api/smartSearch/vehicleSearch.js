import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'
// 根据特征查询车辆信息
export function getVehicleByParameter (queryData) {
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/search_vehicle',
    method: 'post',
    data: queryData
  })
}
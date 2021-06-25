import request from '@/utils/request'
import configInfo from '@/webconfig/configInfo'

export function searchAlarmHistory (data) {
  let entity = {
    'page': {
      'no': data.pageIndex,
      'pageSize': data.pageSize
    },
    'order': 'desc',
    'rule': data.ruleType,
    'end-time': data.endTimeInt,
    'start-time': data.startTimeInt
  }
  if (data.taskId != null && data.taskId.length > 0) {
    entity.suspectIdList = {
      'id': data.taskId
    }
  }
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/search_alarm',
    method: 'post',
    data: entity
  })
}
// 根据告警ID获取告警图片（历史告警）
export function getAlarmHistoryImage (data) {
  let ids = '?type=' + data.type + '&alarm_id=' + encodeURI(data.alarmId) + ''
  ids = ids.replace('#', '%23')
  return request({
    url: configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/alarm_pic' + ids,
    method: 'put'
  })
}
// 根据告警ID获取告警原图图片（历史告警原图）
export function getAlarmOriginalImage (objData) {
  let Url = 'mediaManagement/get_image'
  // let objData = {

  // }

  return http.apiPut(configInfo.DBHuaWeiVcmRestAPIEndPointUri + Url, '', objData)
}
// 查询车辆具体信息-黑名单管理-查询黑名单
export function searchAlarmHistoryCarInfo (param) {
  let entity = {
    'condition': {
      'page': param.pageIndex,
      'limit': param.pageSize
    },
    'type': '0',
    'plate': {
      'plateId': param.plateId
    }
  }
  return http.apiPost(configInfo.DBHuaWeiVcmRestAPIEndPointUri + 'intelligent_analysis/search_bwdata', entity)
}

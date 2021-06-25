import request from '@/utils/request'

// 统计-正在隔离家庭/总共隔离家庭
export function getIsolationOfFamily (query) {
  return request({
    url: '/count/isolationOfFamily',
    method: 'get',
    params: query
  })
}
//统计-正在隔离人员/总共隔离人员
export function getIsolationOfPersonnel (query) {
  return request({
    url: '/count/isolationOfPersonnel',
    method: 'get',
    params: query
  })
}
//统计-隔离家庭核酸检测总次数
export function getNucleicAcidTestingNum (query) {
  return request({
    url: '/count/nucleicAcidTestingNum',
    method: 'get',
    params: query
  })
}
//统计-隔离原因类型
export function getIsolationReason (query) {
  return request({
    url: '/count/isolationReason',
    method: 'get',
    params: query
  })
}
//统计 新增隔离家庭/已有隔离家庭 时间曲线
export function getIsolationFamilyTimeCurve (query) {
  return request({
    url: '/count/isolationFamilyTimeCurve',
    method: 'get',
    params: query
  })
}
//统计-个人上报 有风险人数/总共人数 时间曲线
export function getReportInfo (query) {
  return request({
    url: '/count/reportInfo',
    method: 'get',
    params: query
  })
}


import request from "@/utils/request";
import http from '@/libs/http.js'
import configInfo from '@/webconfig/configInfo'
// 查询社区事件 列表
export function listCommunity_event(query) {
  return request({
    url: "/communityEvent/community_event/list",
    method: "get",
    params: query
  });
}
// 查询事件详情详细
export function getInfo(id) {
  return request({
    url: "/property_company/base_event_info/" + id,
    method: "get"
  });
}

// 查询事件列表
export function listRecord(query) {
  return request({
    url: "/property_company/base_event_record/list",
    method: "get",
    params: query
  });
}
// 查询社区事件 详细
export function getCommunity_event(id) {
  return request({
    url: "/communityEvent/community_event/" + id,
    method: "get"
  });
}

// 新增社区事件
export function addCommunity_event(data) {
  return request({
    url: "/communityEvent/community_event",
    method: "post",
    data: data
  });
}

// 修改社区事件
export function updateCommunity_event(data) {
  return request({
    url: "/communityEvent/community_event",
    method: "put",
    data: data
  });
}

// 删除社区事件
export function delCommunity_event(id) {
  return request({
    url: "/communityEvent/community_event/" + id,
    method: "put"
  });
}

// 导出社区事件
export function exportCommunity_event(query) {
  return request({
    url: "/communityEvent/community_event/export",
    method: "get",
    params: query
  });
}

//查询社区事件上报列表
export function baseEventInfoList(query) {
  return request({
    url: "/property_company/base_event_info/list",
    method: "get",
    params: query
  });
}
// 右边的查询列表
export function baseshijianEventInfoList(query) {
  return request({
    url: "/property_company/base_event_info/queryEventInfoList",
    method: "get",
    params: query
  });
}
//处理社区事件上报
export function handleEvents(data) {
  return request({
    url: "/property_company/base_event_info/handleEvents",
    method: "post",
    data: data
  });
}

// 受理的上报接口
export function shouliEvents(data) {
  return request({
    url: "/property_company/base_event_info/updateEventsStatus",
    method: "post",
    data: data
  });
}

//1+4的请求数据接口
export function getshangbao(data) {
  return request({
    url: "/alert/getFromOneFour",
    method: "post",
    data: data
  });
}
//入库的1+4的接口请求
export function newGetEventData(query){
  return request ({
    url:"/property_company/base_event_info/getEventData",
    method:"get",
    params:query
  })
}


// 1+4的上传上报接口
export function pushshangbao(data) {
  return request({
    url: "/alert/pushToOneFour",
    method: "post",
    data: data
  });
}

export function ajaxFuntion(data) {
  let Url = configInfo.DBRestAPIEndPointUri + '/alert/getFromOneFour'
  return http.apiPost(Url, data)
}

export function ajaxFuntionup(data) {
  let Url = configInfo.DBRestAPIEndPointUri + '/alert/pushToOneFour'
  return http.apiPost(Url, data)
}

// 事件数目总量
export function eventNumber() {
  return request({
    url: "/property_company/base_event_info/countEvent",
    method: "post",
  });
}
///获取分布数据
export function getAnalysisData(query){
  return request({
    url:"property_company/base_event_info/getAnalysisData",
    method:"get",
    params:query
  })
}

// 事件查询数据
export function getEventDistribution(data){
  return request({
    url:"/property_company/base_event_info/getEventDistribution",
    method:"post",
    data:data
  })
}
//获取1+4的数据
export function getHandleAndConclude(query){
  return request({
    url:"/property_company/base_event_info/getHandleAndConclude",
    method:"get",
    params:query
  })
}

import configInfo from '@/config/configInfo.js'
// export default{
//      get_community_api,
// 	 addPerson_epidemic_state_record 
// }

import request from '@/util/request.js'
// 根据字典类型查询字典数据信息
export function getDicts (dictType) {
	let url= configInfo.DBRestAPIEndPointUri+'/system/data/type/out/' + dictType;
	return request.get(url)
}
// 获取社区
export function communityApi () {
	let url= configInfo.DBRestAPIEndPointUri+'/app/communityUnitCommunityList';
	return request.get(url)
}
// 获取小区
export function communityChildApi (id) {
	let url= configInfo.DBRestAPIEndPointUri+'/app/communityUnitCommunityChildList?communityId='+id;
	return request.get(url)
}
// 获取社区公告
export function communityNoticeApi (query) {
	let url= configInfo.DBRestAPIEndPointUri+'/app/systemNoticeList';
	return request.get(url,query)
}
//获取社区公告详情
export function communityNoticeDetailApi(id){
	let url= configInfo.DBRestAPIEndPointUri+'/app/system/notice/'+id;
	return request.get(url)
}
//获取报事报修列表
export function repairRecordApi (query) {
	let url= configInfo.DBRestAPIEndPointUri+'/app/communityServicesRepairRecordList';
	return request.get(url,query)
}
//获取投诉列表
export function complaintApi (query) {
	let url= configInfo.DBRestAPIEndPointUri+'/app/communityServicesOpinionRecordList';
	return request.get(url,query)
}
//获取投票信息列表
export function votingApi (query) {
	let url= configInfo.DBRestAPIEndPointUri+'/app/communityServicesVoteProjectList';
	return request.get(url,query)
}
//获取投票详细列表
export function votingDetailApi (id) {
	let url= configInfo.DBRestAPIEndPointUri+'/app/communityServices/vote_project/' + id;
	return request.get(url)
}
//图片上传
export function imageApi () {
	let url= configInfo.DBRestAPIEndPointUri+'/app/uploadImageSample';
	return request.get(url)
}
// 新增疫情信息收集
export function addPerson_epidemic_state_record (data) {
	let url= configInfo.DBRestAPIEndPointUri+'/epidemic/person_epidemic_state_record/add';
	return request.post(url,data)
}
// 新增人员登记表
export function addpersonRegistrationApi (data) {
	let url= configInfo.DBRestAPIEndPointUri+'/app/communityUnitPersonnelInformation';
	return request.post(url,data)
}
// 新增預約拜訪人员登记表
export function addpersonVisitApi (data) {
	let url= configInfo.DBRestAPIEndPointUri+'/app/passManageVisitPerson';
	return request.post(url,data)
}
// 新增报事报修登记表
export function addrepairRecordApi (data) {
	let url= configInfo.DBRestAPIEndPointUri+'/app/communityServicesRepairRecord';
	return request.post(url,data)
}

// 新增投诉意见记录
export function addcomplaintsApi (data) {
	let url= configInfo.DBRestAPIEndPointUri+'/app/communityServicesOpinionRecord';
	return request.post(url,data)
}

//获取事件上报的列表
export function getEventListPage (data) {
	let url= configInfo.DBRestAPIEndPointUri+'/property_company/base_event_info/list';
	return request.get(url,data)
}

//新增事件上报
export function addEvent (data) {
	let url= configInfo.DBRestAPIEndPointUri+'/property_company/base_event_info';
	return request.post(url,data)
}

//删除事件上报
export function removeEvent (id) {
	let url= configInfo.DBRestAPIEndPointUri+'/property_company/base_event_info/'+id;
	return request.put(url,null)
}

//查看事件上报
export function showEvent (id) {
	let url= configInfo.DBRestAPIEndPointUri+'/property_company/base_event_info/'+id;
	return request.get(url,null)
}

//修改事件上报
export function editEvent (data) {
	let url= configInfo.DBRestAPIEndPointUri+'/property_company/base_event_info';
	return request.put(url,data)
}

//获取事件类型树形
export function divisionTree (data) {
	let url= configInfo.DBRestAPIEndPointUri+'/common/divisiontree';
	return request.get(url,data);
}

//根据事件id查询流程
export function getgroupstatus (id) {
	let url= configInfo.DBRestAPIEndPointUri+'/property_company/base_event_record/getgroupstatus/'+id;
	return request.get(url,null);
}

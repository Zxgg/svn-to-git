
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
// 新增疫情信息收集
export function addPerson_epidemic_state_record (data) {
	let url= configInfo.DBRestAPIEndPointUri+'/epidemic/person_epidemic_state_record/add';
	// debugger
	return request.post(url,data)
}

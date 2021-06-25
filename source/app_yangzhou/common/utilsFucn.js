// import Vue from 'vue'
import configInfo from '@/config/configInfo.js'
// import {
// 	getDeviceBySN
// } from '@/api/camera.js'
// import {
// 	setStartStream,
// 	setStartStreamCeshi,
// 	setStopStream,
// 	startLiveGBSRecord,
// 	downloadLiveGBSRecord,
// 	stopLiveGBSRecord,
// 	RealtimeRecordquery,
// 	RealtimeRecordStart,
// 	RealtimeRecordStop,
// 	controlLiveGBSRecord
// } from '@/api/LiveGBS'
// 获取日期字符串 格式为 yyyy-MM-dd
export function dataFormat(time, format = 'yyyy-MM-dd HH:mm:ss') {
	if (time === null) return ''
	// time = time.replace(new RegExp(/-/gm) ,"/");
	var data = new Date(time)
	var o = {
		'M+': data.getMonth() + 1, // month
		'd+': data.getDate(), // day
		'H+': data.getHours(), // hour
		'm+': data.getMinutes(), // minute
		's+': data.getSeconds(), // second
		'q+': Math.floor((data.getMonth() + 3) / 3), // quarter
		'S': data.getMilliseconds() // millisecond
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (data.getFullYear() + '').substr(4 - RegExp.$1.length))
	}
	var week = {
		'0': '一',
		'1': '二',
		'2': '三',
		'3': '四',
		'4': '五',
		'5': '六',
		'6': '日'
	}
	if (/(E+)/.test(format)) {
		format = format.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? '星期' : '周') : '') + week[this.getDay() +
			''])
	}

	for (var k in o) {
		if (new RegExp('(' + k + ')').test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length === 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length))
		}
	}
	return format
}

// 获取n个月前的今天 n是有范围限制的，最多只能到去年1月，更久之前就不行了 n <= (当前月数+12)
export function getLastMonth(n){
	// debugger
    var returnDate, 
        date = new Date(),
        year = date.getFullYear(),
        month = date.getMonth() + 1, // 时间的月份是从0~11
        day = date.getDate()
    if(parseInt(month)<10){
        month = '0'+month;
    }
    if(parseInt(day)<10){
        day = '0'+day;
    }
    returnDate = year + '-' + (parseInt(month) - n) + '-' + day
    //需要考虑特殊情况:
    //1、当前月份小于等于n月，n个月前就是属于上一年的了
    //2、当天是31号，n个月前的今天有没有31号
    if(parseInt(month) <= n){ //month等于n,n个月就是属于去年
        year = parseInt(year) - 1;
        returnDate = year + '-' + ((12 - n) + parseInt(month)) + '-' + day;
        var prevSize = new Date(year,((12 - n) + parseInt(month)),0).getDate; //获取n个月前的总天数
        if(prevSize < parseInt(day)){ 
            returnDate = year + '-' + (12 - n + 1) + parseInt(month) + '-01'
        }
    }else{
        var prevSize = new Date(year,parseInt(month) - n,0).getDate; //获取n个月前的总天数
        if(prevSize < parseInt(day)){
            returnDate = year + '-' + (parseInt(month) - (n-1)) + '-01'
        }
    }
    return returnDate;
}

export function getDateFromString(time) {
	// var time="2014-08-12 09:25:24 ";
	time = time.replace(/-/g, ':').replace(/\//g, ':').replace(' ', ':');
	// let list=time.split(' ')
	time = time.split(':');
	var time1 = new Date(time[0], (time[1] - 1), time[2], time[3], time[4], time[5]);
	return time1
}
export function textLimit(text, maxLength = 6) {
	if (!(text && text.length > 0)) return ''
	if (text.length > maxLength) {
		console.log('textLimit:' + text, text.length, text.slice(0, maxLength))
		return text.slice(0, maxLength) + '...'
	} else {
		return text
	}
}
export function Guid() {
	function S4() {
		return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1)
	}
	return (S4() + S4() + '-' + S4() + '-' + S4() + '-' + S4() + '-' + S4() + S4() + S4())
}
export function getPXFromRPX(rpx,windowWidth) {
	let size = Number.isNaN(parseFloat(rpx)) ? 0 : parseFloat(rpx)
	let px=size/750*windowWidth
	return px
}
export function getRPXFromPX(px,windowWidth) {
	let size = Number.isNaN(parseFloat(px)) ? 0 : parseFloat(px)
	let rpx=size/windowWidth *750
	return rpx
}
export function getDictLabel(dictList,type) {
	if(dictList.length == 0) return;
	if(!type)type=''
	type=type+''
	let result=''
	let rows=dictList.filter(item=>{
		return item.dictValue==type
	})
	if(rows && rows.length>0){
		result=rows[0].dictLabel
	}
	return result
}
export function getNowTime() {
	var now = new Date()
	var year = now.getFullYear() // 得到年份
	var month = now.getMonth() // 得到月份
	var date = now.getDate() // 得到日期
	var hour = now.getHours() // 得到小时
	var minu = now.getMinutes() // 得到分钟
	var sec = now.getSeconds() // 得到秒

	month = month + 1
	if (month < 10) month = '0' + month
	if (date < 10) date = '0' + date
	if (hour < 10) hour = '0' + hour
	if (minu < 10) minu = '0' + minu
	if (sec < 10) sec = '0' + sec

	var time = ''
	time = year + '-' + month + '-' + date + ' ' + hour + ':' + minu + ':' + sec + ' '
	return time
}
// export function getTimeFromString(timeString){
// 	let list1=getListFromString(timeString,' ')
// 	let ymdList=getListFromString(list1[0])
// 	let hmsList=getListFromString(list1[1])
// }
export async function getHlsHistoryStreamByCameraCodeForContorl(gbCode, startTime, endTime) { // 获取历史视频 流   cameraCode国标设备编号 startTime和endTime 格式为 YYYY-MM-DDTHH:mm:ss
	let url = ''
	let snapPath=''
	let datainfo = {
		serial: configInfo.deviceserial,
		starttime: startTime,
		endtime: endTime,
		code: gbCode
	}
	let LiveGBSURl = configInfo.LiveGBS.split('/api');
	var response = await startLiveGBSRecord(datainfo)
	let videoType = configInfo.videoType
	if (response) {
		if (videoType === 3) {
			if (response.WS_FLV) {
				url = response.WS_FLV
				if (LiveGBSURl.length > 1){
					snapPath=LiveGBSURl[0] + response.SnapURL
				}
			}
		} else if (videoType === 2) {
			if (response.RTMP) {
				url = response.RTMP
			}
		} else if (videoType === 1) {
			if (response.HLS) {
				url = response.HLS
			}
		} else if (videoType === 4) {
			if (response.HLS) {
				url = response.FLV
				if (LiveGBSURl.length > 1){
					snapPath=LiveGBSURl[0] + response.SnapURL
				}
			}
		}
	}
	let returnData = {
		url: url,
		snapPath:snapPath,
		streamid: response.StreamID
	}
	return returnData
}
export async function getHlsStreamByCameraGBCodeForRecord(gbCode) { // 获取实时播放流 此方法返回的参数中多一个streamid  （cameraCode国标设备编号）
	let url = ''
	let streamid = ''
	let snapPath=''
	let videoType = configInfo.videoType
	// let gbCode = await commonFunction.getGbCodeBySn(cameraCode) //获取国标编码
	// let code = cameraCode.split('#')
	if (gbCode == '') {
		uni.showToast({
			title: '设备编码不存在，请联系管理员！',
			duration: 1000
		})
		return
	}
	// 不直连设备
	// let dataInfo = {
	// 	serial: configInfo.deviceserial,
	// 	code: gbCode
	// }
	//直连设备
	let dataInfo = {
		serial: configInfo.deviceserial,
		code: gbCode
	}
	let LiveGBSURl = configInfo.LiveGBS.split('/api');
	// var response = await setStartStream(dataInfo)
	let response = await setStartStreamCeshi(dataInfo)

	console.log('response:', response)
	if (response) {
		if (videoType === 3) {
			if (response.WS_FLV) {
				url = response.WS_FLV 
				if (LiveGBSURl.length > 1){
					snapPath=LiveGBSURl[0] + response.SnapURL
				}
			}
		} else if (videoType === 2) {
			if (response.RTMP) {
				url = response.RTMP
			}
		} else if (videoType === 1) {
			if (response.HLS) {
				url = response.HLS
			}
		} else if (videoType === 4) {
			if (response.HLS) {
				url = response.FLV
				if (LiveGBSURl.length > 1){
					snapPath=LiveGBSURl[0] + response.SnapURL
				}
			}
		}
		streamid = response.StreamID
	}
	
	let returnData = {
		url: url,
		snapPath:snapPath,
		streamid: streamid
	}

	console.log('url8888:', url)

	return returnData
}
export async function getHlsStreamByCameraCodeForRecord(cameraCode) { // 获取实时播放流 此方法返回的参数中多一个streamid  （cameraCode国标设备编号）
	let url = ''
	let streamid = ''
	var videoType = configInfo.videoType
	let gbCode = await commonFunction.getGbCodeBySn(cameraCode) //获取国标编码
	// let code = cameraCode.split('#')
	if (gbCode == '')
		return
	let dataInfo = {
		serial: configInfo.deviceserial,
		code: gbCode
	}
	// var response = await setStartStream(dataInfo)
	var response = await setStartStreamCeshi(dataInfo)

	console.log('response:', response)
	if (response) {
		if (videoType === 3) {
			if (response.WS_FLV) {
				url = response.WS_FLV
			}
		}
		if (videoType === 2) {
			if (response.RTMP) {
				url = response.RTMP
			}
		}
		if (videoType === 1) {
			if (response.HLS) {
				url = response.HLS
			}
		}
		streamid = response.StreamID
	}

	let returnData = {
		url: url,
		streamid: streamid
	}

	console.log('url8888:', url)

	return returnData
}
export async function getGbCodeBySn(cameraCode) {
	let gbCode = ''
	let deviceEntity = {
		'snList': cameraCode
	}
	let deviceResponse = await getDeviceBySN(deviceEntity)
	debugger
	if (deviceResponse.status === '200' && deviceResponse.data.length > 0) {
		if (deviceResponse.data[0].uiStatus != 0)
			gbCode = deviceResponse.data[0].gbCode
	}
	return gbCode;
}
export function getListFromString(str, separChar = ',') {
	if (str && str.length > 0) {
		return str.split(separChar)
	} else {
		return []
	}
}
export function getStringFromList(list, separChar = ',') {
	if (list && list.length > 0) {
		return list.join(separChar)
	} else {
		return ''
	}
}

export function getQueryListFromString(pathSearch) {
	let query = {}
	if (pathSearch && pathSearch.length > 0) {
		// let path=pathSearch.trim('?',left)
		let path = String.prototype.trimLeft.call(pathSearch, '?')
		let paramKeyValueList = getListFromString(path, '&')
		if (paramKeyValueList && paramKeyValueList.length > 0) {
			for (let i = 0; i < paramKeyValueList.length; i++) {
				let param = getListFromString(paramKeyValueList[i], '=')
				if (param && param.length === 2) {
					query[param[0]] = param[1]
				} else if (param && param.length === 1) {
					query[param[0]] = null
				}
			}
		}
	}
	return query
}

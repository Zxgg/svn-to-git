/**
 * 配置文件初始化
 */
// import axios from 'axios'
import configInfo from './configInfo'
// import {get} from '@/util/request.js'
import request from '@/util/request.js'
const configPath = '/static/narsConfig.json' //http://10.16.183.203:8081/h5/static/narsConfig.json

const getFolderPath=(path)=>{
	path.split('/')
}
const webconfig = async () => {
	// hash: "#/"
	// host: "10.16.183.203:8081"
	// hostname: "10.16.183.203"
	// href: "http://10.16.183.203:8081/#/"
	// origin: "http://10.16.183.203:8081"
	// pathname: "/"
	// port: "8081"
	// protocol: "http:"
	
	
	// hash: "#/"
	// host: "10.16.183.203:8080"
	// hostname: "10.16.183.203"
	// href: "http://10.16.183.203:8080/h5/index.html#/"
	// origin: "http://10.16.183.203:8080"
	// pathname: "/h5/index.html"
	// port: "8080"
	// protocol: "http:",
	let hostFolder=''
	if(window && window.location){
		console.log('********有window')
	}
	else{
		console.log('*******没有')
	}
	console.log('window.location:',window.location)
	let url=window.location.href
	if(url && url.indexOf('.html')){
		//html 页面 url包含 .html
		// pathname: "/isstech/yqtb/h5/index.html"
		let folderPath=window.location.pathname.split('.html') // ['/isstech/yqtb/h5/index']
		console.log('folderPath:',folderPath)
		let xgLastIndex=folderPath && folderPath.length>0?folderPath[0].lastIndexOf('/'):-1//
		console.log('xgLastIndex:',xgLastIndex)
		hostFolder=xgLastIndex>=0?folderPath[0].substring(0,xgLastIndex):''
		console.log('hostFolder:',hostFolder)
		
	}else {
		//其他页面，比如开发机 http://localhost:8080/pages/NARS/NARS#/
		hostFolder=''
	}
	// let configFullPath=hostFolder+configPath
	// let host = window.location.origin
	// console.log( '配置文件地址:',host + configFullPath)
	// const configs = await request.get(host + configFullPath)
	// configInfo.baseUrl = host
	// configInfo.DBRestAPIEndPointUri = configs.DBRestAPIEndPointUri
	// console.log('baseurl:' + configInfo.DBRestAPIEndPointUri)
}
export default webconfig

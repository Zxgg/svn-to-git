/**
 * 配置文件初始化
 */
// import axios from 'axios'
import configInfo from './configInfo'
// import {get} from '@/util/request.js'
import request from '@/util/request.js'
// import {
//   getLocalIP
// } from '@/api/role'
const configPath = '/static/narsConfig.json' //http://10.16.183.203:8081/static/narsConfig.json
// let user = this.$store.getters.loginUser
// let org = this.$store.getters.getCurrentOrg

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
	
	//自适应部署路径 需要先获取当前应用在服务器上的部署路径
	let folderPath=window.location.pathname.split('/') // h5 index.html
	for(let i=0;i<folderPath.length;){
		if(folderPath[i] && folderPath[i].length>0){
			i++
		}else{
			folderPath.splice(i,1)
		}
	}
	// console.log( folderPath)
	let hostFolder=''
	for(let i=0;i<folderPath.length-1;i++){
		hostFolder=hostFolder+'/'+folderPath[i]
	}
	// console.log( hostFolder)
	let configFullPath=hostFolder+configPath
	// console.log( configFullPath)
	let host = window.location.origin
	// console.log( window.location)
	const configs = await request.get(host + configFullPath)
	configInfo.baseUrl = host
	// configInfo.DBRestAPIEndPointUri = configs.DBRestAPIEndPointUri
	configInfo.DBRestAPIEndPointUri = configs.DBRestAPIEndPointUri
	configInfo.imageUrl = configs.imageUrl
	// configInfo.vcmImageUrl = "http://10.16.123.58:10086"
	// console.log('baseurl:' + configInfo.DBRestAPIEndPointUri)
	// console.log('vcmImageUrl' + configInfo.vcmImageUrl)

	// await store.dispatch('setConfigs', configs.data)
}
export default webconfig

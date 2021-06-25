/**
 * 配置文件初始化
 */
import axios from 'axios'
import configInfo from './configInfo'
import testData from '@/common/testData';
// import {
//   getLocalIP
// } from '@/api/role'
const configPath = process.env.BASE_URL + 'config.json'
const testDataPath = process.env.BASE_URL + 'yzData.json'
// let user = this.$store.getters.loginUser
// let org = this.$store.getters.getCurrentOrg

const webconfig = async (store) => {
  const configs = await axios.get(configPath)
  configInfo.DBRestAPIEndPointUri = configs.data.DBRestAPIEndPointUri
  configInfo.VideoBasicsRestAPIEndPointUri = configs.data.VideoBasicsRestAPIEndPointUri
  configInfo.DBHuaWeiVcmRestAPIEndPointUri = configs.data.DBHuaWeiVcmRestAPIEndPointUri
  configInfo.SmartNetworkRestAPIEndPointUri = configs.data.SmartNetworkRestAPIEndPointUri
  configInfo.gisServer = configs.data.gisServer
  configInfo.isUseImageProxy = configs.data.isUseImageProxy
  configInfo.imgEchoAddress = configs.data.imgEchoAddress

  configInfo.vcmImageUrl = configs.data.vcmImageUrl
  configInfo.WebSocketUrl = configs.data.WebSocketUrl
  configInfo.deviceserial = configs.data.deviceserial
  configInfo.videoType = configs.data.videoType
  configInfo.LiveGBS = configs.data.LiveGBS
  console.log('' + configInfo.DBRestAPIEndPointUri)

  // await store.dispatch('setConfigs', configs.data)
  //加载测试数据
  const tdata = await axios.get(testDataPath)
  testData.shouye = tdata.data.shouye
  testData.guanai = tdata.data.guanai
  testData.renyuan = tdata.data.renyuan
  testData.car = tdata.data.car
  testData.house = tdata.data.house
  testData.event = tdata.data.event
}
export default webconfig

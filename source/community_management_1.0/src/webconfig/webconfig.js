/**
 * 配置文件初始化
 */
import axios from 'axios'
import configInfo from './configInfo'

const configPath = process.env.BASE_URL + 'config.json'

const webconfig = async (store) => {
  const configs = await axios.get(configPath)
  configInfo.DBRestAPIEndPointUri = configs.data.DBRestAPIEndPointUri
  configInfo.FileUpLoadUri = configs.data.FileUpLoadUri
  configInfo.imgEchoAddress = configs.data.imgEchoAddress
  configInfo.epidemicDeclareUrl = configs.data.epidemicDeclareUrl
  configInfo.VideoBasicsRestAPIEndPointUri = configs.data.VideoBasicsRestAPIEndPointUri
  configInfo.StreamingMediaUrl = configs.data.StreamingMediaUrl
  configInfo.DBHuaWeiVcnRestAPIEndPointUri = configs.data.DBHuaWeiVcnRestAPIEndPointUri
  configInfo.DBHuaWeiVcmRestAPIEndPointUri = configs.data.DBHuaWeiVcmRestAPIEndPointUri
  configInfo.vcmImageUrl = configs.data.vcmImageUrl
  configInfo.videoType = configs.data.videoType
  configInfo.LiveGBS = configs.data.LiveGBS
  configInfo.WebSocketIP = configs.data.WebSocketIP
  configInfo.isUseImageProxy = configs.data.isUseImageProxy
  configInfo.deviceserial = configs.data.deviceserial
  configInfo.gisServer = configs.data.gisServer
}
export default webconfig

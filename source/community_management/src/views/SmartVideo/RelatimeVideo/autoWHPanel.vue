<template>
  <div class="Main"
       ref="MainView">
    <div class="TopContainer">
      <!-- <a style="font-size: 32px;"
         title="一分屏"
         @click="setScreen(1)"
         class="icon iconfont">&#xe632;</a> -->
      <!-- <a title="四分屏"
         @click="setScreen(4)"
         class="icon iconfont">&#xe633;</a>
      <a title="九分屏"
         @click="setScreen(9)"
         class="icon iconfont">&#xe634;</a> -->
      <!-- <a title="麦克风"
               class="icon iconfont">&#xe635;</a> -->
      <a title="关闭预览"
         class="icon iconfont"
         @click="closeVideoStream">&#xe636;</a>
      <a title="截图"
         class="icon iconfont"
         @click="ScreenShot">&#xe637;</a>
      <!-- <a title="放大"
             class="icon iconfont">&#xe638;</a>
          <a title="添加到主题"
             class="icon iconfont">&#xe63a;</a>
          <a title="一键回放"
             class="icon iconfont">&#xe63b;</a> -->
      <!-- <a title="地图定位"
         @click="jumpMap"
         class="icon iconfont">&#xe63c;</a> -->
      <!-- <a title="最大化"
             class="icon iconfont">&#xe639;</a> -->
      <!-- <a title="录像"
         class="icon iconfont"
         @click="localRecord">&#xe640;</a> -->
    </div>
    <div class="CenterContainer">
      <div class="centerContainerDiv"
           v-for="(num, index) in screenNumber"
           :key="index"
           :style="styleStr">
        <videoPlay :ref="'videoPlayer' + num"
                   :id="'OneScreenVideo' + num"
                   style="height:100%; width:100%;"></videoPlay>
      </div>
    </div>

    <el-dialog title="图像编辑"
               :visible.sync="screenShotDialogVisible"
               width="850px"
               center
               append-to-body
               class="screenShot-style">
      <screenShot ref="screenShot"
                  :camName="cameraInfoData.name">
      </screenShot>
    </el-dialog>
  </div>
</template>
<script>
// import cameraTreeLazy from '@/components/cameraTree/cameraTree_lazy'
// import ckvideo from '@/components/ckplayer/ckvideo'
// import esayplayer from '@/components/ckplayer/esayplayer'

import videoPlay from '@/components/ckplayer/vuevideo'
// import videoPlay from '@/components/ckplayer/videoPlay'

// import videoPlay2 from '@/components/ckplayer/vueplayerT1'
import screenShot from '@/components/SmartVideo/RelatimeVideo/screenShot'
import { searchVideoDownloadTask } from '@/api/smartVideo/VideoDowaLoad'
export default {
  components: { videoPlay, screenShot },
  data () {
    return {
      that: this,
      disabledShot: false,
      url1: 'rtmp://202.69.69.180:443/webcast/bshdlive-pc',
      url2: 'http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8',
      url3: 'rtmp://10.129.252.49:1935/aaaa/aabb',
      sourceUrl: '',
      checkedPontsName: 'test',

      styleStr: '', // 屏幕样式
      screenNumber: 4, // 屏幕数量

      nowSelectScreenId: '', // 当前选择屏幕id
      playOrder: 0, // 播放顺序
      nowPlaySrc: [], // 正在播放列表
      playFormsDataList: [],
      // 弹窗
      screenShotDialogVisible: false,
      cameraInfoData: {
        onlineCount: 0,
        taskType: null,
        code: '',
        parentOrgCode: '',
        deviceCount: 0,
        children: null,
        captureType: 2,
        dataType: '',
        name: '',
        monitorPointType: 0,
        sn: '',
        uiStatus: 0
      },
    }
  },
  created () { },
  filters: {},
  methods: {
    test () {
      // this.sourceUrl = this.url3
      this.$refs.videoPlayer0.playVideo(this.url3)
    },
    // 分屏
    setScreen (value) {
      this.screenNumber = value
      let screenColNum = Math.sqrt(this.screenNumber)
      this.styleStr = 'height:calc(100% /' + screenColNum + ' - 4px)!important;' + 'width:calc(100% /' + screenColNum + ' - 4px)!important; float:left!important;'
      console.log('style:', this.styleStr)
      // this.setCanvasSize()
      let self = this

      setTimeout(() => {
        self.setCanvasSize()
        // self.loadPlayVideo()
      }, 100)
    },
    selectScreen (self) {
      // this.nowSelectScreenId = self.currentTarget.id
    },
    getVideoPlayState (isVideoPlay) {
      // this.isVideoPlay = isVideoPlay
    },
    changePlayStatus (status) { //获取子组件的播放状态
      if (status) {
        this.disabledShot = false
      } else {
        this.disabledShot = true
        this.sourceUrl = ''
        this.checkedPontsName = ''
      }
    },
    closeVideoStream () {
      let self = this

      this.playFormsDataList.some((item, i) => {
        if (item.nowSelectScreenIdData === this.nowSelectScreenId) {
          let codeArray = []
          codeArray.push(item.cameraCodeData)
          self.commonFunction.stopHlsStream(codeArray)
          self.playFormsDataList.splice(i, 1)
          return true
        }
      })
      let id = '1'
      // if (num !== 0 && num !== undefined && (typeof (num) === 'number') && (num !== Infinity) && !isNaN(num)) {
      // this.disposeVideoPlay(num + '')
      // } else {
      if (this.nowSelectScreenId.length > 1) {
        let start = this.nowSelectScreenId.split('OneScreenVideo') // 获得字符串的开始位置
        if (start.length > 1) { id = start[1] }// 截取字符串
      } else {
        id = this.nowSelectScreenId.toString()
      }
      this.disposeVideoPlay(id)
      this.removePlayVideoInfoData(this.nowSelectScreenId)
      // }
    },
    ScreenShot () {
      if (this.nowSelectScreenId === '') return
      // if (this.isVideoPlay === false) return
      this.screenShotDialogVisible = true
      this.$nextTick(function () {
        this.$refs.screenShot.ScreenShot(this.nowSelectScreenId, document.getElementById(this.nowSelectScreenId).children[0].children[1].children[0].children[0])
      })
    },
    // 跳转到地图
    jumpMap (data) {
      let dataCode = ''
      for (let i = 0; i < this.playFormsDataList.length; i++) {
        if (this.playFormsDataList[i].nowSelectScreenIdData === this.nowSelectScreenId) {
          dataCode = this.playFormsDataList[i].cameraCodeData
        }
      }
      if (dataCode !== '') {
        this.$router.push({ path: '/GisView', name: 'GisView', params: { cameraSN: dataCode } })
      }
    },
    async localRecord () {
      var playModel = this.playFormsDataList.find((p) => p.nowSelectScreenIdData === this.nowSelectScreenId) // 根据当选中屏幕的ID，获取播放摄像机code
      if (playModel !== undefined && playModel !== null) {
        let userId = this.$store.getters.loginUser.id
        let entity = {
          'currentPage': 1,
          'pageSize': 1,
          'entity': {
            'userId': userId,
            'cameraCode': playModel.cameraCodeData,
            'videoType': 1, // 类型为录像
            'videoState': 1 // 状态为进行中
          }
        }
        let response = await searchVideoDownloadTask(entity) // 判断当前设备是否已经在录像中
        let isRecord = false
        if (response.data != null && response.data.status === '200' && response !== undefined) {
          if (response.data.data !== null && response.data.data.length > 0) {
            isRecord = true
          } else { isRecord = false }
        }
        if (!isRecord) {
          this.commonFunction.GetLocalRecord(playModel.cameraCodeData, false, '', 1) // 根据设备code 讲视频下载到本地
          this.$message.info('录像开始!')
        } else {
          this.$message.warning('当前设备正在录像中，不能重复录像！')
        }
      } else {
        this.$message.warning('请先播放视频！')
      }
    },
    async cameraChange (camera) {
      this.cameraInfoData = camera
      let strRtsp = await this.commonFunction.getHlsStreamByCameraCode(camera.sn)
      console.log('camerachange strRtsp:', strRtsp)
      if (this.nowSelectScreenId === '') {
        this.nowSelectScreenId = 'OneScreenVideo1' // 默认第一个窗口播放第一个视频
      }
      if (this.nowSelectScreenId !== '') {
        let start = this.nowSelectScreenId.split('OneScreenVideo') // 获得字符串的开始位置
        if (start.length > 1) {
          this.playOrder = parseInt(start[1])
        }// 截取字符串
        if (this.playOrder > 0 && this.playOrder <= this.screenNumber) {
        } else {
          this.playOrder = 1
          this.nowSelectScreenId = 'OneScreenVideo1'
        }
        this.addnowPlaySrc(camera.sn, strRtsp, this.nowSelectScreenId)
        // this.setPlayVideoInfoData()
        let trueOrfalse = false
        for (let i = 0; i < this.playFormsDataList.length; i++) {
          if (this.playFormsDataList[i].nowSelectScreenIdData === this.nowSelectScreenId) {
            this.playFormsDataList[i].cameraCodeData = camera.sn
            this.playFormsDataList[i].strRtspData = strRtsp
            trueOrfalse = true
          }
        }
        if (trueOrfalse === false) {
          let data = {
            cameraCodeData: camera.sn,
            strRtspData: strRtsp,
            nowSelectScreenIdData: this.nowSelectScreenId
          }
          this.playFormsDataList.push(data)
        }
      } else {
        this.$message.warning('请选择播放的屏幕')
      }
      this.playVideoSrc(this.nowSelectScreenId, strRtsp)
      this.nowSelectScreenId = 'OneScreenVideo' + this.playOrder.toString()
      this.playOrder++
    },
    // 添加播放列表
    addnowPlaySrc (cameraCode, src, id) {
      let ishave = false

      this.nowPlaySrc.find((item) => {
        if (item.id === id) {
          ishave = true
          item.src = src
          item.cameraCode = cameraCode
        }
      })
      if (!ishave) {
        let entity = {
          src: src,
          cameraCode: cameraCode,
          id: id
        }
        this.nowPlaySrc.push(entity)
      }
    },
    // 播放列表存store
    setPlayVideoInfoData () {
      let entity = {
        VideoData: this.nowPlaySrc
      }
      this.$store.dispatch('setPlayVideoInformation', entity).then((dataTwo) => {
      })
    },
    // 播放视频
    playVideoSrc (myVideo, src) {
      if (myVideo !== '') {
        let id = '1'
        if (myVideo.length > 1) {
          let start = myVideo.split('OneScreenVideo') // 获得字符串的开始位置
          if (start.length > 1) { id = start[1] }// 截取字符串
        } else {
          id = myVideo.toString()
        }
        let videoPlayRefName = 'videoPlayer' + id
        this.$refs[videoPlayRefName][0].playVideo(src)
      }
    },
    setCanvasSize () { // 根据切屏修改水印canvas大小
      let self = this
      let nowPlayFormsDataList = []
      this.playFormsDataList.forEach(item => {
        let start = item.nowSelectScreenIdData.split('OneScreenVideo') // 获得字符串的开始位置
        let id = '1'
        if (start.length > 1) { id = start[1] }// 截取字符串
        let videoPlayRefName = 'videoPlayer' + id
        if (self.$refs[videoPlayRefName].length > 0) {
          self.$refs[videoPlayRefName][0].clearCanvas()
          self.$refs[videoPlayRefName][0].drawCanvasWatermark()
          nowPlayFormsDataList.push(item)
        }
      })

      this.playFormsDataList = nowPlayFormsDataList
    },
    // 关闭预览有问题（nowSelectScreenId+1）
    closeVideoStream () {
      let self = this

      this.playFormsDataList.some((item, i) => {
        if (item.nowSelectScreenIdData === this.nowSelectScreenId) {
          let codeArray = []
          codeArray.push(item.cameraCodeData)
          self.commonFunction.stopHlsStream(codeArray)
          self.playFormsDataList.splice(i, 1)
          return true
        }
      })
      let id = '1'
      // if (num !== 0 && num !== undefined && (typeof (num) === 'number') && (num !== Infinity) && !isNaN(num)) {
      // this.disposeVideoPlay(num + '')
      // } else {
      if (this.nowSelectScreenId.length > 1) {
        let start = this.nowSelectScreenId.split('OneScreenVideo') // 获得字符串的开始位置
        if (start.length > 1) { id = start[1] }// 截取字符串
      } else {
        id = this.nowSelectScreenId.toString()
      }
      this.disposeVideoPlay(id)
      this.removePlayVideoInfoData(this.nowSelectScreenId)
      // }
    },
    // 重新渲染组件
    disposeVideoPlay (id) {
      let videoPlayRefName = 'videoPlayer' + id
      this.$refs[videoPlayRefName][0].screenClose()
    },
    removePlayVideoInfoData (nowSelectScreenIdData) {
      let nowPlaySrcData = []
      for (let i = 0; i < this.nowPlaySrc.length; i++) {
        if (this.nowPlaySrc[i].id !== nowSelectScreenIdData) {
          nowPlaySrcData.push(this.nowPlaySrc[i])
        }
      }
      this.nowPlaySrc = nowPlaySrcData
      this.setPlayVideoInfoData()
    },
    ScreenShotCancel () {
      this.screenShotDialogVisible = false
    },
  },
  mounted () {
    this.setScreen(1)
    this.$Notice.$on('cameravideoplay', (camera) => { this.cameraChange(camera) })
    this.$Notice.$on('ScreenShotCancel', this.ScreenShotCancel())
  }
}
</script>
<style lang="scss" scoped>
.Main {
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
  // background: rgb(214, 9, 37);
}
.TopContainer {
  width: 100%;
  // height: 50px;
  border: 1px solid #005982;
  margin: 0 auto;
  overflow: auto;
  // background: red;
}
.CenterContainer {
  width: 100%;
  // height: 100%;
  height: calc(100%);
  // background: green;
  // padding: 4px;
}
.centerContainerDiv {
  margin: 2px;
}
.iconfont {
  float: left;
  font-size: 26px;
  margin: 10px 5px 5px 5px;
  line-height: 32px;
}
.screenShot-style {
  text-align: center !important;
  position: absolute;
  z-index: 1;
}
</style>
<template>
  <div class="Main"
       ref="MainView">
    <el-button @click="test">加载/停止视频</el-button>
    <!-- <videoPlay ref="videoPlayer0"
               id="OneScreenVideo0"
               @click.native="selectScreen($event)"
               @isVideoPlay="getVideoPlayState()"
               :videoPlayId=0
               style="height:99.4%; width:99.4%;"></videoPlay> -->
    <videoPlay2 />
  </div>
</template>
<script>
// import cameraTreeLazy from '@/components/cameraTree/cameraTree_lazy'
// import ckvideo from '@/components/ckplayer/ckvideo'
// import esayplayer from '@/components/ckplayer/esayplayer'
import videoPlay from '@/components/ckplayer/vuevideo'
import videoPlay2 from '@/components/ckplayer/vueplayerT1'
export default {
  components: { videoPlay, videoPlay2 },
  data () {
    return {
      that: this,
      centContainerHeight: '100px',
      disabledShot: false,
      url1: 'rtmp://202.69.69.180:443/webcast/bshdlive-pc',
      url2: 'http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8',
      url3: 'rtmp://10.129.252.49:1935/aaaa/test123',
      sourceUrl: '',
      checkedPontsName: 'test'
    }
  },
  created () { },
  filters: {},
  methods: {
    refreshCenterContainerHeight () {
      this.centContainerHeight = (this.$refs.MainView.offsetHeight - 72 - 331 - 4) + 'px'
    },
    test () {
      // this.sourceUrl = this.url3
      this.$refs.videoPlayer0.playVideo(this.url3)
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
    }
  },
  mounted () {
    const the_page = this;
    // window.onresize = () => { return (() => { the_page.refreshCenterContainerHeight() })() }; 
    this.refreshCenterContainerHeight()
  }
}
</script>
<style lang="scss" scoped>
.Main {
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
  background: darkseagreen;
}
</style>
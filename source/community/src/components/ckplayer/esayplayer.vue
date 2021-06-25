<template>
  <div class="ali-player">
    <div class="main">
      <!-- <EasyPlayer :videoUrl="sourceUrls"
                  autoplay
                  live
                  stretch></EasyPlayer> -->
      <EasyPlayer video-url="rtmp://10.129.252.49:1935/aaaa/test123" />
    </div>
  </div>
</template>
<script>
// import EasyPlayer from '@easydarwin/easyplayer';
import EasyPlayer from 'easy-player'
export default {
  name: '',
  components: { EasyPlayer },
  props: {
    sourceUrl: {
      type: String,
      default: ''
    },
    height: {
      type: String,
      default: '550px'
    }
  },
  data () {
    return {
      sourceUrls: this.sourceUrl,//如果不赋值，在加载组件时会报错
      videoObject: null,
      player: null,
      loadingVideo: false,
      waiting: false,
      reloadPlayTime: null //当视频流获取超时定时器
    }
  },
  computed: {},
  watch: {
    sourceUrl: {
      handler (newVal, oldVal) {
        // if (this.reloadPlayTime) { //重新播放或者播放新视频时，清空定时器
        //   clearTimeout(this.reloadPlayTime);
        // }
        this.sourceUrls = newVal
        this.playVideo()
      },
      // 代表在wacth里声明了firstName这个方法之后立即先去执行handler方法
      immediate: false
    }
  },
  created () { },
  methods: {
    playVideo () {

      this.player = null

      return true
    },
  },
  beforeDestroy () {
    clearTimeout(this.reloadPlayTime);
    this.player = null //销毁播放器
  }
}
</script>

<style lang="scss" scoped>
.ali-player {
  width: 100%;
  .main {
    box-sizing: border-box;
    color: #ffffff;
    .video-center {
      position: relative;
      .name {
        position: absolute;
        left: 50%;
        top: -20px;
        font-size: 18px;
        -webkit-transform: translateX(-50%);
        transform: translateX(-50%);
      }
      .tips {
        position: absolute;
        top: 50%;
        left: 50%;
        -webkit-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
        z-index: 9999;
        opacity: 0.79;
      }
    }
  }
  #video {
    width: 100%;
    height: 500px !important;
    opacity: 0.79;
  }
}
</style>

<template>
  <div class="videoPlay">
    <div class="videoPlay"
         :id="'videoPlayerBody' + videoPlayId">
      <canvas id="canvasVideo"
              ref="canvasVideo"
              class="canvasVideoPlay">
      </canvas>
      <video-player class="video-player vjs-custom-skin"
                    id="videoPlayer"
                    style="height:100%; width:100%;"
                    ref="videoPlayer"
                    v-if="reWrite"
                    :options="playerOptions"
                    :playsinline="true"
                    customEventName="customstatechangedeventname"
                    @ready="playerReadied($event)"
                    @loadeddata="onPlayerLoadeddata($event)"
                    @canplay="onPlayerCanplay($event)"
                    @canplaythrough="onPlayerCanplaythrough($event)"
                    @play="onPlayerPlay($event)"
                    @playing="onPlayerPlaying($event)"
                    @timeupdate="onPlayerTimeupdate($event)"
                    @pause="onPlayerPause($event)"
                    @waiting="onPlayerWaiting($event)"
                    @fullscreenchange="onPlayerFullScreenchange($event)"
                    @statechanged="playerStateChanged($event)">
      </video-player>
      <!-- <div class="video-close"
           v-if="isPlay && isShowCloseButton"
           @click="screenClose">关闭</div> -->

      <!-- <button class="icon iconfont videoScreen"
              v-if="isPlay"
              @click="fullScreen">&#xe639;</button> -->

    </div>

  </div>
</template>

<script >
import configInfo from '@/webconfig/configInfo'
import videojs from 'video.js'
import 'videojs-flash'
import flvjs from 'flv.js'
window.videojs = videojs
// import hls from 'videojs-contrib-hls'
// hls plugin for videojs6
require('videojs-contrib-hls/dist/videojs-contrib-hls.js')
export default {
  name: 'VideoPlay',
  // 参数
  props: {
    videoPlayId: {
      type: Number,
      default: 0
    },
    isShowCloseButton: {
      type: Boolean,
      default: true
    }

  },
  data () {
    return {
      reWrite: true,
      events: null,
      isPlay: false,
      playerOptions: {
        techOrder: ['html5', 'flash'], // 兼容顺序,使用flash播放，可以播放flv格式的文件
        // playbackRates: [0.7, 1.0, 1.5, 2.0, 3.0], // 播放速度

        autoplay: true, // 如果true,浏览器准备好时开始回放
        notSupportedMessage: '此视频暂无法播放!', // 无法播放时显示的信息
        sourceOrder: true,
        loop: false, // 导致视频一结束就重新开始。
        preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        muted: true, // 默认情况下将会消除任何音频。
        language: 'zh-CN',
        // aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: false, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        hls: true, // 启用hls？
        // flash: {
        //   hls: { withCredentials: false },
        //   swf: '../../assets//videojs/video-js.swf' // 放在static或着public下面文件夹中的videojs文件夹中
        // },
        flash: { hls: { withCredentials: false } },
        html5: { hls: { withCredentials: false } },
        // html5: { hls: { withCredentials: false } },
        sources: [
          // {
          //   type: 'video/mp4', // 资源格式写法：'video/mp4'
          //   src: '/video/1.mp4' // 本地资源地址，注意：本地资源存放在public文件夹中，本文视频资源路径public/video/1.mp4
          // },
          // {
          //   withCredentials: false,
          //   type: 'application/x-mpegURL',
          //   src: 'http://10.16.123.136:80/live/09020360394697740101%23f9e8841e443441be87d99faa62f6bb65/hls.m3u8' // url地址
          // }
          // {
          //   type: 'rtmp/flv', // rtmp
          //   src: 'rtsp://admin:HuaWei123@10.16.105.116/LiveMedia/ch1/Media1'// rtmp
          // }
          // {
          //   type: 'rtmp/mp4', // 资源格式写法：'video/mp4'
          //   src: '//vjs.zencdn.net/v/oceans.mp4' // url地址
          // }
        ],
        // poster: 'path', // 你的封面地址
        // width: document.documentElement.clientWidth,
        // height: this.fileAreaHeight, // 设置高度，fluid需要设置成flase
        // 配置控制栏
        controlBar: {
          volumePanel: {
            inline: false // 音量调节是否水平
          },
          timeDivider: false, // 时间分割线
          currentTimeDisplay: false, // 当前播放位置
          durationDisplay: false, // 总时间
          progressControl: false, // 进度条
          remainingTimeDisplay: false, // 剩余时间
          fullscreenToggle: false // 全屏按钮
        }
      },
      VideoHeightAndWidth: [],
      WatermarkTitle: 'admin',
      isFullScreen: false,
      videoType: null,
    }
  },
  watch: {
    isPlay: {
      handler () {
        this.$emit('isVideoPlay', this.isPlay)
      }
    }
  },
  computed: {
    player () {
      return this.$refs.videoPlayer.player
    }
  },
  mounted () {
    this.videoType = configInfo.videoType
    this.WatermarkTitle = this.$cf.getWaterTxt()
    let self = this
    window.addEventListener('fullscreenchange', function (e) {
      if (self.isFullScreen) {
        // $('.fullScreen').attr('data-original-title', '退出全屏')
        self.isFullScreen = false
      } else {
        self.isFullScreen = true
      }
      if (self.isPlay === true) {
        // let self = this
        self.drawCanvasWatermark()
        // setTimeout(() => {

        // }, 200)
      }
    })
  },
  methods: {
    // --------------------视频播放-开始-------------------- */
    // 初始化话播放-在onPlayerCanplay中调用
    initPlay (player) {
      // console.log('initPlay>当前视频播放器实例对象', this.player);
      player.play()
      //
    },

    /* 视频播放 */
    // 视频准备完毕
    // playerReadied (player) {
    //   console.log('Readied>视频准备完毕!', player)
    // },

    // 视频加载完成
    onPlayerLoadeddata (player) {
      console.log('Loadeddata>视频加载完成!', player)
    },

    // 可以播放视频
    onPlayerCanplay (player) {
      console.log('Canplay>可以播放视频!', player)
      this.initPlay(player)
    },

    // 拖动视频条会触发事件-视频暂停>可以播放视频>可以播放视频至拖动位置>视频播放>视频加载中>可以播放视频>视频播放中>视频加载中>可以播放视频>视频播放中>视频加载中>可以播放视频>视频播放中>可以播放视频至拖动位置
    // 可以播放视频至拖动位置
    onPlayerCanplaythrough (player) {
      console.log('Canplaythrough>可以播放视频至拖动位置!', player)
    },

    // 视频播放
    onPlayerPlay (player) {
      console.log('Play>视频播放!', player)
      this.isPlay = true
      this.drawCanvasWatermark()
      player.play()
    },

    // 视频播放中
    onPlayerPlaying (player) {
      console.log('Playing>视频播放中!', player)
    },

    // 视频时间更新中
    onPlayerTimeupdate (player) {
      // console.log('Timeupdate>视频时间更新中!', player);
    },

    // 视频暂停
    onPlayerPause (player) {
      console.log('Pause>视频暂停!', player)
    },

    // 视频加载中
    onPlayerWaiting (player) {
      console.log('Waiting>视频加载中!', player)
    },

    // 视频状态改变
    playerStateChanged (playerCurrentState) {
      console.log('StateChanged>视频状态改变', playerCurrentState)
    },

    // 视频播放结束
    onPlayerEnded (player) {
      console.log('Ended>视频播放结束!', player)
      this.isPlay = false
    },
    // 全屏变更触发
    onPlayerFullScreenchange (player) {
      console.log('视频全屏', player.isFullscreen_)
      // this.$emit('onPlayerFullScreenchange', player.isFullscreen_, this.videoPlayId, this.isPlay)
      // player.exitFullscreen()
      // player.exitFullscreen() // 强制退出全屏，恢复正常大小
    },
    // 切换视频
    playVideo (url) {
      console.log('url:', url)
      if (url) {
        if (this.videoType === 1) { // 播放流为HLS
          this.playerOptions.sources = [
            {
              type: 'application/x-mpegURL',
              autoplay: true,
              src: url // 本地资源地址，注意：本地资源存放在 static 文件夹中，本文视频资源路径 static/topicMaterial/1.mp4
            }
          ]
        }
        if (this.videoType === 2) { // 播放流为rtmp
          this.playerOptions.sources = [
            {
              autoplay: true,
              src: url //
            }
          ]
        }
        if (this.videoType === 3) { // 播放流为FLV
          this.initPlayer(url)
        }
      } else {
        this.playerOptions.sources = []
      }
    },
    initPlayer (url) {
      let self = this
      if (flvjs.isSupported()) {
        var videoElement = document.getElementById('videoPlayer')
        this.flvPlayer = flvjs.createPlayer({
          type: 'flv',
          isLive: true,
          url: url,
          hasAudio: false
          // autoCleanupSourceBuffer: true, //是否清楚缓存
          // enableStashBuffer: false // 是否开启缓存模式
        })
        this.flvPlayer.attachMediaElement(videoElement.children[0].children[0])
        this.flvPlayer.load()
        setTimeout(() => {
          this.flvPlayer.play()
        }, 150)
        // this.flvPlayer.play()
        this.isPlay = true
        this.flvPlayer.on('error', err => {
          self.isConnection = false
          console.log('播放异常', err)
          if (err === 'NetworkError' || err === 'MediaError') {

            this.initPlay()
            // if (self.timer === null) {
            //   console.log('进入', err)
            //   self.isConnection = false
            //   // 重新请求
            //   self.startConnection()
            //   self.timer = setInterval(self.startConnection, 30000) // 开启定时 30秒一次
            // }
          }
        })

        this.flvPlayer.on('loading_complete', function () {
          console.log('视频源异常')
          if (self.timer === null) {
            console.log('进入视频源异常')
            self.isConnection = false
            // 重新请求
            self.startConnection()
            self.timer = setInterval(self.startConnection, 30000) // 开启定时 30秒一次
          }
        })
      }
    },
    playVideoDelete () {
      this.playerOptions.sources = []
    },
    playerReadied (player) {
      let self = this
      // console.log('Readied>视频准备完毕!')
      this.events = player
      player.tech_.off('dblclick')
    },
    getVideoHeightAndWidth () { // 获取视频流高宽
      let videoInfo = {
        videoHeight: this.$refs.videoPlayer.$refs.video.videoHeight,
        videoWidth: this.$refs.videoPlayer.$refs.video.videoWidth
      }
      // this.VideoHeightAndWidth.push(this.$refs.videoPlayer.$refs.video.videoHeight)
      // this.VideoHeightAndWidth.push(this.$refs.videoPlayer.$refs.video.videoWidth)
      return videoInfo
    },
    // 重新渲染组件
    dispose () {
      this.reWrite = false
      this.$nextTick(function () {
        this.reWrite = true
      })
    },
    screenClose () {
      this.playVideo('')
      this.dispose()
      this.clearCanvas()
      this.isPlay = false
      if (this.isFullScreen === true) { // 如果视频全屏，退出全屏
        this.fullScreen()
      }
      // this.$emit('closeScreen', this.videoPlayId)
    },
    fullScreen () {
      var VideoDiv = document.getElementById('videoPlayerBody' + this.videoPlayId)
      if (this.isFullScreen === false) {
        // this.isFullScreen = true
        this.launchIntoFullscreen(VideoDiv)
      } else {
        // this.isFullScreen = false
        this.exitFullscreen(VideoDiv)
      }
    },
    // 开启全屏函数封装
    launchIntoFullscreen (element) {
      if (element.requestFullscreen) {
        element.requestFullscreen()
      } else if (element.mozRequestFullScreen) {
        element.mozRequestFullScreen()
      } else if (element.webkitRequestFullscreen) {
        element.webkitRequestFullscreen()
      } else if (element.msRequestFullscreen) {
        element.msRequestFullscreen()
      }
    },
    // 关闭全屏函数封装
    exitFullscreen (element) {
      if (document.exitFullscreen) {
        document.exitFullscreen()
      } else if (document.mozCancelFullScreen) {
        document.mozCancelFullScreen()
      } else if (document.webkitExitFullscreen) {
        document.webkitExitFullscreen()
      }
    },
    drawCanvasWatermark () {
      var canvas = this.$refs.canvasVideo
      var VideoDiv = document.getElementById('videoPlayerBody' + this.videoPlayId)
      canvas.width = VideoDiv.offsetWidth
      canvas.height = VideoDiv.offsetHeight
      // console.log(this.canvas.width)
      var ctx = canvas.getContext('2d')
      ctx.rotate((-40 * Math.PI) / 180) // 水印初始偏转角度
      let number = 20
      ctx.font = '14px microsoft yahei'
      ctx.fillStyle = 'rgba(255, 255, 255, 0.1)'

      for (let i = 1; i < number; i++) {
        for (let j = 1; j < i + 1; j++) {
          ctx.fillText(this.WatermarkTitle, -600 + j * 350, i * 100)
        }
      }
    },
    // 清空canvas 画布
    clearCanvas () {
      var canvas = this.$refs.canvasVideo
      var VideoDiv = document.getElementById('videoPlayerBody' + this.videoPlayId)
      canvas.width = VideoDiv.offsetWidth
      canvas.height = VideoDiv.offsetHeight
      var ctx = canvas.getContext('2d')
      ctx.clearRect(0, 0, canvas.width, canvas.height)
    }

    // --------------------视频播放-结束-------------------- */
  }
}
</script>
<style>
.vjs-custom-skin > .video-js .vjs-big-play-button {
  background-color: rgba(0, 0, 0, 0.45);
  font-size: 0em !important;
  /* border-radius: 50%; */
  height: 2em !important;
  line-height: 2em !important;
  margin-top: -1em !important;
}
.vjs-error .vjs-error-display .vjs-modal-dialog-content {
  font-size: 0em !important;
  text-align: center;
}
.vjs-custom-skin > .video-js {
  pointer-events: none;
}
.vjs-fluid {
  /* padding-top: 48.2% !important; */
}
/* .vjs-tech {
  pointer-events: none;
} */
video {
  width: 100% !important;
  height: 100% !important;
  /* height: calc(100vh - 95px) !important; */
  object-fit: fill;
}
.vjs-picture-in-picture-control.vjs-control.vjs-button {
  display: none !important;
}
.vjs-custom-skin > .video-js {
  height: 100%;
}
</style>
<style lang="scss" scoped>
.videoPlay {
  height: 100%;
  width: 100%;
  position: relative;
}

.video-close {
  position: absolute;
  top: 5px;
  right: 5px;
  // margin-right: 20px;
  // margin-top: 10px;
  color: white;
  font-size: 14px;
  background-color: gray;
  opacity: 0.7;
  padding: 2px 5px;
  cursor: pointer;
  border-radius: 2px;
  max-width: 120px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.videoScreen {
  position: absolute;
  bottom: 5px;
  right: 10px;
  // margin-right: 20px;
  // margin-top: 10px;
  color: white;
  font-size: 30px;
  // background-color: gray;
  opacity: 0.7;
  padding: 0px 0px;
  cursor: pointer;
  border-radius: 2px;
  max-width: 120px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  background: transparent;
  border: none;
}
.canvasVideoPlay {
  background: transparent;
  // border-bottom: 0.5px solid white;
  float: left;
  z-index: 999;
  // -webkit-pointer-events: none;
  // -moz-pointer-events: none;
  // -ms-pointer-events: none;
  // -o-pointer-events: none;
  pointer-events: none;
  position: absolute;
  right: 0px;
}
</style>

export default {
  data () {
    return {
      // menu_Width: 200,
      data_top_height: 100,
      data_ContentPanel_height: 200,
      data_left_width: 300,
      data_middle_width: 200,
      data_right_width: 300,
      $ele: null
    }
  },
  mounted () {
    this.initListener()
  },
  activated () {
    if (!this.$ele) {
      // avoid duplication init
      this.initListener()
    }
  },
  beforeDestroy () {
    this.destroyListener()
  },
  deactivated () {
    this.destroyListener()
  },
  methods: {
    $eleResizeHandler (e) {
      console.log('eleResizeHandler')
      if (e.propertyName === 'width') {
        // data_ContentPanel_height=
      }
      else if (e.propertyName === 'height') {
      }
    },
    initListener () {
      this.$ele = document.getElementById('dataContainer')
      this.refreshDataContentLayout()
      //注意： Safari 3.1 到 6.0 版本才支持该事件, 使用webkitTransitionEnd前缀。
      // object.addEventListener("webkitTransitionEnd", myScript);
      this.$ele && this.$ele.addEventListener('transitionend', this.$eleResizeHandler)
    },
    destroyListener () {
      this.$ele && this.$ele.removeEventListener('transitionend', this.$eleResizeHandler)
    },
    refreshDataContentLayout () {
      this.setDataContentLayout(this.data_top_height, this.data_left_width, this.data_right_width)
    },
    setDataContentLayout (dataTopHeight, dataLeftWidth, dataRightWidth) {
      this.data_top_height = dataTopHeight
      this.data_left_width = dataLeftWidth
      this.data_right_width = dataRightWidth
      if (this.$ele !== undefined && this.$ele !== null && this.$ele.offsetWidth === 0) this.$ele = null
      if (this.$ele === undefined || this.$ele === null) {
        this.$ele = document.getElementById('dataContainer')
        if (this.$ele === undefined || this.$ele === null) {
        }
      }
      if (this.$ele === undefined || this.$ele === null) return false
      // this.$ele = document.getElementsByClassName('sidebar-container')[0]
      let eleWidth = this.$ele.offsetWidth
      let eleHeight = this.$ele.offsetHeight
      this.data_ContentPanel_height = eleHeight - dataTopHeight
      this.data_middle_width = eleWidth - dataLeftWidth - dataRightWidth - 2

    }
  }
}
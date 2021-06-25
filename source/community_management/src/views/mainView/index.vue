<template>
  <div class="app-wrapper mainContainerLimitWidth">
    <!-- 系统logo -->
    <div class="SystemTitleRow ">
      <topHeader class="VerticalCenter" />
    </div>
    <!-- 主区域 -->
    <div class="middleContainer divStackPanel_left  mainContainerLimitHeight">
      <!-- 主区域内的菜单 router_menu  navData -->
      <div class="container_leftBar"
           :style="{width: menu_Width+'px'}">
        <el-row class="menuHideButtonRow">
          <el-button type="cyan"
                     :icon="iconName"
                     class="menuHideButton"
                     @click="toCollapseChange"></el-button>
        </el-row>
        <menuBar2 :menuData="router_menus"
                  style="width:100%;height:96%;text-align:left;" />

      </div>
      <!-- 
       主区域内的数据展示区，内部分为
       数据区1（顶部，宽度填满）
       数据区2（左侧）
       数据区3（数据2和数据4的中间） 
       数据区4（右侧） 
       -->
      <div id="dataContainer"
           class="container_rightContent "
           :style="dataWidth">
        <!-- style="height:100%;padding-bottom:20px;"  chensz 20210125 我也不记得当初为什么要写padding-bottom:20px 了-->
        <el-scrollbar style="height:100%;"
                      wrap-class="scrollbar-wrapper "
                      wrap-style="overflow-x:hidden;">
          <div class="data_top"
               :style="{height:data_top_height+'px'}">
            <keep-alive>
              <router-view name="top_panel_router_view"
                           style="height:100%;width:100%;"></router-view>
            </keep-alive>
          </div>
          <div class="divStackPanel_HorizontalLeft data_ContentPanel"
               :style="{height:data_ContentPanel_height+'px'}">
            <div class="data_left"
                 :style="{width:data_left_width+'px'}">
              <keep-alive>
                <router-view name="left_panel_router_view"
                             style="height:100%;width:100%;"></router-view>
              </keep-alive>
            </div>
            <div class="data_middle"
                 :style="{width:data_middle_width+'px'}">
              <keep-alive :exclude="noKeepAlives">
                <router-view style="height:100%;width:100%;"></router-view>
              </keep-alive>
            </div>
            <div class="data_right"
                 :style="{width:data_right_width+'px'}">
              <keep-alive>
                <router-view name="right_panel_router_view"
                             style="height:100%;width:100%;"></router-view>
              </keep-alive>
            </div>
          </div>
        </el-scrollbar>
      </div>
    </div>
  </div>
</template>
<script>
import Cookies from 'js-cookie'
import topHeader from '@/components/Header/topHeader'
//令一种计算布局方式
import mixins_layout_prop from '@/components/ComputeProperty/mixins_layout_prop'
import AppMain from '@/components/AppMain'
import menuBar2 from '@/components/MenuBar'
import SystemlogoImage from '@/assets/images/home/Systemlogo.png'
import TitleBackgroundImage from '@/assets/images/home/TitleBackground.png'
import GisPanel from '@/views/GISView/GISView'
import { mapGetters, mapState } from "vuex";
// import CameraPanel from '@/components/VideoPlay/CameraPanel'
// import VehicleManage from '@/components/Vehicle/VehicleManage'
export default {
  components: { GisPanel, topHeader, menuBar2, AppMain },
  mixins: [mixins_layout_prop],
  data () {
    // Menu: Menu
    return {
      that: this,
      fixedHeader: true,
      noKeepAlives: 'GenEdit,Data,Profile,Vote_option,Vote_record',
      layoutSlave: {
        menuWidth: 200,
        data_top_height: '100px',
        data_ContentPanel_height: '200px',
        data_left_width: '100px',
        data_middle_width: '100px',
        data_right_width: '100px',
      },
      navData: [
      ],
      iconName: 'el-icon-s-fold',
      dataWidth: 'width: calc(100% - 200px);'
    }
  },
  created () {
    // window.addEventListener('storage', this.afterQRScan)
  },
  filters: {

  },
  methods: {
    refreshDataPanelWidth () {
      // console.log('to resize')
      this.refreshDataContentLayout()
      this.$Notice.$emit('resize', 'resize')
    },
    getTime () {
      const data = this.commonFunction.getsecond()
      return data
    },
    getPermission () {
    },
    InitDBBaseData () {
      let router = this.$store.routes
    },
    resetPage () {

    },
    shrinlMeunuViewBtnClick () {
      this.showMenuView = false
      this.dataPanelBorderWidth = '0px solid #11bbf3'
      this.dataPanelWidth = '0px'
    },
    toCollapseChange () {
      if (this.iconName === 'el-icon-s-fold') {
        this.iconName = 'el-icon-s-unfold'
        this.menu_Width = 63
        this.dataWidth = 'width: calc(100% - 63px);'
      } else {
        this.iconName = 'el-icon-s-fold'
        this.menu_Width = 200
        this.dataWidth = 'width: calc(100% - 200px);'
      }
      this.$nextTick(() => {
        this.refreshDataPanelWidth()
      })
      this.$Notice.$emit('toCollapseChange')
    }
  },
  computed: {
    ...mapGetters(["router_menus"])
  },
  watch: {
    $route: {
      handler: function (route) {
        let dataTopHeight = route.meta.top_panel_router_view_height
        let dataLeftWidth = route.meta.left_panel_router_view_width
        let dataRightWidth = route.meta.right_panel_router_view_width
        if (dataTopHeight === undefined) dataTopHeight = 0
        if (dataTopHeight === null) dataTopHeight = 0
        if (dataLeftWidth === undefined) dataLeftWidth = 0
        if (dataLeftWidth === null) dataLeftWidth = 0
        if (dataRightWidth === undefined) dataRightWidth = 0
        if (dataRightWidth === null) dataRightWidth = 0
        this.setDataContentLayout(dataTopHeight, dataLeftWidth, dataRightWidth)
      },
      immediate: true//路由发生变化时界面还未重绘完成，刷新布局没有意义，当前已经在重绘时处理
    }
  },
  mounted () {
    let _this = this
    this.InitDBBaseData()
    const the_page = this; window.onresize = () => { return (() => { the_page.refreshDataPanelWidth() })() }; this.refreshDataPanelWidth()
    this.timer = setInterval(() => {
      const tmp = this.commonFunction.getsecond() * 1000
      _this.time = _this.commonFunction.timestampToTime(tmp, true)
    }, 1000)
  },
  beforeDestroy () {
    if (this.timer) {
      clearInterval(this.timer)
    }
  }
}
</script>
<style lang="scss" scoped>
@import "~@/assets/styles/mixin.scss";
@import "~@/assets/styles/variables.scss";
@import "@/assets/styles/userDiv.scss";
@import "@/assets/styles/menu.scss";
.app-wrapper {
  @include clearfix;
  position: relative;
  height: 100%;
  width: 100%;

  &.mobile.openSidebar {
    position: fixed;
    top: 0;
  }
}
.fixed-header {
  position: fixed;
  top: 0;
  right: 0;
  z-index: 9;
  width: calc(100% - #{$sideBarWidth});
  transition: width 0.28s;
}
.el-scrollbar {
  height: 100%;
  .el-scrollbar__wrap {
    overflow-x: hidden !important;
  }
}
.el-scrollbar__wrap {
  overflow-x: hidden !important;
}
.menuHideButtonRow {
  background: $menuBackgroundColor;
  text-align: left;
  height: 4%;
  display: flex;
}
.menuHideButton {
  background-color: transparent;
  border: none;
  align-self: center;
}
</style>

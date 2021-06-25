<template>
  <div class="app-wrapper mainContainerLimitWidth"
       style="overflow-y: hidden;">

    <!-- 主区域 -->
    <div style="width:100%;height:100%">
      <!-- 地图盒子 -->
      <div class="map"
           :style="backgroundDiv">
        <GisPanel style="width:100%;height:width:100%" />
      </div>
      <!-- 主区域内的菜单 -->
      <div class="container_leftBar"
           :style="{width:menu_Width+'px'}">
        <menuBar :menuData=navData>
          <!-- <el-button type="primary"
                     @click="test">测试</el-button> -->
        </menuBar>

      </div>
      <!-- 系统logo -->
      <div class="headerStyle">
        <topHeader class="VerticalCenter" />
      </div>
      <!-- 页脚 选择社区小区 以及告警 -->
      <!-- <div class="footerStyle">
        <bootomFooter />
      </div> -->
      <!-- 
       主区域内的数据展示区，内部分为
       数据区1（顶部，宽度填满）
       数据区2（左侧）
       数据区3（数据2和数据4的中间） 
       数据区4（右侧） 
       -->
      <div style="width:100%;height:100%">
        <div class="data_left">
          <keep-alive>
            <router-view name="left_panel_router_view"></router-view>
          </keep-alive>
        </div>
        <div class="data_right">
          <keep-alive>
            <router-view name="right_panel_router_view"></router-view>
          </keep-alive>
        </div>
        <!-- <div class="data_top"
             :style="{height:data_top_height+'px'}">
          <keep-alive>
            <router-view name="left_panel_router_view"
                         style="height:100%;width:100%;"></router-view>
          </keep-alive>
        </div> -->
        <!-- <div class="routeContent">
          <keep-alive>
            <router-view name="main"></router-view>
          </keep-alive>
        </div> -->
        <!-- <div class="divStackPanel_HorizontalLeft data_ContentPanel"
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
            <keep-alive>
              <GisPanel style="width:100%;height:width:100%" />
            </keep-alive>
          </div>
          <div class="data_right"
               :style="{width:data_right_width+'px'}">
            <keep-alive>
              <router-view name="right_panel_router_view"
                           style="height:100%;width:100%;"></router-view>
            </keep-alive>
          </div> -->
      </div>

      <!-- <AppMain /> -->
    </div>

  </div>
  </div>
</template>
<script>
import Cookies from 'js-cookie'
import topHeader from '@/components/Header/topHeader'
import bootomFooter from '@/components/footer'
//令一种计算布局方式
// import layout from '@/components/ComputeProperty/layout'
// import layoutProperty from '@/components/ComputeProperty/layoutProp'
import mixins_layout_prop from '@/components/ComputeProperty/mixins_layout_prop'
import AppMain from '@/components/AppMain'
// import menuBar from '@/components/MenuBar/menuBar'
import menuBar from '@/components/MenuBar/menuBar'
import SystemlogoImage from '@/assets/images/home/Systemlogo.png'
import TitleBackgroundImage from '@/assets/images/home/TitleBackground.png'
import GisPanel from '@/views/GISView/GISView'
import $ from "jquery";
// import CameraPanel from '@/components/VideoPlay/CameraPanel'
// import VehicleManage from '@/components/Vehicle/VehicleManage'
export default {
  components: { GisPanel, topHeader, bootomFooter, menuBar, AppMain },
  mixins: [mixins_layout_prop],
  data () {
    // Menu: Menu
    return {
      ball: require("@/assets/images/home/light.png"),
      backgroundDiv: {
        backgroundImage:
          "url(" + require("@/assets/images/home/indexBackground.png") + ")",

        backgroundRepeat: "repeat",

        backgroundSize: "100% 100%"
      },
      titleBackground: {
        backgroundImage:
          "url(" + require("@/assets/images/home/title.png") + ")",

        backgroundRepeat: "repeat",

        backgroundSize: "100% 110%"
      },
      menu_Width: '',
      that: this,
      fixedHeader: true,
      layoutSlave: {
        menuWidth: 200,
        data_top_height: '100px',
        data_ContentPanel_height: '200px',
        data_left_width: '100px',
        data_middle_width: '100px',
        data_right_width: '100px',
      },
      navData: [
        {
          "name": "主页",
          "path": "/index/home",
          "hidden": false,
          "components": "{left_panel_router_view: home_leftPanel,right_panel_router_view: home_rightPanel}",
          "meta": {
            "title": "主页",
            "icon": "icon-iconfontzhizuobiaozhun023101"
          }
        },
        {
          "name": "关爱人员",
          "path": "/index/carepeople",
          "hidden": false,
          "components": "{left_panel_router_view: care_leftPanel,right_panel_router_view: care_rightPanel}",
          "meta": {
            "title": "关爱人员",
            "icon": "icon-iconfontzhizuobiaozhun023101"
          }
        },
        {
          "name": "人员信息",
          "path": "/index/peopleinfo",
          "hidden": false,
          "components": "{left_panel_router_view: people_leftPanel,right_panel_router_view: people_rightPanel}",
          "meta": {
            "title": "人员信息",
            "icon": "icon-iconfontzhizuobiaozhun023101"
          }
        },
        {
          "name": "车辆信息",
          "path": "/index/carinfo",
          "hidden": false,
          "components": "{left_panel_router_view: car_leftPanel,right_panel_router_view: car_rightPanel}",
          "meta": {
            "title": "车辆信息",
            "icon": "icon-iconfontzhizuobiaozhun023101"
          },
        },
        {
          "name": "社区房屋",
          "path": "/index/communityBuilding",
          "hidden": false,
          "components": "{left_panel_router_view: community_leftPanel,right_panel_router_view: community_rightPanel}",
          "meta": {
            "title": "社区房屋",
            "icon": "icon-iconfontzhizuobiaozhun023101"
          },
        },
        {
          "name": "通行数据",
          "path": "/index/trafficData",
          "hidden": false,
          "components": "{left_panel_router_view: trafficData_leftPanel,right_panel_router_view: trafficData_rightPanel}",
          "meta": {
            "title": "通行数据",
            "icon": "icon-iconfontzhizuobiaozhun023101"
          },
        },
        {
          "name": "智能搜索",
          "path": "/smart_search",
          "hidden": false,
          "redirect": "noRedirect",
          "component": "Layout",
          "alwaysShow": true,
          "meta": {
            "title": "智能搜索",
            "icon": "icon-iconfontzhizuobiaozhun023101"
          },
          "children": [
            {
              "name": "SystemConfig",
              "path": "/index/face_search",
              "hidden": false,
              "component": "components/VideoPlay/CameraPanel",
              "meta": {
                "title": "人脸搜索",
                "icon": "icon-gaojing",
                "viewWidth": "400"
              }
            },
            {
              "name": "User",
              "path": "/index/vehicle_search",
              "hidden": false,
              "component": "components/Vehicle/VehicleManage",
              "meta": {
                "title": "车辆搜索",
                "icon": "icon-shishijiankong",
                "viewWidth": "1530"
              }
            }

          ]
        },
        {
          "name": "Monitor",
          "path": "/monitor",
          "hidden": false,
          "redirect": "noRedirect",
          "component": "Layout",
          "alwaysShow": true,
          "meta": {
            "title": "智慧视频",
            "icon": "icon-shishijiankong"
          },
          "children": [
            {
              "name": "Online",
              "path": "/index/relatime_video",
              "hidden": false,
              "component": "components/SmartVideo/RelatimeVideo/topPanel",
              "meta": {
                "title": "实时视频",
                "icon": "icon-shishijiankong"
              }
            },
            {
              "name": "historyVideo",
              "path": "/index/historyVideo_video",
              "hidden": false,
              "component": "monitor/job/index",
              "meta": {
                "title": "历史视频",
                "icon": "icon-shishijiankong"
              }
            }
          ]
        },
        {
          "name": "Prevention",
          "path": "/index/prevention",
          "hidden": false,
          "component": "{left_panel_router_view: prevention_leftPanel,right_panel_router_view: prevention_rightPanel}",
          "meta": {
            "title": "智能防控",
            "icon": "icon-shishijiankong"
          }
        }
      ]
    }
  },
  created () {
    // window.addEventListener('storage', this.afterQRScan)
  },
  filters: {

  },
  methods: {
    mapButton () {
      alert('点击了地图!');
    },
    showMean () {
      $(".container_leftBar").slideToggle("slow");
    },
    refreshDataPanelWidth () {
      console.log('to resize')
      this.refreshDataContentLayout()
      this.$Notice.$emit('resize', 'resize')
    },

    test () {
      // this.layoutProperty.menuWidth
      let testVa = this.menu_Width
      if (testVa > 130) this.menu_Width = testVa - 2
      else if (testVa < 150) this.menu_Width = testVa + 50
      console.log('重新赋值:' + this.menu_Width)
    },
    getTime () {
      const data = this.commonFunction.getsecond()
      return data
    },
    getPermission () {
    },
    InitDBBaseData () {
    },
    resetPage () {

    },
    shrinlMeunuViewBtnClick () {
      this.showMenuView = false
      this.dataPanelBorderWidth = '0px solid #11bbf3'
      this.dataPanelWidth = '0px'
    }
  },
  computed: {
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
#ball {
  width: 120px;
  height: 40px;
  border-radius: 50%;
  position: absolute;
  animation: anim 10s linear infinite;
  z-index: 999;
}

@keyframes anim {
  0% {
    left: 170px;
    top: 2.8%;
  }

  20% {
    left: 530px;
    top: 2.8%;
  }
  23% {
    left: 560px;
    top: 4.3%;
  }
  45% {
    left: 860px;
    top: 4.3%;
  }
  60% {
    left: 1100px;
    top: 4.3%;
  }

  65% {
    left: 1200px;
    top: 4.3%;
  }
  67% {
    left: 1250px;
    top: 4.3%;
  }
  70% {
    left: 1280px;
    top: 2.8%;
  }
  85% {
    left: 1480px;
    top: 2.8%;
  }
  100% {
    left: 1680px;
    top: 2.8%;
  }
}

.app-wrapper {
  height: 100%;
  width: 100%;
  @include clearfix;
  position: relative;

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
.headerStyle {
  left: 0px;
  top: 0px;
  position: absolute;
  // height: 100px;
  width: 100%;
  height: 90px;
  background-color: transparent !important;
}
.footerStyle {
  left: 0px;
  top: calc(100% - 40px);
  position: absolute;
  z-index: 1001;
  // height: 100px;
  width: 100%;
  height: 40px;
  background-color: transparent !important;
}
.container_leftBar {
  position: absolute;
  top: 7%;
  left: 1%;
  z-index: 999;
  height: 500px;
  display: none;
}
.sidebarMeanButton {
  position: absolute;
  top: 20px;
  left: 1%;
  font-size: 26px;
  color: #fff;
}
.sidebarMeanButton:hover {
  color: #0ba2bd;
  text-align: center;
  cursor: pointer;
}
.routeContent {
  position: absolute;
  top: 10%;
  width: 100%;
}
.map {
  width: 100%;
  height: 100%;
}
.data_left {
  position: absolute;
  top: 80px;
  width: 25%;
  width: 480px;
  height: 90%;
  left: 10px;
  background-color: transparent !important;
}
.data_right {
  position: absolute;
  top: 80px;
  width: 25%;
  height: 90%;
  right: 10px;
  background-color: transparent;
}
</style>
<style>
.container_leftBar .el-menu {
  border-right: none;
}
</style>

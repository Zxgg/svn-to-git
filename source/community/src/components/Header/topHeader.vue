<template>
  <el-row class="Main">
    <el-col>
      <div class="titleBackImage"></div>
    </el-col>
    <!-- 左侧个人信息 -->
    <el-col class="edgeInfoLeft">

      <el-row class="titleLogoRow">
        <el-col class="titleText"></el-col>
        <!-- <el-col class="titleRuanTongLogo"></el-col>
        <el-col class="titleHuaWeiLogo"></el-col> -->
      </el-row>

      <div class=" divStackPanel_left textLeft userInfoPanel">

      </div>
    </el-col>
    <!-- 主区域 -->
    <el-col class="mainInfoLeft">
      <el-menu :default-active="activeIndex"
               class="horizontalMenu menuHorizontalRight"
               mode="horizontal"
               @select="handleSelect">
        <el-menu-item :index="item.path"
                      v-for="(item, index) in navData_left">{{ item.meta.title }}</el-menu-item>
      </el-menu>
    </el-col>
    <el-col class="mainInfoLogo"> </el-col>
    <el-col class="mainInfoRight">
      <el-menu :default-active="activeIndex"
               class="horizontalMenu menuHorizontalLeft"
               mode="horizontal"
               @select="handleSelect">
        <el-menu-item :index="item.path"
                      v-for="(item, index) in navData_right">{{ item.meta.title }}</el-menu-item>
      </el-menu>
    </el-col>
    <!-- 右侧退出按钮 -->
    <el-col class="edgeInfo">
      <el-row class="titleLogoRightRow">
        <!-- <el-col class="titleHuaWeiLogo"></el-col>
        <el-col class="titleRuanTongLogo"></el-col> -->
      </el-row>

      <div class=" divStackPanel_HorizontalRight textLeft userInfoPanel logout"
           @click="logoutClick">

      </div>

    </el-col>
  </el-row>
</template>
<script>
import titleImage from "@/assets/images/home/title.png";
export default {
  components: {},
  data () {
    return {
      that: this,
      // Systemlogo: SystemlogoImage,
      centContainerHeight: "100px",
      unit: "",
      currentUser: "",
      activeIndex: this.$route.path,
      //菜单信息
      navData_left: [
        {
          name: "主页",
          path: "/index/home",
          hidden: false,
          components:
            "{left_panel_router_view: home_leftPanel,right_panel_router_view: home_rightPanel}",
          meta: {
            title: "主页",
            icon: "icon-iconfontzhizuobiaozhun023101"
          }
        },

        {
          name: "人员信息",
          path: "/index/peopleinfo",
          hidden: false,
          components:
            "{left_panel_router_view: people_leftPanel,right_panel_router_view: people_rightPanel}",
          meta: {
            title: "人员信息",
            icon: "icon-iconfontzhizuobiaozhun023101"
          }
        }

      ],
      navData_right: [
        {
          name: "车辆信息",
          path: "/index/carinfo",
          hidden: false,
          components:
            "{left_panel_router_view: car_leftPanel,right_panel_router_view: car_rightPanel}",
          meta: {
            title: "车辆信息",
            icon: "icon-iconfontzhizuobiaozhun023101"
          }
        },
        // {
        //   name: "社区房屋",
        //   path: "/index/communityBuilding",
        //   hidden: false,
        //   components:
        //     "{left_panel_router_view: community_leftPanel,right_panel_router_view: community_rightPanel}",
        //   meta: {
        //     title: "社区房屋",
        //     icon: "icon-iconfontzhizuobiaozhun023101"
        //   }
        // },
        // {
        //   "name": "关爱人员",
        //   "path": "/index/carepeople",
        //   "hidden": false,
        //   "components": "{left_panel_router_view: care_leftPanel,right_panel_router_view: care_rightPanel}",
        //   "meta": {
        //     "title": "关爱人员",
        //     "icon": "icon-iconfontzhizuobiaozhun023101"
        //   }
        // },
        // {
        //   "name": "通行数据",
        //   "path": "/index/trafficData",
        //   "hidden": false,
        //   "components": "{left_panel_router_view: trafficData_leftPanel,right_panel_router_view: trafficData_rightPanel}",
        //   "meta": {
        //     "title": "通行数据",
        //     "icon": "icon-iconfontzhizuobiaozhun023101"
        //   },
        // },
        {
          name: "社区事件",
          path: "/index/events",
          hidden: false,
          components:
            "{left_panel_router_view: eventsData_leftPanel,right_panel_router_view: eventsData_rightPanel}",
          meta: {
            title: "社区事件",
            icon: "icon-iconfontzhizuobiaozhun023101"
          }
        }
        // {
        //   "name": "Prevention",
        //   "path": "/index/prevention",
        //   "hidden": false,
        //   "component": "{left_panel_router_view: prevention_leftPanel,right_panel_router_view: prevention_rightPanel}",
        //   "meta": {
        //     "title": "智能防控",
        //     "icon": "icon-shishijiankong"
        //   }
        // },
        // {
        //   "name": "智能搜索",
        //   "path": "/smart_search",
        //   "hidden": false,
        //   "redirect": "noRedirect",
        //   "component": "Layout",
        //   "alwaysShow": true,
        //   "meta": {
        //     "title": "智能搜索",
        //     "icon": "icon-iconfontzhizuobiaozhun023101"
        //   },
        //   "children": [
        //     {
        //       "name": "SystemConfig",
        //       "path": "/index/face_search",
        //       "hidden": false,
        //       "component": "components/VideoPlay/CameraPanel",
        //       "meta": {
        //         "title": "人脸搜索",
        //         "icon": "icon-gaojing",
        //         "viewWidth": "400"
        //       }
        //     },
        //     {
        //       "name": "User",
        //       "path": "/index/vehicle_search",
        //       "hidden": false,
        //       "component": "components/Vehicle/VehicleManage",
        //       "meta": {
        //         "title": "车辆搜索",
        //         "icon": "icon-shishijiankong",
        //         "viewWidth": "1530"
        //       }
        //     }

        //   ]
        // },
        // {
        //   "name": "Monitor",
        //   "path": "/monitor",
        //   "hidden": false,
        //   "redirect": "noRedirect",
        //   "component": "Layout",
        //   "alwaysShow": true,
        //   "meta": {
        //     "title": "智慧视频",
        //     "icon": "icon-shishijiankong"
        //   },
        //   "children": [
        //     {
        //       "name": "Online",
        //       "path": "/index/relatime_video",
        //       "hidden": false,
        //       "component": "components/SmartVideo/RelatimeVideo/topPanel",
        //       "meta": {
        //         "title": "实时视频",
        //         "icon": "icon-shishijiankong"
        //       }
        //     },
        //     {
        //       "name": "historyVideo",
        //       "path": "/index/historyVideo_video",
        //       "hidden": false,
        //       "component": "monitor/job/index",
        //       "meta": {
        //         "title": "历史视频",
        //         "icon": "icon-shishijiankong"
        //       }
        //     }
        //   ]
        // }
      ]
    };
  },
  created () { },
  filters: {},
  methods: {
    refreshCenterContainerHeight () {
      this.centContainerHeight =
        this.$refs.MainView.offsetHeight - 72 - 331 - 4 + "px";
    },
    loadUserInfo () {
      if (
        this.$cf.checkIsNotNull(this.$store.state.user) &&
        this.$cf.checkIsNotNull(
          this.$store.state.user.roles &&
          this.$cf.checkIsNotNull(this.$store.state.user.roles)
        )
      ) {
        // this.unit=this.$store.state.user.roles
        this.currentUser = this.$store.state.user.nickName;
      } else {
      }
    },
    logoutClick () {
      this.$confirm("确定注销并退出系统吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$store.dispatch("LogOut").then(() => {
          location.href = "/index";
        });
      });
    },
    handleSelect (index, indexPath) {
      this.$Notice.$emit('communityGridCannel', '')
      this.activeIndex = index;
      // this.activeIndex = index;
      let selectPath = index;
      console.log("当前选中：" + this.activeIndex + "," + index);
      this.$router.push({ path: selectPath });
      //
    }
  },
  mounted () {
    const the_page = this;
    this.loadUserInfo();
    // window.onresize = () => { return (() => { the_page.refreshCenterContainerHeight() })() }; this.refreshCenterContainerHeight()
  }
};
</script>
<style lang="scss" scoped>
@import "../../assets/styles/menu.scss";
/* 首页标题背景图片 */
.titleBackImage {
  position: absolute;
  background: url("../../assets/images/menu/title.png") 0 0 no-repeat;
  background-size: 100% 100%;
  width: 100%;
  height: 120px;
  z-index: -100;
}
/* 左侧默认菜单背景图片 */
.menuHorizontalRight .el-menu-item {
  text-align: center;
  background: url("../../assets/images/menu/menu-default-left.png") 0 0
    no-repeat;
  background-size: 100% 100%;
  width: 130px;
  height: 30px;
  border: 0px;
  font-size: 16px;
  font-family: YouSheBiaoTiHei;
  font-weight: 400;
}
/* 右侧默认菜单背景图片 */
.menuHorizontalLeft .el-menu-item {
  text-align: center;
  background: url("../../assets/images/menu/menu-default.png") 0 0 no-repeat;
  background-size: 100% 100%;
  width: 130px;
  height: 30px;
  border: 0px;
  font-size: 16px;
  font-family: YouSheBiaoTiHei;
  font-weight: 400;
}
.Main {
  width: 100%;
  height: 80%;
  // overflow-x: hidden;
  // overflow-y: auto;
  background: -webkit-linear-gradient(
      top,
      hsla(234, 54, 41, 1),
      hsla(234, 54, 41, 0),
      transparent
    )
    no-repeat;
}
.mainInfoLogo {
  /* background-image: url("../../assets/images/home/title.png");
  background-repeat: repeat;
  background-size: 100% 100%; */
  width: 565px;
  height: 100%;
  margin: 0px 0px;
  padding: 0px;
}
.mainInfoLeft {
  width: calc((100% - 607px - 250px - 210px) / 2);
  height: 100%;
  margin: 0px 0px;
  padding: 0px;
}
.mainInfoRight {
  width: calc((100% - 607px - 250px - 210px) / 2);
  height: 100%;
  margin: 0px 0px;
  padding: 0px;
}
.edgeInfo {
  width: 210px;
  height: 100%;
  margin: 0px 0px;
  padding: 0px;
}
.edgeInfoLeft {
  width: 250px;
  height: 100%;
  margin: 0px 0px;
  padding: 0px;
}
.titleIcon {
  color: #fff;
  font-size: 34px;
  margin-top: 20px;
  margin-left: 10px;
}

.titleLogoRow {
  margin-left: 90px;
  margin-top: 10px;
}

.titleLogoRightRow {
  margin-left: 0px;
  margin-top: 10px;
}

.titleText {
  background: url("../../assets/images/menu/文昌花园社区logo.png");
  -moz-background-size: 100% 100%;
  background-size: 100% 100%;
  width: 74px;
  height: 28px;
  margin-top: 10px;
}

.titleRuanTongLogo {
  background: url("../../assets/images/menu/软通智慧logo.png");
  -moz-background-size: 100% 100%;
  background-size: 100% 100%;
  width: 74px;
  height: 28px;
  margin-left: 20px;
  margin-top: 10px;
}

.titleHuaWeiLogo {
  background: url("../../assets/images/menu/华为logo.png");
  -moz-background-size: 100% 100%;
  background-size: 100% 100%;
  width: 30px;
  height: 28px;
  margin-left: 20px;
  margin-top: 10px;
}

.userInfoPanel {
  width: 100%;
  height: 100%;
  margin-right: 10px;
  margin-top: 10px;
  padding-left: 20px;
}
.userInfoPanel span {
  color: #fff;
}
.userInfoPanel:hover {
  cursor: pointer;
}
.HorizontalMargin {
  color: #fff;
}
.logout {
  color: #fff;
  margin-right: 34px;
  padding-right: 34px;
}
// 登出 图标
.logout i {
  font-size: 24px;
  margin-left: 12px;
}
// 登出 文字
.logout span {
  font-size: 16px;
  margin-left: 12px;
}
.logout:hover {
  color: #0ebeff;
}
.contain {
  color: #fff;
  background: red;
  width: 50px;
  height: 20px;
}
</style>
<style lang="scss">
.el-menu.el-menu--horizontal {
  border-bottom: solid 1px transparent;
}
</style>

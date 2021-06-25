<template>
  <div class="Main"
       ref="MainView">
    <div class="CenterContainer">
      <el-row>
        <el-col :span="22"
                class="pie ">
          <el-row class="PTZ_el-row">
            <el-col class="PTZ_el-eol"
                    style="padding-left: 37px; padding-top: 20px;"><span class="icon iconfont"
                    @mousedown="PTZ_instructionClick(PTZControlCommandEnum.PTZ_UP_LEFT, '2', sliderValue)"
                    @mouseup="PTZ_instructionClick(PTZControlCommandEnum.PTZ_STOP, '', '')">&#xe668;</span></el-col>
            <el-col class="PTZ_el-eol"><span class="icon iconfont"
                    @mousedown="PTZ_instructionClick(PTZControlCommandEnum.PTZ_UP, '2', sliderValue)"
                    @mouseup="PTZ_instructionClick(PTZControlCommandEnum.PTZ_STOP, '', '')">&#xe662;</span></el-col>
            <el-col class="PTZ_el-eol"
                    style="padding-right: 37px; padding-top: 20px;"><span class="icon iconfont"
                    @mousedown="PTZ_instructionClick(PTZControlCommandEnum.PTZ_UP_RIGHT, '2', sliderValue)"
                    @mouseup="PTZ_instructionClick(PTZControlCommandEnum.PTZ_STOP, '', '')">&#xe665;</span></el-col>
          </el-row>
          <el-row class="PTZ_el-row">
            <el-col class="PTZ_el-eol"><span class="icon iconfont"
                    @mousedown="PTZ_instructionClick(PTZControlCommandEnum.PTZ_LEFT, '2', sliderValue)"
                    @mouseup="PTZ_instructionClick(PTZControlCommandEnum.PTZ_STOP, '', '')">&#xe669;</span></el-col>
            <el-col class="center"><span style=" font-size: 60px !important;"
                    class="icon iconfont"
                    @click="PTZ_instructionClick(PTZControlCommandEnum.PTZ_STOP, '', '')">&#xe65e;</span></el-col>
            <el-col class="PTZ_el-eol"><span class="icon iconfont"
                    @mousedown="PTZ_instructionClick(PTZControlCommandEnum.PTZ_RIGHT, '2', sliderValue)"
                    @mouseup="PTZ_instructionClick(PTZControlCommandEnum.PTZ_STOP, '', '')">&#xe661;</span></el-col>
          </el-row>
          <el-row class="PTZ_el-row">
            <el-col class="PTZ_el-eolThree"><span class="icon iconfont"
                    @mousedown="PTZ_instructionClick(PTZControlCommandEnum.PTZ_DOWN_LEFT, '2', sliderValue)"
                    @mouseup="PTZ_instructionClick(PTZControlCommandEnum.PTZ_STOP, '', '')">&#xe663;</span></el-col>
            <el-col class="PTZ_el-eol"><span class="icon iconfont"
                    @mousedown="PTZ_instructionClick(PTZControlCommandEnum.PTZ_DOWN, '2', sliderValue)"
                    @mouseup="PTZ_instructionClick(PTZControlCommandEnum.PTZ_STOP, '', '')">&#xe66a;</span></el-col>
            <el-col class="PTZ_el-eolFour"><span class="icon iconfont"
                    @mousedown="PTZ_instructionClick(PTZControlCommandEnum.PTZ_DOWN_RIGHT, '2', sliderValue)"
                    @mouseup="PTZ_instructionClick(PTZControlCommandEnum.PTZ_STOP, '', '')">&#xe666;</span></el-col>
          </el-row>
        </el-col>

        <el-col :span="1"
                style="margin-left:10px;">
          <div class="slider_div">
            <el-row>
              <el-tooltip class="item"
                          effect="dark"
                          content="速度"
                          placement="right-start">
                <el-slider v-model="sliderValue"
                           vertical
                           height="160px"
                           :step="1"
                           :min="1"
                           :max="10"
                           show-stops>
                </el-slider>
              </el-tooltip>
            </el-row>

          </div>
        </el-col>

        <el-col :span="1"
                style="margin-left:30px;margin-top:0px">
          <el-row :span="12"
                  style="margin-top:0px">
            <el-tooltip class="item"
                        effect="dark"
                        content="变倍+"
                        placement="right">
              <span class="icon iconfont"
                    @mousedown="PTZ_instructionClick(PTZControlCommandEnum.PTZ_LENS_ZOOM_IN, '2', sliderValue)"
                    @mouseup="PTZ_instructionClick(PTZControlCommandEnum.PTZ_STOP, '', '')">&#xe6da;</span>
            </el-tooltip>
          </el-row>
          <el-row style="margin-top: 5px;">
            <el-tooltip class="item"
                        effect="dark"
                        content="变倍-"
                        placement="right">
              <span class="icon iconfont"
                    @mousedown="PTZ_instructionClick(PTZControlCommandEnum.PTZ_LENS_ZOOM_OUT, '2', sliderValue)"
                    @mouseup="PTZ_instructionClick(PTZControlCommandEnum.PTZ_STOP, '', '')">&#xe6db;</span>
            </el-tooltip>
          </el-row>
          <el-row style="margin-top: 5px;">
            <el-tooltip class="item"
                        effect="dark"
                        content="光圈+"
                        placement="right">
              <span class="icon iconfont"
                    @mousedown="PTZ_instructionClick(PTZControlCommandEnum.PTZ_LENS_APERTURE_OPEN, '2', sliderValue)"
                    @mouseup="PTZ_instructionClick(PTZControlCommandEnum.PTZ_STOP, '', '')">&#xe6d9;</span>
            </el-tooltip>
          </el-row>
          <el-row style="margin-top: 5px;">
            <el-tooltip class="item"
                        effect="dark"
                        content="光圈-"
                        placement="right">
              <span class="icon iconfont"
                    @mousedown="PTZ_instructionClick(PTZControlCommandEnum.PTZ_LENS_APERTURE_CLOSE, '2', sliderValue)"
                    @mouseup="PTZ_instructionClick(PTZControlCommandEnum.PTZ_STOP, '', '')">&#xe6d8;</span>
            </el-tooltip>
          </el-row>
          <el-row style="margin-top: 5px;">
            <el-tooltip class="item"
                        effect="dark"
                        content="调焦+"
                        placement="right">
              <span class="icon iconfont"
                    @mousedown="PTZ_instructionClick(PTZControlCommandEnum.PTZ_LENS_FOCAL_NEAR, '2', sliderValue)"
                    @mouseup="PTZ_instructionClick(PTZControlCommandEnum.PTZ_STOP, '', '')">&#xe6d7;</span>
            </el-tooltip>
          </el-row>
          <el-row style="margin-top: 5px;">
            <el-tooltip class="item"
                        effect="dark"
                        content="调焦-"
                        placement="right">
              <span class="icon iconfont"
                    @mousedown="PTZ_instructionClick(PTZControlCommandEnum.PTZ_LENS_FOCAL_FAR, '2', sliderValue)"
                    @mouseup="PTZ_instructionClick(PTZControlCommandEnum.PTZ_STOP, '', '')">&#xe6e8;</span>
            </el-tooltip>
          </el-row>
          <el-row style="margin-top: 5px;">
            <el-tooltip class="item"
                        effect="dark"
                        content="添加预置位"
                        placement="right">
              <span class="icon iconfont"
                    @click="AddPreset()">&#xe699;</span>
            </el-tooltip>
          </el-row>
        </el-col>
      </el-row>

    </div>
  </div>
</template>
<script>
import { PTZControl, PTZAddPreset, editPresetPosInfo, deletePresetPos } from "@/api/smartVideo/PTZ"
export default {
  data () {
    return {
      sliderValue: 5,
      PTZControlCommandEnum: {
        PTZ_STOP: 1, // 云台停止转动
        PTZ_UP: 2, // 云台向上转动，连续模式下可以使用PTZ_STOP停止转动
        PTZ_DOWN: 3, // 云台向下转动，连续模式下可以使用PTZ_STOP停止转动
        PTZ_LEFT: 4, // 云台向左转动，连续模式下可以使用PTZ_STOP停止转动
        PTZ_UP_LEFT: 5, // 云台左上转动，连续模式下可以使用PTZ_STOP停止转动
        PTZ_DOWN_LEFT: 6, // 云台左下转动，连续模式下可以使用PTZ_STOP停止转动
        PTZ_RIGHT: 7, // 云台向右转动，连续模式下可以使用PTZ_STOP停止转动
        PTZ_UP_RIGHT: 8, // 云台右上转动，连续模式下可以使用PTZ_STOP停止转动
        PTZ_DOWN_RIGHT: 9, // 云台右下转动，连续模式下可以使用PTZ_STOP停止转动
        PTZ_AUTO: 10, // 云台自动控制
        PTZ_PREFAB_BIT_RUN: 11, // 调用预置位
        PTZ_CRUISE_RUN: 12, // 巡航轨迹运行/模式运行
        PTZ_CRUISE_STOP: 13, // 巡航轨迹停止
        PTZ_MODE_CRUISE_RUN: 14, // 模式轨迹运行
        PTZ_MODE_CRUISE_STOP: 15, // 模式轨迹停止
        PTZ_MENU_OPEN: 16, // 打开镜头菜单
        PTZ_MENU_EXIT: 17, // 关闭镜头菜单
        PTZ_MENU_ENTER: 18, // 进入镜头菜单
        PTZ_FLIP: 19, // 云台翻转180度
        PTZ_START: 20, // PTZ_START

        // 镜头控制，包括光圈、缩放、聚焦
        PTZ_LENS_APERTURE_OPEN: 21, // 光圈放大
        PTZ_LENS_APERTURE_CLOSE: 22, // 光圈缩小
        PTZ_LENS_ZOOM_IN: 23, // 范围放大
        PTZ_LENS_ZOOM_OUT: 24, // 范围缩小
        PTZ_LENS_FOCAL_NEAR: 25, // 聚焦近
        PTZ_LENS_FOCAL_FAR: 26, // 聚焦远
        PTZ_AUX_OPEN: 27, // 打开辅助设备 1雨刷，2灯光
        PTZ_AUX_STOP: 28, // 关闭辅助设备

        // 模式
        MODE_SET_START: 29, // 模式设置开始
        MODE_SET_STOP: 30, // 模式设置停止

        // 增强操作
        PTZ_FAST_LOCATE: 31, // 快速定位对焦
        PTZ_HORIZONTAL_SCAN: 32, // 水平扫描开始
        PTZ_VERTICAL_SCAN: 33, // 垂直扫描开始
        PTZ_LOCK: 34, // 云台加锁
        PTZ_UNLOCK: 35, // 云台解锁
        PTZ_ABSOLUTE_MOVE: 36, // 转动到指定位置
        PTZ_DIRECTION_MOVE: 37 // 定向转动
      },

      cameraInfoData: null,

    }
  },
  created () { },
  filters: {},
  methods: {
    // ptz指令
    PTZ_instructionClick (PTZ_ControlCode, controlPara1, controlPara2) {
      let entity = {
        'cameraCode': this.cameraInfoData.sn,
        'controlCode': PTZ_ControlCode,
        'controlPara1': controlPara1,
        'controlPara2': controlPara2
      }
      PTZControl(entity).then(async data => {
        if (data != null) {
        }
      })
    },
    // 添加预置位
    AddPreset () {
      this.$Notice.$emit('AddPreset')
    }
  },
  mounted () {
    this.$Notice.$on('camTreeNodeClick', (camera) => {
      this.cameraInfoData = camera
    })
    this.$Notice.$on('cameravideoplay', (camera) => {
      this.cameraInfoData = camera
    })
    this.$Notice.$on('PTZ_Control', (entity) => {
      this.cameraInfoData = entity.camera
      this.PTZ_instructionClick(entity.controlCode, entity.controlPara1, entity.controlPara2)
    })
  }
}
</script>
<style lang="scss" scoped>
.Main {
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
}
.CenterContainer {
  width: 100%;
  position: inherit;
  height: calc(100% - 100px);
  background: blueviolet;
  // text-align: center;
}
// 圆盘
.PTZ_el-row {
  height: 33%;
  width: 100%;
}
.PTZ_el-eol {
  height: 86%;
  width: 33%;
  margin-top: 14px;
}

.PTZ_el-eolTwo {
  height: 86%;
  width: 33%;
  margin-top: 14px;
}

.PTZ_el-eolThree {
  height: 86%;
  width: 33%;
  margin-top: -8px;
  padding-left: 34px;
}
.PTZ_el-eolFour {
  height: 86%;
  width: 33%;
  margin-top: -8px;
  padding-right: 34px;
}
.pie {
  width: 200px;
  height: 200px;
  text-align: center;
  background: url("../../../assets/images/PTZ/yuanpan.png") no-repeat center
    center / 100% 100%;
}
.center {
  height: 86%;
  width: 33%;
  border-radius: 10px;
}
.slider_div {
  text-align: center;
}
</style>
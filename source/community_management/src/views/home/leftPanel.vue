<template>
  <div class="Main"
       ref="MainView">
    <el-scrollbar style="height:100%"
                  wrap-class="scrollbar-wrapper "
                  wrap-style="overflow-x:hidden;">
      <el-row>
        <div class="title1Contain textLeft">
          <i class="icon iconfont  iconshuxian-copy title1Icon"></i>
          <span class="title1 VerticalCenter">实有信息</span>
        </div>
      </el-row>
      <el-row v-for="(item,index) in showData"
              :key="index"
              class="box-card">
        <el-col :span="14"
                class="box-col">
          <div class="title  VerticalCenter HorizontalLeft">
            <i class="icon iconfont  iconguangquanfangda"></i>
            {{item.title}}
          </div>
        </el-col>
        <el-col :span="10"
                class="box-col">
          <div class="value  VerticalCenter HorizontalRight">
            {{item.value}}
            <!-- <i class="el-icon-caret-right"></i> -->
          </div>
        </el-col>
      </el-row>
    </el-scrollbar>
  </div>
</template>
<script>
import { countActualInformation } from "@/api/home"
export default {
  //components: { cameraTreeLazy },
  //props: {dataInfo: {type: Object,default: null}},
  data () {
    return {
      that: this,
      showData: []
    }
  },
  created () {
    this.getCountActualInformation()
  },
  mounted () { },
  filters: {},
  methods: {
    getCountActualInformation () {
      countActualInformation().then(res => {
        if (res.data) {
          this.showData = [
            {
              title: '实有房屋',
              iconClass: '',
              value: res.data.buildingCount,
              unit: '套'
            },
            {
              title: '实有人口',
              iconClass: '',
              value: res.data.personnelCount,
              unit: '人'
            },
            {
              title: '实有车辆',
              iconClass: '',
              value: res.data.vehicleCount,
              unit: '辆'
            },
            {
              title: '实有关爱人员',
              iconClass: '',
              value: res.data.caringCount,
              unit: '人'
            },
            // {
            //   title: '实有设施',
            //   iconClass: '',
            //   value: 15,
            //   unit: '台'
            // },
            {
              title: '实有组织',
              iconClass: '',
              value: res.data.organizationCount,
              unit: '个'
            },
            {
              title: '实有事件',
              iconClass: '',
              value: res.data.eventCount,
              unit: '件'
            }
          ]
        }
      })
    },
  },
  computed: {},
  watch: {
    //dataInfo: {
    //handler: function (newValue) {
    //console.log('new value change')
    //},
    //deep: true,
    //immediate: true
    //}
  },
  beforeDestroy () { }
}
</script>
<style lang="scss" scoped>
.Main {
  width: 100%;
  height: 100%;
  padding: 0px 20px;
  overflow-x: hidden;
  overflow-y: auto;
}
.title1Icon {
  color: #00c0ff;
  font-size: 24px;
}
.title1Contain {
  margin-top: 20px;
  margin-bottom: 10px;
}
.title1 {
  height: 22px;
  font-size: 16px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #000000;
  line-height: 22px;
}
.box-card {
  width: 100%;
  background: #ffffff;
  border-bottom: 1px solid #e8e8e8;
}

.box-col {
  height: 100px;
}
.title {
  margin-left: 26px;
  font-size: 14px;
  font-weight: lighter;
  font-family: PingFangSC-Light, PingFang SC;
  font-weight: 300;
  color: #999999;
}
.value {
  margin-right: 25px;
  font-size: 20px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #0091ff;
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
</style>
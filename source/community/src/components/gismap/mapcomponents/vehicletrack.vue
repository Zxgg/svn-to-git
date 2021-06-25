<template>
  <div>
    <div ref="vehiclePop"
         class="vehicleFeature-popup">
      <span ref="popup-closer"
            class="vehicleFeature-popup-closer"
            @click="vehiclePopClose">
      </span>
      <el-row>
        <el-col style="width:60%">
          <img class="image"
               v-if="vehicleFeature"
               :src="vehicleFeature.img">
        </el-col>
        <el-col style="width:40%">
          <el-row class="spanText"> <label class=" vehicleTitle">特征信息</label></el-row>
          <el-row class="spanText"><span>车牌号：</span>
            <span v-if="vehicleFeature">{{vehicleFeature.vehiclePlate}}</span>
          </el-row>
          <el-row class="spanText"><span>品牌：</span><span v-if="vehicleFeature">{{vehicleFeature.vehicleBrand}}</span></el-row>
          <el-row class="spanText"><span>型号：</span><span v-if="vehicleFeature">{{vehicleFeature.vehicleType}}</span></el-row>
          <el-row class="spanText"><span>车身颜色：</span><span v-if="vehicleFeature">{{vehicleFeature.vehicleColor}}</span></el-row>
        </el-col>
      </el-row>
      <div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['vehicleFeature'],
  data () {
    return {
    }
  },
  mounted () {
    this.initElement()
  },
  methods: {
    initElement () {
      var element = this.$refs.vehiclePop
      this.$emit('vehicleElement', element)
    },
    vehiclePopClose () {
      this.$emit('vehicleClose')
    }
  }
}
</script>

<style lang='scss'>
.vehicleFeature-popup {
  position: absolute;
  padding: 10px;
  bottom: 12px;
  left: -50px;
  width: 360px;
  height: 200px;
  background: rgba(0, 217, 255, 0.2);
  border: 1px solid #00d9ff;
}
.vehicleFeature-popup:after,
.vehicleFeature-popup:before {
  top: 100%;
  border: solid transparent;
  content: " ";
  height: 0;
  width: 0;
  position: absolute;
  pointer-events: none;
  opacity: 0.5;
}
.vehicleFeature-popup:after {
  border-top-color: #00d9ff;
  border-width: 10px;
  left: 48px;
  margin-left: -10px;
}
.vehicleFeature-popup:before {
  border-top-color: #00d9ff;
  border-width: 11px;
  left: 48px;
  margin-left: -11px;
}
.vehicleFeature-popup-closer:after {
  content: "✖";
}
.vehicleFeature-popup-closer {
  position: absolute;
  top: 2px;
  right: 2px;
  color: #00ffff;
}
.vehicleFeature-popup a:active {
  color: #00ffff !important;
}
.vehicleFeature-popup a:hover {
  color: #00ffff !important;
}
.vehicleTitle {
  color: #00d9ff;
  font-size: 16px;
  line-height: 30px;
}
// .spanText {
//   line-height: 30px;
//   span {
//     color: #00d9ff;
//     font-size: 14px;
//   }
// }
.image {
  width: 90%;
  height: 174px;
}
</style>
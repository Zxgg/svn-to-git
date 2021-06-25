<template>
  <div>
    <div ref="areapop"
         class="feature-areapop">
      <a ref="areapop-closer"
         class="feature-areapop-closer"
         @click="areaPopClose"></a>
      <el-row style="width:100%;">
        <el-col class="bayonepop_left">
          <el-row class="spanText"
                  style="margin-top:10px !important"><span>社区名称：</span><span v-if="areaFeature">{{areaFeature.getProperties().community_name}}</span></el-row>
          <el-row class="spanText"><span>街道：</span><span v-if="areaFeature">{{areaFeature.getProperties().street_name}}</span></el-row>
          <el-row class="spanText"><span>地址：</span><span v-if="areaFeature">{{areaFeature.getProperties().address}}</span></el-row>
          <!-- <el-row class="spanText">
            <span>卡口坐标：</span>
            <span v-if="areaFeature">{{areaFeature.getProperties().geometry.flatCoordinates[0].toFixed(2)}}</span>
            <span v-if="areaFeature">{{"，"}}</span>
            <span v-if="areaFeature">{{areaFeature.getProperties().geometry.flatCoordinates[1].toFixed(2)}}</span>
          </el-row> -->
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: 'areapop',
  props: ['areaFeature'],
  data () {
    return {

    }
  },
  mounted () {
    this.initAreaElement()
  },
  methods: {
    initAreaElement () {
      var element = this.$refs.areapop
      this.$emit('areaElement', element)
    },
    areaPopClose () {
      this.$emit('areaPopClose')
    }
  }
}
</script>

<style lang='scss' scoped>
.feature-areapop {
  position: absolute;
  background-color: #032941;
  padding: 15px;
  border-radius: 10px;
  border: 1px solid #005982;
  bottom: 12px;
  left: -50px;
  min-width: 300px;
  opacity: 0.7;
}
.feature-areapop:after,
.feature-areapop:before {
  top: 100%;
  border: solid transparent;
  content: " ";
  height: 0;
  width: 0;
  position: absolute;
  pointer-events: none;
}

.feature-areapop:after {
  border-top-color: #032941;
  border-width: 10px;
  left: 48px;
  margin-left: -10px;
}

.feature-areapop:before {
  border-top-color: #005982;
  border-width: 11px;
  left: 48px;
  margin-left: -11px;
}
.feature-areapop-closer:after {
  content: "✖";
}
.feature-areapop-closer {
  position: absolute;
  top: 2px;
  right: 5px;
  color: #0f86ae;
}
.feature-areapop a:active {
  color: #00ffff !important;
}
.feature-areapop a:hover {
  color: #00ffff !important;
}
.bayonepop_left span {
  color: #11bbf3;
  font-size: 14px;
}
.bayonepop_left div {
  margin-top: 10px;
}
.bayoneLocation_box {
  position: absolute;
  top: 75%;
  border: 1px solid #005982;
  border-radius: 4px;
  background: #032941;
  width: 16%;
  height: 23.5%;
}
</style>

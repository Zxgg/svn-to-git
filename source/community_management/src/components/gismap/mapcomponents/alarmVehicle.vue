<template>
  <div style="width:100%;height:100%">
    <div class="alarmVehicle_inside">
      <el-row style="width:100%;height:70%">
        <el-col style="width:80%;height:100%">
          <img :src="pic1"
               style="width:100%;height:100%" />
        </el-col>
        <el-col style="width:20%;height:100%">
          <el-row class="alarmVehicle_inside_row">
            <span class="icon iconfont spanBtn"
                  @click="editModelInfo()">&#xe6d7;</span>
          </el-row>
          <el-row class="alarmVehicle_inside_row"
                  style="margin-top:15px">
            <span class="icon iconfont spanBtn"
                  @click="editModelInfo()">&#xe6eb;</span>
          </el-row>
          <el-row class="alarmVehicle_inside_row"
                  style="margin-top:15px">
            <span class="icon iconfont spanBtn"
                  @click="editModelInfo()">&#xe60a;</span>
          </el-row>
        </el-col>
      </el-row>
      <el-row style="width:100%;height:15%">
        <label class="alarmVehicle_inside_label">{{"鲁N123G0"}}</label>
        <label class="alarmVehicle_inside_label"
               style="margin-left:10px">{{"张三"}}</label>
      </el-row>
      <el-row style="width:100%;height:15%">
        <label class="alarmVehicle_inside_label">{{"2020/08/02 13:01:22"}}</label>
      </el-row>
    </div>
    <div class="alarmVehicle_inside">
      <el-row style="width:100%;height:70%">
        <el-col style="width:80%;height:100%">
          <img :src="pic1"
               style="width:100%;height:100%" />
        </el-col>
        <el-col style="width:20%;height:100%">
          <el-row class="alarmVehicle_inside_row">
            <span class="icon iconfont spanBtn"
                  @click="editModelInfo()">&#xe6d7;</span>
          </el-row>
          <el-row class="alarmVehicle_inside_row"
                  style="margin-top:15px">
            <span class="icon iconfont spanBtn"
                  @click="editModelInfo()">&#xe6eb;</span>
          </el-row>
          <el-row class="alarmVehicle_inside_row"
                  style="margin-top:15px">
            <span class="icon iconfont spanBtn"
                  @click="editModelInfo()">&#xe60a;</span>
          </el-row>
        </el-col>
      </el-row>
      <el-row style="width:100%;height:15%">
        <label class="alarmVehicle_inside_label">{{"鲁N123G0"}}</label>
        <label class="alarmVehicle_inside_label"
               style="margin-left:10px">{{"张三"}}</label>
      </el-row>
      <el-row style="width:100%;height:15%">
        <label class="alarmVehicle_inside_label">{{"2020/08/02 13:01:22"}}</label>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      pic1: 'https://www.cn357.com/ud/ue/fg/image/20160930/water/1475220019967137.jpg',
      pic2: 'https://www.cn357.com/ud/ue/fg/image/20160930/water/1475220019967137.jpg'
    }
  },
  methods: {
    showBigImg () {

    },
    editModelInfo () {

    }
  }
}
</script>

<style lang="scss">
.alarmVehicle_inside {
  border: 1px solid #11bbf3;
  width: 90%;
  height: 45%;
  margin: 4.5%;
}
.alarmVehicle_inside_row span {
  color: #11bbf3;
  margin: 5px;
  font-size: 22px;
}
.alarmVehicle_inside_row span:hover {
  cursor: pointer;
}
.alarmVehicle_inside_label {
  color: #11bbf3;
  font-size: 12px;
  margin-left: 2px;
}
</style>

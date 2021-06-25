<template>
  <el-row style="width:100%;height:500px;border:1px dashed #005982;">
    <div class="MineCase_right_inside">
      <div class="MineCase_right_inside_top"><span>车辆基本信息</span></div>
      <div style="height:10px;"></div>
      <div class="MineCase_right_inside_bottom">
        <el-col style="width:100%;height:100%">
          <el-row style="height:30%">
            <label class="vehiclepop_span">车牌号：</label>
            <label class="vehiclepop_span1">{{111}}</label>
            <label class="vehiclepop_span2">准运危化品类型：</label>
            <label class="vehiclepop_span1">{{11}}</label>
          </el-row>
          <el-row style="height:30%">
            <label class="vehiclepop_span">车辆型号：</label>
            <label class="vehiclepop_span1">{{111}}</label>
            <label class="vehiclepop_span2">当前运输品类：</label>
            <label class="vehiclepop_span1">{{11}}</label>
            <label class="vehiclepop_span2">化工品来源：</label>
            <label class="vehiclepop_span1">{{11}}</label>
          </el-row>
          <el-row style="height:30%">
            <label class="vehiclepop_span">所属公司：</label>
            <label class="vehiclepop_span1">{{111}}</label>
            <label class="vehiclepop_span2">公司联系电话：</label>
            <label class="vehiclepop_span1">{{11}}</label>
            <el-button class="vehiclepop_button">拨打</el-button>
          </el-row>
        </el-col>
      </div>
    </div>
    <div class="MineCase_right_inside">
      <div class="MineCase_right_inside_top"><span>驾驶员信息</span></div>
      <div style="height:10px;"></div>
      <div class="MineCase_right_inside_bottom">
        <el-col style="width:100%;height:100%">
          <el-row style="height:24%">
            <label class="vehiclepop_span">姓名：</label>
            <label class="vehiclepop_span1">{{111}}</label>
            <label class="vehiclepop_span2">性别：</label>
            <label class="vehiclepop_span1">{{11}}</label>
            <label class="vehiclepop_span2">年龄：</label>
            <label class="vehiclepop_span1">{{11}}</label>
            <label class="vehiclepop_span2">身份证号码：</label>
            <label class="vehiclepop_span1">{{11}}</label>
          </el-row>
          <el-row style="height:24%">
            <label class="vehiclepop_span">家庭住址：</label>
            <label class="vehiclepop_span1">{{111}}</label>
          </el-row>
          <el-row style="height:24%">
            <label class="vehiclepop_span">驾驶证号码：</label>
            <label class="vehiclepop_span1">{{111111111111111111111}}</label>
            <label class="vehiclepop_span2">准驾车型：</label>
            <label class="vehiclepop_span1">{{11}}</label>
            <label class="vehiclepop_span2">发证机关：</label>
            <label class="vehiclepop_span1">{{11}}</label>
          </el-row>
          <el-row style="height:24%">
            <label class="vehiclepop_span">联系电话：</label>
            <label class="vehiclepop_span1">{{1111111111}}</label>
            <el-button class="vehiclepop_button">拨打</el-button>
            <el-button class="vehiclepop_button"
                       style="width:100px">下发消息</el-button>
          </el-row>
        </el-col>
      </div>
    </div>
    <el-col style="width:50%;height:10%">
      <h3 class="vehiclepop_h3">{{"车辆行驶正常"}}</h3>
    </el-col>
    <el-col style="width:50%;height:10%">
      <h3 class="vehiclepop_h3">{{"发送车辆信息给最近的安全员"}}</h3>
    </el-col>
  </el-row>
</template>

<script>
export default {

}
</script>

<style lang="scss">
.outer-container {
  width: 100%;
  height: 100%;
  position: relative;
  overflow: hidden;
}
.inner-container {
  position: absolute;
  left: 0;
  top: 0;
  right: -17px;
  bottom: 0;
  overflow-x: hidden;
  overflow-y: scroll;
}
.MineCase_right_inside_bottom {
  border: 1px solid #005982;
  width: 100%;
  height: 100%;
  margin-left: 1%;
}
.MineCase_right_inside {
  width: 93%;
  height: 40%;
  margin: 20px;
}
.MineCase_right_inside_top {
  // width: 120px;
  height: 20px;
  // background: #005982;
  position: absolute;
  // bottom: 10px;
  left: 40px;

  line-height: 20px;
  color: #11bbf3;
  span {
    margin-left: 0px;
    margin-right: 10px;
  }
}
.vehiclepop_span {
  float: left;
  margin-top: 20px;
  margin-left: 20px;
  color: #11bbf3;
  font-size: 14px;
}
.vehiclepop_span1 {
  float: left;
  margin-top: 22px;
  color: #11bbf3;
  font-size: 12px;
}
.vehiclepop_span2 {
  float: left;
  margin-left: 60px;
  margin-top: 20px;
  color: #11bbf3;
  font-size: 14px;
}
.vehiclepop_button {
  float: left;
  margin-top: 12px !important;
  margin-left: 70px !important;
  width: 60px;
}
.vehiclepop_h3 {
  float: left;
  color: #00ffff;
  margin-left: 15%;
  // margin-bottom: 20px;
}
</style>

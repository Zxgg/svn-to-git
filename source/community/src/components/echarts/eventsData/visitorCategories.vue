<template>
  <div class="content">
    <el-col :span="8">
      <div class="leirong bg2" @click="send('民生服务')">

        <span class="top">{{form.livelihoodServices}}件</span>
        <span class="bottom">民生服务</span>
      </div>
      <div class="leirong " @click="send('社会治安')">
        <span class="top">{{form.publicSecurity}}件</span>
        <span class="bottom">社会治安</span>
      </div>
    </el-col>
    <el-col :span="8">
      <div class="leirong bg2" @click="send('公共安全')">
        <span class="top">{{form.publicSafety}}件</span>
        <span class="bottom">公共安全</span>
      </div>
      <div class="leirong bg1" @click="send('城市管理')">
        <span class="top">{{form.cityManagement}}件</span>
        <span class="bottom">城市管理</span>
      </div>
      <div class="leirong" @click="send('环境保护')">
        <span class="top">{{form.environmentalProtection}}件</span>
        <span class="bottom">环境保护</span>
      </div>
    </el-col>
    <el-col :span="8">
      <div class="leirong bg2" @click="send('专项活动')">
        <span class="top">{{form.specialActivities}}件</span>
        <span class="bottom">专项活动</span>
      </div>
      <div class="leirong" @click="send('纠纷调解')">
        <span class="top">{{form.disputeMediation}}件</span>
        <span class="bottom">纠纷调解</span>
      </div>
    </el-col>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
import {getEventDistribution,getAnalysisData} from "../../../api/communityEvent/community_event.js"
import bus from "../../../assets/eventbus";
export default {
  data() {
   return{
     form:{},
      xData : [
      "社会治安",
      "纠纷调解",
      "纠纷调解",
      "专项活动",
      "公共安全",
      "环境保护",
      "城市管理",
      "民生服务"
    ]
   }
  },
  mounted(){
    this.getAnalysisData_()
    this.getEventDistribution_()
  },
  methods: {
    ...mapMutations(["CHANGE_SET"]),
    send(msg) {
      this.$Notice.$emit("get", msg);
    },
    async getAnalysisData_(){
      const {data} =  await getAnalysisData()
      this.form = data.data
      console.log(this.form,'-=-=-');
    },
 getEventDistribution_(msg){
     let query = {
        communityId:"0355931e-a70b-11eb-a21e-0050569ba051",
        eventType:msg
      }
      getEventDistribution(query).then(res=>{
      if(res.code===200){
       this.data=res.data
      }
     })
    }
  }
};
</script>
<style lang="scss" scoped>
.content {
  height: 100%;
  width: 100%;
}
.el-col {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.leirong {
  height: 34%;
  width: 100%;
  position: relative;
  color: aliceblue;
  background-image: url(../../../assets/images/events/事件分类bg-1.png);
  background-repeat: no-repeat;
  background-size: contain;
  background-position: 26%;
  margin-bottom: 20px;
  .top {
    height: 20px;
    position: absolute;
    top: 10px;
    left: 30%;
    font-size: 14px;
    font-weight: bold;
    color: #ffc6be;
  }
  .bottom {
    font-size: 12px;
    width: 100px;
    height: 20px;
    text-align: center;
    line-height: 20px;
    position: absolute;
    bottom: -20px;
    left: 0px;
    background: url(../../../assets/images/events/事件分类bg-4.png) no-repeat;
    color: #b2cbe1;
  cursor: pointer;

  }
}
.bg2 {
  background-image: url(../../../assets/images/events/事件分类bg-2.png);
  .top {
    color: #d8fbff;
  }
}
.bg1 {
  background-image: url(../../../assets/images/events/事件分类bg-3.png);
  .top {
    color: #ffc958;
  }
}
</style>

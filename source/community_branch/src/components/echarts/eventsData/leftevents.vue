<template>
  <div style="height:100%">
    <div class="eventList">
      <el-card v-for="item in eventthingsList" :key="item.id">
        <div class="etitle">
          <div class="eventtitle">
            <span class="beforeicon_first"></span>{{ item.eventName }}
          </div>
          <div class="eventstype">{{ item.eventType }}</div>
        </div>
        <div class="card">
          <div class="card-content">
            <div class="height card-title card-title-first">
              <span class="beforeicon_two"></span>
              {{ item.createTime | forData }}
            </div>
            <div class="height card-title card-title-two">
              <span class="beforeicon_three" @click.stop="send(item)"></span>
              {{ item.address }}
            </div>
            <div class="content">
              {{ item.content }}
            </div>
          </div>
          <div
            class="cardimg"
            @click.stop="getdialog(item.id)"
            v-if="item.alertFile"
          >
            <img :src="item.alertFile" class="image" />
          </div>
        </div>
      </el-card>
    </div>
    <div>
      <el-dialog
        class="guanaimotai"
        title="关爱人员提醒"
        :visible.sync="abnormaldailg"
        width="530px"
        :before-close="handleClose"
      >
        <el-form :model="myform" :inline="true" label-width="85px">
          <el-row class="demo-form-inline">
            <el-col :span="10">
              <div class="imgbox">
                <el-image
                  fit="fill"
                  :src="myform.alertFile"
                  v-show="myform.bigPicture"
                  :preview-src-list="[myform.bigPicture]"
                >
                </el-image>
                <el-image
                  fit="fill"
                  :src="myform.alertFile"
                  v-show="!myform.bigPicture"
                >
                </el-image>
              </div>
            </el-col>

            <el-col :span="4">
              <div class="contentbox">
                <div>{{ myform.confidence }}%</div>
                <div>相似度</div>
              </div>
            </el-col>

            <el-col :span="10">
              <div class="imgbox">
                <el-image
                  :src="myform.fileId"
                  fit="fill"
                  :preview-src-list="[myform.fileId]"
                >
                </el-image>
              </div>
            </el-col>

            <el-col :span="24">
              <el-form-item label="时间:">
                <div class="formcontent">
                  {{ myform.createTime || "未知" }}
                </div>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="地址:">
                <div class="formcontent">
                  {{ myform.address || "未知" }}
                </div>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item class="" label="描述:">
                <div class="formcontent">
                  {{ myform.content || "未知" }}
                </div>
              </el-form-item>
            </el-col>
            <el-col :span="8" v-for="item in chulilist" :key="item.chulistate">
              <div class="btn" @click.stop="chuli(item.chulistate)">
                {{ item.chuli }}
              </div>
            </el-col>
          </el-row>
        </el-form>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import $ from "jquery";
import resize from "../mixins/resize";
import { mapGetters, mapState } from "vuex";
import {
  baseEventInfoList,
  shouliEvents,
  pushshangbao,
  ajaxFuntionup
} from "@/api/communityEvent/community_event";
// import {  getInfo } from "@/api/login.js";
import bus from "@/assets/eventbus";
import configInfo from "@/webconfig/configInfo";
import Cookies from "js-cookie";
import moment from "moment";
export default {
  props: {
    currentComunity: {
      communityId: String,
      communityChildId: String
    }
  },
  data() {
    return {
      abnormaldailg: false,
      eventList: null,
      eventthingsList: [],
      list: {},
      myform: {},
      mw: null,
      username: null,
      chulilist: [
        {
          chulistate: "1",
          chuli: "受理"
        },
        {
          chulistate: "2",
          chuli: "上报"
        },
        {
          chulistate: "0",
          chuli: "误报"
        }
      ]
    };
  },

  computed: {},
  created() {},
  mounted() {
    // this.Getinfo_();
    this.getEventList();
    this.$Notice.$on("getEventslist", data => {
      this.getEventList();
    });
  },
  watch: {
    eventthingsList: {
      handler(newVal) {
        this.eventthingsList = newVal;
      },
      deep: true
    }
  },
  filters: {
    forData(str) {
      return moment(str).format("YYYY-MM-DD HH:MM:SS");
    }
  },
  methods: {
    handleClose(done) {
      done();
    },
    // 发送给地图数据
    send(msg) {
      this.$Notice.$emit("sendaddress", msg);
    },
    getEventList(n) {
      let param = {
        dealWithType: "1",
        communityId: "0355931e-a70b-11eb-a21e-0050569ba051",
        eventStatus: "0"
      };
      baseEventInfoList(param).then(response => {
        if (response.code === 200) {
          this.eventthingsList = response.rows;
          if (n) {
            bus.$emit("get2", n);
          }
        }
        this.eventthingsList.forEach(val => {
          if (val.alertFile) {
            if (val.alertFile.indexOf(configInfo.imgEchoAddress) === -1) {
              val.alertFile = configInfo.imgEchoAddress + val.alertFile;
            }
          }
          if (val.bigPicture) {
            if (val.bigPicture.indexOf(configInfo.imgEchoAddress) === -1) {
              val.bigPicture = configInfo.imgEchoAddress + val.bigPicture;
            }
          }
          if (val.fileId) {
            if (val.fileId.indexOf(configInfo.imgEchoAddress) === -1) {
              val.fileId = configInfo.imgEchoAddress + val.fileId;
            }
          }
        });
      });
    },
    getdialog(id) {
      this.abnormaldailg = true;
      let alist = [];
      alist = this.eventthingsList.filter(val => {
        return val.id === id;
      });
      console.log(id);
      this.list = alist[0];
      this.myform = alist[0];
      this.myform.confidence = Number(this.myform.confidence).toFixed(2);
    },
    // Getinfo_(){
    //   getInfo().then(res=>{
    //     this.username=res.user.nickName
    //   })
    // },
    // 受理的接口
    getEvent(eventStatus) {
      let data = {
        handleBy: "文昌花园社区管理员",
        // handleBy: this.username,
        eventStatus: eventStatus,
        id: this.myform.id
      };
      shouliEvents(data).then(response => {
        if (response.code === 200) {
          this.getEventList("1");
          this.$message({
            message: "操作成功！！",
            type: "success"
          });
        }
      });
    },
    //点击上传的接口
    pushshangbao_(info) {
      let data = info;
      ajaxFuntionup(data).then(response => {
        if (response.code === 200) {
          this.getEventList();
          this.$message({
            message: "操作成功！！",
            type: "success"
          });
        }
      });
    },
    // pushshangbao_ (info) {
    //   let data = JSON.stringify(info);
    //   console.log(data);
    //   $.ajax({
    //     url: configInfo.DBRestAPIEndPointUri + "/alert/pushToOneFour",
    //     type: "POST",
    //     headers: {
    //       "Content-Type": "application/json"
    //     },
    //     dataType: "json",
    //     data: data,
    //     success: function (result) {
    //       if (result.code === 200) {
    //         this.getEventList();
    //         this.$message({
    //           message: "操作成功！！",
    //           type: "success"
    //         });
    //       }
    //     },
    //     error: function (xhr, status, p3, p4) {
    //       var err = "Error " + " " + status + " " + p3;
    //       if (xhr.responseText && xhr.responseText[0] == "{")
    //         err = JSON.parse(xhr.responseText).message;
    //       alert(err);
    //     }
    //   });
    // },
    chuli(chulistate) {
      let list = this.myform;
      console.log(this.myform, 2121);
      if (chulistate === "0") {
        this.getEvent("-1");
        this.abnormaldailg = false;
        return;
      }
      list.chulistate = chulistate;
      if (chulistate === "1") {
        this.getEvent("1");
      }
      if (chulistate === "2") {
        this.pushshangbao_(this.myform);
        this.getEvent("9");
      }
      this.abnormaldailg = false;
    },
    getCookie() {
      this.username = Cookies.get("username");
    }
  }
};
</script>
<style lang="scss" scoped>
/deep/.el-card__body {
  height: calc(100% - 12.5px);
  padding: 0;
}
.height {
  height: 18%;
  line-height: 18px;
}
.el-card.is-always-shadow {
  -webkit-box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0);
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0);
}
.beforeicon_first {
  width: 20px;
  height: 15px;
  display: inline-block;
  margin: 0px 0 0 -28px;
  background: url(../../../assets/images/events/icon1.png) no-repeat;
  background-size: 96% 130%;
  background-position: 0 0;
}
.beforeicon_two {
  width: 20px;
  height: 15px;
  display: inline-block;
  margin: 0px 0 0 -28px;
  background: url(../../../assets/images/events/icon3.png) no-repeat;
  background-size: 123% 141%;
  background-position: 0 0;
}
.beforeicon_three {
  width: 20px;
  height: 15px;
  display: inline-block;
  margin: 0px 0 0 -28px;
  background: url(../../../assets/images/events/icon2.png) no-repeat;
  background-size: 123% 141%;
  background-position: 0 0;
  cursor: pointer;
}
.card-title-first::before {
  margin: -5px 0 0 -32px;
}
.card-title-two::before {
  margin: -5px 0 0 -32px;
}
.eventList {
  width: 100%;
  height: 100%;
  letter-spacing: 1px;
  overflow: auto;
  .el-card {
    width: 100%;
    height: 48%;
    margin-bottom: 2%;
    border: none;
    font-size: 12px;
    background-color: transparent;
    border-radius: 0;
    padding-right: 20px;
    box-sizing: border-box;
  }
  .etitle {
    font-size: 14px;
    font-family: Source Han Sans CN;
    font-weight: 400;
    color: #fff;
    background: no-repeat url(../../../assets/images/events/事件告警bg-1.png);
    background-size: 100% 100%;
    padding-left: 40px;
    display: flex;
    justify-content: space-between;
    .eventtitle {
      height: 100%;
      width: 100%;
      line-height: 20px;
    }
    .eventstype {
      height: 20px;
      width: 80px;
      background: no-repeat
        url(../../../assets/images/events/社会治安bnt-bg.png);
      background-size: 100% 100%;
    }
  }
}

.card {
  height: calc(100% - 11.5px);
  display: flex;
  justify-content: space-between;
  margin-top: 3px;
  padding-left: 36px;
  font-size: 14px;
  font-family: Source Han Sans CN;
  font-weight: 400;
  color: #fff;
  line-height: 20px;
}
.cardimg {
  box-sizing: border-box;
  flex: 0 0 30%;
  height: 80%;
  padding: 4px;
  margin-right: 2%;
  box-sizing: border-box;
  background: #000;
  background: no-repeat url(../../../assets/images/events/事件告警bg-1.png);
  background-size: 100% 100%;
}
.cardimg img {
  padding: 1%;
  height: 98%;
  width: 98%;
  border-image: linear-gradient(180deg, #62b5fc) 1 1;
  cursor: pointer;
}
.card-content {
  flex: 1;
  .content {
    overflow: hidden;
  }
}
.guanaimotai {
  .imgbox {
    width: 188px;
    height: 217px;
    background: url("../../../assets/images/events/照片.png") no-repeat;
    background-size: 100% 100%;
    padding: 2px;
    .el-image {
      height: 100%;
      width: 100%;
      /deep/ .img {
        width: 100%;
        height: 100%;
      }
    }
  }
  /deep/.el-form-item__content {
    width: calc(100% - 100px);
  }
  .contentbox {
    height: 188px;
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    > div:first-of-type {
      width: 100%;
      font-size: 25px;
      font-family: Impact;
      font-weight: 400;
      color: #e2eff9;
      margin-bottom: 32px;
    }
    > div:last-of-type {
      width: 42px;
      font-size: 14px;
      font-family: Source Han Sans CN;
      font-weight: 500;
      color: #e2eff9;
    }
  }
  .demo-form-inline {
    padding-left: 1%;
  }
  /deep/.el-dialog {
    border: 0px solid #005982;
    background-color: transparent;

    .el-dialog__header {
      // background-color: transparent;
      // padding: 0;
      height: 100%;
      background: url("../../../assets/images/events/弹框背景1.png") no-repeat;
      background-size: 100% 100%;
    }
    .el-dialog__body {
      // padding: 0;
      background: url("../../../assets/images/events/弹框背景2.png") no-repeat;
      background-size: 117% 117%;
      background-position: 54% 52%;
      color: #fff;
    }
  }
  /deep/.el-form-item {
    width: 100%;
    .el-form-item__label {
      color: #fff;
    }
  }
  .btn {
    color: #fff;
    width: 110px;
    height: 33px;
    line-height: 33px;
    text-align: center;
    font-size: 20px;
    background: url("../../../assets/images/events/bnt.png") no-repeat;
    background-size: 100% 100%;
    &:hover {
      background: url("../../../assets/images/events/bnt-on.png") no-repeat;
      background-size: 100% 100%;
    }
  }
}
</style>

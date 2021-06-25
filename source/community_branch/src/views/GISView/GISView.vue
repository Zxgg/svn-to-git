<template>
  <div id="mapBox">
    <div class="centerView"
         v-if="eventOpen">
      <div class="jilu_title">
        <div>社区事件列表</div>
        <div @click="closeEventList"
             class="center_span">
          关闭
        </div>
      </div>
      <div style="height: 300px;overflow-y: auto;">
        <div class="tr_first">
          <!-- <div class="centerTd">小区名称</div> -->
          <div class="centerTd">事件名称</div>
          <div class="centerTd">事件状态</div>
          <div class="centerTd">告警时间</div>
          <!-- <div class="centerTd">操作</div> -->
        </div>
        <div class="tr_list"
             v-for="(item, index) in tableData">
          <!-- <div class="centerTd">{{getCommunityChildName(item.communityId)}}</div> -->
          <div class="centerTd">{{item.eventName}}</div>
          <div class="centerTd">{{item.eventStatus}}</div>
          <div class="centerTd">{{item.createTime}}</div>
          <!-- <div class="centerTd">
            <span @click="handleEvents_(item.id,'1',index)"
                  class="center_span">上报1+4平台</span> |
            <span @click="handleEvents_(item.id,'2',index)"
                  class="center_span">自办结</span>
          </div> -->
          <!-- <div class="tr_border"></div> -->
          </tr>
        </div>
      </div>
    </div>
    <div id="map"
         ref="rootMap">
    </div>
    <div class="toolbar">
      <!-- <div class="toolbar_firstdiv">
        <el-dropdown class="select_layer"
                     trigger="click">
          <el-button type="primary"
                     title="标绘"
                     class="icon iconfont">&#xe791;</el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="drawMap('Point')">点</el-dropdown-item>
            <el-dropdown-item @click.native="drawMap('Rectangle')">框选</el-dropdown-item>
            <el-dropdown-item @click.native="drawMap('Circle')">圈选</el-dropdown-item>
            <el-dropdown-item @click.native="drawMap('Polygon')">多边形</el-dropdown-item>
            <el-dropdown-item @click.native="drawMap('LineString')">自由线</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <el-dropdown class="select_layer"
                     trigger="click">
          <el-button type="primary"
                     title="测量工具"
                     class="icon iconfont">&#xe600;</el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="measure('LineString')">测距</el-dropdown-item>
            <el-dropdown-item @click.native="measure('Polygon')">测面</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <el-button type="primary"
                   class="icon iconfont toolbar_button_clear"
                   title="清除"
                   @click="ClearVector">&#xe6a2;</el-button>
      </div> -->
      <el-button class="icon iconfont deviceIcon"
                 @click="deviceHandle"
                 title="监控设备">&#xe6fc;</el-button>
      <div class="deviceIconName"
           @click="deviceHandle">{{'监控设备'}}</div>
      <el-button class="icon iconfont gridmemberIcon"
                 @click="gridMemberHandle"
                 title="网格员">&#xe62b;</el-button>
      <div class="deviceIconName"
           @click="gridMemberHandle">{{'网格员'}}</div>
      <!-- <div class="toolbar_lastdiv toolbar_lastdivTemp">
        <div class="icon iconfont deviceIcon"
             @click="deviceHandle"
             title="设备">&#xe6fc;</div>
        <div class="icon iconfont alarmIcon"
             @click="alarmHandle"
             title="告警">&#xe745;</div>
        <div class="icon iconfont faceIcon"
             @click="faceHandle"
             title="人脸">&#xe6fd;</div>
        <div class="icon iconfont doorIcon"
             @click="doorHandle"
             title="门禁">&#xe6fe;</div>
        <div class="icon iconfont carIcon"
             @click="vehicleHandle"
             title="车辆">&#xe62c;</div>
      </div> -->
    </div>
    <div class="dataReturn"
         v-if="isReturn">
      <span class="icon iconfont"
            @click="dataReturn">&#xe66b; 返回</span>
    </div>
    <!-- <div class="alarmEvent"
         v-show="eventshow"
         @click="showEventList">
      <span>{{eventNum}}</span>
      <span>起</span>
      <span>/</span>
      <span>{{allNum}}</span>
      <span>起</span>
      <div>
        <span class="icon iconfont">&#xe745;</span>
        <span>社区事件</span>
      </div>
    </div> -->

    <div class="alarmEventNew"
         v-show="eventshow">
      <!-- <div class="alarmNumberTitle">社区事件数（件）</div>

      <div class="alarmNumberDiv">
        <div class="alarmNumber"
             v-for="(number,index) in allAlarmNumber"
             :key="index">
          <div class="alarmNumberAloneBG">
            <span class="alarmNumberAlone">{{number}}</span>
          </div>
        </div>
      </div> -->

    </div>
    <el-dialog :visible.sync="gridMemberOpen"
               width="45%"
               append-to-body
               :show-close="false"
               class="dialogPeopleInfoClass">
      <div class="dialog_title">
        <el-col :span="23">{{gridMemberform.grid}}</el-col>
        <el-col :span="1"><span @click="gridMemberOpen = false">{{"×"}}</span></el-col>
      </div>
      <div style="width:100%;height:100%">
        <el-col :span="7">
          <div class="dialogPeopleInfoClass_pic">
            <img style="width:94%;height:92%;margin:3%"
                 :src="gridMemberform.pic">
          </div>
        </el-col>
        <el-col :span="16">
          <div class="dialogPeopleInfoClass_info">
            <el-form ref="form"
                     :model="gridMemberform" label-width="100px">
              <el-form-item label="所属网格:"
                            style=" padding-top: 15px;">
                <span class="addr">{{ gridMemberform.grid || '暂无'}}</span>
              </el-form-item>
              <el-form-item label="网格员:">
                <span class="addr">{{ gridMemberform.gridName || '暂无'}}</span>
              </el-form-item>
              <el-form-item label="网格范围:">
                <span class="addr">{{ gridMemberform.gridBound || '暂无'}}</span>
              </el-form-item>
              <el-form-item label="联系方式:">
                <span class="addr">{{ gridMemberform.phoneNumber || '暂无'}}</span>
              </el-form-item>
              <el-form-item label="户数:">
                <span class="addr">{{ gridMemberform.households || '暂无'}}</span>
              </el-form-item>
              <el-form-item label="常驻人员:">
                <span class="addr">{{ gridMemberform.permanent_people || '暂无'}}</span>
              </el-form-item>
              <el-form-item label="党员:">
                <span class="addr">{{ gridMemberform.partymember || '暂无' }}</span>
              </el-form-item>
              <el-form-item label="面积:">
                <span class="addr">{{ gridMemberform.gridArea || '暂无'}}</span>
              </el-form-item>
            </el-form>
          </div>
        </el-col>
      </div>
    </el-dialog>
    <areapop v-show="false"
             :areaFeature="areaFeature"
             @areaElement="AreaElement"
             @areaPopClose="areaPopClose" />
    <featurepop v-show="false"
                :feature="layerFeature"
                @featureElement="FeatureElement"
                @popClose="popClose" />
    <vehicletrack v-show="false"
                  :vehicleFeature="vehicleFeature"
                  @vehicleElement="VehicleElement"
                  @vehicleClose="vehicleClose" />
    <abnormalEvent v-show="false"
                   :abnormalFeature="abnormalFeature"
                   @abnormalElement="AbnormalElement"
                   @abnormalClose="abnormalClose" />
    <el-dialog :visible.sync="dialogVideoInfoVisible"
               width="840px"
               append-to-body
               :show-close="false"
               class="dialogCarClassS">
      <div class="dialog_title">
        <el-col :span="23">视频播放</el-col>
        <el-col :span="1"><span @click="dialogVideoInfoVisible = false">{{"×"}}</span></el-col>
      </div>
      <div class="dialogFeatureVideo">
        <div class="dialogPlayVideo">
          <videoPlay ref="videoPlayer"
                     style="height:100%;width:100%"></videoPlay>
        </div>
      </div>

    </el-dialog>
    <!-- <el-dialog title="社区事件列表"
               :visible.sync="eventOpen"
               width="640px"
               :before-close="handleClose"
               :append-to-body="true"> -->

    <!-- </el-dialog> -->
    <topgrid v-show="!eventshow" />

  </div>
</template>
<script>
import "ol/ol.css";
import "@/assets/styles/main/gismap.scss";
import $ from "jquery";
import initMap from "@/components/gismap/mapfunction/initMap";
import initArea from "@/components/gismap/mapfunction/areaVector";
import getData from "@/components/gismap/mapfunction/getData";
import cluster from "@/components/gismap/mapfunction/cluster";
import location from "@/components/gismap/mapfunction/location";
import track from "@/components/gismap/mapfunction/trackAnalysis";
import drawMap from "@/components/gismap/mapfunction/draw";
import measure from "@/components/gismap/mapfunction/measure";
import mapOverlay from "@/components/gismap/mapfunction/mapOverlay";
import areapop from "@/components/gismap/mapcomponents/areapop";
import featurepop from "@/components/gismap/mapcomponents/featurepop";
import vehicletrack from "@/components/gismap/mapcomponents/vehicletrack";
import abnormalEvent from "@/components/gismap/mapcomponents/abnormal";
import { areaVectorStyle } from "@/components/gismap/mapfunction/vectorStyle";
import videoPlay from "@/components/ckplayer/vuevideo";
import {
  listCommunity_event,
  getCommunity_event,
  baseEventInfoList,
  handleEvents,
} from "@/api/communityEvent/community_event";
import { getEventNumNew } from "@/api/homePage/eventNumStatistics";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import { mapGetters, mapState } from "vuex";
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import configInfo from "@/webconfig/configInfo";
import moment from "moment";

//顶部数字
import topgrid from "@/components/events/components/topgrid";
export default {
  data() {
    return {
      areaFeature: null,
      layerFeature: null,
      vehicleFeature: null,
      deviceShow: true,
      alarmShow: true,
      doorShow: true,
      faceShow: true,
      gridMemberShow: true,
      vehicleShow: true,
      dialogVideoInfoVisible: false,
      eventshow: true,
      abnormalElement: null,
      abnormalFeatures: null,
      abnormalFeature: null,
      community_eventList: null,
      eventNum: 0,
      allNum: 0,
      allAlarmNumber: [],
      eventOpen: false,
      tableData: [],
      communityChildList: [],
      stompClient: "",
      timer: "",
      isReturn: false,
      activeroute: this.$route.path,
      gridMemberOpen: false,
      gridMemberform: {},
    };
  },
  created() {
    this.getCommunityChildList();
    this.getEventNumNew();
    // this.getEventList();
  },
  components: {
    areapop,
    featurepop,
    vehicletrack,
    abnormalEvent,
    videoPlay,
    topgrid,
  },
  mixins: [
    initMap,
    initArea,
    cluster,
    getData,
    location,
    track,
    drawMap,
    measure,
    mapOverlay,
  ],
  filters: {},
  computed: {
    ...mapGetters(["currentComunity"]),
  },
  watch: {
    $route: {
      handler(val, oldval) {
        if (val.name === "events") {
          this.eventshow = false;
        } else {
          this.eventshow = true;
        }
      },
      // 深度观察监听
      deep: true,
    },

    currentComunity: {
      handler(val, data) {
        this.refreshData();
        // console.log(val.communityId, 'val')
      },
      deep: true,
    },
  },
  methods: {
    dataReturn() {
      this.areaLayer1.getSource().clear();
      this.isReturn = false;
      this.$Notice.$emit("communityGridReturn", "");
    },
    deviceLocation(val) {
      // console.log(val, 'val')
      let feature = this.cameraSource
        .getFeatures()
        .find((p) => p.getProperties().cameraName === val.address);
      this.location(feature);
      let starTime_T = moment(val.reportingTime)
        .subtract(15, "seconds")
        .format("YYYY-MM-DDTHH:mm:ss");
      let endTime_T = moment(val.reportingTime)
        .add(15, "seconds")
        .format("YYYY-MM-DDTHH:mm:ss");
      // console.log(starTime_T, 'starTime_T===;;;', endTime_T, 'endTime_T;;;;;;;;;', feature.getProperties().cameraSn, 'feature.getProperties().cameraSn')
      // setTimeout(() => {
      //   this.playVideo(feature.getProperties().cameraSn)
      // }, 1500);
      // this.playVideoHistoryFuction(feature.getProperties().cameraSn, starTime_T, endTime_T)
      // moment(val.reportingTime).subtract(15, 'seconds').format('YYYY-MM-DDTHH:mm:ss')
    },
    async playVideoHistoryFuction(cameraSN, startTime, endTime) {
      let hls = await this.commonFunction.getHlsHistoryStreamByCameraCodeForContorl(
        cameraSN,
        startTime,
        endTime
      );
      this.$refs.videoPlayer.playVideo(hls);
    },
    deviceHandle() {
      this.deviceShow = !this.deviceShow;
      if (this.deviceShow) {
        this.map.addLayer(this.cameraLayer);
      } else {
        this.map.removeLayer(this.cameraLayer);
      }
    },
    gridMemberHandle() {
      this.gridMemberShow = !this.gridMemberShow;
      if (this.gridMemberShow) {
        this.map.addLayer(this.gridMemberLayer);
      } else {
        this.map.removeLayer(this.gridMemberLayer);
      }
    },
    alarmHandle() {
      this.alarmShow = !this.alarmShow;
      if (this.alarmShow) {
        this.map.addLayer(this.alarmLayer);
      } else {
        this.map.removeLayer(this.alarmLayer);
      }
    },
    faceHandle() {
      this.faceShow = !this.faceShow;
      if (this.faceShow) {
        this.map.addLayer(this.faceLayer);
      } else {
        this.map.removeLayer(this.faceLayer);
      }
    },
    doorHandle() {
      this.doorShow = !this.doorShow;
      if (this.doorShow) {
        this.map.addLayer(this.doorLayer);
      } else {
        this.map.removeLayer(this.doorLayer);
      }
    },
    vehicleHandle() {
      this.vehicleShow = !this.vehicleShow;
      if (this.vehicleShow) {
        this.map.addLayer(this.vehicleLayer);
      } else {
        this.map.removeLayer(this.vehicleLayer);
      }
    },
    AreaElement(element) {
      this.areaElement = element;
    },
    FeatureElement(element) {
      this.featureElement = element;
    },
    VehicleElement(element) {
      this.vehicleElement = element;
    },
    AbnormalElement(element) {
      this.abnormalElement = element;
    },
    areaPopClose() {
      if (this.highFeature) {
        this.highFeature.setStyle(areaVectorStyle);
      }
      this.map.removeOverlay(this.areaPopup);
    },
    popClose() {
      this.map.removeOverlay(this.popup);
    },
    vehicleClose() {
      this.map.removeOverlay(this.vehiclePopup);
    },
    abnormalClose() {
      this.map.removeOverlay(this.overlay);
    },
    ClearVector() {
      const me = this;
      me.drawsource.clear(); // 清除标绘
      me.measuresource.clear(); // 清除测量
      me.map.getOverlays().clear(); // 清除覆盖层
      me.map.removeLayer(this.tr_layer);
      me.map.removeLayer(this.lc_layer);
    },
    vehicleLocation(data) {
      const me = this;
      me.vehicleFeature = data;
      me.location(data);
    },
    vehicleTrack(vehicleList) {
      this.track(vehicleList);
    },
    playVideo(cameraSN) {
      this.dialogVideoInfoVisible = true;
      this.playVideoFuction(cameraSN);
    },
    async playVideoFuction(cameraSN) {
      let hls = await this.commonFunction.getHlsStreamByCameraCode(cameraSN);
      if(hls){
        this.$refs.videoPlayer.playVideo(hls);
      }
    },
    handleClose(done) {
      done();
    },
    getEventNumNew() {
      getEventNumNew().then((response) => {
        if (response && response.data) {
          this.allAlarmNumber = [];
          this.eventNum = response.data.typeDclNum;
          let strNum = response.data.allNum + "";
          let alarmNumber = response.data.allNum;
          for (let i = 0; i < strNum.length; i++) {
            let number = alarmNumber % 10; //除以10取余数就是最后一位
            this.allAlarmNumber.push(number);
            alarmNumber = parseInt(alarmNumber / 10);
          }

          this.allAlarmNumber = this.allAlarmNumber.reverse();

          // this.allAlarmNumber
          // let aaa = strNum.toCharArray();
        }
      });
    },
    eventLocation(data) {
      // console.log(data, 'data;====data')
      let feature = this.cameraSource
        .getFeatures()
        .find((p) => p.getProperties().cameraName === data);
      this.location(feature);
    },
    refreshData() {
      this.community_eventList = [];
      let queryParams = {
        pageNum: 1,
        pageSize: 10,
        type: null,
        processed: "0",
        communityId: this.currentComunity.communityId,
        communityChildId: this.currentComunity.communityChildId,
      };
      listCommunity_event(queryParams).then((response) => {
        // console.log(response, 'res')
        if (response && response.rows.length > 0) {
          this.community_eventList = response.rows;
          this.eventNum = this.community_eventList.length;
          // this.getAlarm()
        }
      });
    },
    showEventList() {
      this.eventOpen = !this.eventOpen;
      this.getEventNumNew();
      this.getEventList();
    },
    closeEventList() {
      this.eventOpen = false;
    },
    //查询社区事件列表
    getEventList() {
      // debugger;
      let param = {
        dealWithType: "0",
        communityId: this.currentComunity.communityId,
      };
      baseEventInfoList(param).then((response) => {
        if (response && response.rows.length > 0) {
          console.log("response", response);
          this.tableData = response.rows;
          this.eventNum = response.total;
        }
      });
    },
    //根据社区id查询小区列表
    getCommunityChildList() {
      let param = {
        communityId: this.currentComunity.communityId,
      };
      listCommunity_child(param).then((response) => {
        if (response && response.rows.length > 0) {
          this.communityChildList = response.rows;
        }
      });
    },
    getCommunityChildName(community_child) {
      // debugger;
      // let communityChildName = "";
      let arr = this.communityChildList.filter((communityChild) => {
        return community_child == communityChild.id;
      });
      return arr[0].communityChildName;
    },
    handleEvents_(id, type, index) {
      let data = {
        id: id,
        dealWithType: type,
      };
      handleEvents(data).then((response) => {
        // debugger;
        // console.log(data);

        if (response && response.code == "200") {
          this.$message({
            message: "操作成功！！",
            type: "success",
          });
          this.tableData.splice(index, 1);
          this.getEventList();
          this.getEventNumNew();
          // this.communityChildList = response.rows;
        }
      });
    },
    initWebSocket() {
      this.connection();
      let that = this;
      // 断开重连机制,尝试发送消息,捕获异常发生时重连
      this.timer = setInterval(() => {
        try {
          that.stompClient.send("test");
        } catch (err) {
          console.log("断线了: " + err);
          that.connection();
        }
      }, 5000);
    },
    connection() {
      // debugger;
      // 建立连接对象
      let socket = new SockJS(configInfo.WebSocketUrl + "/endpoint-websocket");
      // 获取STOMP子协议的客户端对象
      this.stompClient = Stomp.over(socket);
      // 定义客户端的认证信息,按需求配置
      let headers = {
        Authorization: "",
      };
      // 向服务器发起websocket连接
      this.stompClient.connect(
        headers,
        () => {
          // debugger;
          console.log("开始订阅 /topic/test");
          this.stompClient.subscribe(
            "/topic/test",
            (msg) => {
              // 订阅服务端提供的某个topic
              // console.log(msg, 'msg===gss===');
              this.$Notice.$emit("getEventslist", "re");
              this.getEventNumNew();
              let data = JSON.parse(msg.body);
              let body = JSON.parse(data.content);
              // console.log(body, 'data========================================data')
              this.eventLocation(body.address);
              // this.getEventList();
              // this.eventNum++;
              // console.log('收到广播======index===', JSON.parse(JSON.parse(msg.body).content)); // msg.body存放的是服务端发送给我们的信息
              // let bodyData = JSON.parse(JSON.parse(msg.body).content);
              // // this.infoList.peopleName = bodyData.peopleName;
              // // this.infoList.phone = bodyData.people_info.phone;
              // // this.infoList.cameraName = bodyData.track_info.cameraName;
              // //   this.infoList.cameraLocationName =
              // //     bodyData.camera_info.cameraLocationName;
              // //   this.infoList.visitTime = bodyData.track_info.visitTime;
              // this.infoList.time = bodyData.visitTime;
              // this.infoList.avatarSrc = bodyData.imageId;
              // //   this.infoList.trackSrc = configInfo.PicShowURL + bodyData.track_info.imageId;
              // var self = this;
              // if (this.headImgArrActive.length <= 10) {
              //   self.headImgArrActive.push(this.infoList.avatarSrc);
              // } else {
              //   self.headImgArrActive.splice(0, 1);
              //   self.headImgArrActive.push(this.infoList.avatarSrc);
              // }
            }
            // headers
          );
          // this.stompClient.send()  //用户加入接口

          // 发送改变
          this.stompClient.subscribe("/topic/main/eventStatusChange", (msg) => {
            // 订阅服务端提供的某个topic
            // debugger
            this.$Notice.$emit("getEventschange", msg);
          });
        },
        (err) => {
          console.log("失败");
          console.log(err);
        }
      );
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
    }, // 断开连接
  },
  mounted() {
    this.initWebSocket();
    this.refreshData();
    this.$Notice.$on("VehicleTrack", (vehicleList) =>
      this.vehicleTrack(vehicleList)
    );
    this.$Notice.$on("VehicleLocation", (data) => this.vehicleLocation(data));
    this.$Notice.$on("sendaddress", (val) => {
      this.deviceLocation(val);
    });
    this.$Notice.$on("communityGridCannel", (val) => {
      this.isReturn = false;
      this.areaLayer1.getSource().clear();
      if (this.$route.name === "peopleInfo")
        this.$Notice.$emit("communityGridReturn", "");
    });
    if (this.$route.name === "events") this.eventshow = false;
  },
};
</script>


<style>
.dialogCarClassS .el-dialog {
  background-image: url("../../assets/images/playVideo/bg.png");
  background-repeat: no-repeat;
  background-color: transparent;
  background-size: 840px 530px;
  height: 530px;
  border: 0;
}
.dialogCarClassS .el-dialog__body {
  font-size: 14px;
  font-family: Source Han Sans CN;
  font-weight: 400;
  color: #fff;
  padding: 10px 45px;
  height: 550px;
}
.dialogCarClassS .el-dialog__header {
  /* background-color:rgba(34, 39, 81, 1); */
  padding: 6px 75px;
  background-color: transparent;
  border: 0;
}
.dialogCarClassS .el-dialog__title {
  color: #e2eff9;
}
.dialogCarClassS .el-dialog__headerbtn {
  top: 9px;
  right: 66px;
}
.dialogCarClassS .el-dialog__headerbtn .el-dialog__close {
  color: #e2eff9;
}
.dialogCarClassS .el-dialog:not(.is-fullscreen) {
  margin-top: 8% !important;
}
.dialogCarClassS .el-divider {
  background-color: #fff !important;
}
</style>
<style scoped="true">
/* @import "~@/assets/styles/main/carInfo.css"; */

.dialogPlayVideo {
  width: 107.8%;
  height: 460px;
  margin-left: -30px;
}
#dialogFeatureVideo {
  width: 100%;
  height: 600px;
}
.centerView {
  /* background-color: #ffffff; */
  z-index: 100;
  margin: 14px 12px;
  padding-bottom: 24px;
  /* border-radius: 10px; */
  position: absolute;
  width: 884px;
  height: 341px;
  border: 1px solid #36a9ff;
  top: 30%;
  left: 26%;
  width: 45%;
  /* background-color: rgba(54, 169, 255, 0.4); */
  /* opacity: 0.6; */
  /* background-image: linear-gradient(
    rgba(54, 169, 255, 0.6),
    rgba(0, 0, 0, 0.3)
  ); */
  background-color: #193961;
}

.jilu_title {
  height: 38px;
  line-height: 38px;
  font-size: 14px;
  font-weight: 500;
  color: #ffffff;
  text-align: left;
  border-bottom: 1px solid #36a9ff;
  display: flex;
  flex-direction: row;
  padding: 0 24px;
  justify-content: space-between;
}

.tr_first {
  background-color: #01d2f6;
  font-size: 12px;
  font-weight: 400;
  color: #041932;
  line-height: 28px;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
  -ms-flex-direction: row;
  justify-content: space-between;
  flex-direction: row;
  padding: 0 24px;
  height: 28px;
}

.tr_list {
  height: 32px;
  border-bottom: 1px solid #155d92;
  color: #ffffff;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  line-height: 28px;
  display: flex;
  flex-direction: row;
  padding: 0 24px;
  justify-content: space-between;
}

.centerTd {
  width: 25%;
  text-align: center;
  height: 28px;
}

.tr_border {
  height: 1px;
  width: 100%;
  background-color: #36a9ff;
}

.center_span {
  color: #01d2f6;
}
.dataReturn {
  position: absolute;
  top: 120px;
  left: 520px;
  width: 60px;
  height: 50px;
  /* background: #01d2f6; */
}
.dataReturn span {
  font-size: 18px;
  color: #4ed7ff;
  cursor: pointer;
  font-weight: bold;
}
.dataReturn span:hover {
  color: #ffcc33;
}
</style>
<style scoped>
.dialogPeopleInfoClass_info{
  width: 550px;
  height: 318px;
  background: url("~@/assets/images/peopleinfo/nr-bg.png")
  no-repeat center center / 100% 100%;
}
.dialogPeopleInfoClass_pic{
  margin-top: 15%;
  /* margin-: 12%; */
  width: 197px;
  height: 219px;
  background: url("~@/assets/images/peopleinfo/picbg.png")
  no-repeat center center / 100% 100%; 
}
</style>
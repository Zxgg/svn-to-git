<template>
  <div class="right_content" style="height:100%">
    <div class="btnfenlei">
      <div
        v-for="item in fenlei"
        :key="item.id"
        @click.stop="tab(item.value)"
        :class="item.value === tabflag ? 'btn active' : 'btn'"
      >
        {{ item.label }}
      </div>
    </div>
    <el-table
      v-show="tabflag === 1"
      empty-text="加载中"
      :data="tableData"
      style="width: 100%"
      :header-cell-style="{
        background: 'transparent',
        color: '#CAE5FB',
        textAlign: 'center',
        padding: '0px',
        fontSize: '14px',
        height: '18'
      }"
      :cell-style="{
        background: 'transparent',
        color: '#D3E3EC',
        textAlign: 'center',
        height: '30'
      }"
      class="tabbox"
      @row-click="handle"
    >
      <!-- type="index" -->
      <el-table-column type="index" width="50">
        <template slot-scope="scope">
          <img
            style="margin-left:10px"
            src="../../../assets/images/events/no.1.png"
            alt=""
          />
        </template>
      </el-table-column>
      <el-table-column prop="eventName" label="事件名称" width="150">
        <template slot-scope="scope">
          <span :title="'事件名称：' + scope.row.eventName">{{
            scope.row.eventName | ellipsis
          }}</span>
        </template>
      </el-table-column>

      <!-- <el-table-column prop="eventName"
                       label="摘要"  width="50"> </el-table-column>
      </el-table-column> -->
      <el-table-column prop="dealWithType" label="事件来源" width="90">
        <template slot-scope="scope">
          <span :title="'事件名称：' + scope.row.eventName">{{
            scope.row.dealWithType
          }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="division" label="所属网格" width="80">
        <template slot-scope="scope">
          <span :title="'事件名称：' + scope.row.eventName">{{
            scope.row.division
          }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="eventStatus" label="处置状态">
        <template slot-scope="scope">
          <span v-show="scope.row.eventStatus === '1'" class="bg bg2"
            >已受理</span
          >
          <span v-show="scope.row.eventStatus === '2'" class="bg bg1"
            >已分派</span
          >
          <span v-show="scope.row.eventStatus === '3'" class="bg bg3"
            >已办结</span
          >
          <span v-show="scope.row.eventStatus === '4'" class="bg bg3"
            >已归档</span
          >
        </template>
      </el-table-column>
    </el-table>
    <el-table
      v-show="tabflag === 2"
      :data="tab2"
      empty-text="加载中"
      style="width: 100%"
      :header-cell-style="{
        background: 'transparent',
        color: '#CAE5FB',
        textAlign: 'center',
        padding: '0px',
        fontSize: '14px',
        height: '18'
      }"
      :cell-style="{
        background: 'transparent',
        color: '#D3E3EC',
        textAlign: 'center',
        height: '30'
      }"
      class="tabbox"
      @row-click="shijian"
    >
      <!-- type="index" -->
      <el-table-column type="index" width="50">
        <template slot-scope="scope">
          <img
            style="margin-left:10px"
            src="../../../assets/images/events/no.1.png"
            alt=""
          />
        </template>
      </el-table-column>
      <el-table-column prop="eventName" label="事件名称" width="150">
        <template slot-scope="scope">
          <span :title="'事件名称：' + scope.row.eventName">{{
            scope.row.eventName | ellipsis
          }}</span>
        </template>
      </el-table-column>

      <el-table-column prop="dealWithType" label="事件来源" width="90">
        <template slot-scope="scope">
          <span :title="'事件名称：' + scope.row.eventName">{{
            scope.row.dealWithType
          }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="division" width="80" label="所属网格">
        <template slot-scope="scope">
          <span :title="'事件名称：' + scope.row.eventName">{{
            scope.row.division
          }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="eventStatus" label="处置状态"> </el-table-column>
    </el-table>
    <!-- 事件详情的模态框 -->
    <el-dialog
      class="motai"
      title="事件详情"
      :visible.sync="zibanjiedialogVisible"
      width="80%"
      center
    >
      <div class="motai_father">
        <el-row :gutter="20" class="motai_top">
          <el-col :span="15">
            <div class="xinxititle">基本信息</div>
            <div class="info-form">
              <el-form
                :model="zibanjieform"
                :inline="true"
                label-width="100px"
                class="motai_form"
              >
                <div class="motai_box">
                  <el-form-item class="" label="行政区划:">
                    <div class="formcontent">
                      {{ zibanjieform.division || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item class="" label="处置类型:">
                    <div class="formcontent">
                      {{ zibanjieform.dealWithType || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item class="" label="事件编号:">
                    <div class="formcontent">
                      {{ zibanjieform.id || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item class="" label="事件标题:">
                    <div class="formcontent">
                      {{ zibanjieform.eventName || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item label="事件来源:">
                    <div class="formcontent">
                      {{ zibanjieform.dealWithType || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item label="事件类型:">
                    <div class="formcontent">
                      {{ zibanjieform.eventType || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item label="上报人:">
                    <div class="formcontent">
                      {{ zibanjieform.handleBy || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item label="上报时间:">
                    <div class="formcontent">
                      {{ zibanjieform.reportingTime || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item label="诉求人姓名:">
                    <div
                      class="formcontent"
                      v-if="zibanjieform.id === 'RT000000001'"
                    >
                      {{ zibanjieform.handleBy || "暂无" }}
                    </div>
                    <div class="formcontent" v-else>
                      {{ zibanjieform.petitioner || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item label="诉求人电话:">
                    {{ zibanjieform.createbyPhone || "暂无" }}
                  </el-form-item>
                </div>
                <!-- <div class="motai_box">
     <el-form-item label="状态:">
                    <div class="formcontent"
                        v-show="zibanjieform.eventStatus==='1'">已受理</div>
                    <div class="formcontent"
                         v-show="zibanjieform.eventStatus==='2'">已分派</div>
                    <div class="formcontent"
                         v-show="zibanjieform.eventStatus==='3'">已办结</div>
                                             <div class="formcontent"
                         v-show="zibanjieform.eventStatus==='4'">已归档</div>
                  </el-form-item>
               </div> -->
                <div class="motai_box motai_box_long" style="height:25px">
                  <el-form-item class="" label="严重程度:">
                    <div class="formcontent">
                      {{
                        zibanjieform.eventDegree === "1"
                          ? "一般"
                          : "严重" || "暂无"
                      }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item label="检查对象:">
                    <div class="formcontent">
                      <!-- {{ zibanjieform.updataBy || "小区管理" }} -->
                      小区管理
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item label="检查类别:">
                    <div
                      class="formcontent"
                      v-if="zibanjieform.id === 'RT000000001'"
                    >
                      <!-- {{ zibanjieform.checkType || "改建扩建" }} -->
                      改建扩建
                    </div>
                    <div class="formcontent" v-else>
                      <!-- {{ zibanjieform.checkType || "改建扩建" }} -->
                      智能抓拍
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box motai_box_long">
                  <el-form-item label="内容:">
                    <div class="formcontent">
                      {{ zibanjieform.content || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box motai_box_long">
                  <el-form-item label="备注:">
                    <div class="formcontent">
                      {{ zibanjieform.remarks || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
              </el-form>
            </div>
          </el-col>
          <el-col :span="9">
            <div class="xinxititle ">附件信息</div>
            <div class="fujiancontent">
              <div class="fujiancontent_first">
                <div>抓拍对比照</div>
                <div class="fujian" v-show="zibanjieform.alertFile">
                  <el-image
                    style="width: 100%; height: 100%"
                    :src="zibanjieform.alertFile"
                    v-show="!zibanjieform.bigPicture"
                  >
                    <div slot="placeholder" class="image-slot">
                      加载中<span class="dot">...</span>
                    </div>
                  </el-image>
                  <el-image
                    style="width: 100%; height: 100%"
                    :src="zibanjieform.alertFile"
                    v-show="zibanjieform.bigPicture"
                    :preview-src-list="[zibanjieform.bigPicture]"
                  >
                    <div slot="placeholder" class="image-slot">
                      加载中<span class="dot">...</span>
                    </div>
                  </el-image>
                </div>
              </div>
              <div class="fujiancontent_two">
                <div></div>
                <div class="fujian" v-show="zibanjieform.fileId">
                  <el-image
                    style="width: 100%; height: 100%"
                    :src="zibanjieform.fileId"
                    :preview-src-list="[zibanjieform.fileId]"
                  >
                    <div slot="placeholder" class="image-slot">
                      加载中<span class="dot">...</span>
                    </div>
                  </el-image>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
        <div class="motai_bottom">
          <div class="xinxititle">
            事件流程
          </div>
          <div class="liuchengfather">
            <div
              v-for="(item, i) in liuchengList"
              :key="item.id"
              class="liucheng"
            >
              <div class="liucheng_first">
                <div>
                  <img src="../../../assets/images/events/gouxuan.png" alt="" />
                </div>

                <div v-show="i < 4">
                  <img src="../../../assets/images/events/jiantou.png" alt="" />
                </div>
              </div>
              <div class="liucheng_two">{{ item.eventStatusSon }}</div>
              <div class="liucheng_three">
                <div class="liucheng_three_a">
                  <div>
                    处理时间
                  </div>
                  <div>
                    {{ item.createTime || "暂无" }}
                  </div>
                </div>
                <div>
                  <div>
                    处理人
                  </div>
                  <div>
                    {{ item.handleBy || "暂无" }}
                  </div>
                </div>
                <div>
                  <div>
                    处理意见
                  </div>
                  <div>
                    {{ item.content || "暂无" }}
                  </div>
                </div>
              </div>
            </div>
            <div v-show="1 - liuchengList.length > 0" class="liucheng ">
              <div class="liucheng_first">
                <div>
                  <img
                    src="../../../assets/images/events/gouxuan.png"
                    alt=""
                    v-show="false"
                  />
                </div>

                <div>
                  <img src="../../../assets/images/events/jiantou.png" alt="" />
                </div>
              </div>
              <div class="liucheng_two">上报</div>
            </div>
            <div v-show="2 - liuchengList.length > 0" class="liucheng ">
              <div class="liucheng_first">
                <div>
                  <img
                    src="../../../assets/images/events/gouxuan.png"
                    alt=""
                    v-show="false"
                  />
                </div>

                <div>
                  <img src="../../../assets/images/events/jiantou.png" alt="" />
                </div>
              </div>
              <div class="liucheng_two">受理</div>
            </div>
            <div v-show="3 - liuchengList.length > 0" class="liucheng ">
              <div class="liucheng_first">
                <div>
                  <img
                    src="../../../assets/images/events/gouxuan.png"
                    alt=""
                    v-show="false"
                  />
                </div>

                <div>
                  <img src="../../../assets/images/events/jiantou.png" alt="" />
                </div>
              </div>
              <div class="liucheng_two">分派</div>
            </div>
            <div v-show="4 - liuchengList.length > 0" class="liucheng ">
              <div class="liucheng_first">
                <div>
                  <img
                    src="../../../assets/images/events/gouxuan.png"
                    alt=""
                    v-show="false"
                  />
                </div>

                <div>
                  <img src="../../../assets/images/events/jiantou.png" alt="" />
                </div>
              </div>
              <div class="liucheng_two">办结</div>
            </div>
            <div v-show="5 - liuchengList.length > 0" class="liucheng ">
              <div class="liucheng_first">
                <div>
                  <img
                    src="../../../assets/images/events/gouxuan.png"
                    alt=""
                    v-show="false"
                  />
                </div>

                <div>
                  <img
                    src="../../../assets/images/events/jiantou.png"
                    alt=""
                    v-show="false"
                  />
                </div>
              </div>
              <div class="liucheng_two">归档</div>
            </div>
          </div>
        </div>
      </div>
    </el-dialog>
    <!-- 1+4平台的模态框 -->
    <el-dialog
      class="motai"
      title="工单详情"
      :visible.sync="dialogVisible"
      width="80%"
      center
    >
      <div class="motai_father">
        <el-row :gutter="20" class="motai_top">
          <el-col :span="15">
            <div class="xinxititle">基本信息</div>
            <div class="info-form">
              <el-form
                :model="jieform"
                :inline="true"
                label-width="100px"
                class="motai_form"
              >
                <div class="motai_box">
                  <el-form-item class="" label="行政区划:">
                    <div class="formcontent">
                      {{ jieform.division || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item class="" label="处置类型:">
                    <div class="formcontent">
                      {{ jieform.disposition || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item class="" label="事件编号:">
                    <div class="formcontent">{{ jieform.id || "暂无" }}</div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item class="" label="事件标题:">
                    <div class="formcontent">
                      {{ jieform.eventName || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item class="" label="事件来源:">
                    <div class="formcontent">
                      {{ jieform.dealWithType || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item class="" label="事件类型:">
                    <div class="formcontent">
                      {{ jieform.eventType || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item class="" label="上报人:">
                    <div class="formcontent">
                      {{ jieform.handleBy || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item class="" label="上报时间:">
                    <div class="formcontent">
                      {{ jieform.reportingTime || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item class="" label="诉求人姓名:">
                    <div class="formcontent">
                      {{ jieform.petitioner || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item label="诉求人电话:">
                    <div class="formcontent">
                      {{ jieform.createByPhone || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box motai_box_long" style="height:25px">
                  <el-form-item class="" label="严重程度:">
                    <div class="formcontent">
                      {{ jieform.eventDegree || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item label="检查对象:">
                    <div class="formcontent">
                      {{ jieform.updataBy || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box">
                  <el-form-item label="检查类别:">
                    <div class="formcontent">
                      {{ jieform.checkType || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
                <div class="motai_box motai_box_long">
                  <el-form-item class="" label="内容:">
                    <span class="formcontent">{{ jieform.content }}</span>
                  </el-form-item>
                </div>
                <div class="motai_box motai_box_long">
                  <el-form-item class="" label="备注:">
                    <div class="formcontent">
                      {{ jieform.remark || "暂无" }}
                    </div>
                  </el-form-item>
                </div>
              </el-form>
            </div>
          </el-col>
          <el-col :span="9">
            <div class="xinxititle ">附件信息</div>
            <div class="fujiancontent">
              <div class="fujiancontent_first">
                <div>处置前</div>
                <div class="fujian" v-show="jieform.fileId">
                  <el-image
                    style="width: 100%; height: 100%"
                    :src="jieform.fileId"
                    :preview-src-list="[jieform.fileId]"
                  >
                    <div slot="placeholder" class="image-slot">
                      加载中<span class="dot">...</span>
                    </div>
                  </el-image>
                </div>
              </div>
              <div class="fujiancontent_two">
                <div>处置后</div>
                <div class="fujian" v-show="jieform.alertFile">
                  <el-image
                    style="width: 100%; height: 100%"
                    :src="jieform.alertFile"
                    :preview-src-list="[jieform.alertFile]"
                  >
                    <div slot="placeholder" class="image-slot">
                      加载中<span class="dot">...</span>
                    </div>
                  </el-image>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
        <div class="matai_bottom">
          <div class="xinxititle">
            事件流程
          </div>
          <div class="liuchengfather">
            <div v-for="(itm, i) in jieform.remarks" :key="i" class="liucheng">
              <div class="liucheng_first">
                <div>
                  <img src="../../../assets/images/events/gouxuan.png" alt="" />
                </div>

                <div v-show="jieform.remarks.length !== i + 1">
                  <img src="../../../assets/images/events/jiantou.png" alt="" />
                </div>
              </div>
              <div class="liucheng_two">{{ itm.stepname }}</div>
              <div class="liucheng_three">
                <div class="liucheng_three_a">
                  <div>
                    处理时间
                  </div>
                  <div>
                    {{ itm.stepfinishdate || "暂无" }}
                  </div>
                </div>
                <div>
                  <div>
                    处理人
                  </div>
                  <div>
                    {{ itm.stephandleuser || "暂无" }}
                  </div>
                </div>
                <div>
                  <div>
                    处理意见
                  </div>
                  <div>
                    {{ itm.stepopinion || "暂无" }}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import {
  baseshijianEventInfoList,
  ajaxFuntion,
  getCommunity_event,
  getInfo,
  listRecord,
  getshangbao,
  newGetEventData
} from "@/api/communityEvent/community_event";
import configInfo from "@/webconfig/configInfo";
import bus from "../../../assets/eventbus";
export default {
  props: {
    currentComunity: {
      communityId: String,
      communityChildId: String
    }
  },
  data() {
    return {
      community_eventList: null,
      tabflag: 1,
      tableData: [],
      loading: true,
      tab1: [],
      tab2: [],
      rowid: null,
      liuchengactive1: 2,
      liuchengactive2: 4,
      zibanjiedialogVisible: false,
      dialogVisible: false,
      caringremindList: [],
      zibanjieform: {},
      jieform: {},
      msg: null,
      active: 1,
      liuchengList: [],
      eventTypeList: [],
      processList: [
        {
          value: "0",
          label: "上报"
        },
        {
          value: "1",
          label: "受理"
        },
        {
          value: "2",
          label: "处置"
        },
        {
          value: "3",
          label: "办结"
        },
        {
          value: "4",
          label: "归档"
        }
      ],
      fenlei: [
        { value: 1, label: "社区自办" },
        { value: 2, label: "上级分拨" }
      ]
    };
  },
  filters: {
    ellipsis(value) {
      if (!value) return "";
      if (value.length > 8) {
        return value.slice(0, 8) + "...";
      }
      return value;
    }
  },
  created() {},
  mounted() {
    this.getDicts("base_event_type").then(response => {
      this.eventTypeList = response.data;
    });
    this.getfirst();
    this.$Notice.$on("getEventschange", msg => {
      console.log(msg);
      this.getEventList();
    });
    this.newGetEventData_();
    var s = this;
    bus.$on("get2", function(msg) {
      if (msg === "1") {
        s.getEventList();
      }
      if (msg === "2") {
        s.newGetEventData_();
      }
    });
  },

  watch: {
    currentComunity: {
      handler(val, data) {
        this.refreshData();
      },
      deep: true
    },
    tableData: {
      handler(newVal) {
        this.tableData = newVal;
      },
      deep: true
    }
  },

  methods: {
    //页面初始化加载
    getfirst() {
      this.getEventList();
      this.newGetEventData_();
    },
    // tab切换
    tab(v) {
      this.tabflag = v;
      if (v === 1) {
        //查询社区事件
        this.getEventList();
      }
      if (v === 2) {
        //查询自结事件
        this.newGetEventData_();
      }
    },
    handle(row) {
      this.rowid = row.id;
      this.zibanjiedialogVisible = true;
      this.zibanjieform = row;
      // this.zibanjieform.eventStatus=this.processList[this.zibanjieform.eventStatus].label
      if (row.fileId) {
        if (row.fileId.indexOf(configInfo.imgEchoAddress) === -1) {
          this.zibanjieform.fileId = configInfo.imgEchoAddress + row.fileId;
        }
      }
      if (row.bigPicture) {
        if (row.bigPicture.indexOf(configInfo.imgEchoAddress) === -1) {
          this.zibanjieform.bigPicture =
            configInfo.imgEchoAddress + row.bigPicture;
        }
      }
      if (row.alertFile) {
        if (row.alertFile.indexOf(configInfo.imgEchoAddress) === -1) {
          this.zibanjieform.alertFile =
            configInfo.imgEchoAddress + row.alertFile;
        }
      }

      this.handleInfo();
    },

    render() {
      $("#imgtjia").innerHTML = `
           <img src="+${this.jieform.fileId}+"/>
            `;
    },
    shijian(row) {
      // debugger
      console.log(row);
      this.dialogVisible = true;
      // if(this.jieform.eventStatus)
      if (row.fileId) {
        if (row.fileId.indexOf(configInfo.imgEchoAddress) === -1) {
          row.fileId = configInfo.imgEchoAddress + row.fileId;
        }
      }
      if (row.alertFile) {
        if (row.alertFile.indexOf(configInfo.imgEchoAddress) === -1) {
          row.alertFile = configInfo.imgEchoAddress + row.alertFile;
        }
      }
      let con = JSON.parse(JSON.stringify(row));
      this.jieform = con;
      // console.log(this.jieform,909090);
      // console.log(typeof this.jieform.eventStatus,9090);
      // if (row.fileId.indexOf(configInfo.imgEchoAddress) === -1) {
      //   this.zibanjieform.fileId = configInfo.imgEchoAddress + row.fileId;
      // }
    },
    // 查询社区事件列表
    getEventList() {
      let param = {
        dealWithType: "1",
        communityId: "0355931e-a70b-11eb-a21e-0050569ba051",
        eventStatus: "0"
      };
      baseshijianEventInfoList(param).then(response => {
        if (response && response.rows.length > 0) {
          this.tab1 = response.rows;
          this.tableData = this.tab1;
          this.loading = false;
          this.tableData.forEach(val => {
            if (val.dealWithType === "1") {
              val.dealWithType = "智能化事件";
            }
            if (val.dealWithType === "2") {
              val.dealWithType = "人工处理";
            }
          });
        }
      });
    },
    // 入库后的1+4平台的数据请求
    //  newGetEventData_(){
    //     let params={
    //    communityId: "0355931e-a70b-11eb-a21e-0050569ba051",
    //     pageNum: 1,
    //     pageSize: 10,
    //     }
    //     newGetEventData(params).then(res=>{
    //       if(res.code===200){
    //         res.data.forEach(val=>{
    //           val.remarks= JSON.parse(val.remarks)
    //         })
    //         this.tab2 = res.data

    //       }
    //     })
    // },
    //请求1+4平台数据列表
    newGetEventData_() {
      let data = {
        pageindex: "1",
        pagesize: "10"
      };
      ajaxFuntion(data).then(response => {
        if (response) {
          response.data.data.forEach(val => {
            val.remarks = JSON.parse(val.remarks);
          });
          this.tab2 = response.data.data;
          this.loading = false;
        }
      });
    },
    // 查询事件详细信息
    handleInfo() {
      // this.formSpan = 12;
      const id = this.rowid;
      let queryParams = {
        pageNum: 1,
        pageSize: 99999,
        eventId: id,
        orderByColumn: "createTime",
        isAsc: "desc"
      };
      listRecord(queryParams).then(response => {
        if (response.code === 200) {
          this.liuchengList = response.rows;
          this.liuchengchuli();
        }
      });
    },
    // 处理流程的展示信息
    liuchengchuli() {
      this.liuchengList = this.liuchengList.filter((val, index) => {
        return (
          val.eventStatusSon !== "2" &&
          val.eventStatusSon !== "4" &&
          val.eventStatusSon !== "6"
        );
      });
      this.liuchengList.forEach((val, index) => {
        val.eventStatusSon = this.processList[index].label;
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.btnfenlei {
  float: right;
  height: 30px;
  margin-bottom: 2%;
  > div {
    cursor: pointer;
  }
  button {
    width: 100px;
  }
}
.btn {
  display: inline-block;
  width: 100px;
  height: 30px;
  text-align: center;
  line-height: 30px;
  color: #ffffff;
  background: no-repeat url(../../../assets/images/events/社区事件bnt.png);
  background-size: 100% 100%;
}

.active {
  background: no-repeat url(../../../assets/images/events/社区事件bnt-on.png);
  background-size: 100% 100%;
}
.el-table {
  height: calc(98% - 38px);
  background-color: transparent;
  /deep/.el-table__body-wrapper {
    height: calc(100% - 30px);
    overflow: auto;
  }
}
/deep/.table-wrapper .el-table td {
  border-bottom: 0px solid #dfe6ec;
}
.el-table /deep/tr,
/deep/.el-table__header,
.el-table /deep/ thead,
/deep/.el-table__header-wrapper,
.el-table /deep/tr:hover {
  background-color: transparent;
}
.el-table /deep/tr {
  background: url(../../../assets/images/events/事件处置bg-1.png) no-repeat;
  background-size: 100% 100%;
}
.el-table__body /deep/tr {
  cursor: pointer;
}
/deep/.el-table th.is-leaf,
.el-table /deep/td {
  border-bottom: 0px solid #dfe6ec;
}
/deep/.el-table::before {
  content: "";
  position: absolute;
  background-color: transparent;
  z-index: 1;
}
/deep/.el-table__empty-text {
  color: #ffffff;
}
.el-form-item {
  margin-bottom: 0;
}

/deep/.el-dialog__body {
  height: 100%;
}
.information::before {
  content: "";
  display: block;
  width: 0px;
  height: 0px;
  border: 10px solid rgba(0, 0, 0, 0);
  border-bottom: 10px solid #004367;
}
.information {
  width: 200px;
}
//受理的样式
.bg {
  padding: 5px 10px;
  width: 100px;
}
.bg1 {
  background: no-repeat url(../../../assets/images/events/处理中.png);
  background-size: 100% 100%;
}
.bg2 {
  background: no-repeat url(../../../assets/images/events/已上报.png);
  background-size: 100% 100%;
}
.bg3 {
  background: no-repeat url(../../../assets/images/events/处置完成.png);
  background-size: 100% 100%;
}

.motai {
  letter-spacing: 0.5px;
  position: relative;
  .motai_father {
    height: 100%;
    .motai_top {
      height: 50%;
      overflow: auto;
      .info-form {
        height: 100%;
      }
    }
    .motai_bottom {
      height: calc(50% - 10%);
      margin-top: 10px;
      overflow: auto;
    }
  }

  .xinxititle {
    background: no-repeat url(../../../assets/images/events/标题背景1.png);
    background-size: 100% 100%;
    height: 40px;
    font-size: 14px;
    font-family: SourceHanSansCN;
    font-weight: bold;
    color: #4ef4ff;
    line-height: 40px;
    box-sizing: border-box;
    padding-left: 20px;
    &::before {
      content: "";
      height: 38px;
      width: 10px;
      background: no-repeat url(../../../assets/images/events/标题背景2.png);
    }
  }
  .fujiancontent_first {
    display: flex;
    > div:first-of-type {
      flex: 0 0 80px;
      padding-top: 20px;
    }
  }
  .fujiancontent_two {
    display: flex;
    > div:first-of-type {
      flex: 0 0 80px;
      padding-top: 20px;
    }
  }
  .fujian {
    width: 230px;
    height: 180px;
    margin-top: 10px;
    background: no-repeat url(../../../assets/images/events/照片bg.png);
    box-sizing: border-box;
    padding: 5px;
    img {
      width: 100%;
      height: 100%;
    }
  }

  .liuchengfather {
    display: flex;
    justify-content: start;
    padding-left: 30px;
    overflow: auto;
    &::-webkit-scrollbar {
      height: 8px;
    }
    .liucheng {
      flex: 0 0 calc(20% - 30px);
      height: 100%;
      margin-right: 20px;
      display: flex;
      justify-content: start;
      flex-direction: column;
      > div {
        margin-top: 20px;
      }
      .liucheng_first {
        display: flex;
        justify-content: space-between;
        height: 50px;
        div {
          height: 50px;
          width: 50px;
          position: relative;
          left: 50%;
          transform: translateX(-50%);
          img {
            width: 26px;
            height: 18px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
          }
        }
        div:first-of-type {
          background: no-repeat url(../../../assets/images/events/kuang.png);
          background-size: 100% 100%;
        }
        div:last-of-type {
          left: 20%;
          margin-right: 10px;
        }
      }
      .liucheng_two {
        color: #d3e3ec;
        text-align: center;
        height: 20px;
      }
      .liucheng_three {
        height: calc(100% - 20px - 50px);
        width: 80%;
        padding: 10px;
        background: no-repeat url(../../../assets/images/events/nr-bg.png);
        background-size: 100% 100%;
        position: relative;
        left: 50%;
        transform: translateX(-50%);
        &::before {
          content: "";
          width: 10px;
          height: 10px;
          border: 10px solid transparent;
          border-bottom: 10px solid rgba(3, 30, 73, 0.3);
          // opacity: 0.3;
          position: absolute;
          top: -10%;
          left: 47%;
        }
        > div {
          margin-bottom: 20px;
        }
      }
    }
  }

  /deep/.el-dialog {
    border: 0px solid #005982;
    background-color: transparent;
    height: 100%;
    position: fixed;
    top: -10%;
    left: 50%;
    transform: translateX(-50%);
    background: no-repeat url(../../../assets/images/events/弹框bg.png);
    background-size: 100% 100%;
    .el-dialog__header {
      background-color: transparent;
      border-bottom: 0px solid #005982;
      height: 18px;
      .el-dialog__title {
        height: 100%;
        font-size: 18px;
        font-family: PangMenZhengDao;
        font-weight: bold;
        color: #ffffff;
        text-shadow: 0px 2px 2px rgba(1, 90, 166, 0.68);

        background: linear-gradient(180deg, #fffcff 0%, #7bc6f7 99.365234375%);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
      }
    }
    .el-dialog__body {
      padding: 2% 2% 1%;
      background-position: 54% 52%;
      color: #d3e3ed;
    }
  }
  .motai_form {
    display: flex;
    flex-wrap: wrap;
    .motai_box {
      width: 49%;
      height: 25px;
      margin-top: 10px;
      overflow: auto;
      /deep/.el-form-item {
        height: 20px;
        line-height: 20px;
        width: 95%;
        .el-form-item__label {
          height: 20px;
          line-height: 20px;
        }
        .el-form-item__content {
          width: calc(100% - 105px);
          height: 20px;
          line-height: 20px;
        }
      }
      &.motai_box_long {
        width: 90%;
        height: 60px;
        line-height: 20px;
      }
      .formcontent {
        font-size: 14px;
        font-family: Adobe Heiti Std;
        font-weight: normal;
        color: #d3e3ec;
        width: 100%;
        display: inline-block;
      }
    }
  }

  .btn {
    color: #d3e3ed;
    width: 110px;
    height: 33px;
    line-height: 33px;
    text-align: center;
    font-size: 20px;
  }
}
</style>

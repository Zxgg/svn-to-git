<template>
  <div class="Main" ref="MainView">
    <div class="rightBox">
      <!-- 社区简介 -->
      <div class="communityProfile">
        <div class="car-boxTitle">
          <span>社区简介</span>
          <span>COMMUNITY PROFILE</span>
        </div>

        <div class="specialPer">
          <div class="specialPertext">{{ communityDescription }}</div>
          <div class="grid_league">
            <div class="grid_league_title">
              <div><span @click="pptShow('1')">网格大联盟</span></div>
              <div>
                <span @click="pptShow('2')">社区组织网络图</span>
              </div>
              <div>
                <span @click="pptShow('3')">网格员服务事项</span>
              </div>
            </div>
            <div class="grid_league_content">
              <el-row :gutter="2">
                <el-col :span="6" class="grid_league_content_left">
                  <div class="imgbox">
                    <!-- <img src="./../../assets/images/index/网格长1.png" alt=""> -->
                    <div class="img">
                      <img
                        src="../../assets/images/index_wangge/01.jpg"
                        alt=""
                      />
                    </div>
                  </div>

                  <div class="name">郑翔</div>
                  <div class="total">网格长</div>
                </el-col>
                <el-col :span="18" class="grid_league_content_right">
                  <!-- <div v-for="item in list" :key="item.name" :span="8" class="">
                    <div class="imgbox">
                      <img :src="item.url" alt="" />
                      <div class="name">{{ item.name }}</div>
                    </div>
                    <div class="total">{{ item.tot }}</div>
                  </div> -->

                  <div>
                    <div class="imgbox">
                      <img
                        src="../../assets/images/index_wangge/003.jpg"
                        alt=""
                      />
                      <div class="name">蒋华锋</div>
                    </div>
                    <div class="total">治安指导员</div>
                  </div>
                  <div>
                    <div class="imgbox">
                      <img
                        src="../../assets/images/index_wangge/001.png"
                        alt=""
                      />
                      <div class="name">陈桂香</div>
                    </div>
                    <div class="total">民生指导员</div>
                  </div>
                  <div>
                    <div class="imgbox">
                      <img
                        src="../../assets/images/index_wangge/004.png"
                        alt=""
                      />
                      <div class="name">奚峰</div>
                    </div>
                    <div class="total">市场监督指导员</div>
                  </div>
                  <div>
                    <div class="imgbox">
                      <img
                        src="../../assets/images/index_wangge/005.png"
                        alt=""
                      />
                      <div class="name">易思茂</div>
                    </div>
                    <div class="total">应急管理指导员</div>
                  </div>
                  <div>
                    <div class="imgbox">
                      <img
                        src="../../assets/images/index_wangge/05.jpg"
                        alt=""
                      />
                      <div class="name">周寅</div>
                    </div>
                    <div class="total">消防指导员</div>
                  </div>
                  <div>
                    <div class="imgbox">
                      <img
                        src="../../assets/images/index_wangge/06.jpg"
                        alt=""
                      />
                      <div class="name">李振兴</div>
                    </div>
                    <div class="total">城管指导员</div>
                  </div>
                </el-col>
              </el-row>
            </div>
          </div>
        </div>
        <!-- <div class="motaibox2" id="tu" v-show="firstdialog">
          <div ref="showtu">
            <div class="motai_father" v-show="onedialog">
              <img src="./../../assets/images/index/网格大联盟.png" alt="" />
              <el-button class="btnright" @click="pptShow('2')" circle>
                <i class="el-icon-arrow-right"></i>
              </el-button>
            </div>
            <div class="motai_father" v-show="twodialog">
              <el-button class="btnleft" circle @click="pptShow('1')"
                ><i class="el-icon-arrow-left"></i
              ></el-button>
              <img src="./../../assets/images/index/社区组织图.png" alt="" />
              <el-button class="btnright" @click="pptShow('3')" circle>
                <i class="el-icon-arrow-right"></i>
              </el-button>
            </div>
            <div class="motai_father" v-show="threedialog">
              <el-button class="btnleft" circle @click="pptShow('2')"
                ><i class="el-icon-arrow-left"></i
              ></el-button>
              <img src="./../../assets/images/index/社区服务图.png" alt="" />
            </div>
            <div @click.stop="handleClose" class="colse">X</div>
          </div>
        </div> -->
        <el-dialog
          class="motai"
          :visible.sync="firstdialog"
          :fullscreen="true"
          center
        >
          <div class="motaibox" id="tu" ref="showtu">
            <div
              class="motai_father"
              v-show="onedialog"
              @keyup.right.native="pptShow('2')"
            >
              <img src="./../../assets/images/index/网格大联盟.png" alt="" />
              <el-button class="btnright" @click="pptShow('2')" circle>
                <i class="el-icon-arrow-right"></i>
              </el-button>
            </div>
            <div
              class="motai_father"
              v-show="twodialog"
              @keyup.space.native="pptShow('1')"
              @keyup.right.native="pptShow('3')"
            >
              <el-button class="btnleft" circle @click="pptShow('1')"
                ><i class="el-icon-arrow-left"></i
              ></el-button>
              <img :src="imgLists.url" alt="" />
              <el-button class="btnright" @click="pptShow('3')" circle>
                <i class="el-icon-arrow-right"></i>
              </el-button>
            </div>
            <div
              class="motai_father"
              v-show="threedialog"
              @keyup.left.native="pptShow('2')"
            >
              <el-button class="btnleft" circle @click="pptShow('2')"
                ><i class="el-icon-arrow-left"></i
              ></el-button>
              <img src="./../../assets/images/index/社区服务图.png" alt="" />
            </div>
            <div @click.stop="handleClose" class="colse">X</div>
          </div>
        </el-dialog>
      </div>

      <div class="communityGlory">
        <el-row>
          <el-col :span="24" class="car-boxTitle">
            <span>社区光荣榜</span>
            <span>COMMUNITY HALL OF FAME</span>
          </el-col>
        </el-row>

        <el-row class="communityGloryRow">
          <el-row class="gloryContent">
            <div class="outer-container">
              <div class="inner-container" style="margin-top: 7px;">
                <el-row
                  v-for="(item, index) in communityHonor"
                  :key="index"
                  class="specialPerLi"
                >
                  <el-col :span="3" class="honorXuhao">
                    <span>{{ index + 1 }}</span>
                  </el-col>
                  <el-col :span="21" class="honorContent">{{
                    item.honor
                  }}</el-col>
                </el-row>
              </div>
            </div>
          </el-row>
        </el-row>
      </div>
      <!-- 网格分布 -->
      <div class="eventsNum">
        <el-row>
          <el-col :span="24" class="car-boxTitle">
            <span>网格活动</span>
            <span>GRID ACTIVITIES</span>
          </el-col>
        </el-row>
        <div class="eventsNumContent">
          <el-carousel
            :interval="4000"
            autoplay
            type="card"
            height="200px"
            indicator-position="none"
            arrow="always"
          >
            <el-carousel-item
              v-for="(item, index) in imgList"
              :key="index"
              class="bg"
            >
              <div class="card">
                <img :src="item.url" alt="" />
              </div>
            </el-carousel-item>
          </el-carousel>
          <div class="contentTitle">
            创新网格工作，深化“党建、服务、自治、文化到网格”
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import screenfull from "screenfull";
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import populationFlowChart from "@/components/echarts/main/populationFlow";
import travelStatisticsChart from "@/components/echarts/main/travelStatistics";
import { getSpecialPersonNum } from "@/api/homePage/specialPerson";
import { getCareEventNum } from "@/api/homePage/careEventNum";
export default {
  components: { populationFlowChart, travelStatisticsChart },
  data() {
    return {
      that: this,
      centContainerHeight: "100px",
      specialNums: [],
      firstdialog: false,
      onedialog: false,
      twodialog: false,
      threedialog: false,
      offsetHeight: null,
      wanggezhang: {},
      shownum: null,
      tableData: [
        {
          date: "2016-05-02",
          type: "长期未出现",
          num: "3"
        },
        {
          date: "2016-05-04",
          type: "生日提醒",
          num: "1"
        },
        {
          date: "2016-05-01",
          type: "长期未出现",
          num: "3"
        },
        {
          date: "2016-05-03",
          type: "生日提醒",
          num: "1"
        }
      ],
      list: [],
      imgList: [],
      imgLists: {},
      communityDescription: "社区简介",
      communityDescriptionImage: null,
      communityDescriptionImagefit: "contain",
      communityHonor: [], //社区荣誉
      personnelDistribution: [] //人员分布 网格 常驻人口 党员
    };
  },
  created() {},
  filters: {},
  computed: {
    ...mapGetters(["currentComunity"])
  },
  watch: {
    currentComunity: {
      handler(val, data) {
        this.getData();
        // console.log(val.communityId, 'val')
      },
      deep: true
    },
    offsetHeight: {
      handler(val, data) {
        console.log(val, data, "我是高度");
      },
      deep: true
    }
  },
  methods: {
    // tu(){
    //   this.communityHonor.forEach((val,i)=>{
    //     val.url=`../../assets/images/index/${i+1}.png`
    //   })

    // },
    pptShow(show) {
      this.shownum = Number(show);
      if (show === "1") {
        this.threedialog = false;
        this.twodialog = false;
        this.onedialog = true;
      }
      if (show === "2") {
        this.onedialog = false;
        this.threedialog = false;
        this.twodialog = true;
      }
      if (show === "3") {
        this.onedialog = false;
        this.twodialog = false;
        this.threedialog = true;
      }
      this.firstdialog = true;
      // var elem = document.getElementById("tu");
      // elem.style.width = "100%";
      // elem.style.height = "100%";
      // elem.style.overflowY = "scroll";
      // this.requestFullScreen(elem); // 某个页面元素
    },

    // 监听是否按下了esc键
    handleClose() {
      this.onedialog = false;
      this.twodialog = false;
      this.threedialog = false;
      this.firstdialog = false;
    },
    areaFormat(row, col, cell) {
      return cell && cell.length > 0 ? cell + " km²" : "";
    },
    refreshCenterContainerHeight() {
      this.centContainerHeight =
        this.$refs.MainView.offsetHeight - 72 - 331 - 4 + "px";
    },
    getRowClass({ row, column, rowIndex, columnIndex }) {
      return "background:#222751;color:#fff;border-bottom-color:#182751;";
    },
    getData() {
      this.tableData = [];
      let communityId = this.currentComunity.communityId;
      let communityChildId = this.currentComunity.communityChildId;
      // getSpecialPersonNum(communityId, communityChildId).then((res) => {
      //   // console.log(res, 'ressss')
      //   if (res && res.data)
      //     this.specialNums = [{ name: '艾滋病人', num: res.data.aidsNum },
      //     { name: '社区矫正人员', num: res.data.correctNum },
      //     { name: '肇事肇祸倾向的精神病人', num: res.data.mentalNum },
      //     { name: '刑满释放人员', num: res.data.releaseNum },
      //     // { name: '闲散流浪人员信息', num: res.data.releaseNum },
      //     { name: '吸毒人员', num: res.data.drugNum },]
      // })
      this.specialNums = this.testData.shouye.specialNums;
      this.tableData = this.testData.shouye.careEvent;
      // getCareEventNum(communityId, communityChildId).then((res) => {
      //   // console.log(res, 'ressss111')
      //   if (res && res.data) {
      //     res.data.forEach(data => {
      //       let careEvent = {
      //         type: data.eventName,
      //         num: data.num
      //       }
      //       this.tableData.push(careEvent)
      //     });
      //   }
      // })
    },
    checkFull() {
      //判断浏览器是否处于全屏状态 （需要考虑兼容问题）
      //火狐浏览器
      debugger;
      var isFull =
        document.mozFullScreen ||
        document.fullScreen ||
        //谷歌浏览器及Webkit内核浏览器
        document.webkitIsFullScreen ||
        document.webkitRequestFullScreen ||
        document.mozRequestFullScreen ||
        document.msFullscreenEnabled;
      if (isFull === undefined) {
        isFull = false;
      }
      return isFull;
    }
  },
  mounted() {
    this.communityDescription = this.testData.shouye.communityDescription;
    this.communityDescriptionImage = this.testData.shouye.communityDescriptionImage;
    this.communityDescriptionImagefit = this.testData.shouye.communityDescriptionImagefit;
    this.communityHonor = this.testData.shouye.communityHonor;
    this.personnelDistribution = this.testData.renyuan.peopleHousingInfoList;
    this.wanggezhang = this.testData.shouye.wanggezhang;
    this.list = this.testData.shouye.lianmenglist;
    this.imgList = this.testData.shouye.imgList;
    this.imgLists = this.testData.shouye.wanggeimg;
    // this.tu()
    this.getData();
    $(document).keydown(event => {
      // let self =this
      if (!this.firstdialog) {
        return;
      }
      console.log(event.keyCode);
      if (event.keyCode === 37) {
        console.log(this.shownum, 9090);
        if (this.shownum === 1) {
          return;
        }
        let to = String(this.shownum - 1);
        this.pptShow(to);
      }
      if (event.keyCode === 39) {
        console.log(this.shownum, 9090);
        if (this.shownum === 3) {
          return;
        }
        let to = String(this.shownum + 1);
        this.pptShow(to);
      }
    });
  }
  // mounted () { const the_page = this; window.onresize = () => { return (() => { the_page.refreshCenterContainerHeight() })() }; this.refreshCenterContainerHeight() }
};
</script>
<style lang="scss" scoped>
@import "~@/assets/styles/main/index.css";
.gridTitleClass {
  height: 25px;
  line-height: 30px;
  /* background-color: #042247; */
}

.gridLine {
  width: 440px;
}

.honorContent {
  color: #fff;
  font-size: 12px;
  margin-left: 10px;
}
.communityDescriptionImageC {
  width: 100%;
  height: 293px;
  padding-left: 5%;
  padding-right: 5%;
}
.Main {
  width: 100%;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
  background: transparent;
}
.el-table /deep/ .el-table__row {
  background-color: transparent;
}

.car-boxTitle {
  height: 20px;
  margin-bottom: 5px;
  margin-top: 0px;
}
.communityProfile {
  height: 45%;
  .specialPer {
    height: calc(100% - 25px);
    background: url(../../assets/images/index/社区简介bg.png) no-repeat center
      center;
    background-size: 100% 100%;
    color: #d3e3ec;
    font-size: 12px;
    padding: 5px 20px 0;
    .specialPertext {
      height: 150px;
      color: #d3e3ec;
      font-size: 14px;
      letter-spacing: 2px;
      text-indent: 24px;
      font-family: SourceHanSansCN;
      font-weight: 400;
      color: #d3e3ec;
      line-height: 20px;
      padding: 0px 10px 5px;
      overflow: auto;
      border-bottom: 1px dashed #94d0fa;
    }
    .grid_league {
      height: calc(100% - 150px);
      border-top: 1px dashed #94d0fa;
      .grid_league_title {
        margin-top: 2%;
        height: 30px;
        display: flex;
        justify-content: space-between;
        flex-wrap: nowrap;
        div {
          font-size: 18px;
          margin: 0 2%;
          font-family: YouSheBiaoTiHei;
          font-weight: 400;
          color: #ffffff;
          padding: 5px;
          background: url("./../../assets/images/home/社区组织.png") no-repeat;
          background-size: 100% 100%;
          span {
            cursor: pointer;
            background: linear-gradient(
              0deg,
              rgba(98, 194, 234, 0.94) 0.146484375%,
              rgba(213, 239, 247, 0.94) 100%
            );
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
          }
          &:first-of-type {
            background: url("./../../assets/images/home/网格大联盟.png")
              no-repeat;
            background-size: 100% 100%;
            span {
              background: linear-gradient(
                0deg,
                rgba(146, 169, 186, 0.94) 0%,
                rgba(255, 208, 155, 0.94) 0%,
                rgba(255, 254, 251, 0.94) 100%
              );
              -webkit-background-clip: text;
              -webkit-text-fill-color: transparent;
            }
          }
        }
      }
      .grid_league_content {
        height: calc(100% - 30px - 2%);
        padding: 1% 2%;
        .el-row {
          height: 100%;
          .grid_league_content_left {
            height: 100%;
            text-align: center;
            background: url("./../../assets/images/index/网格长bg.png")
              no-repeat center center / 100% 100%;
            padding-top: 5%;
            display: flex;
            flex-direction: column;
            .imgbox {
              width: 80%;
              margin: 0 auto;
              height: 60%;
              padding: 2px;
              .img {
                height: 100px;
                width: 100%;
                top: 100%;
                img {
                  height: 100%;
                  width: 100%;
                }
              }
            }
            .name {
              margin: 10px 0px;
            }
            .total {
              color: #dcc593;
              font-size: 16px;
              font-weight: bold;
            }
          }
          .grid_league_content_right {
            background: url("./../../assets/images/index/网格员-bg.png")
              no-repeat center center / 100% 100%;
            height: 100%;
            text-align: center;
            display: flex;
            flex-wrap: wrap;
            padding-top: 5px;
            > div {
              height: 50%;
              width: 33%;
              .imgbox {
                height: 66px;
                padding: 1% 6% 0;
                width: 70%;
                margin: 0 auto;
                position: relative;
                z-index: 3;
                .name {
                  position: absolute;
                  bottom: 0;
                  left: 0;
                  z-index: 10;
                  height: 20px;
                  line-height: 20px;
                  width: 100%;
                  text-align: center;
                  background-color: rgba(0, 0, 0, 0.5);
                }
                img {
                  width: 100%;
                  height: 100%;
                  &:nth-of-type(3) {
                    height: 30%;
                  }
                }
              }
              .total {
                color: #8ec3f1;
                margin-top: 8%;
              }
            }
          }
        }
      }
    }
  }
}
.communityGlory {
  height: 25%;
  color: #ffffff;
}
.eventsNum {
  height: 30%;
  width: 98%;
  .eventsNumContent {
    height: calc(100% - 25px);
    padding-left: 20px;
    .contentTitle {
      font-size: 14px;
      font-family: YouSheBiaoTiHei;
      font-weight: 400;
      color: #9cd0e4;
      line-height: 29px;
      margin-top: 4%;
      text-align: center;
      background: linear-gradient(0deg, #92a9ba 0%, #ffd09b 0%, #fffefb 100%);
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
    }
    /deep/.el-carousel {
      .el-carousel__arrow {
        height: 20px;
        width: 30px;
        i {
          display: none;
        }
      }
      .el-carousel__arrow--left {
        background: url("./../../assets/images/home/翻页.png") no-repeat center
          center / 100% 100%;
      }
      .el-carousel__arrow--right {
        background: url("./../../assets/images/home/翻页-r.png") no-repeat
          center center / 100% 100%;
      }
    }
    .bg {
      background-color: transparent;
      opacity: 0.2;
      .card {
        background: url("./../../assets/images/home/网格分布-f.png") no-repeat
          center center / 100% 100%;
        height: 100%;
        width: 100%;
      }
      &.is-active {
        background-color: transparent !important;
        opacity: 1;
        transform: translateX(80.25px) scale(1) !important;
        width: 65%;
        .card {
          background: url("./../../assets/images/home/网格分布-f.png") no-repeat
            center center / 100% 100%;
          height: 100%;
          width: 100%;
          padding: 20px;
          img {
            height: 100%;
            width: 100%;
          }
        }
      }
    }
  }
}
.motai {
  /deep/.el-dialog {
    background-color: transparent;
    height: 100%;
    position: fixed;
    left: 50%;
    transform: translateX(-50%);
    border: none;
    .motaibox {
      position: relative;
    }
    .colse {
      cursor: pointer;
      color: #fff;
      font-size: 26px;
      font-weight: bold;
      position: absolute;
      right: 1%;
      top: 1%;
    }
    .el-dialog__header {
      display: none;
    }

    .el-dialog__body {
      padding: 0px !important;
    }

    .motai_father {
      width: 100%;
      height: 100%;
      position: relative;
      .btnleft {
        position: absolute;
        top: 50%;
        left: 2%;
      }
      .btnright {
        position: absolute;
        top: 50%;
        right: 2%;
      }
      img {
        height: 100%;
        width: 100%;
      }
    }
  }
}
.motaibox2 {
  .motai_father {
    width: 100%;
    height: 100%;
    position: relative;
    .btnleft {
      position: absolute;
      top: 50%;
      left: 2%;
    }
    .btnright {
      position: absolute;
      top: 50%;
      right: 2%;
    }
    img {
      width: 100%;
      height: 100%;
    }
  }
}
</style>

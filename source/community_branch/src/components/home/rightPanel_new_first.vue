<template>
  <div class="Main"
       ref="MainView">
    <div class="rightBox">
      <!-- 社区简介 -->
      <div class="communityProfile">
        <el-col :span="24"
                class="car-boxTitle"
                style="margin-top:10px">
          <span>社区简介</span>
           <span>COMMUNITY PROFILE</span>
        </el-col>

        <div class="specialPer">
          <div class="specialPertext">{{ communityDescription }}</div>
        </div>
      </div>

      <div class="communityGlory">
        <el-row>
          <el-col :span="24"
                  class="car-boxTitle">
            <span>社区光荣榜</span>
             <span>COMMUNITY HALL OF FAME</span>
          </el-col>
        </el-row>

        <el-row class="communityGloryRow">
          <el-row class="gloryContent">
            <div class="outer-container">
              <div class="inner-container"
                   style="margin-top: 7px;">
                <el-row v-for="(item, index) in communityHonor"
                        :key="index"
                        class="specialPerLi">

                  <el-col :span="3"
                          class="honorXuhao">
                    <span>{{index +1}}</span>
                  </el-col>
                  <el-col :span="21"
                          class="honorContent">{{ item.honor }}</el-col>
                </el-row>
              </div>
            </div>

          </el-row>

        </el-row>

      </div>
      <!-- 网格分布 -->
      <div class="eventsNum">

        <el-row>
          <el-col :span="24"
                  class="car-boxTitle">
            <span>网格分布</span>
             <span>THE GRID DISTRIBUTION </span>
          </el-col>
        </el-row>

        <el-row class="eventsNumRow">
          <el-row class="eventsContent">
            <div class="outer-container">
              <div class="inner-container"
                   style="margin-top: 20px; margin-bottom: -10px;">
                <el-row style="height: 15px; line-height: 1px;"
                        class="eventsContentLi">
                  <el-col :span="5">网格名称</el-col>
                  <el-col :span="10">区域</el-col>
                  <el-col :span="3">面积</el-col>
                  <el-col :span="3">网格员</el-col>
                </el-row>
                <el-row v-for="(item, index) in personnelDistribution"
                        :key="index"
                        class="eventsContentLi">

                  <el-col :span="5">
                    <span>{{item.gridName}}</span>
                  </el-col>
                  <el-col :span="10">{{ item.range }}</el-col>
                  <el-col :span="3">{{ item.area }}</el-col>
                  <el-col :span="3">{{ item.gridMember }}</el-col>
                  <div class="gridLine"
                       style="border-top: 1px dashed #236BBE;
                        margin-top: -10px; margin-left: -10px; border-sizing：border-box;"></div>
                </el-row>
              </div>
            </div>

          </el-row>

        </el-row>

      </div>

    </div>
  </div>
</template>
<script>
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
import populationFlowChart from "@/components/echarts/main/populationFlow";
import travelStatisticsChart from "@/components/echarts/main/travelStatistics";
import { getSpecialPersonNum } from "@/api/homePage/specialPerson";
import { getCareEventNum } from "@/api/homePage/careEventNum";
export default {
  components: { populationFlowChart, travelStatisticsChart },
  data () {
    return {
      that: this,
      centContainerHeight: "100px",
      specialNums: [],
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
      communityDescription: "社区简介",
      communityDescriptionImage: null,
      communityDescriptionImagefit: "contain",
      communityHonor: [], //社区荣誉
      personnelDistribution: [] //人员分布 网格 常驻人口 党员
    };
  },
  created () { },
  filters: {},
  computed: {
    ...mapGetters(["currentComunity"])
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        this.getData();
        // console.log(val.communityId, 'val')
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
    areaFormat (row, col, cell) {
      return cell && cell.length > 0 ? cell + " km²" : "";
    },
    refreshCenterContainerHeight () {
      this.centContainerHeight =
        this.$refs.MainView.offsetHeight - 72 - 331 - 4 + "px";
    },
    getRowClass ({ row, column, rowIndex, columnIndex }) {
      return "background:#222751;color:#fff;border-bottom-color:#182751;";
    },
    getData () {
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
    }
  },
  mounted () {
    this.communityDescription = this.testData.shouye.communityDescription;
    this.communityDescriptionImage = this.testData.shouye.communityDescriptionImage;
    this.communityDescriptionImagefit = this.testData.shouye.communityDescriptionImagefit;
    this.communityHonor = this.testData.shouye.communityHonor;
    this.personnelDistribution = this.testData.renyuan.peopleHousingInfoList;
    // this.tu()


    this.getData();
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
.specialPer {
  height: calc(100% - 0px);
  background: url(../../assets/images/index/社区简介.png) no-repeat center
    center;
  background-size: 100% 100%;
  color: #d3e3ec;
  font-size: 12px;
  padding: 5% 5% 0;
}
</style>

<template>
  <div class="main"
       ref="MainView">
    <el-scrollbar style="height:100%"
                  wrap-class="scrollbar-wrapper "
                  wrap-style="overflow-x:hidden;">
      <el-row>
        <div class="title1Contain textLeft">
          <i class="icon iconfont  iconshuxian-copy title1Icon"></i>
          <span class="title1 VerticalCenter">关爱人员</span>
        </div>
        <el-table stripe
                  :show-header="false"
                  :data="caringPersonTypeStatistic"
                  :height="dataHeight">
          <el-table-column label="简要信息"
                           fit
                           align="center"
                           class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-row class="textLeft HorizontalMargin">
                <el-col :span="14">
                  <div class="tableTitle   HorizontalLeft">
                    <i class="icon iconfont  iconguangquanfangda"></i>
                    {{scope.row.typeName}}
                  </div>
                </el-col>
                <el-col :span="10">
                  <div class="value   HorizontalRight">
                    {{scope.row.value}}
                  </div>
                </el-col>
              </el-row>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-row>
        <div class="title1Contain textLeft">
          <i class="icon iconfont  iconshuxian-copy title1Icon"></i>
          <span class="title1 VerticalCenter">社区内组织</span>
        </div>
        <el-table stripe
                  :show-header="false"
                  :data="groupTypeStatistic"
                  :height="dataHeight">
          <el-table-column label="简要信息"
                           fit
                           align="center"
                           class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-row class="textLeft HorizontalMargin">
                <el-col :span="14">
                  <div class="tableTitle   HorizontalLeft">
                    <i class="icon iconfont  iconguangquanfangda"></i>
                    {{scope.row.typeName}}
                  </div>
                </el-col>
                <el-col :span="10">
                  <div class="value   HorizontalRight">
                    {{scope.row.value}}
                  </div>
                </el-col>
              </el-row>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-row>
        <div class="title1Contain textLeft">
          <i class="icon iconfont  iconshuxian-copy title1Icon"></i>
          <span class="title1 VerticalCenter">特殊人员</span>
        </div>
        <el-table stripe
                  :show-header="false"
                  :data="specialPersonTypeStatistic"
                  :height="dataHeight">
          <el-table-column label="简要信息"
                           fit
                           align="center"
                           class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-row class="textLeft HorizontalMargin">
                <el-col :span="14">
                  <div class="tableTitle   HorizontalLeft">
                    <i class="icon iconfont  iconguangquanfangda"></i>
                    {{scope.row.typeName}}
                  </div>
                </el-col>
                <el-col :span="10">
                  <div class="value   HorizontalRight">
                    {{scope.row.value}}
                  </div>
                </el-col>
              </el-row>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-row class="personCheckIn">
        <div class="title1Contain textLeft">
          <i class="icon iconfont  iconshuxian-copy title1Icon"></i>
          <span class="title1 VerticalCenter">房屋入住率</span>
        </div>
        <div class="water_div">
          <water :data="livePersonTypeStatistic"
                 :height="'200px'" />
          <!-- </div> -->
          <!-- <div> -->
          <span class="title1 VerticalCenter">{{livePersonTypeStatistic.value}}</span>
          <span class="title1 VerticalCenter HorizontalMargin">/</span>
          <span class="title1 VerticalCenter">{{livePersonTypeStatistic.total}}</span>
        </div>
      </el-row>
    </el-scrollbar>
  </div>
</template>
<script>

import water from '@/components/dashboard/water'
import { countHouse, countSpecial, getCommunityOrg, getcaringType, } from "@/api/home";

export default {
  components: { water },
  data () {
    return {
      that: this,
      formHeight: 50,
      //关爱人员分类统计
      caringPersonTypeStatistic: [],
      //社会组织分类统计
      groupTypeStatistic: [],
      //特殊人员分类统计
      specialPersonTypeStatistic: [],
      //居住人口分类  本地/外地
      livePersonTypeStatistic: {
        type: '入住比率',
        value: 0,
        total: 0,
        ratio: 0
      },
      // 特殊人员字典
      specialOptions: [],
    }
  },
  created () {
    // this.getTestData()
    this.getDicts("base_special_person").then(response => {
      let temp = response.data
      temp = temp.filter(item => {
        return item.dictValue !== '0'
      })
      this.specialOptions = temp;

    });
  },
  filters: {},
  methods: {
    // 特殊人员字典翻译
    specialFormat (value) {
      return this.selectDictLabel(this.specialOptions, value);
    },
    getTestData () {
      this.caringPersonTypeStatistic = [
        {
          type: '01',
          typeName: '孤寡老人',
          value: 31,
        },
        {
          type: '01',
          typeName: '孕妇',
          value: 5,
        },
        {
          type: '01',
          typeName: '残障人员',
          value: 7,
        },
        {
          type: '01',
          typeName: '因公牺牲军属',
          value: 2,
        }
      ]
      this.groupTypeStatistic = [
        {
          type: '01',
          typeName: '综治机构',
          value: 1,
        },
        {
          type: '01',
          typeName: '群防群治组织',
          value: 5,
        },
        {
          type: '01',
          typeName: '社会组织',
          value: 7,
        },
        {
          type: '01',
          typeName: '非公经济组织',
          value: 2,
        },
        {
          type: '01',
          typeName: '境外非政府组织',
          value: 0,
        }
      ]
      this.specialPersonTypeStatistic = [
        {
          type: '01',
          typeName: '刑满释放人员',
          value: 2,
        },
        {
          type: '01',
          typeName: '社区矫正人员',
          value: 5,
        },
        {
          type: '01',
          typeName: '肇事肇祸等严重精神障碍患者',
          value: 1,
        },
        {
          type: '01',
          typeName: '吸毒人员',
          value: 0,
        }, {
          type: '01',
          typeName: '艾滋病危险人员',
          value: 0,
        }, {
          type: '01',
          typeName: '重点青少年',
          value: 2,
        },
      ]
      this.livePersonTypeStatistic = {
        type: '入住比率',
        value: 580,
        total: 640,
        ratio: 90.63
      }

    },
    refreshData () {
      //获取房屋入住率
      countHouse().then(response => {
        if (response && response.data && response.data.houseNum > 0) {
          this.livePersonTypeStatistic.total = response.data.houseNum
          this.livePersonTypeStatistic.ratio = response.data.liveRate * 100
          this.livePersonTypeStatistic.value = Math.round(response.data.houseNum * response.data.liveRate)
        } else {
          this.livePersonTypeStatistic = {
            type: '入住比率',
            value: 0,
            total: 0,
            ratio: 0
          }
        }
      });
      //关爱人员
      getcaringType().then(response => {
        if (response && response.data && response.data.length > 0) {
          this.caringPersonTypeStatistic = []
          response.data.forEach(element => {
            this.caringPersonTypeStatistic.push({
              typeName: element.name,
              value: element.num,
            })
          });
        } else {
          this.caringPersonTypeStatistic = [
            {
              typeName: '无数据',
              value: '',
            }
          ]
        }
      });
      //社区内组织
      getCommunityOrg().then(response => {
        if (response && response.data && response.data.length > 0) {
          this.groupTypeStatistic = []
          response.data.forEach(element => {
            this.groupTypeStatistic.push({
              typeName: element.name,
              value: element.num,
            })
          });
        } else {
          this.groupTypeStatistic = [
            {
              typeName: '无数据',
              value: '',
            }
          ]
        }
      });
      //特殊人员
      countSpecial().then(response => {
        // data:
        // aidsNum: 0
        // correctNum: 0
        // drugNum: 0
        // mentalNum: 0
        // releaseNum: 4
        if (response && response.data) {
          let keys = Object.keys(response.data)
          if (keys && keys.length > 0) {
            keys.forEach(key => {
              if (response.data[key] > 0) {
                let name = this.specialFormat(key + '')
                this.specialPersonTypeStatistic.push({
                  typeName: name,
                  value: response.data[key],
                })
              }
            });
          }
        }
        else {
          this.specialPersonTypeStatistic = [
            {
              typeName: '无数据',
              value: '',
            }
          ]
        }
      });

    }
  },
  mounted () {
    this.$nextTick(() => {
      this.formHeight = 27.3 + 27.3 + 27.3 + 27.3 + 250 + 20
      this.refreshData()
    })
  },
  computed: {
    dataHeight () {
      let tableHeight = this.$store.getters.dataEleHeight - this.formHeight - 100
      if (tableHeight < 80) tableHeight = 80
      return tableHeight / 3
    }
  },
}
</script>
<style lang="scss" scoped>
@import "../../assets/styles/table.scss";
.main {
  padding: 0px 24px;
  width: 100%;
  height: 100%;
  flex-flow: row nowrap;
  overflow-x: hidden;
  overflow-y: auto;
}
.title1Icon {
  color: #00c0ff;
  font-size: 24px;
}
.title1Contain {
  margin-top: 20px;
  margin-bottom: 10px;
}
.title1 {
  height: 22px;
  font-size: 16px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: #000000;
  line-height: 22px;
}
.title {
  margin-left: 30px;
  font-size: 14px;
  // color: #ffffff;
  font-weight: lighter;
}
.tableTitle {
  margin: 0 auto 3px auto;
  text-align: center;
  color: #999999;
}
.personCheckIn {
  width: 100%;
  height: 250px;
  // background-color: #ffffff;
}

.el-scrollbar {
  height: 100%;
  .el-scrollbar__wrap {
    overflow-x: hidden !important;
  }
}
.el-scrollbar__wrap {
  overflow-x: hidden !important;
}
.water_div {
  background-color: #ffffff;
  padding-bottom: 8px;
}
</style>
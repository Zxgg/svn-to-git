<template>
  <div :class="className" :style="{ height: height, width: width }" id="main" />
</template>

<script>
import echarts from "echarts";
require("echarts/theme/macarons"); // echarts theme
import resize from "../mixins/resize";
import bus from "../../../assets/eventbus";
// import { getpeovehiNum } from "@/api/trafficData/vehipeoData.js";
import {getEventDistribution} from "../../../api/communityEvent/community_event.js"
const animationDuration = 6000;

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: "chart"
    },
    width: {
      type: String,
      default: "90%"
    },
    height: {
      type: String,
      default: "100%"
    },
    currentComunity: {
      communityId: String,
      communityChildId: String
    }
  },
  data() {
    return {
      chart: null,
      datas: null,
      xdata: [
        "网格一",
        "网格二",
        "网格三",
        "网格四",
        "网格五",
        "网格六",
        "网格七",
        "网格八",
        "网格九",
        "网格十",
        "网格十一"
      ]
    };
  },
  created() {},
  mounted() {
    this.getEventDistribution_("民生服务")
    this.$Notice.$on("get", msg=> {
      this.getEventDistribution_(msg)
    });
  },
  beforeDestroy() {
    if (!this.chart) {
      return;
    }
    this.chart.dispose();
    this.chart = null;
  },
  methods: {
    getEventDistribution_(msg){
     let query = {
        communityId:"0355931e-a70b-11eb-a21e-0050569ba051",
        eventType:msg
      }
    getEventDistribution(query).then(res=>{
      if(res){
        console.log(res.data,",====");
       this.datas=res.data
       console.log(this.datas,'123');
       this.initChart();
      }
     })
    },
    getdatas() {
      let datam = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
      if( !this.datas){
        return datam
      }
      this.datas.forEach(val => {
        if (val.division === "第一网格") {
          datam[0] = val.num;
        }
        if (val.division === "第二网格") {
          datam[1] = val.num;
        }
        if (val.division === "第三网格") {
          datam[2] = val.num;
        }
        if (val.division === "第四网格") {
          datam[3] = val.num;
        }
        if (val.division === "第五网格") {
          datam[4] = val.num;
        }
        if (val.division === "第六网格") {
          datam[5] = val.num;
        }
        if (val.division === "第七网格") {
          datam[6] = val.num;
        }
        if (val.division === "第八网格") {
          datam[7] = val.num;
        }
        if (val.division === "第九网格") {
          datam[8] = val.num;
        }
        if (val.division === "第十网格") {
          datam[9] = val.num;
        }
        if (val.division === "第十一网格") {
          datam[10] = val.num;
        }
      });
       return datam
    },
    initChart() {
      let data;
      data = this.getdatas();
      this.chart = echarts.init(this.$el, "macarons");

      //第二种表格图
      //      let dom = 100;
      //      let  barWidth = dom / 20;
      //      let colors = [{
      //         type: 'linear',
      //         x: 0,
      //         x2: 1,
      //         y: 0,
      //         y2: 0,
      //         colorStops: [{
      //             offset: 0,
      //             color: "#2D7B7C"
      //         }, {
      //             offset: 0.5,
      //             color: "#2D7B7C"
      //         }, {
      //             offset: 0.5,
      //             color: "#2D7B7C"
      //         }, {
      //             offset: 1,
      //             color: "#2D7B7C"
      //         }]
      //     }
      // ];
      //       this.chart.setOption(
      //       {
      //       backgroundColor: "#010d3a",
      //     //标题
      //     title: {
      //         text: '志愿项目服务时长变化趋势',
      //         textStyle: {
      //             color: '#00FFFF',
      //             fontWeight: '800',
      //             fontSize: `20`,
      //         },
      //         left: 'center',
      //         top: '5%'
      //     },
      //     //提示框
      //     tooltip: {
      //         trigger: 'axis',
      //         formatter: "{b} : {c}",
      //         axisPointer: { // 坐标轴指示器，坐标轴触发有效
      //             type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
      //         }
      //     },
      //     /**区域位置*/
      //         grid: {
      //           top: "20",
      //           left: "20",
      //           right: "0",
      //           bottom: "20"
      //           // containLabel: true
      //         },
      //     /**图例大小*/
      //          legend: {
      //           type: 'plain',
      //           left: 0,
      //           top: 20,
      //           itemGap: 20,
      //           itemWidth: 35,
      //           itemHeight: 20
      //         },
      //     //X轴
      //     xAxis: {
      //         name:'X',
      //          nameTextStyle: {
      //             color: '#FFFFFF',
      //             padding: [0, 0, 0, 20]

      //           },
      //         data: this.xData,
      //         show: true,
      //         type: 'category',
      //         axisLine: {
      //             show: true,
      //             lineStyle: {
      //                 color: 'rgba(255,255,255,1)',
      //                  shadowColor:'rgba(255,255,255,1)',
      //                 shadowOffsetX: '20',
      //             },
      //             symbol: ['none', 'arrow'],
      //             symbolOffset: [0, 25]
      //         },
      //         splitLine: {
      //             show: true,
      //             lineStyle: {
      //                 color: 'rgba(255,255,255,0.2)'
      //             },
      //         },
      //         axisLabel: {
      //             rotate: -30,
      //             fontSize: 12,
      //         },
      //     },
      //     yAxis: {
      //         show: true,
      //         splitNumber: 4,
      //         axisLine: {
      //             show: true,
      //             lineStyle: {
      //                 color: 'rgba(255,255,255,0.2)'
      //             },
      //         },
      //         splitLine: {
      //             show: true,
      //             lineStyle: {
      //                 color: 'rgba(255,255,255,0.2)'

      //             },
      //         },
      //         axisLabel: {
      //             color: '#FFFFFF',
      //         }
      //     },
      //     series: [{
      //             type: 'bar',
      //             barWidth: barWidth,
      //             itemStyle: {
      //                 normal: {
      //                     color: colors
      //                     // function(params) {
      //                     //     return colors[params.dataIndex % 7];
      //                     // }
      //                 }
      //             },
      //             label: {
      //                 show: true,
      //                 position: [barWidth / 2, -(barWidth + 20)],
      //                 // color: '#ffffff',
      //                 fontSize: 12,
      //                 fontStyle: 'bold',
      //                 align: 'center',
      //             },
      //             data: data
      //         },
      //         {
      //             z: 2,
      //             type: 'pictorialBar',
      //             data: data,
      //             symbol: 'diamond',
      //             symbolOffset: [0, '50%'],
      //             symbolSize: [barWidth, barWidth * 0.5],
      //             itemStyle: {
      //                 normal: {
      //                     color: colors
      //                     // function(params) {
      //                     //     return colors[params.dataIndex % 7];
      //                     // },
      //                 }
      //             },
      //         },
      //         {
      //             z: 3,
      //             type: 'pictorialBar',
      //             symbolPosition: 'end',
      //             data: data,
      //             symbol: 'diamond',
      //             symbolOffset: [0, '-50%'],
      //             symbolSize: [barWidth, barWidth * 0.5],
      //             itemStyle: {
      //                 normal: {
      //                     borderWidth: 0,
      //                    color: colors[0].colorStops[0].color
      //                     // function(params) {
      //                     //     return colors[params.dataIndex % 7].colorStops[0].color;
      //                     // },

      //                 }
      //             },
      //         },
      //         // {
      //         //     z: 4,
      //         //     type: 'pictorialBar',
      //         //     symbolPosition: 'end',
      //         //     data: data,
      //         //     symbol: 'circle',
      //         //     symbolOffset: [0, -barWidth],
      //         //     symbolSize: [barWidth * 0.5, barWidth * 0.5],
      //         //     itemStyle: {
      //         //         normal: {
      //         //             borderWidth: 0,
      //         //             color: function(params) {
      //         //                 return colors[params.dataIndex % 7].colorStops[0].color;
      //         //             },

      //         //         }
      //         //     },
      //         // },
      //     ],
      // }
      //       )
      // 第一种
      this.chart.setOption({
        backgroundColor: "transparent",
        grid: {
          top: "24",
          left: "20",
          right: "0",
          bottom: "20"
          // containLabel: true
        },
        tooltip: {
          trigger: "axis",
          formatter: "{b} : {c}",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        xAxis: {
          data: this.xdata,
          //坐标轴
          show: true,
          splitArea: { show: false },
          splitLine: {
            show: false
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#3eb2e8"
            }
          },

          //坐标值标注
          axisLabel: {
            interval: 0,

            show: true,
            textStyle: {
              color: "#2ec7c9",
              fontSize: "14px"
            }
          }
        },
        yAxis: {
          //坐标轴
          axisLine: {
            show: false
          },
          splitArea: { show: false },
          // splitLine: {
          //   show: false
          // },
          //   splitArea : {show : false},
          //坐标值标注
          axisLabel: {
            show: false
          },
          //分格线
          splitLine: {
            show: false
          }
        },
        series: [
          {
            name: "a",
            tooltip: {
              show: false
            },
            type: "bar",
            barWidth: 8,
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(
                  0,
                  1,
                  0,
                  0,
                  [
                    {
                      offset: 0,
                      color: "#2D7B7C" // 0% 处的颜色
                    },
                    {
                      offset: 0.6,
                      color: "#20646F" // 60% 处的颜色
                    },
                    {
                      offset: 1,
                      color: "#44B3AC" // 100% 处的颜色
                    }
                  ],
                  false
                )
              }
            },
            data: data,
            barGap: 0
          },
          {
            type: "bar",
            barWidth: 7,
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(
                  0.1,
                  1,
                  0,
                  3,
                  [
                    {
                      offset: 0,
                      color: "#2D7B7C" // 0% 处的颜色
                    },
                    {
                      offset: 0.6,
                      color: "#20646F" // 60% 处的颜色
                    },
                    {
                      offset: 1,
                      color: "#44B3AC" // 100% 处的颜色
                    }
                  ],
                  true
                )
              }
            },
            barGap: 0,
            data: data
          },
          {
            name: "b",
            tooltip: {
              show: true
            },
            type: "pictorialBar",
            itemStyle: {
              borderWidth: 1,
              borderColor: "#44b3ac",
              color: "#44b3ac",
              normal: {
                label: {
                  show: true, //开启显示
                  position: "top", //在上方显示
                  textStyle: {
                    //数值样式
                    color: "#44b3ac",
                    fontSize: 12
                  }
                }
              }
            },
            symbol: "path://M 0,0 l 120,0 l -30,60 l -120,0 z",
            symbolSize: ["0", "9"],
            symbolOffset: ["1", "-5"],
            symbolRotate: -5,

            symbolPosition: "end",
            data: data,
            z: 3
          }
        ]
      });
      // }
    }
    // getpeovehiNumData() {
    //   this.data = [];
    //   let communityId = this.currentComunity.communityId;
    //   let communityChildId = this.currentComunity.communityChildId;
    //   getpeovehiNum(communityId, communityChildId).then(res => {
    //     if (res && res.data) {
    //       this.data = res.data;
    //       console.log(this.data, "this.data");
    //       this.$nextTick(() => {
    //         this.initChart();
    //       });
    //     }
    //   });
    // }
  }
};
</script>
<style lang="scss" scoped></style>

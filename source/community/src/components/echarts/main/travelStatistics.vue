<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getTravel } from "@/api/homePage/travelStatistics"
const animationDuration = 6000

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '100%'
    },
    currentComunity: {
      communityId: String,
      communityChildId: String
    }
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        this.getTravelData()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      personList: [],
      vehicleList: [],
      visitPersonList: [],
      visitVehicleList: []
    }
  },
  mounted () {
    this.getTravelData()
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy () {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart () {
      this.chart = echarts.init(this.$el, 'macarons');
      this.chart.setOption({
        tooltip: {
          trigger: 'axis'
        },
        title: {
          right: '10%',
          text: '天/24h',
          top: '10%',
          textStyle: {
            color: '#fff',
            fontStyle: 'normal',
            fontFamily: '微软雅黑',
            fontSize: 12,
          },
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '13%',
          height: '70%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,//坐标轴两边留白
          data: ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23'],
          axisLabel: { //坐标轴刻度标签的相关设置。
            interval: 0,//设置为 1，表示『隔一个标签显示一个标签』
            //	margin:15,
            textStyle: {
              color: '#fff',
              fontStyle: 'normal',
              fontFamily: '微软雅黑',
              fontSize: 12,
            },
            formatter: function (params) {
              var newParamsName = "";
              var paramsNameNumber = params.length;
              var provideNumber = 4;  //一行显示几个字
              var rowNumber = Math.ceil(paramsNameNumber / provideNumber);
              if (paramsNameNumber > provideNumber) {
                for (var p = 0; p < rowNumber; p++) {
                  var tempStr = "";
                  var start = p * provideNumber;
                  var end = start + provideNumber;
                  if (p == rowNumber - 1) {
                    tempStr = params.substring(start, paramsNameNumber);
                  } else {
                    tempStr = params.substring(start, end) + "\n";
                  }
                  newParamsName += tempStr;
                }

              } else {
                newParamsName = params;
              }
              return newParamsName
            },
            //rotate:50,
          },
          axisTick: {//坐标轴刻度相关设置。
            show: false,
          },
          axisLine: {//坐标轴轴线相关设置
            lineStyle: {
              color: '#E5E9ED',
              // opacity:0.2
            }
          },
          splitArea: { show: false },//设置网格区域
          splitLine: { //坐标轴在 grid 区域中的分隔线。
            show: false,
            lineStyle: {
              color: '#E5E9ED',
              // 	opacity:0.1
            }
          }
        },
        yAxis: [
          {
            type: 'value',
            splitNumber: 5,
            axisLabel: {
              textStyle: {
                color: '#fff',
                fontStyle: 'normal',
                fontFamily: '微软雅黑',
                fontSize: 12,
              }
            },
            axisLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            splitArea: { show: false },//设置网格区域
            splitLine: {
              show: false,
              lineStyle: {
                color: '#E5E9ED',
                // 	opacity:0.1
              }
            }

          }
        ],
        series: [
          {
            name: '居住人员',
            type: 'line',
            smooth: false,
            itemStyle: {
              normal: {
                color: '#FA6400',
                lineStyle: {
                  color: "#FA6400",
                  width: 1
                },
                areaStyle: {
                  color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                    offset: 0,
                    color: 'rgba(250,100,0,0)'
                  }, {
                    offset: 1,
                    color: 'rgba(250,100,0,0.7)'
                  }]),
                }
              }
            },
            data: this.personList
          },
          {
            name: '访客人员',
            type: 'line',
            smooth: false,
            itemStyle: {
              normal: {
                color: 'rgba(247,181,0,1)',
                lineStyle: {
                  color: "rgba(247,181,0,1)",
                  width: 1
                },
                areaStyle: {
                  color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                    offset: 0,
                    color: 'rgba(247,181,0,0)'
                  }, {
                    offset: 1,
                    color: 'rgba(247,181,0,0.7)'
                  }]),
                }
              }
            },
            data: this.visitPersonList
          },
          {
            name: '居住车辆',
            type: 'line',
            smooth: false,
            itemStyle: {
              normal: {
                color: 'rgba(0,255,198,1)',
                lineStyle: {
                  color: "rgba(0,255,198,1)",
                  width: 1
                },
                areaStyle: {
                  color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                    offset: 0,
                    color: 'rgba(0,255,198,0)'
                  }, {
                    offset: 1,
                    color: 'rgba(0,255,198,0.7)'
                  }]),
                }
              }
            },
            data: this.vehicleList
          },
          {
            name: '访客车辆',
            type: 'line',
            smooth: false,
            itemStyle: {
              normal: {
                color: 'rgba(0,220,255,1)',
                lineStyle: {
                  color: "rgba(0,220,255,1)",
                  width: 1
                },
                areaStyle: {
                  color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                    offset: 0,
                    color: 'rgba(0,220,255,0)'
                  }, {
                    offset: 1,
                    color: 'rgba(0,220,255,0.7)'
                  }]),
                }
              }
            },
            data: this.visitVehicleList
          }
        ]
      })
    },
    getTravelData () {
      // let aData = new Date();
      // let value = aData.getFullYear() + "-" + (aData.getMonth() + 1) + "-" + aData.getDate();
      // let type = 2
      this.personList = []
      this.vehicleList = []
      this.visitPersonList = []
      this.visitVehicleList = []
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getTravel(communityId, communityChildId).then((res) => {
        // console.log(res, '123545sadsa')
        if (res && res.data) {
          res.data.personList.forEach(person => {
            this.personList.push(person.num)
          });
          res.data.vehicleList.forEach(vehicle => {
            this.vehicleList.push(vehicle.num)
          });
          res.data.visitPersonList.forEach(visitPerson => {
            this.visitPersonList.push(visitPerson.num)
          });
          res.data.visitVehicleList.forEach(visitVehic => {
            this.visitVehicleList.push(visitVehic.num)
          });
        }
        this.$nextTick(() => {
          this.initChart()
        })
      })
    },
  }
}
</script>

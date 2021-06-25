<template>
  <div :class="className"
       id="chart"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getVehicleTypeNum } from "@/api/homePage/vehicleTypeNum"
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
      default: '110%'
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
        this.getVehicleNumData()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      vehicleNum: {
        localVehicle: 0,
        nonlocalVehicle: 0
      }
    }
  },
  mounted () {
    this.getVehicleNumData()
    // this.$nextTick(() => {
    //   this.initChart()
    // })
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
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        tooltip: {
          show: false,
          formatter: "{b} <br> {c}%",
          trigger: 'item',
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          top: '5%',
          left: '32% ',
          itemWidth: 7,
          itemHeight: 7,
          textStyle: {
            color: '#89A7AF',
          },
          data: [{
            name: '本地车辆'
          },
          {
            name: '外地车辆'
          }]
        },
        grid: {
          containLabel: true,
          height: '70%',
          y: "10%",
          right: "20%"
        },
        xAxis: [{
          type: 'value',
          axisTick: {
            show: false,
          },
          axisLine: {
            show: false,
          },
          axisLabel: {
            show: false
          },
          splitLine: {
            show: false,
          },
          splitArea: { show: false }
        }],
        yAxis: [{
          //type: 'category',
          data: [''],
          axisTick: {
            show: false,
          },
          axisLine: {
            show: false,
          },
          axisLabel: {
            textStyle: {
              color: '#fff',
            }
          },
          splitArea: { show: false }
        }],
        series: [
          {
            name: '本地车辆',
            type: 'bar',
            barWidth: '25%',
            stack: '危货种类占比',
            label: {
              normal: {
                borderWidth: 10,
                distance: 20,
                align: 'center',
                verticalAlign: 'middle',
                borderRadius: 1,
                borderColor: '#00B3FF',
                backgroundColor: '#00B3FF',
                show: true,
                // position: 'top',
                formatter: '{c}%',
                color: '#fff'
              }
            },
            itemStyle: {
              color: '#00B3FF'
            },
            data: [{
              value: this.vehicleNum.localVehicle.toFixed(2),
              itemStyle: {
                normal: {
                  color: {
                    type: 'bar',
                    colorStops: [{
                      offset: 0,
                      color: '#00B3FF' // 0% 处的颜色
                    }, {
                      offset: 1,
                      color: '#00B3FF' // 100% 处的颜色
                    }],
                    globalCoord: false, // 缺省为 false

                  }
                }
              }
            }]
          },
          {
            name: '本地车三角形',
            type: 'line',
            barWidth: 5,
            markPoint: {
              symbol: 'triangle',
              symbolRotate: '180',
              itemStyle: {
                color: {
                  type: 'linear',
                  x: 0,
                  y: 0,
                  x2: 1,
                  y2: 0,
                  colorStops: [{
                    offset: 0, color: '#00B3FF' // 0% 处的颜色
                  }, {
                    offset: 1, color: '#00B3FF' // 100% 处的颜色
                  }],
                  globalCoord: false // 缺省为 false
                }
              },
              symbolSize: [6, 5],// 容器大小
              symbolOffset: [0, -15],//位置偏移
              data: [{
                coord: [53.11 / 2]
              }],
              label: {
                normal: {
                  show: false
                },
                offset: [0, 0],
              }
            }
          },
          {
            name: '外地车辆',
            type: 'bar',
            stack: '危货种类占比',
            itemStyle: {
              color: '#0364CE'
            },
            label: {
              normal: {
                borderRadius: 1,
                borderColor: '#0364CE',
                backgroundColor: '#0364CE',
                show: true,
                // position: 'top',
                formatter: '{c}%',
                color: '#fff'
              }
            },
            data: [{
              value: this.vehicleNum.nonlocalVehicle.toFixed(2),
              itemStyle: {
                normal: {
                  color: {
                    type: 'bar',
                    colorStops: [{
                      offset: 0,
                      color: '#0364CE' // 0% 处的颜色
                    }, {
                      offset: 1,
                      color: '#0364CE' // 100% 处的颜色
                    }],
                    globalCoord: false, // 缺省为 false

                  }
                }
              }
            }]
          },
          {
            name: '外地车辆三角形',
            type: 'line',
            barWidth: 0,
            markPoint: {
              symbol: 'triangle',
              symbolRotate: '180',
              itemStyle: {
                color: {
                  type: 'linear',
                  x: 0,
                  y: 0,
                  x2: 1,
                  y2: 0,
                  colorStops: [{
                    offset: 0, color: '#0364CE' // 0% 处的颜色
                  }, {
                    offset: 1, color: '#0364CE' // 100% 处的颜色
                  }],
                  globalCoord: false // 缺省为 false
                }
              },
              symbolSize: [6, 5],// 容器大小
              symbolOffset: [0, -15],//位置偏移
              data: [{
                coord: [65.11 + 23 / 2]
              }],
              label: {
                normal: {
                  show: false
                },
                offset: [0, 0],
              }
            }
          }
        ]
      })
    },
    getVehicleNumData () {
      this.vehicleNum.localVehicle = this.testData.shouye.renfangche.vehicleNum.localVehicle
      this.vehicleNum.nonlocalVehicle = this.testData.shouye.renfangche.vehicleNum.nonlocalVehicle
      this.$nextTick(() => {
        this.initChart()
      })
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      // getVehicleTypeNum(communityId, communityChildId).then((res) => {
      //   // console.log(res, '123545sadsa')
      //   if (res && res.data.length > 0) {
      //     let localSum, nonlocalSum
      //     res.data.forEach(data => {
      //       if (data.name === '外地车') {
      //         nonlocalSum = data.num
      //       } else {
      //         localSum = data.num
      //       }
      //     });
      //     let sum = localSum + nonlocalSum
      //     if (localSum != 0)
      //       this.vehicleNum.localVehicle = localSum / sum * 100
      //     if (nonlocalSum != 0)
      //       this.vehicleNum.nonlocalVehicle = nonlocalSum / sum * 100
      //     this.$nextTick(() => {
      //       this.initChart()
      //     })
      //   }

      // })
    },
  }
}
</script>
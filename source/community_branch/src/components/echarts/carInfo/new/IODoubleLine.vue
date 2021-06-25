<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'

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
    data: {
      type: Object,
      default: {
        xAxis: ['10.01', '10.02', '10.03', '10.04', '10.05', '10.06', '10.07'],
        lines: [
          {
            name: '新增流动人口（百）',
            data: [120, 132, 101, 134, 90, 230, 210]
          }, {
            name: '租住人口（个）',
            data: [220, 182, 191, 234, 290, 330, 310]
          }
        ]
      }
    }
  },
  data () {
    return {
      chart: null,
      stack: '总量',
      areaStyle: {
      },
      type: 'line',
      smooth: 0.5,//折线的弧度
      oData: [],
      iData: []
    }
  },
  mounted () {

    this.oData = this.testData.car.vehicleFlowInfo.lines[0].data
    this.iData = this.testData.car.vehicleFlowInfo.lines[1].data
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
  watch: {
    data: {
      handler () {
        this.initChart()
      },
      deep: true
    }
  },
  methods: {
    initChart () {
      this.chart = echarts.init(this.$el, 'macarons')
      let legend = this.data.lines.map(item => item.name)
      let oDataList = []
      for (let i = 7; i >= 1; i--) {
        oDataList.push(this.commonFunction.getLastWeekTime(i))
      }
      let xAxis = oDataList
      // let xAxis = this.data.xAxis
      let color = ["#60DCFF", "#6FF9DF"]
      const hexToRgba = (hex, opacity) => {
        let rgbaColor = "";
        let reg = /^#[\da-f]{6}$/i;
        if (reg.test(hex)) {
          rgbaColor = `rgba(${parseInt("0x" + hex.slice(1, 3))},${parseInt(
            "0x" + hex.slice(3, 5)
          )},${parseInt("0x" + hex.slice(5, 7))},${opacity})`;
        }
        return rgbaColor;
      }
      this.chart.setOption({
        // title: {
        //   text: '堆叠区域图'
        // },
        color: color,
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#6a7985'
            }
          }
        },
        legend: {
          data: legend,
          textStyle: {
            color: '#BDBBBB',
          },
          top: '10%',
          left: 12,
          itemHeight: 0,
          itemWidth: 12
        },
        // toolbox: {
        //   feature: {
        //     saveAsImage: {}
        //   }
        // },
        grid: {
          left: '3%',
          right: '6%',
          bottom: '20%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            boundaryGap: false,
            data: xAxis,
            axisLabel: {
              show: true,
              interval: 0,
              textStyle: {
                color: '#8DA5B8',  //更改坐标轴文字颜色
                fontSize: 12      //更改坐标轴文字大小
              }
            },
            "axisLine": {       //y轴
              "show": false
            },
            //设置刻度线
            axisTick: {
              show: false
            },
          },
        ],
        yAxis: [
          {
            type: 'value',
            axisLabel: {
              show: true,
              textStyle: {
                color: '#8DA5B8',  //更改坐标轴文字颜色
                fontSize: 12      //更改坐标轴文字大小
              }
            },
            //设置网格线颜色
            splitLine: {
              show: false,
              // lineStyle: {
              //   color: ['#979797'],
              //   width: 1,
              //   type: 'solid'
              // }
            },
            "axisLine": {       //y轴
              "show": false
            },
            //设置刻度线
            axisTick: {
              show: false
            },
            splitArea: { show: false }//设置网格区域
          }
        ],
        series: [{
          name: "出",
          type: "line",
          smooth: true,
          // showSymbol: false,/
          symbolSize: 0,
          zlevel: 3,
          lineStyle: {
            normal: {
              color: color[0],
              shadowBlur: 3,
              shadowColor: hexToRgba(color[0], 0.5),
              shadowOffsetY: 1
            }
          },
          areaStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(
                0,
                0,
                0,
                1,
                [{
                  offset: 0,
                  color: hexToRgba(color[0], 0.3)
                },
                {
                  offset: 1,
                  color: hexToRgba(color[0], 0.1)
                }
                ],
                false
              ),
              shadowColor: hexToRgba(color[0], 0.1),
              shadowBlur: 10
            }
          },
          data: this.oData
        }, {
          name: "入",
          type: "line",
          smooth: true,
          // showSymbol: false,
          symbolSize: 0,
          zlevel: 3,
          lineStyle: {
            normal: {
              color: color[1],
              shadowBlur: 3,
              shadowColor: hexToRgba(color[1], 0.5),
              shadowOffsetY: 1
            }
          },
          areaStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(
                0,
                0,
                0,
                1,
                [{
                  offset: 0,
                  color: hexToRgba(color[1], 0.3)
                },
                {
                  offset: 1,
                  color: hexToRgba(color[1], 0.1)
                }
                ],
                false
              ),
              shadowColor: hexToRgba(color[1], 0.1),
              shadowBlur: 10
            }
          },
          data: this.iData
        }]
      })
    }
  }
}
</script>

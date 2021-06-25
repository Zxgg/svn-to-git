<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

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
    }
  },
  mounted () {
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
      let xAxis = this.data.xAxis
      let series = []
      this.data.lines.forEach(element => {
        let itemStyle = {
          normal: {
            color: element.lineColor ? element.lineColor : null, //改变折线点的颜色
            lineStyle: {
              color: element.linePointColor ? element.linePointColor : null //改变折线颜色
            },
          }
        }
        let areaStyle = this.areaStyle
        if (element.lineAreaColor) {
          areaStyle = {
            color: element.lineAreaColor,
            opacity: 0.8
            // origin:'start'
          }
        }
        let smooth = this.smooth
        if (element.smooth !== undefined && element.smooth !== null && element.smooth >= 0 && element.smooth <= 1) {
          smooth = element.smooth
        }
        series.push({
          name: element.name,
          type: this.type,
          smooth: smooth,
          // stack: this.stack,//不适用堆叠模式 
          // areaStyle: areaStyle,
          itemStyle: itemStyle,
          data: element.data
        })
      });
      this.chart.setOption({
        // title: {
        //   text: '堆叠区域图'
        // },
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
            color: '#0091ff',
          },
          top: '10%'
        },
        // toolbox: {
        //   feature: {
        //     saveAsImage: {}
        //   }
        // },
        grid: {
          left: '3%',
          right: '5%',
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
              textStyle: {
                color: '#fff',  //更改坐标轴文字颜色
                fontSize: 10      //更改坐标轴文字大小
              }
            },
          },
        ],
        yAxis: [
          {
            type: 'value',
            axisLabel: {
              show: true,
              textStyle: {
                color: '#fff',  //更改坐标轴文字颜色
                fontSize: 10      //更改坐标轴文字大小
              }
            },
            //设置网格线颜色
            splitLine: {
              show: true,
              lineStyle: {
                color: ['#979797'],
                width: 1,
                type: 'dashed'
              }
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
        series: series
      })
    }
  }
}
</script>

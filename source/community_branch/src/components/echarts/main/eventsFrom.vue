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
      default: '220px'
    }
  },
  data () {
    return {
      chart: null
    }
  },
  mounted () {
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
      this.chart = echarts.init(this.$el, 'macarons')

      this.chart.setOption({
        color: ['#3398DB'],
        tooltip: {
          trigger: 'axis',
          axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '20%',
          top: "18%",
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
            axisTick: {
              alignWithLabel: true
            },
            axisLabel: {
              show: true,
              textStyle: {
                color: '#ccc',  //更改坐标轴文字颜色
                fontSize: 10      //更改坐标轴文字大小
              }
            },
            "axisLine": {       //x轴
              "show": false

            },
            //设置刻度线
            axisTick: {
              show: false
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            axisLabel: {
              show: true,
              textStyle: {
                color: '#ccc',  //更改坐标轴文字颜色
                fontSize: 10      //更改坐标轴文字大小
              }
            },
            "axisLine": {       //y轴
              "show": false

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
            //设置刻度线
            axisTick: {
              show: false
            },
            splitArea: { show: false }//设置网格区域
          },
        ],
        series: [
          {
            name: '直接访问',
            type: 'bar',
            barWidth: '40%',
            data: [108, 252, 200, 334, 390, 330, 220, 123, 132, 256, 283, 321],
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(
                  0, 0, 0, 1,
                  [
                    { offset: 0, color: '#088bc4' },                   //柱图渐变色
                    // { offset: 0.5, color: '#165b8e' },                 //柱图渐变色
                    { offset: 1, color: '#1e3460' },                   //柱图渐变色
                  ]
                )
              },
            },
          },

        ]
      })
    }
  }
}
</script>

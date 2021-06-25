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
        color: ['#006CED', '#01FFFD', '#00FFC6', '#F6B200'],
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          data: [{ name: '东门', textStyle: { color: 'white' } },
          { name: '南门', textStyle: { color: 'white' } },
          { name: '西门', textStyle: { color: 'white' } },
          { name: '北门', textStyle: { color: 'white' } }],
          itemHeight: 8, // 设置高度
          itemWidth: 8,
          textStyle: {
            color: '#ffffff',
            fontSize: "10"
          },
          right: 20,
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '20%',
          containLabel: true
        },
        xAxis: {
          // type: 'value',
          type: 'category',
          data: ['1月', '2月', '3月', '4月', '5月', '6月'],
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
            "show": true
          },
          //设置刻度线
          axisTick: {
            show: false
          }
        },
        yAxis: [{
          type: 'value',
          axisLabel: {
            show: false,
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
            show: false,
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
        },],
        series: [
          {
            name: '东门',
            type: 'bar',
            data: [120, 50, 60, 40, 91, 87]
          },
          {
            name: '南门',
            type: 'bar',
            data: [60, 38, 150, 50, 41, 87]
          },
          {
            name: '西门',
            type: 'bar',
            data: [140, 45, 40, 60, 44, 130]
          },
          {
            name: '北门',
            type: 'bar',
            data: [50, 122, 30, 30, 74, 30]
          },
        ]
      })
    }
  }
}
</script>

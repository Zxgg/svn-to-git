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
          data: ['新增流动人口', '租住人口'],
          textStyle: {
            color: '#ffffff',
          },
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '30%',
          height: '50%',
          containLabel: true,
        },
        xAxis: [
          {
            type: 'category',
            boundaryGap: false,
            data: ['10.01', '10.02', '10.03', '10.04', '10.05', '10.06', '10.07'],
            axisLabel: {
              show: true,
              textStyle: {
                color: '#ccc',  //更改坐标轴文字颜色
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
                color: '#ccc',  //更改坐标轴文字颜色
                fontSize: 10      //更改坐标轴文字大小
              }
            },
            //设置网格线颜色
            splitLine: {
              show: true,
              lineStyle: {
                color: ['#434A7E'],
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
        series: [
          {
            name: '新增流动人口',
            type: 'line',
            stack: '总量',
            areaStyle: {},
            data: [120, 132, 101, 134, 90, 230, 210]
          },
          {
            name: '租住人口',
            type: 'line',
            stack: '总量',
            areaStyle: {},
            data: [220, 182, 191, 234, 290, 330, 310]
          },
        ]
      })
    }
  }
}
</script>

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
      default: '200px'
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
      var total = 459
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        title: {
          zlevel: 0,
          text: [
            '{value|' + total + '}',
            '{name|总数}',
          ].join('\n'),
          rich: {
            value: {
              color: '#ffffff',
              fontSize: 36,
              fontWeight: 'bold',
              lineHeight: 40,
            },
            name: {
              color: '#909399',
              lineHeight: 20
            },
          },
          top: 'center',
          left: '220',
          textAlign: 'center',
          textStyle: {
            rich: {
              value: {
                color: '#ffffff',
                fontSize: 25,
                fontWeight: 'bold',
                lineHeight: 40,
              },
              name: {
                color: '#909399',
                lineHeight: 20
              },
            },
          },
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 10,
          data: [{ name: '在线', textStyle: { color: 'white' } },
          { name: '未在线', textStyle: { color: 'white' } }],
          itemHeight: 10, // 设置高度
          textStyle: {
            color: '#ffffff',
            fontSize: "10"
          },
        },
        //设置饼状图每个颜色块的颜色
        color: ['rgb(27,255,225,0.7)', 'rgb(66,105,225,0.7)'],
        series: [
          {
            name: '访问来源',
            type: 'pie',
            radius: ['40%', '58%'],
            avoidLabelOverlap: false,
            zlevel: 1,
            label: {
              normal: {
                backgroundColor: '#13213B',
                show: false,
                position: 'center',
                formatter: [
                  '{value|{c}}',
                  '{name|{b}}'
                ].join('\n'),
                rich: {
                  value: {
                    color: '#ffffff',
                    fontSize: 40,
                    fontWeight: 'bold',
                    lineHeight: 40,
                  },
                  name: {
                    color: '#909399',
                    lineHeight: 20
                  },
                },
              },
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '30',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              { value: 135, name: '在线' },
              { value: 310, name: '未在线' },
            ]
          }
        ]
      })
    }
  }
}
</script>

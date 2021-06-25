<template>
  <div :style="{height:height,width:width}">
    <div :class="className"
         class="circle"
         :style="{height:height,width:width}" />
    <el-row class="textInfo">
      <span>{{showData && showData.attentionName ?showData.attentionName+':':''}}</span>
      <span>{{showData && showData.attentionNum ?showData.attentionNum:''}}</span>
      <span>{{showData && showData.totalNum ? '/'+showData.totalNum:''}}</span>
    </el-row>
  </div>
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
const animationDuration = 6000
//占比 圆形占比，比如入住率75%
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
      default () {
        return {
          title: '现存比例',
          showRate: true,//是否按比例显示标题，不填或者false为按值显示
          totalNum: 100,
          totalName: '总数',
          totalColor: 'rgba(251, 200, 79, 1)',
          attentionNum: 30,//关注项的数量
          attentionName: '现存',//关注项的名称
          attentionColor: 'rgba(251, 200, 79, 1)',
          otherName: '其他',
          otherNum: 70//其他项的数量，这个值不需要传入，由计算得出值
        }
      }
    }
  },
  data () {
    return {
      chart: null,
      showData: null,
      liveRate: 0,
      otherRate: 0,
      attentionName: '',
      defaultValue: {
        title: '现存比例',
        showRate: true,//是否按比例显示标题，不填或者false为按值显示
        totalNum: 100,
        totalName: '总数',
        totalColor: 'rgba(251, 200, 79, .2)',
        attentionNum: 30,//关注项的数量
        attentionName: '现存',//关注项的名称
        attentionColor: 'rgba(251, 200, 79, 1)',
        otherName: '其他',
        otherNum: 70//其他项的数量，这个值不需要传入，由计算得出值
      }
    }
  },
  watch: {
    data: {
      handler: function (newValue) {
        console.log('new value change')
        if (!newValue) return false
        this.showData = newValue
        this.$nextTick(() => {
          this.initChart()
        })
      },
      deep: true,
      immediate: true
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
  methods: {
    initChart () {
      if (!(this.showData && this.showData.totalNum >= 0)) return false
      let rateUnit = ''
      if (this.showData.totalNum > 0) {
        if (this.showData.showRate) {
          this.liveRate = this.$cf.getFloatNum((this.showData.attentionNum / this.showData.totalNum) * 100, 0)
          this.otherRate = this.$cf.getFloatNum(((this.showData.totalNum - this.showData.attentionNum) / this.showData.totalNum) * 100, 0)
          rateUnit = '%'
        }
        else {
          this.liveRate = this.showData.attentionNum
          this.otherRate = this.showData.totalNum - this.showData.attentionNum
        }
      } else {
        if (this.showData.showRate) {
          this.liveRate = 0
          this.otherRate = 0
          rateUnit = '%'
        } else {
          this.liveRate = 0
          this.otherRate = 0
        }
      }
      let totalNum = this.showData.totalNum ? this.showData.totalNum : '0'
      let title = this.showData.title ? this.showData.title : this.defaultValue.title
      let totalName = this.showData.totalName ? this.showData.totalName : this.defaultValue.totalName
      let totalColor = this.showData.totalColor ? this.showData.totalColor : this.defaultValue.totalColor
      let attentionName = this.showData.attentionName ? this.showData.attentionName : this.defaultValue.attentionName
      let attentionColor = this.showData.attentionColor ? this.showData.attentionColor : this.defaultValue.attentionColor

      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        title: [{
          text: this.liveRate + rateUnit,
          subtext: title,
          left: '48%',
          top: '40%',
          textAlign: 'center',
          textStyle: {
            fontSize: '14',
            fontWeight: '600',
            color: attentionColor,
            textAlign: 'center',
          },
          subtextStyle: {
            fontSize: '12',
            color: attentionColor,
            textAlign: 'center',
          },
          x: 'center',
          y: 'center',
        }],
        series: [
          {
            type: 'pie',
            radius: ['45%', '55%'],
            center: ['50%', '50%'],
            data: [
              {
                hoverOffset: 1,
                value: this.liveRate,
                name: attentionName,
                itemStyle: {
                  color: attentionColor,
                },
                label: {
                  show: true,//显示示意线
                  normal: {
                    formatter: (params) => {
                      if (this.showData.showRate) {
                        return '{b| ' + params.name + ':' + this.showData.attentionNum + '} ' + '(' + '{c|' + params.percent.toFixed(0) + '%}' + ')'
                      } else {
                        return '{b| ' + params.name + ':' + this.showData.attentionNum + '} '
                      }
                    },
                    borderWidth: 0,
                    borderRadius: 0,
                    padding: [0, -50],
                    height: 30,
                    fontSize: 12,
                    align: 'center',
                    // color: '#00FFC3',
                    rich: {
                      b: {
                        fontSize: 12,
                        lineHeight: 0,
                        color: attentionColor,
                        padding: [0, 0, 0, 0]
                      },
                      c: {
                        fontSize: 12,
                        lineHeight: 0,
                        color: attentionColor
                      }

                    }
                  }
                },
                labelLine: {
                  normal: {
                    length: 28,
                    length2: 35,
                    smooth: false,//转折是否平滑
                    lineStyle: {
                      // width: 0,
                      type: 'solid'
                    }
                  }
                },
                hoverAnimation: false,
              },
              {
                label: {
                  show: false,
                  normal: {
                    formatter: (params) => {
                      if (this.showData.showRate) {
                        return '{b|' + params.name + ':}' + '{c| ' + this.showData.totalNum + '}'
                      } else {
                        return '{b|' + params.name + ':' + this.showData.totalNum + '}'
                      }
                    },
                    rich: {
                      b: {
                        fontSize: 12,
                        lineHeight: 0,
                        color: attentionColor,
                        padding: [0, 0, 0, 0]
                      },
                      c: {
                        fontSize: 12,
                        lineHeight: 0,
                        color: attentionColor
                      }

                    }
                  }
                },
                labelLine: {
                  normal: {
                    length: 18,
                    length2: 25,
                    smooth: false,
                    lineStyle: {
                      // width: 0,
                      color: attentionColor,
                      type: 'solid'
                    }
                  }
                },
                value: this.otherRate,
                name: totalName,
                hoverAnimation: false,
                itemStyle: {
                  color: totalColor,
                },
              }
            ]
          }]
      })
    }
  }
}
</script>
<style scoped>
.textInfo {
  height: 30px;
}
.circle {
  height: calc(100% - 30px);
}
</style>
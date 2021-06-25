<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getHouseNum } from "@/api/homePage/houseNum"
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
  data () {
    return {
      chart: null,
      liveRate: ''
    }
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        this.getData()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  mounted () {
    this.getData()
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
      var num = 75;
      num = this.liveRate;
      // console.log(num, 'num')
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        title: [{
          text: isNaN(num) || !num ? 0 + '%' : num + '%',
          subtext: "入住率",
          left: '48%',
          top: '30%',
          textAlign: 'center',
          textStyle: {
            fontSize: '14',
            fontWeight: '600',
            color: 'rgba(251, 200, 79, 1)',
            textAlign: 'center',
          },
          subtextStyle: {
            fontSize: '16',
            color: 'rgba(251, 200, 79, 1)',
            textAlign: 'center',
          },
          x: 'center',
          y: 'center',
        }],
        series: [
          {
            type: 'pie',
            radius: ['55%', '65%'],
            center: ['50%', '45%'],
            data: [{
              hoverOffset: 1,
              value: num,
              name: '入住率',
              itemStyle: {
                color: 'rgba(251, 200, 79, 1)',
              },
              label: {
                show: false
              },
              labelLine: {
                normal: {
                  smooth: true,
                  lineStyle: {
                    width: 0
                  }
                }
              },
              hoverAnimation: false,
            },
            {
              label: {
                show: false
              },
              labelLine: {
                normal: {
                  smooth: true,
                  lineStyle: {
                    width: 0
                  }
                }
              },
              value: isNaN(num) || !num ? 100 - 0 : 100 - num,
              hoverAnimation: false,
              itemStyle: {
                color: 'rgba(251, 200, 79, .2)',
              },
            }
            ]
          }]
      })
    },
    getData () {
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      // this.liveRate = this.testData.shouye.renfangche.liveRate
      this.$nextTick(() => {
        this.initChart()
      })
      // getHouseNum(communityId, communityChildId).then((res) => {
      //   // console.log(res, '123545sadsa')
      //   if (res && res.data) {
      //     this.liveRate = res.data.liveRate * 100
      //   }
      //   this.$nextTick(() => {
      //     this.initChart()
      //   })
      // })
    },
  }
}
</script>

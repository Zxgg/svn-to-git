<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getPerProportion } from "@/api/homePage/perProportion"
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
        this.getPerProportionData()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      mobilityData: []
    }
  },
  mounted () {
    this.getPerProportionData()
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
      let data = [{
        name: "本地人",
        value: 4800,
      },
      {
        name: "外地人",
        value: 1000,
      },
      {
        name: "访客",
        value: 800,
      }];
      data = this.mobilityData
      // console.log(data, 'data')
      var num = 75;
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        color: ['#00EEFF', '#20B8C3', '#198890'],
        tooltip: { //提示框
          trigger: "item",
          triggerOn: "mousemove",
          backgroundColor: "rgba(1,70,86,1)",
          borderColor: "rgba(0,246,255,1)",
          borderWidth: 0.5,
          textStyle: {
            fontSize: 14
          }
        },
        title: [{
          text: '人口',
          subtext: '流动',
          textStyle: {
            fontSize: 14,
            color: "#00EEFF"
          },
          subtextStyle: {
            fontSize: 14,
            color: '#00EEFF'
          },
          textAlign: "center",
          x: '48%',
          y: '40%',
        }],
        series: [{
          type: 'pie',
          radius: ['35%', '55%'],
          center: ['50%', '55%'],
          startAngle: '300',
          hoverOffset: 3,
          labelLine: {
            normal: {
              length: 10,
              length2: 40,
              lineStyle: {
                type: 'solid'
              }
            }
          },
          label: {
            normal: {
              formatter: (params) => {
                return '{b| ' + params.name + '} ' + '{c|' + params.percent.toFixed(0) + '%}'
              },
              borderWidth: 0,
              borderRadius: 0,
              padding: [0, -50],
              height: 30,
              fontSize: 12,
              align: 'center',
              color: '#00EEFF',
              rich: {
                b: {
                  fontSize: 12,
                  lineHeight: 0,
                  color: '#00EEFF',
                  padding: [0, 0, 0, 0]
                },
                c: {
                  fontSize: 12,
                  lineHeight: 0,
                  color: '#00EEFF'
                }

              }
            }
          },
          data: data,
        }]
      })
    },
    getPerProportionData () {
      this.mobilityData = []
      this.mobilityData = this.testData.shouye.renfangche.mobilityData
      this.$nextTick(() => {
        this.initChart()
      })
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      // getPerProportion(communityId, communityChildId).then((res) => {
      //   // console.log(res, '123545sadsa')
      //   if (res && res.data) {
      //     this.mobilityData = [{
      //       name: "本地人",
      //       value: res.data.local
      //     }, {
      //       name: "外地人",
      //       value: res.data.nonlocal
      //     }, {
      //       name: "访客",
      //       value: res.data.visitors
      //     }]
      //   }
      //   this.$nextTick(() => {
      //     this.initChart()
      //   })
      // })
    },
  }
}
</script>

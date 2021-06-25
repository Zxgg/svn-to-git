<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getPerHouseInform } from "@/api/PerInformation/perHouseInform"
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
        this.getliveTypeData()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      liveTypeData: []
    }
  },
  mounted () {
    this.getliveTypeData()
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
      let data = [];
      data = this.liveTypeData
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        color: ['#00FFC3', '#04CC99', '#05A079', '#056D53'],
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
          text: '居住',
          subtext: '类型',
          textStyle: {
            fontSize: 14,
            color: "#00FFC3"
          },
          subtextStyle: {
            fontSize: 14,
            color: '#00FFC3'
          },
          textAlign: "center",
          x: '48%',
          y: '40%',
        }],
        series: [{
          color: ['#00FFC3', '#04CC99', '#05A079', '#056D53'],
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
              color: '#00FFC3',
              rich: {
                b: {
                  fontSize: 12,
                  lineHeight: 0,
                  color: '#00FFC3',
                  padding: [0, 0, 0, 0]
                },
                c: {
                  fontSize: 12,
                  lineHeight: 0,
                  color: '#00FFC3'
                }

              }
            }
          },
          data: data,
        }]
      })
    },
    getliveTypeData () {
      this.liveTypeData = this.testData.shouye.renfangche.liveTypeData
      this.$nextTick(() => {
        this.initChart()
      })
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      // getPerHouseInform(communityId, communityChildId).then((res) => {
      //   // console.log(res, '123545sadsa')
      //   if (res && res.data.length > 0) {
      //     debugger
      //     res.data.forEach(data => {
      //       let xData = {
      //         value: data.num,
      //         name: data.name
      //       }
      //       this.liveTypeData.push(xData)
      //     });
      //   }
      //   this.$nextTick(() => {
      //     this.initChart()
      //   })
      // })
    },
  }
}
</script>

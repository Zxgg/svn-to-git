<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getpeovehiNum } from '@/api/trafficData/vehipeoData.js'

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
        this.getpeovehiNumData()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      data: {
        personListt: [],
        vehicleList: []
      }
    }
  },
  created () {
    // this.getData()
  },
  mounted () {
    this.getpeovehiNumData()
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
      let xAxis = ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23']
      let yAxis_vehicle = [0, 0, 0, 0, 0, 0, 0]
      let yAxis_person = [0, 0, 0, 0, 0, 0, 0]
      if (this.data && this.data.personList && this.data.vehicleList) {
        //取X轴
        xAxis = this.data.vehicleList.map(item => item.name)
        //取Y轴
        yAxis_person = this.data.personList.map(item => item.num)
        yAxis_vehicle = this.data.vehicleList.map(item => item.num)
      }
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        legend: {
          show: true,
          icon: "rect",
          itemWidth: 14,
          itemHeight: 5,
          itemGap: 10,
          right: "35%",
          top: 0,
          textStyle: {
            fontSize: 10,
            color: "#ccc"
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            lineStyle: {
              color: {
                type: 'linear',
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [{
                  offset: 0,
                  color: 'rgba(0, 255, 233,0)'
                }, {
                  offset: 0.5,
                  color: 'rgba(255, 255, 255,1)',
                }, {
                  offset: 1,
                  color: 'rgba(0, 255, 233,0)'
                }],
                global: false
              }
            },
          },
        },
        grid: {
          top: '15%',
          left: '5%',
          right: '5%',
          bottom: '25%',
          height: '60%'
          // containLabel: true
        },
        xAxis: [{
          type: 'category',
          axisLine: {
            show: true
          },
          splitArea: {
            show: false,
            color: '#f00',
            lineStyle: {
              color: '#f00'
            },
          },
          axisLabel: {
            color: '#fff'
          },
          splitLine: {
            show: false
          },
          boundaryGap: false,
          data: xAxis,
        }],
        yAxis: [{
          type: 'value',
          min: 0,
          // max: 140,
          splitNumber: 4,
          splitLine: {
            show: true,
            lineStyle: {
              color: 'rgba(255,255,255,0.1)'
            }
          },
          axisLine: {
            show: false,
          },
          axisLabel: {
            show: false,
            margin: 20,
            textStyle: {
              color: '#d1e6eb',
            },
          },
          axisTick: {
            show: false,
          },
          splitArea: { show: false }//设置网格区域
        }],
        series: [{
          name: '人员',
          type: 'line',
          smooth: true, //是否平滑
          showAllSymbol: false,
          // symbol: 'image://./static/images/guang-circle.png',
          symbol: 'circle',
          symbolSize: 2,
          lineStyle: {
            normal: {
              color: "#00b3f4",
            },
          },
          itemStyle: {
            color: "#00b3f4",
          },
          tooltip: {
            show: true
          },
          areaStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(0,179,244,0.3)'
              },
              {
                offset: 1,
                color: 'rgba(0,179,244,0)'
              }
              ], false),
              shadowColor: 'rgba(0,179,244, 0.9)',
              shadowBlur: 20
            }
          },
          data: yAxis_person
        },
        {
          name: '车辆',
          type: 'line',
          smooth: true, //是否平滑
          showAllSymbol: false,
          // symbol: 'image://./static/images/guang-circle.png',
          symbol: 'circle',
          symbolSize: 2,
          lineStyle: {
            normal: {
              color: "#00ca95",
            },
          },
          itemStyle: {
            color: "#00ca95",
          },
          tooltip: {
            show: true
          },
          areaStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(0,202,149,0.3)'
              },
              {
                offset: 1,
                color: 'rgba(0,202,149,0)'
              }
              ], false),
              shadowColor: 'rgba(0,202,149, 0.9)',
              shadowBlur: 20
            }
          },
          data: yAxis_vehicle,
        },
        ]
      })
    },
    getpeovehiNumData () {
      this.data = []
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getpeovehiNum(communityId, communityChildId).then(res => {
        if (res && res.data) {
          this.data = res.data
          console.log(this.data, 'this.data')
          this.$nextTick(() => {
            this.initChart()
          })
        }
      })
    }
  }
}
</script>

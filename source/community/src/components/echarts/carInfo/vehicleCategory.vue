<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getVehicleType } from '@/api/vehicle/vehicleType'

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
    },
    xData: {
      type: Array,
      default: []
    },
    labels: {
      type: String,
      default: '车辆进口'
    }
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        // this.getVehicleTypeSApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      xData: []
    }
  },
  mounted () {
    // this.getVehicleTypeSApi()
    // this.xData = this.testData.car.vehicleCategory
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
      let xData = []
      xData = this.xData
      var color = ['#394DFD', '#00D9FF']
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        normal: {
          bottom: 0
        },
        // grid: {
        //   x: '0%', //相当于距离左边效果:padding-left
        //   y: '60%',  //相当于距离上边效果:padding-top
        //   bottom: '3%'
        // },
        color: color,
        // backgroundColor: '#0A173B',
        title: {
          text: this.labels,
          left: 'center',
          top: 'center',
          textStyle: {
            fontSize: 10,
            color: '#fff',
            fontWeight: 'normal'
          }
        },

        tooltip: {
          trigger: 'item'
        },
        // legend: {
        //   orient: 'vertical',
        //   right: '5%',
        //   top: 0,
        //   textStyle: {
        //     color: '#fff',
        //   }
        // },
        series: [
          {
            type: 'pie',
            roseType: 'radius',
            center: ['center', 'center'],
            radius: ['30%', '60%'],
            data: xData,
            hoverOffset: 1,
            label: {
              normal: {
                formatter: '{font|{b}}\n{hr|}\n{font|{d}%}',
                rich: {
                  font: {
                    fontSize: 10,
                    // padding: [5, 0, 0, 0],
                    color: '#fff'
                  },
                  hr: {
                    height: 0,
                    borderWidth: 1,
                    width: '100%',
                    borderColor: '#fff'
                  }
                }
              },
            },
            labelLine: {
              lineStyle: {
                color: '#fff'
              }
            },
            emphasis: {
              itemStyle: {
                shadowBlur: 0,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0,0,0,0.5)'
              }
            }
          }]
      })
    },
    getVehicleTypeSApi () {
      this.xData = [
        {
          value: 0,
          name: '东门'
        },
        {
          value: 0,
          name: '西门'
        },
        {
          value: 0,
          name: '南门'
        },
        {
          value: 0,
          name: '北门'
        },
      ]
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getVehicleType(communityId, communityChildId).then(res => {
        // console.log(res, 'nis')
        if (res && res.data.length > 0) {
          // res.data.forEach(data => {
          //   let xData = {
          //     name: data.name,
          //     value: data.num
          //   }
          //   this.xData.push(xData)
          // });
          for (let index = 0; index < res.data.length; index++) {
            this.xData[index].value = res.data[index].num
          }
        }
        console.log('x:', this.xData)
        this.$nextTick(() => {
          this.initChart()
        })
      })
    }
  }
}
</script>

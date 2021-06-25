<template>
  <div :class="className"
       id='myChart'
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getVisitVehicle } from '@/api/vehicle/visitVehicle'

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
        // this.getVisitvehicleApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      xData: [],
      yData: [],
      //访客车辆类型字典
      visitVehicleTypeList: []
    }
  },
  created () {
    this.getDicts("base_visitor_vehicle_type").then(response => {
      this.visitVehicleTypeList = response.data;
      console.log('haha :', this.visitVehicleTypeList)
    });
  },
  mounted () {
    // this.getVisitvehicleApi()
    this.xData = this.testData.car.activeVehicle.x
    this.yData = this.testData.car.activeVehicle.y
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
    // // 字典翻译
    visitVehicleTypeFormat (value) {
      return this.selectDictLabel(this.visitVehicleTypeList, value);
    },

    initChart () {
      let xData = [1, 2, 9, 4, 8, 10]
      let yData = ['SUV', '厢式车', '多用途汽车', '跑车', '小型货车', '中型货车']
      xData = this.xData
      yData = this.yData
      var myChart = echarts.init(document.getElementById('myChart'));
      // import echarts from 'echarts'
      var charts = { // 按顺序排列从大到小
        cityList: yData,
        cityData: xData
      }
      var top10CityList = charts.cityList
      var top10CityData = charts.cityData
      var color = ['rgba(248,195,248', 'rgba(100,255,249', 'rgba(135,183,255', 'rgba(248,195,248', 'rgba(100,255,249']

      let lineY = []
      for (var i = 0; i < charts.cityList.length; i++) {
        var x = i
        if (x > color.length - 1) {
          x = color.length - 1
        }
        var data = {
          name: charts.cityList[i],
          color: color[x] + ')',
          value: top10CityData[i],
          itemStyle: {
            normal: {
              show: true,
              color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                offset: 0,
                color: color[x] + ', 0.3)'
              }, {
                offset: 1,
                color: color[x] + ', 1)'
              }], false),
              barBorderRadius: 10
            },
            emphasis: {
              shadowBlur: 15,
              shadowColor: 'rgba(0, 0, 0, 0.1)'
            }
          }
        }
        lineY.push(data)
      }
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        title: {
          show: false
        },
        tooltip: {
          trigger: 'item'
        },
        grid: {
          borderWidth: 0,
          left: '5%',
          right: '15%',
          bottom: '10%',
          height: '70%'
        },
        color: color,
        yAxis: [{
          type: 'category',
          inverse: true,
          axisTick: {
            show: false
          },
          axisLine: {
            show: false
          },
          axisLabel: {
            show: false,
            inside: false
          },
          // data: top10CityList
        }, {
          type: 'category',
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            show: true,
            inside: false,
            textStyle: {
              color: '#b3ccf8',
              fontSize: '14',
              fontFamily: 'PingFangSC-Regular'
            },
            formatter: function (val) {
              return `${val} 辆`
            }
          },
          splitArea: {
            show: false
          },
          splitLine: {
            show: false
          },
          data: top10CityData
        }],
        xAxis: {
          type: 'value',
          axisTick: {
            show: false
          },
          axisLine: {
            show: false
          },
          splitLine: {
            show: false
          },
          axisLabel: {
            show: false
          },
          splitArea: {
            show: false
          },
        },
        series: [{
          name: '',
          type: 'bar',
          zlevel: 2,
          barWidth: '10px',
          data: lineY,
          animationDuration: 1500,
          label: {
            normal: {
              color: '#b3ccf8',
              show: true,
              position: [0, '-24px'],
              textStyle: {
                fontSize: 16
              },
              formatter: function (a, b) {
                return a.name
              }
            }
          }
        }],
        animationEasing: 'cubicOut'
      })
    },
    getVisitvehicleApi () {
      // let self = this
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getVisitVehicle(communityId, communityChildId).then(res => {
        console.log(res, 'ressss12')
        if (res && res.data.length > 0) {
          this.xData = []
          this.yData = []
          res.data.forEach(datas => {
            datas.name = this.visitVehicleTypeFormat(datas.name)
            this.xData.push(datas.num)
            this.yData.push(datas.name)
          });
        }
        this.$nextTick(() => {
          console.log('x:', this.xData)
          console.log('y:', this.yData)
          this.initChart()
        })
      })
    }
  }
}
</script>

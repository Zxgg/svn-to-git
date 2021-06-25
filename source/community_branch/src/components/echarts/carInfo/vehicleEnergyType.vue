<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getenergyVehicle } from '@/api/vehicle/energyVehicle'

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
      default: '90%'
    },
    currentComunity: {
      communityId: String,
      communityChildId: String
    }
  },
  watch: {
    currentComunity: {
      handler (val, data) {
        // this.getenergyApi()
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
    // this.getenergyApi()
    this.xData = this.testData.car.vehicleEnergyType
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
      let xData = [{ name: '燃油车', value: 80 }, { name: '新能源', value: 60 }, { name: '混合动力', value: 30 }]
      xData = this.xData
      var seriesdata1 = xData;
      var colorList = ['#407AFB', '#1791FF', '#36B0FE', '#6635EF', '#73ACFF', '#41CBAB', '#7BDD43', '#FFC653', '#FF6519', '#EE3939', '#FFAFDA', '#00FFFF']


      // var objData = this.array2obj(seriesdata1, 'name')

      //将合计值转换为数组
      // var h = '6725';
      // let arr = h.split('');
      // let m = '';

      // for (let i = 0; i < arr.length; i++) {
      //   m += "{a|" + arr[i] + "}";
      //   if (i != arr.length - 1) { m += '  ' }
      // };
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        series: {
          type: 'pie',
          center: ['center', 'center'],
          radius: ['40%', '50%'],
          // hoverOffset: 1,
          itemStyle: {
            normal: {
              color: function (params) {
                return colorList[params.dataIndex]
              }
            }
          },
          label: {
            show: true,
            position: 'outside',
            formatter: function (data) {
              //console.log('data',data)
              return '\n{icon|}\n' + '{name|' + data.name + "}" + " {value|" + data.value.toFixed(0) + " ," + "辆}";
            },
            //padding: [-40, -90, -20, -80],
            rich: {
              icon: {
                height: 5,
                width: 5,
                lineHeight: 5,
                //marginBottom: 10,
                padding: [0, -5],
                borderRadius: 5,
                backgroundColor: 't', // 圆点颜色和饼图块状颜色一致
              },
              name: {
                fontSize: 14,
                padding: [20, 100, -20, -90],
                color: '#ffffff'
              },
              value: {
                fontSize: 14,
                padding: [20, -90, -20, -100],
                color: '#ffffff'
              }
            }
          },
          labelLine: {
            show: false,
            normal: {
              length: 10,
              length2: 90,
              align: 'right',
              lineStyle: {
                width: 1
              }
            }
          },
          "data": seriesdata1,
          "seriesIndex": 0
        }
      })
    },
    array2obj (array, key) {
      var resObj = {}
      for (var i = 0; i < array.length; i++) {
        resObj[array[i][key]] = array[i]
      }
      return resObj
    },
    getenergyApi () {
      this.xData = [{ name: '燃油车', value: 0 }, { name: '新能源', value: 0 }, { name: '混合动力', value: 0 }]
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      let self = this
      getenergyVehicle(communityId, communityChildId).then(res => {
        // console.log(res, 'energy')
        if (res && res.data.length > 0) {
          // this.xData = []
          // res.data.forEach(data => {
          //   let xData = {
          //     name: data.name,
          //     value: data.num
          //   }
          //   this.xData.push(xData)
          // })
          for (let index = 0; index < res.data.length; index++) {
            self.xData[index].value = res.data[index].num
          }
        }
        // console.log('777:', self.xData)
        self.$nextTick(() => {
          self.initChart()
        })
      })
    }
  }
}
</script>

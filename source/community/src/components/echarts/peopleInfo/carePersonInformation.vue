<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getCaringPeopleType } from '@/api/caringPeople/caringpeoType'

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
        // this.getCarpeoApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  created () {
    this.getDicts("base_type_of_caring").then(response => {
      this.carePersonList = response.data;
    });
  },
  data () {
    return {
      communityId: '',
      communityChildId: '',
      chart: null,
      carePersonList: [],
      xData: ['留守老人', '留守妇女', '留守儿童', '精神疾病', '疾病', '残疾', '瘫痪', '孕妇'],
      yData: [0, 0, 0, 0, 0, 0, 0, 0]
    }
  },
  mounted () {
    this.xData = this.testData.renyuan.carePersonData.x
    this.yData = this.testData.renyuan.carePersonData.y
    this.$nextTick(() => {
      this.initChart()
    })
    // this.getCarpeoApi()
  },
  beforeDestroy () {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    visitVehicleTypeFormat (value) {
      return this.selectDictLabel(this.carePersonList, value);
    },
    initChart () {
      var xData = []
      var yData = []
      xData = this.xData
      yData = this.yData
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        // backgroundColor: '#031245',
        grid: {
          left: '12%',
          top: '20%',
          bottom: '12%',
          right: '8%',
          height: '45%'
        },
        xAxis: {
          data: xData,
          axisTick: {
            show: false
          },
          axisLine: {
            lineStyle: {
              color: '#80aeda',
              width: 1 //这里是为了突出显示加上的
            }
          },
          axisLabel: {
            textStyle: {
              color: '#fff',
              fontSize: 8,
            },
            rotate: 20,//倾斜度 -90 至 90 默认为0
          },
          splitArea: { show: false }//设置网格区域
        },
        yAxis: [{
          axisTick: {
            show: false
          },
          axisLine: {
            lineStyle: {
              color: 'rgba(255, 129, 109, 0.1)',
              width: 1, //这里是为了突出显示加上的,
              show: false
            }
          },
          axisLabel: {
            textStyle: {
              color: '#fff'
            }
          },
          splitArea: {
            show: false,
            areaStyle: {
              color: 'rgba(255,255,255,0.5)'
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#434A7E',
              width: 0.5,
              type: 'dashed'
            }
          }
        }
        ],
        series: [{
          type: 'pictorialBar',
          barCategoryGap: '0%',
          symbol: 'path://M0,10 L10,10 C5.5,10 5.5,5 5,0 C4.5,5 4.5,10 0,10 z',
          label: {
            show: true,
            position: 'top',
            distance: 15,
            color: '#08DFFE',
            fontWeight: 'bolder',
            fontSize: 12,
          },
          itemStyle: {
            normal: {
              color: {
                type: 'linear',
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [{
                  offset: 0,
                  color: '#9A11FF'
                },
                {
                  offset: 1,
                  color: '#08DFFE'
                }
                ],
                global: false //  缺省为  false
              }
            },
            emphasis: {
              opacity: 1
            }
          },
          data: yData
        }]
      })
    },
    getCarpeoApi () {
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      this.xData = ['独居老人', '残疾人员', '重点治疗人员', '安置帮教人员', '知名人士', '其他关爱人员', '瘫痪', '孕妇']
      this.yData = [0, 0, 0, 0, 0, 0, 0, 0]
      getCaringPeopleType(communityId, communityChildId).then(res => {
        // console.log(res, 'caring')
        if (res && res.data.length > 0) {
          this.xData = []
          this.yData = []
          res.data.forEach(data => {
            this.xData.push(data.name)
            this.yData.push(data.num)
          })
          this.$nextTick(() => {
            console.log('6x:', this.xData)
            console.log('6y:', this.yData)
            this.initChart()
          })
        }
      })
    }
  }
}
</script>

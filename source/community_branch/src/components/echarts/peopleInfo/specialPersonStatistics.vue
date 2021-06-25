<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getSpecialPersonNum } from '@/api/homePage/specialPerson'

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
        // this.getSpecialApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      communityId: '',
      communityChildId: '',
      chart: null,
      xData: [],
      yData: [],
      //特殊人员字典
      specialPersonList: []
    }
  },
  created () {
    this.getDicts("base_special_person").then(response => {
      this.specialPersonList = response.data;
    });
  },
  mounted () {
    this.xData = this.testData.renyuan.specialPersonData.x
    this.yData = this.testData.renyuan.specialPersonData.y
    // this.getSpecialApi()
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
    //字典翻译
    specialPersonFormat (value) {
      return this.selectDictLabel(this.specialPersonList, value);
    },
    initChart () {
      var xData = [10, 22, 31, 42, 51]
      var yData = []
      yData = this.yData
      xData = this.xData
      var myColor = ['#eb2100', '#eb3600', '#d0570e', '#d0a00e', '#34da62', '#00e9db'];
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        grid: {
          left: '8%',
          top: '8%',
          right: '8%',
          bottom: '8%',
          height: '80%',
          containLabel: true
        },
        xAxis: [{
          show: false,
        }],
        yAxis: [{
          axisTick: 'none',
          axisLine: 'none',
          offset: '10',
          axisLabel: {
            textStyle: {
              color: '#ffffff',
              fontSize: '12',
            }
          },
          data: yData
        }, {
          axisTick: 'none',
          axisLine: 'none',
          axisLabel: {
            show: false,
            textStyle: {
              color: '#ffffff',
              fontSize: '12',
            }
          },
          data: ['7', '6', '5', '4', '3']
        }, {
          axisLine: {
            lineStyle: {
              color: 'rgba(0,0,0,0)'
            }
          },
          data: [],
        }],
        series: [{
          name: '条',
          type: 'bar',
          yAxisIndex: 0,
          data: xData,
          label: {
            normal: {
              show: true,
              position: 'right',
              textStyle: {
                color: '#ffffff',
                fontSize: '12',
              }
            }
          },
          barWidth: 6,
          itemStyle: {
            normal: {
              color: function (params) {
                var num = myColor.length;
                return myColor[params.dataIndex % num]
              },
            }
          },
          z: 2
        }, {
          name: '白框',
          type: 'bar',
          yAxisIndex: 1,
          barGap: '-100%',
          data: [99, 99.5, 99.5, 99.5, 99.5],
          barWidth: 10,
          itemStyle: {
            normal: {
              color: '#0e2147',
              barBorderRadius: 5,
            }
          },
          z: 1
        }, {
          name: '外框',
          type: 'bar',
          yAxisIndex: 2,
          barGap: '-100%',
          data: [100, 100, 100, 100, 100],
          barWidth: 12,
          itemStyle: {
            normal: {
              color: function (params) {
                var num = myColor.length;
                return myColor[params.dataIndex % num]
              },
              barBorderRadius: 5,
            }
          },
          z: 0
        },
        {
          name: '外圆',
          type: 'scatter',
          hoverAnimation: false,
          data: [0, 0, 0, 0, 0],
          yAxisIndex: 2,
          symbolSize: 16,
          itemStyle: {
            normal: {
              color: function (params) {
                var num = myColor.length;
                return myColor[params.dataIndex % num]
              },
              opacity: 1,
            }
          },
          z: 2
        }
        ]
      })
    },
    getSpecialApi () {
      let self = this
      this.yData = ['刑满释放人员', '社区矫正人员', '精神障碍患者', '吸毒人员', '艾滋病危险人员']
      this.xData = [0, 0, 0, 0, 0]
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getSpecialPersonNum(communityId, communityChildId).then(res => {
        console.log(res, 'specialx')
        if (res && res.data) {
          // this.yData.push(self.specialPersonFormat(res.data.aidsNum))
          // this.yData = []
          this.xData = [res.data.aidsNum, res.data.correctNum, res.data.drugNum, res.data.mentalNum, res.data.releaseNum]
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

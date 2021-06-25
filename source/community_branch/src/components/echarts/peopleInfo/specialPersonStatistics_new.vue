<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getPeopleInfoApi } from '@/api/PerInformation/peopleInfo'

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
      specialPersonList: [],
      data: this.testData.renyuan.specialPersonData
    }
  },
  created () {
    this.getDicts("base_special_person").then(response => {
      this.specialPersonList = response.data;
    });
  },
  mounted () {
    this.xData = this.data.x
    this.yData = this.data.y
    // this.getPeopleInfoApi()
    this.$nextTick(() => {
      this.initChart()
    })
    this.$Notice.$on('communityGrid', (val) => {
      // this.getCode(val)
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
      var charts = { // 按顺序排列从大到小
        cityList: this.data.y,
        cityData: this.data.x
      }
      var top10CityList = charts.cityList
      var top10CityData = charts.cityData
      var color = ['#358B6A', '#8B3535', '#F0D34E', '#3175C2', '#6D45B6']
      var color1 = ['#55716A', '#B75A5A', '#FDD772', '#3175C2', '#9450B0']

      let lineY = []
      let lineT = []
      for (var i = 0; i < charts.cityList.length; i++) {
        var data = {
          name: charts.cityList[i],
          color: color[i],
          value: top10CityData[i],
          barGap: '-100%',
          itemStyle: {
            normal: {
              show: true,
              color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                offset: 0,
                color: color[i]
              }, {
                offset: 1,
                color: color1[i]
              }], false),
              barBorderRadius: 10
            },
            emphasis: {
              shadowBlur: 15,
              shadowColor: 'rgba(0, 0, 0, 0.1)'
            }
          }
        }
        var data1 = {
          value: top10CityData[0],
          itemStyle: {
            color: '#001235',
            barBorderRadius: 10
          }
        }
        lineY.push(data)
        lineT.push(data1)
      }
      this.chart.setOption({
        title: {
          show: false
        },
        tooltip: {
          trigger: 'item',
          formatter: (p) => {
            if (p.seriesName === 'total') {
              return ''
            }
            return `${p.name}<br/>${p.value}`
          }
        },
        grid: {
          borderWidth: 0,
          top: '10%',
          left: '5%',
          right: '15%',
          bottom: '3%'
        },
        color: color,
        yAxis: [{
          type: 'category',
          inverse: false,
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
          data: top10CityList
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
            color: '#FFC9D7E1',
            formatter: function (val) {
              return `${val}`
            },
            rich: {
              lg: {
                backgroundColor: '#339911',
                color: '#FFC9D7E1',
                borderRadius: 15,
                // padding: 5,
                align: 'center',
                width: 15,
                height: 15
              },
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
          splitArea: { show: false }//设置网格区域
        },
        series: [{
          name: 'total',
          type: 'bar',
          zlevel: 1,
          barGap: '-100%',
          barWidth: '10px',
          data: lineT,
          legendHoverLink: false
        }, {
          name: 'bar',
          type: 'bar',
          zlevel: 2,
          barWidth: '10px',
          data: lineY,
          label: {
            normal: {
              color: '#b3ccf8',
              show: true,
              position: [0, '-24px'],
              textStyle: {
                fontSize: 16
              },
              formatter: function (a) {
                let num = ''
                let str = ''
                str = `{color4|${a.name}}`
                // if (a.dataIndex === 0) {

                // } else if (a.dataIndex === 1) {
                //   str = `{color2|${num}} {color4|${a.name}}`
                // } else {
                //   str = `{color3|${num}} {color4|${a.name}}`
                // }
                return str;
              },
              rich: {
                color1: {
                  color: '#ff9500',
                  fontWeight: 700
                },
                color2: {
                  color: '#02d8f9',
                  fontWeight: 700
                },
                color3: {
                  color: '#027fff',
                  fontWeight: 700
                },
                color4: {
                  color: '#e5eaff'
                }
              }
            }
          }
        }],
      })
    },
    getCode (code) {
      getPeopleInfoApi(code).then(res => {
        if (res && res.data.specialPersonData) {
          this.data = res.data.specialPersonData
        }
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

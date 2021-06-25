<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
//网格户数统计
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
      mobilityData: [],
      colorItmes: ['#FFFFFF', '#FF9D55', '#176C8E', '#2BD9F3', '#00C494', '#00ffff', '#00cfff', '#00C494', '#00ffff', '#00cfff', '#176C8E'],
      ringImg: require('@/assets/images/index/房屋数量-sj.png'),

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
      let trafficWay = [{
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
      // data = this.mobilityData
      let data = this.getEchartData(this.mobilityData)


      var seriesOption = [{
        name: '',
        type: 'pie',
        clockWise: false,
        radius: [55, 59],
        hoverAnimation: false,
        itemStyle: {
          normal: {
            label: {
              show: true,
              position: 'outside',
              color: '#ddd',
              formatter: function (params) {
                var percent = 0;
                var total = 0;
                for (var i = 0; i < trafficWay.length; i++) {
                  total += trafficWay[i].value;
                }
                // percent = ((params.value / total) * 100).toFixed(0);
                percent = params.value
                if (params.name !== '') {
                  return '' + params.name + '：' + percent;
                } else {
                  return '';
                }
              },
            },
            labelLine: {
              length: 10,
              length2: 40,
              show: true,
              color: '#00ffff'
            }
          }
        },
        data: data
      }];


      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        color: this.colorItmes,
        tooltip: { //提示框
          show: false
        },
        graphic: {
          elements: [{
            type: "image",
            z: 3,
            style: {
              image: this.ringImg,
              width: 98,
              height: 98
            },
            left: 'center',
            top: 'center',
            position: [100, 100]
          }]
        },
        series: seriesOption
      })
    },

    getEchartData (trafficWay) {

      let processingData = []
      for (var i = 0; i < trafficWay.length; i++) {
        processingData.push({
          value: trafficWay[i].value,
          name: trafficWay[i].name,
          itemStyle: {
            normal: {
              borderWidth: 3,
              shadowBlur: 5,
              borderColor: this.colorItmes[i],
              shadowColor: this.colorItmes[i]
            }
          }
        }, {
          value: 100,
          name: '',
          itemStyle: {
            normal: {
              label: {
                show: false
              },
              labelLine: {
                show: false
              },
              color: 'rgba(0, 0, 0, 0)',
              borderColor: 'rgba(0, 0, 0, 0)',
              borderWidth: 5
            }
          }
        });
      }

      return processingData
    },

    getPerProportionData () {
      this.mobilityData = []
      let temp = []
      this.testData.renyuan.peopleHousingInfoList.forEach(element => {
        temp.push({
          name: element.gridName,
          value: element.households
        })
      });
      this.mobilityData = temp
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

<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
import 'echarts-liquidfill'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getOccupancy } from '@/api/community/occupancy'

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
        this.getouucpancyApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      ratioColor: ['#1991d2', '#3831f3', '#1991d2'],
      ratio: '', //比例值
    }
  },
  mounted () {
    this.getouucpancyApi()
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
      var ratioArr = [];
      for (let ratioIndex = 0; ratioIndex < 3; ratioIndex++) {
        ratioArr.push(this.ratio / 100)
      }
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        title: {
          text: this.ratio + '%',
          textStyle: {
            fontSize: 40,
            fontFamily: 'Microsoft Yahei',
            fontWeight: 'bold',
            color: '#01D8FF'
          },
          x: 'center',
          y: 'center'
        },
        series: [{
          type: 'liquidFill',
          radius: '70%',
          center: ['50%', '50%'],
          color: this.ratioColor,
          data: ratioArr,
          backgroundStyle: {
            // borderWidth: 1,
            color: '#1f2c52'
          },
          label: {
            normal: {
              formatter: '',
            }
          },
          outline: {
            show: true,
            itemStyle: {
              borderColor: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: '#00D9FF'
              }, {
                offset: 1,
                color: '#3B4AFF'
              }]),
              borderWidth: 4, // 轮廓大小
              shadowBlur: 'none', // 轮廓阴影
            }
          },
          itemStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
              offset: 0,
              color: '#00D9FF'
            }, {
              offset: 1,
              color: '#3B4AFF'
            }]),
          },
        },
        ]
      })
    },
    getouucpancyApi () {
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getOccupancy(communityId, communityChildId).then(res => {
        if (res.data != null) {
          this.ratio = res.data.liveRate * 100
        }
        this.$nextTick(() => {
          this.initChart()
        })
      })
    }
  }
}
</script>

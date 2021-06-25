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
        // this.geteducationApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      data: this.testData.renyuan.eduData,
      ditDegree: []
    }
  },
  mounted () {
    this.getDicts("base_degree").then((response) => {
      this.ditDegree = response.data;
    });
    // this.getPeopleInfoApi()
    this.$nextTick(() => {
      this.initChartOld()
    })
    this.$Notice.$on('communityGrid', (val) => {
      this.getCode(val)
    })
    this.$Notice.$on('communityGridReturn', (val) => {
      this.data = this.testData.renyuan.eduData;
      this.$nextTick(() => {
        this.initChartOld()
      })
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
    initChartOld () {
      let nameList = ['']
      this.chart = echarts.init(this.$el, 'macarons')
      let img = require('@/assets/images/peopleinfo/culture.png')
      this.chart.setOption({
        color: ["#00EAFF", "#61A9ED", "#6361BC", "#F7906C", "#F8F4FF", "#8C70F8"],
        graphic: {
          elements: [{
            type: "image",
            z: 3,
            style: {
              image: img,
              width: 50,
              height: 50
            },
            left: 'center',
            top: 'center',
            position: [100, 100]
          }]
        },
        tooltip: {
          trigger: 'item',
          textStyle: {
            fontSize: 14
          },
          formatter: "{b} : {c} ({d}%)"
        },
        calculable: true,
        series: [
          {
            type: 'pie',
            radius: ["69.5%", "70%"],
            center: ['50%', '50%'],
            hoverAnimation: false,
            labelLine: {
              normal: {
                show: false,
              },
              emphasis: {
                show: false
              }
            },
            name: "",
            data: [{
              name: '',
              value: 0,
              itemStyle: {
                normal: {
                  color: "#0674bf"
                }
              }
            }]
          }, {
            stack: 'a',
            type: 'pie',
            radius: ['35%', '75%'],
            center: ['50%', '50%'],
            roseType: 'area',
            zlevel: 10,
            // label: {
            //   normal: {
            //     formatter: "{b} : {d}%",
            //     borderWidth: 0,
            //     borderRadius: 4,
            //     // padding: [0, -50],
            //     height: 40,
            //     fontSize: 12,
            //     align: 'center',
            //     rich: {
            //       b: {
            //         fontSize: 13,
            //         lineHeight: 5,
            //         color: '#41B3DC'
            //       }
            //     }
            //   }
            // },
            label: {
              normal: {
                formatter: params => {
                  let total = 0;
                  for (let index = 0; index < this.data.length; index++) {
                    total += this.data[index].value;
                  }
                  let percent = ((params.value / total) * 100).toFixed(0);
                  return (
                    '{name|' + params.name + '}{value|' +
                    params.value + '人' + '}'
                  );
                },
                rich: {
                  name: {
                    fontSize: 14,
                    padding: [0, 10, 0, 4],
                    color: '#81c8f5'
                  },
                  value: {
                    fontSize: 18,
                    // fontWeight: 'bold',
                    color: '#81c8f5'
                  }
                }
              }
            },
            labelLine: {
              normal: {
                show: true,
                length: 10,
                length2: 25
              },
              emphasis: {
                show: false
              }
            },
            data: this.data
          }]
      })
    },
    getCode (code) {
      getPeopleInfoApi(code).then(res => {
        if (res && res.data.eduData.length > 0) {
          for (let index = 0; index < res.data.eduData.length; index++) {
            res.data.eduData[index].name = this.selectDictLabel(this.ditDegree, res.data.eduData[index].name);
          }
          this.data = res.data.eduData
          this.$nextTick(() => {
            this.initChartOld()
          })
        }
      })
    },
    getPeopleInfoApi () {
      getPeopleInfoApi('321002004013001').then(res => {
        if (res && res.data.eduData.length > 0) {
          this.data = res.data.eduData
          this.$nextTick(() => {
            this.initChartOld()
          })
        } else {
          this.data = this.testData.renyuan.eduData
          this.$nextTick(() => {
            this.initChartOld()
          })
        }
      }).catch(err => {
        this.data = this.testData.renyuan.eduData
        this.$nextTick(() => {
          this.initChartOld()
        })
      })
    }
  }
}
</script>

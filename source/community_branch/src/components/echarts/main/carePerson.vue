<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getCaringType } from "@/api/homePage/caringType"
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
        this.getCaringTypeData()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      carePerson: {
        oneNumTitle: '孤寡老人',
        oneNum: 0,
        oneNumRatio: 0,
        twoNumTitle: '独居老人',
        twoNum: 0,
        twoNumRatio: 0,
        threeNumTitle: '残疾人员',
        threeNum: 0,
        threeNumRatio: 0,
        fourNumTitle: '留守儿童',
        fourNum: 0,
        fourNumRatio: 0,
      }
    }
  },
  mounted () {
    this.getCaringTypeData()
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
      var placeHolderStyle = {
        normal: {
          label: {
            show: false
          },
          labelLine: {
            show: false
          },
          color: "rgba(0,0,0,0)",
          borderWidth: 0
        },
        emphasis: {
          color: "rgba(0,0,0,0)",
          borderWidth: 0
        }
      };


      var dataStyle = {
        normal: {
          formatter: '{c}',
          position: 'center',
          show: true,
          textStyle: {
            fontSize: '12',
            fontWeight: 'normal',
            color: '#00DCFF'
          }
        }
      };
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        title: [{
          text: this.carePerson.oneNumTitle,
          left: '14.5%',
          top: '88%',
          textAlign: 'center',
          textStyle: {
            fontWeight: 'normal',
            fontSize: '12',
            color: '#00DCFF',
            textAlign: 'center',
          },
        }, {
          text: this.carePerson.twoNumTitle,
          left: '37.5%',
          top: '88%',
          textAlign: 'center',
          textStyle: {
            color: '#00DCFF',
            fontWeight: 'normal',
            fontSize: '12',
            textAlign: 'center',
          },
        }, {
          text: this.carePerson.threeNumTitle,
          left: '60.5%',
          top: '88%',
          textAlign: 'center',
          textStyle: {
            color: '#00DCFF',
            fontWeight: 'normal',
            fontSize: '12',
            textAlign: 'center',
          },
        }, {
          text: this.carePerson.fourNumTitle,
          left: '82.5%',
          top: '88%',
          textAlign: 'center',
          textStyle: {
            color: '#00DCFF',
            fontWeight: 'normal',
            fontSize: '12',
            textAlign: 'center',
          },
        }],

        //第一个图表
        series: [{
          type: 'pie',
          hoverAnimation: false, //鼠标经过的特效
          radius: ['60%', '70%'],
          center: ['15%', '50%'],
          startAngle: 225,
          labelLine: {
            normal: {
              show: false
            }
          },
          label: {
            normal: {
              position: 'center'
            }
          },
          data: [{
            value: 100,
            itemStyle: {
              normal: {
                color: '#fff'
              }
            },
          }, {
            value: 35,
            itemStyle: placeHolderStyle,
          },

          ]
        },
        //上层环形配置
        {
          type: 'pie',
          hoverAnimation: false, //鼠标经过的特效
          radius: ['60%', '70%'],
          center: ['15%', '50%'],
          startAngle: 225,
          labelLine: {
            normal: {
              show: false
            }
          },
          label: {
            normal: {
              position: 'center'
            }
          },
          data: [{
            value: this.carePerson.oneNum,
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                  offset: 0,
                  color: '#394CFE'

                }, {
                  offset: 0.5,
                  color: '#05CDFF'

                }])
              }
            },
            label: dataStyle,
          }, {
            value: 100 - this.carePerson.oneNumRatio,
            itemStyle: placeHolderStyle,
          },

          ]
        },
        //第二个图表
        {
          type: 'pie',
          hoverAnimation: false,
          radius: ['60%', '70%'],
          center: ['38%', '50%'],
          startAngle: 225,
          labelLine: {
            normal: {
              show: false
            }
          },
          label: {
            normal: {
              position: 'center'
            }
          },
          data: [{
            value: 100,
            itemStyle: {
              normal: {
                color: '#E1E8EE'
              }
            },

          }, {
            value: 35,
            itemStyle: placeHolderStyle,
          },

          ]
        },
        //上层环形配置
        {
          type: 'pie',
          hoverAnimation: false,
          radius: ['60%', '70%'],
          center: ['38%', '50%'],
          startAngle: 225,
          labelLine: {
            normal: {
              show: false
            }
          },
          label: {
            normal: {
              position: 'center'
            }
          },
          data: [{
            value: this.carePerson.twoNum,
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                  offset: 0,
                  color: '#394CFE'

                }, {
                  offset: 0.5,
                  color: '#05CDFF'

                }])
              }
            },
            label: dataStyle,
          }, {
            value: 100 - this.carePerson.twoNumRatio,
            itemStyle: placeHolderStyle,
          },
          ]
        },
        //第三个图表
        {
          type: 'pie',
          hoverAnimation: false,
          radius: ['60%', '70%'],
          center: ['61%', '50%'],
          startAngle: 225,
          labelLine: {
            normal: {
              show: false
            }
          },
          label: {
            normal: {
              position: 'center'
            }
          },
          data: [{
            value: 100,
            itemStyle: {
              normal: {
                color: '#E1E8EE'
              }
            },

          }, {
            value: 35,
            itemStyle: placeHolderStyle,
          },

          ]
        },
        //上层环形配置
        {
          type: 'pie',
          hoverAnimation: false,
          radius: ['60%', '70%'],
          center: ['61%', '50%'],
          startAngle: 225,
          labelLine: {
            normal: {
              show: false
            }
          },
          label: {
            normal: {
              position: 'center'
            }
          },
          data: [{
            value: this.carePerson.threeNum,
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                  offset: 0,
                  color: '#394CFE'

                }, {
                  offset: 0.5,
                  color: '#05CDFF'

                }])
              }
            },
            label: dataStyle,
          }, {
            value: 100 - this.carePerson.threeNumRatio,
            itemStyle: placeHolderStyle,
          },
          ]
        },
        //第四个图表
        {
          type: 'pie',
          hoverAnimation: false,
          radius: ['60%', '70%'],
          center: ['83%', '50%'],
          startAngle: 225,
          labelLine: {
            normal: {
              show: false
            }
          },
          label: {
            normal: {
              position: 'center'
            }
          },
          data: [{
            value: 100,
            itemStyle: {
              normal: {
                color: '#E1E8EE'
              }
            },

          }, {
            value: 35,
            itemStyle: placeHolderStyle,
          },

          ]
        },
        //上层环形配置
        {
          type: 'pie',
          hoverAnimation: false,
          radius: ['60%', '70%'],
          center: ['83%', '50%'],
          startAngle: 225,
          labelLine: {
            normal: {
              show: false
            }
          },
          label: {
            normal: {
              position: 'center'
            }
          },
          data: [{
            value: this.carePerson.fourNum,
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                  offset: 0,
                  color: '#394CFE'

                }, {
                  offset: 0.5,
                  color: '#05CDFF'

                }])
              }
            },
            label: dataStyle,
          }, {
            value: 100 - this.carePerson.fourNumRatio,
            itemStyle: placeHolderStyle,
          },
          ]
        },
        ]
      })
    },
    getCaringTypeData () {
      // this.carePerson= this.testData.shouye.carePerson
      this.carePerson.oneNumTitle = this.testData.shouye.carePerson.oneNumTitle// "孤寡老人":
      this.carePerson.oneNum = this.testData.shouye.carePerson.oneNum // "孤寡老人":
      this.carePerson.twoNumTitle = this.testData.shouye.carePerson.twoNumTitle// "孤寡老人":
      this.carePerson.twoNum = this.testData.shouye.carePerson.twoNum// "独居老人":
      this.carePerson.threeNumTitle = this.testData.shouye.carePerson.threeNumTitle// "孤寡老人":
      this.carePerson.threeNum = this.testData.shouye.carePerson.threeNum//"残疾人员":
      this.carePerson.fourNumTitle = this.testData.shouye.carePerson.fourNumTitle// "孤寡老人":
      this.carePerson.fourNum = this.testData.shouye.carePerson.fourNum// "留守儿童":

      let sum = this.carePerson.oneNum + this.carePerson.twoNum + this.carePerson.threeNum + this.carePerson.fourNum
      if (sum != 0) {
        this.carePerson.oneNumRatio = this.carePerson.oneNum / sum * 100
        this.carePerson.twoNumRatio = this.carePerson.twoNum / sum * 100
        this.carePerson.threeNumRatio = this.carePerson.threeNum / sum * 100
        this.carePerson.fourNumRatio = this.carePerson.fourNum / sum * 100
      }
      return true
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      getCaringType(communityId, communityChildId).then((res) => {
        // console.log(res, '123545sadsa')
        let care = {
          oneNumRatio: 0,
          twoNumRatio: 0,
          threeNumRatio: 0,
          fourNumRatio: 0
        }
        if (res && res.data) {
          res.data.forEach(data => {
            switch (data.name) {
              case "孤寡老人":
                care.oneNumRatio = data.num
                break;
              case "独居老人":
                care.twoNumRatio = data.num
                break;
              case "残疾人员":
                care.threeNumRatio = data.num
                break;
              case "留守儿童":
                care.fourNumRatio = data.num
                break;
              default:
                break;
            }
          });
        }
        let sum = care.oneNumRatio + care.twoNumRatio + care.threeNumRatio + care.fourNumRatio
        if (sum != 0) {
          this.carePerson.oneNumRatio = care.oneNumRatio / sum * 100
          this.carePerson.twoNumRatio = care.twoNumRatio / sum * 100
          this.carePerson.threeNumRatio = care.threeNumRatio / sum * 100
          this.carePerson.fourNumRatio = care.fourNumRatio / sum * 100
        }
        // if (res && res.data) {
        //   res.data.forEach(data => {
        //     let houseNum = {
        //       name: '',
        //       value: 0
        //     }
        //     houseNum.name = data.name
        //     houseNum.value = data.num
        //     this.houseNumData.push(houseNum)
        //   });
        // }
        this.$nextTick(() => {
          this.initChart()
        })
      })
    },
  }
}
</script>

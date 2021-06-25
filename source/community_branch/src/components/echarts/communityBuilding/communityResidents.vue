<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from '../mixins/resize'
import { getHouseTree } from '@/api/community/houseTree'
import { listCommunity } from "@/api/communityUnit/community"

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
        this.gethousetreeApi()
        // console.log(val.communityId, 'val')
      },
      deep: true
    }
  },
  data () {
    return {
      chart: null,
      colors: [
        "#05FDB8",
        "#25B9D6",
        "#FFA12F",
        "#B62AFF",
        "#6E35FF",
        "#9CFF00",
      ],
      treeData: {
        name: '',
        children: [],
      },
      treeData1: {}
    }
  },
  mounted () {
    this.gethousetreeApi()
    // this.$nextTick(() => {
    //   this.initChart()
    // })
  },
  beforeDestroy () {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    getData () {
      if (this.treeData1.children && this.treeData1.children.length > 0) {
        let data = {
          name: this.treeData1.name,
          children: [],
          label: {
            position: "inside",
            show: true,
          },
          lineStyle: { color: "#25B9D6" },
          itemStyle: {
            borderColor: "#25B9D6",
            color: "#25B9D6"
          },
          symbolSize: 40
        };
        if (this.treeData1.children && this.treeData1.children.length > 0) {
          this.treeData1.children.forEach(item => {
            let obj = {
              name: item.name,
              // value: 1,
              children: [],
              label: {
                show: true,
                position: "inside",
              },
              lineStyle: { color: "#FFA12F" },
              itemStyle: {
                borderColor: "#FFA12F",
                color: "#FFA12F"
              },
              symbolSize: 20
            }
            data.children.push(obj);
          })
        }
        return [data]
      } else {
        let data = {
          name: this.treeData.name,
          children: [],
          label: {
            show: true,
            position: "inside",
          },
          lineStyle: { color: "#FFA12F" },
          itemStyle: {
            borderColor: "#FFA12F",
            color: "#FFA12F"
          },
          symbolSize: 30
        };
        if (this.treeData.children && this.treeData.children.length > 0) {
          this.treeData.children.forEach(item1 => {
            let obj = {
              name: item1.name,
              // value: 1,
              children: [],
              label: {
                show: true,
                position: "inside",
              },
              lineStyle: { color: "#B62AFF" },
              itemStyle: {
                borderColor: "#B62AFF",
                color: "#B62AFF"
              },
              symbolSize: 15
            };
            if (item1.children && item1.children.length > 0) {
              item1.children.forEach(item2 => {
                let obj2 = {
                  name: item2.name,
                  // value: j,
                  label: {
                    show: true,
                    position: "inside",
                  },
                  lineStyle: { color: "#6E35FF" },
                  itemStyle: {
                    borderColor: "#6E35FF",
                    color: "#6E35FF"
                  },
                  symbolSize: 6
                };
                if (item2.children && item2.children.length > 0) {
                  item2.children.forEach(item3 => {
                    let obj3 = {
                      name: item3.name,
                      // value: j,
                      label: {
                        show: true,
                        position: "inside",
                      },
                      lineStyle: { color: "#9CFF00" },
                      itemStyle: {
                        borderColor: "#9CFF00",
                        color: "#9CFF00"
                      },
                      symbolSize: 6
                    };
                    obj2.children.push(obj3)
                  });
                }
                obj.children.push(obj2)
              });
            }
            data.children.push(obj);
          });
          return [data];
        }
      }
    },
    initChart () {
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        type: "tree",
        tooltip: {//提示框
          trigger: "item",
          triggerOn: "mousemove",
          backgroundColor: "rgba(1,70,86,1)",
          borderColor: "rgba(0,246,255,1)",
          borderWidth: 0.5,
          textStyle: {
            fontSize: 10
          }
        },
        series: [
          {
            type: "tree",
            top: '10%',
            bottom: '10%',
            left: 0,
            right: 0,
            hoverAnimation: true, //hover样式
            data: this.getData(),
            layout: "radial",
            symbol: "circle",
            animationDurationUpdate: 750,
            expandAndCollapse: true, //子树折叠和展开的交互，默认打开
            initialTreeDepth: 2,
            roam: false, //是否开启鼠标缩放和平移漫游。scale/move/true
            focusNodeAdjacency: true,
            itemStyle: {
              borderWidth: 1,
            },
            label: { //标签样式
              show: true,
              color: "#fff",
              fontSize: 10,
              fontFamily: "SourceHanSansCN",
              position: "inside",
              rotate: 0,
            },
            lineStyle: {
              width: 1,
              curveness: 0.5,
            }
          }
        ]
      })
    },
    gethousetreeApi () {
      this.treeData = {}
      this.treeData1 = {}
      let param = {
        pageNum: 1,
        pageSize: 100000
      }
      let communityId = this.currentComunity.communityId
      let communityChildId = this.currentComunity.communityChildId
      if (this.$cf.checkIsNotNull(this.$store.state.user) && this.$cf.checkIsNotNull(this.$store.state.user.roles
        && this.$cf.checkIsNotNull(this.$store.state.user.roles))) {
        getHouseTree(communityId, communityChildId).then(res => {
          listCommunity(param).then(response => {
            if (res && res.data) {
              this.treeData = {
                name: res.data.name,
                children: res.data.children
              }
              this.$nextTick(() => {
                this.initChart()
              })
            } else if (response && response.rows.length > 0) {
              let childs = []
              response.rows.forEach(element => {
                childs.push({
                  name: element.name
                })
              });
              this.treeData1 = {
                name: this.$store.state.user.name,
                children: childs
              }
              this.$nextTick(() => {
                this.initChart()
              })
            }
          });
        })
      }
    }
  }
}
</script>

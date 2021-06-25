<template>
  <div :class="className"
       :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
// import resize from './mixins/resize'

const animationDuration = 6000

export default {
  name: 'P_H_V_RelationChart',
  // mixins: [resize],
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
    data: {
      type: Object,
      default: null
    }
  },
  data () {
    return {
      chart: null
    }
  },
  mounted () {
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
  watch: {
    data: {
      handler: function (newValue) {
        // console.log('new value change')
        // if (!newValue) return false
        // this.data = newValue
        this.$nextTick(() => {
          this.initChart()
        })
      },
      deep: true,
      immediate: true
    }
  },
  methods: {
    initChart () {
      // import echarts from 'echarts'
      let data = this.data

      const color1 = '#00D9FF';
      const color2 = '#ff7d18';
      const color3 = '#10a050';

      data.nodes.forEach(node => {
        if (node.category === 0) {
          node.symbolSize = 100;
          // node.symbol = 'image://' + '../../asset/images/PHV/BuildingTextBackground.png'
          node.itemStyle = {
            color: color1
          };
        } else if (node.category === 1) {
          // node.symbol = '../../asset/images/PHV/VehicleImage.png'
          node.itemStyle = {
            color: color2
          };
        } else if (node.category === 2) {
          node.itemStyle = {
            color: color3
          };
        }
      });

      data.links.forEach(link => {
        link.label = {
          align: 'center',
          fontSize: 12
        };

        if (link.name === '参股') {
          link.lineStyle = {
            color: color2
          }
        } else if (link.name === '董事') {
          link.lineStyle = {
            color: color1
          }
        } else if (link.name === '法人') {
          link.lineStyle = {
            color: color3
          }
        }
      });

      let categories = [{
        name: '人员',
        itemStyle: {
          color: color1
        }
      },
      {
        name: '房屋',
        itemStyle: {
          color: color2
        }
      },
      {
        name: '车辆',
        itemStyle: {
          color: color3
        }
      }]
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        title: {
          text: '关系图谱',
          textStyle: {
            // fontSize: 20,
            // color: '#FFFFFF99'
            color: '#000000'
          },
        },
        legend: [{
          // selectedMode: 'single',
          data: categories.map(x => x.name),
          textStyle: {
            fontSize: 14,
            // color: '#FFFFFF99'
            color: '#000000'
          },
          // icon: 'circle'
        }],
        series: [{
          type: 'graph',
          layout: 'force',
          symbolSize: 58,
          draggable: true,
          roam: true,
          focusNodeAdjacency: true,
          categories: categories,
          edgeSymbol: ['', 'arrow'],
          // edgeSymbolSize: [80, 10],
          edgeLabel: {
            normal: {
              show: true,
              textStyle: {
                fontSize: 20
              },
              formatter (x) {
                return x.data.name;
              }
            }
          },
          label: {
            show: true
          },
          force: {
            repulsion: 2000,
            edgeLength: 120
          },
          data: data.nodes,
          links: data.links
        }]
      })
    }
  }
}
</script>

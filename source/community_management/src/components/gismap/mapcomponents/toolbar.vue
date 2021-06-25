<template>
  <div class="toolbar_box">
    <el-dropdown class="select_layer"
                 trigger="click">
      <el-button type="primary"
                 class="icon iconfont">&#xe69c;&nbsp;图层<i class="el-icon-arrow-down el-icon--right"></i></el-button>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>
          <div style="width:80px">
            <el-checkbox-group v-model="checkedCameras">
              <el-checkbox v-for="camera in cameras"
                           :label="camera"
                           @change="checked=>checkedChange(checked, camera)"
                           :key="camera">{{camera}}</el-checkbox>
            </el-checkbox-group>
          </div>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <el-dropdown class="select_layer"
                 trigger="click">
      <el-button type="primary"
                 class="icon iconfont">&#xe791;&nbsp;标绘<i class="el-icon-arrow-down el-icon--right"></i></el-button>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="drawMap('Point')">点</el-dropdown-item>
        <el-dropdown-item @click.native="drawMap('Rectangle')">框选</el-dropdown-item>
        <el-dropdown-item @click.native="drawMap('Circle')">圈选</el-dropdown-item>
        <el-dropdown-item @click.native="drawMap('Polygon')">多边形</el-dropdown-item>
        <el-dropdown-item @click.native="drawMap('LineString')">自由线</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <el-dropdown class="select_layer"
                 trigger="click">
      <el-button type="primary"
                 class="icon iconfont"><i class="el-icon-s-cooperation"></i>&nbsp;工具<i class="el-icon-arrow-down el-icon--right"></i></el-button>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="measure('LineString')">测距</el-dropdown-item>
        <el-dropdown-item @click.native="measure('Polygon')">测面</el-dropdown-item>
        <el-dropdown-item @click.native="initFull">初始位</el-dropdown-item>
        <el-dropdown-item @click.native="initHeatMap">热力图</el-dropdown-item>
        <el-dropdown-item ref="exprotMap"><a id="image-download"
             @click.once="exportMap(true)"
             download="map.png">地图导出</a></el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <el-button type="primary"
               class="icon iconfont toolbar_button_clear"
               @click="CloseVector"><i class="el-icon-delete"></i>清除</el-button>
    <el-button type="primary"
               class="icon iconfont toolbar_button_clear"
               style="width:100px"
               @click="RealTrackClick"><i class="el-icon-s-promotion"></i>实时轨迹</el-button>
  </div>
</template>

<script>
import { formatArea, formatLength } from '@/components/gismap/mapfunction/measure'
import Draw, { createBox } from 'ol/interaction/Draw'
import { heatMap } from '@/components/gismap/mapfunction/heatMap'
import { monitorLineStyle, monitorPointStyle } from '@/components/gismap/mapfunction/vectorStyle'
import { unByKey } from 'ol/Observable'
import Overlay from 'ol/Overlay'
import { LineString, Polygon, Point } from 'ol/geom'

import { Vector as VectorLayer } from 'ol/layer'
import { Vector as VectorSource } from 'ol/source'
// import { getVectorContext } from 'ol/render'
// import { routeFeature } from '@/components/gismap/mapfunction/initLayer'
import { _one } from '@/components/gismap/mapfunction/trackAnalysis'
import '@/styles/gismap.scss'
export default {
  data () {
    return {
      helpTooltipElement: null,
      interaction: null,
      measureTooltipElement: null,
      measureTooltip: null,
      feature: null,
      listener: null,
      helpTooltip: null,
      tooltipCoord: null,
      draw: null,
      checkedCameras: ['设备', '车辆', '检查站', '重点路段', '危化品厂'],
      cameras: ['设备', '车辆', '检查站', '重点路段', '危化品厂'],
      cameraChecked: true,
      vehicelChecked: true,
      checkpointChecked: true,
      FocusChecked: true,
      dangerChecked: true,
      mapContext: '',
      Isexport: true,
      speed: 60,
      animating: false,
      now: null,
      geoMarker: null,
      startMarker: null,
      endMarker: null,
      routeFeature: null,
      routeLength: null,
      routeCoords: null,
      conf: {
        monitor: {
          // 起点坐标
          p: [118.23916100722, 37.49818441389],
          // 波动系数
          set_num: 0.001,
          // 线的样式
          line_style: monitorLineStyle([34, 139, 34, 0.6]),
          // 点的样式
          p_style: monitorPointStyle('./gisImage/car.png', 0.1, 'normal 16px 黑体', '危化品车辆'),
          // 刷新时间
          time: 200
        },
        center: [116.06, 39.67]
      },
      all_obj: {
        monitor: {
          layer: null,
          data_c: null,
          p_data: null,
          timer: null
        }
      }

    }
  },
  props: ['map', 'sources', 'layers'],
  methods: {
    checkedChange (checked, camera) {
      if (checked) {
        switch (camera) {
          case '车辆': this.map.addLayer(this.layers.vehicleLayer); break
          case '设备': this.map.addLayer(this.layers.cameraLayer); break
          case '检查站': this.map.addLayer(this.layers.checkpointLayer); break
          case '危化品厂': this.map.addLayer(this.layers.dangerLayer); break
        }
      } else {
        switch (camera) {
          case '车辆': this.map.removeLayer(this.layers.vehicleLayer); break
          case '设备': this.map.removeLayer(this.layers.cameraLayer); break
          case '检查站': this.map.removeLayer(this.layers.checkpointLayer); break
          case '危化品厂': this.map.removeLayer(this.layers.dangerLayer); break
        }
      }
    },
    measure (type) {
      this.map.on('pointermove', this.pointerMoveHandler)
      this.map.getViewport().addEventListener('mouseout', this.map_mouseout)
      if (type) {
        this.interaction = new Draw({
          source: this.sources.source,
          type: type
        })
        this.map.addInteraction(this.interaction)
        this.createMeasureTooltip()
        this.createHelpTooltip()
        this.interaction.on('drawstart', this.draw_start)
        this.interaction.on('drawend', this.draw_stop)
      }
    },
    pointerMoveHandler (evt) {
      var continuePolygonMsg = '继续点击绘制多边形'
      var continueLineMsg = '继续点击绘制线'
      if (evt.dragging) {
        return
      }
      var helpMsg = '开始绘制'
      if (this.feature) {
        var geom = (this.feature.getGeometry())
        if (geom instanceof Polygon) {
          helpMsg = continuePolygonMsg
        } else if (geom instanceof LineString) {
          helpMsg = continueLineMsg
        }
      }
      this.helpTooltipElement.innerHTML = helpMsg
      this.helpTooltip.setPosition(evt.coordinate)
      this.helpTooltipElement.classList.remove('hidden')
    },
    createMeasureTooltip () {
      if (this.measureTooltipElement) {
        this.measureTooltipElement.parentNode.removeChild(this.measureTooltipElement)
      }
      this.measureTooltipElement = document.createElement('div')
      this.measureTooltipElement.className = 'tooltip tooltip-measure'
      this.measureTooltip = new Overlay({
        element: this.measureTooltipElement,
        offset: [0, -15],
        positioning: 'bottom-center'
      })
      this.map.addOverlay(this.measureTooltip)
    },
    createHelpTooltip () {
      if (this.helpTooltipElement) {
        this.helpTooltipElement.parentNode.removeChild(this.helpTooltipElement)
      }
      this.helpTooltipElement = document.createElement('div')
      this.helpTooltipElement.className = 'tooltip hidden'
      this.helpTooltip = new Overlay({
        element: this.helpTooltipElement,
        offset: [15, 0],
        positioning: 'center-left'
      })
      this.map.addOverlay(this.helpTooltip)
    },
    draw_start (evt) {
      this.feature = evt.feature
      if (this.feature) {
        this.tooltipCoord = evt.coordinate
        this.listener = this.feature.getGeometry().on('change', this.listener_change)
      }
    },
    draw_stop () {
      this.measureTooltipElement.className = 'tooltip tooltip-static'
      this.measureTooltip.setOffset([0, -7])
      this.feature = null
      this.measureTooltipElement = null
      unByKey(this.listener)
      this.map.un('pointermove', this.pointerMoveHandler)
      if (this.interaction) {
        this.map.removeInteraction(this.interaction)
      }
      if (this.helpTooltipElement) {
        this.helpTooltipElement.classList.add('hidden')
      }
      this.map.removeOverlay(this.helpTooltip)
    },
    listener_change (evt) {
      var geom = evt.target
      var output
      if (geom instanceof Polygon) {
        output = formatArea(geom) // 面积值
        this.tooltipCoord = geom.getInteriorPoint().getCoordinates() // 坐标
      } else if (geom instanceof LineString) {
        var sourceProj = this.map.getView().getProjection()
        output = formatLength(geom, sourceProj) // 长度值
        this.tooltipCoord = geom.getLastCoordinate() // 坐标
      }
      this.measureTooltipElement.innerHTML = output
      this.measureTooltip.setPosition(this.tooltipCoord)
    },
    map_mouseout () {
      this.helpTooltipElement.classList.add('hidden')
    },
    drawMap (typeValue) {
      if (this.draw) { this.map.removeInteraction(this.draw) }
      this.drawgraph(typeValue)
      this.map.addInteraction(this.draw)
      this.draw.on('drawend', this.drawend_query)
      // this.cameraFeature = drawend_query(this.draw, this.cameraSource);
    },
    async drawend_query (evt) {
      // var polygon = await evt.feature.getGeometry();
      // var extent = polygon.getExtent();
      // var features = this.cameraSource.getFeaturesInExtent(extent); //先缩小feature的范围
      // features.forEach(feature => {
      //   var newCoords = feature.getGeometry().getCoordinates();
      //   if (polygon.intersectsCoordinate(newCoords)) {
      //     this.cameraFeature.push(feature);
      //   }
      // });
      if (this.draw) { this.map.removeInteraction(this.draw) }
    },
    drawgraph (typeValue) {
      var geometryFunction, maxPoints
      if (typeValue === 'Rectangle') {
        typeValue = 'LineString'
        maxPoints = 2
        geometryFunction = createBox()
      }
      this.draw = new Draw({
        source: this.sources.drawsource,
        type: typeValue,
        geometryFunction: geometryFunction,
        maxPoints: maxPoints
      })
    },
    initFull () {
      this.map.getView().setCenter([118.24716471892582, 37.50884887693753])
      this.map.getView().setZoom(15)
    },
    exportMap (em) {
      this.map.once('rendercomplete', this.exportMapEvent)
      this.map.renderSync()
    },
    exportMapEvent (event) {
      var mapCanvas = document.createElement('canvas')
      var size = this.map.getSize()
      mapCanvas.width = size[0]
      mapCanvas.height = size[1]
      this.mapContext = mapCanvas.getContext('2d')
      Array.prototype.forEach.call(document.querySelectorAll('.ol-layer canvas'), this.canvasSave)
      if (navigator.msSaveBlob) {
        // link download attribuute does not work on MS browsers
        navigator.msSaveBlob(mapCanvas.msToBlob(), 'map.png')
      } else {
        var link = document.getElementById('image-download')
        link.href = mapCanvas.toDataURL()
        link.click()
      }
    },
    canvasSave (canvas) {
      if (canvas.width > 0) {
        var opacity = canvas.parentNode.style.opacity
        this.mapContext.globalAlpha = opacity === '' ? 1 : Number(opacity)
        var transform = canvas.style.transform
        // Get the transform parameters from the style's transform matrix
        var matrix = transform
          .match(/^matrix\(([^(]*)\)$/)[1]
          .split(',')
          .map(Number)
        // Apply the transform to the export map context
        CanvasRenderingContext2D.prototype.setTransform.apply(
          this.mapContext,
          matrix
        )
        this.mapContext.drawImage(canvas, 0, 0)
      }
    },
    CloseVector () {
      const me = this
      me.sources.drawsource.clear()// 清除标绘
      me.sources.source.clear()// 清除测量
      me.map.getOverlays().clear()// 清除覆盖层
      me.map.removeLayer(heatMap())
      me.map.removeLayer(me.all_obj.monitor.layer)
    },
    // --------------------------
    initHeatMap () {
      this.map.addLayer(heatMap())// 热力图
    },
    initLayer () {
      const me = this
      me.all_obj.monitor.layer = new VectorLayer()
      me.all_obj.monitor.data_c = new VectorSource()
      me.all_obj.monitor.layer.setSource(me.all_obj.monitor.data_c)
    },
    RealTrackClick () {
      const me = this
      me.map.addLayer(me.all_obj.monitor.layer)
      let p_data = new Feature({
        geometry: new Point(me.conf.monitor.p)
      })
      p_data.setStyle(me.conf.monitor.p_style)
      me.all_obj.monitor.data_c.addFeature(p_data)
      me._map_fit(me.all_obj.monitor.data_c)
      me.all_obj.monitor.p_data = p_data
      //
      p_data = null
      me._monitor_init()
    },
    _map_fit (data_c) {
      const me = this
      console.log(data_c.getFeatures(), 'data_c')
      // 整个容器每个元素的最小最大 集合数组
      var point_arr = []
      data_c.getFeatures().forEach(function (ele, index) {
        point_arr.push(_one(ele.getGeometry()))
      })

      // 假设第一个点为最合适的点
      var fit_point = point_arr[0]
      point_arr.forEach(function (point, index) {
        // 最小经度
        if (point[0] < fit_point[0]) {
          fit_point[0] = point[0]
        }

        // 最小纬度
        if (point[1] < fit_point[1]) {
          fit_point[1] = point[1]
        }

        // 最大经度
        if (point[2] > fit_point[2]) {
          fit_point[2] = point[2]
        }

        // 最大纬度
        if (point[3] > fit_point[3]) {
          fit_point[3] = point[3]
        }
      })

      // 没有数据
      if (data_c.getFeatures().length === 0) {

      } else if (data_c.getFeatures().length === 1) {
        me.map.getView().centerOn(
          [fit_point[0], fit_point[1]],
          me.map.getSize(), [window.innerWidth / 2, window.innerHeight / 2])

        me.map.getView().setZoom(12)
      } else {
        me.map.getView().fit(fit_point, {
          size: me.map.getSize(),
          padding: [100, 100, 100, 100],
          constrainResolution: false
        })
      }
    },
    _monitor_init () {
      const me = this
      // 追踪
      let old_p = null
      let new_p = [0, 0]
      // 得到旧的点
      old_p = me.all_obj.monitor.p_data.getGeometry().flatCoordinates
      // ***********************************模拟数据
      new_p[0] = old_p[0] + Math.random() * me.conf.monitor.set_num
      new_p[1] = old_p[1] + Math.random() * me.conf.monitor.set_num
      // if (Math.random() > 0.5) {
      //   new_p[0] = old_p[0] + Math.random() * me.conf.monitor.set_num
      // } else {
      //   new_p[0] = old_p[0] - Math.random() * me.conf.monitor.set_num
      // }

      // if (Math.random() > 0.5) {
      //   new_p[1] = old_p[1] + Math.random() * me.conf.monitor.set_num
      // } else {
      //   new_p[1] = old_p[1] - Math.random() * me.conf.monitor.set_num
      // }
      // *******************************************

      // 移动点
      me.all_obj.monitor.p_data
        .setGeometry(new Point(new_p))

      // 设置转向
      me.all_obj.monitor.p_data.getStyle().getImage().setRotation(me._map_p_rotation(new_p, old_p))

      // 线的渲染
      me._monitor_init_line(new_p, old_p)

      // 清除
      old_p = null
      new_p = null
      me.all_obj.monitor.timer = setTimeout(() => {
        me._monitor_init()
      }, me.conf.monitor.time)
    },
    _monitor_init_line (new_p, old_p) {
      const me = this
      var line_data = new Feature({
        geometry: new LineString([old_p, new_p])
      })
      line_data.setStyle(me.conf.monitor.line_style)

      // 注入容器
      me.all_obj.monitor.data_c.addFeature(line_data)
    },
    _map_p_rotation (new_p, old_p) {
      // 90度的PI值
      var pi_90 = Math.atan2(1, 0)
      // 当前点的PI值
      var pi_ac = Math.atan2(new_p[1] - old_p[1], new_p[0] - old_p[0])

      return pi_90 - pi_ac
    }
  },
  mounted () {
    this.initLayer()
  }
}
</script>

<style lang="scss" scoped>
</style>

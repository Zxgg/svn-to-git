
import { getArea, getDistance } from 'ol/sphere.js'
import { transform } from 'ol/proj'
import { Circle as CircleStyle, Fill, Stroke, Style } from 'ol/style'
import Draw from 'ol/interaction/Draw'
import { Vector as VectorLayer } from 'ol/layer'
import { Vector as VectorSource } from 'ol/source'
import { unByKey } from 'ol/Observable'
import Overlay from 'ol/Overlay'
import { LineString, Polygon } from 'ol/geom'
export const formatArea = (polygon) => {
  var area = getArea(polygon, {
    projection: 'EPSG:4326'
  })
  var output
  if (area > 10000) {
    output = (Math.round(area / 1000000 * 100) / 100) + ' ' + 'km<sup>2<sup>'
  } else {
    output = (Math.round(area * 100) / 100) + ' ' + 'm<sup>2<sup>'
  }
  return output
}
export const formatLength = (line, sourceProj) => {
  var length
  var coordinates = line.getCoordinates()
  length = 0

  for (var i = 0, ii = coordinates.length - 1; i < ii; ++i) {
    var c1 = transform(coordinates[i], sourceProj, 'EPSG:4326')
    var c2 = transform(coordinates[i + 1], sourceProj, 'EPSG:4326')
    length += getDistance(c1, c2)
  }
  var output
  if (length > 100) {
    output = (Math.round(length / 1000 * 100) / 100) + ' ' + 'km' // 换算成KM单位
  } else {
    output = (Math.round(length * 100) / 100) + ' ' + 'm' // m为单位
  }
  return output
}
export var measure_vectorSource = (measuresource) => {
  var measureVector = new VectorLayer({
    source: measuresource,
    style: new Style({ // 图层样式
      fill: new Fill({
        color: 'rgba(255, 255, 255, 0.2)' // 填充颜色
      }),
      stroke: new Stroke({
        color: '#ffcc33', // 边框颜色
        width: 2 // 边框宽度
      }),
      image: new CircleStyle({
        radius: 7,
        fill: new Fill({
          color: '#ffcc33'
        })
      })
    })
  })
  return measureVector
}
const measure = {
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
      measuresource: null,
      measureLayer: null
    }
  },
  mounted () {
    this.initMeasureLayer()
  },
  methods: {
    initMeasureLayer () {
      let me = this
      me.measuresource = new VectorSource()
      me.measureLayer = new VectorLayer({
        name: '标绘',
        source: me.measuresource,
        style: new Style({ // 图层样式
          fill: new Fill({
            color: 'rgba(255, 255, 255, 0.2)' // 填充颜色
          }),
          stroke: new Stroke({
            color: '#ffcc33', // 边框颜色
            width: 2 // 边框宽度
          }),
          image: new CircleStyle({
            radius: 7,
            fill: new Fill({
              color: '#ffcc33'
            })
          })
        })
      })
      me.map.addLayer(me.measureLayer)
    },
    measure (type) {
      this.map.on('pointermove', this.pointerMoveHandler)
      this.map.getViewport().addEventListener('mouseout', this.map_mouseout)
      if (type) {
        this.interaction = new Draw({
          source: this.measuresource,
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
  }
}
export default measure
import Draw, { createBox } from 'ol/interaction/Draw'
import { Vector as VectorLayer } from 'ol/layer'
import { Circle as CircleStyle, Fill, Stroke, Style } from 'ol/style'
import { Vector as VectorSource } from 'ol/source'
const mapDraw = {
  data () {
    return {
      draw: null,
      drawsource: null,
      drawLayer: null,
    }
  },
  mounted () {
    this.initDrawLayer()
  },
  methods: {
    initDrawLayer () {
      let me = this
      me.drawsource = new VectorSource()
      me.drawLayer = new VectorLayer({
        name: '标绘',
        source: me.drawsource,
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
      me.map.addLayer(me.drawLayer)
    },
    drawMap (typeValue) {
      if (this.draw) { this.map.removeInteraction(this.draw) }
      this.drawgraph(typeValue)
      this.map.addInteraction(this.draw)
      this.draw.on('drawend', this.drawend_query)
      // this.cameraFeature = drawend_query(this.draw, this.cameraSource);
    },
    async drawend_query (evt) {
      var polygon = await evt.feature.getGeometry();
      // var extent = polygon.getExtent();
      console.log(polygon.flatCoordinates, '面积')
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
        source: this.drawsource,
        type: typeValue,
        geometryFunction: geometryFunction,
        maxPoints: maxPoints
      })
    },
  }
}
export default mapDraw
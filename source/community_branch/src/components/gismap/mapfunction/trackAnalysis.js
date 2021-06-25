import { Vector as VectorLayer } from 'ol/layer'
import { Vector as VectorSource } from 'ol/source'
import Feature from 'ol/Feature'
import { routeStyle } from '@/components/gismap/mapfunction/vectorStyle'
import { LineString, Point } from 'ol/geom'
import { getVectorContext } from 'ol/render'
import View from 'ol/View'
import { getCenter } from 'ol/extent'
import configInfo from '@/webconfig/configInfo'
const track = {
  data () {
    return {
      tr_source: null,
      tr_layer: null,
      routeCoords: null,
      routeLength: null,
      geoMarker: null,
      startMarker: null,
      endMarker: null,
      animating: null,
      now: null,
      speed: 1,
    }
  },
  mounted () {
    this.initTrackLayer()
    // this.track()
  },
  methods: {
    initTrackLayer () {
      let self = this
      self.tr_source = new VectorSource()
      self.tr_layer = new VectorLayer()
      self.tr_layer.setSource(self.tr_source)
      self.tr_layer.setStyle(this.routeLayerStyle)
      self.map.addLayer(self.tr_layer)
    },
    track (vehicleTrack) {
      let me = this
      me.tr_source.clear()
      let polyline = []
      // let coordinate = [
      //   [119.43752616375383, 32.40796774612293],
      //   [119.4343748182417, 32.430428226484075],
      //   [119.4233884901167, 32.44845267106415],
      //   [119.41452913714232, 32.48274978585656]
      // ];
      // for (let i = 0; i < coordinate.length; i++) {
      //   polyline.push(coordinate[i]);
      // }
      vehicleTrack.forEach(vehicle => {
        let feature = me.vehicleFeatures.find(p => p.getProperties().sn === vehicle.sn)
        polyline.push(feature.getGeometry().getCoordinates())
      })
      console.log(polyline, '1')
      let route = new LineString(polyline)
      me.routeCoords = route.getCoordinates()
      me.routeLength = me.routeCoords.length
      let routeFeature = new Feature({ type: 'route', geometry: route })
      me.geoMarker = new Feature({ type: 'geoMarker', geometry: new Point(this.routeCoords[0]) })
      me.startMarker = new Feature({ type: 'icon', geometry: new Point(this.routeCoords[0]) })
      me.endMarker = new Feature({ type: 'icon', geometry: new Point(this.routeCoords[this.routeLength - 1]) })
      me.tr_source.addFeatures([routeFeature, me.geoMarker, me.startMarker, me.endMarker])
      me.map.setView(new View({
        center: getCenter(route.getExtent()), // 格式：[x,y]
        // center: [119.41919061230607, 32.43940449673803], // 格式：[x,y]
        zoom: 11,
        minZoom: 8,
        maxZoom: 18,
        projection: configInfo.gisServer.projection
      }))
      this.startAnimation()
    },
    startAnimation () {
      if (this.animating) {
        this.stopAnimation(false)
      } else {
        this.animating = true
        this.now = new Date().getTime()
        this.geoMarker.setStyle(null)
        this.tr_layer.on('postrender', this.moveFeature)
        this.map.render()
      }
    },
    stopAnimation (ended) {
      this.animating = false
      var coord = ended ? this.routeCoords[this.routeLength - 1] : this.routeCoords[0]
      var geometry = this.geoMarker.getGeometry()
      geometry.setCoordinates(coord)
      this.tr_layer.un('postrender', this.moveFeature)
    },
    moveFeature (event) {
      var vectorContext = getVectorContext(event)
      var frameState = event.frameState
      if (this.animating) {
        var elapsedTime = frameState.time - this.now
        var index = Math.round((this.speed * elapsedTime) / 1000)
        if (index >= this.routeLength) {
          this.stopAnimation(true)
          return
        }
        var currentPoint = new Point(this.routeCoords[index])
        var feature = new Feature(currentPoint)
        vectorContext.drawFeature(feature, routeStyle().geoMarker)
      }
      this.map.render()
    },
    routeLayerStyle (feature) {
      if (this.animating && feature.get('type') === 'geoMarker') {
        return null
      }
      return routeStyle()[feature.get('type')]
    },
  }
}
export default track
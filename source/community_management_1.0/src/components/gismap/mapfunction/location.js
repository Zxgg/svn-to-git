import View from 'ol/View'
import { getCenter } from 'ol/extent'
import { unByKey } from 'ol/Observable'
import { easeOut } from 'ol/easing'
import { Circle as CircleStyle, Stroke, Style } from 'ol/style'
import { getVectorContext } from 'ol/render'
import configInfo from '@/webconfig/configInfo'
import { Vector as VectorSource } from 'ol/source'
import Feature from 'ol/Feature'
import { Vector as VectorLayer } from 'ol/layer'
import { Point } from 'ol/geom'
import { createOneStyle } from '@/components/gismap/mapfunction/vectorStyle'
// export const getLocationView = () => {
//   let view = new View({
//     // center: getCenter(cameraItem.getGeometry().getExtent()), // 格式：[x,y]
//     center: [119.41919061230607, 32.43940449673803], // 格式：[x,y]
//     zoom: 13,
//     minZoom: 10,
//     maxZoom: 18,
//     projection: 'EPSG:4326'
//   })
//   return view
// }
// export const flash = (feature, tileLayer, map) => {
//   // feature.setStyle(locationStyle(feature))
//   // console.log(tileLayer)
//   // alert(1)
//   let duration = 3000
//   let start = new Date().getTime()
//   let listenerKey = tileLayer.on('postrender', animate)

//   function animate (event) {
//     let vectorContext = getVectorContext(event)
//     let frameState = event.frameState
//     let flashGeom = feature.getGeometry().clone()
//     let elapsed = frameState.time - start
//     let elapsedRatio = elapsed / duration
//     // radius will be 5 at start and 30 at end.
//     let radius = easeOut(elapsedRatio) * 25 + 5
//     let opacity = easeOut(1 - elapsedRatio)

//     let style = new Style({
//       image: new CircleStyle({
//         radius: radius,
//         stroke: new Stroke({
//           color: 'rgba(255, 0, 0, ' + opacity + ')',
//           width: 2 + opacity
//         })
//       })
//     })

//     vectorContext.setStyle(style)
//     vectorContext.drawGeometry(flashGeom)
//     if (elapsed > duration) {
//       unByKey(listenerKey)
//       return
//     }
//     // tell OpenLayers to continue postrender animation
//     map.render()
//   }
// }
const location = {
  data () {
    return {
      lc_source: null,
      lc_layer: null,
      locationFeature: null
    }
  },
  mounted () {
    this.initLocationLayer()
    // this.location()
  },
  methods: {
    initLocationLayer () {
      let self = this
      self.lc_source = new VectorSource()
      self.lc_layer = new VectorLayer()
      self.lc_layer.setSource(self.lc_source)
      self.lc_layer.setStyle(createOneStyle)
      self.map.addLayer(self.lc_layer)
    },
    location (data, element) {
      let me = this
      let me_data = {
        pop: null,
        data: null
      }
      me.lc_source.clear()
      let feature = me.cameraFeatures.find(p => p.getProperties().cameraSn === data.cameraSn)
      if (feature) {
        me.map.setView(new View({
          center: getCenter(feature.getGeometry().getExtent()), // 格式：[x,y]
          // center: [119.41919061230607, 32.43940449673803], // 格式：[x,y]
          zoom: 13,
          minZoom: 8,
          maxZoom: 18,
          projection: configInfo.gisServer.projection
        }))
        me.locationFeature = new Feature({ geometry: new Point(feature.getGeometry().getCoordinates()), name: feature.get('name') })
        me.locationFeature.setProperties(data)
        me.lc_source.addFeature(me.locationFeature)
        me.flash()
        me_data.pop = this.popOverlay(element)
        me.map.addOverlay(me_data.pop)
        let coordinate = feature.getGeometry().getCoordinates()
        me_data.pop.setPosition(coordinate)
        me_data.data = feature
      }
      return me_data
      // me.map.addLayer(me.lc_layer)
    },
    flash () {
      let duration = 3000
      let start = new Date().getTime()
      let listenerKey = this.lc_layer.on('postrender', (event) => {
        let vectorContext = getVectorContext(event)
        let frameState = event.frameState
        let flashGeom = this.locationFeature.getGeometry().clone()
        let elapsed = frameState.time - start
        let elapsedRatio = elapsed / duration
        // radius will be 5 at start and 30 at end.
        let radius = easeOut(elapsedRatio) * 25 + 5
        let opacity = easeOut(1 - elapsedRatio)
        let style = new Style({
          image: new CircleStyle({
            radius: radius,
            stroke: new Stroke({
              color: 'rgba(255, 0, 0, ' + opacity + ')',
              width: 2 + opacity
            })
          })
        })
        vectorContext.setStyle(style)
        vectorContext.drawGeometry(flashGeom)
        if (elapsed > duration) {
          unByKey(listenerKey)
          return
        }
        // tell OpenLayers to continue postrender animation
        this.map.render()
      })
    }
  }
}
export default location
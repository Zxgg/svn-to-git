import Map from 'ol/Map'
import View from 'ol/View'
import * as control from 'ol/control'
import { ScaleLine, OverviewMap } from 'ol/control'
import Overlay from 'ol/Overlay'
import { mapIserver, tianditu, baidu } from '@/components/gismap/mapfunction/tileLayer'
import { areaVectorStyle, areaVectorHighStyle } from '@/components/gismap/mapfunction/vectorStyle'
import configInfo from '@/webconfig/configInfo'
const initMap = {
  data () {
    return {
      map: null,
      // 地图View展示选项配置
      viewOptions: {
        projection: 'EPSG:4326', // 坐标系
        // center: [102.87855327334883, 29.95539168988012], // [104.089175, 30.650451]
        center: [119.41557789746531, 32.40390654374482],
        zoom: 10,
        minZoom: 11,
        maxZoom: 18,
        // extent: [101.9004807124176, 28.837774208959473, 103.38848561448468, 30.9210929107385], // [minX, minY, maxX, maxY]
        // minZoom: 6, // 缩放最小级别
        // maxZoom: 19 // 缩放最大级别
      },
      url3: 'rtmp://10.129.252.49:1935/aaaa/aabb',
    }
  },
  mounted () {
    this.initMap()
  },
  methods: {
    // 加载地图底图
    initMap () {
      let me = this
      me.map = new Map({
        target: 'map',
        controls: control.defaults({ attribution: false, zoom: false, rotate: false }),
        view: new View({
          center: [119.5145228209552, 32.66119250081479],
          zoom: 10,
          minZoom: 8,
          maxZoom: 18,
          projection: 'EPSG:4326'
        })
      })
      switch (configInfo.gisServer.mapType) {
        case 'iserver':
          me.layer = mapIserver()
          me.map.addLayer(me.layer); break
        case 'tianditu':
          me.layer = tianditu('vec_w')
          me.layer_ = tianditu('cva_w')
          me.map.addLayer(me.layer)
          me.map.addLayer(me.layer_); break
        case 'baidu':
          me.layer = baidu()
          me.map.addLayer(me.layer); break
        // case 'geoserver': sss = 1; break
      }
      var scaleContol = new ScaleLine()
      me.map.addControl(scaleContol)// 比例尺
      // me.overViewMap()//鹰眼图
      me.map.on('click', this.onClick)
      me.map.on('pointermove', this.moveMapHandle)
      me.map.updateSize()
    },
    overViewMap () {
      let overviewMap = null
      switch (configInfo.gisServer.mapType) {
        case 'iserver':
          overviewMap = new OverviewMap({
            view: new View({
              projection: 'EPSG:4326',
              multiWorld: true
            }),
            className: 'ol-overviewmap ol-custom-overviewmap',
            collapsed: true,
            layers: [mapIserver()]
          }); break
        case 'tianditu':
          overviewMap = new OverviewMap({
            view: new View({
              projection: 'EPSG:4326',
              multiWorld: true
            }),
            className: 'ol-overviewmap ol-custom-overviewmap',
            collapsed: true,
            layers: [tianditu('vec_w'), tianditu('cva_w')]
          }); break
        case 'baidu':
          overviewMap = new OverviewMap({
            view: new View({
              projection: 'EPSG:4326',
              multiWorld: true
            }),
            className: 'ol-overviewmap ol-custom-overviewmap',
            collapsed: true,
            layers: [baidu()]
          }); break
      }
      this.map.addControl(overviewMap);
    },
    async moveMapHandle (event) {
      let me = this
      var pixel = me.map.getEventPixel(event.originalEvent)
      var hit = me.map.hasFeatureAtPixel(pixel)
      this.$refs.rootMap.style.cursor = hit ? 'pointer' : ''
      // me.map.removeOverlay(me.popup)
      // me.map.forEachFeatureAtPixel(pixel, (feature, layer) => {
      //   let features = feature.getProperties().features
      //   if (layer && layer.get('name') === 'camera' && features !== undefined && features.length < 2 && features[0].get('name') === 'device') {
      //     me.popup = me.popOverlay(this.featureElement)
      //     me.map.addOverlay(me.popup)
      //     let coordinates = features[0].getGeometry().getCoordinates()
      //     me.popup.setPosition(coordinates)
      //     this.layerFeature = features[0]
      //   }
      // })
    },
    onClick (e) {
      let me = this
      console.log(e.coordinate, 'e.coordinate')
      var pixel = me.map.getEventPixel(e.originalEvent)
      me.map.forEachFeatureAtPixel(pixel, (feature, layer) => {
        // if (me.highFeature) {
        //   me.highFeature.setStyle(areaVectorStyle)
        // }
        // if (feature) {
        //   let features = feature.getProperties().features
        //   console.log(features, 'features')
        //   if (features !== undefined && features.length < 2 && features[0].get('name') === 'device') {
        //     // me.popup = me.popOverlay(this.featureElement)
        //     // me.map.addOverlay(me.popup)
        //     // let coordinates = features[0].getGeometry().getCoordinates()
        //     // me.popup.setPosition(coordinates)
        //     // this.layerFeature = features[0]
        //     this.playVideo(features[0].get('cameraSn'))
        //   } else if (feature.getProperties().level === 'district') {
        //     me.highFeature = feature;
        //     feature.setStyle(areaVectorHighStyle)
        //     this.$Notice.$emit('communityData', feature.getProperties().name)
        //     // me.areaPopup = me.popOverlay(this.areaElement)
        //     // me.map.addOverlay(me.areaPopup)
        //     // // var coordinates = features[0].getGeometry().getCoordinates()
        //     // me.areaPopup.setPosition(feature.getProperties().center)
        //     // me.areaFeature = feature
        //   } else if (features !== undefined && features[0].get('name') === 'alarm') {
        //     this.getAlarmOverlay(features[0])
        //   }
        // }
      })
    },
    popOverlay (Element) {
      return new Overlay(({
        element: Element,
        autoPan: true,
        autoPanAnimation: {
          duration: 250
        },
      }))
    }
  }
}

export default initMap
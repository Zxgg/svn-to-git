import Map from 'ol/Map'
import View from 'ol/View'
import * as control from 'ol/control'
import { ScaleLine, OverviewMap } from 'ol/control'
import Overlay from 'ol/Overlay'
import { Vector as VectorSource } from "ol/source";
import { Vector as VectorLayer } from "ol/layer";
import Feature from "ol/Feature";
import { Point } from "ol/geom";
import DragPan from "ol/interaction/DragPan"
import { mapIserver, tianditu, baidu, offlineMap, wgs84togcj02 } from '@/components/gismap/mapfunction/tileLayer'
import { areaVectorStyle, areaVectorHighStyle } from '@/components/gismap/mapfunction/vectorStyle'
import configInfo from '@/webconfig/configInfo'
import { getCenter } from 'ol/extent'
import Projection from 'ol/proj/Projection';
import Static from 'ol/source/ImageStatic';
import ImageLayer from 'ol/layer/Image';
import { transform } from 'ol/proj'
import { distributeLayer } from '@/components/gismap/mapfunction/initLayer'
const initMap = {
  data () {
    return {
      map: null,
      // 地图View展示选项配置
      viewOptions: {
        projection: 'EPSG:4326', // 坐标系
        // center: [102.87855327334883, 29.95539168988012], // [104.089175, 30.650451]
        // center: [119.41557789746531, 32.40390654374482], //扬州中心
        center: [119.4669345910234, 32.400444455080034], //扬州文昌社区
        zoom: 10,
        minZoom: 11,
        maxZoom: 18,
        // extent: [101.9004807124176, 28.837774208959473, 103.38848561448468, 30.9210929107385], // [minX, minY, maxX, maxY]
        // minZoom: 6, // 缩放最小级别
        // maxZoom: 19 // 缩放最大级别
      },
      url3: 'rtmp://10.129.252.49:1935/aaaa/aabb',
      CoordinateUtil: {
        x_pi: (3.14159265358979324 * 3000.0) / 180.0,
        //pai
        pi: 3.1415926535897932384626,
        //离心率
        ee: 0.00669342162296594323,
        //长半轴
        a: 6378245.0,
        //84转国测局
        wgs84togcj02: (lng, lat) => {
          var dlat = this.CoordinateUtil.transformlat(lng - 105.0, lat - 35.0);
          var dlng = this.CoordinateUtil.transformlng(lng - 105.0, lat - 35.0
          );
          var radlat = (lat / 180.0) * this.CoordinateUtil.pi;
          var magic = Math.sin(radlat);
          magic = 1 - this.CoordinateUtil.ee * magic * magic;
          var sqrtmagic = Math.sqrt(magic);
          dlat =
            (dlat * 180.0) /
            (((this.CoordinateUtil.a * (1 - this.CoordinateUtil.ee)) /
              (magic * sqrtmagic)) *
              this.CoordinateUtil.pi);
          dlng =
            (dlng * 180.0) /
            ((this.CoordinateUtil.a / sqrtmagic) *
              Math.cos(radlat) *
              this.CoordinateUtil.pi);
          var mglat = lat + dlat;
          var mglng = lng + dlng;
          return [mglng, mglat];
        },
        //经度转换
        transformlat: (lng, lat) => {
          var ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
          ret += ((20.0 * Math.sin(6.0 * lng * this.CoordinateUtil.pi) + 20.0 * Math.sin(2.0 * lng * this.CoordinateUtil.pi)) * 2.0) / 3.0;
          ret += ((20.0 * Math.sin(lat * this.CoordinateUtil.pi) + 40.0 * Math.sin((lat / 3.0) * this.CoordinateUtil.pi)) * 2.0) / 3.0;
          ret += ((160.0 * Math.sin((lat / 12.0) * this.CoordinateUtil.pi) + 320 * Math.sin((lat * this.CoordinateUtil.pi) / 30.0)) * 2.0) / 3.0;
          return ret;
        },
        //纬度转换
        transformlng: (lng, lat) => {
          var ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
          ret += ((20.0 * Math.sin(6.0 * lng * this.CoordinateUtil.pi) + 20.0 * Math.sin(2.0 * lng * this.CoordinateUtil.pi)) * 2.0) / 3.0;
          ret += ((20.0 * Math.sin(lng * this.CoordinateUtil.pi) + 40.0 * Math.sin((lng / 3.0) * this.CoordinateUtil.pi)) * 2.0) / 3.0;
          ret += ((150.0 * Math.sin((lng / 12.0) * this.CoordinateUtil.pi) + 300.0 * Math.sin((lng / 30.0) * this.CoordinateUtil.pi)) * 2.0) / 3.0;
          return ret;
        }
      },
      eventthingsList: [],
    }
  },
  mounted () {
    this.initMap()
  },
  methods: {
    // 加载地图底图
    initMap () {
      let me = this
      // var center = transform([119.4669345910234, 32.400444455080034], 'EPSG:4326', 'EPSG:4326');
      // var extent = [
      //   center[0] - 550 * 1000 / 2,
      //   center[1] - 344 * 1000 / 2,
      //   center[0] + 550 * 1000 / 2,
      //   center[1] + 344 * 1000 / 2
      // ]
      // var projection = new Projection({
      //   code: 'xkcd-image',
      //   units: 'pixels',
      //   extent: extent,
      // });
      // let imageLayer = new ImageLayer({
      //   source: new Static({
      //     attributions: '© <a href="http://xkcd.com/license.html">xkcd</a>',
      //     url: '/bg.png',
      //     projection: projection,
      //     imageExtent: extent,
      //   }),
      // })
      me.map = new Map({
        target: 'map',
        controls: control.defaults({ attribution: false, zoom: false, rotate: false }),
        view: new View({
          // projection: projection,
          // center: getCenter(extent),
          // center: [119.5145228209552, 32.66119250081479], //扬州中心
          center: [119.4669345910234, 32.400444455080034], //扬州文昌社区
          zoom: 16.5,
          minZoom: 16.5,
          maxZoom: 16.5,
          projection: 'EPSG:4326'
        })
      })
      let pan = this.getPan();//地图拖动事件
      pan.setActive(false);
      if (configInfo.gisServer.mapType === 'gaode') me.map.getView().setCenter(this.CoordinateUtil.wgs84togcj02(119.4655345910234, 32.400444455080034))
      else me.map.getView().setCenter([119.4669345910234, 32.400444455080034])
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
        case 'gaode':
        // me.layer = offlineMap()
        // me.layer.className = "ol-layer-gaode"
        // me.layer.setOpacity(0.2)
        // me.map.addLayer(me.layer); break
      }
      var scaleContol = new ScaleLine()
      // me.map.addLayer(imageLayer)
      me.map.addControl(scaleContol)// 比例尺  
      // let testLayer = new VectorLayer({
      //   source: new VectorSource(),
      //   style: new Style({
      //     image: new Icon({
      //       src: "/bg.png",
      //       scale: 1
      //     })
      //   })
      // });
      // let testFeature = new Feature({
      //   geometry: new Point([119.46616, 32.40008]),
      //   // geometry: new Point([119.65049587971161, 29.084691836765526]),
      // });
      // testLayer.getSource().addFeature(testFeature)
      // me.map.addLayer(testLayer)
      // me.overViewMap()//鹰眼图
      me.distributeSource = new VectorSource()
      me.map.addLayer(distributeLayer(me.distributeSource, 'distribute'))
      me.map.on('click', this.onClick)
      me.map.on('pointermove', this.moveMapHandle)
      me.map.updateSize()
    },
    getPan () {
      let pan = null
      this.map.getInteractions().forEach(element => {
        if (element instanceof DragPan) {
          pan = element
        }
      })
      return pan
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
      me.map.removeOverlay(me.popup)
      me.map.forEachFeatureAtPixel(pixel, (feature, layer) => {
        if (feature.get('city_name') != '广陵区') {
          // console.log(feature, 'feature-device')
        }
        let features = feature.getProperties().features
        if (layer.get('name') === 'camera' && features !== undefined && features.length < 2 && features[0].get('name') === 'device') {
          me.popup = me.popOverlay(this.featureElement)
          me.map.addOverlay(me.popup)
          let coordinates = features[0].getGeometry().getCoordinates()
          me.popup.setPosition(coordinates)
          this.layerFeature = features[0]
        }
        else if (feature.get('name') === 'device') {
          me.popup = me.popOverlay(this.featureElement)
          me.map.addOverlay(me.popup)
          let coordinates = feature.getGeometry().getCoordinates()
          me.popup.setPosition(coordinates)
          this.layerFeature = feature
        }
      })
    },
    onClick (e) {
      let me = this
      console.log(e.coordinate, 'e.coordinate')
      var pixel = me.map.getEventPixel(e.originalEvent)
      me.map.forEachFeatureAtPixel(pixel, (feature, layer) => {
        if (me.highFeature) {
          me.highFeature.setStyle(areaVectorStyle)
        }
        if (feature) {
          let features = feature.getProperties().features
          // console.log(features, 'features')
          if (features !== undefined && features.length < 2 && features[0].get('name') === 'device') {
            // me.popup = me.popOverlay(this.featureElement)
            // me.map.addOverlay(me.popup)
            // let coordinates = features[0].getGeometry().getCoordinates()
            // me.popup.setPosition(coordinates)
            // this.layerFeature = features[0]
            this.playVideo(features[0].get('cameraSn'))
          } else if (feature.get('name') === 'device') {
            // me.popup = me.popOverlay(this.featureElement)
            // me.map.addOverlay(me.popup)
            // let coordinates = feature.getGeometry().getCoordinates()
            // me.popup.setPosition(coordinates)
            // this.layerFeature = feature
            this.playVideo(feature.get('cameraSn'))
          } else if (feature.get('featureName') === 'grid') {
            // me.highFeature = feature;
            // feature.setStyle(areaVectorHighStyle)
            this.areaLayer1.getSource().clear();
            this.areaLayer1.getSource().addFeature(feature)
            // this.$Notice.$emit('communityData', feature.getProperties().name)
            // console.log(this.$route.name, 'this.$route.name')
            if (this.$route.name === 'peopleinfo') {
              this.$Notice.$emit('communityGrid', feature.getProperties().code)
              this.isReturn = true
            }
            // me.areaPopup = me.popOverlay(this.areaElement)
            // me.map.addOverlay(me.areaPopup)
            // // var coordinates = features[0].getGeometry().getCoordinates()
            // let point = feature.getGeometry().getInteriorPoint()
            // me.areaPopup.setPosition([point.flatCoordinates[0], point.flatCoordinates[1]])
            // me.areaFeature = feature
          } else if (features !== undefined && features[0].get('name') === 'alarm') {
            this.getAlarmOverlay(features[0])
          }
          // else if (features !== undefined && features.length > 1 && features.length < 11 && layer.get('name') === 'camera') {
          //   features.forEach(feature => {
          //     layer.getSource().getSource().removeFeature(feature)
          //   })
          //   me.distributeSource.addFeatures(features)
          //   me.map.on('moveend', (e) => {
          //     this.secondCluster()
          //     me.distributeSource.clear()
          //   })
          // }
        }
      })
    },
    secondCluster () {
      const me = this
      me.map.getLayers().forEach(layer => {
        switch (layer.get('name')) {
          case 'camera': me.distributeSource.getFeatures().forEach(feature => {
            layer.getSource().getSource().addFeature(feature)
          }); break
          case 'hemisphereLayer': me.distributeSource.getFeatures().forEach(feature => {
            if (feature.getProperties().type === '半球固定摄像机') layer.getSource().getSource().addFeature(feature)
          }); break
          case 'YuntaiLayer': me.distributeSource.getFeatures().forEach(feature => {
            if (feature.getProperties().type === '有云台枪机') layer.getSource().getSource().addFeature(feature)
          }); break
          case 'cylinderLayer': me.distributeSource.getFeatures().forEach(feature => {
            if (feature.getProperties().type === '筒机') layer.getSource().getSource().addFeature(feature)
          }); break
          case 'ballLayer': me.distributeSource.getFeatures().forEach(feature => {
            if (feature.getProperties().type === '球机') layer.getSource().getSource().addFeature(feature)
          }); break
          default:
            break
        }
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
    },
  }
}

export default initMap
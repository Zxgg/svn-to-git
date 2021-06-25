import { Cluster, Vector as VectorSource } from 'ol/source'
import { Vector as VectorLayer } from 'ol/layer'
import { deviceClusterStyle, alarmClusterStyle, carClusterStyle, doorClusterStyle, faceClusterStyle } from '@/components/gismap/mapfunction/vectorStyle'
const cluster = {
  data () {
    return {
      cameraSource: null,
      alarmSource: null,
      cameraLayer: null,
      alarmLayer: null,
      faceSource: null,
      faceLayer: null,
      doorSource: null,
      doorLayer: null,
      vehicleSource: null,
      vehicleLayer: null
    }
  },
  mounted () {
    this.cluster()
  },
  methods: {
    cluster () {
      let me = this
      me.cameraSource = new Cluster({
        distance: 40,
        source: new VectorSource(),
        wrapX: false
      })
      me.cameraLayer = new VectorLayer({
        name: 'camera',
        source: me.cameraSource,
        style: deviceClusterStyle
      })
      me.map.addLayer(me.cameraLayer)//摄像机图层
      me.alarmSource = new Cluster({
        distance: 40,
        source: new VectorSource(),
        wrapX: false
      })
      me.alarmLayer = new VectorLayer({
        name: 'alarm',
        source: me.alarmSource,
        style: alarmClusterStyle
      })
      me.map.addLayer(me.alarmLayer)//告警图层
      me.faceSource = new Cluster({
        distance: 40,
        source: new VectorSource(),
        wrapX: false
      })
      me.faceLayer = new VectorLayer({
        name: 'face',
        source: me.faceSource,
        style: faceClusterStyle
      })
      me.map.addLayer(me.faceLayer)//人脸图层
      me.doorSource = new Cluster({
        distance: 40,
        source: new VectorSource(),
        wrapX: false
      })
      me.doorLayer = new VectorLayer({
        name: 'door',
        source: me.doorSource,
        style: doorClusterStyle
      })
      me.map.addLayer(me.doorLayer)//门禁图层
      me.vehicleSource = new Cluster({
        distance: 40,
        source: new VectorSource(),
        wrapX: false
      })
      me.vehicleLayer = new VectorLayer({
        name: 'vehicle',
        source: me.vehicleSource,
        style: carClusterStyle
      })
      me.map.addLayer(me.vehicleLayer)//车辆图层
    },
  }
}
export default cluster
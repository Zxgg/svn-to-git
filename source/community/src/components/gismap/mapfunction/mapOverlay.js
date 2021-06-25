import Overlay from 'ol/Overlay'
const mapOverlay = {
  data () {
    return {
      alarmElement: null,
    }
  },
  mounted () {

  },
  methods: {
    getAlarmOverlay (feature) {
      let me = this
      // let abnormalFeatures = this.alarmSource.getSource().getFeatures();
      this.overlay = this.alarmOverlay(this.abnormalElement)
      this.map.addOverlay(this.overlay)
      let coordinate = feature.getGeometry().getCoordinates()
      this.overlay.setPosition(coordinate)
      this.abnormalFeature = feature
      // abnormalFeatures.forEach(feature => {
      //   let alarmElement = document.createElement('div')
      //   alarmElement.className = feature.get('id')
      //   alarmElement.appendChild(this.abnormalElement)

      //   console.log(this.abnormalFeature, 'this.abnormalFeature')
      // });
    },
    alarmOverlay (element) {
      return new Overlay(({
        element: element,
        autoPan: true,
        autoPanAnimation: {
          duration: 250
        }
      }))
    },
  }
}
export default mapOverlay
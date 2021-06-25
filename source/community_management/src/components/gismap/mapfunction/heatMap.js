import VectorSource from 'ol/source/Vector'
import { Heatmap as HeatmapLayer } from 'ol/layer'
import GeoJSON from 'ol/format/GeoJSON'
export const heatMap = () => {
  var vector = new HeatmapLayer({
    source: new VectorSource({
      features: (new GeoJSON()).readFeatures(geojsonObject())
    }),
    blur: parseInt(15, 10),
    radius: parseInt(10, 10)
    // weight: function (feature) {
    //   var name = feature.get('name')
    //   var magnitude = parseFloat(name.substr(2))
    //   return magnitude   5
    // }
  })
  return vector
}
export const geojsonObject = () => {
  var heatData = {
    type: 'FeatureCollection',
    features: [
      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.042007, 37.349998] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.660004, 37.423000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.315994, 37.721001] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.288002, 37.627998] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.055000, 37.826000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.761002, 37.903999] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.154999, 37.700001] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.272995, 37.776001] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.075104, 37.852959] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.016006, 37.659000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.274067, 37.725273] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.575996, 37.729000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.283333, 37.944908] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.078003, 37.070000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.841003, 37.639999] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.035004, 37.930000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.995026, 37.815025] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.248001, 37.297001] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.952003, 37.474998] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.207001, 37.472000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.291000, 37.851002] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.099998, 37.299999] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.638000, 37.523998] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.702003, 37.402000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.500000, 37.830002] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.473999, 37.883999] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.373001, 37.412998] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.228996, 37.625000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.848999, 37.701000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.934654, 37.166161] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.297997, 37.792000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.054993, 37.158001] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.331871, 37.707264] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.598000, 37.864000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.147949, 37.497187] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.080513, 37.630220] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.550000, 37.720001] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.831787, 37.268858] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.935249, 37.542351] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.320986, 37.202118] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.449841, 37.622501] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.368000, 37.837002] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.826080, 37.157536] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.973783, 37.282158] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.907997, 37.153000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.591003, 37.863998] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.310997, 37.364000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.500000, 37.500000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.916702, 37.516700] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.448002, 37.773998] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.700001, 37.083332] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.316700, 37.883301] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.933304, 37.950001] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.761993, 37.410000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.216751, 37.568726] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.225220, 37.773746] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.934000, 37.633999] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.638000, 37.402000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.178000, 37.785999] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.233002, 37.266998] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.374001, 37.702999] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.983002, 37.083000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.532997, 37.167000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.699997, 37.833000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.282997, 37.333000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.217003, 37.449997] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.000000, 37.016998] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.667000, 37.016998] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.033001, 37.616997] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.783001, 37.233002] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.899994, 37.817001] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.333000, 37.783001] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.000000, 37.000000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.708000, 37.677002] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.553001, 37.244003] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.705994, 37.365997] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.040001, 37.504997] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.895000, 37.135002] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.897995, 37.202999] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.810997, 37.800999] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.309998, 37.320999] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.971000, 37.423000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.311001, 37.622002] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.123001, 37.692001] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.068001, 37.264000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.960999, 37.036999] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.104000, 37.962002] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.063000, 37.282001] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.371002, 37.889000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.807999, 37.659000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.653000, 37.944000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.173000, 37.542999] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.123001, 37.184999] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.507999, 37.219999] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.978000, 37.084000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.843002, 37.869000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.797000, 37.301000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.068000, 37.215000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.454000, 37.391000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.299000, 37.321000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.743999, 37.817000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.044998, 37.082000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.283001, 37.406000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.332001, 37.959000] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.659145, 37.259964] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.807220, 37.334835] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.599289, 37.493851] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.480255, 37.755272] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.991455, 37.331165] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.280418, 37.970155] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.281559, 37.295090] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.674783, 37.850159] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.541466, 37.755356] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.141739, 37.690456] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.412556, 37.799683] } },

      { 'type': 'Feature', 'properties': { 'name': 'EPSG:4326' }, 'geometry': { type: 'Point', 'coordinates': [118.400000, 37.500000] } }
    ]
  }
  return heatData
}

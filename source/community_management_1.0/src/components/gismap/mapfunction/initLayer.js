import { clusterStyle, sourceStyle } from '@/components/gismap/mapfunction/vectorStyle'
import { Cluster, Vector as VectorSource } from 'ol/source'
import { Vector as VectorLayer } from 'ol/layer'
import Feature from 'ol/Feature'
export const measureLayer = (source, name) => {
  let measureLayer = new VectorLayer({
    name: name,
    source: source,
    style: sourceStyle
  })
  return measureLayer
}
export const clusterSource = (distance) => {
  let clusterSource = new Cluster({
    distance: distance,
    source: new VectorSource(),
    wrapX: false
  })
  return clusterSource
}
export const clusterLayer = (source, name) => {
  let clusterLayer = new VectorLayer({
    name: name,
    source: source,
    style: clusterStyle
  })
  return clusterLayer
}
export const routeFeature = (type, point) => {
  var feature = new Feature({
    type: type,
    geometry: point
  })
  return feature
}

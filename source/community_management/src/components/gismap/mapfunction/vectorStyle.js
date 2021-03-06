import { Circle as CircleStyle, Fill, Stroke, Style, Text, Icon } from 'ol/style'
export const clusterStyle = (feature) => {
  var size = feature.get('features').length
  // var style = styleCache[size]
  let style
  if (size > 1) {
    style = new Style({
      // image: new CircleStyle({
      //   radius: 10,
      //   stroke: new Stroke({
      //     color: '#FFA500'
      //   }),
      //   fill: new Fill({
      //     color: '#FF8C00'
      //   })
      // }),
      // text: new Text({
      //   text: size.toString(),
      //   fill: new Fill({
      //     color: '#fff'
      //   })
      // })
      image: new Icon({
        src: '/deviceIcon.png',
        scale: 1
      })
    })
    // styleCache[size] = style
  } else {
    let oneFeature = feature.get('features')[0]
    style = createOneStyle(oneFeature)
  }
  return style
}
export const deviceClusterStyle = (feature) => {
  var size = feature.get('features').length
  let style
  if (size > 1) {
    style = new Style({
      image: new Icon({
        src: '/deviceIcon.png',
        scale: 1
      })
    })
  } else {
    let oneFeature = feature.get('features')[0]
    style = createOneStyle(oneFeature)
  }
  return style
}
export const alarmClusterStyle = (feature) => {
  var size = feature.get('features').length
  let style
  if (size > 1) {
    style = new Style({
      image: new Icon({
        src: '/alarmIcon.png',
        scale: 1
      })
    })
  } else {
    let oneFeature = feature.get('features')[0]
    style = createOneStyle(oneFeature)
  }
  return style
}
export const carClusterStyle = (feature) => {
  var size = feature.get('features').length
  let style
  if (size > 1) {
    style = new Style({
      image: new Icon({
        src: '/carIcon.png',
        scale: 1
      })
    })
  } else {
    let oneFeature = feature.get('features')[0]
    style = createOneStyle(oneFeature)
  }
  return style
}
export const doorClusterStyle = (feature) => {
  var size = feature.get('features').length
  let style
  if (size > 1) {
    style = new Style({
      image: new Icon({
        src: '/doorIcon.png',
        scale: 1
      })
    })
  } else {
    let oneFeature = feature.get('features')[0]
    style = createOneStyle(oneFeature)
  }
  return style
}
export const faceClusterStyle = (feature) => {
  var size = feature.get('features').length
  let style
  if (size > 1) {
    style = new Style({
      image: new Icon({
        src: '/faceIcon.png',
        scale: 1
      })
    })
  } else {
    let oneFeature = feature.get('features')[0]
    style = createOneStyle(oneFeature)
  }
  return style
}
export const createOneStyle = (feature) => {
  let imageStyle = getIconStyle(feature)
  return new Style({
    geometry: feature.getGeometry(),
    image: new Icon({
      src: imageStyle.src,
      scale: imageStyle.scale
    })
  })
}
export const getIconStyle = (feature) => {
  let image = {
    src: '',
    scale: 1
  }
  switch (feature.get('name')) {
    case 'device': image.src = '/deviceIcon.png'; image.scale = 1; break
    case 'alarm': image.src = '/alarmIcon.png'; image.scale = 1; break
    case 'door': image.src = '/doorIcon.png'; image.scale = 1; break
    case 'face': image.src = '/faceIcon.png'; image.scale = 1; break
    case 'vehicle': image.src = '/carIcon.png'; image.scale = 1; break
  }
  return image
}
export const sourceStyle = () => {
  let sourceStyle = new Style({ // ????????????
    fill: new Fill({
      color: 'rgba(255, 255, 255, 0.2)' // ????????????
    }),
    stroke: new Stroke({
      color: '#ffcc33', // ????????????
      width: 2 // ????????????
    }),
    image: new CircleStyle({
      radius: 7,
      fill: new Fill({
        color: '#ffcc33'
      })
    })
  })
  return sourceStyle
}
export const routeStyle = () => {
  var styles = {
    'route': new Style({
      stroke: new Stroke({
        width: 3,
        color: [250, 100, 0, 0.8],
        lineDash: [1, 2, 3, 4, 5, 6],
      })
    }),
    'icon': new Style({
      image: new Icon({
        anchor: [0.5, 1],
        src: 'data/icon.png'
      })
    }),
    'geoMarker': new Style({
      image: new CircleStyle({
        radius: 7,
        fill: new Fill({ color: 'black' }),
        stroke: new Stroke({
          color: 'white',
          width: 2
        })
      })
    })
  }
  return styles
}
export const realRoutePoint = () => {
  let style = new Style({
    image: new Icon({
      anchor: [0.5, 46],
      anchorXUnits: 'fraction',
      anchorYUnits: 'pixels',
      src: 'gisImage/dgtVehicle.png',
      scale: 0.1
    })
  })
  return style
}
export const monitorLineStyle = (color) => {
  let style = new Style({
    stroke: new Stroke({
      width: 2,
      color: color
      // lineDash: [10, 10],
    })
  })
  return style
}
export const monitorPointStyle = (src, scale, font, text) => {
  let style = new Style({
    // ??????????????????
    image: new Icon({
      src: src,
      // size: [50,50],
      // ?????????????????????????????? ??????[0,0]  ??????[0,1]  ??????[1???1]
      anchor: [0.5, 0.5],
      // ????????????????????????????????????
      scale: scale,
      // ????????????
      rotateWithView: true
      // rotation: 0,
    }),
    text: new Text({
      // ????????????
      textAlign: 'center',
      // ?????????
      textBaseline: 'middle',
      offsetY: -30,
      // ????????????
      font: font,
      // ????????????
      text: text,
      // ??????????????????
      fill: new Fill({
        color: 'rgba(255,255,255,1)'
      }),
      padding: [5, 5, 5, 5],
      backgroundFill: new Fill({
        color: 'rgba(0,0,255,0.6)'
      })
    })
  })
  return style
}
export const areaVectorStyle = () => {
  let areaVectorStyle = new Style({ // ????????????
    fill: new Fill({
      color: 'rgba(27, 148, 244, 0.2)'// ????????????
    }),
    stroke: new Stroke({
      color: '#1B94F4', // ????????????
      width: 2 // ????????????
    })
  })
  return areaVectorStyle
}
export const areaVectorHighStyle = () => {
  return new Style({
    fill: new Fill({
      color: 'rgba(0, 217, 255, 0.2)'// ????????????
    }),
    stroke: new Stroke({
      color: '#00D9FF', // ????????????
      width: 2 // ????????????
    })
  })
}
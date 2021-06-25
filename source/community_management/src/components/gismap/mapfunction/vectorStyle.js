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
  let sourceStyle = new Style({ // 图层样式
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
    // 设置一个标识
    image: new Icon({
      src: src,
      // size: [50,50],
      // 注意这个，竟然是比例 左上[0,0]  左下[0,1]  右下[1，1]
      anchor: [0.5, 0.5],
      // 这个直接就可以控制大小了
      scale: scale,
      // 开启转向
      rotateWithView: true
      // rotation: 0,
    }),
    text: new Text({
      // 对其方式
      textAlign: 'center',
      // 基准线
      textBaseline: 'middle',
      offsetY: -30,
      // 文字样式
      font: font,
      // 文本内容
      text: text,
      // 文本填充样式
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
  let areaVectorStyle = new Style({ // 图层样式
    fill: new Fill({
      color: 'rgba(27, 148, 244, 0.2)'// 填充颜色
    }),
    stroke: new Stroke({
      color: '#1B94F4', // 边框颜色
      width: 2 // 边框宽度
    })
  })
  return areaVectorStyle
}
export const areaVectorHighStyle = () => {
  return new Style({
    fill: new Fill({
      color: 'rgba(0, 217, 255, 0.2)'// 填充颜色
    }),
    stroke: new Stroke({
      color: '#00D9FF', // 边框颜色
      width: 2 // 边框宽度
    })
  })
}
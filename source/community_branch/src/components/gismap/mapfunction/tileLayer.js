import XYZ from 'ol/source/XYZ'
import TileGrid from 'ol/tilegrid/TileGrid'
import TileImage from 'ol/source/TileImage'
import TileLayer from 'ol/layer/Tile'
import { TileSuperMapRest } from '@supermap/iclient-ol'
import configInfo from '@/webconfig/configInfo'
export const mapIserver = () => {
  let layer = new TileLayer({
    source: new TileSuperMapRest({
      url: configInfo.gisServer.mapUrl
    }),
    projection: configInfo.gisServer.projection
  })
  return layer
}
export const tianditu = (lyr) => {
  let layer = new TileLayer({
    // title: "天地图路网",
    source: new XYZ({
      crossOrigin: 'anonymous',
      url: 'http://t4.tianditu.com/DataServer?T=' + lyr + '&x={x}&y={y}&l={z}&tk=0a16ba8b1f290b944ffbf68f6c038547'
    })
  })
  return layer
}
export const baidu = () => {
  let resolutions = []
  for (let i = 0; i < 19; i++) {
    resolutions[i] = Math.pow(2, 18 - i)
  }
  let tilegrid = new TileGrid({
    origin: [0, 0],
    resolutions: resolutions
  })
  let layer = new TileLayer({
    source: new TileImage({
      projection: 'EPSG:3857',
      tileGrid: tilegrid,
      tileUrlFunction: (tileCoord, pixelRatio, proj) => {
        if (!tileCoord) {
          return ''
        }
        let z = tileCoord[0]
        let x = tileCoord[1]
        let y = -tileCoord[2]

        if (x < 0) {
          x = 'M' + -x
        }
        if (y < 0) {
          y = 'M' + -y
        }
        return 'http://online3.map.bdimg.com/onlinelabel/?qt=tile&x=' + x + '&y=' + y + '&z=' + z + '&styles=pl&udt=20151021&scaler=1&p=1'
      }
    })
  })
  return layer
}
export const offlineMap = () => {
  let layer = new TileLayer({
    className: 'ol-layer-gaode',
    // title: "天地图路网",
    source: new XYZ({
      // tileUrlFunction: (tileCoord) => {
      //   // console.log(tileCoord, "tileCoord");
      //   var oo = "00000000";
      //   var zz = tileCoord[0];
      //   var z = "L" + zz;
      //   var xx = tileCoord[1].toString(16).toUpperCase();
      //   // console.log(xx, "xx");
      //   var x = "C" + oo.substring(0, 8 - xx.length) + xx;
      //   // console.log(x, "xxxxxxxxxxxxxxxx");
      //   var yy = tileCoord[2].toString(16).toUpperCase();
      //   // console.log(yy, "yy");
      //   var y = "R" + oo.substring(0, 8 - yy.length) + yy;
      //   // console.log(y, "yyyyyyyyyyyyyy");
      //   return configInfo.gisServer.offlineMap + z + "/" + y + "/" + x + ".png";

      // }
      url: configInfo.gisServer.offlineMap + '{z}/{x}/{y}.png'
    })
  })
  return layer
}
export const staticImage = () => {

  return imageLayer
}
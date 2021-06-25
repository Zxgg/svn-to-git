import Feature from 'ol/Feature'
import { Point } from 'ol/geom'
import { getDeviceList, getCameraList } from "@/api/smartVideo/device"
import configInfo from '@/webconfig/configInfo'
const getData = {
  data() {
    return {
      cameraFeatures: [],
      alarmFeatures: [],
      vehicleFeatures: [],
      faceFeatures: [],
      doorFeatures: [],
      gridMemberFeatures: []
    }
  },
  mounted() {
    // this.getDevice()
    this.getTestDevice()
    this.getTestGridMember()
    // this.getVehicle()
    // this.getFace()
    // this.getDoor()
  },
  methods: {
    getDevice() {
      let entity = { currentPage: 1, pageSize: 10240, body: { orgId: "32100000002107001186" } }
      getDeviceList(entity).then(response => {
        if (response && response.data.length > 0) {
          response.data.forEach(camera => {
            if (camera && camera.longitude > 0 && camera.latitude > 0) {
              let feature = (new Feature({
                geometry: new Point([camera.longitude, camera.latitude]),
                name: 'device',
                id: camera.id,
                cameraSn: camera.sn,
                cameraName: camera.captureName,
                cameraAddress: camera.monitorPointAddress
              }))
              this.cameraFeatures.push(feature)
            }
          })
          this.cameraSource.getSource().addFeatures(this.cameraFeatures)
        }
      }).catch(err => {
        console.log('err:', err)
      })
    },
    getTestDevice() {
      let captures = this.testData.map.captures
      let entity = { page: "1", limit: "2000" }
      getCameraList(entity).then(res => {
        if (res && res.data.cameralist.camera.length > 0) {
          captures.forEach(camera => {
            let sn = res.data.cameralist.camera.find(p => p.name == camera.captureName)?.sn
            let feature = (new Feature({
              geometry: new Point([camera.longitude, camera.latitude]),
              name: 'device',
              id: camera.id,
              cameraSn: sn,
              cameraName: camera.captureName,
              cameraAddress: camera.monitorPointAddress
            }))
            this.cameraFeatures.push(feature)
          });
          this.cameraSource.addFeatures(this.cameraFeatures)
        }
      })
      // if (configInfo.gisServer.mapType === 'gaode1') {
      //   let captures = this.testData.map.captures
      //   captures.forEach(camera => {
      //     if (camera && camera.longitude > 0 && camera.latitude > 0) {
      //       let feature = (new Feature({
      //         geometry: new Point(this.CoordinateUtil.wgs84togcj02(camera.longitude, camera.latitude)),
      //         name: 'device',
      //         id: camera.id,
      //         cameraSn: camera.sn,
      //         cameraName: camera.captureName,
      //         cameraAddress: camera.monitorPointAddress
      //       }))
      //       this.cameraFeatures.push(feature)
      //     }
      //   })
      //   this.cameraSource.addFeatures(this.cameraFeatures)
      //   // this.cameraSource.getSource().addFeatures(this.cameraFeatures)
      // } else {
      //   let captures = this.testData.map.captures

      //   captures.forEach(camera => {
      //     if (camera && camera.longitude > 0 && camera.latitude > 0) {
      //       let feature = (new Feature({
      //         geometry: new Point([camera.longitude, camera.latitude]),
      //         name: 'device',
      //         id: camera.id,
      //         cameraSn: camera.sn,
      //         cameraName: camera.captureName,
      //         cameraAddress: camera.monitorPointAddress
      //       }))
      //       this.cameraFeatures.push(feature)
      //     }
      //   })
      //   this.cameraSource.addFeatures(this.cameraFeatures)
      // this.cameraSource.getSource().addFeatures(this.cameraFeatures)
    },
    getTestGridMember() {
      let gridMembers = this.testData.map.gridMember
      gridMembers.forEach(gridMember => {
        if (gridMember && gridMember.longitude > 0 && gridMember.latitude > 0) {
          let feature = (new Feature({
            geometry: new Point([gridMember.longitude, gridMember.latitude]),
            name: 'gridMember',
            grid: gridMember.grid,
            gridName: gridMember.gridName,
            phoneNumber: gridMember.phoneNumber,
            gridBound: gridMember.gridBound,
            households: gridMember.households,
            permanent_people: gridMember.permanent_people,
            partymember: gridMember.partymember,
            gridArea: gridMember.gridArea,
            old80: gridMember.old80,
            disabled_person: gridMember.disabled_person,
            subsistenceallowances: gridMember.subsistenceallowances,
            population_people: gridMember.population_people,
            ethnicminority: gridMember.ethnicminority,
            pic: gridMember.pic,
          }))
          this.gridMemberFeatures.push(feature)
        }
      })
      this.gridMemberSource.addFeatures(this.gridMemberFeatures)
    },
    getAlarm() {
      this.alarmFeatures = []
      this.alarmSource.getSource().clear()
      let i = 0.000001
      this.community_eventList.forEach(alarm => {
        i = 2 * i + i * 2
        let data = {
          longitude: 119.31934937260907 + i,
          latitude: 32.5299851026287 + i,
          eventName: alarm.name,
          eventTime: alarm.createTime,
          id: alarm.id,
          eventAdrress: alarm.remark
        }
        let feature = (new Feature({
          geometry: new Point([data.longitude, data.latitude]),
          name: 'alarm',
          eventName: data.eventName,
          id: data.id,
          eventTime: data.eventTime,
          eventAdrress: data.eventAdrress
        }))
        this.alarmFeatures.push(feature)
      });
      this.alarmSource.getSource().addFeatures(this.alarmFeatures)
      setTimeout(() => {
        this.getAlarmOverlay(this.alarmSource.getSource().getFeatures()[0])
      }, 1);
    },
    getVehicle() {
      let vehicles = [
        {
          longitude: 119.40723999760907,
          latitude: 32.5904099073162,
          vehiclePlate: '京A.88888',
          id: '1',
          sn: '1'
        },
        {
          longitude: 119.60224732182782,
          latitude: 32.5739304151287,
          vehiclePlate: '京A.66666',
          id: '2',
          sn: '2'
        },
        {
          longitude: 119.49787720464032,
          latitude: 32.3871628370037,
          vehiclePlate: '京A.66666',
          id: '3',
          sn: '3'
        },
        {
          longitude: 119.48768414433455,
          latitude: 32.50709831458249,
          vehiclePlate: '京A.88888',
          id: '4',
          sn: '4'
        },
        {
          longitude: 119.68530986353967,
          latitude: 32.4622755143696,
          vehiclePlate: '京A.66666',
          id: '5',
          sn: '5'
        },
        {
          longitude: 119.21635254643718,
          latitude: 32.35145727059746,
          vehiclePlate: '京A.88888',
          id: '6',
          sn: '6'
        },
      ]
      vehicles.forEach(vehicle => {
        let feature = (new Feature({
          geometry: new Point([vehicle.longitude, vehicle.latitude]),
          name: 'vehicle',
          id: vehicle.id,
          sn: vehicle.sn,
          vehiclePlate: vehicle.vehiclePlate
        }))
        this.vehicleFeatures.push(feature)
      });
      this.vehicleSource.getSource().addFeatures(this.vehicleFeatures)
    },
    getFace() {
      let faces = [
        {
          longitude: 119.47041138432782,
          latitude: 32.90077367684745,
          peopleName: '张三',
          id: '1'
        },
        {
          longitude: 119.65168579839032,
          latitude: 32.8211227979412,
          peopleName: '李四',
          id: '2'
        },
        {
          longitude: 119.52534302495282,
          latitude: 32.52723852059745,
          peopleName: '王五',
          id: '3'
        },
        {
          longitude: 119.31660279057782,
          latitude: 32.63710180184745,
          peopleName: '王五',
          id: '3'
        },
      ]
      faces.forEach(face => {
        let feature = (new Feature({
          geometry: new Point([face.longitude, face.latitude]),
          name: 'face',
          id: face.id,
          peopleName: face.peopleName
        }))
        this.faceFeatures.push(feature)
      });
      this.faceSource.getSource().addFeatures(this.faceFeatures)
    },
    getDoor() {
      let doors = [
        {
          longitude: 119.19575318120282,
          latitude: 32.45582738778495,
          id: '1'
        },
        {
          longitude: 119.39900025151532,
          latitude: 32.51625219247245,
          id: '2'
        },
        {
          longitude: 119.64069947026532,
          latitude: 32.4695602979412,
          id: '3'
        },
      ]
      doors.forEach(door => {
        let feature = (new Feature({
          geometry: new Point([door.longitude, door.latitude]),
          name: 'door',
          id: door.id
        }))
        this.doorFeatures.push(feature)
      });
      this.doorSource.getSource().addFeatures(this.doorFeatures)
    }
  }
}
export default getData
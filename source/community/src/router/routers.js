import MainView from '@/views/mainView/mainView'
// import HomeView from '@/view/HomeView/HomeView'
import CameraPanel from '@/components/VideoPlay/CameraPanel'
import VehicleManage from '@/components/Vehicle/VehicleManage'
//主页
import home_topPanel from '@/components/home/topPanel'
// import home_leftPanel from '@/components/home/leftPanel'

import home_leftPanel from '@/components/home/leftPanel_New'

// import home_rightPanel from '@/components/home/rightPanel'
import home_rightPanel from '@/components/home/rightPanel_new'
//人员信息
import people_leftPanel from '@/components/peopleInfo/leftPanel_new'
import people_rightPanel from '@/components/peopleInfo/rightPanel_new'
//关爱人员
import care_leftPanel from '@/components/carePeople/leftPanel'
import care_rightPanel from '@/components/carePeople/rightPanel'
//车辆信息
import car_leftPanel from '@/components/carInfo/new/leftPanel'
import car_rightPanel from '@/components/carInfo/new/rightPanel'
//社区房屋
import community_leftPanel from '@/components/communityBuilding/leftPanel'
import community_rightPanel from '@/components/communityBuilding/rightPanel'
//通行数据
import trafficData_leftPanel from '@/components/trafficData/leftPanel'
import trafficData_rightPanel from '@/components/trafficData/rightPanel'
//社区事件
import events_leftPanel from '@/components/events/leftPanel'
import events_rightPanel from '@/components/events/rightPanel'
import events_topPanel from '@/components/events/topPanel'
//智能防控
import prevention_leftPanel from '@/components/smartPrevention/leftPanel'
import prevention_rightPanel from '@/components/smartPrevention/rightPanel'


import FaceSearch_leftPanel from '@/components/SmartSearch/FaceSearch/leftPanel'
import FaceSearch_rightPanel from '@/components/SmartSearch/FaceSearch/rightPanel'

import VehicleSearch_leftPanel from '@/components/SmartSearch/VehicleSearch/leftPanel'
import VehicleSearch_rightPanel from '@/components/SmartSearch/VehicleSearch/rightPanel'

import RelatimeTime_topPanel from '@/components/SmartVideo/RelatimeVideo/topPanel'
import RelatimeTime_leftPanel from '@/components/SmartVideo/RelatimeVideo/leftPanel'
import historyVideo_topPanel from '@/components/SmartVideo/HistoryVideo/topPanel'
import peopleinfo from '@/views/components/peopleInfo'
import homeView from '@/views/components/main'
import carinfo from '@/views/components/carInfo'
import communityBuilding from '@/views/components/communityBuilding'
import trafficData from '@/views/components/trafficData'
import carePeople from '@/views/components/carePeople'
import prevention from '@/views/components/prevention'
// 智能搜索部分
import faceSearch from '@/views/components/IntelligentSearch/faceSearch'
import vehicleSearch from '@/views/components/IntelligentSearch/vehicleSearch'

export const constantRoutes = [
  // {
  //   path: '/login',
  //   // redirect: '/login',
  //   component: (resolve) => require(['@/views/login'], resolve),
  //   hidden: true
  // },
  {
    path: '/index',
    name: 'default',
    component: MainView,
    redirect: '/index/home',
    children: [
      // {
      //   path: '/index/home',
      //   name: 'home',
      //   components: {
      //     main: homeView
      //   },
      // },
      // {
      //   path: '/index/carepeople',
      //   name: 'carePeople',
      //   components: {
      //     main: carePeople
      //   },
      // },
      // {
      //   path: '/index/prevention',
      //   name: 'prevention',
      //   components: {
      //     main: prevention
      //   },
      // },
      // {
      //   path: '/index/peopleinfo',
      //   name: 'peopleinfo',
      //   components: {
      //     main: peopleinfo,
      //   },
      // },
      // {
      //   path: '/index/carinfo',
      //   name: 'carinfo',
      //   components: {
      //     main: carinfo,
      //   },
      // },
      // {
      //   path: '/index/communityBuilding',
      //   name: 'communityBuilding',
      //   components: {
      //     main: communityBuilding,
      //   },
      // },
      // {
      //   path: '/index/trafficData',
      //   name: 'communityBuilding',
      //   components: {
      //     main: trafficData,
      //   },
      // },
      // {
      //   path: '/index/face_search',
      //   name: 'face_search',
      //   components: {
      //     main: faceSearch,
      //   },
      // },
      // {
      //   path: '/index/vehicle_search',
      //   name: 'vehicle_search',
      //   components: {
      //     main: vehicleSearch
      //   },
      // },
      {
        path: '/index/home',
        name: 'home',
        components: {
          // default: HomeView,
          top_panel_router_view: home_topPanel,
          left_panel_router_view: home_leftPanel,
          right_panel_router_view: home_rightPanel
        },
        meta: {
          keepAlive: true,
        },
        hidden: true
      },
      {
        path: '/index/peopleinfo',
        name: 'peopleinfo',
        components: {
          left_panel_router_view: people_leftPanel,
          right_panel_router_view: people_rightPanel
        },
        meta: {
          keepAlive: true,
        },
        hidden: true
      },
      {
        path: '/index/carepeople',
        name: 'carepeople',
        components: {
          left_panel_router_view: care_leftPanel,
          right_panel_router_view: care_rightPanel
        },
        meta: {
          keepAlive: true,
        },
        hidden: true
      },
      {
        path: '/index/carinfo',
        name: 'carinfo',
        components: {
          left_panel_router_view: car_leftPanel,
          right_panel_router_view: car_rightPanel
        },
        meta: {
          keepAlive: true,
        },
        hidden: true
      },
      {
        path: '/index/communityBuilding',
        name: 'communityBuilding',
        components: {
          left_panel_router_view: community_leftPanel,
          right_panel_router_view: community_rightPanel
        },
        meta: {
          keepAlive: true,
        },
        hidden: true
      },
      {
        path: '/index/trafficData',
        name: 'trafficData',
        components: {
          left_panel_router_view: trafficData_leftPanel,
          right_panel_router_view: trafficData_rightPanel
        },
        meta: {
          keepAlive: true,
        },
        hidden: true
      }
      ,
      {
        path: '/index/events',
        name: 'events',
        components: {
          left_panel_router_view: events_leftPanel,
          right_panel_router_view: events_rightPanel,
          top_panel_router_view: events_topPanel
        },
        meta: {
          keepAlive: true,
        },
        hidden: true
      },
      {
        path: '/index/prevention',
        name: 'prevention',
        components: {
          left_panel_router_view: prevention_leftPanel,
          right_panel_router_view: prevention_rightPanel
        },
        meta: {
          keepAlive: true,
        },
        hidden: true
      },
      {
        path: '/index/face_search',
        name: 'face_search',
        components: {
          // default: HomeView,
          left_panel_router_view: FaceSearch_leftPanel,
          right_panel_router_view: FaceSearch_rightPanel
        },
        meta: {
          keepAlive: true,
          top_panel_router_view_height: 100,
          left_panel_router_view_width: 400,
          right_panel_router_view_width: 200
        },
        hidden: true
      },
      {
        path: '/index/vehicle_search',
        name: 'vehicle_search',
        components: {
          // default: HomeView,
          left_panel_router_view: VehicleSearch_leftPanel,
          right_panel_router_view: VehicleSearch_rightPanel
        },
        meta: {
          keepAlive: true,
          top_panel_router_view_height: 0,
          left_panel_router_view_width: 260,
          right_panel_router_view_width: 340
        },
        hidden: true
      },
      {
        path: '/index/relatime_video',
        name: 'relatime_video',
        components: {
          // default: HomeView,
          top_panel_router_view: RelatimeTime_topPanel,
          left_panel_router_view: RelatimeTime_leftPanel
        },
        meta: {
          keepAlive: true,
          top_panel_router_view_height: 100,
          left_panel_router_view_width: 800,
          right_panel_router_view_width: 10
        },
        hidden: true
      },
      {
        path: '/index/historyVideo_video',
        name: 'historyVideo_video',
        components: {
          // default: HomeView,
          // top_panel_router_view: RelatimeTime_topPanel,
          left_panel_router_view: historyVideo_topPanel
        },
        meta: {
          keepAlive: true,
        },
        hidden: true
      },
      {
        path: '/index/CameraPanel',
        name: 'CameraPanel',
        component: CameraPanel,
        meta: {
          // keepAlive: true,
          title: 'captureList',
          hideInMenu: true
        }
      },
      {
        path: '/index/VehicleManage',
        name: 'VehicleManage',
        component: VehicleManage,
        meta: {
          // keepAlive: true,
          title: 'captureList',
          hideInMenu: true
        }
      }
      // {
      //   path: '/HomeView',
      //   name: 'HomeView',
      //   components: {
      //     default: HomeView,
      //     CameraPanelRouterView: CameraPanel,
      //     VehicleManageRouterView: VehicleManage,
      //     VehicleRecordSearchRouterView: VehicleRecordSearch,
      //     AlarmDisposeRouterView: AlarmDispose,
      //     IntelligentAnalysisRouterView: IntelligentAnalysis,
      //     ManufacturersManagementRouterView: ManufacturersManagement
      //   },
      //   meta: { keepAlive: true },
      //   hidden: true
      // }
    ]
  }
]
export default constantRoutes

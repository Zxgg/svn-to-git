import MainView from '@/views/mainView'
// import HomeView from '@/view/HomeView/HomeView'
import GisPanel from '@/views/GISView/GISView'

import CameraPanel from '@/components/VideoPlay/CameraPanel'
import VehicleManage from '@/components/Vehicle/VehicleManage'

import home_topPanel from '@/views/home/topPanel'
import home_leftPanel from '@/views/home/leftPanel'
import home_rightPanel from '@/views/home/rightPanel'

import FaceSearch_leftPanel from '@/components/SmartSearch/FaceSearch/leftPanel'
import FaceSearch_rightPanel from '@/components/SmartSearch/FaceSearch/rightPanel'

import VehicleSearch_leftPanel from '@/components/SmartSearch/VehicleSearch/leftPanel'
import VehicleSearch_rightPanel from '@/components/SmartSearch/VehicleSearch/rightPanel'

import RelatimeTime_topPanel from '@/views/SmartVideo/RelatimeVideo/topPanel'
import RelatimeTime_leftPanel from '@/views/SmartVideo/RelatimeVideo/leftPanel'
import RelatimeTime_autoWHPanel from '@/views/SmartVideo/RelatimeVideo/autoWHPanel'

//系统管理
import user from '@/views/system/user'
import role from '@/views/system/role'
import dept from '@/views/system/dept'
import dict from '@/views/system/dict'
import notice from '@/views/system/notice'
import menu from '@/views/system/menu'
import config from '@/views/system/config'
//系统管理
export const constantRoutes = [
  {
    path: '/login',
    // redirect: '/login',
    component: (resolve) => require(['@/views/login'], resolve),
    hidden: true
  },
  //page1
  {
    path: '/page1',
    beforeEnter (to, from, next) {
      window.location = "/page1.html"
    }
  }

  // ,
  // {
  //   path: '/',
  //   name: 'default',
  //   component: MainView,
  //   redirect: '/home',
  //   children: [
  // {
  //   path: '/home',
  //   name: 'home',
  //   components: {
  //     default: (resolve) => require(['@/views/GISView/GISView'], resolve),
  //     // top_panel_router_view: home_topPanel,
  //     top_panel_router_view: (resolve) => require(['@/views/home/topPanel'], resolve),
  //     left_panel_router_view: (resolve) => require(['@/views/home/leftPanel'], resolve),
  //     right_panel_router_view: (resolve) => require(['@/views/home/rightPanel'], resolve),
  //     // default: GisPanel
  //   },
  //   meta: {
  //     keepAlive: true,
  //     top_panel_router_view_height: 400,
  //     left_panel_router_view_width: 300,
  //     right_panel_router_view_width: 300
  //   },
  //   hidden: true
  // },
  // {
  //   path: '/system/user',
  //   name: 'system_user',
  //   components: {
  //     default: user
  //   },
  //   meta: {
  //     keepAlive: true,
  //     top_panel_router_view_height: 0,
  //     left_panel_router_view_width: 0,
  //     right_panel_router_view_width: 0
  //   },
  //   hidden: true
  // },
  // {
  //   path: '/system/role',
  //   name: 'system_role',
  //   components: {
  //     default: role
  //   },
  //   meta: {
  //     keepAlive: true,
  //     top_panel_router_view_height: 0,
  //     left_panel_router_view_width: 0,
  //     right_panel_router_view_width: 0
  //   },
  //   hidden: true
  // },
  // {
  //   path: '/system/notice',
  //   name: 'system_notice',
  //   components: {
  //     default: notice
  //   },
  //   meta: {
  //     keepAlive: true,
  //   },
  //   hidden: true
  // },
  // {
  //   path: '/system/menu',
  //   name: 'system_menu',
  //   components: {
  //     default: menu
  //   },
  //   meta: {
  //     keepAlive: true,
  //   },
  //   hidden: true
  // },
  // {
  //   path: '/system/config',
  //   name: 'system_config',
  //   components: {
  //     default: config
  //   },
  //   meta: {
  //     keepAlive: true,
  //   },
  //   hidden: true
  // },
  // {
  //   path: '/system/dict',
  //   name: 'system_dict',
  //   components: {
  //     default: dict
  //   },
  //   meta: {
  //     keepAlive: true,
  //   },
  //   hidden: true
  // },
  // {
  //   path: '/system/dept',
  //   name: 'system_dept',
  //   components: {
  //     default: dept
  //   },
  //   meta: {
  //     keepAlive: true,
  //   },
  //   hidden: true
  // },
  // {
  //   path: '/face_search',
  //   name: 'face_search',
  //   components: {
  //     // default: HomeView,
  //     left_panel_router_view: FaceSearch_leftPanel,
  //     right_panel_router_view: FaceSearch_rightPanel,
  //     default: GisPanel
  //   },
  //   meta: {
  //     keepAlive: true,
  //     top_panel_router_view_height: 100,
  //     left_panel_router_view_width: 400,
  //     right_panel_router_view_width: 200
  //   },
  //   hidden: true
  // },
  // {
  //   path: '/vehicle_search',
  //   name: 'vehicle_search',
  //   components: {
  //     // default: HomeView,
  //     left_panel_router_view: VehicleSearch_leftPanel,
  //     right_panel_router_view: VehicleSearch_rightPanel,
  //     default: GisPanel
  //   },
  //   meta: {
  //     keepAlive: true,
  //     top_panel_router_view_height: 0,
  //     left_panel_router_view_width: 260,
  //     right_panel_router_view_width: 340
  //   },
  //   hidden: true
  // },
  // {
  //   path: '/relatime_video',
  //   name: 'relatime_video',
  //   components: {
  //     // default: HomeView,
  //     left_panel_router_view: RelatimeTime_leftPanel,
  //     default: RelatimeTime_autoWHPanel
  //   },
  //   meta: {
  //     keepAlive: true,
  //     top_panel_router_view_height: 0,
  //     left_panel_router_view_width: 400,
  //     right_panel_router_view_width: 0
  //   },
  //   hidden: true
  // },
  // {
  //   path: '/CameraPanel',
  //   name: 'CameraPanel',
  //   component: CameraPanel,
  //   meta: {
  //     // keepAlive: true,
  //     title: 'captureList',
  //     hideInMenu: true
  //   }
  // },
  // {
  //   path: '/VehicleManage',
  //   name: 'VehicleManage',
  //   component: VehicleManage,
  //   meta: {
  //     // keepAlive: true,
  //     title: 'captureList',
  //     hideInMenu: true
  //   }
  // }

  // ]
  // }
]
export default constantRoutes

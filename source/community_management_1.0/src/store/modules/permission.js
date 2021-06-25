// import { constantRoutes } from '@/router'
import { constantRoutes } from '@/router/routers.js'
import { getRouters } from '@/api/menu'
// import Layout from '@/layout/index'
import MainView from '@/views/mainView'
import router from '../../router'
const permission = {
  state: {
    routes: [],
    addRoutes: [],
    router_menu: []
  },
  mutations: {
    SET_ROUTES: (state, routes) => {
      state.addRoutes = routes
      state.routes = constantRoutes.concat(routes)
      state.router_menu = routes
    }
  },
  actions: {
    // 生成路由
    GenerateRoutes ({ commit }) {
      return new Promise(resolve => {
        // 向后端请求路由数据
        getRouters().then(res => {
          commit('SET_ROUTES', res.data)//目录
          // const accessedRoutes = filterAsyncRouter(res.data)
          routerChildrenList = []
          filterAsyncRouter(res.data)//加载各个子路由
          var tempRoute = routerChildrenList
          tempRoute.push({ path: '*', redirect: '/404', hidden: true })
          const rootRouters = SetRootRouter(tempRoute)

          resolve([rootRouters])
        })
      })
    }
  }
}
function SetRootRouter (children) {
  var root = {
    path: '/',
    name: 'root',
    component: (resolve) => require(['@/views/mainView'], resolve),
    redirect: '/home',
    children: [
      {
        path: '/404',
        component: (resolve) => require(['@/views/error/404'], resolve),
        hidden: true
      },
      {
        path: '/401',
        component: (resolve) => require(['@/views/error/401'], resolve),
        hidden: true
      },
      {
        name: 'Profile',
        path: '/user/profile',
        component: (resolve) => require(['@/views/system/user/profile/index'], resolve),
        hidden: true,
        // redirect: 'noredirect',
        meta: { title: '个人中心', icon: 'user' }
      },
      {
        name: 'Data',
        path: '/dict/type/data/:dictId(\\d+)',
        component: (resolve) => require(['@/views/system/dict/data'], resolve),
        hidden: true,
        meta: { title: '字典数据', icon: '' }
      },
      {
        name: 'JobLog',
        path: '/job/log',
        component: (resolve) => require(['@/views/monitor/job/log'], resolve),
        hidden: true,
        meta: { title: '调度日志' }
      },
      {
        name: 'GenEdit',
        path: '/gen/edit/:tableId(\\d+)',
        component: (resolve) => require(['@/views/tool/gen/editTable'], resolve),
        hidden: true,
        meta: { title: '修改生成配置' }
      }
    ]
  }
  root.children = root.children.concat(children)
  return root
}
function getListFromString (str, separChar = ',') {
  if (str && str.length > 0) {
    return str.split(separChar)
  } else {
    return []
  }
}
function getVHName (viewPanelName) {
  let name = ''
  switch (viewPanelName) {
    case 'top_panel_router_view': name = 'top_panel_router_view_height'
      break
    case 'left_panel_router_view': name = 'left_panel_router_view_width'
      break
    case 'right_panel_router_view': name = 'right_panel_router_view_width'
      break
  }
  return name
}
var routerChildrenList = []
function filterAsyncRouter (asyncRouterMap) {
  let temp = asyncRouterMap.filter(route => {
    if (route.children != null && route.children && route.children.length) {
      filterAsyncRouter(route.children)
    }
    if (route.path && route.path.length > 0 && route.component) {
      if (route.path.substr(0, 1) !== '/') {
        route.path = '/' + route.path
      }
      // 处理多路由组件
      if (route.multiComponent) {
        let componentList = getListFromString(route.component)
        route.component = {}
        //多路由组件

        if (componentList.length > 1) {
          delete route.component;//路由中包含component会导致路由components失效 chensz 20201117
          route.components = {}//路由中包含components会导致路由component失效 chensz 20201117
          componentList.forEach(element => {
            let dict = getListFromString(element, ':')
            if (dict && dict.length === 1) {
              let routerViewName = 'default'
              let componentName = dict[0]
              route.components[routerViewName] = loadView(componentName)
            } else if (dict && dict.length === 2) {
              let routerViewName = dict[0]
              let componentName = dict[1]
              route.components[routerViewName] = loadView(componentName)
            } else if (dict && dict.length === 3) {
              let routerViewName = dict[0]
              let componentName = dict[1]
              let vh = dict[2]
              let vhName = getVHName(routerViewName)
              if (vhName.length > 0) {
                route.meta[vhName] = vh
              }
              route.components[routerViewName] = loadView(componentName)
            }
          });
        }
        else if (componentList.length === 1) {
          let dict = getListFromString(componentList[0], ':')
          if (dict && dict.length === 1) {
            let routerViewName = 'default'
            let componentName = dict[0]
            route.component = loadView(componentName)
          } else if (dict && dict.length === 2) {
            let routerViewName = dict[0]
            let componentName = dict[1]
            let temp = { routerViewName }
            route.component = loadView(componentName)
          } else if (dict && dict.length === 3) {
            let routerViewName = dict[0]
            let componentName = dict[1]
            // let vh = dict[2]
            // let vhName = getVHName(routerViewName)
            // if (vhName.length > 0) {
            //   route.meta[vhName] = vh
            // }
            route.component = loadView(componentName)
          }
        }

      } else {
        route.component = loadView(route.component)
      }
      routerChildrenList.push(route)
    }
  })

}
// 遍历后台传来的路由字符串，转换为组件对象
function filterAsyncRouter0 (asyncRouterMap) {
  return asyncRouterMap.filter(route => {

    if (route.component) {
      // 处理多路由组建
      if (route.multiComponent) {
        let componentList = getListFromString(route.component)
        route.component = {}
        //多路由组件
        route.components = []
        componentList.forEach(element => {
          let dict = getListFromString(element, ':')
          if (dict && dict.length === 1) {
            let routerViewName = 'default'
            let componentName = dict[0]
            route.components.push({
              [routerViewName]: loadView(componentName)
            })
          } else if (dict && dict.length === 2) {
            let routerViewName = dict[0]
            let componentName = dict[1]
            let temp = { routerViewName }
            route.components.push({
              [routerViewName]: loadView(componentName)
            })
          }
        });
      } else {
        route.component = loadView(route.component)
      }
    } else {
      route.component = (resolve) => require(['@/views/mainView'], resolve)
    }
    if (!route.path) {
      route.path = '/home'
    }
    if (route.children != null && route.children && route.children.length) {
      route.children = filterAsyncRouter(route.children)
    }
    return true
  })
}
export const loadView = (view) => { // 路由懒加载
  return (resolve) => require([`@/views/${view}`], resolve)
}
const routerList = [
  {
    path: '/system/user',
    name: 'system_user',
    components: {
      default: (resolve) => require([`@/views/system/user`], resolve)
    },
    meta: {
      keepAlive: true,
      top_panel_router_view_height: 0,
      left_panel_router_view_width: 0,
      right_panel_router_view_width: 0
    },
    hidden: true
  },
  {
    path: '/system/role',
    name: 'system_role',
    components: {
      default: (resolve) => require([`@/views/system/role`], resolve)
    },
    meta: {
      keepAlive: true,
      top_panel_router_view_height: 0,
      left_panel_router_view_width: 0,
      right_panel_router_view_width: 0
    },
    hidden: true
  },
  {
    path: '/system/notice',
    name: 'system_notice',
    components: {
      default: (resolve) => require([`@/views/system/notice`], resolve)
    },
    meta: {
      keepAlive: true,
    },
    hidden: true
  },
  {
    path: '/system/menu',
    name: 'system_menu',
    components: {
      default: (resolve) => require([`@/views/system/menu`], resolve)
    },
    meta: {
      keepAlive: true,
    },
    hidden: true
  },
  {
    path: '/system/config',
    name: 'system_config',
    components: {
      default: (resolve) => require([`@/views/system/config`], resolve)
    },
    meta: {
      keepAlive: true,
    },
    hidden: true
  },
  {
    path: '/system/dict',
    name: 'system_dict',
    components: {
      default: (resolve) => require([`@/views/system/dict`], resolve)
    },
    meta: {
      keepAlive: true,
    },
    hidden: true
  },
  {
    path: '/system/dept',
    name: 'system_dept',
    components: {
      default: (resolve) => require([`@/views/system/dept`], resolve)
    },
    meta: {
      keepAlive: true,
    },
    hidden: true
  }

]
export default permission

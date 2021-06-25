import variables from '@/assets/styles/element-variables.scss'
import defaultSettings from '@/settings'
import Cookies from 'js-cookie'
const { showSettings, tagsView, fixedHeader, sidebarLogo } = defaultSettings

const state = {
  theme: variables.theme,
  showSettings: showSettings,
  tagsView: tagsView,
  fixedHeader: fixedHeader,
  sidebarLogo: sidebarLogo,
  currentComunity: {
    communityId: Cookies.get('communityId') ? Cookies.get('communityId') : '',
    communityChildId: Cookies.get('communityChildId') ? Cookies.get('communityChildId') : '',
  }
}

const mutations = {
  CHANGE_SETTING: (state, { key, value }) => {
    if (state.hasOwnProperty(key)) {
      state[key] = value
    }
  },
  CHANGE_COMMUNITYID: (state, communityId) => {
    Cookies.set('communityId', communityId)
    state.currentComunity.communityId = communityId
  },
  CHANGE_CHANGE_COMMUNITYCHILDID: (state, communityChildId) => {
    Cookies.set('communityChildId', communityChildId)
    state.currentComunity.communityChildId = communityChildId
  },
}

const actions = {
  changeSetting ({ commit }, data) {
    commit('CHANGE_SETTING', data)
  },
  changeCommunityId ({ commit }, communityId) {
    console.log('设置communityId：' + communityId)
    commit('CHANGE_COMMUNITYID', communityId)
  },
  changeCommunityChildId ({ commit }, communityChildId) {
    console.log('设置communityChildId：' + communityChildId)
    commit('CHANGE_CHANGE_COMMUNITYCHILDID', communityChildId)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}


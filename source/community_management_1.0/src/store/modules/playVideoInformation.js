
export default {
  state: {
    playVideoData: []
  },
  mutations: {
    setPlayVideoData (state, playVideoData) {
      state.playVideoData = playVideoData
    }
  },
  getters: {
    playVideoData: state => state.playVideoData
  },
  actions: {
    setPlayVideoInformation ({ commit }, playVideoData) { // 获取人脸集合
      commit('setPlayVideoData', playVideoData)
    }
  }
}

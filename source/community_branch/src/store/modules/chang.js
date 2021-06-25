const state = {
  visited:1,
}

const mutations = {
  CHANGE_SET: (state, view) => {
    state.visited=view
    console.log(state.visited);
  },
}
export default {
  // namespaced: true,
  state,
  mutations,
}
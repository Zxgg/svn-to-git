<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <el-input v-model="inputParam" placeholder="输入参数"></el-input>
    <el-input v-model="inputParam2" placeholder="输入参数2"></el-input>
    <!-- tag="li" 表示路由标签渲染成 li 控件 -->
    <!-- event="mouseover" 表示在哪个事件时激活路由 -->
    <!-- <router-link to="/Test1/123" tag="li" active-class="_active"
      >Go to Test</router-link
    > -->
    <router-link
      :to="{
        name: 'Test',
        params: { id: inputParam },
        query: { num: inputParam2 }
      }"
      tag="li"
      active-class="_active"
      >Go to Test2</router-link
    >
    <router-view></router-view>
    <h2>Essential Links</h2>
    <ul>
      <li>
        <a href="https://vuejs.org" target="_blank">
          Core Docs
        </a>
      </li>
      <li>
        <a href="https://forum.vuejs.org" target="_blank">
          Forum
        </a>
      </li>
      <li>
        <a href="https://chat.vuejs.org" target="_blank">
          Community Chat
        </a>
      </li>
      <li>
        <a href="https://twitter.com/vuejs" target="_blank">
          Twitter
        </a>
      </li>
      <br />
      <li>
        <a href="http://vuejs-templates.github.io/webpack/" target="_blank">
          Docs for This Template
        </a>
      </li>
    </ul>
    <h2>Ecosystem</h2>
    <ul>
      <li>
        <a href="http://router.vuejs.org/" target="_blank">
          vue-router
        </a>
      </li>
      <li>
        <a href="http://vuex.vuejs.org/" target="_blank">
          vuex
        </a>
      </li>
      <li>
        <a href="http://vue-loader.vuejs.org/" target="_blank">
          vue-loader
        </a>
      </li>
      <li>
        <a href="https://github.com/vuejs/awesome-vue" target="_blank">
          awesome-vue
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      msg: 'Welcome to Your Vue.js App',
      inputParam: 123,
      inputParam2: 456,
      imagePath:
        'http://172.16.134.10:1153/mp_mcss/vcn-image.do?fileUuid=92dc1c25980d4a1c9b59d928a092e478%23184606354973360%2399158934249139100001%23f9e8841e443441be87d99faa62f6bb65'
    }
  },
  methods: {}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import '../css/styleDefault.css';
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>

<template>
  <div>
    <el-carousel indicator-position="none" :autoplay="true" :interval="10000">
      <el-carousel-item
        v-for="k in Math.ceil(eventsList.length / 9)"
        :key="k"
        class=""
      >
       <el-row :gutter="12">
          <el-col
            :span="8"
            v-for="item in eventsList.slice((k - 1) * 9, k * 9)"
            :key="item.id"
          >
            <div class="card">
              <div class="atitle">
                <div class="name">
                  <span class="text">{{ item.name }}</span>
                </div>
                <div class="cardimg">
                  <img :src="item.pic" class="image" />
                </div>
              </div>

              <div class="address">
                地址:<span>{{ item.towns }}</span>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>
<script>
import $ from "jquery";
import { mapGetters, mapState } from "vuex";
export default {
  data() {
    return {
      eventsList: []
    };
  },
  mounted() {
    this.eventsList = this.testData.event.eventsList;
  }
};
</script>
<style lang="scss" scoped>
@import "~@/assets/styles/main/events.css";
.el-row {
  width: 100%;
  height: 100%;
  margin-left: 10px !important;
  font-size: 10px;
}
.el-col {
  margin-top: 1%;
  box-sizing: border-box;
  height: 32%;
}

.card {
  width: 100%;
  height: 100%;
  background: no-repeat url(../../../assets/images/events/社区网格员.png);
  background-size: 100% 100%;
  padding: 10px 14px 0;
  position: relative;
}
.atitle {
  display: flex;
  justify-content: space-between;
  height: 80%;
}
.name {
  width: 20px;
  height: 70%;
  writing-mode: vertical-lr;
  background: no-repeat url(../../../assets/images/events/社区网格员-1.png);
  background-size: 100% 100%;
  padding: 10% 1% 0;
}
.cardimg {
  width: 70%;
  height: 90%;
  border: 3px solid #98d7ff;
  border-top-right-radius: 20%;
  border-bottom-left-radius: 20%;
  overflow: hidden;
}
img {
  width: 100%;
  height: 100%;
}

.address {
  font-size: 10px;
  height: 15%;
  box-sizing: border-box;
  padding: 2px 0px 0 2%;
  color: #dbeff5;
  text-align: left;
  background: no-repeat url(../../../assets/images/events/社区网格员-2.png);
  background-size: 100%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.el-carousel {
  height: 100%;
}
.el-carousel__item,
/deep/.el-carousel__container {
  height: 100%;
}
/* /deep/.el-carousel__container {
  height: 381px;
} */
/deep/.el-carousel__container .is-active {
  background-color: transparent !important;
}
</style>

<template>
  <div>
    <el-dialog class="guanaimotai"
               title="关爱人员提醒"
               :visible.sync="abnormaldailg"
               width="530px"
               :before-close="abnormalShijianclose">
      <el-form :model="myform"
               :inline="true"
               label-width="85px">
        <el-row class="demo-form-inline">
          <el-col :span="10">
            <div class="imgbox">
              <img :src="beforeDisposal"
                   alt="" />
            </div>
          </el-col>

          <el-col :span="4">
            <div class="contentbox">
              <div>99%</div>
              <div>相似度</div>
            </div>
          </el-col>

          <el-col :span="10">
            <div class="imgbox">
              <img :src="afterDisposal"
                   alt="" />
            </div>
          </el-col>

          <el-col :span="24">
            <el-form-item label="时间:">
              <div class="formcontent">
                {{ myform.time || "未知" }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="地址:">
              <div class="formcontent">
                {{ myform.address || "未知" }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item class=""
                          label="描述">
              <div class="formcontent">
                {{ myform.content || "未知" }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="8"
                  v-for="item in chulilist"
                  :key="item.chulistate">
            <div class="btn"
                 @click.stop="chuli(item.chulistate)">
              {{ item.chuli }}
            </div>
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import bus from "@/assets/eventbus";
export default {
  props: {
    myform: {
      type: Object,
      default: () => { }
    },
    abnormaldailg: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      chulilist: [
        {
          chulistate: 0,
          chuli: "受理"
        },
        {
          chulistate: 1,
          chuli: "上报"
        },
        {
          chulistate: 2,
          chuli: "误报"
        }
      ]
    };
  },
  methods: {
    abnormalShijianclose () {
      this.$emit("abnormalShijianclose", false);
    },
    chuli (chulistate) {
      let list = this.myform;
      if (chulistate === 2) {
        this.abnormalShijianclose();
        return;
      }
      list.chulistate = chulistate;
      list.state = 0;
      bus.$emit("get2", list);
      this.abnormalShijianclose();
    }
  }
};
</script>

<style lang="scss" scoped>
.guanaimotai {
  .imgbox {
    width: 188px;
    height: 217px;
    background: url("../../../../assets/images/events/照片.png") no-repeat;
    background-size: 100% 100%;
    img {
      width: 100%;
      height: 100%;
    }
  }
  .contentbox {
    height: 188px;

    display: flex;
    flex-direction: column;
    justify-content: center;
    > div:first-of-type {
      width: 65px;
      height: 30px;
      font-size: 36px;
      font-family: Impact;
      font-weight: 400;
      color: #e2eff9;
      margin-bottom: 32px;
    }
    > div:last-of-type {
      width: 42px;
      font-size: 14px;
      font-family: Source Han Sans CN;
      font-weight: 500;
      color: #e2eff9;
    }
  }
  .demo-form-inline {
    padding-left: 1%;
  }
  /deep/.el-dialog {
    border: 0px solid #005982;
    background-color: transparent;

    .el-dialog__header {
      // background-color: transparent;
      // padding: 0;
      height: 100%;
      background: url("../../../../assets/images/events/弹框背景1.png")
        no-repeat;
      background-size: 100% 100%;
    }
    .el-dialog__body {
      // padding: 0;
      background: url("../../../../assets/images/events/弹框背景2.png")
        no-repeat;
      background-size: 117% 117%;
      background-position: 54% 52%;
      color: #fff;
    }
  }
  /deep/.el-form-item {
    margin-bottom: 7px;
    .el-form-item__label {
      color: #fff;
    }
  }
  .btn {
    color: #fff;
    width: 110px;
    height: 33px;
    line-height: 33px;
    text-align: center;
    font-size: 20px;
    background: url("../../../../assets/images/events/bnt.png") no-repeat;
    background-size: 100% 100%;
    &:hover {
      background: url("../../../../assets/images/events/bnt-on.png") no-repeat;
      background-size: 100% 100%;
    }
  }
}
</style>

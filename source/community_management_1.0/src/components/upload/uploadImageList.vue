<template>
  <div class="Main"
       ref="MainView">
    <el-upload :action="upLoadUrl"
               multiple
               :disabled="isDisabled"
               drag
               :headers="{ Authorization: token }"
               list-type="picture-card"
               :on-preview="handlePictureCardPreview"
               :on-remove="handleRemove"
               :on-success="handleSuccess"
               :on-error="handleError"
               :file-list="imageList"
               :accept="acceptType">
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%"
           :src="dialogImageUrl"
           alt="" />
    </el-dialog>
  </div>
</template>
<script>
import { getToken } from "@/utils/auth";
import configInfo from "@/webconfig/configInfo";
//import cameraTreeLazy from '@/components/cameraTree/cameraTree_lazy'
export default {
  //components: { cameraTreeLazy },
  props: {
    upLoadUrl: {
      type: String,
      default: "",
    },
    filesJson: {
      type: String,
      default () {
        return "";
      },
    },
    acceptType: {
      type: String,
      default () {
        return ".jpg,.jpeg,.png,.gif,.bmp,.JPG,.JPEG,.PBG,.GIF,.BMP";
      },
    },
    isDisabled: {
      type: Boolean,
      default () {
        return false;
      }

    },
    uploadState: {
      type: Boolean,
      default () {
        return false;
      }
    }
  },
  data () {
    return {
      that: this,
      dialogImageUrl: "",
      dialogVisible: false,
      //图片上传成功的地址
      imageList: [
        // {
        //   // uid: 1609220215787,
        //   // status: "success",
        //   name: "20191213151344.jpg",
        //   url:
        //     "http://10.16.123.60:80/group1/M00/00/00/ChB7PF_qnOOAVoFYAADNLEZ7Wew006.jpg?token=2c51428a75acffbd5a1b1a0a9bf18cfe&ts=1609210948",
        // }
      ],
      // uploadState: false
    };
  },
  created () { },
  mounted () {
    this.setLayout();
  },
  filters: {},
  methods: {
    setLayout () {
      let uploadBtns = document.getElementsByClassName("el-upload-dragger");
      if (uploadBtns && uploadBtns.length > 0) {
        uploadBtns.forEach((element) => {
          element.style.width = "146px";
          element.style.height = "146px";
        });
      }
    },
    handleRemove (file, fileList) {
      // this.uploadState = true
      let item = []
      fileList.forEach(el => {
        item.push({
          name: el.name,
          url: el.hasOwnProperty('response') ? el.response.data : el.url.replace(configInfo.imgEchoAddress, '')
        })
      })
      this.$emit("update:filesJson", JSON.stringify(item));
    },
    handlePictureCardPreview (file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true;
    },
    handleSuccess (response, file, fileList) {
      // this.uploadState = true
      let item = []
      fileList.forEach(el => {
        item.push({
          name: el.name,
          url: el.hasOwnProperty('response') ? el.response.data : el.url.replace(configInfo.imgEchoAddress, '')
        })
      })
      this.$emit("update:filesJson", JSON.stringify(item));
    },
    handleError (err, file, fileList) {
      console.log("upload error", file);
    },
  },
  computed: {
    token: function () {
      return getToken();
    },
  },
  watch: {
    filesJson: {
      handler: function (newValue, oldValue) {
        // console.log('upaloadstate:', this.uploadState)
        // console.log('newValue:', newValue)
        // console.log('oldValue:', oldValue)
        if (!this.uploadState) {
          newValue = this.$cf.returnJson(newValue)
          oldValue = this.$cf.returnJson(oldValue)
          if (newValue.length === 0) {
            this.imageList = [];
            return;
          }
          for (let i = 0; i < newValue.length; i++) {
            if (oldValue[i] != newValue[i]) {
              this.imageList = [];
              newValue.forEach((el) => {
                this.imageList.push({
                  name: el.name,
                  url: configInfo.imgEchoAddress + el.url
                });
              });
              return;
            }
          }
        }
      },
      deep: true,
      immediate: true
    },
  },
  beforeDestroy () { },
};
</script>
<style lang="scss" scoped>
.Main {
  width: 390px;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
}
</style>
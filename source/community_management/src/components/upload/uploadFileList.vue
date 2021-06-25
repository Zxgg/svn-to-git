<template>
  <div class="Main"
       ref="MainView">
    <!-- <el-upload class="upload-demo"
               multiple
               :headers="{Authorization:token}"
               :action="upLoadUrl"
               :file-list="fileList">
      <el-button size="small"
                 type="primary">点击上传</el-button>
      <div slot="tip"
           class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
    </el-upload> -->
    <el-upload :action="upLoadUrl"
               multiple
               :headers="{Authorization:token}"
               :on-preview="handlePictureCardPreview"
               :on-remove="handleRemove"
               :on-success="handleSuccess"
               :on-error="handleError"
               :file-list="fileList">
      <el-button size="small"
                 type="primary">点击上传</el-button>
      <div slot="tip"
           class="el-upload__tip">只能上传图片文件和pdf文件</div>
    </el-upload>
  </div>
</template>
<script>
import { getToken } from '@/utils/auth'
import configInfo from "@/webconfig/configInfo";
export default {
  props: {
    upLoadUrl: {
      type: String,
      default: ''
    },
    filesJson: {
      type: String,
      default () {
        return ''
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
      dialogImageUrl: '',
      dialogVisible: false,
      //图片上传成功的地址
      fileList: [
      ]
    }
  },
  created () { },
  mounted () {
    // this.setLayout()
  },
  filters: {},
  methods: {
    setLayout () {
      let uploadBtns = document.getElementsByClassName("el-upload-dragger")
      if (uploadBtns && uploadBtns.length > 0) {
        uploadBtns.forEach(element => {
          element.style.width = '146px'
          element.style.height = '146px'
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
      this.dialogImageUrl = file.url;
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
      console.log('upload error', file)
    },
  },
  computed: {
    token: function () {
      return getToken()
    }
  },
  watch: {
    filesJson: {
      handler: function (newValue, oldValue) {
        if (!this.uploadState) {
          newValue = this.$cf.returnJson(newValue)
          oldValue = this.$cf.returnJson(oldValue)
          if (newValue.length === 0) {
            this.fileList = [];
            return;
          }
          for (let i = 0; i < newValue.length; i++) {
            if (oldValue[i] != newValue[i]) {
              this.fileList = [];
              newValue.forEach((el) => {
                this.fileList.push({
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
  beforeDestroy () { }
}
</script>
<style lang="scss" scoped>
.Main {
  // width: 390px;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
}
</style>
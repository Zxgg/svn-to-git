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
        // },
        // {
        //   // uid: 1609220215788,
        //   // status: "success",
        //   name: "20191213151336.jpg",
        //   url:
        //     "http://10.16.123.60:80/group1/M00/00/00/ChB7PF_qnOWAQO3JAAEIrFXBC1g000.jpg?token=1c2dfefbcddfdce31f6607b461b57fba&ts=1609210949",
        // },
        // {
        //   // uid: 1609220215789,
        //   // status: "success",
        //   name: "1.png",
        //   url:
        //     "http://10.16.123.60:80/group1/M00/00/00/ChB7PF_qnOiALm4qAAA1ULEdwyE566.png?token=e0ab9a1a4bc5e5e4a55c29d8e814c487&ts=1609210953",
        // },
      ],
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
      let index = this.getIndex(file.uid);
      this.imageList.splice(index, 1);
    },
    handlePictureCardPreview (file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true;
    },
    handleSuccess (response, file, fileList) {
      this.imageList.push({
        uid: file.uid,
        status: "success",
        name: file.name,
        data: file.response.data,
        url: configInfo.imgEchoAddress + file.response.data,
      });
      console.log('shanchuan:', this.imageList)
    },
    handleError (err, file, fileList) {
      console.log("upload error", file);
    },
    getIndex (uid) {
      for (var i = 0; i < this.imageList.length; i++) {
        if (this.imageList[i].uid == uid) return i;
      }
      return -1;
    },
    getListFromJson (json) {
      this.imageList = [];
      let list = []
      let imgListNew = []
      if (json !== '' && json !== null && json !== undefined) { list = json.split(',') }
      // let list = JSON.parse(json);
      if (list && list.length > 0) {
        list.forEach(file => {
          imgListNew.push({
            uid: this.$cf.getNumGuid(),
            status: "success",
            url: configInfo.imgEchoAddress + file,
          })
          console.log('guid:', this.imageList)
        })
        this.imageList = imgListNew;
      }
    },
    getJsonFromList () {
      let imgs = []
      this.imageList.forEach(item => {
        imgs.push(item.data)
      })
      imgs = imgs.join(',')
      return imgs
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
        if (newValue !== oldValue) {
          this.getListFromJson(newValue);
          console.log('new value change:' + this.imageList.length)
        }

      },
      deep: true,
      immediate: true,
    },
    imageList: {
      handler: function (newValue) {
        let newFilesJson = this.getJsonFromList();
        this.$emit("update:filesJson", newFilesJson);
        console.log('内部变化，' + newFilesJson)
      },
      deep: true,
      immediate: true,
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
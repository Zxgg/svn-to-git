<template>
  <div class="Main"
       ref="MainView">
    <el-upload :action="upLoadUrl"
               drag
               :multiple="false"
               :limit="1"
               :headers="{Authorization:token}"
               list-type="picture-card"
               :on-preview="handlePictureCardPreview"
               :on-remove="handleRemove"
               :on-success="handleSuccess"
               :on-error="handleError"
               :accept="acceptType"
               :file-list="imageList">
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%"
           :src="dialogImageUrl"
           alt="">
    </el-dialog>
  </div>
</template>
<script>
import { getToken } from '@/utils/auth'
import configInfo from "@/webconfig/configInfo";

//import cameraTreeLazy from '@/components/cameraTree/cameraTree_lazy'
export default {
  //components: { cameraTreeLazy },
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
    acceptType: {
      type: String,
      default () {
        return '.jpg,.jpeg,.png,.gif,.bmp,.JPG,.JPEG,.PBG,.GIF,.BMP'
      }
    }
  },
  data () {
    return {
      that: this,
      dialogImageUrl: '',
      dialogVisible: false,
      //图片上传成功的地址
      imageList: [],//用于回显和承载上传后的图片，图片路径可能时外部回显的http地址和本地上传的blob地址
      imageList2: [],//用于记录上传后的地址
      imagePathPrefix: 'http://10.17.13.103:80',//图片回显前缀
      // acceptType: '.jpg,.jpeg,.png,.gif,.bmp,.JPG,.JPEG,.PBG,.GIF,.BMP'
    }
  },
  created () { },
  mounted () {
    this.imagePathPrefix = configInfo.imgEchoAddress
    this.setLayout()
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
      let index = this.getIndex(file.uid)
      this.imageList.splice(index, 1);
      this.imageList2.splice(index, 1);
    },
    handlePictureCardPreview (file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleSuccess (response, file, fileList) {
      // let url = file.response.data
      let url = this.imagePathPrefix + file.response.data
      this.imageList = []
      this.imageList.push({
        uid: file.uid,
        status: "success",
        name: file.name,
        url: url,
        size: file.size,
        type: file.raw.type
      })
      url = file.response.data
      this.imageList2 = []
      this.imageList2.push({
        uid: file.uid,
        status: "success",
        name: file.name,
        url: url,
        size: file.size,
        type: file.raw.type
      })
    },
    handleError (err, file, fileList) {
      console.log('upload error', file)
    },
    getIndex (uid) {
      for (var i = 0; i < this.imageList.length; i++) {
        if (this.imageList[i].uid == uid) return i;
      }
      return -1;
    },
    addWaibuJsonList (list) {
      if (list && list.constructor === Array && list.length > 0) {
        let newList = []
        let newList2 = []

        list.forEach(element => {
          let url = element.url
          newList2.push({
            uid: element.uid,
            status: "success",
            name: element.name,
            url: url
          })
          if (element.url !== undefined && element.url !== null && element.url.indexOf('http') === 0) {
          } else {
            url = this.imagePathPrefix + element.url
          }
          newList.push({
            uid: element.uid,
            status: "success",
            name: element.name,
            url: url
          })
        });
        this.imageList = newList
        this.imageList2 = newList2
      } else if (list && list.constructor === Object) {
        this.imageList2.push(list)
        if (list.url !== undefined && list.url !== null && list.url.indexOf('http') === 0) {
        } else {
          list.url = this.imagePathPrefix + list.url
        }
        this.imageList.push(list)
      } else {
      }
    },
    getListFromJson (json) {
      if (!(json && json.length > 0)) {
        this.imageList = []
        this.imageList2 = []
        return false
      }
      let list = JSON.parse(json)
      //判断新的外部传入的图片列表是否和当前的一样，一样就不做处理
      if (this.imageList2.length === list.length) {
        //循环判断
        for (let index = 0; index < this.imageList2.length; index++) {
          let waibu = list[index];
          let neibu = this.imageList2[index];
          if (neibu.uid === waibu.uid && neibu.url === waibu.url) {

          } else {
            //只要又一个不一样，就直接重新赋值
            // this.imageList2 = list
            // this.imageList = list
            this.addWaibuJsonList(list)
            break
          }
        }
      } else {
        // this.imageList2 = list
        // this.imageList = list
        this.addWaibuJsonList(list)

      }


    },
    getJsonFromList () {
      if (this.imageList2 && this.imageList2.length > 0) {
        return JSON.stringify(this.imageList2);
      } else {
        return ''
      }

    }
  },
  computed: {
    token: function () {
      return getToken()
    }
  },
  watch: {
    filesJson: {
      handler: function (newValue) {
        this.getListFromJson(newValue)
        console.log('new value change:' + this.imageList2.length)
      },
      deep: true,
      immediate: true
    },
    imageList: {
      handler: function (newValue) {
        let newFilesJson = this.getJsonFromList()
        this.$emit('update:filesJson', newFilesJson)

        console.log('内部变化，' + newFilesJson)
      },
      deep: true,
      immediate: true
    }
  },
  beforeDestroy () { }
}
</script>
<style lang="scss" scoped>
.Main {
  width: 390px;
  height: 100%;
  overflow-x: hidden;
  overflow-y: auto;
}
</style>
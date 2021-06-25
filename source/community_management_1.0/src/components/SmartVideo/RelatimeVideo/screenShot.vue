<template>
  <div class="screemShotDiv">
    <canvas id="canvas"
            class="canvas-style">
    </canvas>
    <textarea name="textBox"
              id="textBox"
              cols="5"
              rows="1"
              ref="textBox"
              class="text-style"
              @blur="textareaBlur"
              @focus="textareaFocus"
              :style="{ display: textareaDisplay }">
    </textarea>
    <span slot="footer"
          class="dialog_footer">
      <el-row class="footerRow">
        <el-button @click="CutImage">裁剪</el-button>
        <el-button class="el-icon-check"
                   @click="ConfirmCut"></el-button>
        <el-button class="el-icon-close"
                   @click="CancelCut"></el-button>
      </el-row>
      <el-row class="footerRow">
        <el-button @click="DrawRectangle">矩形</el-button>
        <el-button @click="DrawLines">画线</el-button>
        <el-button @click="WriteWords">文字</el-button>
        <el-button @click="RevokeDraw">撤销</el-button>
        <el-button @click="RestoreImg">复原</el-button>
        <el-button @click="SaveCanvasImg">保存</el-button>
      </el-row>
      <el-row class="footerRow">
        <el-button @click="ScreenShotUpload">上传</el-button>
        <el-button @click="ScreenShotCancel">取消</el-button>
        <el-button @click="ToSearchFaces">以图搜脸</el-button>
        <el-button @click="ToSearchCars">以图搜车</el-button>
      </el-row>
    </span>
    <el-dialog :title="title"
               :visible.sync="screenShotUploadShow"
               width="800px"
               append-to-body>
      <el-form ref="form"
               :model="form"
               :rules="rules"
               label-width="140px">
        <el-form-item label="添加到"
                      prop="targetLibrary">
          <el-select v-model="targetLibrary"
                     placeholder="请选择要添加到哪个图像库">
            <el-option v-for="dict in targetLibraryOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="文件名"
                      prop="name">
          <el-input v-model="form.name"
                    placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="姓名"
                      v-if="targetLibrary==='image_personinfo_library'"
                      prop="personName">
          <el-input v-model="form.personName"
                    placeholder="请输入用户名称" />
        </el-form-item>
        <el-form-item label="身份证号"
                      v-if="targetLibrary==='image_personinfo_library'"
                      prop="identificationNumber">
          <el-input v-model="form.identificationNumber"
                    placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="车牌号"
                      v-if="targetLibrary==='image_vehicleinfo_library'"
                      prop="plate">
          <el-input v-model="form.plate"
                    placeholder="请输入车牌号" />
        </el-form-item>
        <el-form-item label="车品牌"
                      v-if="targetLibrary==='image_vehicleinfo_library'"
                      prop="brand">
          <el-input v-model="form.brand"
                    placeholder="请输入车品牌" />
        </el-form-item>
        <el-form-item label="文件类型"
                      prop="fileType">
          <el-select v-model="form.fileType"
                     placeholder="请选择文件类型">
            <el-option v-for="dict in fileTypeOptions"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="文件大小"
                      prop="fileSize">
          <el-input v-model="form.fileSize"
                    placeholder="请输入文件大小">
            <template slot="append">KB</template>
          </el-input>
        </el-form-item> -->
        <el-form-item label="文件来源"
                      prop="fileSource">
          <el-input v-model="form.fileSource"
                    placeholder="请输入文件来源" />
        </el-form-item>
        <el-form-item label="备注"
                      prop="remark">
          <el-input v-model="form.remark"
                    placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer"
           class="dialog-footer">
        <el-button type="primary"
                   @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script >
import double_LineVue from '../../dashboard/double_Line.vue'
import configInfo from "@/webconfig/configInfo";
import { getToken } from '@/utils/auth'
import { uploadBase64Image } from '@/api/file/RestIO'
import { addImage_information_library } from "@/api/imagesInformationLibrarys/image_information_library";
import { addImagePerinforLibrary } from "@/api/imagesInformationLibrarys/ImagePerinforLibrary";
import { addImageVehicleinforLibrary } from "@/api/imagesInformationLibrarys/ImageVehicleinforLibrary";
import { addImageVideoLibrary } from "@/api/imagesInformationLibrarys/imageVideoLibrary";

export default {
  name: 'screenShot',
  props: {
    camName: {
      type: String,
      default: ''
    }
  },
  data () {
    return {
      title: '保存',
      form: {},
      //要添加到的图像库的类型
      targetLibrary: null,
      //可选的图像库类型
      targetLibraryOptions: [],
      // 文件类型字典
      fileTypeOptions: [],
      canvas: null,
      context: null,
      canvasWidth: 800,
      canvasHeight: 450,
      startX: '', // 画画开始的X坐标
      startY: '', // 画画开始的Y坐标
      endX: '', // 画画结束的X坐标
      endY: '', // 画画结束的Y坐标
      // textStartX: '', // 文字开始的X坐标
      // textStartY: '', // 文字开始的Y坐标
      cutWidth: '',
      cutHeight: '',
      isMouseDownInCanvas: false, // 鼠标是否按下
      isDrawImg: false,
      isClickCutImage: false,
      isClickDrawRect: false, // 按下矩形按钮
      isClickDrawLines: false, // 按下画线按钮
      isClickWriteWords: false, // 按下文字按钮
      srcImageCanvas: null,
      imageCanvasList: [],
      textareaDisplay: 'none',
      //图片上传接口地址
      fileUpLoadUrl: null,
      //是否显示截图上传弹窗
      screenShotUploadShow: false,
      // 表单校验
      rules: {
        name: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
      },
      WatermarkTitle: 'admin 2020/11/18'
    }
  },
  created () {
    this.getDicts("base_image_info_lib_file_type").then(response => {
      this.fileTypeOptions = response.data;
    });
    this.targetLibraryOptions = [
      {
        dictValue: 'image_information_library',
        dictLabel: '图像信息库'
      },
      {
        dictValue: 'image_video_library',
        dictLabel: '视频信息库'
      },
      {
        dictValue: 'image_personinfo_library',
        dictLabel: '人员图像信息库'
      },
      {
        dictValue: 'image_vehicleinfo_library',
        dictLabel: '车辆图像信息库'
      }
    ]
    this.reset()
  },
  methods: {
    // 表单重置
    reset () {
      this.targetLibrary = 'image_information_library'//默认添加到图片库form.fileType
      this.form = {
        id: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null,
        name: null,
        filePath: null,
        fileType: 'image/jpeg',
        fileSize: null,
        fileSource: null,
        libsId: null,
        libsType: null,
        //截图
        deviceId: null,
        //人员
        personName: null,
        identificationNumber: null,
        //车辆
        brand: null,
        plate: null,
      };
      this.resetForm("form");
    },
    /** 提交按钮 */
    async submitForm () {
      let self = this
      //导出base64格式的图片数据
      var base64Data = this.canvas.toDataURL("image/jpeg", 1.0);
      //获取大小
      var blob = this.$cf.dataURItoBlob(base64Data)
      let response = await uploadBase64Image('screenShot.jpg', base64Data)
      if (!(response && response.code === 200)) {
        self.msgError("图片上传失败");
        return false
      }
      self.form.filePath = response.data
      self.form.fileSize = blob.size / 1024
      self.form.fileType = blob.type
      this.$refs["form"].validate(valid => {
        if (valid) {
          switch (self.targetLibrary) {
            case 'image_information_library':
              addImage_information_library(self.form).then(response => {
                if (response.code === 200) {
                  self.msgSuccess("添加成功");
                  self.screenShotUploadShow = false;
                }
              });
              break
            case 'image_video_library':
              addImageVideoLibrary(self.form).then(response => {
                if (response.code === 200) {
                  self.msgSuccess("添加成功");
                  self.screenShotUploadShow = false;
                }
              });
              break
            case 'image_personinfo_library':
              addImagePerinforLibrary(self.form).then(response => {
                if (response.code === 200) {
                  self.msgSuccess("添加成功");
                  self.screenShotUploadShow = false;
                }
              });
              break
            case 'image_vehicleinfo_library':
              addImageVehicleinforLibrary(self.form).then(response => {
                if (response.code === 200) {
                  self.msgSuccess("添加成功");
                  self.screenShotUploadShow = false;
                }
              });
              break
          }

        }
      });
    },
    // 取消按钮
    cancel () {
      this.screenShotUploadShow = false;
      this.reset();
    },
    drawCanvasWatermark () {
      this.context.rotate((-40 * Math.PI) / 180) // 水印初始偏转角度
      let number = 10
      this.context.font = '14px microsoft yahei'
      this.context.fillStyle = 'rgba(255, 255, 255, 0.1)'
      for (let i = 1; i < number; i++) {
        for (let j = 1; j < i + 1; j++) {
          this.context.fillText(this.WatermarkTitle, -600 + j * 350, i * 100)
        }
      }
      this.context.rotate((40 * Math.PI) / 180) // 水印初始偏转角度
    },
    ScreenShot (nowSelectScreenId, labelsPrompt) {
      this.imageCanvasList = []
      this.isClickCutImage = false
      this.isClickDrawRect = false
      this.isClickDrawLines = false
      this.isClickWriteWords = false
      var video = labelsPrompt
      console.log(video)
      var canvas = document.getElementById('canvas')
      var context = canvas.getContext('2d')
      canvas.width = this.canvasWidth
      canvas.height = this.canvasHeight
      this.canvas = canvas
      this.context = context
      this.context.drawImage(video, 0, 0, canvas.width, canvas.height)
      this.drawCanvasWatermark()
      var srcImg = this.canvas.toDataURL('image/jpeg')
      this.srcImageCanvas = new Image()
      this.srcImageCanvas.src = srcImg
      this.imageCanvasList.push(this.srcImageCanvas)
    },
    CutImage () {
      this.isClickCutImage = true
      this.isClickDrawRect = false
      this.isClickDrawLines = false
      this.isClickWriteWords = false
    },
    ConfirmCut () {
      if (this.isClickCutImage === true) {
        this.context.clearRect(0, 0, this.canvasWidth, this.canvasHeight)
        if (this.imageCanvasList.length === 0) {
          this.context.drawImage(this.srcImageCanvas, this.startX, this.startY, this.cutWidth, this.cutHeight, 0, 0, this.canvasWidth, this.canvasHeight)
        } else {
          this.context.drawImage(this.imageCanvasList[this.imageCanvasList.length - 1], this.startX, this.startY, this.cutWidth, this.cutHeight, 0, 0, this.canvasWidth, this.canvasHeight)
        }
        let imgObj = new Image()
        imgObj.src = this.canvas.toDataURL('image/jpeg')
        this.imageCanvasList.push(imgObj)
      }
    },
    CancelCut () {
      this.context.clearRect(0, 0, this.canvasWidth, this.canvasHeight)
      if (this.imageCanvasList.length === 0) {
        this.context.drawImage(this.srcImageCanvas, 0, 0, this.canvasWidth, this.canvasHeight)
      } else {
        this.context.drawImage(this.imageCanvasList[this.imageCanvasList.length - 1], 0, 0, this.canvasWidth, this.canvasHeight)
      }
    },
    DrawRectangle () {
      this.isClickCutImage = false
      this.isClickDrawRect = true
      this.isClickDrawLines = false
      this.isClickWriteWords = false
    },
    DrawLines () {
      this.isClickCutImage = false
      this.isClickDrawRect = false
      this.isClickDrawLines = true
      this.isClickWriteWords = false
    },
    WriteWords () {
      this.isClickCutImage = false
      this.isClickDrawRect = false
      this.isClickDrawLines = false
      this.isClickWriteWords = true
    },
    textareaFocus () {
      this.isDrawImg = true
    },
    textareaBlur () {
      var txt = document.getElementById('textBox').value
      if (txt === 'undefined' || txt === null || txt === '') {
        this.isDrawImg = false
        this.textareaDisplay = 'none'
        return
      }
      if (this.isClickWriteWords === true) { // 当鼠标有按下操作时执行
        this.context.font = '26px Arial bolder'
        this.context.fillStyle = 'red'
        this.$nextTick(function () {
          this.$refs.textBox.value = ''
          this.textareaDisplay = 'none'
          this.context.fillText(txt, this.startX, this.startY)
        })
      }
    },
    RevokeDraw () {
      this.textareaDisplay = 'none'
      this.imageCanvasList.pop()
      if (this.imageCanvasList.length === 0) {
        this.context.drawImage(this.srcImageCanvas, 0, 0, this.canvasWidth, this.canvasHeight)
      } else {
        this.context.drawImage(this.imageCanvasList[this.imageCanvasList.length - 1], 0, 0, this.canvasWidth, this.canvasHeight)
      }
    },
    RestoreImg () {
      this.context.drawImage(this.srcImageCanvas, 0, 0, this.canvasWidth, this.canvasHeight)
      this.imageCanvasList = []
    },
    SaveCanvasImg () {
      var url = canvas.toDataURL('image/png') // 得到图片的base64编码数据
      var a = document.createElement('a') // 生成一个a元素
      var event = new MouseEvent('click') // 创建一个单击事件
      var imageName = this.camName + '_' + this.commonFunction.TimeToTimeStamp(Date.now()) + '.png'
      a.download = imageName || 'photo' // 设置图片名称
      a.href = url // 将生成的URL设置为a.href属性
      a.dispatchEvent(event) // 触发a的单击事件
    },
    CanvasMouseDown (e) {
      this.isMouseDownInCanvas = true
      this.endX = e.offsetX
      this.endY = e.offsetY
      this.startX = e.offsetX
      this.startY = e.offsetY
      // 文字
      if (this.isMouseDownInCanvas === true && this.isClickWriteWords === true) { // 当按下文字按钮时执行
        this.textareaDisplay = ''
      }
    },
    CanvasMouseMove (e) {
      this.isMouseMoveInCanvas = true
      this.context.imageSmoothingQuality = 'high'
      this.endX = e.offsetX
      this.endY = e.offsetY
      let wwidth = this.endX - this.startX
      let wheigth = this.endY - this.startY
      // 矩形
      if (this.isMouseDownInCanvas === true && this.isClickDrawRect === true) { // 当鼠标有按下操作时执行
        // 清除指定区域的所有像素
        this.context.clearRect(0, 0, this.canvasWidth, this.canvasHeight)
        if (this.imageCanvasList.length === 0) {
          this.context.drawImage(this.srcImageCanvas, 0, 0, this.canvasWidth, this.canvasHeight)
        } else {
          this.context.drawImage(this.imageCanvasList[this.imageCanvasList.length - 1], 0, 0, this.canvasWidth, this.canvasHeight)
        }
        this.context.strokeStyle = 'red' // 矩形框颜色
        this.context.lineWidth = '2' // 矩形框宽度
        this.context.strokeRect(this.startX, this.startY, wwidth, wheigth) // 绘制矩形
        this.isDrawImg = true
      }
      // 画线
      if (this.isMouseDownInCanvas === true && this.isClickDrawLines === true) { // 当鼠标有按下操作时执行
        // 清除指定区域的所有像素
        this.context.clearRect(0, 0, this.canvasWidth, this.canvasHeight)
        if (this.imageCanvasList.length === 0) {
          this.context.drawImage(this.srcImageCanvas, 0, 0, this.canvasWidth, this.canvasHeight)
        } else {
          this.context.drawImage(this.imageCanvasList[this.imageCanvasList.length - 1], 0, 0, this.canvasWidth, this.canvasHeight)
        }
        this.context.beginPath()
        this.context.moveTo(this.startX, this.startY)
        this.context.lineWidth = 2
        this.context.strokeStyle = 'red'
        this.context.lineTo(this.endX, this.endY)
        this.context.stroke()
        this.isDrawImg = true
      }
      // 裁剪
      if (this.isMouseDownInCanvas === true && this.isClickCutImage === true) {
        this.context.clearRect(0, 0, this.canvasWidth, this.canvasHeight)
        if (this.imageCanvasList.length === 0) {
          this.context.drawImage(this.srcImageCanvas, 0, 0, this.canvasWidth, this.canvasHeight)
        } else {
          this.context.drawImage(this.imageCanvasList[this.imageCanvasList.length - 1], 0, 0, this.canvasWidth, this.canvasHeight)
        }
        this.context.strokeStyle = 'blue' // 矩形框颜色
        this.context.lineWidth = '1' // 矩形框宽度
        this.context.strokeRect(this.startX, this.startY, wwidth, wheigth) // 绘制矩形
        this.cutWidth = wwidth
        this.cutHeight = wheigth
      }
    },
    CanvasMouseUp (e) {
      this.isMouseDownInCanvas = false
      if (this.isClickDrawRect === true || this.isClickDrawLines === true) {
        if (this.isDrawImg === true) {
          let imgObj = new Image()
          imgObj.src = this.canvas.toDataURL('image/jpeg')
          this.imageCanvasList.push(imgObj)
        }
      }
      if (this.isClickWriteWords === true && this.isDrawImg === true) {
        let imgObj = new Image()
        imgObj.src = this.canvas.toDataURL('image/jpeg')
        this.imageCanvasList.push(imgObj)
      }
      this.isDrawImg = false
    },
    CanvasMouseMethod () {
      let self = this
      /* 为canvas绑定mouse事件 */
      var mycanvas = document.getElementById("canvas");
      if (mycanvas) {
        mycanvas.onmousedown = function (e) {
          self.CanvasMouseDown(e)
        }
        mycanvas.onmouseup = function (e) {
          self.CanvasMouseUp(e)
        }
        mycanvas.onmousemove = function (e) {
          self.CanvasMouseMove(e)
        }
      }
    },
    /**上传到网格的视频图像信息库 */
    ScreenShotUpload () {
      //弹出选择页面，选择存到哪个图像库，并输入各种参数
      this.screenShotUploadShow = true

    },
    /**原来的上传，上传到视综的视频图像库 */
    ScreenShotUpload_old () {
      let imgSrc = this.canvas.toDataURL('image/jpeg').split(',')
      let timeNow = this.commonFunction.TimeToTimeStamp(Date.now())
      let entity = {
        'title': this.camName + '_' + timeNow + '.png',
        'createTime': this.commonFunction.getTimeByTimeStamp(timeNow),
        'creatorId': this.$store.getters.loginUser.id,
        'creatorName': this.$store.getters.loginUser.name,
        'imgContent': imgSrc[1],
        'orgId': this.$store.getters.getCurrentOrg.code
      }
      this.$store.dispatch('UploadImage', entity).then(async data => {
        if (data != null) {
          if (data.status === '200') {
            this.params.confirmContent = '上传成功！'
          } else {
            this.params.confirmContent = '上传失败！'
          }
        } else {
          this.params.confirmContent = '上传失败！'
        }
      })
      // this.$Notice.$emit('UploadScreenShot', entity)
    },
    ScreenShotCancel () {
      this.$Notice.$emit('ScreenShotCancel')
    },
    ToSearchFaces () {
      this.$router.push({ path: '/ChildSearchFaceByImage', name: 'ChildSearchFaceByImage', params: { imageBaseData: this.canvas.toDataURL('image/jpeg') } })
    },
    ToSearchCars () {
      this.$router.push({ path: '/ChildSearchVehicleByImage', name: 'ChildSearchVehicleByImage', params: { imageBaseData: this.canvas.toDataURL('image/jpeg') } })
    }
  },
  mounted () {
    this.fileUpLoadUrl = configInfo.FileUpLoadUri;
    this.WatermarkTitle = this.$cf.getWaterTxt()
    this.CanvasMouseMethod()
  },
  computed: {
    token: function () {
      return getToken()
    }
  },
}
</script>
<style lang="scss" scoped>
// .canvas-style {
//   // background-color: #fff;
//   float: left;
//   position: absolute;
//   z-index: 2;
//   margin-left: -391px;
// }
// .text-style {
//   float: left;
//   position: absolute;
//   font-size: 24px;
//   color: red;
//   word-break: break-all;
//   background-color: transparent;
//   resize: none;
//   z-index: 3;
//   margin-left: -391px;
//   border: solid 1px red;
// }
// .text-style:focus {
//   border: solid 1px red;
// }
// .screemShotDiv {
//   height: 550px;
// }
// .screemShotDiv .dialog_footer {
//   margin-top: 450px;
//   float: left;
//   position: absolute;
//   z-index: 4;
//   margin-left: -182px;
//   margin-top: 465px;
// }
// .footerRow {
//   margin-bottom: 5px;
// }
</style>

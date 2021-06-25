<template>
  <el-row class="mainContent">
    <el-col id="ChildVehicleGroupManage_left">
      <div id="ChildVehicleGroupManage_left_Input">
        <el-input v-model="ChildVehicleGroupManage_left_InputValue"
                  class="ChildVehicleGroupManage_left_InputS"
                  placeholder="请输入内容搜索" />
        <!-- <a id="ChildVehicleGroupManage_left_Input_a"
           style="font-size: 20px; }"
           title="搜索"
           @click="retrievalVehicleGroup"
           class="icon iconfont"> &#xe617;</a> -->
      </div>
      <div id="ChildVehicleGroupManage_left_Button">
        <div id="ChildVehicleGroupManage_left_Button_">
          <el-button id="ChildVehicleGroupManage_left_Button_1"
                     type="text"
                     size="small"
                     @click="showAddVehicleGroupdialogVisible"
                     class="iconfont iconjia"></el-button>
          <el-button id="ChildVehicleGroupManage_left_Button_1"
                     type="text"
                     size="small"
                     @click="DelectLibrayList"
                     class="iconfont iconjian"></el-button>
        </div>
      </div>
      <div id="ChildVehicleGroupManage_left_Tabel">

        <VehicleBlackGroupDatabassTree ref="FaceBlackDBTree"
                                       @GetBlackList="handleClick"
                                       @UpdataBlackList="ShowUpdataLibaryDialog"
                                       class="ChildVehicleGroupManage_left_Tabel_cameraTree" />
      </div>
    </el-col>
    <el-col id="ChildVehicleGroupManage_right">
      <div id="ChildVehicleGroupManage_right_label">
        <span id="ChildVehicleGroupManage_right_label_value">车辆详情展示</span>
      </div>
      <div id="ChildVehicleGroupManage_right_operation">
        <el-button id="ChoosePictureButtonS"
                   slot="trigger"
                   size="small"
                   @click="showAddAlarmDetail"
                   type="primary">新增车辆黑名单</el-button>

        <span id="ChildVehicleGroupManage_right_operation_span">车牌号：</span>
        <el-input v-model="PersonCarName"
                  class="GrandsonMonitorTaskList_One_ContentInput" />
        <span id="ChildVehicleGroupManage_right_operation_span_carType">车辆类型：</span>
        <el-select v-model="optionsVehicleTypeVuleS"
                   clearable
                   placeholder="请选择">
          <el-option v-for="(item,index) in optionsVehicleType"
                     :key="index"
                     :label="item.label"
                     :value="item.value">
          </el-option>
        </el-select>
        <span id="ChildVehicleGroupManage_right_operation_span_carNumber">车牌类型：</span>
        <el-select v-model="optionsCarPlateTypeVuleS"
                   clearable
                   placeholder="请选择">
          <el-option v-for="(item,index) in optionsCarPlateType"
                     :key="index"
                     :label="item.label"
                     :value="item.value">
          </el-option>
        </el-select>

        <el-button id="ChildVehicleGroupManage_right_operation_button1"
                   slot="trigger"
                   size="small"
                   @click="retrieval"
                   type="primary">检索</el-button>
        <el-button id="ChildVehicleGroupManage_right_operation_button2"
                   slot="trigger"
                   size="small"
                   @click="reset"
                   type="primary">重置</el-button>

        <span id="ChildVehicleGroupManage_right_operation_span_CurrentLibrary">当前库：{{CurrentVehicleGroupName | ellipsis}}</span>
      </div>
      <div id="ChildVehicleGroupManage_right_content">
        <div class="outer-container">
          <div class="inner-container">
            <div v-for="(info,index) in myInfo"
                 :key="index">

              <div id="ChildVehicleGroupManage-Right-Central-Out">
                <div id="ChildVehicleGroupManage-Right-Central-inside">
                  <div id="ChildVehicleGroupManage-Right-Central-inside-One">
                    <label id="ChildVehicleGroupManage-Right-Central-inside-Label">车牌号：{{info.lpn}}</label>
                  </div>
                  <div id="ChildVehicleGroupManage-Right-Central-inside-two">
                    <img id="ImageFaceDate"
                         class="el-upload-list__item-thumbnail"
                         @click="showBigImg(info.urlList.url)"
                         :src="info.urlList.url"
                         alt="">
                  </div>
                  <div id="ChildVehicleGroupManage-Right-Central-inside-three">
                    <label id="ChildVehicleGroupManage-Right-Central-inside-three-Label">车辆详情</label>
                    <a style="font-size: 20px; }"
                       id="ChildVehicleGroupManage-Right-Central-inside-three-xiangqing"
                       title="删除"
                       @click="delectAlarmDetail(info)"
                       class="icon iconfont">&#xe6a0;</a>
                    <a style="font-size: 20px; }"
                       id="ChildVehicleGroupManage-Right-Central-inside-three-xiangqing"
                       title="修改"
                       @click="updateAlarmDetail(info)"
                       class="icon iconfont">&#xe652;</a>
                    <a style="font-size: 20px; }"
                       id="ChildVehicleGroupManage-Right-Central-inside-three-xiangqing"
                       title="详情"
                       @click="showAlarmDetail(info)"
                       class="icon iconfont">&#xe659;</a>
                  </div>
                  <div id="ChildVehicleGroupManage-Right-Central-inside-four">
                    <label id="ChildVehicleGroupManage-Right-Bottom-Camera">车辆类型：{{info.carType | getCarType(that)}}</label>
                    <label id="ChildVehicleGroupManage-Right-Bottom-CameraCode">车牌类型：{{info.brandType | getCarPlateType(that)}}</label>
                    <label id="ChildVehicleGroupManage-Right-Bottom-Time">车身颜色：{{info.vehicleColor | getColorType(that)}}</label>
                  </div>
                </div>
              </div>

            </div>

          </div>
        </div>

      </div>
      <div id="ChildVehicleGroupManage_right_Pagination">
        <div id="PaginationS">
          <Pagination style=" float: right; margin-top:3px"
                      :total="params.total"
                      :page.sync="params.pageIndex"
                      :limit.sync="params.pageSize"
                      @pagination="handleClick(diageFaceData)" />
        </div>
      </div>

      <div v-show="divdialogVisible">
        <el-dialog title="车辆黑名单详细信息"
                   :visible.sync="dialogAlarmInfoVisible"
                   width="800px"
                   :before-close="handleClose">
          <div class="ChildSearchFaceByImageDetailDev">

            <el-col>
              <!-- 人脸图片 -->
              <el-row id="ChildSearchFaceVideo">
                <div id="dialogChildSearchFaceDetailImage">
                  <!-- <img id="dialogChildSearchFaceDetailImageDate"
                       class="el-upload-list__item-thumbnail"
                       :src="ImageFaceDetailBit64"
                       @click="showBigImg(ImageFaceDetailBit64)"
                       alt=""> -->
                  <div v-for="(Imageinfo,index) in oldImageFill"
                       :key="index"
                       id="dialogChildSearchFaceDetailImageDiv">

                    <div id="dialogChildSearchFaceDetailImageDate">
                      <img id="ImageFace_"
                           class="el-upload-list__item-thumbnail"
                           :src="Imageinfo"
                           alt="">
                    </div>
                  </div>
                </div>
              </el-row>
              <!-- 详情 -->
              <el-row id="dialogChildSearchFaceDetail">
                <div id="divdialogVisible-Details">
                  <el-row id="divdialogVisible-Details-row-one">
                    <el-col id="divdialogVisible-Details-row-one-col-one">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车牌号</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车辆类型</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车牌颜色</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车辆品牌</label>
                      </el-row>

                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">年款</label>
                      </el-row>

                    </el-col>
                    <el-col id="divdialogVisible-Details-row-one-col-two">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsName"
                                  readonly
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="optionsVehicleTypeVule"
                                   disabled
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in optionsVehicleType"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="optionsCarPlateColorVule"
                                   disabled
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in optionsCarPlateColor"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="CarTypeVule"
                                   disabled
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in CarType"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsYear"
                                  readonly
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                    </el-col>
                    <el-col id="divdialogVisible-Details-row-one-col-three">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车牌类型</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车主</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车身颜色</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车辆子品牌</label>
                      </el-row>

                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">描述</label>
                      </el-row>

                    </el-col>
                    <el-col id="divdialogVisible-Details-row-one-col-four">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="optionsCarPlateTypeVule"
                                   disabled
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in optionsCarPlateType"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailOwnerName"
                                  readonly
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="optionsCarColorVule"
                                   disabled
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in optionsCarColor"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="carSonTypeVule"
                                   disabled
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in carSonType"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>

                      </el-row>

                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsmiaoshu"
                                  readonly
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>

                    </el-col>
                  </el-row>
                  <el-row id="divdialogVisible-Details-row-three">
                    <el-button id="close"
                               @click="closeAddClueDialogVisible"
                               type="primary">关闭</el-button>
                  </el-row>
                </div>
              </el-row>
            </el-col>

          </div>
          <span slot="footer"
                class="dialog-footer">
          </span>
        </el-dialog>
        <el-dialog title="修改车辆黑名单"
                   :visible.sync="dialogAlarmInfoUpdate"
                   width="800px"
                   :before-close="handleClose">
          <div class="ChildSearchFaceByImageDetailDev">
            <el-col>
              <!-- 人脸图片 -->
              <el-row id="ChildSearchFaceVideo">
                <div id="dialogChildSearchFaceDetailImage">
                  <div id="dialogChildSearchFaceDetailImage_Div">
                    <div v-for="(Imageinfo,index) in oldImageFill"
                         :key="index"
                         id="dialogChildSearchFaceDetailImageDiv">

                      <div id="ImageDiv">
                        <img id="ImageFace"
                             class="el-upload-list__item-thumbnail"
                             :src="Imageinfo"
                             alt="">
                      </div>
                      <el-button @click="delectPaceImage_update(index)"
                                 id="delectimagebutton">删除图片</el-button>
                    </div>
                  </div>
                  <div id="dialogChildSearchFaceDetailImageUploadButton">
                    <el-upload class="upload-demo"
                               id="uploadDemo"
                               ref="upload"
                               action="https://jsonplaceholder.typicode.com/posts/"
                               :show-file-list="false"
                               :on-change="handlePictureCardPreviewAdd"
                               :file-list="fileList"
                               :before-upload="uploadImgUploadAdd"
                               :auto-upload="true">
                      <el-button id="ChoosePictureButton"
                                 slot="trigger"
                                 size="small"
                                 type="primary">选择图片</el-button>
                    </el-upload>
                  </div>
                </div>
              </el-row>
              <!-- 详情 -->
              <el-row id="dialogChildSearchFaceDetail">
                <div id="divdialogVisible-Details">
                  <el-row id="divdialogVisible-Details-row-one">
                    <el-col id="divdialogVisible-Details-row-one-col-one">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车牌号</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车辆类型</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车牌颜色</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车辆品牌</label>
                      </el-row>

                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">年款</label>
                      </el-row>

                    </el-col>
                    <el-col id="divdialogVisible-Details-row-one-col-two">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsName"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="optionsVehicleTypeVule"
                                   clearable
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in optionsVehicleType"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="optionsCarPlateColorVule"
                                   clearable
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in optionsCarPlateColor"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="CarTypeVule"
                                   clearable
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in CarType"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsYear"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>

                    </el-col>
                    <el-col id="divdialogVisible-Details-row-one-col-three">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车牌类型</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车主</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车身颜色</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车辆子品牌</label>
                      </el-row>

                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">选择图片</label>
                      </el-row>

                    </el-col>
                    <el-col id="divdialogVisible-Details-row-one-col-four">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="optionsCarPlateTypeVule"
                                   clearable
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in optionsCarPlateType"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailOwnerName"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="optionsCarColorVule"
                                   clearable
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in optionsCarColor"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="carSonTypeVule"
                                   clearable
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in carSonType"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>

                      </el-row>

                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <!-- <el-input v-model="detailsmiaoshu"
                                  readonly
                                  unselectable="on"
                                  class="personIDCardInput" /> -->
                      </el-row>

                    </el-col>
                  </el-row>
                  <el-row id="divdialogVisible-Details-row-two">
                    <el-col id="divdialogVisible-Details-row-two-col-one">
                      <label id="divdialogVisible-Details-row-two-col-one-label">描述 </label>
                    </el-col>
                    <el-col id="divdialogVisible-Details-row-two-col-two-2">
                      <el-input v-model="detailsDescribe"
                                unselectable="on"
                                class="personIDCardInput" />
                    </el-col>
                  </el-row>
                  <el-row id="divdialogVisible-Details-row-three-">
                    <el-button id="close"
                               @click="loadImage"
                               type="primary">保存</el-button>
                    <el-button id="close"
                               @click="closeAddClueDialogVisible"
                               type="primary">取消</el-button>
                  </el-row>
                </div>
              </el-row>
            </el-col>

          </div>
          <span slot="footer"
                class="dialog-footer">
          </span>
        </el-dialog>

        <el-dialog title="新增车辆黑名单"
                   :visible.sync="dialogAlarmInfoAdd"
                   width="800px"
                   :before-close="handleClose">
          <div class="ChildSearchFaceByImageDetailDevNew">

            <!-- <el-col> -->
            <el-form :model="ruleModel"
                     :rules="rules"
                     :validate-on-rule-change="false"
                     ref="ruleForm"
                     label-width="100px"
                     class="demo-ruleForm">
              <!-- 人脸图片 -->
              <el-row id="ChildSearchFaceVideo">
                <div id="dialogChildSearchFaceDetailImageNew">
                  <!-- <div id="dialogChildSearchFaceDetailImage_Div"> -->
                  <el-form-item>
                    <div v-for="(Imageinfo,index) in ImageInfoList"
                         :key="index"
                         id="dialogChildSearchFaceDetailImageDiv">

                      <div id="ImageDiv">
                        <img id="ImageFaceNew"
                             class="el-upload-list__item-thumbnail"
                             :src="Imageinfo"
                             alt="">
                      </div>
                      <el-button @click="delectPaceImage(index)"
                                 id="delectimagebutton">删除图片</el-button>
                    </div>
                  </el-form-item>
                  <!-- </div> -->
                  <!-- <div id="dialogChildSearchFaceDetailImageUploadButton"> -->

                  <!-- </div> -->

                </div>
              </el-row>
              <!-- 详情 -->
              <el-row id="dialogChildSearchFaceDetailNew">
                <div id="divdialogVisible-DetailsNew">
                  <el-row id="divdialogVisible-Details-row-one">
                    <!-- <el-col id="divdialogVisible-Details-row-one-col-one"> -->
                    <!-- <el-row id="divdialogVisible-Details-row-one-col-one-row-one"> -->
                    <!-- <el-form-item label="车牌号"
                                    prop="name">
                        <el-input v-model="detailsName"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-form-item> -->
                    <!-- <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车牌号</label> -->
                    <!-- </el-row> -->
                    <!-- <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车辆类型</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车牌颜色</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车辆品牌</label>
                      </el-row>

                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">年款</label>
                      </el-row> -->

                    <!-- </el-col> -->
                    <el-col id="divdialogVisible-Details-row-one-col-twoNew">
                      <!-- <el-row id="divdialogVisible-Details-row-one-col-one-row-one"> -->
                      <el-form-item label="车牌号"
                                    prop="detailsName">
                        <el-input v-model="ruleModel.detailsName"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-form-item>
                      <!-- </el-row> -->
                      <!-- <el-row id="divdialogVisible-Details-row-one-col-one-row-one"> -->
                      <el-form-item label="车辆类型"
                                    prop="optionsVehicleTypeVule">
                        <el-select v-model="ruleModel.optionsVehicleTypeVule"
                                   clearable
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in optionsVehicleType"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-form-item>
                      <!-- </el-row> -->
                      <!-- <el-row id="divdialogVisible-Details-row-one-col-one-row-one"> -->
                      <el-form-item label="车牌颜色"
                                    prop="optionsCarPlateColorVule">
                        <el-select v-model="ruleModel.optionsCarPlateColorVule"
                                   clearable
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in optionsCarPlateColor"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-form-item>
                      <!-- </el-row> -->
                      <!-- <el-row id="divdialogVisible-Details-row-one-col-one-row-one"> -->
                      <el-form-item label="车辆品牌"
                                    prop="CarTypeVule">
                        <el-select v-model="CarTypeVule"
                                   clearable
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in CarType"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-form-item>
                      <!-- </el-row> -->
                      <!-- <el-row id="divdialogVisible-Details-row-one-col-one-row-one"> -->
                      <el-form-item label="年款"
                                    prop="detailsYear">
                        <el-input v-model="detailsYear"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-form-item>
                      <!-- </el-row> -->

                    </el-col>
                    <!-- <el-col id="divdialogVisible-Details-row-one-col-three">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车牌类型</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车主</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车身颜色</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">车辆子品牌</label>
                      </el-row>

                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">选择图片</label>
                      </el-row>

                    </el-col> -->
                    <el-col id="divdialogVisible-Details-row-one-col-fourNew">
                      <!-- <el-row id="divdialogVisible-Details-row-one-col-one-row-one"> -->
                      <el-form-item label="车牌类型"
                                    prop="optionsCarPlateTypeVule">
                        <el-select v-model="ruleModel.optionsCarPlateTypeVule"
                                   clearable
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in optionsCarPlateType"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-form-item>
                      <!-- </el-row> -->
                      <!-- <el-row id="divdialogVisible-Details-row-one-col-one-row-one"> -->
                      <el-form-item label="车主"
                                    prop="detailOwnerName">
                        <el-input v-model="detailOwnerName"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-form-item>
                      <!-- </el-row> -->
                      <!-- <el-row id="divdialogVisible-Details-row-one-col-one-row-one"> -->
                      <el-form-item label="车身颜色"
                                    prop="optionsCarColorVule">
                        <el-select v-model="ruleModel.optionsCarColorVule"
                                   clearable
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in optionsCarColor"
                                     :key="index.value"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-form-item>
                      <!-- </el-row> -->
                      <!-- <el-row id="divdialogVisible-Details-row-one-col-one-row-one"> -->
                      <el-form-item label="车辆子品牌"
                                    prop="carSonTypeVule">
                        <el-select v-model="carSonTypeVule"
                                   clearable
                                   placeholder="请选择">
                          <el-option v-for="(item,index) in carSonType"
                                     :key="index"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-form-item>
                      <el-form-item label="选择图片"
                                    prop="fileList">
                        <el-upload class="upload-demo"
                                   id="uploadDemo"
                                   ref="upload"
                                   action="https://jsonplaceholder.typicode.com/posts/"
                                   :show-file-list="false"
                                   :on-change="handlePictureCardPreviewAdd"
                                   :file-list="fileList"
                                   :before-upload="uploadImgAdd"
                                   :auto-upload="true">
                          <el-button slot="trigger"
                                     size="small"
                                     type="primary">选择图片</el-button>
                        </el-upload>
                      </el-form-item>
                      <!-- </el-row> -->

                      <!-- <el-row id="divdialogVisible-Details-row-one-col-one-row-one"> -->
                      <!-- <el-input v-model="detailsmiaoshu"
                                  readonly
                                  unselectable="on"
                                  class="personIDCardInput" /> -->
                      <!-- </el-row> -->

                    </el-col>
                  </el-row>
                  <el-row id="divdialogVisible-Details-row-twoNew">
                    <!-- <el-col id="divdialogVisible-Details-row-two-col-one">
                      <label id="divdialogVisible-Details-row-two-col-one-label">描述 </label>
                    </el-col> -->
                    <!-- <el-col id="divdialogVisible-Details-row-two-col-two-2"> -->
                    <el-form-item label="描述"
                                  prop="detailsDescribe">
                      <el-input v-model="detailsDescribe"
                                unselectable="on"
                                class="personIDCardInput" />
                    </el-form-item>

                    <!-- </el-col> -->
                  </el-row>
                  <!-- <el-row id="divdialogVisible-Details-row-three"> -->
                  <!-- <el-form-item> -->

                  <!-- </el-form-item> -->
                  <!-- </el-row> -->
                </div>
              </el-row>
              <!-- </el-col> -->
            </el-form>

          </div>
          <span slot="footer"
                class="dialog-footer"
                style="text-align: center !important;">
            <el-button id="close"
                       @click="AddPaceList('ruleForm')"
                       type="primary">保存</el-button>
            <el-button id="close"
                       @click="closeAddClueDialogVisible"
                       type="primary">取消</el-button>
          </span>
        </el-dialog>

        <!--大图-->
        <el-dialog title="大图"
                   :visible.sync="fullImagedialogVisible"
                   :before-close="handleClose">
          <div class="full-img">
            <img :src="BigImgSrc"
                 width="800px"
                 height="550px">
          </div>
          <span slot="footer"
                class="dialog-footer">
            <el-button type="primary"
                       @click="fullImagedialogVisible = false">确 定</el-button>
          </span>
        </el-dialog>

      </div>
      <div id="divDialogVisibleTwo"
           v-show="divdialogVisibleTwo">
        <!-- 新增黑名单组库 -->
        <el-dialog title="新增黑名单组库"
                   :visible.sync="AddVehicleGroupdialogVisible"
                   :before-close="handleClose">
          <div id="AddVehicleGroupdialogVisible_Div">
            <el-row id="AddVehicleGroupdialogVisible_Div_row_top">
              <a id="AddVehicleGroupdialogVisible_Div_row_top_a">新增车辆黑名单组</a>
            </el-row>
            <el-row id="AddVehicleGroupdialogVisible_Div_row_bottom">
              <el-row id="AddVehicleGroupdialogVisible_Div_row_bottom_1">
                <el-col id="AddVehicleGroupdialogVisible_Div_row_bottom_Label">
                  <a id="AddVehicleGroupdialogVisible_Div_row_bottom_Label_a">库名称</a>
                </el-col>
                <el-col id="AddVehicleGroupdialogVisible_Div_row_bottom_input">
                  <el-input v-model="AddVehicleGroupdialogVisible_Div_row_bottom_input_Name"
                            class="AddVehicleGroupdialogVisible_Div_row_bottom_input_name" />
                </el-col>
              </el-row>
              <el-row id="AddVehicleGroupdialogVisible_Div_row_bottom_2">
                <el-col id="AddVehicleGroupdialogVisible_Div_row_bottom_Label">
                  <a id="AddVehicleGroupdialogVisible_Div_row_bottom_Label_a">描述</a>
                </el-col>
                <el-col id="AddVehicleGroupdialogVisible_Div_row_bottom_input_2">
                  <el-input v-model="AddVehicleGroupdialogVisible_Div_row_bottom_input_describe"
                            class="AddVehicleGroupdialogVisible_Div_row_bottom_input_name" />
                </el-col>
              </el-row>
              <el-button id="AddVehicleGroupdialogVisible_Div_row_bottom_button"
                         @click="AddLibraryList"
                         type="primary">保存</el-button>
              <el-button id="AddVehicleGroupdialogVisible_Div_row_bottom_button"
                         @click="CloseAddLibraryListDialog"
                         type="primary">取消</el-button>
            </el-row>
          </div>
        </el-dialog>

        <!-- 修改名单库 -->
        <el-dialog title="修改黑名单组库"
                   :visible.sync="UpdateVehicleGroupdialogVisible"
                   :before-close="handleClose">
          <div id="AddVehicleGroupdialogVisible_Div">
            <el-row id="AddVehicleGroupdialogVisible_Div_row_top">
              <a id="AddVehicleGroupdialogVisible_Div_row_top_a">修改黑名单组</a>
            </el-row>
            <el-row id="AddVehicleGroupdialogVisible_Div_row_bottom">
              <el-row id="AddVehicleGroupdialogVisible_Div_row_bottom_1">
                <el-col id="AddVehicleGroupdialogVisible_Div_row_bottom_Label">
                  <a id="AddVehicleGroupdialogVisible_Div_row_bottom_Label_a">组名称</a>
                </el-col>
                <el-col id="AddVehicleGroupdialogVisible_Div_row_bottom_input">
                  <el-input v-model="AddVehicleGroupdialogVisible_Div_row_bottom_input_Name_upload"
                            class="AddVehicleGroupdialogVisible_Div_row_bottom_input_name" />
                </el-col>
              </el-row>
              <el-row id="AddVehicleGroupdialogVisible_Div_row_bottom_2">
                <el-col id="AddVehicleGroupdialogVisible_Div_row_bottom_Label">
                  <a id="AddVehicleGroupdialogVisible_Div_row_bottom_Label_a">描述</a>
                </el-col>
                <el-col id="AddVehicleGroupdialogVisible_Div_row_bottom_input_2">
                  <el-input v-model="AddVehicleGroupdialogVisible_Div_row_bottom_input_describe_upload"
                            class="AddVehicleGroupdialogVisible_Div_row_bottom_input_name" />
                </el-col>
              </el-row>
              <el-button id="AddVehicleGroupdialogVisible_Div_row_bottom_button"
                         @click="AddLibraryList_upload"
                         type="primary">保存</el-button>
              <el-button id="AddVehicleGroupdialogVisible_Div_row_bottom_button"
                         @click="CloseUpdateLibraryListDialog"
                         type="primary">取消</el-button>
            </el-row>
          </div>
        </el-dialog>
      </div>

      <div id="divdialogVisibleThree"
           v-show="divDialogVisibleThree">
        <!-- 批量名单导入 -->
        <el-dialog title="批量导入"
                   :visible.sync="BatchListImportDialogVisible"
                   :before-close="handleClose">
          <div id="BatchListImportDialogVisible_Div">
            <el-row id="BatchListImportDialogVisible_Div_one">
              <a id="BatchListImportDialogVisible_Div_one_a">{{CurrentVehicleGroupName}}</a>
              <el-button id="BatchListImportDialogVisible_Div_one_button"
                         @click="importFaceList">导入</el-button>
            </el-row>
            <el-row id="BatchListImportDialogVisible_Div_tow">
              <div id="BatchListImportDialogVisible_Div_tow_div">
                <!-- <el-tabs v-model="activeNameS"
                         class="BatchListImportDialogVisible_Div_tow_div_tabs">
                  <el-tab-pane label="黑名单"
                               name="first">
                    <VehicleGroupBlackDatabaseTree ref="VehicleGroupBlackDBTree"
                                                   class="ChildVehicleGroupManage_left_Tabel_cameraTree" />
                  </el-tab-pane>
                  <el-tab-pane label="白名单"
                               name="second">
                    <VehicleGroupWhiteDatabaseTree ref="VehicleGroupWhiteDBTree"
                                                   class="ChildVehicleGroupManage_left_Tabel_cameraTree" />
                  </el-tab-pane>
                  <el-tab-pane label="红名单"
                               name="three">
                    <VehicleGroupRedDatabaseTree ref="VehicleGroupRedDBTree"
                                                 class="ChildVehicleGroupManage_left_Tabel_cameraTree" />
                  </el-tab-pane>
                  <el-tab-pane label="静态库"
                               name="four">
                    <VehicleGroupSStaticDatabaseTree ref="VehicleGroupStaticSDBTree"
                                                     class="RealTimeVideoPlayView_tabs_cameraTree" />
                  </el-tab-pane>
                </el-tabs> -->
              </div>
            </el-row>
          </div>
        </el-dialog>

      </div>

    </el-col>
  </el-row>
</template>
<script>
// import Pagination from '@/components/Pagination/Pagination'
import VehicleBlackGroupDatabassTree from './VehicleBlackGroupDatabassTree'
import { optionsSex, detailsDocumentType, optionsVehicleType, optionsCarPlateColor, optionsCarPlateType, optionsCarColor } from '@/common/enum'
/* import { searchAlarmHistoryCarInfo } from '@/api/AlarmManagement' */
import {
  IntelligentAnalysis_uplodeImage, GetImageBase64, GetFacesLibaryPicResultByFileID, GetStaticLibaryPicResultByFileID, UpdateVehicleGroup,
  AddPeopleList, GetVehicleRepositoriesList, searchDictionaryList, findVehicleBrandTypeList, GetVehicleBlackList, AddStaticPeopleList,
  AddVehicle, AddVehicleGroup, DeleteVehicleGroupById
} from '@/api/pvMonitor/IntelligentAnalysis'

export default {
  components: { VehicleBlackGroupDatabassTree },
  data () {
    return {
      that: this,
      ruleModel: {
        detailsName: '',
        optionsVehicleTypeVule: '',
        optionsCarPlateColorVule: '',
        optionsCarPlateTypeVule: '',
        optionsCarColorVule: ''
      },
      rules: {
        detailsName: [{ required: true, message: '请输入车牌号', trigger: 'blur' }], // 车牌号
        optionsVehicleTypeVule: [{ required: true, message: '请选择车辆类型', trigger: 'change' }], // 车辆类型
        optionsCarPlateColorVule: [{ required: true, message: '请选择车牌颜色', trigger: 'change' }], // 车牌颜色
        // CarTypeVule: [], // 车辆品牌
        // detailsYear: [], //  年款
        optionsCarPlateTypeVule: [{ required: true, message: '请选择车牌类型', trigger: 'change' }], //  车牌类型
        // detailOwnerName: [], //  车主
        optionsCarColorVule: [{ required: true, message: '请选择车身颜色', trigger: 'change' }] //  车身颜色
        // carSonTypeVule: [], //  车辆子品牌
        // detailsDescribe: [] //  描述
        // ImageInfoList: [{ required: true, message: '请选择图片', trigger: 'blur' }] //  图片
      },
      ChildVehicleGroupManage_left_InputValue: '',
      activeName: 'first', // tab默认页
      activeNameS: 'first', // tab默认页
      PersonCarId: '',
      params: {
        pageIndex: 1,
        pageSize: 20,
        total: 0
      },
      ImageFaceDateBit64: '',
      optionsVehicleTypeVule: '',
      optionsVehicleType: optionsVehicleType,
      optionsCarPlateColorVule: '',
      optionsCarPlateColor: optionsCarPlateColor,
      CarTypeVule: '', // 车辆品牌
      optionsCarPlateTypeVule: '',
      detailsmiaoshu: '', // 描述
      optionsCarPlateType: optionsCarPlateType,
      carSonTypeVule: '',
      optionsCarColorVule: '',
      optionsCarColor: optionsCarColor,
      myInfo: [],
      ImageInfoList: [], // 图片集合
      info: '',
      divdialogVisible: false,
      dialogAlarmInfoVisible: false,
      FaceInfoData: [],
      ImageFaceDetailBit64: '', // 告警详情图片
      fullImagedialogVisible: false, // 大图显隐
      detailsName: '', // 详情中的姓名
      detailsDocumentTypeVule: '', // 详情中的证件类型
      detailsYear: '', // 详情中的年款
      detailsSex: '', // 详情中的性别
      detailsTime: '', // 详情中的出生日期
      detailsDocumentCode: '', // 详情中的证件编码
      dialogAlarmInfoUpdate: false, // 修改
      AddVehicleGroupdialogVisible: false, // 新增名单库
      AddStaticVehicleGroupdialogVisible: false, // 新增静态名单库
      UpdataStaticVehicleGroupdialogVisible: false, // 修改静态名单库
      UpdateVehicleGroupdialogVisible: false, // 修改名单库
      dialogAlarmInfoAdd: false, // 新增名单
      BatchListImportDialogVisible: false, // 批量人员导入框
      optionsVehicleTypeVuleS: '', // 车辆类型（检索中使用）
      optionsCarPlateTypeVuleS: '', // 车牌类型（检索中使用）
      optionsSex: optionsSex,
      detailsDocumentType: detailsDocumentType,
      PersonCarType: detailsDocumentType,
      PersonCarTypeVule: '', // 检索框中类型
      detailOwnerName: '', // 详情中的车主
      fileList: [],
      dialogImageUrl: '',
      AddVehicleGroupdialogVisible_Div_row_bottom_input_Name: '', // 新增名单库中的名称
      AddVehicleGroupdialogVisible_Div_row_bottom_input_Name_upload: '', // 修改名单库中的名称
      AddVehicleGroupdialogVisible_Div_row_bottom_input_Name_upload_static: '', // 修改静态名单库的名称
      AddVehicleGroupdialogVisible_Div_row_bottom_input_describe: '', // 新增名单库中的备注
      AddVehicleGroupdialogVisible_Div_row_bottom_input_describe_upload: '', // 修改名单库中的备注
      AddVehicleGroupdialogVisible_Div_row_bottom_input_peopelLabel: '', // 新增静态名单库中的用户标签
      AddVehicleGroupdialogVisible_Div_row_bottom_input_peopelLabel_upload: '', // 新增静态名单库中的用户标签
      AddVehicleGroupdialogVisible_Div_row_bottom_input_describe_upload_static: '', // 新增静态名单库中的备注
      CurrentVehicleGroupName: '', // 当前库名称
      CurrentVehicleGroupId: '', // 当前库Id
      PersonCarName: '', // 证件姓名
      localFile: {},
      localFileList: [], // 增加中的图片地址集合
      imgFillIdList: [], // 图片fillid集合
      UpdataInfoData: '', // 修改中的人员信息
      detailsNation: '', // 民族
      detailsProfession: '', // 职业
      detailsCountry: '', // 详情中的国家
      detailsDescribe: '', // 描述
      diageFaceData: '', // 弹框中修改删除增加后刷新页面使用的变量

      detailsNameS: '',
      detailsSexS: '',
      detailsNationS: '',
      detailsDescribeS: '',
      detailsTimeS: '',
      detailsProfessionS: '',
      detailsCountryS: '',
      detailsDocumentCodeS: '',
      detailsDocumentTypeVuleS: '',
      imageFill: [],
      oldImageFill: [], // 旧的图片base64集合，从数据库中直接读取到图片转换成base64的集合。
      oldImageFillId: [], // 旧的图片fillId集合，从数据库中直接读取到图片的FillId的集合。
      NewImageFillId: [], // 修改名单方法中的新的FillId的集合
      NewImageFill: [], // 新的图片地址用来上传图片使用的地址
      plateId: '', // 修改车辆黑名单使用的 车牌ID

      peopleInfoData: { // 静态库取出来的数据 批量添加操作使用
        bornTime: '',
        country: '',
        credentialNumber: '',
        domicile: '',
        presentPlace: '',
        credentialType: '',
        description: '',
        gender: '',
        name: '',
        nationality: '',
        occupation: '',
        tag: '',
        pictures: ''
      },
      BigImgSrc: '',
      carSonTypeAll: [], // 车辆子品牌(全集)
      carSonType: [], // 车辆子品牌（子集，一旦选了品牌，从全集中找到对应的子品牌）
      CarType: [], // 车辆品牌
      divDialogVisibleThree: false,
      divdialogVisibleTwo: false
    }
  },
  filters: {
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 20) {
        return value.slice(0, 20) + '...'
      }
      return value
    },
    getCarType: function (value, self) {
      if (value != null && value !== '' && self.optionsVehicleType !== null) {
        let list = self.optionsVehicleType.filter((item) => {
          return item.value === value
        })
        if (list !== undefined && list !== null && list.length > 0) { return list[0].label }
      }
      return value
    },
    getCarPlateType: function (value, self) {
      if (value != null && value !== '' && self.optionsCarPlateType !== null) {
        let list = self.optionsCarPlateType.filter((item) => {
          return item.value === value
        })
        if (list !== undefined && list !== null && list.length > 0) { return list[0].label }
      }
      return value
    },
    getColorType: function (value, self) {
      if (value != null && value !== '' && self.optionsCarColor !== null) {
        let list = self.optionsCarColor.filter((item) => {
          return item.value === value
        })
        if (list !== undefined && list !== null && list.length > 0) { return list[0].label }
      }
      return value
    }
  },
  methods: {
    handleClose (done) {
      done()
    },
    messageTips (messageVule) {
      this.$message({
        message: messageVule,
        type: 'warning',
        center: true
      })
    },
    handleClick (faceData) {
      if (faceData) {
        if (faceData.groupid) {
          const self = this
          self.myInfo = []
          this.diageFaceData = faceData
          let orgCode = {
            condition: {
              page: this.params.pageIndex,
              limit: this.params.pageSize
              // page: 1,
              // limit: 20
            },
            type: '0',
            plate: {
              groupid: faceData.groupid
            }
          }
          this.CurrentVehicleGroupName = faceData.name
          this.CurrentVehicleGroupId = faceData.groupid

          GetVehicleBlackList(orgCode).then((filedata) => { // 查询车辆库

            if (filedata) {

              if (filedata.data) {
                self.myInfo = filedata.data
                this.params.total = filedata.page.total
                // this.params.pageIndex = filedata.page.currentPage
                // this.params.pageSize = filedata.page.rows
                if (filedata.data.length !== 0) {
                  for (let i = 0; i < filedata.data.length; i++) {
                    self.getImageByFileId(i, filedata.data[i].pictures.picture.fileId)
                  }
                }
              } else {
                this.messageTips('无符合条件数据')
              }
            }
          })
        }
      }
    },
    retrieval () { // 检索功能
      if (this.CurrentVehicleGroupId) {
        const self = this
        self.myInfo = []
        let orgCode = {
          condition: {
            page: this.params.pageIndex,
            limit: this.params.pageSize
          },
          type: '0',
          plate: {
            groupid: this.CurrentVehicleGroupId,
            lpn: this.PersonCarName,
            carType: this.optionsVehicleTypeVuleS,
            plateUse: this.optionsCarPlateTypeVuleS
          }
        }
        GetVehicleBlackList(orgCode).then((filedata) => { // 查询人脸库

          if (filedata) {
            if (filedata.data) {
              self.myInfo = filedata.data
              this.params.total = filedata.page.total
              // this.params.pageIndex = filedata.page.currentPage
              // this.params.pageSize = filedata.page.rows
              if (filedata.length !== 0) {
                for (let i = 0; i < filedata.length; i++) {
                  this.getImageByFileId(i, filedata[i].pictures.picture.fileId)
                }
              }
            } else {
              this.messageTips('无符合条件数据')
            }
          }
        })
      } else {
        this.commonFunction.dialogSuccess('请先选择车辆黑名单组')
      }
    },
    handlePictureCardPreview (file, fileList) {
      this.dialogImageUrl = file.url
      this.localFile = file.raw // 或者 this.localFile=file.raw

      // 转换操作可以不放到这个函数里面，
      // 因为这个函数会被多次触发，上传时触发，上传成功也触发
      let reader = new FileReader()
      reader.readAsDataURL(this.localFile)// 这里也可以直接写参数event.raw

      // 转换成功后的操作，reader.result即为转换后的DataURL ，
      // 它不需要自己定义，你可以console.log(reader.result)看一下
      reader.onload = () => {
        console.log(reader.result)
        this.dialogImageUrl = reader.result
      }
      // /* 另外一种本地预览方法 */
      /* let URL = window.URL || window.webkitURL;
      this.dialogImageUrl = URL.createObjectURL(file.raw); */
    },
    handlePictureCardPreviewAdd (file, fileList) {
      this.localFile = file.raw
    },
    uploadImgAdd () {
      if (this.ImageInfoList.length === 5) {
        this.commonFunction.dialogSuccess('最多可上传5张图片')
      } else {
        let reader = new FileReader()
        reader.readAsDataURL(this.localFile)// 这里也可以直接写参数event.raw
        reader.onload = () => {
          console.log(reader.result)
          this.localFileList.push(this.localFile)
          this.ImageInfoList.push(reader.result)
        }
      }
    },
    uploadImgUploadAdd () {
      if (this.oldImageFill.length === 5) {
        this.commonFunction.dialogSuccess('最多可上传5张图片')
      } else {
        let reader = new FileReader()
        reader.readAsDataURL(this.localFile)// 这里也可以直接写参数event.raw
        reader.onload = () => {
          console.log(reader.result)
          this.localFileList.push(this.localFile)
          this.oldImageFill.push(reader.result)
          this.NewImageFill.push(this.localFile)
        }
      }
    },
    async getImageByFileId (index, fileId) {
      const self = this
      GetImageBase64(fileId).then(async dataThree => { // 根据fileid获取图片bit64

        let imageBasa64 = 'data:image/png;base64,' + dataThree.data
        self.myInfo[index].urlList.url = imageBasa64
      })
    },
    async getImageByFileIdS (fileId) {
      // const self = this
      GetImageBase64(fileId).then(response => {

        let imgBaseD = response.data.data
        this.oldImageFill.push('data:image/png;base64,' + imgBaseD)
      })
    },
    showAlarmDetail (InfoDate) { // xiangqing
      // let self = this
      this.oldImageFill = []
      this.oldImageFillId = []
      this.NewImageFillId = []
      this.NewImageFill = []
      var faceListData = []
      if (InfoDate.carList.length !== 0) {
        faceListData = InfoDate.carList[0].carId.split(',')
        if (faceListData.length !== 0) {
          for (let j = 0; j < faceListData.length; j++) {
            this.getImageByFileIdS(faceListData[j])
          }
        }
      }
      this.dialogAlarmInfoVisible = true
      this.dialogAlarmInfoUpdate = false // 修改
      this.AddVehicleGroupdialogVisible = false // 新增名单库
      this.AddStaticVehicleGroupdialogVisible = false // 新增静态名单库
      this.UpdataStaticVehicleGroupdialogVisible = false // 修改静态名单库
      this.UpdateVehicleGroupdialogVisible = false // 修改名单库
      this.dialogAlarmInfoAdd = false // 新增名单
      this.BatchListImportDialogVisible = false // 批量人员导入框
      this.divdialogVisible = true
      this.ControlFaceInfoData = InfoDate
      this.detailsName = InfoDate.lpn // 详情中的车牌号
      this.optionsVehicleTypeVule = InfoDate.carType// 详情中的车辆类型
      this.optionsCarPlateColorVule = InfoDate.vehicleColor // 详情中的车身颜色
      this.CarTypeVule = InfoDate.vehiclebrand // 详情中的车辆品牌
      this.detailsYear = InfoDate.year // 详情中的年款
      this.detailOwnerName = InfoDate.ownerName // 车主
      this.optionsCarPlateTypeVule = InfoDate.plateUse // 详情中车牌类型
      this.optionsCarColorVule = InfoDate.vehicleColor // 车身颜色
      this.carSonTypeVule = InfoDate.vehicleSub // 车辆子品牌
      this.detailsmiaoshu = InfoDate.description // 描述
    },
    updateAlarmDetail (InfoDate) { // 修改弹框
      // let self = this

      this.oldImageFill = []
      this.oldImageFillId = []
      this.NewImageFill = []
      this.NewImageFillId = []
      this.plateId = ''
      var faceListData = []
      if (JSON.stringify(InfoDate.carList) === undefined || JSON.stringify(InfoDate.carList) === null || InfoDate.carList === '') { } else {
        if (JSON.stringify(InfoDate.carList[0].carId) === undefined || JSON.stringify(InfoDate.carList[0].carId) === null) { } else {
          if (InfoDate.carList[0].carId.length !== 0) {
            faceListData = InfoDate.carList[0].carId.split(',')
          }
        }
      }

      if (faceListData.length !== 0) {
        for (let j = 0; j < faceListData.length; j++) {
          this.oldImageFillId.push(faceListData[j])
          this.getImageByFileIdS(faceListData[j])
        }
      }
      this.dialogAlarmInfoUpdate = true
      this.dialogAlarmInfoVisible = false
      this.dialogAlarmInfoAdd = false
      this.AddVehicleGroupdialogVisible = false // 新增名单库
      this.AddStaticVehicleGroupdialogVisible = false // 新增静态名单库
      this.UpdataStaticVehicleGroupdialogVisible = false // 修改静态名单库
      this.UpdateVehicleGroupdialogVisible = false // 修改名单库
      this.BatchListImportDialogVisible = false // 批量人员导入框
      this.divdialogVisible = true
      this.ControlFaceInfoData = InfoDate
      this.plateId = InfoDate.plateId

      this.detailsName = InfoDate.lpn // 详情中的车牌号
      this.optionsVehicleTypeVule = InfoDate.carType// 详情中的车辆类型
      this.optionsCarPlateColorVule = InfoDate.vehicleColor // 详情中的车身颜色
      this.CarTypeVule = InfoDate.vehiclebrand // 详情中的车辆品牌

      this.detailsYear = InfoDate.year // 详情中的年款
      this.detailOwnerName = InfoDate.ownerName // 车主
      this.optionsCarPlateTypeVule = InfoDate.plateUse // 详情中车牌类型
      this.optionsCarColorVule = InfoDate.vehicleColor // 车身颜色
      this.carSonTypeVule = InfoDate.vehicleSub // 车辆子品牌
      this.detailsmiaoshu = InfoDate.description // 描述
    },
    async loadImage () { // 上传图片
      let imagedata = this.oldImageFill.length
      if (imagedata == null || imagedata === 'undefined' || imagedata === 0) {
        this.commonFunction.dialogSuccess('至少要有一张图片')
      } else {
        if (this.NewImageFill == null || this.NewImageFill === 'undefined' || this.NewImageFill.length === 0) {
          this.UpdatePeopleListData()
        } else {
          let files = this.NewImageFill
          for (let i = 0; i < this.NewImageFill.length; i++) {
            var form = new FormData()
            form.append('files', files[i])
            form.append('source_system_id', '0')
            form.append('type', '1')
            IntelligentAnalysis_uplodeImage(form).then(fileIdss => {
              this.NewImageFillId.push(fileIdss.data.data[0].casefile.casefileid)
              this.UpdatePeopleListData()
            })
          }
        }
      }
    },
    UpdatePeopleListData () { // 修改车辆黑名单
      var AllFillIDList = []
      if (this.oldImageFillId == null || this.oldImageFillId === 'undefined' || this.oldImageFillId.length === 0) {
      } else {
        for (let i = 0; i < this.oldImageFillId.length; i++) {
          AllFillIDList.push({ carId: this.oldImageFillId[i] })
        }
      }

      if (this.NewImageFill == null || this.NewImageFill === 'undefined' || this.NewImageFill.length === 0) {
      } else {
        for (let j = 0; j < this.NewImageFillId.length; j++) {
          AllFillIDList.push({ carId: this.NewImageFillId[j] })
        }
      }

      let orgData = {
        plateList: {
          plate: {
            '@lpn': this.detailsName,
            '@ownerId': null,
            '@plateId': this.plateId,
            '@ownerName': this.detailOwnerName,
            '@plateColor': this.optionsCarPlateColorVule,
            '@plateUse': this.optionsCarPlateTypeVule,
            '@vehicleColor': this.optionsCarColorVule,
            '@carType': this.optionsVehicleTypeVule,
            '@vehicleBrand': this.CarTypeVule,
            '@vehicleSub': this.carSonTypeVule,
            carList: AllFillIDList,
            groupid: this.CurrentVehicleGroupId,
            '@vehicleYear': this.detailsYear,
            '@description': this.detailsDescribe
          }
        },
        type: '0'
      }

      // 暂时封印
      UpdateVehicle(orgData).then((filedata) => { // 修改车辆

        let fileIdss = filedata
        if (fileIdss.status === '200' && fileIdss.message === '请求成功') {
          this.commonFunction.dialogSuccess('修改成功')
          this.handleClick(this.diageFaceData)
          this.detailsName = null
          this.detailsSex = null
          this.detailsNation = null
          this.detailsDescribe = null
          this.detailsTime = null
          this.detailsProfession = null
          this.detailsCountry = null
          this.detailsDocumentTypeVule = null
          this.detailsDocumentCode = null
          this.dialogAlarmInfoUpdate = false
          this.divdialogVisible = false
        } else {
          this.commonFunction.dialogSuccess('修改失败')
          this.handleClick(this.diageFaceData)
          this.detailsName = null
          this.detailsSex = null
          this.detailsNation = null
          this.detailsDescribe = null
          this.detailsTime = null
          this.detailsProfession = null
          this.detailsCountry = null
          this.dialogAlarmInfoUpdate = false
          this.divdialogVisible = false
        }
      })
    },
    closeAddClueDialogVisible () {
      this.divdialogVisible = false
      this.dialogAddClueVisible = false
      this.dialogAlarmInfoVisible = false
      this.dialogAlarmInfoUpdate = false // 修改
      this.AddVehicleGroupdialogVisible = false // 新增名单库
      this.AddStaticVehicleGroupdialogVisible = false // 新增静态名单库
      this.UpdataStaticVehicleGroupdialogVisible = false // 修改静态名单库
      this.UpdateVehicleGroupdialogVisible = false // 修改名单库
      this.dialogAlarmInfoAdd = false // 新增名单
      this.BatchListImportDialogVisible = false // 批量人员导入框
    },
    async showBigImg (BigImgInfoData) { // 查看大图
      this.fullImagedialogVisible = true
      this.divdialogVisible = true
      // this.BigImgSrc = BigImgInfoData
      this.BigImgSrc = await this.commonFunction.GetImageBase64_AddWaterMarker(BigImgInfoData)
    },
    reset () { // 重置
      // this.CurrentVehicleGroupId = ''
      this.optionsVehicleTypeVuleS = '' // 车辆类型
      this.optionsCarPlateTypeVuleS = '' // 车牌类型
      this.PersonCarName = ''
    },
    showAddAlarmDetail () { // 打开新增人脸弹框
      this.$nextTick(() => {
        // this.$refs.ruleForm.clearValidate() // 移除校验结果
        if (this.$refs.ruleForm) {
          this.$refs.ruleForm.resetFields()
        }
      })
      this.ruleModel.detailsName = ''
      this.detailOwnerName = ''
      this.ruleModel.optionsCarColorVule = ''
      this.ruleModel.optionsCarPlateColorVule = ''
      this.ruleModel.optionsCarPlateTypeVule = ''
      this.ruleModel.optionsVehicleTypeVule = ''
      this.CarTypeVule = ''
      this.carSonTypeVule = ''
      this.detailsYear = ''
      this.detailsDescribe = ''
      // this.dialogAlarmInfoAdd = false
      // this.divdialogVisible = false
      this.NewImageFillId = []
      this.NewImageFill = []
      this.ImageInfoList = []
      this.localFileList = []
      this.oldImageFill = []
      this.NewImageFill = []
      let nowVehicleGroupId = this.CurrentVehicleGroupId
      if (nowVehicleGroupId === null || nowVehicleGroupId === 'undefined') {
        this.commonFunction.dialogSuccess('请先选择车辆黑名单库')
      } else {
        if (nowVehicleGroupId.length === 0) { this.commonFunction.dialogSuccess('请先选择车辆黑名单库') } else {
          this.dialogAlarmInfoAdd = true
          this.dialogAlarmInfoUpdate = false
          this.dialogAlarmInfoVisible = false
          this.divdialogVisible = true
          this.dialogImageUrl = ''
        }
      }
    },
    showAddVehicleGroupdialogVisible () {
      this.divdialogVisibleTwo = true
      if (this.activeName === 'four') {
        this.AddStaticVehicleGroupdialogVisible = true
      } else {
        this.AddVehicleGroupdialogVisible = true
      }
    },
    CloseAddLibraryListDialog () {
      this.AddVehicleGroupdialogVisible = false
      this.AddStaticVehicleGroupdialogVisible = false
      this.divdialogVisibleTwo = false
    },
    AddLibraryList () {
      // 新增人脸库接口
      let self = this
      let orgCode = {
        type: '0',
        description: this.AddVehicleGroupdialogVisible_Div_row_bottom_input_describe,
        name: this.AddVehicleGroupdialogVisible_Div_row_bottom_input_Name,
        scheme: '1'
      }
      AddVehicleGroup(orgCode).then((filedata) => { // 新增车辆黑名单组

        let fileIdss = filedata
        if (fileIdss.message === '请求成功' && fileIdss.status === '200') {
          self.commonFunction.dialogSuccess('添加成功')
          self.AddVehicleGroupdialogVisible = false
          self.divdialogVisibleTwo = false
          self.$refs.FaceBlackDBTree.getOrgList()
        }
      })
    },
    DelectLibrayList () { // 删除名单库
      this.$confirm('是否确认删除该数据项?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(res => {
        if (res === 'confirm') {
          if (this.CurrentVehicleGroupId === 'null' || this.CurrentVehicleGroupId === 'undefined') {
            this.commonFunction.dialogSuccess('请先选择车辆黑名单库')
          } else {
            if (this.CurrentVehicleGroupId.length === 0) { this.commonFunction.dialogSuccess('请先选择车辆黑名单库') } else {
              let orgData = {
                grouplist: {
                  groupid: [
                    this.CurrentVehicleGroupId
                  ]
                },
                type: '0',
                isDelList: 'true'
              }
              DeleteVehicleGroupById(orgData).then((filedata) => { // 删除人脸库

                let fileIdss = filedata
                if (fileIdss.status === '200' && fileIdss.message === '请求成功') {
                  this.commonFunction.dialogSuccess('删除成功')
                  this.$refs.FaceBlackDBTree.getOrgList()
                  this.CurrentVehicleGroupName = null
                } else {
                  this.commonFunction.dialogSuccess('删除失败')
                  this.$refs.FaceBlackDBTree.getOrgList()
                  this.CurrentVehicleGroupId = null
                  this.CurrentVehicleGroupName = null
                }
              })
            }
          }
        }
      })
    },
    delectAlarmDetail (InfoDate) { // 删除车辆黑名单
      this.$confirm('是否确认删除该数据项?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(res => {
        if (res === 'confirm') {
          let orgData = {
            plateList: [
              {
                plateid: [
                  InfoDate.plateId
                ]
              }
            ],
            deleteType: '1',
            type: '0'
          }
          DeleteVehicleById(orgData).then((returnData) => { // 删除车辆黑名单

            let returnDataS = returnData
            if (returnDataS.status === '200' && returnDataS.message === '请求成功') {
              this.commonFunction.dialogSuccess('删除成功')
              this.handleClick(this.diageFaceData)
            } else {
              this.commonFunction.dialogSuccess('删除失败')
            }
          })
        }
      })
    },
    delectPaceImage (imgDataList) {
      // let imageDataInfo = this.ImageInfoList
      // 删除元素
      this.ImageInfoList.splice(imgDataList, 1)
    },
    delectPaceImage_update (imgDataList) {
      // let imageDataInfo = this.oldImageFill
      // 删除元素
      if (this.oldImageFill.length > imgDataList) {
        this.oldImageFill.splice(imgDataList, 1)
        this.oldImageFillId.splice(imgDataList, 1)
      } else {
        let sum = imgDataList - this.oldImageFill.length
        this.NewImageFillId.splice(sum, 1)
        this.NewImageFill.splice(sum, 1)
      }
    },
    async AddPaceList2 () {

      let fileList = this.localFileList
      let self = this
      // if (fileList === 'null' || fileList === 'undefined' || this.detailsName === 'null' || this.detailsName === 'undefined' || this.optionsVehicleTypeVule === 'null' || this.optionsVehicleTypeVule === 'undefined' || this.optionsCarPlateColorVule === 'null' || this.optionsCarPlateColorVule === 'undefind' || this.optionsCarPlateTypeVule === 'null' || this.optionsCarPlateTypeVule === 'undefind' || this.optionsCarColorVule === 'null' || this.optionsCarColorVule === 'unfefind') {
      //   this.commonFunction.dialogSuccess('图片，车牌号，车牌类型，车辆类型，车牌颜色，车身颜色不能为空')
      // } else {
      //   if (fileList.length === 0 || this.detailsName.length === 0 || this.optionsVehicleTypeVule.length === 0 || this.optionsCarPlateColorVule.length === 0 || this.optionsCarPlateTypeVule.length === 0 || this.optionsCarColorVule.length === 0) {
      //     this.commonFunction.dialogSuccess('图片，车牌号，车牌类型，车辆类型，车牌颜色，车身颜色不能为空')
      //   }
      if (fileList === 'null' || fileList === 'undefined') {
        this.commonFunction.dialogSuccess('图片不能为空')
      } else {
        if (fileList.length === 0) {
          this.commonFunction.dialogSuccess('图片不能为空')
        } else {
          // let self = this
          for (let i = 0; i < fileList.length; i++) {
            // var ss = fileList[i]
            var form = new FormData()
            form.append('files', fileList[i])
            form.append('source_system_id', '0')
            form.append('type', '1')
            IntelligentAnalysis_uplodeImage(form).then(response => {

              let fileIdss = response.data
              this.imgFillIdList.push(fileIdss[0].casefile.casefileid)
              var imgFillIdData = this.imgFillIdList
              for (let i = 0; i < imgFillIdData.length; i++) {
                this.imageFill.push({ carId: imgFillIdData[i] })
              }
              let orgCode = {
                plateList: {
                  plate: {
                    '@lpn': this.ruleModel.detailsName,
                    '@ownerId': null,
                    '@plateId': '',
                    '@ownerName': this.detailOwnerName,
                    '@plateColor': this.ruleModel.optionsCarPlateColorVule,
                    '@plateUse': this.ruleModel.optionsCarPlateTypeVule,
                    '@vehicleColor': this.ruleModel.optionsCarColorVule,
                    '@carType': this.ruleModel.optionsVehicleTypeVule,
                    '@vehicleBrand': this.CarTypeVule,
                    '@vehicleSub': this.carSonTypeVule,
                    carList: this.imageFill,
                    groupid: this.CurrentVehicleGroupId,
                    '@vehicleYear': this.detailsYear,
                    '@description': this.detailsDescribe
                  }
                },
                type: '0'
              }
              // 先检查是否已经有这个车牌的黑名单，没有才添加，有的话就给出弹窗提示
              let parm = {
                condition: {
                  limit: 10,
                  page: 1
                },
                type: 0,
                plate: {
                  lpn: this.ruleModel.detailsName
                }
              }
              let repeatFlag = false
              GetVehicleBlackList(parm).then(response => {
                if (response.status === '200' && response.data != null && response.data.length > 0) {
                  self.commonFunction.dialogError('黑名单库中已经存在此车牌！')
                  repeatFlag = true
                  // return false
                }
                if (repeatFlag) return false
                // let peoplelist
                AddVehicle(orgCode).then((filedata) => { // 新增人脸库

                  let data = filedata
                  if (data.status === '200' || data.message === '请求成功') {
                    this.commonFunction.dialogSuccess('添加成功')
                    // this.ruleModel.detailsName = null
                    // this.detailOwnerName = null
                    // this.ruleModel.optionsCarColorVule = null
                    // this.ruleModel.optionsCarPlateColorVule = null
                    // this.ruleModel.optionsCarColorVule = null
                    // this.ruleModel.optionsVehicleTypeVule = null
                    // this.CarTypeVule = null
                    // this.carSonTypeVule = null
                    // this.detailsYear = null
                    // this.detailsDescribe = null
                    this.dialogAlarmInfoAdd = false
                    this.dialogAlarmInfoUpdate = false
                    this.divdialogVisible = false
                    // this.NewImageFillId = []
                    // this.NewImageFill = []
                    // this.ImageInfoList = []
                    // this.localFileList = []
                    // this.oldImageFill = []
                    // this.NewImageFill = []
                    this.handleClick(this.diageFaceData)
                  } else {
                    this.commonFunction.dialogSuccess('添加失败')
                    // this.ruleModel.detailsName = null
                    // this.detailOwnerName = null
                    // this.ruleModel.optionsCarColorVule = null
                    // this.ruleModel.optionsCarPlateColorVule = null
                    // this.ruleModel.optionsCarColorVule = null
                    // this.ruleModel.optionsVehicleTypeVule = null
                    // this.CarTypeVule = null
                    // this.carSonTypeVule = null
                    // this.detailsYear = null
                    // this.detailsDescribe = null
                    this.dialogAlarmInfoAdd = false
                    this.dialogAlarmInfoUpdate = false
                    this.divdialogVisible = false
                    // this.NewImageFillId = []
                    // this.NewImageFill = []
                    // this.ImageInfoList = []
                    // this.localFileList = []
                    // this.oldImageFill = []
                    // this.NewImageFill = []
                    this.handleClick(this.diageFaceData)
                  }
                })
              })

            })

          }



        }
      }
    },
    async AddPaceList (formName) { // 增加车辆黑名单

      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.AddPaceList2()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    showBatchListImportDialogVisible () { // 展开批量导入功能
      this.divDialogVisibleThree = true
      this.BatchListImportDialogVisible = true
    },
    async importFaceList () { // 批量导入功能
      if (this.CurrentVehicleGroupName === '') {
        this.commonFunction.dialogSuccess('请选择名单库')
      } else {
        const self = this
        if (this.activeNameS === 'four') {
          let FaceStaticDBTreeVule = ''
          FaceStaticDBTreeVule = this.$refs.VehicleGroupStaticSDBTree.getCheckedFaceDB()
          if (FaceStaticDBTreeVule == null || FaceStaticDBTreeVule === 'undefined') {
            this.commonFunction.dialogSuccess('请选择名单库')
          } else {
            if (FaceStaticDBTreeVule.length === 0) {
              this.commonFunction.dialogSuccess('请选择名单库')
            } else {
              for (let i = 0; i < FaceStaticDBTreeVule.length; i++) {
                let orgCode = {
                  libraryIds: FaceStaticDBTreeVule[0].id,
                  page: {
                    no: this.params.pageIndex,
                    size: this.params.pageSize,
                    sort: 'asc',
                    orderName: 'time'
                  }
                }
                let filedata = await GetStaticLibaryPicResultByFileID(orgCode)
                if (filedata.length !== 0) {
                  var FaceStaticDBVule = []
                  let DAS = filedata.data.data
                  for (let y = 0; y < DAS.length; y++) {
                    let pic = []
                    let picI = []
                    for (let h = 0; h < DAS[y].pictures.length; h++) {
                      pic.push({ fileId: DAS[y].pictures[h].fileId })
                    }
                    picI.push({ picture: pic })
                    DAS.forEach(item => {
                      this.$set(item, 'pictures', picI)
                    })
                    FaceStaticDBVule.push(DAS[y])
                  }
                  if (this.activeName === 'four') {
                    let peopleInfoDataList = []
                    for (let y = 0; y < FaceStaticDBVule.length; y++) {
                      Object.keys(self.peopleInfoData).forEach(key => { self.peopleInfoData[key] = FaceStaticDBVule[y][key] })
                      peopleInfoDataList.push(self.peopleInfoData)
                    }
                    this.AddStaticPeopleListFuction(peopleInfoDataList)
                  } else {
                    let peopleInfoDataList = []
                    for (let y = 0; y < FaceStaticDBVule.length; y++) {
                      Object.keys(self.peopleInfoData).forEach(key => { self.peopleInfoData[key] = FaceStaticDBVule[y][key] })
                      peopleInfoDataList.push(self.peopleInfoData)
                    }
                    this.AddPeopleListFuction(peopleInfoDataList)
                  }
                }
              }
            }
          }
        } else {
          let FaceDBTreeVule = ''
          if (this.activeNameS === 'first') {
            FaceDBTreeVule = this.$refs.VehicleGroupBlackDBTree.getCheckedFaceDB()
          } else if (this.activeNameS === 'second') {
            FaceDBTreeVule = this.$refs.VehicleGroupWhiteDBTree.getCheckedFaceDB()
          } else if (this.activeNameS === 'three') {
            FaceDBTreeVule = this.$refs.VehicleGroupRedDBTree.getCheckedFaceDB()
          }
          if (FaceDBTreeVule === null || FaceDBTreeVule === 'undefined') {
            this.commonFunction.dialogSuccess('请选择名单库')
          } else {
            if (FaceDBTreeVule.length === 0) { this.commonFunction.dialogSuccess('请选择名单库') } else {
              for (let i = 0; i < FaceDBTreeVule.length; i++) {
                let orgCode = {
                  repositorIds: FaceDBTreeVule[0].id,
                  page: {
                    no: this.params.pageIndex,
                    size: this.params.pageSize,
                    sort: 'asc',
                    orderName: 'time'
                  }
                }
                let filedata = await GetFacesLibaryPicResultByFileID(orgCode)
                if (filedata.length !== 0) {
                  let FaceDBVule = []
                  let DAS = filedata.data.data
                  for (let y = 0; y < DAS.length; y++) {
                    let pic = []
                    let picI = []
                    for (let h = 0; h < DAS[y].faceList.length; h++) {
                      pic.push({ fileId: DAS[y].faceList[h].fileId })
                    }

                    picI.push({ picture: pic })

                    DAS.forEach(item => {
                      this.$set(item, 'pictures', picI)
                    })

                    FaceDBVule.push(DAS[y])
                  }
                  if (this.activeName === 'four') {
                    this.AddStaticPeopleListFuction(FaceDBVule)
                  } else {
                    this.AddPeopleListFuction(FaceDBVule)
                  }
                }
              }
            }
          }
        }
      }
    },
    AddPeopleListFuction (InfoData) {
      var FaceDBVule = InfoData
      let orgCodeS = {
        sync: 'true',
        callbackURL: 'null',
        repositorId: this.CurrentVehicleGroupId,
        peopleList: [{
          people:
            FaceDBVule
        }]
      }
      AddPeopleList(orgCodeS).then((filedata) => { // 新增人脸库

        let data = filedata
        if (data.status === '200' || data.message === '请求成功') {
          this.commonFunction.dialogSuccess('添加成功')
        } else {
          this.commonFunction.dialogSuccess('添加失败')
        }
      })
    },
    AddStaticPeopleListFuction (InfoData) {
      let FaceStaticDBVule = InfoData
      let orgCode = {
        sync: 'true',
        callbackURL: 'null',
        libraryId: this.CurrentVehicleGroupId,
        peoples: [{
          people:
            FaceStaticDBVule
        }]
      }
      AddStaticPeopleList(orgCode).then((filedata) => { // 新增静态人脸库
        let data = filedata
        if (data.status === '200' || data.message === '请求成功') {
          this.commonFunction.dialogSuccess('添加成功')
        } else {
          this.commonFunction.dialogSuccess('添加失败')
        }
      })
    },
    choicefirstS () {
      this.CurrentVehicleGroupName = ''
      this.CurrentVehicleGroupId = ''
      this.myInfo = []
    },
    ShowUpdataLibaryDialog (data) { // 打开修改名单库弹框
      this.divdialogVisibleTwo = true
      if (this.activeName === 'four') {
        this.UpdataStaticVehicleGroupdialogVisible = true
        this.UpdateVehicleGroupdialogVisible = false
        this.AddVehicleGroupdialogVisible_Div_row_bottom_input_Name_upload_static = data.name
        this.AddVehicleGroupdialogVisible_Div_row_bottom_input_peopelLabel_upload = data.type
        if (data.type === '2') { this.AddVehicleGroupdialogVisible_Div_row_bottom_input_peopelLabel_upload }
        this.AddVehicleGroupdialogVisible_Div_row_bottom_input_describe_upload_static = data.description
      } else {
        this.UpdateVehicleGroupdialogVisible = true
        this.UpdataStaticVehicleGroupdialogVisible = false
        this.AddVehicleGroupdialogVisible_Div_row_bottom_input_Name_upload = data.name
        this.AddVehicleGroupdialogVisible_Div_row_bottom_input_describe_upload = data.description
      }
    },
    CloseUpdateLibraryListDialog () {
      this.UpdataStaticVehicleGroupdialogVisible = false
      this.UpdateVehicleGroupdialogVisible = false
      this.divdialogVisibleTwo = false
    },
    AddLibraryList_upload () { // 保存修改名单库
      let orgData = {
        description: this.AddVehicleGroupdialogVisible_Div_row_bottom_input_describe_upload,
        name: this.AddVehicleGroupdialogVisible_Div_row_bottom_input_Name_upload,
        type: '0',
        groupid: this.CurrentVehicleGroupId
      }
      UpdateVehicleGroup(orgData).then((filedata) => { // 修改车辆黑名单组

        let data = filedata
        if (data.status === '200' || data.message === '请求成功') {
          this.commonFunction.dialogSuccess('修改成功')
          this.$refs.FaceBlackDBTree.getOrgList()
          this.UpdataStaticVehicleGroupdialogVisible = false
          this.UpdateVehicleGroupdialogVisible = false
          this.divdialogVisibleTwo = false
        } else {
          this.commonFunction.dialogSuccess('修改失败')
          this.$refs.FaceBlackDBTree.getOrgList()
          this.UpdataStaticVehicleGroupdialogVisible = false
          this.UpdateVehicleGroupdialogVisible = false
          this.divdialogVisibleTwo = false
        }
      })
    },
    retrievalVehicleGroup () { // 检索人脸库（搜索的是库不是人脸名单）
      this.$refs.FaceBlackDBTree.getOrgListByName(this.ChildVehicleGroupManage_left_InputValue)
    },
    selectCarBrandTypeChange (carBrandType) {
      let list = this.carSonTypeAll.filter((item) => {
        return item.parentCarType === carBrandType
      })
      this.carSonType = list
    },
    getCarBrandSubTypeMenu () {
      let param = {
        typeCodes: {
          typeCode: 'VCM.SDB.lpr.VehicleSub'
        }
      }
      let self = this
      self.carSonTypeAll = []
      self.carSonType = []
      findVehicleBrandTypeList(param).then(async data => {

        if (data != null && data.data && data.data.typeList && data.data.typeList.type && data.data.typeList.type.dataDictionarys) {
          let dataList = data.data.typeList.type.dataDictionarys.dataDictionary
          dataList.forEach(element => {
            self.carSonTypeAll.push({
              label: element.name,
              value: element.value,
              parentCarType: element.description
            })
          })
        }
      })
    },
    getCarBrandTypeMenu () {
      let param = {
        typeCodes: {
          typeCode: 'VCM.SDB.lpr.VehicleBrand'
        }
      }
      let self = this
      self.CarType = []
      findVehicleBrandTypeList(param).then(async data => {

        if (data != null && data.data && data.data.typeList && data.data.typeList.type && data.data.typeList.type.dataDictionarys) {
          let dataList = data.data.typeList.type.dataDictionarys.dataDictionary
          dataList.forEach(element => {
            self.CarType.push({
              label: element.name,
              value: element.value
            })
          })
        }
      })
    },
    getOptionsVehicleTypeMenu () {
      let entityData = {
        pageIndex: 1,
        pageSize: 10,
        entity: { parentID: 400 }
      }
      let self = this
      self.optionsVehicleType = []
      searchDictionaryList(entityData).then(data => {
        if (data != null) {
          data.data.forEach(element => {
            self.optionsVehicleType.push({
              value: element.value,
              label: element.name
            })
          })
          // self.optionsVehicleType = data.data
        }
      })
    },
    getPlateTypeMenu () {
      let entityData = {
        pageIndex: 1,
        pageSize: 10,
        entity: { parentID: 401 }
      }
      let self = this
      self.optionsCarPlateType = []
      searchDictionaryList(entityData).then(data => {
        if (data != null) {
          data.data.forEach(element => {
            self.optionsCarPlateType.push({
              value: element.value,
              label: element.name
            })
          })
          // self.optionsCarPlateType = data.data
        }
      })
    },
    getCarColorMenu () {
      let entityData = {
        pageIndex: 1,
        pageSize: 10,
        entity: { parentID: 403 }
      }
      let self = this
      searchDictionaryList(entityData).then(data => {
        self.optionsCarColor = []
        if (data != null) {
          data.data.forEach(element => {
            self.optionsCarColor.push({
              value: element.value,
              label: element.name
            })
          })
          // self.optionsCarColor = data.data
        }
      })
    },
    defaultSearchDate () {
      let self = this
      let VehicleList = []
      // 获取车辆黑名单列表
      let queryData = {
        page: {
          no: 1,
          pageSize: 900,
          sort: 'asc',
          orderName: 'time'
        }
      }
      self.data = [{
        children: [],
        name: '车辆黑名单分组',
        Count: 0,
        ID: '1'
      }]
      GetVehicleRepositoriesList(queryData).then((faceStaticRepositoriesdata) => { // 获取根节点组织机构数据

        if (faceStaticRepositoriesdata.data) {
          for (var i = 0; i < faceStaticRepositoriesdata.data.length; i++) {
            let dataType = faceStaticRepositoriesdata.data[i].type
            VehicleList.push(faceStaticRepositoriesdata.data[i])
          }

          this.handleClick(VehicleList[0])
        }
      })
    }
  },
  watch: {
    CarTypeVule (val) {
      this.selectCarBrandTypeChange(val)
    }
  },
  mounted () {
    this.getCarColorMenu()
    this.getPlateTypeMenu()
    this.getOptionsVehicleTypeMenu()
    this.getCarBrandTypeMenu()
    this.getCarBrandSubTypeMenu()
    this.defaultSearchDate()
  }
}
</script>
<style lang="scss" scoped>
#ImageFaceDate {
  width: 98%;
  height: 98%;
  margin-left: 1.5%;
  margin-top: 1%;
}
#ChildVehicleGroupManage_left_Input_a {
  margin-top: 11px;
  margin-left: -337px;
  position: fixed;
  color: #005982;
}
#ChildVehicleGroupManage_left_Input_a:hover {
  color: #11bbf3;
}
#BatchListImportDialogVisible_Div_tow {
  width: 560px;
  height: 580px;
  border: 0.5px solid #005982;
}
#BatchListImportDialogVisible_Div_one {
  width: 560px;
  height: 53px;
}
#BatchListImportDialogVisible_Div {
  width: 560px;
  height: 633px;
}
#dialogChildSearchFaceDetailImage_Div {
  width: 100%;
  height: 215px;
  float: left;
}
#uploadDemo {
  width: 1px;
  height: 1px;
  float: left;
}
#dialogChildSearchFaceDetailImageUploadButton {
  width: 1px;
  height: 1px;
}
#dialogChildSearchFaceDetailImageDiv {
  border: 0.5px solid #005982;
  width: 153.7px;
  height: 215px;
  float: left;
}
#AddVehicleGroupdialogVisible_Div_row_bottom_button {
  margin-top: 20px;
  width: 100px;
}
#AddVehicleGroupdialogVisible_Div_row_bottom_input_3 {
  width: 305px;
  height: 100%;
}
#AddVehicleGroupdialogVisible_Div_row_bottom_input_2 {
  width: 305px;
  height: 100%;
}
#AddVehicleGroupdialogVisible_Div_row_bottom_Label_a {
  color: #0f86ae;
  font-size: 15px;
  line-height: 30px;
}
#AddVehicleGroupdialogVisible_Div_row_bottom_input {
  width: 305px;
  height: 100%;
}
#AddVehicleGroupdialogVisible_Div_row_bottom_Label {
  margin-left: 8px;
  width: 60px;
  height: 100%;
}
#AddVehicleGroupdialogVisible_Div_row_bottom_2 {
  height: 60px;
  margin-top: 20px;
}
#AddVehicleGroupdialogVisible_Div_row_bottom_1 {
  height: 30px;
  margin-top: 30px;
}
#AddVehicleGroupdialogVisible_Div_row_top_a {
  color: #0f86ae;
  margin-top: 7px;
  float: left;
  margin-left: 8px;
  font-size: 15px;
}
#AddVehicleGroupdialogVisible_Div_row_top {
  height: 30px;
  width: 100%;
  border: 0.5px solid #005982;
  border-left: 0px;
  border-top: 0px;
  border-right: 0px;
}
#AddVehicleGroupdialogVisible_Div {
  height: 270px;
  width: 100%;
  margin-left: -1%;
  border: 0.5px solid #005982;
}
#ChildVehicleGroupManage_right_operation_span_CurrentLibrary {
  float: right;
  font-size: 18px;
  color: #0294a0;
  line-height: 48px;
  margin-right: 10px;
}
#ImageFace {
  width: 100%;
  height: 80%;
  margin-top: 10%;
  margin-left: 0px;
}
#ImageFaceNew {
  width: 100%;
  height: 80%;
  margin-top: 10%;
  margin-left: 0px;
}
#ImageFace_ {
  width: 94%;
  height: 100%;
}
#ImageDiv {
  width: 98%;
  height: 80%;
  padding: 1px;
  border: 0.5px solid #005982;
}
#ImageDiv_ {
  width: 98%;
  height: 98%;
  padding: 1px;
}
#divdialogVisible-Details-row-two {
  width: 100%;
  height: 17.5%;
}
#divdialogVisible-Details-row-twoNew {
  width: 92%;
  height: 17.5%;
}
#divdialogVisible-Details-row-two-col-one-label {
  color: #005982;
  font-size: 15px;
  float: right;
  margin-top: 18px;
}
#divdialogVisible-Details-row-two-col-one {
  width: 13%;
  height: 100%;
  margin-left: -7px;
}
#divdialogVisible-Details-row-two-col-one-2 {
  width: 13%;
  height: 100%;
  margin-left: -24px;
}
#divdialogVisible-Details-row-two-col-two {
  width: 87%;
  height: 100%;
}
#divdialogVisible-Details-row-two-col-two-2 {
  width: 84%;
  height: 100%;
  margin-left: 7px;
}
#divdialogVisible-Details-row-two-col-two-2-button {
  width: 170px;
  margin-top: 15px;
}
#divdialogVisible-Details-row-one-col-one-row-one-label {
  color: #005982;
  line-height: 100%;
  font-size: 15px;
  float: right;
  margin-right: 5px;
  margin-top: 8px;
}
#divdialogVisible-Details-row-one-col-one-row-one {
  width: 100%;
  height: 20%;
}
#divdialogVisible-Details-row-one-col-four {
  width: 37%;
  height: 100%;
}
#divdialogVisible-Details-row-one-col-fourNew {
  width: 42%;
  height: 100%;
  margin-left: 8%;
}
#divdialogVisible-Details-row-one-col-three {
  width: 10%;
  height: 100%;
}
#divdialogVisible-Details-row-one-col-two {
  width: 37%;
  height: 100%;
}
#divdialogVisible-Details-row-one-col-twoNew {
  width: 42%;
  height: 100%;
}
#divdialogVisible-Details-row-one-col-one {
  width: 13%;
  height: 100%;
}

#divdialogVisible-Details-row-three {
  width: 100%;
  height: 15%;
}
#divdialogVisible-Details-row-one {
  width: 100%;
  height: 78%;
}
#divdialogVisible-Details {
  width: 92%;
  height: 90%;
  margin-left: 2.5%;
  margin-top: 2%;
}
#divdialogVisible-DetailsNew {
  width: 92%;
  height: 99%;
  margin-left: 4%;
  margin-top: 2%;
}
#dialogChildSearchFaceDetail {
  width: 66.5%;
  margin-left: -10px;
  height: 320px;
  margin-top: 10px;
  border: 0.5px solid #005982;
  border-radius: 4px;
}
#dialogChildSearchFaceDetailNew {
  width: 66.5%;
  margin-left: -10px;
  height: 370px;
  margin-top: 10px;
  border: 0.5px solid #005982;
  border-radius: 4px;
}
#dialogChildSearchFaceDetailImageDate {
  width: 160px;
  height: 213px;
}
#dialogChildSearchFaceDetailImage {
  width: 100%;
  height: 215px;
}
#dialogChildSearchFaceDetailImage_ {
  width: 160px;
  height: 206px;
}
#ChildSearchFaceVideo {
  width: 66.5%;
  margin-left: -10px;
  height: 220px;
  border: 0.5px solid #005982;
  border-radius: 4px;
}
// 弹框详情
.ChildSearchFaceByImageDetailDev {
  width: 160%;
  height: 510px;
}
// 弹框详情
.ChildSearchFaceByImageDetailDevNew {
  width: 160%;
  height: 600px;
}
#ChildVehicleGroupManage-Right-Bottom-Time {
  width: 97%;
  height: 38px;
  float: left;
  line-height: 38px;
  color: #005982;
  margin-left: 6px;
  font-size: 14px;
}
#ChildVehicleGroupManage-Right-Bottom-CameraCode {
  width: 97%;
  height: 38px;
  float: left;
  line-height: 38px;
  color: #005982;
  margin-left: 6px;
  font-size: 14px;
}
#ChildVehicleGroupManage-Right-Bottom-Camera {
  width: 97%;
  height: 38px;
  float: left;
  line-height: 38px;
  color: #005982;
  margin-left: 6px;
  font-size: 14px;
}
#ChildVehicleGroupManage-Right-Central-inside-Label {
  width: 100%;
  height: 80px;
  color: #005982;
  font-size: 14px;
  margin-top: 8px;
}
#ChildVehicleGroupManage-Right-Central-inside-three-Label {
  color: #005982;
  font-size: 14px;
  line-height: 30px;
  margin-left: 10px;
}
#ChildVehicleGroupManage-Right-Central-inside-three-xiangqing {
  margin-top: 5px;
  width: 20px;
  margin-right: 9px;
  height: 20px;
  float: right;
  border: none;
  color: #0f86ae;
}
#ChildVehicleGroupManage-Right-Central-inside-three-xiangqing:hover {
  border: 0.5px solid #00ffff;
}
#ChildVehicleGroupManage-Right-Central-inside-three-dingwei:hover {
  border: 0.5px solid #00ffff;
}
#ChildVehicleGroupManage-Right-Central-inside-three-xiansuoxinxi:hover {
  border: 0.5px solid #00ffff;
}
#ChildVehicleGroupManage-Right-Central-inside-three-renlianchaxun:hover {
  border: 0.5px solid #00ffff;
}

#ChildVehicleGroupManage-Right-Central-inside-three-dingwei {
  margin-top: 5px;
  width: 20px;
  margin-right: 5px;
  height: 20px;
  float: right;
  border: none;
}
#ChildVehicleGroupManage-Right-Central-inside-three-xiansuoxinxi {
  margin-top: 5px;
  width: 20px;
  margin-right: 5px;
  height: 20px;
  float: right;
  border: none;
}
#ChildVehicleGroupManage-Right-Central-inside-three-renlianchaxun {
  margin-right: 5px;
  margin-top: 5px;
  width: 20px;
  height: 20px;
  float: right;
  border: none;
}
#ChildVehicleGroupManage-Right-Central-inside-One {
  width: 100%;
  height: 30px;
  text-align: center;
  margin-top: 8px;
}
#ChildVehicleGroupManage-Right-Central-inside-two {
  width: 96.8%;
  height: 120px;
  margin-left: 1.5%;
  border: 0.5px solid #005982;
  // background: url("../../assets/images/IntelligentAnalysis/imageBackground.png")
  //   no-repeat center center / 80% 100%;
}
#ChildVehicleGroupManage-Right-Central-inside-three {
  width: 99.8%;
  height: 30px;
  margin-top: 3px;
  border: 0.5px solid #005982;
}
#ChildVehicleGroupManage-Right-Central-inside-four {
  width: 99.8%;
  height: 115px;
}
#ChildVehicleGroupManage-Right-Central-inside {
  width: 92%;
  height: 97%;
  margin-left: 3.8%;
  margin-top: 1.5%;
  border: 0.5px solid #005982;
  border-radius: 4px;
}
#ChildVehicleGroupManage-Right-Central-Out:hover {
  border: 0.5px solid #00ffff;
}
#ChildVehicleGroupManage-Right-Central-Out {
  width: 220px;
  height: 320px;
  border: 0.5px solid #005982;
  float: left;
}

#PaginationS {
  width: 40px;
  height: 30px;
  float: right;
  margin-top: 3px;
}
#ChildVehicleGroupManage_left_Button_ {
  float: right;
  margin-top: 5px;
  margin-right: 30px;
}
#ChildVehicleGroupManage_left_Button_1 {
  color: #11bbf3;
  font-size: 25px;
  width: 30px;
}
#ChildVehicleGroupManage_right_operation_button1 {
  width: 100px;
  margin-left: 50px;
}
#ChildVehicleGroupManage_right_operation_button2 {
  width: 100px;
  margin-left: 20px;
}
#ChildVehicleGroupManage_right_operation_span_carNumber {
  font-size: 14px;
  color: #0f86ae;
  margin-left: 10px;
}
#ChildVehicleGroupManage_right_operation_span_carType {
  font-size: 14px;
  color: #0f86ae;
  margin-left: 10px;
}
#ChildVehicleGroupManage_right_operation_span {
  font-size: 14px;
  color: #0f86ae;
  margin-left: 80px;
}
#ChoosePictureButtonS {
  margin-top: 10px;
}
#ChoosePictureButton {
  margin-top: 197px;
  margin-left: 491px;
  width: 180px;
  position: fixed; // 浮动
  z-index: 999999;
}
.GrandsonMonitorTaskList_One_ContentInput {
  width: 130px;
}
#ChildVehicleGroupManage_right_label_value {
  font-size: 22px;
  color: #0294a0;
  line-height: 40px;
  margin-left: 10px;
}
#ChildVehicleGroupManage_right_label {
  width: 99.8%;
  margin-left: 4px;
  margin-top: 3px;
  height: 40px;
  border: 0.5px solid #005982;
  border-radius: 4px;
}
#ChildVehicleGroupManage_right_Pagination {
  width: 99.8%;
  margin-left: 4px;
  height: 40px;
  border: 0.5px solid #005982;
  margin-top: 4px;
}
#ChildVehicleGroupManage_right_operation {
  width: 99.8%;
  margin-left: 4px;
  height: 45px;
  border: 0.5px solid #005982;
  border-right: none;
  border-left: none;
}
#ChildVehicleGroupManage_right_content {
  width: 99.8%;
  margin-left: 4px;
  height: 82%;
  border: 0.5px solid #005982;
  border-radius: 2px;
}
#ChildVehicleGroupManage_left_InputS {
  margin-top: 10px;
}
#ChildVehicleGroupManage_left_Input {
  width: 100%;
  height: 40px;
}
#ChildVehicleGroupManage_left_Button {
  width: 100%;
  height: 40px;
}
#ChildVehicleGroupManage_left_Tabel {
  width: 100%;
  height: 89%;
  border: 0.5px solid #005982;
}
#ChildVehicleGroupManage_left {
  width: 18%;
  height: 100%;
}
#ChildVehicleGroupManage_right {
  width: 81.8%;
  height: 100%;
}

.ChildVehicleGroupManage_left_Tabel_cameraTree {
  height: 100%;
  overflow: auto;
}
.ChildVehicleGroupManage_left_Tabel_cameraTree::-webkit-scrollbar {
  width: 13px;
  height: 16px;
  background-color: #f5f5f5;
}

/*定义滚动条轨道 内阴影+圆角*/
.ChildVehicleGroupManage_left_Tabel_cameraTree::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);

  background-color: #1b384a;
}

/*定义滑块 内阴影+圆角*/
.ChildVehicleGroupManage_left_Tabel_cameraTree::-webkit-scrollbar-thumb {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  background-color: #555;
}
</style>
<style>
.el-form-item__error {
  padding-top: 0px !important;
}
#dialogChildSearchFaceDetailImageNew .el-form-item__content {
  margin-left: 0px !important;
}
.el-dialog__footer {
  text-align: center !important;
}
#BatchListImportDialogVisible_Div_one_a {
  color: #005982;
  font-size: 18px;
}
#BatchListImportDialogVisible_Div_one_button {
  width: 80px;
  float: right;
  margin-right: 15px;
}
#divdialogVisible-Details-row-one-col-one-row-one
  .el-input--suffix
  .el-input__inner {
  height: 30px;
  width: 265px;
}
#ChildVehicleGroupManage_left_Tabel .el-tabs__item {
  width: 25%;
  height: 100% !important;
}
#BatchListImportDialogVisible_Div_tow_div .el-tabs__item {
  width: 25%;
  height: 100% !important;
}
#ChildVehicleGroupManage_right_operation .el-input__icon {
  height: 100%;
  width: 25px;
  text-align: center;
  -webkit-transition: all 0.3s;
  transition: all 0.3s;
  line-height: 12px;
}
#ChildVehicleGroupManage_left_Input .el-input__inner {
  margin-top: 3px;
  border: 0.5px solid #005982;
  height: 35px;
  padding-left: 30px;
}
.mainContent {
  padding: 8px 5px 0px 5px;
}
#divdialogVisible-Details-row-two-col-two .el-input__inner {
  height: 50px !important;
}
#divdialogVisible-Details-row-two-col-two-2 .el-input__inner {
  height: 50px !important;
}
#ChildVehicleGroupManage_left_Button_ .el-button {
  padding: 0px 0px;
  height: 30px;
}
#divDialogVisibleTwo .el-dialog {
  -webkit-transform: none;
  transform: none;
  left: 0;
  position: relative;
  margin: 0 auto;
  width: 420px !important;
}
#divdialogVisibleThree .el-dialog {
  -webkit-transform: none;
  transform: none;
  left: 0;
  position: relative;
  margin: 0 auto;
  width: 600px !important;
}
#AddVehicleGroupdialogVisible_Div_row_bottom_input_2 .el-input__inner {
  height: 60px;
}
#AddVehicleGroupdialogVisible_Div_row_bottom_input_3 .el-input {
  width: 305px;
}
.ChildSearchPersonByImage_tabs .el-tabs__content {
  height: 640px !important;
}
.BatchListImportDialogVisible_Div_tow_div_tabs .el-tabs__content {
  height: 520px !important;
}
</style>

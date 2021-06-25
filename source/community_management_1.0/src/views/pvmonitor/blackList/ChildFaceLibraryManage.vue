<template>
  <el-row class="mainContent">
    <el-col id="ChildFaceLibraryManage_left">
      <div id="ChildFaceLibraryManage_left_Input">
        <el-input v-model="ChildFaceLibraryManage_left_InputValue"
                  class="ChildFaceLibraryManage_left_InputS"
                  placeholder="请输入内容搜索" />
        <!-- <a id="ChildFaceLibraryManage_left_Input_a"
           style="font-size: 20px; }"
           title="搜索"
           @click="retrievalFaceLibrary"
           class="icon iconfont"> &#xe617;</a> -->
      </div>
      <div id="ChildFaceLibraryManage_left_Button">
        <div id="ChildFaceLibraryManage_left_Button_">
          <el-button id="ChildFaceLibraryManage_left_Button_1"
                     type="text"
                     size="small"
                     @click="showAddFaceLibrarydialogVisible"
                     class="iconfont iconjia"></el-button>
          <el-button id="ChildFaceLibraryManage_left_Button_1"
                     type="text"
                     size="small"
                     @click="DelectLibrayList"
                     class="iconfont iconjian"></el-button>
        </div>
      </div>
      <div id="ChildFaceLibraryManage_left_Tabel">
        <el-tabs v-model="activeName"
                 @tab-click="choicefirstS"
                 class="ChildSearchPersonByImage_tabs_S">
          <el-tab-pane label="黑名单"
                       name="first">
            <FaceBlackDatabaseTree ref="FaceBlackDBTree"
                                   @GetBlackFacesLibary="handleClick"
                                   @UpdataFacesLibary="ShowUpdataLibaryDialog"
                                   class="ChildFaceLibraryManage_left_Tabel_cameraTree" />
          </el-tab-pane>
          <el-tab-pane label="白名单"
                       name="second">
            <FaceWhiteDatabaseTree ref="FaceWhiteDBTree"
                                   @GetWhiteFacesLibary="handleClick"
                                   @UpdataFacesLibary="ShowUpdataLibaryDialog"
                                   class="ChildFaceLibraryManage_left_Tabel_cameraTree" />
          </el-tab-pane>
          <!-- <el-tab-pane label="红名单"
                       name="three">
            <FaceRedDatabaseTree ref="FaceRedDBTree"
                                 @GetRedFacesLibary="handleClick"
                                 @UpdataFacesLibary="ShowUpdataLibaryDialog"
                                 class="ChildFaceLibraryManage_left_Tabel_cameraTree" />
          </el-tab-pane>
          <el-tab-pane label="静态库"
                       name="four">
            <FaceLibraryStaticDatabaseTree ref="FaceStaticDBTree"
                                           @GetStaticFacesLibary="handleClick"
                                           @UpdataFacesLibary="ShowUpdataLibaryDialog"
                                           class="RealTimeVideoPlayView_tabs_cameraTree" />
          </el-tab-pane> -->
        </el-tabs>
      </div>
    </el-col>
    <el-col id="ChildFaceLibraryManage_right">
      <div id="ChildFaceLibraryManage_right_label">
        <span id="ChildFaceLibraryManage_right_label_value">人脸名单详情展示</span>
      </div>
      <div id="ChildFaceLibraryManage_right_operation">
        <el-button id="ChildFaceLibraryManage_right_operation_button2"
                   slot="trigger"
                   size="small"
                   @click="showBatchListImportDialogVisible"
                   type="primary">批量人员导入</el-button>
        <el-button id="ChoosePictureButtonS"
                   slot="trigger"
                   size="small"
                   @click="showAddAlarmDetail"
                   type="primary">新增人脸</el-button>

        <span id="ChildFaceLibraryManage_right_operation_span">名称：</span>
        <el-input v-model="PersonCarName"
                  class="GrandsonMonitorTaskList_One_ContentInput" />
        <span id="ChildFaceLibraryManage_right_operation_span_carType">证件类型：</span>
        <el-select v-model="PersonCarTypeVule"
                   placeholder="请选择">
          <el-option v-for="item in PersonCarType"
                     :key="item.value"
                     :label="item.label"
                     :value="item.value">
          </el-option>
        </el-select>
        <span id="ChildFaceLibraryManage_right_operation_span_carNumber">证件号码：</span>
        <el-input v-model="PersonCarId"
                  class="GrandsonMonitorTaskList_One_ContentInput" />

        <el-button id="ChildFaceLibraryManage_right_operation_button1"
                   slot="trigger"
                   size="small"
                   @click="retrieval"
                   type="primary">检索</el-button>
        <el-button id="ChildFaceLibraryManage_right_operation_button2"
                   slot="trigger"
                   size="small"
                   @click="reset"
                   type="primary">重置</el-button>

        <span id="ChildFaceLibraryManage_right_operation_span_CurrentLibrary">当前库：{{CurrentFaceLibraryName | CurrentLibraryEllipsis}}</span>
      </div>
      <div id="ChildFaceLibraryManage_right_content">
        <div class="outer-container">
          <div class="inner-container">
            <div v-for="(info,index) in myInfo"
                 :key="index">

              <div id="ChildFaceLibraryManage-Right-Central-Out">
                <div id="ChildFaceLibraryManage-Right-Central-inside">
                  <div id="ChildFaceLibraryManage-Right-Central-inside-One">
                    <label id="ChildFaceLibraryManage-Right-Central-inside-Label">姓名：{{info.name}}</label>
                  </div>
                  <div id="ChildFaceLibraryManage-Right-Central-inside-two">
                    <img id="ImageFaceDate"
                         class="el-upload-list__item-thumbnail"
                         v-if="activeName !== 'four'"
                         @click="showBigImg(info.faceList[0].url)"
                         :src="info.faceList[0].url"
                         alt="">
                    <img id="ImageFaceDate"
                         class="el-upload-list__item-thumbnail"
                         v-if="activeName === 'four'"
                         @click="showBigImg(info.pictures[0].url)"
                         :src="info.pictures[0].url"
                         alt="">
                  </div>
                  <div id="ChildFaceLibraryManage-Right-Central-inside-three">
                    <label id="ChildFaceLibraryManage-Right-Central-inside-three-Label">人脸详情</label>
                    <a style="font-size: 20px; }"
                       id="ChildFaceLibraryManage-Right-Central-inside-three-xiangqing"
                       title="删除"
                       @click="delectAlarmDetail(info)"
                       class="icon iconfont">&#xe6a0;</a>
                    <a style="font-size: 20px; }"
                       id="ChildFaceLibraryManage-Right-Central-inside-three-xiangqing"
                       title="修改"
                       @click="updateAlarmDetail(info)"
                       class="icon iconfont">&#xe652;</a>
                    <a style="font-size: 20px; }"
                       id="ChildFaceLibraryManage-Right-Central-inside-three-xiangqing"
                       title="详情"
                       @click="showAlarmDetail(info)"
                       class="icon iconfont">&#xe659;</a>
                  </div>
                  <div id="ChildFaceLibraryManage-Right-Central-inside-four">
                    <label id="ChildFaceLibraryManage-Right-Bottom-Camera">证件类型：{{info.credentialType | getDetailsDocumentType(that)}}</label>
                    <label id="ChildFaceLibraryManage-Right-Bottom-CameraCode">证件编码：{{info.credentialNumber| ellipsis}}</label>
                    <label id="ChildFaceLibraryManage-Right-Bottom-Time">性别：{{info.gender | getGender(that)}}</label>
                  </div>
                </div>
              </div>

            </div>

          </div>
        </div>

      </div>
      <div id="ChildFaceLibraryManage_right_Pagination">
        <div id="PaginationS">
          <Pagination style=" float: right; margin-top:3px"
                      :total="params.total"
                      :page.sync="params.pageIndex"
                      :limit.sync="params.pageSize"
                      @pagination="handleClick(diageFaceData)" />
        </div>
      </div>

      <div v-show="divdialogVisible">
        <el-dialog title="人员详细信息"
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
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">姓名</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">证件类型</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">国家</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">职业</label>
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
                        <el-select v-model="detailsDocumentTypeVule"
                                   disabled
                                   placeholder="请选择">
                          <el-option v-for="item in detailsDocumentType"
                                     :key="item.value"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsCountry"
                                  readonly
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsProfession"
                                  readonly
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                    </el-col>
                    <el-col id="divdialogVisible-Details-row-one-col-three">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">性别</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">证件编码</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">民族</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">出生日期</label>
                      </el-row>
                    </el-col>
                    <el-col id="divdialogVisible-Details-row-one-col-four">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="detailsSex"
                                   disabled
                                   placeholder="请选择">
                          <el-option v-for="item in optionsSex"
                                     :key="item.value"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsDocumentCode"
                                  readonly
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsNation"
                                  readonly
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-date-picker v-model="detailsTime"
                                        type="datetime"
                                        value-format="yyyy-MM-dd"
                                        placeholder="年-月-日  时-分-秒">
                        </el-date-picker>

                      </el-row>
                    </el-col>
                  </el-row>
                  <el-row id="divdialogVisible-Details-row-two">
                    <el-col id="divdialogVisible-Details-row-two-col-one">
                      <label id="divdialogVisible-Details-row-two-col-one-label">描述</label>
                    </el-col>
                    <el-col id="divdialogVisible-Details-row-two-col-two">
                      <el-input v-model="detailsDescribe"
                                readonly
                                unselectable="on"
                                class="personIDCardInput" />
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
        <el-dialog title="修改名单"
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
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">姓名</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">证件类型</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">国家</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">职业</label>
                      </el-row>
                    </el-col>
                    <el-col id="divdialogVisible-Details-row-one-col-two">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsName"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="detailsDocumentTypeVule"
                                   placeholder="请选择">
                          <el-option v-for="item in detailsDocumentType"
                                     :key="item.value"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsCountry"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsProfession"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                    </el-col>
                    <el-col id="divdialogVisible-Details-row-one-col-three">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">性别</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">证件编码</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">民族</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">出生日期</label>
                      </el-row>
                    </el-col>
                    <el-col id="divdialogVisible-Details-row-one-col-four">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="detailsSex"
                                   placeholder="请选择">
                          <el-option v-for="item in optionsSex"
                                     :key="item.value"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsDocumentCode"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsNation"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-date-picker v-model="detailsTime"
                                        type="date"
                                        value-format="yyyy-MM-dd"
                                        placeholder="选择日期">
                        </el-date-picker>
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
                    <el-col id="divdialogVisible-Details-row-two-col-one-2">
                      <label id="divdialogVisible-Details-row-two-col-one-label">选择图片</label>
                    </el-col>
                    <el-col id="divdialogVisible-Details-row-two-col-two-2">
                    </el-col>
                  </el-row>
                  <el-row id="divdialogVisible-Details-row-three">
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

        <el-dialog title="新增名单"
                   :visible.sync="dialogAlarmInfoAdd"
                   width="800px"
                   :before-close="handleClose">
          <div class="ChildSearchFaceByImageDetailDev">

            <el-col>
              <!-- 人脸图片 -->
              <el-row id="ChildSearchFaceVideo">
                <div id="dialogChildSearchFaceDetailImage">
                  <div id="dialogChildSearchFaceDetailImage_Div">
                    <div v-for="(Imageinfo,index) in ImageInfoList"
                         :key="index"
                         id="dialogChildSearchFaceDetailImageDiv">

                      <div id="ImageDiv">
                        <img id="ImageFace"
                             class="el-upload-list__item-thumbnail"
                             :src="Imageinfo"
                             alt="">
                      </div>
                      <el-button @click="delectPaceImage(index)"
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
                               :before-upload="uploadImgAdd"
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
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">姓名</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">证件类型</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">国家</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">职业</label>
                      </el-row>
                    </el-col>
                    <el-col id="divdialogVisible-Details-row-one-col-two">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsNameS"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="detailsDocumentTypeVuleS"
                                   placeholder="请选择">
                          <el-option v-for="item in detailsDocumentType"
                                     :key="item.value"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsCountryS"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsProfessionS"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                    </el-col>
                    <el-col id="divdialogVisible-Details-row-one-col-three">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">性别</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">证件编码</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">民族</label>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <label id="divdialogVisible-Details-row-one-col-one-row-one-label">出生日期</label>
                      </el-row>
                    </el-col>
                    <el-col id="divdialogVisible-Details-row-one-col-four">
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-select v-model="detailsSexS"
                                   placeholder="请选择">
                          <el-option v-for="item in optionsSex"
                                     :key="item.value"
                                     :label="item.label"
                                     :value="item.value">
                          </el-option>
                        </el-select>
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsDocumentCodeS"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-input v-model="detailsNationS"
                                  unselectable="on"
                                  class="personIDCardInput" />
                      </el-row>
                      <el-row id="divdialogVisible-Details-row-one-col-one-row-one">
                        <el-date-picker v-model="detailsTimeS"
                                        type="date"
                                        :picker-options="endDatePicker"
                                        value-format="yyyy-MM-dd"
                                        placeholder="选择日期">
                        </el-date-picker>
                      </el-row>
                    </el-col>
                  </el-row>
                  <el-row id="divdialogVisible-Details-row-two">
                    <el-col id="divdialogVisible-Details-row-two-col-one">
                      <label id="divdialogVisible-Details-row-two-col-one-label">描述 </label>
                    </el-col>
                    <el-col id="divdialogVisible-Details-row-two-col-two-2">
                      <el-input v-model="detailsDescribeS"
                                unselectable="on"
                                class="personIDCardInput" />
                    </el-col>
                    <el-col id="divdialogVisible-Details-row-two-col-one-2">
                      <label id="divdialogVisible-Details-row-two-col-one-label">选择图片</label>
                    </el-col>
                    <el-col id="divdialogVisible-Details-row-two-col-two-2">
                    </el-col>
                  </el-row>
                  <el-row id="divdialogVisible-Details-row-three">
                    <el-button id="close"
                               @click="AddPaceList"
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
        <!-- 新增名单库 -->
        <el-dialog title="新增名单库"
                   :visible.sync="AddFaceLibrarydialogVisible"
                   :before-close="handleClose">
          <div id="AddFaceLibrarydialogVisible_Div">
            <el-row id="AddFaceLibrarydialogVisible_Div_row_top">
              <a id="AddFaceLibrarydialogVisible_Div_row_top_a">新增人脸库</a>
            </el-row>
            <el-row id="AddFaceLibrarydialogVisible_Div_row_bottom">
              <el-row id="AddFaceLibrarydialogVisible_Div_row_bottom_1">
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_Label">
                  <a id="AddFaceLibrarydialogVisible_Div_row_bottom_Label_a">库名称</a>
                </el-col>
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_input">
                  <el-input v-model="AddFaceLibrarydialogVisible_Div_row_bottom_input_Name"
                            class="AddFaceLibrarydialogVisible_Div_row_bottom_input_name" />
                </el-col>
              </el-row>
              <el-row id="AddFaceLibrarydialogVisible_Div_row_bottom_1">
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_Label">
                  <a id="AddFaceLibrarydialogVisible_Div_row_bottom_Label_a">库类型</a>
                </el-col>
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_input_3">
                  <el-select v-model="FaceLibraryTypeVule"
                             placeholder="请选择">
                    <el-option v-for="item in FaceLibraryType"
                               :key="item.value"
                               :label="item.label"
                               :value="item.value">
                    </el-option>
                  </el-select>
                </el-col>
              </el-row>
              <el-row id="AddFaceLibrarydialogVisible_Div_row_bottom_2">
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_Label">
                  <a id="AddFaceLibrarydialogVisible_Div_row_bottom_Label_a">描述</a>
                </el-col>
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_input_2">
                  <el-input v-model="AddFaceLibrarydialogVisible_Div_row_bottom_input_describe"
                            class="AddFaceLibrarydialogVisible_Div_row_bottom_input_name" />
                </el-col>
              </el-row>
              <el-button id="AddFaceLibrarydialogVisible_Div_row_bottom_button"
                         @click="AddLibraryList"
                         type="primary">保存</el-button>
              <el-button id="AddFaceLibrarydialogVisible_Div_row_bottom_button"
                         @click="CloseAddLibraryListDialog"
                         type="primary">取消</el-button>
            </el-row>
          </div>
        </el-dialog>
        <!-- 新增静态名单库 -->
        <el-dialog title="新增名单库"
                   :visible.sync="AddStaticFaceLibrarydialogVisible"
                   :before-close="handleClose">
          <div id="AddFaceLibrarydialogVisible_Div">
            <el-row id="AddFaceLibrarydialogVisible_Div_row_top">
              <a id="AddFaceLibrarydialogVisible_Div_row_top_a">新增人脸库</a>
            </el-row>
            <el-row id="AddFaceLibrarydialogVisible_Div_row_bottom">
              <el-row id="AddFaceLibrarydialogVisible_Div_row_bottom_1">
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_Label">
                  <a id="AddFaceLibrarydialogVisible_Div_row_bottom_Label_a">库名称</a>
                </el-col>
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_input">
                  <el-input v-model="AddFaceLibrarydialogVisible_Div_row_bottom_input_Name"
                            class="AddFaceLibrarydialogVisible_Div_row_bottom_input_name" />
                </el-col>
              </el-row>
              <el-row id="AddFaceLibrarydialogVisible_Div_row_bottom_1">
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_Label">
                  <a id="AddFaceLibrarydialogVisible_Div_row_bottom_Label_a">用户标签</a>
                </el-col>
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_input_3">
                  <el-input v-model="AddFaceLibrarydialogVisible_Div_row_bottom_input_peopelLabel"
                            class="AddFaceLibrarydialogVisible_Div_row_bottom_input_name" />
                </el-col>
              </el-row>
              <el-row id="AddFaceLibrarydialogVisible_Div_row_bottom_2">
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_Label">
                  <a id="AddFaceLibrarydialogVisible_Div_row_bottom_Label_a">描述</a>
                </el-col>
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_input_2">
                  <el-input v-model="AddFaceLibrarydialogVisible_Div_row_bottom_input_describe"
                            class="AddFaceLibrarydialogVisible_Div_row_bottom_input_name" />
                </el-col>
              </el-row>
              <el-button id="AddFaceLibrarydialogVisible_Div_row_bottom_button"
                         @click="AddLibraryList"
                         type="primary">保存</el-button>
              <el-button id="AddFaceLibrarydialogVisible_Div_row_bottom_button"
                         @click="CloseAddLibraryListDialog"
                         type="primary">取消</el-button>
            </el-row>
          </div>
        </el-dialog>

        <!-- 修改名单库 -->
        <el-dialog title="修改名单库"
                   :visible.sync="UpdateFaceLibrarydialogVisible"
                   :before-close="handleClose">
          <div id="AddFaceLibrarydialogVisible_Div">
            <el-row id="AddFaceLibrarydialogVisible_Div_row_top">
              <a id="AddFaceLibrarydialogVisible_Div_row_top_a">修改人脸库</a>
            </el-row>
            <el-row id="AddFaceLibrarydialogVisible_Div_row_bottom">
              <el-row id="AddFaceLibrarydialogVisible_Div_row_bottom_1">
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_Label">
                  <a id="AddFaceLibrarydialogVisible_Div_row_bottom_Label_a">库名称</a>
                </el-col>
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_input">
                  <el-input v-model="AddFaceLibrarydialogVisible_Div_row_bottom_input_Name_upload"
                            class="AddFaceLibrarydialogVisible_Div_row_bottom_input_name" />
                </el-col>
              </el-row>
              <el-row id="AddFaceLibrarydialogVisible_Div_row_bottom_1">
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_Label">
                  <a id="AddFaceLibrarydialogVisible_Div_row_bottom_Label_a">库类型</a>
                </el-col>
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_input_3">
                  <el-select disabled
                             v-model="FaceLibraryTypeVule_upload"
                             placeholder="请选择">
                    <el-option v-for="item in FaceLibraryType_upload"
                               :key="item.value"
                               :label="item.label"
                               :value="item.value">
                    </el-option>
                  </el-select>
                </el-col>
              </el-row>
              <el-row id="AddFaceLibrarydialogVisible_Div_row_bottom_2">
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_Label">
                  <a id="AddFaceLibrarydialogVisible_Div_row_bottom_Label_a">描述</a>
                </el-col>
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_input_2">
                  <el-input v-model="AddFaceLibrarydialogVisible_Div_row_bottom_input_describe_upload"
                            class="AddFaceLibrarydialogVisible_Div_row_bottom_input_name" />
                </el-col>
              </el-row>
              <el-button id="AddFaceLibrarydialogVisible_Div_row_bottom_button"
                         @click="AddLibraryList_upload"
                         type="primary">保存</el-button>
              <el-button id="AddFaceLibrarydialogVisible_Div_row_bottom_button"
                         @click="CloseUpdateLibraryListDialog"
                         type="primary">取消</el-button>
            </el-row>
          </div>
        </el-dialog>
        <!-- 修改静态名单库 -->
        <el-dialog title="修改名单库"
                   :visible.sync="UpdataStaticFaceLibrarydialogVisible"
                   :before-close="handleClose">
          <div id="AddFaceLibrarydialogVisible_Div">
            <el-row id="AddFaceLibrarydialogVisible_Div_row_top">
              <a id="AddFaceLibrarydialogVisible_Div_row_top_a">修改人脸库</a>
            </el-row>
            <el-row id="AddFaceLibrarydialogVisible_Div_row_bottom">
              <el-row id="AddFaceLibrarydialogVisible_Div_row_bottom_1">
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_Label">
                  <a id="AddFaceLibrarydialogVisible_Div_row_bottom_Label_a">库名称</a>
                </el-col>
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_input">
                  <el-input v-model="AddFaceLibrarydialogVisible_Div_row_bottom_input_Name_upload_static"
                            class="AddFaceLibrarydialogVisible_Div_row_bottom_input_name" />
                </el-col>
              </el-row>
              <el-row id="AddFaceLibrarydialogVisible_Div_row_bottom_1">
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_Label">
                  <a id="AddFaceLibrarydialogVisible_Div_row_bottom_Label_a">用户标签</a>
                </el-col>
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_input_3">
                  <el-input v-model="AddFaceLibrarydialogVisible_Div_row_bottom_input_peopelLabel_upload"
                            class="AddFaceLibrarydialogVisible_Div_row_bottom_input_name" />
                </el-col>
              </el-row>
              <el-row id="AddFaceLibrarydialogVisible_Div_row_bottom_2">
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_Label">
                  <a id="AddFaceLibrarydialogVisible_Div_row_bottom_Label_a">描述</a>
                </el-col>
                <el-col id="AddFaceLibrarydialogVisible_Div_row_bottom_input_2">
                  <el-input v-model="AddFaceLibrarydialogVisible_Div_row_bottom_input_describe_upload_static"
                            class="AddFaceLibrarydialogVisible_Div_row_bottom_input_name" />
                </el-col>
              </el-row>
              <el-button id="AddFaceLibrarydialogVisible_Div_row_bottom_button"
                         @click="AddLibraryList_upload"
                         type="primary">保存</el-button>
              <el-button id="AddFaceLibrarydialogVisible_Div_row_bottom_button"
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
              <a id="BatchListImportDialogVisible_Div_one_a">{{CurrentFaceLibraryName}}</a>
              <el-button id="BatchListImportDialogVisible_Div_one_button"
                         @click="importFaceList">导入</el-button>
            </el-row>
            <el-row id="BatchListImportDialogVisible_Div_tow">
              <div id="BatchListImportDialogVisible_Div_tow_div">
                <el-tabs v-model="activeNameS"
                         class="BatchListImportDialogVisible_Div_tow_div_tabs">
                  <el-tab-pane label="黑名单"
                               name="first">
                    <FaceLibraryBlackDatabaseTree ref="FaceLibraryBlackDBTree"
                                                  class="ChildFaceLibraryManage_left_Tabel_cameraTree" />
                  </el-tab-pane>
                  <el-tab-pane label="白名单"
                               name="second">
                    <FaceLibraryWhiteDatabaseTree ref="FaceLibraryWhiteDBTree"
                                                  class="ChildFaceLibraryManage_left_Tabel_cameraTree" />
                  </el-tab-pane>
                  <!-- <el-tab-pane label="红名单"
                               name="three">
                    <FaceLibraryRedDatabaseTree ref="FaceLibraryRedDBTree"
                                                class="ChildFaceLibraryManage_left_Tabel_cameraTree" />
                  </el-tab-pane>
                  <el-tab-pane label="静态库"
                               name="four">
                    <FaceLibrarySStaticDatabaseTree ref="FaceLibraryStaticSDBTree"
                                                    class="RealTimeVideoPlayView_tabs_cameraTree" />
                  </el-tab-pane> -->
                </el-tabs>
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
import FaceBlackDatabaseTree from './FaceBlackDatabaseTree'
import FaceWhiteDatabaseTree from './FaceWhiteDatabaseTree'
// import FaceRedDatabaseTree from '@/components/FaceRedDatabaseTree/FaceRedDatabaseTree'
// import FaceLibraryStaticDatabaseTree from '@/components/FaceLibraryStaticDatabaseTree/FaceLibraryStaticDatabaseTree'
// import FaceLibrarySStaticDatabaseTree from '@/components/FaceLibrarySStaticDatabaseTree/FaceLibrarySStaticDatabaseTree'
import FaceLibraryBlackDatabaseTree from './FaceLibraryBlackDatabaseTree'
import FaceLibraryWhiteDatabaseTree from './FaceLibraryWhiteDatabaseTree'
// import FaceLibraryRedDatabaseTree from '@/components/FaceLibraryRedDatabaseTree/FaceLibraryRedDatabaseTree'
import { optionsSex, detailsDocumentType, FaceLibraryType } from '@/common/enum'
import configInfo from '@/webconfig/configInfo'

import {
  IntelligentAnalysis_uplodeImage, GetImageBase64, GetFacesLibaryPicResultByFileID, GetStaticLibaryPicResultByFileID, GetFaceRepositoriesList,
  searchDictionaryList, AddFaceRepositories, AddPeopleList, UpdateFaceRepositories, DeleteFaceRepositories, GetFaceStaticRepositoriesList,
  UpdateStaticPeopleList, UpdatePeopleList
} from '@/api/pvMonitor/IntelligentAnalysis'
export default {
  components: { FaceBlackDatabaseTree, FaceWhiteDatabaseTree, FaceLibraryBlackDatabaseTree, FaceLibraryWhiteDatabaseTree },
  data () {
    return {
      that: this,
      ChildFaceLibraryManage_left_InputValue: '',
      activeName: 'first', // tab默认页
      activeNameS: 'first', // tab默认页
      PersonCarId: '',
      params: {
        pageIndex: 1,
        pageSize: 20,
        total: 0
      },
      ImageFaceDateBit64: '',
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
      detailsSex: '', // 详情中的性别
      detailsTime: '', // 详情中的出生日期
      detailsDocumentCode: '', // 详情中的证件编码
      dialogAlarmInfoUpdate: false, // 修改
      AddFaceLibrarydialogVisible: false, // 新增名单库
      AddStaticFaceLibrarydialogVisible: false, // 新增静态名单库
      UpdataStaticFaceLibrarydialogVisible: false, // 修改静态名单库
      UpdateFaceLibrarydialogVisible: false, // 修改名单库
      dialogAlarmInfoAdd: false, // 新增名单
      BatchListImportDialogVisible: false, // 批量人员导入框
      divdialogVisibleTwo: false,
      divDialogVisibleThree: false,
      optionsSex: optionsSex,
      detailsDocumentType: detailsDocumentType,
      PersonCarType: detailsDocumentType,
      PersonCarTypeVule: '', // 检索框中类型
      fileList: [],
      BigImgSrc: '', // 大图图片路径
      dialogImageUrl: '',
      AddFaceLibrarydialogVisible_Div_row_bottom_input_Name: '', // 新增名单库中的名称
      AddFaceLibrarydialogVisible_Div_row_bottom_input_Name_upload: '', // 修改名单库中的名称
      AddFaceLibrarydialogVisible_Div_row_bottom_input_Name_upload_static: '', // 修改静态名单库的名称
      AddFaceLibrarydialogVisible_Div_row_bottom_input_describe: '', // 新增名单库中的备注
      AddFaceLibrarydialogVisible_Div_row_bottom_input_describe_upload: '', // 修改名单库中的备注
      AddFaceLibrarydialogVisible_Div_row_bottom_input_peopelLabel: '', // 新增静态名单库中的用户标签
      AddFaceLibrarydialogVisible_Div_row_bottom_input_peopelLabel_upload: '', // 新增静态名单库中的用户标签
      AddFaceLibrarydialogVisible_Div_row_bottom_input_describe_upload_static: '', // 新增静态名单库中的备注
      FaceLibraryTypeVule: '', // 人脸库类型
      FaceLibraryTypeVule_upload: '', // 人脸库类型(修改中)
      FaceLibraryType: FaceLibraryType,
      FaceLibraryType_upload: FaceLibraryType,
      CurrentFaceLibraryName: '', // 当前库名称
      CurrentFaceLibraryId: '', // 当前库Id
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
      endDatePicker: this.chooseDate()
    }
  },
  filters: {
    ellipsis (value) {
      if (!value) return ''
      if (value.length > 10) {
        return value.slice(0, 10) + '...'
      }
      return value
    },
    CurrentLibraryEllipsis (value) {
      if (!value) return ''
      if (value.length > 20) {
        return value.slice(0, 20) + '...'
      }
      return value
    },
    getDetailsDocumentType: function (value, self) {
      if (value != null) { return self.commonFunction.getDetailsDocumentType(value) }
    },
    getSex: function (value, self) {
      if (value != null) { return self.commonFunction.getSex(value) }
    },
    getGender (value, self) {
      let genderName = ''
      self.genderItem.forEach(val => {
        if (val.value === value) { genderName = val.name }
      })
      return genderName
    }
  },
  methods: {
    messageTips (messageVule) {
      this.$message({
        message: messageVule,
        type: 'warning',
        center: true
      })
    },
    getGender () {
      let entityData = {
        pageIndex: 1,
        pageSize: 10,
        entity: {
          parentID: 200
        }

      }
      let self = this

      searchDictionaryList(entityData).then(data => {
        self.genderItem = data.data
      })
    },
    chooseDate () {
      return {
        disabledDate (time) {
          return time.getTime() > Date.now() // 选择时间不能大于当前时间
        }
      }
    },
    handleClick (faceData) {
      const self = this
      self.myInfo = []
      // this.params.total = 0
      // this.params.pageIndex = 1
      // this.params.pageSize = 20
      if (faceData) {
        if (faceData.id) {
          this.diageFaceData = faceData
          if (this.activeName === 'four') {
            let orgCode = {
              libraryIds: faceData.id,
              page: {
                no: this.params.pageIndex,
                size: this.params.pageSize,
                sort: 'asc',
                orderName: 'time'
              }
            }
            this.CurrentFaceLibraryName = faceData.name
            this.CurrentFaceLibraryId = faceData.id

            GetStaticLibaryPicResultByFileID(orgCode).then((filedata) => { // 查询静态人脸库
              if (filedata) {
                if (filedata.data) {
                  /* let fileIdss = filedata */
                  self.myInfo = filedata.data
                  this.params.total = filedata.page.total
                  // this.params.pageIndex = filedata.page.currentPage
                  // this.params.pageSize = filedata.page.rows
                  if (filedata.data.length !== 0) {
                    // let orgCode = ''
                    for (let i = 0; i < filedata.data.length; i++) {
                      /* this.getImageByFileId(i, filedata[i].pictures[0].fileId) */

                      for (let j = 0; j < filedata.data[i].pictures.length; j++) {
                        // for (let j = 0; j < filedata.data[i].faceList.length; j++) {
                        // if (commonConst.isUseImageProxy) {
                        //   filedata.data[i].faceList[j].url = commonConst.vcmImageUrl + encodeURIComponent(filedata.data[i].faceList[j].url)
                        // } else {
                        //   filedata.data[i].faceList[j].url = encodeURIComponent(filedata.data[i].faceList[j].url)
                        // }

                        // if (commonConst.isUseImageProxy) {
                        filedata.data[i].pictures[j].url = configInfo.vcmImageUrl + encodeURIComponent(filedata.data[i].pictures[j].url)
                        // } else {
                        // filedata.data[i].pictures[j].url = encodeURIComponent(filedata.data[i].pictures[j].url)
                        // }
                      }
                    }
                  }
                } else {
                  this.messageTips('无符合条件数据')
                  this.params.total = 0
                  // this.commonFunction.dialogSuccess('无符合条件数据')
                }
              }
            })
          } else {
            let orgCode = {
              repositorIds: faceData.id,
              page: {
                no: this.params.pageIndex,
                size: this.params.pageSize,
                sort: 'asc',
                orderName: 'time'
              }
            }
            this.CurrentFaceLibraryName = faceData.name
            this.CurrentFaceLibraryId = faceData.id

            GetFacesLibaryPicResultByFileID(orgCode).then((filedata) => { // 查询人脸库
              if (filedata) {
                if (filedata.data) {
                  /* let fileIdss = filedata */
                  self.myInfo = filedata.data
                  this.params.total = filedata.page.total
                  // this.params.pageIndex = filedata.page.currentPage
                  // this.params.pageSize = filedata.page.rows
                  if (filedata.data.length !== 0) {
                    // let orgCode = ''
                    for (let i = 0; i < filedata.data.length; i++) {
                      /* this.getImageByFileId(i, filedata[i].faceList[0].fileId) */
                      for (let j = 0; j < filedata.data[i].faceList.length; j++) {
                        // if (commonConst.isUseImageProxy) {
                        filedata.data[i].faceList[j].url = configInfo.vcmImageUrl + encodeURIComponent(filedata.data[i].faceList[j].url)
                        // } else {
                        // filedata.data[i].faceList[j].url = encodeURIComponent(filedata.data[i].faceList[j].url)
                        // }
                      }
                    }
                  }
                } else {
                  this.messageTips('无符合条件数据')
                  this.params.total = 0
                  // this.commonFunction.dialogSuccess('无符合条件数据')
                }
              }
            })
          }
        }
      }
    },
    retrieval () { // 检索功能
      const self = this
      self.myInfo = []
      // this.params.total = 0
      // this.params.pageIndex = 1
      // this.params.pageSize = 20
      if (this.CurrentFaceLibraryId) {
        if (this.activeName === 'four') {
          let orgCode = {
            libraryIds: this.CurrentFaceLibraryId,
            credentialType: this.PersonCarTypeVule, // 证件类型
            credentialNumber: this.PersonCarId, // 证件号码
            name: this.PersonCarName, // 姓名
            page: {
              no: this.params.pageIndex,
              size: this.params.pageSize,
              sort: 'asc',
              orderName: 'time'
            }
          }

          GetStaticLibaryPicResultByFileID(orgCode).then((filedata) => { // 查询人脸库
            if (filedata) {
              if (filedata.data) {
                /* let fileIdss = filedata */
                self.myInfo = filedata.data
                this.params.total = filedata.page.total
                // this.params.pageIndex = filedata.page.currentPage
                // this.params.pageSize = filedata.page.rows
                if (filedata.data.length !== 0) {
                  // let orgCode = ''
                  for (let i = 0; i < filedata.data.length; i++) {
                    /* this.getImageByFileId(i, filedata[i].pictures[0].fileId) */

                    for (let j = 0; j < filedata.data[i].pictures.length; j++) {
                      // for (let j = 0; j < filedata.data[i].faceList.length; j++) {
                      // if (commonConst.isUseImageProxy) {
                      //   filedata.data[i].faceList[j].url = commonConst.vcmImageUrl + encodeURIComponent(filedata.data[i].faceList[j].url)
                      // } else {
                      //   filedata.data[i].faceList[j].url = encodeURIComponent(filedata.data[i].faceList[j].url)
                      // }

                      // if (commonConst.isUseImageProxy) {
                      filedata.data[i].pictures[j].url = configInfo.vcmImageUrl + encodeURIComponent(filedata.data[i].pictures[j].url)
                      // } else {
                      // filedata.data[i].pictures[j].url = encodeURIComponent(filedata.data[i].pictures[j].url)
                      // }
                    }
                  }
                }
              } else {
                this.messageTips('无符合条件数据')
                this.params.total = 0
                // this.commonFunction.dialogSuccess('无符合条件数据')
              }
            }
          })
        } else {
          let orgCode = {
            repositorIds: this.CurrentFaceLibraryId,
            credentialType: this.PersonCarTypeVule, // 证件类型
            credentialNumber: this.PersonCarId, // 证件号码
            name: this.PersonCarName, // 姓名
            page: {
              no: this.params.pageIndex,
              size: this.params.pageSize,
              sort: 'asc',
              orderName: 'time'
            }
          }

          GetFacesLibaryPicResultByFileID(orgCode).then((filedata) => { // 查询人脸库
            if (filedata) {
              if (filedata.data) {
                /* let fileIdss = filedata */
                self.myInfo = filedata.data
                this.params.total = filedata.page.total
                // this.params.pageIndex = filedata.page.currentPage
                // this.params.pageSize = filedata.page.rows
                if (filedata.data.length !== 0) {
                  // let orgCode = ''
                  for (let i = 0; i < filedata.data.length; i++) {
                    /* this.getImageByFileId(i, filedata[i].faceList[0].fileId) */
                    for (let j = 0; j < filedata.data[i].faceList.length; j++) {
                      // if (commonConst.isUseImageProxy) {
                      filedata.data[i].faceList[j].url = configInfo.vcmImageUrl + encodeURIComponent(filedata.data[i].faceList[j].url)
                      // } else {
                      // filedata.data[i].faceList[j].url = encodeURIComponent(filedata.data[i].faceList[j].url)
                      // }
                    }
                  }
                }
              } else {
                this.messageTips('无符合条件数据')
                this.params.total = 0
                // this.commonFunction.dialogSuccess('无符合条件数据')
              }
            }
          })
        }
      } else {
        this.commonFunction.dialogSuccess('请先选择人脸名单组')
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
        // console.log(reader.result)
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
          // console.log(reader.result)
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
          // console.log(reader.result)
          this.localFileList.push(this.localFile)
          this.oldImageFill.push(reader.result)
          this.NewImageFill.push(this.localFile)
        }
      }
    },
    getImageByFileId (index, fileId) {
      const self = this
      GetImageBase64(fileId).then(async dataThree => { // 根据fileid获取图片bit64

        let imageBasa64 = 'data:image/png;base64,' + dataThree
        self.myInfo[index].creatTime = imageBasa64
      })
    },
    async getImageByFileIdS (fileId) {
      // const self = this
      GetImageBase64(fileId).then(response => {

        if (response) {
          if (response.data) {
            let imgBaseD = response.data
            this.oldImageFill.push('data:image/png;base64,' + imgBaseD)
          }
        }
      })
    },

    showAlarmDetail (InfoDate) { // xiangqing
      /* const self = this */

      this.oldImageFill = []
      this.oldImageFillId = []
      this.NewImageFillId = []
      this.NewImageFill = []
      if (this.activeName === 'four') {
        let faceListData = InfoDate.pictures
        if (faceListData.length !== 0) {
          for (let j = 0; j < faceListData.length; j++) {
            this.getImageByFileIdS(faceListData[j].fileId)
          }
        }
      } else {
        let faceListData = InfoDate.faceList
        if (faceListData.length !== 0) {
          for (let j = 0; j < faceListData.length; j++) {
            this.getImageByFileIdS(faceListData[j].fileId)
          }
        }
      }

      this.dialogAlarmInfoVisible = true
      this.dialogAlarmInfoUpdate = false
      this.divdialogVisible = true
      this.ControlFaceInfoData = InfoDate

      this.detailsName = InfoDate.name // 详情中的姓名
      this.detailsDocumentTypeVule = InfoDate.credentialType// 详情中的证件类型
      this.detailsSex = InfoDate.gender // 详情中的性别
      this.detailsTime = InfoDate.bornTime// 出生日期
      this.detailsDocumentCode = InfoDate.credentialNumber // 证件编码

      this.detailsNation = InfoDate.nationality
      this.detailsDescribe = InfoDate.description
      this.detailsProfession = InfoDate.occupation
      this.detailsCountry = InfoDate.country
    },
    updateAlarmDetail (InfoDate) { // 修改弹框
      // const self = this
      this.oldImageFill = []
      this.oldImageFillId = []
      this.NewImageFill = []
      this.NewImageFillId = []
      if (this.activeName === 'four') {
        let faceListData = InfoDate.pictures
        if (faceListData.length !== 0) {
          for (let j = 0; j < faceListData.length; j++) {
            this.oldImageFillId.push(faceListData[j].fileId)
            this.getImageByFileIdS(faceListData[j].fileId)
          }
        }
      } else {
        let faceListData = InfoDate.faceList
        if (faceListData.length !== 0) {
          for (let j = 0; j < faceListData.length; j++) {
            this.oldImageFillId.push(faceListData[j].fileId)
            this.getImageByFileIdS(faceListData[j].fileId)
          }
        }
      }

      this.dialogAlarmInfoUpdate = true
      this.dialogAlarmInfoVisible = false
      this.divdialogVisible = true
      this.ControlFaceInfoData = InfoDate

      this.detailsName = InfoDate.name // 详情中的姓名
      this.detailsDocumentTypeVule = InfoDate.credentialType// 详情中的证件类型
      this.detailsSex = InfoDate.gender // 详情中的性别
      this.detailsTime = InfoDate.bornTime// 出生日期
      this.detailsDocumentCode = InfoDate.credentialNumber // 证件编码
      this.UpdataInfoData = InfoDate

      this.detailsNation = InfoDate.nationality
      this.detailsDescribe = InfoDate.description
      this.detailsProfession = InfoDate.occupation
      this.detailsCountry = InfoDate.country
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
            let form = new FormData()
            form.append('files', files[i])
            form.append('source_system_id', '0')
            form.append('type', '1')
            let fileIdss = await IntelligentAnalysis_uplodeImage(form)
            if (fileIdss) {
              if (fileIdss.data) {
                let sss = fileIdss.data.data[0].casefile.casefileid
                this.NewImageFillId.push({ fileId: sss })
              }
            }
          }
          this.UpdatePeopleListData()
        }
      }
    },
    UpdatePeopleListData () { // 修改人员方法

      if (this.activeName === 'four') {
        let orgData = {
          people: {
            id: this.UpdataInfoData.id,
            name: this.detailsName,
            gender: this.detailsSex,
            nationality: this.detailsNation,
            description: this.detailsDescribe,
            bornTime: this.detailsTime,
            occupation: this.detailsProfession,
            tag: null,
            country: this.detailsCountry,
            credentialType: this.detailsDocumentTypeVule,
            credentialNumber: this.detailsDocumentCode,
            library_id: this.UpdataInfoData.libraryId,
            repositorName: null,
            pictures: null,
            addPictures: {
              picture: this.NewImageFillId
            },
            fileIds: {
              fileId: this.oldImageFillId
            }
          }
        }
        UpdateStaticPeopleList(orgData).then((filedata) => { // 修改人脸
          if (filedata) {
            let fileIdss = filedata
            if (fileIdss.status === 200 && fileIdss.message === '请求成功') {
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
              this.detailsDocumentTypeVule = null
              this.detailsDocumentCode = null
              this.dialogAlarmInfoUpdate = false
              this.divdialogVisible = false
            }
          }
        })
      } else {
        let orgData = {
          people: {
            id: this.UpdataInfoData.peopleId,
            similarity: null,
            name: this.detailsName,
            peopleId: this.UpdataInfoData.peopleId,
            gender: this.detailsSex,
            nationality: this.detailsNation,
            description: this.detailsDescribe,
            createTime: null,
            type: null,
            bornTime: this.detailsTime,
            occupation: this.detailsProfession,
            tag: null,
            country: this.detailsCountry,
            credentialType: this.detailsDocumentTypeVule,
            credentialNumber: this.detailsDocumentCode,
            repositorId: this.UpdataInfoData.repositorId,
            repositorName: null,
            pictures: null,
            addPictures: {
              picture: this.NewImageFillId
            },
            fileIds: {
              fileId: this.oldImageFillId
            },
            repositoriesType: 1
          }
        }
        UpdatePeopleList(orgData).then((filedata) => { // 修改人脸
          if (filedata) {
            let fileIdss = filedata
            if (fileIdss.status === 200 && fileIdss.data.message === '请求成功') {
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
          }
        })
      }
    },
    closeAddClueDialogVisible () {
      this.dialogAddClueVisible = false
      this.dialogAlarmInfoUpdate = false
      this.dialogAlarmInfoVisible = false
      this.divdialogVisible = false
    },
    async showBigImg (BigImgInfoData) { // 查看大图
      this.fullImagedialogVisible = true
      this.divdialogVisible = true
      // this.BigImgSrc = BigImgInfoData
      this.BigImgSrc = await this.commonFunction.GetImageBase64_AddWaterMarker(BigImgInfoData)
    },
    reset () { // 重置
      // this.CurrentFaceLibraryId = ''
      this.PersonCarTypeVule = '' // 证件类型
      this.PersonCarId = '' // 证件号码
      this.PersonCarName = ''
    },
    showAddAlarmDetail () { // 打开新增人脸弹框
      let nowFaceLibraryId = this.CurrentFaceLibraryId
      if (nowFaceLibraryId === 'null' || nowFaceLibraryId === 'undefined') {
        this.commonFunction.dialogSuccess('先选择人脸库')
      } else {
        if (nowFaceLibraryId.length === 0) { this.commonFunction.dialogSuccess('先选择人脸库') } else {
          this.dialogAlarmInfoAdd = true
          this.divdialogVisible = true
          this.dialogImageUrl = ''
        }
      }
    },
    showAddFaceLibrarydialogVisible () {
      this.divdialogVisibleTwo = true
      if (this.activeName === 'four') {
        this.AddStaticFaceLibrarydialogVisible = true
      } else {
        this.AddFaceLibrarydialogVisible = true
      }
    },
    CloseAddLibraryListDialog () {
      this.divdialogVisibleTwo = false
      this.AddFaceLibrarydialogVisible = false
      this.AddStaticFaceLibrarydialogVisible = false
    },
    AddLibraryList () {
      // 新增人脸库接口
      const self = this
      if (this.activeName === 'four') {
        let orgCode = {
          staticLibrary: {
            tag: this.AddFaceLibrarydialogVisible_Div_row_bottom_input_peopelLabel,
            name: this.AddFaceLibrarydialogVisible_Div_row_bottom_input_Name,
            description: this.AddFaceLibrarydialogVisible_Div_row_bottom_input_describe
          }
        }
        AddFaceStaticRepositories(orgCode).then((filedata) => { // xinzengjingtai人脸库
          if (filedata) {

            let fileIdss = filedata
            if (fileIdss.length !== 0) {
              self.commonFunction.dialogSuccess('添加成功')
              self.AddFaceLibrarydialogVisible = false
              self.divdialogVisibleTwo = false
              // self.$refs.FaceStaticDBTree.getOrgList()
              self.AddStaticFaceLibrarydialogVisible = false
            }
          }
        })
      } else {
        let orgCode = {
          repository: {
            type: this.FaceLibraryTypeVule,
            name: this.AddFaceLibrarydialogVisible_Div_row_bottom_input_Name,
            description: this.AddFaceLibrarydialogVisible_Div_row_bottom_input_describe
          }
        }
        AddFaceRepositories(orgCode).then((filedata) => { // xinzeng人脸库

          if (filedata.data) {
            let fileIdss = filedata.data
            if (fileIdss.length !== 0) {
              self.commonFunction.dialogSuccess('添加成功')
              self.AddFaceLibrarydialogVisible = false
              self.divdialogVisibleTwo = false
              self.$refs.FaceBlackDBTree.getOrgList()
              self.$refs.FaceWhiteDBTree.getOrgList()
              // self.$refs.FaceRedDBTree.getOrgList()
              self.AddStaticFaceLibrarydialogVisible = false
            }
          }
        })
      }
    },
    DelectLibrayList () { // 删除名单库
      this.$confirm('是否确认删除该数据项?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(res => {
        if (res === 'confirm') {
          let self = this
          if (this.activeName === 'four') {
            let id = this.CurrentFaceLibraryId
            DeleteStaticRepositoriesById(id).then((filedata) => { // 删除人脸库

              if (filedata.data) {
                let fileIdss = filedata.data
                if (fileIdss.status === '200') {
                  self.commonFunction.dialogSuccess('删除成功')
                  // self.$refs.FaceStaticDBTree.getOrgList()
                } else {
                  self.commonFunction.dialogSuccess('删除失败')
                  // self.$refs.FaceStaticDBTree.getOrgList()
                }
              }
            })
          } else {
            let enid = {
              id: this.CurrentFaceLibraryId
            }

            DeleteFaceRepositories(enid).then((filedata) => { // 删除人脸库

              if (filedata.data) {
                let fileIdss = filedata.data
                if (fileIdss.status === '200') {
                  self.commonFunction.dialogSuccess('删除成功')
                  self.$refs.FaceBlackDBTree.getOrgList()
                  self.$refs.FaceWhiteDBTree.getOrgList()
                  // self.$refs.FaceRedDBTree.getOrgList()
                } else {
                  self.commonFunction.dialogSuccess('删除失败')
                  self.$refs.FaceBlackDBTree.getOrgList()
                  self.$refs.FaceWhiteDBTree.getOrgList()
                  // self.$refs.FaceRedDBTree.getOrgList()
                }
              }
            })
          }
        }
      })
    },
    handleClose (done) {
      done()
    },
    delectAlarmDetail (InfoDate) { // 删除人脸
      this.$confirm('是否确认删除该数据项?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(res => {
        if (res === 'confirm') {
          let self = this
          if (this.activeName === 'four') {
            let orgData = {
              library_id: InfoDate.libraryId,
              ids: InfoDate.id
            }
            deleteStaticPeopleList(orgData).then((returnData) => { // 删除人脸
              if (returnData.data) {

                let returnDataS = returnData
                if (returnDataS.status === '200') {
                  self.commonFunction.dialogSuccess('删除成功')
                  self.handleClick(self.diageFaceData)
                } else {
                  self.commonFunction.dialogSuccess('删除失败')
                  self.handleClick(self.diageFaceData)
                }
              }
            })
          } else {
            let orgData = {
              credentialNumber: InfoDate.credentialNumber,
              repositorId: InfoDate.repositorId
            }
            DeletePeopleList(orgData).then((returnData) => { // 删除人脸
              if (returnData) {
                let returnDataS = returnData
                if (returnDataS.status === '200') {
                  self.commonFunction.dialogSuccess('删除成功')
                  self.handleClick(self.diageFaceData)
                } else {
                  self.commonFunction.dialogSuccess('删除失败')
                  self.handleClick(self.diageFaceData)
                }
              }
            })
          }
        }
      })
    },
    delectPaceImage (imgDataList) {
      // 删除元素
      this.ImageInfoList.splice(imgDataList, 1)
    },
    delectPaceImage_update (imgDataList) {
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
    async AddPaceList () { // 增加人脸库名单

      this.imgFillIdList = []

      let fileList = this.localFileList
      if (fileList === 'null' || fileList === 'undefined' || this.detailsNameS === 'null' || this.detailsNameS === 'undefined' || this.detailsSexS === 'null' || this.detailsSexS === 'undefined' || this.detailsDocumentTypeVuleS === 'null' || this.detailsDocumentTypeVuleS === 'undefined' || this.detailsDocumentCodeS === 'null' || this.detailsDocumentCodeS === 'undefined') {
        this.commonFunction.dialogSuccess('图片,姓名，性别，证件类型，证件编号不能为空')
      } else {
        if (fileList.length === 0 || this.detailsNameS.length === 0 || this.detailsSexS.length === 0 || this.detailsDocumentTypeVuleS.length === 0 || this.detailsDocumentCodeS.length === 0) {
          this.commonFunction.dialogSuccess('图片,姓名，性别，证件类型，证件编号不能为空')
        } else {
          if (this.activeName === 'four') {
            /* const self = this */
            for (let i = 0; i < fileList.length; i++) {
              var form = new FormData()
              form.append('files', fileList[i])
              form.append('source_system_id', '0')
              form.append('type', '1')
              let response = await IntelligentAnalysis_uplodeImage(form)
              let fileIdss = response.data.data
              this.imgFillIdList.push(fileIdss[0].casefile.casefileid)
            }
            let imgFillIdData = this.imgFillIdList
            for (let i = 0; i < imgFillIdData.length; i++) {
              this.imageFill.push({ fileId: imgFillIdData[i] })
            }
            let orgCode = {
              sync: 'true',
              callbackURL: 'null',
              peoples: [{
                people: [{
                  id: '',
                  name: this.detailsNameS,
                  gender: this.detailsSexS,
                  nationality: this.detailsNationS,
                  description: this.detailsDescribeS,
                  bornTime: this.detailsTimeS,
                  occupation: this.detailsProfessionS,
                  tag: null,
                  country: this.detailsCountryS,
                  credentialType: this.detailsDocumentTypeVuleS,
                  credentialNumber: this.detailsDocumentCodeS,
                  libraryid: this.CurrentFaceLibraryId,
                  pictures: {
                    picture: this.imageFill
                  }
                }]
              }]

            }
            AddStaticPeopleList(orgCode).then((filedata) => { // 新增静态人脸库

              let data = filedata
              if (data.status === '200' || data.message === '请求成功') {
                this.commonFunction.dialogSuccess('添加成功')
                this.handleClick(this.diageFaceData)
                this.detailsNameS = null
                this.detailsSexS = null
                this.detailsNationS = null
                this.detailsDescribeS = null
                this.detailsTimeS = null
                this.detailsProfessionS = null
                this.detailsCountryS = null
                this.detailsDocumentTypeVuleS = null
                this.detailsDocumentCodeS = null
                this.imageFill = []
                this.ImageInfoList = []
                this.dialogAlarmInfoAdd = false
                this.divdialogVisible = false
                this.NewImageFillId = []
                this.NewImageFill = []
              } else {
                this.commonFunction.dialogSuccess('添加失败')
                this.handleClick(this.diageFaceData)
                this.detailsNameS = null
                this.detailsSexS = null
                this.detailsNationS = null
                this.detailsDescribeS = null
                this.detailsTimeS = null
                this.detailsProfessionS = null
                this.detailsCountryS = null
                this.detailsDocumentTypeVuleS = null
                this.detailsDocumentCodeS = null
                this.imageFill = []
                this.ImageInfoList = []
                this.dialogAlarmInfoAdd = false
                this.divdialogVisible = false
                this.NewImageFillId = []
                this.NewImageFill = []
              }
            })
          } else {
            /* const self = this */
            for (let j = 0; j < fileList.length; j++) {
              var formS = new FormData()
              formS.append('files', fileList[j])
              formS.append('source_system_id', '0')
              formS.append('type', '1')
              IntelligentAnalysis_uplodeImage(formS).then(response => {
                let fileIdss = response.data
                this.imgFillIdList.push(fileIdss[0].casefile.casefileid)
                let imgFillIdData = this.imgFillIdList
                for (let i = 0; i < imgFillIdData.length; i++) {
                  this.imageFill.push({ fileId: imgFillIdData[i] })
                }
                let orgCode = {
                  sync: 'true',
                  callbackURL: 'null',
                  peopleList: [{
                    people: [{
                      id: '',
                      name: this.detailsNameS,
                      gender: this.detailsSexS,
                      nationality: this.detailsNationS,
                      description: this.detailsDescribeS,
                      bornTime: this.detailsTimeS,
                      occupation: this.detailsProfessionS,
                      tag: null,
                      country: this.detailsCountryS,
                      credentialType: this.detailsDocumentTypeVuleS,
                      credentialNumber: this.detailsDocumentCodeS,
                      repositorId: this.CurrentFaceLibraryId,
                      pictures: {
                        picture: this.imageFill
                      }
                    }]
                  }]
                }
                AddPeopleList(orgCode).then((filedata) => { // 新增人脸库

                  let data = filedata
                  if (data.status === '200' || data.message === '请求成功') {
                    this.commonFunction.dialogSuccess('添加成功')
                    this.handleClick(this.diageFaceData)
                    this.detailsNameS = null
                    this.detailsSexS = null
                    this.detailsNationS = null
                    this.detailsDescribeS = null
                    this.detailsTimeS = null
                    this.detailsProfessionS = null
                    this.detailsCountryS = null
                    this.detailsDocumentTypeVuleS = null
                    this.detailsDocumentCodeS = null
                    this.imageFill = []
                    this.ImageInfoList = []
                    this.dialogAlarmInfoAdd = false
                    this.divdialogVisible = false

                    this.NewImageFillId = []
                    this.NewImageFill = []
                  } else {
                    this.commonFunction.dialogSuccess('添加失败')
                    this.handleClick(this.diageFaceData)
                    this.detailsNameS = null
                    this.detailsSexS = null
                    this.detailsNationS = null
                    this.detailsDescribeS = null
                    this.detailsTimeS = null
                    this.detailsProfessionS = null
                    this.detailsCountryS = null
                    this.detailsDocumentTypeVuleS = null
                    this.detailsDocumentCodeS = null
                    this.imageFill = []
                    this.ImageInfoList = []
                    this.dialogAlarmInfoAdd = false
                    this.divdialogVisible = false

                    this.NewImageFillId = []
                    this.NewImageFill = []
                  }
                })
              })

            }
          }
        }
      }
    },
    showBatchListImportDialogVisible () { // 展开批量导入功能
      this.divDialogVisibleThree = true
      this.BatchListImportDialogVisible = true
    },
    async importFaceList () { // 批量导入功能
      if (this.CurrentFaceLibraryName === '') {
        this.commonFunction.dialogSuccess('请选择名单库')
      } else {
        const self = this
        if (this.activeNameS === 'four') {
          let FaceStaticDBTreeVule = ''
          FaceStaticDBTreeVule = this.$refs.FaceLibraryStaticSDBTree.getCheckedFaceDB()
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
                  let FaceStaticDBVule = []
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
            FaceDBTreeVule = this.$refs.FaceLibraryBlackDBTree.getCheckedFaceDB()
          } else if (this.activeNameS === 'second') {
            FaceDBTreeVule = this.$refs.FaceLibraryWhiteDBTree.getCheckedFaceDB()
          } else if (this.activeNameS === 'three') {
            FaceDBTreeVule = this.$refs.FaceLibraryRedDBTree.getCheckedFaceDB()
          }
          if (FaceDBTreeVule == null || FaceDBTreeVule === 'undefined') {
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
                GetFacesLibaryPicResultByFileID(orgCode).then(filedata => {

                  if (filedata.data.length !== 0) {
                    let FaceDBVule = []
                    let DAS = filedata.data
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
                })
              }
            }
          }
        }
      }
    },
    AddPeopleListFuction (InfoData) {
      let FaceDBVule = InfoData
      let orgCodeS = {
        sync: 'true',
        callbackURL: 'null',
        repositorId: this.CurrentFaceLibraryId,
        peopleList: [{
          people:
            FaceDBVule
        }]
      }
      AddPeopleList(orgCodeS).then((filedata) => { // 新增人脸库

        let data = filedata
        if (data.status === '200' || data.message === '请求成功') {
          this.commonFunction.dialogSuccess('添加成功')
          this.handleClick(this.diageFaceData)
          this.BatchListImportDialogVisible = false
          this.divDialogVisibleThree = false
        } else {
          this.commonFunction.dialogSuccess('添加失败')
          this.handleClick(this.diageFaceData)
          this.BatchListImportDialogVisible = false
          this.divDialogVisibleThree = false
        }
      })
    },
    AddStaticPeopleListFuction (InfoData) {
      let FaceStaticDBVule = InfoData
      let orgCode = {
        sync: 'true',
        callbackURL: 'null',
        libraryId: this.CurrentFaceLibraryId,
        peoples: [{
          people:
            FaceStaticDBVule
        }]
      }
      AddStaticPeopleListS(orgCode).then((filedata) => { // 新增静态人脸库

        let data = filedata
        if (data.status === '200' || data.message === '请求成功') {
          this.commonFunction.dialogSuccess('添加成功')
          this.handleClick(this.diageFaceData)
          this.BatchListImportDialogVisible = false
          this.divDialogVisibleThree = false
        } else {
          this.commonFunction.dialogSuccess('添加失败')
          this.handleClick(this.diageFaceData)
          this.BatchListImportDialogVisible = false
          this.divDialogVisibleThree = false
        }
      })
    },
    choicefirstS (tab, event) {
      this.CurrentFaceLibraryName = ''
      this.CurrentFaceLibraryId = ''
      this.myInfo = []
      if (tab.name === 'first') {
        this.defaultSearchDate(1)
      } else if (tab.name === 'second') {
        this.defaultSearchDate(2)
      } else if (tab.name === 'three') {
        this.defaultSearchDate(3)
      } else if (tab.name === 'four') {
        this.defaultSearchDate(4)
      }
    },
    ShowUpdataLibaryDialog (data) { // 打开修改名单库弹框
      this.divdialogVisibleTwo = true
      if (this.activeName === 'four') {
        this.UpdataStaticFaceLibrarydialogVisible = true
        this.AddFaceLibrarydialogVisible_Div_row_bottom_input_Name_upload_static = data.name
        this.AddFaceLibrarydialogVisible_Div_row_bottom_input_peopelLabel_upload = data.type
        if (data.type === '2') {
          this.AddFaceLibrarydialogVisible_Div_row_bottom_input_peopelLabel_upload
        }
        this.AddFaceLibrarydialogVisible_Div_row_bottom_input_describe_upload_static = data.description
      } else {
        this.UpdateFaceLibrarydialogVisible = true
        this.AddFaceLibrarydialogVisible_Div_row_bottom_input_Name_upload = data.name
        this.FaceLibraryTypeVule_upload = data.type
        this.AddFaceLibrarydialogVisible_Div_row_bottom_input_describe_upload = data.description
      }
    },
    CloseUpdateLibraryListDialog () {
      this.UpdataStaticFaceLibrarydialogVisible = false
      this.UpdateFaceLibrarydialogVisible = false
      this.divdialogVisibleTwo = false
    },
    AddLibraryList_upload () { // 保存修改名单库

      if (this.activeName === 'four') {
        let orgData = {
          staticLibrary: {
            description: this.AddFaceLibrarydialogVisible_Div_row_bottom_input_describe_upload_static,
            name: this.AddFaceLibrarydialogVisible_Div_row_bottom_input_Name_upload_static,
            id: this.CurrentFaceLibraryId
          }
        }
        UpdateFaceStaticRepositories(orgData).then((filedata) => { // 修改静态人脸库（不是人脸名单）

          let data = filedata.data
          if (data.status === '200' || data.message === '请求成功') {
            this.commonFunction.dialogSuccess('修改成功')
            // this.$refs.FaceStaticDBTree.getOrgList()
            this.UpdataStaticFaceLibrarydialogVisible = false
            this.UpdateFaceLibrarydialogVisible = false
            this.divdialogVisibleTwo = false
          } else {
            this.commonFunction.dialogSuccess('修改失败')
            // this.$refs.FaceStaticDBTree.getOrgList()
            this.UpdataStaticFaceLibrarydialogVisible = false
            this.UpdateFaceLibrarydialogVisible = false
            this.divdialogVisibleTwo = false
          }
        })
      } else {
        let orgData = {
          repository: {
            description: this.AddFaceLibrarydialogVisible_Div_row_bottom_input_describe_upload,
            name: this.AddFaceLibrarydialogVisible_Div_row_bottom_input_Name_upload,
            id: this.CurrentFaceLibraryId
          }
        }
        UpdateFaceRepositories(orgData).then((filedata) => { // 修改人脸库（不是人脸名单）

          let data = filedata
          if (data.status === '200' || data.message === '请求成功') {
            this.commonFunction.dialogSuccess('修改成功')
            this.$refs.FaceBlackDBTree.getOrgList()
            this.$refs.FaceWhiteDBTree.getOrgList()
            this.$refs.FaceRedDBTree.getOrgList()
            this.UpdataStaticFaceLibrarydialogVisible = false
            this.UpdateFaceLibrarydialogVisible = false
            this.divdialogVisibleTwo = false
          } else {
            this.commonFunction.dialogSuccess('修改失败')
            this.$refs.FaceBlackDBTree.getOrgList()
            this.$refs.FaceWhiteDBTree.getOrgList()
            this.$refs.FaceRedDBTree.getOrgList()
            this.UpdataStaticFaceLibrarydialogVisible = false
            this.UpdateFaceLibrarydialogVisible = false
            this.divdialogVisibleTwo = false
          }
        })
      }
    },
    retrievalFaceLibrary () { // 检索人脸库（搜索的是库不是人脸名单）
      if (this.activeName === 'first') {
        this.$refs.FaceBlackDBTree.getOrgListByName(this.ChildFaceLibraryManage_left_InputValue)
      } else if (this.activeName === 'second') {
        this.$refs.FaceWhiteDBTree.getOrgListByName(this.ChildFaceLibraryManage_left_InputValue)
      } else if (this.activeName === 'three') {
        // this.$refs.FaceRedDBTree.getOrgListByName(this.ChildFaceLibraryManage_left_InputValue)
      } else if (this.activeName === 'four') {
        // this.$refs.FaceStaticDBTree.getOrgListByName(this.ChildFaceLibraryManage_left_InputValue)
      }
    },
    defaultSearchDate (tab) {

      let self = this
      if (tab === 1) {
        let blackList = []
        let queryData = {
          no: 1, size: 999, sort: 'asc', ordername: 'name'
        }
        GetFaceRepositoriesList(queryData).then((faceRepositoriesdata) => {

          if (faceRepositoriesdata.data) {
            for (var i = 0; i < faceRepositoriesdata.data.length; i++) {
              let dataType = faceRepositoriesdata.data[i].type

              switch (dataType) {
                case '2':
                  blackList.push(faceRepositoriesdata.data[i])
                  break
              }
            }
            this.handleClick(blackList[0])
          }
        })
      } else if (tab === 2) {
        let whiteList = []
        let queryDataSS = {
          no: 1, size: 999, sort: 'asc', ordername: 'name'
        }
        GetFaceRepositoriesList(queryDataSS).then((faceRepositoriesdata) => {

          if (faceRepositoriesdata.data) {
            for (var i = 0; i < faceRepositoriesdata.data.length; i++) {
              let dataType = faceRepositoriesdata.data[i].type

              switch (dataType) {
                case '3':
                  whiteList.push(faceRepositoriesdata.data[i])
                  break
              }
            }
            this.handleClick(whiteList[0])
          }
        })
      } else if (tab === 3) {
        let redList = []
        let queryDataSSS = {
          no: 1, size: 999, sort: 'asc', ordername: 'name'
        }
        GetFaceRepositoriesList(queryDataSSS).then((faceRepositoriesdata) => {

          if (faceRepositoriesdata.data) {
            for (var i = 0; i < faceRepositoriesdata.data.length; i++) {
              let dataType = faceRepositoriesdata.data[i].type

              switch (dataType) {
                case '4':
                  redList.push(faceRepositoriesdata.data[i])
                  break
              }
            }
            this.handleClick(redList[0])
          }
        })
      } else if (tab === 4) {
        let StaticList = []
        let queryDataS = {
          no: 1, size: 999, sort: 'asc', ordername: 'tag'
        }
        GetFaceStaticRepositoriesList(queryDataS).then((faceStaticRepositoriesdata) => { // 获取根节点组织机构数据
          if (faceStaticRepositoriesdata.data) {
            for (var i = 0; i < faceStaticRepositoriesdata.data.length; i++) {
              StaticList.push(faceStaticRepositoriesdata.data[i])
            }
            this.handleClick(StaticList[0])
          }
        })
      }
    }
  },
  mounted () {
    let self = this
    this.getGender()
    this.defaultSearchDate(1)
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
#ChildFaceLibraryManage_left_Input_a {
  margin-top: 11px;
  margin-left: -337px;
  position: fixed;
  color: #005982;
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
#AddFaceLibrarydialogVisible_Div_row_bottom_button {
  margin-top: 15px;
  width: 100px;
}
#AddFaceLibrarydialogVisible_Div_row_bottom_input_3 {
  width: 305px;
  height: 100%;
}
#AddFaceLibrarydialogVisible_Div_row_bottom_input_2 {
  width: 305px;
  height: 100%;
}
#AddFaceLibrarydialogVisible_Div_row_bottom_Label_a {
  color: #0f86ae;
  font-size: 15px;
  line-height: 30px;
}
#AddFaceLibrarydialogVisible_Div_row_bottom_input {
  width: 305px;
  height: 100%;
}
#AddFaceLibrarydialogVisible_Div_row_bottom_Label {
  margin-left: 8px;
  width: 60px;
  height: 100%;
}
#AddFaceLibrarydialogVisible_Div_row_bottom_2 {
  height: 60px;
  margin-top: 10px;
}
#AddFaceLibrarydialogVisible_Div_row_bottom_1 {
  height: 30px;
  margin-top: 10px;
}
#AddFaceLibrarydialogVisible_Div_row_top_a {
  color: #0f86ae;
  margin-top: 7px;
  float: left;
  margin-left: 8px;
  font-size: 15px;
}
#AddFaceLibrarydialogVisible_Div_row_top {
  height: 30px;
  width: 100%;
  border: 0.5px solid #005982;
  border-left: 0px;
  border-top: 0px;
  border-right: 0px;
}
#AddFaceLibrarydialogVisible_Div {
  height: 270px;
  width: 100%;
  margin-left: -1%;
  border: 0.5px solid #005982;
}
#ChildFaceLibraryManage_right_operation_span_CurrentLibrary {
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
  height: 25%;
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
  width: 37%;
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
  height: 25%;
}
#divdialogVisible-Details-row-one-col-four {
  width: 37%;
  height: 100%;
}
#divdialogVisible-Details-row-one-col-three {
  width: 10%;
  height: 100%;
}
#divdialogVisible-Details-row-one-col-two {
  width: 37%;
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
  height: 60%;
}
#divdialogVisible-Details {
  width: 92%;
  height: 90%;
  margin-left: 2.5%;
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
#ChildFaceLibraryManage-Right-Bottom-Time {
  width: 97%;
  height: 38px;
  float: left;
  line-height: 38px;
  color: #005982;
  margin-left: 6px;
  font-size: 14px;
}
#ChildFaceLibraryManage-Right-Bottom-CameraCode {
  width: 97%;
  height: 38px;
  float: left;
  line-height: 38px;
  color: #005982;
  margin-left: 6px;
  font-size: 14px;
}
.inner-container {
  position: absolute;
  left: 0;
  top: 0;
  right: -17px;
  bottom: 0;
  overflow-x: hidden;
  overflow-y: scroll;
}
#ChildFaceLibraryManage-Right-Bottom-Camera {
  width: 97%;
  height: 38px;
  float: left;
  line-height: 38px;
  color: #005982;
  margin-left: 6px;
  font-size: 14px;
}
.outer-container {
  width: 100%;
  height: 100%;
  position: relative;
  overflow: hidden;
}
#ChildFaceLibraryManage-Right-Central-inside-Label {
  width: 100%;
  height: 80px;
  color: #005982;
  font-size: 14px;
  margin-top: 8px;
}
#ChildFaceLibraryManage-Right-Central-inside-three-Label {
  color: #005982;
  font-size: 14px;
  line-height: 30px;
  margin-left: 10px;
}
#ChildFaceLibraryManage-Right-Central-inside-three-xiangqing {
  margin-top: 5px;
  width: 20px;
  margin-right: 9px;
  height: 20px;
  float: right;
  border: none;
  color: #0f86ae;
}
#ChildFaceLibraryManage-Right-Central-inside-three-xiangqing:hover {
  border: 0.5px solid #00ffff;
}
#ChildFaceLibraryManage-Right-Central-inside-three-dingwei:hover {
  border: 0.5px solid #00ffff;
}
#ChildFaceLibraryManage-Right-Central-inside-three-xiansuoxinxi:hover {
  border: 0.5px solid #00ffff;
}
#ChildFaceLibraryManage-Right-Central-inside-three-renlianchaxun:hover {
  border: 0.5px solid #00ffff;
}

#ChildFaceLibraryManage-Right-Central-inside-three-dingwei {
  margin-top: 5px;
  width: 20px;
  margin-right: 5px;
  height: 20px;
  float: right;
  border: none;
}
#ChildFaceLibraryManage-Right-Central-inside-three-xiansuoxinxi {
  margin-top: 5px;
  width: 20px;
  margin-right: 5px;
  height: 20px;
  float: right;
  border: none;
}
#ChildFaceLibraryManage-Right-Central-inside-three-renlianchaxun {
  margin-right: 5px;
  margin-top: 5px;
  width: 20px;
  height: 20px;
  float: right;
  border: none;
}
#ChildFaceLibraryManage-Right-Central-inside-One {
  width: 100%;
  height: 30px;
  text-align: center;
  margin-top: 8px;
}
#ChildFaceLibraryManage-Right-Central-inside-two {
  width: 96.8%;
  height: 120px;
  margin-left: 1.5%;
  border: 0.5px solid #005982;
  // background: url("../../assets/images/IntelligentAnalysis/imageBackground.png")
  // no-repeat center center / 80% 100%;
}
#ChildFaceLibraryManage-Right-Central-inside-three {
  width: 99.8%;
  height: 30px;
  margin-top: 3px;
  border: 0.5px solid #005982;
}
#ChildFaceLibraryManage-Right-Central-inside-four {
  width: 99.8%;
  height: 115px;
}
#ChildFaceLibraryManage-Right-Central-inside {
  width: 92%;
  height: 97%;
  margin-left: 3.8%;
  margin-top: 1.5%;
  border: 0.5px solid #005982;
  border-radius: 4px;
}
#ChildFaceLibraryManage-Right-Central-Out:hover {
  border: 0.5px solid #00ffff;
}
#ChildFaceLibraryManage-Right-Central-Out {
  width: 220px;
  height: 320px;
  border: 0.5px solid #005982;
  float: left;
}

#PaginationS {
  width: 40px;
  height: 30px;
  float: right;
  // margin-top: 3px;
}
#ChildFaceLibraryManage_left_Button_ {
  float: right;
  margin-top: 5px;
  margin-right: 30px;
}
#ChildFaceLibraryManage_left_Button_1 {
  color: #11bbf3;
  font-size: 25px;
  width: 30px;
}
#ChildFaceLibraryManage_right_operation_button1 {
  width: 100px;
  margin-left: 50px;
}
#ChildFaceLibraryManage_right_operation_button2 {
  width: 100px;
  margin-left: 20px;
}
#ChildFaceLibraryManage_right_operation_span_carNumber {
  font-size: 14px;
  color: #0f86ae;
  margin-left: 10px;
}
#ChildFaceLibraryManage_right_operation_span_carType {
  font-size: 14px;
  color: #0f86ae;
  margin-left: 10px;
}
#ChildFaceLibraryManage_right_operation_span {
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
#ChildFaceLibraryManage_right_label_value {
  font-size: 22px;
  color: #0294a0;
  line-height: 40px;
  margin-left: 10px;
}
#ChildFaceLibraryManage_right_label {
  width: 99.8%;
  margin-left: 4px;
  margin-top: 3px;
  height: 40px;
  border: 0.5px solid #005982;
  border-radius: 4px;
}
#ChildFaceLibraryManage_right_Pagination {
  width: 99.8%;
  margin-left: 4px;
  height: 40px;
  border: 0.5px solid #005982;
  margin-top: 4px;
}
#ChildFaceLibraryManage_right_operation {
  width: 99.8%;
  margin-left: 4px;
  height: 45px;
  border: 0.5px solid #005982;
  border-right: none;
  border-left: none;
}
#ChildFaceLibraryManage_right_content {
  width: 99.8%;
  margin-left: 4px;
  height: 82%;
  border: 0.5px solid #005982;
  border-radius: 2px;
}
#ChildFaceLibraryManage_left_InputS {
  margin-top: 10px;
}
#ChildFaceLibraryManage_left_Input {
  width: 100%;
  height: 40px;
}
#ChildFaceLibraryManage_left_Button {
  width: 100%;
  height: 40px;
}
#ChildFaceLibraryManage_left_Tabel {
  width: 100%;
  height: 89%;
  border: 0.5px solid #005982;
}
#ChildFaceLibraryManage_left {
  width: 18%;
  height: 100%;
}
#ChildFaceLibraryManage_right {
  width: 81.9%;
  height: 100%;
}

.ChildFaceLibraryManage_left_Tabel_cameraTree {
  height: 100%;
  overflow-y: scroll;
}
.ChildFaceLibraryManage_left_Tabel_cameraTree::-webkit-scrollbar {
  width: 13px;
  height: 16px;
  background-color: #f5f5f5;
}

/*定义滚动条轨道 内阴影+圆角*/
.ChildFaceLibraryManage_left_Tabel_cameraTree::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);

  background-color: #1b384a;
}

/*定义滑块 内阴影+圆角*/
.ChildFaceLibraryManage_left_Tabel_cameraTree::-webkit-scrollbar-thumb {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  background-color: #555;
}
</style>
<style>
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
#ChildFaceLibraryManage_left_Tabel .el-tabs__item {
  width: 25%;
  height: 50% !important;
}
#BatchListImportDialogVisible_Div_tow_div .el-tabs__item {
  width: 25%;
  height: 100% !important;
}
#ChildFaceLibraryManage_right_operation .el-input__icon {
  height: 100%;
  width: 25px;
  text-align: center;
  -webkit-transition: all 0.3s;
  transition: all 0.3s;
  line-height: 12px;
}
#ChildFaceLibraryManage_left_Input .el-input__inner {
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
#ChildFaceLibraryManage_left_Button_ .el-button {
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
#AddFaceLibrarydialogVisible_Div_row_bottom_input_2 .el-input__inner {
  height: 60px;
}
#AddFaceLibrarydialogVisible_Div_row_bottom_input_3 .el-input {
  width: 305px;
}
/* .ChildSearchPersonByImage_tabs_S .el-tabs__content {
  height: 640px !important;
} */
.ChildSearchPersonByImage_tabs_S .el-tabs__content {
  height: 60% !important;
}
.ChildSearchPersonByImage_tabs_S .el-tabs__content .el-tab-pane {
  height: 720px !important;
}
.BatchListImportDialogVisible_Div_tow_div_tabs .el-tabs__content {
  height: 520px !important;
}
</style>

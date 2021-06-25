<template>
  <el-row>
    <el-col id="ChildSearchVehicleByImageView-Left">
      <div id="ChildSearchVehicleByImageView-Left-Top">
        <div id="PictureRetrievalText">
          <label id="PictureRetrievalLabel">图片检索</label>
        </div>
      </div>
      <div id="ChildSearchVehicleByImageView-Left-Bottom">
        <div id="PictureBox">
          <div id="ImageDiv">
            <!-- <img id="ImageFace"
                 class="el-upload-list__item-thumbnail"
                 :src="dialogImageUrl"
                 alt=""> -->

            <img id="ImageFace"
                 class="el-upload-list__item-thumbnail"
                 v-if="dialogImageUrlLength > 0"
                 :src="dialogImageUrl" />

            <img id="ImageFaceTwo"
                 v-if="dialogImageUrlLength <= 0"
                 src="../../../../assets/images/IntelligentAnalysis/imageBackground.png" />
          </div>
          <div id="ChildSearchVehicleByImageView-Left-Bottom-chooseImg">
            <!-- 放图片 -->
            <!-- <div id="asd"> -->
            <el-upload class="upload-demo"
                       ref="upload"
                       action="https://jsonplaceholder.typicode.com/posts/"
                       :show-file-list="false"
                       :on-change="handlePictureCardPreview"
                       :file-list="fileList"
                       :auto-upload="true">
              <el-button id="ChoosePictureButton"
                         slot="trigger"
                         size="small"
                         type="primary">选择图片</el-button>
              <div id="Similarity">
                <label id="SimilarityLable"
                       for="SimilarityLable">相似度:</label>
                <div id="SimilaritySlider">
                  <el-slider v-model="SimilaritySlidervalue1"
                             :min="80"
                             :step="5"
                             :show-stops="true">
                  </el-slider>
                  <label id="SimilarityScaleValue1"
                         for="SimilarityScaleValue1">80</label>
                  <label id="SimilarityScaleValue2"
                         for="SimilarityScaleValue">85</label>
                  <label id="SimilarityScaleValue3"
                         for="SimilarityScaleValue">90</label>
                  <label id="SimilarityScaleValue4"
                         for="SimilarityScaleValue">95</label>
                  <label id="SimilarityScaleValue5"
                         for="SimilarityScaleValue">100</label>
                </div>
              </div>
            </el-upload>
          </div>
        </div>
        <div id="ChildSearchVehicleByImageView-Left-Bottom-ConditionBox">
          <div id="StartTime">
            <label id="StartTimeLabel"
                   for="StartTimeLabel">起始时间:</label>
            <div id="ChooseStarTimeBox">
              <el-date-picker v-model="params.start_time"
                              style="width:100%"
                              class=".el-input__inner"
                              type="datetime"
                              :picker-options="startDatePicker"
                              @change="dataTimeChange('start')"
                              placeholder="年-月-日  时-分-秒">
              </el-date-picker>
            </div>
          </div>
          <div id="EndTime">
            <label id="EndTimeLable"
                   for="EndTimeLable">截至时间:</label>
            <div id="ChooseEndTimeBox">
              <el-date-picker v-model="params.end_time"
                              style="width: auto"
                              class=".el-input__inner"
                              type="datetime"
                              :picker-options="endDatePicker"
                              @change="dataTimeChange('end')"
                              placeholder="年-月-日  时-分-秒">
              </el-date-picker>
            </div>
          </div>
        </div>
        <div id="ChildSearchVehicleByImageView-Left-Bottom-ConditionQueryBox">
          <el-col id="ChildSearchVehicleByImageView-Left-Bottom-ConditionQueryBox-label">
            <el-row id="ChildSearchVehicleByImageView-row01">
              <label id="ChildSearchVehicleByImageView-row01-label">车牌号码：</label>
            </el-row>
            <el-row id="ChildSearchVehicleByImageView-row01">
              <label id="ChildSearchVehicleByImageView-row01-label">车辆类型：</label>
            </el-row>
            <el-row id="ChildSearchVehicleByImageView-row01">
              <label id="ChildSearchVehicleByImageView-row01-label">车牌类型：</label>
            </el-row>
            <el-row id="ChildSearchVehicleByImageView-row01">
              <label id="ChildSearchVehicleByImageView-row01-label">车牌颜色：</label>
            </el-row>
            <el-row id="ChildSearchVehicleByImageView-row01">
              <label id="ChildSearchVehicleByImageView-row01-label">车身颜色：</label>
            </el-row>
            <el-row id="ChildSearchVehicleByImageView-row01">
              <label id="ChildSearchVehicleByImageView-row01-label">特殊车辆：</label>
            </el-row>
            <el-row id="ChildSearchVehicleByImageView-row01">
              <label id="ChildSearchVehicleByImageView-row01-label">有无车牌：</label>
            </el-row>
            <el-row id="ChildSearchVehicleByImageView-row01">
              <label id="ChildSearchVehicleByImageView-row01-label">车牌遮挡：</label>
            </el-row>
          </el-col>
          <el-col id="ChildSearchVehicleByImageView-Left-Bottom-ConditionQueryBox-selectBox">
            <el-row id="ChildSearchVehicleByImageView-row02">
              <el-input v-model="carPlate"
                        class="queryDateInput" />
            </el-row>
            <el-row id="ChildSearchVehicleByImageView-row02">
              <el-select v-model="valueVehicleType"
                         placeholder="请选择">
                <el-option v-for="item in optionsVehicleType"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
            </el-row>
            <el-row id="ChildSearchVehicleByImageView-row02">
              <el-select v-model="valueCarPlateType"
                         placeholder="请选择">
                <el-option v-for="item in optionsCarPlateType"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
            </el-row>
            <el-row id="ChildSearchVehicleByImageView-row02">
              <el-select v-model="valueCarPlateColor"
                         placeholder="请选择">
                <el-option v-for="item in optionsCarPlateColor"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
            </el-row>
            <el-row id="ChildSearchVehicleByImageView-row02">
              <el-select v-model="valueCarColor"
                         placeholder="请选择">
                <el-option v-for="item in optionsCarColor"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
            </el-row>
            <el-row id="ChildSearchVehicleByImageView-row02">
              <el-select v-model="valueSpeScialVehicleTypeIdx"
                         placeholder="请选择">
                <el-option v-for="item in optionsSpeScialVehicleTypeIdx"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
            </el-row>
            <el-row id="ChildSearchVehicleByImageView-row02">
              <el-select v-model="valueLicLensedVehicle"
                         placeholder="请选择">
                <el-option v-for="item in optionsLicLensedVehicle"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
            </el-row>
            <el-row id="ChildSearchVehicleByImageView-row02">
              <el-select v-model="valueObscuredPlate"
                         placeholder="请选择">
                <el-option v-for="item in optionsObscuredPlate"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
            </el-row>
          </el-col>
        </div>
        <div id="Retrieve-Reset-Button">
          <el-button id="RetrieveButton"
                     @click="loadImage"
                     type="primary">检索</el-button>
          <el-button id="ResetButton"
                     @click="ResetButtonClick"
                     type="primary">重置</el-button>
        </div>
      </div>
    </el-col>
    <el-col id="ChildSearchVehicleByImageView-Right">
      <div id="ChildSearchVehicleByImageView-Right-Top">
        <div id="FaceSet">
          <label id="FaceSetLabel">车辆集合</label>
        </div>
        <div>
          <el-button id="Export"
                     @click="exportPeopleFaces"
                     type="primary">导出</el-button>
          <el-button id="TrackDisplay"
                     @click="trackDisplayFuction"
                     type="primary">轨迹展示</el-button>
        </div>
      </div>
      <div id="ChildSearchVehicleByImageView-Right-Central">
        <div class="outer-container">
          <div class="inner-container">
            <div v-for="(info, index) in myInfo"
                 :key="index">
              <div id="ChildSearchVehicleByImageView-Right-Central-Out">
                <div id="ChildSearchVehicleByImageView-Right-Central-inside">
                  <div id="ChildSearchVehicleByImageView-Right-Central-inside-One">
                    <label id="ChildSearchVehicleByImageView-Right-Central-inside-Label">相似度{{ info.confidenceThreshold }}</label>
                  </div>
                  <div id="ChildSearchVehicleByImageView-Right-Central-inside-two">
                    <img id="ImageFaceDate"
                         class="el-upload-list__item-thumbnail"
                         @click="
                        showBigImg(
                          info.cameraName,
                          info.imageTimestamp,
                          info.picUrl
                        )
                      "
                         :src="info.thumPicUrl"
                         alt="" />
                  </div>
                  <div id="ChildSearchVehicleByImageView-Right-Central-inside-three">
                    <label id="ChildSearchVehicleByImageView-Right-Central-inside-three-Label">车辆详情</label>
                    <a style="font-size: 20px; }"
                       id="ChildSearchVehicleByImageView-Right-Central-inside-three-renlianchaxun"
                       title="检索"
                       @click="getBase64(info.picUrl)"
                       class="icon iconfont iconfontColor">&#xe64b;</a>
                    <a style="font-size: 20px; }"
                       id="ChildSearchVehicleByImageView-Right-Central-inside-three-xiansuoxinxi"
                       title="添加线索"
                       @click="showAddClue(info)"
                       class="icon iconfont iconfontColor">&#xe62a;</a>
                    <a style="font-size: 20px; }"
                       id="ChildSearchVehicleByImageView-Right-Central-inside-three-dingwei"
                       title="定位"
                       @click="jumpMap(info)"
                       class="icon iconfont iconfontColor">&#xe786;</a>
                    <a style="font-size: 20px; }"
                       id="ChildSearchVehicleByImageView-Right-Central-inside-three-xiangqing"
                       title="详情"
                       @click="showAlarmDetail(info)"
                       class="icon iconfont iconfontColor">&#xe659;</a>
                  </div>
                  <div id="ChildSearchVehicleByImageView-Right-Central-inside-four">
                    <label id="ChildSearchVehicleByImageView-Right-Bottom-Camera">摄像机：{{ info.cameraName }}</label>
                    <label id="ChildSearchVehicleByImageView-Right-Bottom-CameraCode">车牌号：{{ info.lprInfo.plateNumber }}</label>
                    <label id="ChildSearchVehicleByImageView-Right-Bottom-Time">时间：{{ info.imageTimestamp }}</label>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div id="ChildSearchVehicleByImageView-Right-Bottom">
        <el-row id="Pagination">
          <pagination style="float: right; margin-top: 3px"
                      :total="params.total"
                      :page.sync="params.pageIndex"
                      :limit.sync="params.pageSize"
                      @pagination="loadImage" />
        </el-row>
      </div>
      <div v-show="divdialogVisible">
        <el-dialog title="详情"
                   :visible.sync="dialogAlarmInfoVisible"
                   width="800px"
                   :before-close="handleClose">
          <div class="ChildSearchFaceByImageDetailDev">
            <el-col>
              <!-- 人脸视频 -->
              <el-row id="ChildSearchFaceVideo">
                <videoPlay ref="videoPlayer"
                           style="height: 100% !important; width: 100%"></videoPlay>
              </el-row>
              <!-- 人脸时间、播放 暂停 -->
              <el-row id="dialogplayChildSearchFaceRow">
                <div id="dialogplayChildSearchFace">
                  <span class="queryDateName"> 时间段 </span>
                  <el-date-picker v-model="AlarmDetailParams.start_time"
                                  type="datetime"
                                  :picker-options="startDatePicker"
                                  placeholder="年-月-日  时-分-秒">
                  </el-date-picker>

                  <span style="color: #005982">----</span>

                  <el-date-picker v-model="AlarmDetailParams.end_time"
                                  style="width: 210px"
                                  type="datetime"
                                  :picker-options="endDatePicker"
                                  placeholder="年-月-日  时-分-秒">
                  </el-date-picker>
                  <span class="icon iconfont iconfontColor"
                        id="iconIconfontIconfontColor"
                        @click="playHistoryVideo(FaceInfoData.cameraSn)">&#xe609;</span>
                </div>
              </el-row>
              <!-- 详情 -->
              <el-row id="dialogChildSearchFaceDetail">
                <el-col :span="10">
                  <div id="dialogChildSearchFaceDetailImage">
                    <img id="dialogChildSearchFaceDetailImageDate"
                         class="el-upload-list__item-thumbnail"
                         :src="ImageFaceDetailBit64"
                         @click="
                        showBigImg(
                          FaceInfoData.cameraName,
                          FaceInfoData.imageTimestamp,
                          ImageFaceDetailBit64
                        )
                      "
                         alt="" />
                  </div>
                </el-col>
                <el-col :span="11"
                        id="el-col-11">
                  <el-col id="el_col_1">
                    <el-row id="el_row_1"><span id="el_row_1_span">车牌号</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span">域名称</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span">摄像机名称</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span">车辆类型</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span">车身颜色</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span">车牌遮挡</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span">特殊车辆</span></el-row>
                  </el-col>
                  <el-col id="el_col_2">
                    <el-row id="el_row_1"><span id="el_row_1_span_">{{
                        this.plateNumberDetails | ellipsis
                      }}</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span_">{{
                        this.domainNameDetails | ellipsis
                      }}</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span_">{{
                        this.cameraNameDetails | ellipsis
                      }}</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span_">{{
                        this.carTypeDetails
                          | ellipsis
                          | getOptionsVehicleType(that)
                      }}</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span_">{{
                        this.carColorDetails
                          | ellipsis
                          | getOptionsCarColor(that)
                      }}</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span_">{{
                        this.obscuredPlateDetails
                          | ellipsis
                          | getObscuredPlate(that)
                      }}</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span_">{{
                        this.specialVehicleTypeIdxDetails
                          | ellipsis
                          | getSpecialVehicleTypeIdx(that)
                      }}</span></el-row>
                  </el-col>
                  <el-col id="el_col_3">
                    <el-row id="el_row_1"><span id="el_row_1_span">车牌类型</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span">域编码</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span">摄像机编码</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span">年款</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span">车牌颜色</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span">行驶方向</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span">天窗站人</span></el-row>
                  </el-col>
                  <el-col id="el_col_4">
                    <el-row id="el_row_1"><span id="el_row_1_span_">{{
                        this.plateTypeDetails | ellipsis | getPlateType(that)
                      }}</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span_">{{
                        this.domainCodeDetails | ellipsis
                      }}</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span_">{{
                        this.cameraSnDetails | ellipsis
                      }}</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span_">{{
                        this.yearDetails | ellipsis
                      }}</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span_">{{
                        this.plateColorDetails | ellipsis | gatPlateColor(that)
                      }}</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span_">{{
                        this.carDirectionDetails | ellipsis
                      }}</span></el-row>
                    <el-row id="el_row_1"><span id="el_row_1_span_">{{
                        this.headOutSunroofDetails
                          | ellipsis
                          | getHeadOutSunroof(that)
                      }}</span></el-row>
                  </el-col>
                </el-col>
              </el-row>
            </el-col>
          </div>
          <span slot="footer"
                class="dialog-footer"> </span>
        </el-dialog>
        <el-dialog title="线索信息"
                   :visible.sync="dialogAddClueVisible"
                   width="680px"
                   label-width="100px">
          <div class="alarmAddClueDev">
            <el-col>
              <el-form id="addCluefoDiaolg"
                       ref="AddClueInfoData"
                       :model="AddClueInfoData">
                <div class="el_form_itemDiv">
                  <el-form-item label="创建人:">
                    <el-input class="addCluefoDiaolg_input"
                              v-model="AddClueInfoData.creator"
                              readonly="true" />
                  </el-form-item>
                  <el-form-item label="联系电话:">
                    <el-input class="addCluefoDiaolg_input"
                              v-model="AddClueInfoData.creatorTelephone" />
                  </el-form-item>
                </div>
                <div class="el_form_itemDiv">
                  <el-form-item label="部 门:">
                    <el-input class="addCluefoDiaolg_input"
                              v-model="AddClueInfoData.orgName"
                              readonly="true" />
                  </el-form-item>
                  <el-form-item label="部门电话:">
                    <el-input class="addCluefoDiaolg_input"
                              v-model="AddClueInfoData.orgTelephone" />
                  </el-form-item>
                </div>

                <el-form-item label="描述:">
                  <el-input type="textarea"
                            style="width: 480px"
                            class="textareaStyle"
                            v-model="AddClueInfoData.note"></el-input>
                </el-form-item>
                <el-form-item label="关联线索:">
                  <el-table :data="clueTableData"
                            :header-cell-class-name="getHeaderRowClass"
                            :cell-class-name="getRowClass"
                            style="width: 100%;background:#032941;Opacity='0.5';width:495px "
                            height="250"
                            border
                            highlight-current-row
                            @current-change="handleCurrentChange"
                            :default-sort="{ prop: 'createTime', order: 'descending' }">
                    <el-table-column type="index"
                                     width="35"> </el-table-column>
                    <el-table-column prop="name"
                                     label="线索名称"
                                     sortable
                                     width="160">
                    </el-table-column>

                    <el-table-column prop="creator"
                                     label="创建人"
                                     sortable
                                     width="140">
                    </el-table-column>

                    <el-table-column prop="createTime"
                                     label="创建时间">
                    </el-table-column>
                  </el-table>
                  <Pagination style="float: right"
                              :total="clueTablePaginationParams.total"
                              :page.sync="clueTablePaginationParams.pageIndex"
                              :limit.sync="clueTablePaginationParams.pageSize"
                              @pagination="showAddClue"></Pagination>
                </el-form-item>
              </el-form>
            </el-col>
            <span slot="footer"
                  class="dialog-footer bottomButton">
              <el-button @click="closeAddClueDialogVisible">取 消</el-button>
              <el-button type="primary"
                         @click="showAlarmRelationClue('AddClueInfoData')">确 定</el-button>
            </span>
          </div>
        </el-dialog>
        <!--大图-->
        <el-dialog title="大图"
                   :visible.sync="fullImagedialogVisible">
          <div class="full-img">
            <img :src="BigImgSrc"
                 width="800px"
                 height="550px" />
          </div>
          <span slot="footer"
                class="dialog-footer">
            <el-button type="primary"
                       @click="fullImagedialogVisible = false">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </el-col>
  </el-row>
</template>
<script>
import axios from "axios";
import configInfo from '@/webconfig/configInfo'
// import Pagination from "@/components/Pagination/Pagination";
// import videoPlay from "@/components/videoPlay/videoPlay";
import { IntelligentAnalysis_getVehicleByParameter, IntelligentAnalysis_uplodeImage, IntelligentAnalysis_getVehicleByFaceId } from "@/api/pvMonitor/IntelligentAnalysis";
import {
  optionsSpeScialVehicleTypeIdx,
  optionsObscuredPlate,
  optionsLicLensedVehicle,
  optionsCarColor,
  optionsCarPlateColor,
  optionsCarPlateType,
  optionsVehicleType,
} from "@/common/enum";
export default {
  // components: { Pagination, videoPlay },
  data () {
    return {
      that: this,
      params: {
        start_time: this.commonFunction.addThreeS(
          this.commonFunction.getNowTime(),
          -604800
        ),
        end_time: this.commonFunction.getNowTime(),
        pageIndex: 1,
        pageSize: 20,
        name: "",
        total: 1,
      },
      AlarmDetailParams: {
        start_time: "",
        end_time: "",
      },
      clueTablePaginationParams: {
        pageIndex: 1,
        pageSize: 20,
        total: 1,
      },
      carPlate: "", // 车牌号码
      info: "",
      myInfo: [],
      fileList: [],
      FaceInfoData: [],
      dialogImageUrl: "",
      dialogImageUrlLength: "",
      localFile: {},
      // 相似度滑块的值
      SimilaritySlidervalue1: 80,
      ImageFaceDateBit64: "",
      ImageFaceDetailBit64: "", // 告警详情图片
      divdialogVisible: false,
      BigImgSrc: "", // 大图图片路径
      dialogAlarmInfoVisible: false,
      loadImageFile: "", // 上传图片返回的fillId
      secondSearchImageFile: "", // 二次查询返回的fillId
      image: "",
      camera: "",
      cameraCode: "",
      time: "",
      fileId: "",
      fullImagedialogVisible: false, // 大图显隐
      startDatePicker: this.beginDate(),
      endDatePicker: this.processDate(),
      clueTableData: [],
      dialogAddClueVisible: false,
      AddClueInfoData: {
        clueNumber: "",
        note: null,
        creator: "",
        orgName: "",
        creatTime: "",
        name: "",
        creatorId: "",
        creatorTelephone: null,
        updateTime: "",
        id: "",
        orgTelephone: "",
        orgId: "",
      },
      ClueInfoData: {
        clueNumber: "",
        note: null,
        creator: "",
        orgName: "",
        creatTime: "",
        name: "",
        creatorId: "",
        creatorTelephone: null,
        updateTime: "",
        id: "",
        orgTelephone: "",
        orgId: "",
      },
      alarmOriginaData: [],
      AlarmInfoData: {
        alarmtime: "",
        cameraname: "",
        alarmtype: "",
        score: "",
        blackSmallImage: "",
        blackBigImage: "",
        thumPicUrl: "",
        picUrl: "",
        code: "",
      },
      AddclueAlarminfo: null,
      optionsVehicleType: optionsVehicleType,
      valueVehicleType: "",
      optionsCarPlateType: optionsCarPlateType,
      valueCarPlateType: "",
      optionsCarPlateColor: optionsCarPlateColor,
      valueCarPlateColor: "",
      optionsCarColor: optionsCarColor,
      valueCarColor: "",
      optionsSpeScialVehicleTypeIdx: optionsSpeScialVehicleTypeIdx,
      valueSpeScialVehicleTypeIdx: "",
      optionsLicLensedVehicle: optionsLicLensedVehicle,
      valueLicLensedVehicle: "",
      optionsObscuredPlate: optionsObscuredPlate,
      valueObscuredPlate: "",
      nowSelectClueId: "",

      plateNumberDetails: "", // 详情中的车牌号
      domainNameDetails: "", // 详情中的域名称
      cameraNameDetails: "", // 摄像机名称
      carTypeDetails: "", // 车辆类型
      carColorDetails: "", // 车身颜色
      obscuredPlateDetails: "", // 车牌遮挡
      specialVehicleTypeIdxDetails: "", // 特殊车辆

      plateTypeDetails: "", // 车牌类型
      domainCodeDetails: "", // 域编码
      cameraSnDetails: "", //  摄像机编码
      yearDetails: "", // 年款
      plateColorDetails: "", // 车牌颜色
      carDirectionDetails: "", // 行驶方向
      headOutSunroofDetails: "", // 天窗站人
    };
  },
  filters: {
    ellipsis (value) {
      if (!value) return "";
      if (value.length > 14) {
        return value.slice(0, 14) + "...";
      }
      return value;
    },
    getOptionsVehicleType: function (value, self) {
      if (value != null) {
        return self.commonFunction.getOptionsVehicleType(value);
      }
    },
    getOptionsCarColor: function (value, self) {
      if (value != null) {
        return self.commonFunction.getOptionsCarColor(value);
      }
    },
    getObscuredPlate: function (value, self) {
      if (value != null) {
        return self.commonFunction.getObscuredPlate(value);
      }
    },
    getSpecialVehicleTypeIdx: function (value, self) {
      if (value != null) {
        return self.commonFunction.getSpecialVehicleTypeIdx(value);
      }
    },
    getPlateType: function (value, self) {
      if (value != null) {
        return self.commonFunction.getPlateType(value);
      }
    },
    gatPlateColor: function (value, self) {
      if (value != null) {
        return self.commonFunction.gatPlateColor(value);
      }
    },
    getHeadOutSunroof: function (value, self) {
      if (value != null) {
        return self.commonFunction.getHeadOutSunroof(value);
      }
    },
  },
  methods: {
    handlePictureCardPreview (file, fileList) {
      this.dialogImageUrl = file.url;
      this.localFile = file.raw; // 或者 this.localFile=file.raw

      // 转换操作可以不放到这个函数里面，
      // 因为这个函数会被多次触发，上传时触发，上传成功也触发
      let reader = new FileReader();
      reader.readAsDataURL(this.localFile); // 这里也可以直接写参数event.raw

      // 转换成功后的操作，reader.result即为转换后的DataURL ，
      // 它不需要自己定义，你可以console.log(reader.result)看一下
      reader.onload = () => {
        this.dialogImageUrl = reader.result;
        this.dialogImageUrlLength = reader.result.length;
      };
      // /* 另外一种本地预览方法 */
      /* let URL = window.URL || window.webkitURL;
      this.dialogImageUrl = URL.createObjectURL(file.raw); */
    },
    dataTimeChange (type) {
      // type 取值： start 或者 end
      if (!(this.params.end_time && this.params.start_time)) {
        return;
      }
      let startTime = new Date(this.params.start_time).getTime();
      let endTime = new Date(this.params.end_time).getTime();
      if (startTime > endTime) {
        if (type === "start") {
          this.params.start_time = "";
        } else if (type === "end") {
          this.params.end_time = "";
        }
      }
    },
    beginDate () {
      const self = this;
      return {
        disabledDate (time) {
          if (self.params.end_time) {
            // 如果结束时间不为空，则小于结束时间
            return new Date(self.params.end_time).getTime() < time.getTime();
          } else {
            // return time.getTime() > Date.now()//开始时间不选时，结束时间最大值小于等于当天
            // return time.getTime() < Date.now() - 1000*3600*24//开始时间不选时，结束时间最大值大于等于当天
          }
        },
      };
    },
    processDate () {
      const self = this;
      return {
        disabledDate (time) {
          if (self.params.start_time) {
            // 如果开始时间不为空，则结束时间大于开始时间
            return new Date(self.params.start_time).getTime() > time.getTime();
          } else {
            // return time.getTime() < Date.now() - 1000*3600*24//开始时间不选时，结束时间最大值大于等于当天
          }
        },
      };
    },
    handleClick (LoadFileId) {
      const self = this;
      self.myInfo = [];
      const startTimeInt = this.commonFunction.TimeToTimeStamp(
        this.params.start_time
      );
      const endTimeInt = this.commonFunction.TimeToTimeStamp(
        this.params.end_time
      );
      const SimilarityVule = this.SimilaritySlidervalue1;
      if (LoadFileId.length !== 0) {
        // 根据图片搜索
        let orgCode = {
          startTime: startTimeInt,
          endTime: endTimeInt,
          page: {
            no: this.params.pageIndex,
            size: this.params.pageSize,
            sort: "desc",
            orderName: "time",
          },
          confidenceThreshold: SimilarityVule,
          picture: { fileId: LoadFileId },
          searchType: "0",
        };
        IntelligentAnalysis_getVehicleByFaceId(orgCode).then((dataTwo) => {
          // 根据图片获取车辆数据
          if (dataTwo) {
            if (dataTwo.data) {
              self.myInfo = dataTwo.data;
              this.params.total = dataTwo.page.total;
              this.params.pageIndex = dataTwo.page.currentPage;
              this.params.pageSize = dataTwo.page.rows;
              for (let i = 0; i < dataTwo.data.length; i++) {
                dataTwo.data[
                  i
                ].recordTime = this.commonFunction.getTimeByTimeStamp(
                  dataTwo.data[i].recordTime
                );
                // let fillIdData = dataTwo.data[i].fileId.substring(dataTwo.data[i].fileId.lastIndexOf('%'))
                // this.getImageByFileId(i, fillIdData)
                if (configInfo.isUseImageProxy) {
                  dataTwo.data[i].thumPicUrl =
                    configInfo.vcmImageUrl +
                    encodeURIComponent(dataTwo.data[i].thumPicUrl);
                  dataTwo.data[i].picUrl =
                    configInfo.vcmImageUrl +
                    encodeURIComponent(dataTwo.data[i].picUrl);
                } else {
                  dataTwo.data[i].thumPicUrl = encodeURIComponent(
                    dataTwo.data[i].thumPicUrl
                  );
                  dataTwo.data[i].picUrl = encodeURIComponent(
                    dataTwo.data[i].picUrl
                  );
                }
              }
            } else {
              this.messageTips("无符合条件数据");
            }
          }
        });
      } else {
        // 根据条件搜索
        let orgDate = {
          page: {
            sortOrder: "desc",
            pageSize: this.params.pageSize,
            currentPage: this.params.pageIndex,
          },
          condition: {
            startTime: startTimeInt,
            endTime: endTimeInt,
            deviceSource: "-1",
            ptype: this.valueCarPlateType,
            colorList: {
              color: this.valueCarColor,
            },
            cartype: this.valueVehicleType,
            specialVehicleTypeIdx: this.valueSpeScialVehicleTypeIdx,
            obscuredPlate: this.valueObscuredPlate,
            pcolor: this.valueCarPlateColor,
            pnr: this.carPlate,
            licensedVehicle: this.valueLicLensedVehicle,
          },
        };
        IntelligentAnalysis_getVehicleByParameter().then((dataTwo) => {
          // 根据特征获取车辆数据
          if (dataTwo) {
            if (dataTwo.data) {
              self.myInfo = dataTwo.data;
              this.params.total = dataTwo.page.total;
              this.params.pageIndex = dataTwo.page.currentPage;
              this.params.pageSize = dataTwo.page.rows;
              for (let i = 0; i < dataTwo.data.length; i++) {
                dataTwo.data[
                  i
                ].imageTimestamp = this.commonFunction.getTimeByTimeStamp(
                  dataTwo.data[i].imageTimestamp
                );
                // dataTwo.data[i].fileId.substring(dataTwo.data[i].fileId.lastIndexOf('%') + 1)
                // this.getImageByFileId(i, dataTwo.data[i].fileId)
                if (configInfo.isUseImageProxy) {
                  dataTwo.data[i].thumPicUrl =
                    configInfo.vcmImageUrl +
                    encodeURIComponent(dataTwo.data[i].thumPicUrl);
                  dataTwo.data[i].picUrl =
                    configInfo.vcmImageUrl +
                    encodeURIComponent(dataTwo.data[i].picUrl);
                } else {
                  dataTwo.data[i].thumPicUrl = encodeURIComponent(
                    dataTwo.data[i].thumPicUrl
                  );
                  dataTwo.data[i].picUrl = encodeURIComponent(
                    dataTwo.data[i].picUrl
                  );
                }
              }
            } else {
              this.messageTips("无符合条件数据");
            }
          }
        });
      }
    },
    getImageByFileId (index, fileId) {
      const self = this;
      GetVehicleImageBase64By(fileId).then(async (dataThree) => {
        // 根据fileid获取图片bit64
        let imageBasa64 = "data:image/png;base64," + dataThree;
        self.myInfo[index].imageUrl = imageBasa64;
        /* this.ImageFaceDateBit64 = "data:image/png;base64," + dataThree; */
      });
    },
    loadImage () {
      // 上传图片
      if (this.params.start_time) {
        if (this.params.end_time) {
          if (
            this.params.start_time.length === 0 ||
            this.params.end_time.length === 0
          ) {
            this.commonFunction.dialogSuccess("开始时间或结束时间为空！");
            return;
          }
          let imagedata = this.localFile.size;
          if (imagedata === undefined && this.secondSearchImageFile === "") {
            this.handleClick("");
          } else if (
            imagedata === undefined &&
            this.secondSearchImageFile !== ""
          ) {
            this.handleClick(this.secondSearchImageFile);
          } else if (
            imagedata !== undefined &&
            this.secondSearchImageFile === ""
          ) {
            let files = this.localFile;
            var form = new FormData();
            form.append("files", files);
            form.append("source_system_id", "0");
            form.append("type", "1");
            IntelligentAnalysis_uplodeImage(form).then((filedata) => {
              if (filedata.status == "500") {
                this.commonFunction.dialogSuccess(filedata.message);
              }
              // 上传图片
              let fileIdss = filedata.data;
              this.loadImageFile = fileIdss[0].casefile.casefileid;
              this.handleClick(this.loadImageFile);
            });
          }
        } else {
          this.commonFunction.dialogSuccess("开始时间或结束时间为空！");
        }
      } else {
        this.commonFunction.dialogSuccess("开始时间或结束时间为空！");
      }
    },
    exportPeopleFaces () {
      if (
        this.params.start_time.length === 0 ||
        this.params.end_time.length === 0
      ) {
        this.commonFunction.dialogSuccess("开始时间或结束时间为空！");
        return;
      }
      const startTimeInt = this.commonFunction.TimeToTimeStamp(
        this.params.start_time
      );
      const endTimeInt = this.commonFunction.TimeToTimeStamp(
        this.params.end_time
      );

      let imagedata = this.localFile.size;
      if (imagedata === undefined && this.secondSearchImageFile === "") {
        let orgCode = {
          page: {
            sortOrder: "desc",
            pageSize: "2",
            currentPage: this.params.pageIndex,
          },
          condition: {
            startTime: startTimeInt,
            endTime: endTimeInt,
            deviceSource: "-1",
            ptype: this.valueCarPlateType,
            colorList: {
              color: this.valueCarColor,
            },
            cartype: this.valueVehicleType,
            specialVehicleTypeIdx: this.valueSpeScialVehicleTypeIdx,
            obscuredPlate: this.valueObscuredPlate,
            pcolor: this.valueCarPlateColor,
            pnr: this.carPlate,
            licensedVehicle: this.valueLicLensedVehicle,
          },
        };

        this.loading = true;
        let _this = this;
        this.messageTips("正在导出，时间较长，请稍等。");
        let url =
          configInfo.DBHuaWeiVcmRestAPIEndPointUri +
          "intelligent_analysis/export_people_faces";
        // axios.post('http://10.16.123.166:10086/api/open/V1.0.0/vcm/intelligent_analysis/export_vehicle_for_feature', orgCode, { responseType: 'arraybuffer' }, { timeout: '50000' }).then(res => {
        axios
          .post(
            url,
            orgCode,
            { responseType: "arraybuffer" },
            { timeout: "500000" }
          )
          .then(
            (res) => {
              if (res) {
                _this.loading = false;
                const aLink = document.createElement("a");
                let blob = new Blob([res.data], {
                  type: "application/vnd.ms-excel",
                });
                aLink.href = URL.createObjectURL(blob);
                aLink.setAttribute(
                  "download",
                  "以图搜车导出结果" +
                  this.commonFunction.getNowTime() +
                  ".xlsx"
                ); // 设置下载文件名称
                aLink.click();
                this.$refs.loadElement.appendChild(aLink);
              }
            },
            (res) => {
              _this.$message.error(res.msg);
              _this.loading = false;
            }
          );
      } else if (imagedata === undefined && this.secondSearchImageFile !== "") {
        this.exportVehicleFaces(
          startTimeInt,
          endTimeInt,
          this.secondSearchImageFile
        );
      } else if (imagedata !== undefined && this.secondSearchImageFile === "") {
        this.exportVehicleFaces(this.loadImageFile);
      }
    },
    exportVehicleFaces (startTimeInt, endTimeInt, picturesObj) {
      let orgCode = {
        startTime: startTimeInt,
        endTime: endTimeInt,
        page: {
          no: this.params.pageIndex,
          size: "2",
          sort: "desc",
          orderName: "time",
        },
        confidenceThreshold: this.SimilaritySlidervalue1,
        picture: { fileId: picturesObj },
        searchType: "0",
      };
      this.loading = true;
      let _this = this;
      this.messageTips("正在导出，时间较长，请稍等。");
      let url =
        configInfo.DBHuaWeiVcmRestAPIEndPointUri +
        "intelligent_analysis/export_people_faces";
      // axios.post('http://10.16.123.166:10086/api/open/V1.0.0/vcm/intelligent_analysis/export_vehicle_for_feature', orgCode, { responseType: 'arraybuffer' }, { timeout: '50000' }).then(res => {
      axios
        .post(
          url,
          orgCode,
          { responseType: "arraybuffer" },
          { timeout: "500000" }
        )
        .then(
          (res) => {
            if (res) {
              _this.loading = false;
              const aLink = document.createElement("a");
              let blob = new Blob([res.data], {
                type: "application/vnd.ms-excel",
              });
              aLink.href = URL.createObjectURL(blob);
              aLink.setAttribute(
                "download",
                "以图搜车导出结果" + this.commonFunction.getNowTime() + ".xlsx"
              ); // 设置下载文件名称
              aLink.click();
              this.$refs.loadElement.appendChild(aLink);
            }
          },
          (res) => {
            _this.$message.error(res.msg);
            _this.loading = false;
          }
        );
    },
    messageTips (messageVule) {
      this.$message({
        message: messageVule,
        type: "warning",
        center: true,
      });
    },
    async secondSearch (secondSearchImagebase64) {
      // 二次查询
      let base64 = await this.commonFunction.GetImageBase64(
        secondSearchImagebase64
      );

      /* this.dialogImageUrl = secondSearchImagebase64
      var base64String = secondSearchImagebase64 */

      this.dialogImageUrl = base64;
      var base64String = base64;

      // 这里对base64串进行操作，去掉url头，并转换为byte
      var bytes = window.atob(base64String.split(",")[1]);

      // 处理异常，将ASCII码小于0的转换为大于0，这里有两种写法
      var ab = new ArrayBuffer(bytes.length);
      var ia = new Uint8Array(ab);
      for (var i = 0; i < bytes.length; i++) {
        ia[i] = bytes.charCodeAt(i); // 这里有点疑惑，ia是怎么改变ab的？注：①
      }
      // Blob对象
      var blob = new Blob([ab], { type: "image/jpeg" }); // type为图片的格式

      // FormData对象
      var fd = new FormData();

      // FormData对象接受三个参数，第三个参数为文件名，通常我们只传前两个参数，第三个参数不传则使用默认文件名，这里使用的Blob对象，所以需要一个文件名，用时间戳代替。
      fd.append("files", blob, Date.now() + ".jpg");
      fd.append("source_system_id", "0");
      fd.append("type", "1");

      // this.$store
      // .dispatch("IntelligentAnalysis_uplodeImage", fd)
      IntelligentAnalysis_uplodeImage(fd)
        .then((filedata) => {
          // 上传图片
          let fileIdss = filedata;
          this.secondSearchImageFile = fileIdss[0].casefile.casefileid;
          this.handleClick(this.secondSearchImageFile);
        });
    },
    getBase64 (imgDataUrl) {
      this.secondSearch(imgDataUrl);
    },
    ResetButtonClick () {
      // 重置
      this.params.start_time = this.commonFunction.addThreeS(
        this.commonFunction.getNowTime(),
        -604800
      );
      this.params.end_time = this.commonFunction.getNowTime();
      this.SimilaritySlidervalue1 = 80;
      this.dialogImageUrl = "";
      this.carPlate = "";
      this.valueVehicleType = "";
      this.valueCarPlateColor = "";
      this.valueCarColor = "";
      this.valueSpeScialVehicleTypeIdx = "";
      this.valueLicLensedVehicle = "";
      this.valueObscuredPlate = "";
      this.valueCarPlateType = "";
      this.localFile = {};
      this.secondSearchImageFile = "";
      /* let aa = this.optionsSpeScialVehicleTypeIdx */
    },
    jumpMap (dataInfo) {
      // 跳转地图
      let dataCode = dataInfo.cameraSn;
      if (dataCode !== "") {
        this.$router.push({
          path: "/GisView",
          name: "GisView",
          params: { cameraSN: dataCode },
        });
      }
    },
    showAlarmDetail (InfoDate) {
      this.dialogAlarmInfoVisible = true;
      this.divdialogVisible = true;
      this.FaceInfoData = InfoDate;
      this.ImageFaceDetailBit64 = InfoDate.picUrl;

      // 告警时间前后15秒 +设备code
      this.AlarmDetailParams.start_time = this.commonFunction.addThreeS(
        InfoDate.imageTimestamp,
        -15
      );
      this.AlarmDetailParams.end_time = this.commonFunction.addThreeS(
        InfoDate.imageTimestamp,
        15
      );
      this.playHistoryVideo(InfoDate.cameraSn);

      this.plateNumberDetails = InfoDate.lprInfo.plateNumber; // 详情中的车牌号
      this.domainNameDetails = InfoDate.domainName; // 详情中的域名称
      this.cameraNameDetails = InfoDate.cameraName; // 摄像机名称
      this.carTypeDetails = InfoDate.carType; // 车辆类型
      this.carColorDetails = InfoDate.carColor; // 车身颜色
      this.obscuredPlateDetails = InfoDate.obscuredPlate; // 车牌遮挡
      this.specialVehicleTypeIdxDetails = InfoDate.specialVehicleTypeIdx; // 特殊车辆

      this.plateTypeDetails = InfoDate.plateType; // 车牌类型
      this.domainCodeDetails = InfoDate.domainCode; // 域编码
      this.cameraSnDetails = InfoDate.cameraSn; //  摄像机编码
      this.yearDetails = InfoDate.year; // 年款
      this.plateColorDetails = InfoDate.plateColor; // 车牌颜色
      this.carDirectionDetails = InfoDate.carDirection; // 行驶方向
      this.headOutSunroofDetails = InfoDate.headOutSunroof; // 天窗站人
    },
    async playHistoryVideo (code) {
      let hls = await this.commonFunction.getHlsHistoryStreamByCameraCode(
        code,
        this.AlarmDetailParams.start_time,
        this.AlarmDetailParams.end_time
      );
      this.$refs.videoPlayer.playVideo(hls);
    },
    showAddClue (Infodata) {
      this.dialogAddClueVisible = true;
      this.divdialogVisible = true;
      let org = this.$store.getters.getCurrentOrg;
      let user = this.$store.getters.loginUser;
      if (org != null) {
        this.AddClueInfoData.orgName = org.name;
        this.AddClueInfoData.orgId = org.code;
        this.AddClueInfoData.orgTelephone = org.telephone;
      }
      if (user != null) {
        this.AddClueInfoData.creator = user.name;
        this.AddClueInfoData.creatorId = user.id;
        this.AddClueInfoData.creatorTelephone = user.mobileTelephone;
      }
      let entity = {
        pageIndex: this.clueTablePaginationParams.pageIndex,
        pageSize: this.clueTablePaginationParams.pageSize,
      };
      this.$store.dispatch("getClueList", entity).then(async (data) => {
        if (data != null) {
          this.clueTableData = data.data;
          this.clueTablePaginationParams.total = data.totalCount;
          // this.clueTablePaginationParams.pageIndex = data.totalPage
          // data.data.forEach(val => {
          // val.common - Info.alarm - time = this.commonFunction.getTime(val.commonInfo.alarmTime)
          // this.alarmData.push(val)
          // })
          // console.log(1)
        }
        // console.log(2)
      });
      this.AddclueAlarminfo = Infodata;
    },
    async showBigImg (cameraName, alarmTime, BigImgInfoData) {
      // 查看大图
      this.BigImgSrc = "";
      this.fullImagedialogVisible = true;
      this.divdialogVisible = true;
      this.BigImgSrc = BigImgInfoData;
      this.BigImgSrc = await this.commonFunction.GetImageBase64_AddWaterMarker(
        BigImgInfoData,
        cameraName,
        alarmTime
      );
    },
    closeAddClueDialogVisible () {
      this.dialogAddClueVisible = false;
      this.divdialogVisible = false;
    },
    handleClose (done) {
      this.$refs.videoPlayer.playVideo("");
      done();
    },
    getRowClass ({ row, rowIndex }) {
      return "PersonAlarmView_table_cellRow_New";
    },
    handleSelectionChange (val) {
      this.multipleSelection = val;
      this.$message("复选框选项变更时触发");
      // alert(val)
    },
    getHeaderRowClass ({ row, rowIndex }) {
      return "PersonAlarmView_table_HeaderRow_New";
    },
    trackDisplayFuction () {
      let trackData = [];
      if (this.myInfo.length > 0) {
        for (let i = 0; i < this.myInfo.length; i++) {
          let data = {
            cameraSN: this.myInfo[i].cameraSn, // 设备编号
            cameraName: this.myInfo[i].cameraName, // 设备名称
            cameraAddress: null, // 设备地址
            timeStamp: this.myInfo[i].imageTimestamp, // 时间 (时间戳转换之后的时间)
            thumPicUrl: this.myInfo[i].picUrl, // 图片
            platformType: 1, // 平台类型 (1.VCM 2.Roma)
          };
          trackData.push(data);
        }
        this.$router.push({
          path: "/GisView",
          name: "GisView",
          params: { track: trackData },
        });
      } else {
        this.commonFunction.dialogSuccess("无数据，未能跳转");
      }
    },
    showAlarmRelationClue (formName) {
      this.$refs[formName].validate((valid) => {
        if (this.nowSelectClueId === "") {
          this.commonFunction.dialogSuccess("请选择关联的线索");
          return;
        }
        this.getOriginaImage(this.AddclueAlarminfo.fileId, 2);
      });
    },
    // 获取原图缩略图
    async getOriginaImage (fileId, type) {
      /* let entity = {
        fileId: fileId,
        imageType: '1',
        imageSize: '2' // 1原图2缩略图
      }
      let entityBigImage = {
        fileId: fileId,
        imageType: '1',
        imageSize: '1' // 1原图2缩略图
      }
      let self = this
      this.$store.dispatch('AlarmManagement/AlarmManagement_getAlarmOriginalImage', entity).then(async data => {
        if (data.status === '200') {
          self.AlarmInfoData.blackSmallImage = data.data
        }
        this.$store.dispatch('AlarmManagement/AlarmManagement_getAlarmOriginalImage', entityBigImage).then(async data => {
          if (data.status === '200') {
            self.AlarmInfoData.blackBigImage = data.data
            if (type === 2) {
              await self.getPicsAndVideoModel(this.AddclueAlarminfo.alarmid, this.AddclueAlarminfo.picUrl, this.AddclueAlarminfo.thumPicUrl, this.AlarmInfoData.blackBigImage, this.AlarmInfoData.blackSmallImage)
            }
          }
        })
      }) */
      let self = this;
      if (type === 2) {
        await self.getPicsAndVideoModel(
          this.AddclueAlarminfo.alarmid,
          this.AddclueAlarminfo.picUrl,
          this.AddclueAlarminfo.thumPicUrl
        );
      }
    },
    async getPicsAndVideoModel (
      alarmId,
      snapBigImage,
      snapSmallImage,
      blackBigImage,
      blcakSmallImage
    ) {
      let picsAndVideoModel = null;

      let entity = await this.addFileInfoEntity(
        1,
        alarmId,
        snapBigImage,
        ".jpg"
      );
      let entity1 = await this.addFileInfoEntity(
        1,
        alarmId,
        snapSmallImage,
        ".jpg"
      );
      /* let entity2 = await this.addFileInfoEntity(1, alarmId, blackBigImage, '.jpg')
      let entity3 = await this.addFileInfoEntity(1, alarmId, blcakSmallImage, '.jpg') */
      let captureBigImageId = "";
      let captureThumImageId = "";
      /* let blackBigImageId = ''
      let blackThumImageId = '' */
      Promise.all([
        await this.$store
          .dispatch("FileInfo_AddFileInfo", entity)
          .then(async (data) => {
            if (data.status === "200") {
              captureBigImageId = data.data.content;
            }
          }),
        await this.$store
          .dispatch("FileInfo_AddFileInfo", entity1)
          .then(async (data) => {
            if (data.status === "200") {
              captureThumImageId = data.data.content;
            }
          }),
        /*  await this.$store.dispatch('FileInfo_AddFileInfo', entity2).then(async data => {
          if (data.status === '200') {
            blackBigImageId = data.data.fileId
          }
        }),
        await this.$store.dispatch('FileInfo_AddFileInfo', entity3).then(async data => {
          if (data.status === '200') {
            blackThumImageId = data.data.fileId
          }
        }) */
      ]).then(() => {
        picsAndVideoModel = {
          captureBigImageId: captureBigImageId,
          captureThumImageId: captureThumImageId,
          /* blackBigImageId: blackBigImageId,
          blackThumImageId: blackThumImageId */
        };
        let PicsAndVideoModelStr = JSON.stringify(picsAndVideoModel);
        this.AddAlarmRelationClue(PicsAndVideoModelStr);
      });
    },
    AddAlarmRelationClue (PicsAndVideoModel) {
      let self = this;
      /*  let alarmContent = this.alarmOriginaData.find(function (x) {
        return x.alarm.alarmid === self.AddclueAlarminfo.alarmid
      }) */
      let alarmContent = self.AddclueAlarminfo;
      let entity = {
        alarmId: this.AddclueAlarminfo.alarmid,
        cameraId: this.AddclueAlarminfo.cameraSn,
        alarmType: "20",
        alarmContent: JSON.stringify(alarmContent),
        picsAndVideo: PicsAndVideoModel,
        platformType: 1,
        contentType: 1,
        Address: "",
        longitude: "",
        latitude: "",
        createTime: "",
        confirmingPersonName: this.AddClueInfoData.creator,
        confirmingPersonId: this.AddClueInfoData.creatorId,
        confirmingTelephone: this.AddClueInfoData.creatorTelephone,
        confirmingOrgName: this.AddClueInfoData.orgName,
        confirmingOrgId: this.AddClueInfoData.orgId,
        confirmingOrgTelephone: this.AddClueInfoData.orgTelephone,
        note: this.AddClueInfoData.note,
        clueId: this.nowSelectClueId,
        id: self.commonFunction.Guid(),
      };
      this.closeAddClueDialogVisible();
      this.$store
        .dispatch("ClueManagement_AddAlarmRelationClue", entity)
        .then(async (data) => {
          if (data.status === "200") {
            this.commonFunction.dialogSuccess("添加成功！");
          }
        });
    },
    async addFileInfoEntity (fileType, fileName, content, suffix) {
      let contentData = await this.commonFunction.GetImageBase64(content);
      let contentDataInfo = contentData.substring(22);
      let id = this.commonFunction.Guid();
      let entity = {
        fileId: id,
        suffix: suffix,
        fileName: fileName + suffix,
        fileType: fileType,
        businessId: "",
        content: contentDataInfo,
      };

      return entity;
    },
    // 选择关联线索的row_id
    handleCurrentChange (val) {
      this.nowSelectClueId = val.id;
    },
    // url转换图片
    GetImageBase64_AddWaterMarker (
      url,
      text = "",
      font = "16px microsoft yahei",
      textColor = "rgba(255, 255, 255, 0.5)"
    ) {
      // store.getters.getCurrentOrg.code
      // let user = store.getters.loginUser;
      // let org = store.getters.getCurrentOrg;
      // text = user.name + " " + org.name + " " + user.personnelID;
      // if (url === "") {
      //   return;
      // }
      function getBase64Image (img) {
        // 渲染
        var _canvas = document.createElement("canvas");
        _canvas.width = img.width;
        _canvas.height = img.height;
        var context = _canvas.getContext("2d");
        context.drawImage(img, 0, 0, _canvas.width, _canvas.height);

        // 导出base64
        var base64 = _canvas.toDataURL("image/jpg");
        return base64;
      }
      return new Promise(function (resolve, reject) {
        var img = new Image();
        img.crossOrigin = "Anonymous"; // 解决canvas.toDataURL图片跨域问题
        img.onload = function () {
          resolve(getBase64Image(img));
        };
        img.src = url;
      });
    },
  },
  mounted () {
    this.loadImage();
    let jumpInfoImgbit64 = this.$route.params.imageBaseData;
    if (jumpInfoImgbit64 === "null" || jumpInfoImgbit64 === "undefined") {
    } else {
      if (jumpInfoImgbit64) {
        this.dialogImageUrl = jumpInfoImgbit64;
        this.dialogImageUrlLength = jumpInfoImgbit64.length;
        this.handleClick(jumpInfoImgbit64);
      } else {
        let imgBase64 = this.GetImageBase64_AddWaterMarker(
          "",
          "",
          jumpInfoImgbit64
        );
        this.handleClick(imgBase64);
      }
      /* if (jumpInfoImgbit64.length !== 0 && jumpInfoImgbit64.length > 500) {
        this.handleClick(jumpInfoImgbit64)
      } else {
        let imgBase64 = this.GetImageBase64_AddWaterMarker(jumpInfoImgbit64)
        this.handleClick(imgBase64)
      } */
    }
  },
};
</script>
<style lang="scss" scoped>
#ImageFaceTwo {
  width: 100%;
  height: 100%;
  margin-left: 0;
  margin-top: 0;
}
#ImageFaceDate {
  width: 98%;
  height: 98%;
  margin-left: 1.5%;
  margin-top: 1%;
}
#el_row_1_span_ {
  float: left;
  margin-left: 10px;
  color: #005982;
}
#el_row_1_span {
  float: right;
  color: #005982;
}
#el_row_1 {
  width: 100%;
  line-height: 35px;
  height: 14%;
}
#el-col-11 {
  width: 66%;
  height: 87%;
  margin-top: 20px;
}
#el_col_1 {
  width: 20%;
  height: 100%;
}
#el_col_2 {
  width: 30%;
  height: 100%;
}
#el_col_3 {
  width: 20%;
  height: 100%;
}
#el_col_4 {
  width: 30%;
  height: 100%;
}
#iconIconfontIconfontColor {
  margin-left: 20px;
}
#iconIconfontIconfontColor:hover {
  color: #00ffff;
}
#ChildSearchVehicleByImageView-row01-label {
  color: #005982;
  font-size: 14px;
  line-height: 35px;
  float: right;
}
#ChildSearchVehicleByImageView-row02 {
  height: 35px;
  width: 100%;
}
#ChildSearchVehicleByImageView-row01 {
  height: 35px;
  width: 100%;
}
#ChildSearchVehicleByImageView-Left-Bottom-ConditionQueryBox-label {
  height: 280px;
  width: 29%;
  margin-top: 6px;
}
#ChildSearchVehicleByImageView-Left-Bottom-ConditionQueryBox-selectBox {
  height: 280px;
  width: 65%;
  margin-left: 3px;
  margin-top: 6px;
}
#ChildSearchVehicleByImageView-Left-Bottom-ConditionQueryBox {
  border: 0.5px solid #005982;
  border-radius: 4px;
  height: 288px;
  width: 80%;
  margin-left: 10%;
  margin-top: 7px;
}
#ChildSearchVehicleByImageView-Left-Bottom-chooseImg {
  border: 0.5px solid #005982;
  border-radius: 4px;
  height: 105px;
  width: 80%;
  margin-left: 10%;
}
#alarmInfoDiaolg_Span {
  color: #005982;
  float: left;
}
.el_form_itemDiv {
  display: flex !important;
  width: 100%;
}
.addCluefoDiaolg_input {
  width: 200px;
}
.alarmAddClueDev {
  width: 104%;
  height: 520px;
}
.queryDateName {
  color: #005982;
  margin-left: 20px;
}
#iconfont1 {
  margin-left: 10px;
  color: #0b86a3;
}
#iconfont2 {
  margin-left: 10px;
  color: #0b86a3;
}
.el-col-10 {
  width: 32.66667%;
}
#dialogChildSearchFaceDetailImageDate {
  width: 210px;
  height: 160px;
}
#dialogChildSearchFaceDetailImage {
  width: 100%;
  height: 180px;
  margin-top: 20%;
  background: url("../../../../assets/images/IntelligentAnalysis/imageBackground.png")
    no-repeat center center / 75% 90%;
}
.el-form-item {
  margin-bottom: 0px !important;
}
#dialogChildSearchFaceDetail {
  margin-top: 20px;
  border: 0.5px solid #005982;

  border-radius: 2px;
  height: 290px;
}
#dialogplayChildSearchFace {
  border: 0.5px solid #005982;
  text-align: left;

  border-radius: 2px;
  margin-top: 10px;
}
#ChildSearchFaceVideo {
  width: 100%;
  height: 300px;
}

#dialogplayChildSearchFaceRow {
  height: 40px;
  line-height: 40px;
}

// 告警弹框详情
.ChildSearchFaceByImageDetailDev {
  width: 104%;
  height: 620px;
}

#ChildSearchVehicleByImageView-Right-Bottom-Camera {
  width: 97%;
  height: 38px;
  float: left;
  line-height: 38px;
  color: #005982;
  margin-left: 6px;
  font-size: 14px;
}
#ChildSearchVehicleByImageView-Right-Bottom-CameraCode {
  width: 97%;
  height: 38px;
  float: left;
  line-height: 38px;
  color: #005982;
  margin-left: 6px;
  font-size: 14px;
}
#ChildSearchVehicleByImageView-Right-Bottom-Time {
  width: 97%;
  height: 38px;
  float: left;
  line-height: 38px;
  color: #005982;
  margin-left: 6px;
  font-size: 14px;
}
#ChildSearchVehicleByImageView-Right-Central-Out {
  width: 220px;
  height: 320px;
  border: 0.5px solid #005982;
  float: left;
}
#ChildSearchVehicleByImageView-Right-Central-inside {
  width: 92%;
  height: 97%;
  margin-left: 3.8%;
  margin-top: 1.5%;
  border: 0.5px solid #005982;
  border-radius: 4px;
}
#ChildSearchVehicleByImageView-Right-Central-Out:hover {
  border: 0.5px solid #00ffff;
}
#ChildSearchVehicleByImageView-Right-Central-inside-Label {
  width: 100%;
  height: 80px;
  color: #005982;
  font-size: 14px;
  margin-top: 8px;
}
#ChildSearchVehicleByImageView-Right-Central-inside-three-Label {
  color: #005982;
  font-size: 14px;
  line-height: 30px;
  margin-left: 10px;
}
#ChildSearchVehicleByImageView-Right-Central-inside-three-xiangqing {
  margin-top: 5px;
  width: 20px;
  margin-right: 9px;
  height: 20px;
  float: right;
  border: none;
}
#ChildSearchVehicleByImageView-Right-Central-inside-three-xiangqing:hover {
  border: 0.5px solid #00ffff;
}
#ChildSearchVehicleByImageView-Right-Central-inside-three-dingwei:hover {
  border: 0.5px solid #00ffff;
}
#ChildSearchVehicleByImageView-Right-Central-inside-three-xiansuoxinxi:hover {
  border: 0.5px solid #00ffff;
}
#ChildSearchVehicleByImageView-Right-Central-inside-three-renlianchaxun:hover {
  border: 0.5px solid #00ffff;
}

#ChildSearchVehicleByImageView-Right-Central-inside-three-dingwei {
  margin-top: 5px;
  width: 20px;
  margin-right: 5px;
  height: 20px;
  float: right;
  border: none;
}
#ChildSearchVehicleByImageView-Right-Central-inside-three-xiansuoxinxi {
  margin-top: 5px;
  width: 20px;
  margin-right: 5px;
  height: 20px;
  float: right;
  border: none;
}
#ChildSearchVehicleByImageView-Right-Central-inside-three-renlianchaxun {
  margin-right: 5px;
  margin-top: 5px;
  width: 20px;
  height: 20px;
  float: right;
  border: none;
}
#ChildSearchVehicleByImageView-Right-Central-inside-One {
  width: 100%;
  height: 30px;
  text-align: center;
  margin-top: 8px;
}
#ChildSearchVehicleByImageView-Right-Central-inside-two {
  width: 96.8%;
  height: 120px;
  margin-left: 1.5%;
  border: 0.5px solid #005982;
  background: url("../../../../assets/images/IntelligentAnalysis/imageBackground.png")
    no-repeat center center / 80% 100%;
}
#ChildSearchVehicleByImageView-Right-Central-inside-three {
  width: 99.8%;
  height: 30px;
  margin-top: 3px;
  border: 0.5px solid #005982;
}
#ChildSearchVehicleByImageView-Right-Central-inside-four {
  width: 99.8%;
  height: 115px;
  /* border: 0.5px solid red; */
}
#ImageFaceDate {
  width: 98%;
  height: 98%;
  margin-left: 1.5%;
  margin-top: 1%;
}
#PictureBox {
  width: 100%;
  height: 180px;
}
#ImageDiv {
  width: 45%;
  height: 140px;
  padding: 1px;
  margin-left: 27.5%;
  margin-top: 30px;
  // background: url("../../../../assets/images/IntelligentAnalysis/imageBackground.png")
  //   no-repeat center center / 100% 100%;
}
#ImageFace {
  width: 100%;
  height: 140px;
}
#ChildSearchVehicleByImageView-Left {
  width: 24.5%;
  height: 100%;
  border: 0.5px solid #005982;
  margin-top: 3px;
  border-radius: 4px;
}
#ChildSearchVehicleByImageView-Right {
  width: 75%;
  height: 100%;
  /* border: 1px solid #11bbf3;
  margin-top: 3px;
  margin-left: 4px;
  border-radius: 2px; */
}
#PictureRetrievalText {
  width: 99.7%;
  height: 30px;
  /* margin-left: 0.5px;
  margin-top: 0.5px; */
  border: 0.5px solid #005982;
  border-radius: 4px;
  font-size: 20px !important;
  color: "#11bbf3";
  padding-top: 6px;
  border-left: none;
  border-top: none;
  border-right: none;
}
#PictureRetrievalLabel {
  margin-top: 5px;
  color: #00ffff;
  font-size: 18px;
  margin-left: 20px;
}
#ChildSearchVehicleByImageView-Right-Top {
  width: 100%;
  height: 35px;
  border: 0.5px solid #005982;
  margin-top: 3px;
  margin-left: 4px;
  border-radius: 2px;
}
#ChildSearchVehicleByImageView-Right-Central {
  width: 100%;
  height: 89.5%;
  border: 0.5px solid #005982;
  margin-top: 3px;
  margin-left: 4px;
  border-radius: 2px;
}
#ChildSearchVehicleByImageView-Right-Bottom {
  width: 100%;
  height: 38px;
  border: 0.5px solid #005982;
  margin-top: 5px;
  margin-left: 4px;
  border-radius: 2px;
  overflow: hidden;
}
#ChildSearchVehicleByImageView-Left-Bottom {
  width: 100%;
  height: 80%;
  position: relative;
}
#ChoosePictureButton {
  // width: 250px;
  height: 30px;
  margin-top: 10px;
  margin-left: 14px;
  padding: 5px;
}
#ChildSearchVehicleByImageView-Left-Bottom-ConditionBox {
  font-size: 14px;
  width: 80%;
  // height: 80px;
  margin-left: 10%;
  border: 0.5px solid #005982;
  margin-top: 75px;
  border-radius: 4px;
}
.el-input__inner {
  width: 40px !important;
  height: 30px !important;
}
#Retrieve-Reset-Button {
  width: 80%;
  height: 30px;
  margin-left: 10%;
  margin-top: 10px;
}
#RetrieveButton {
  width: 30%;
  height: 30px;
  margin-left: 17%;
  padding: 5px;
}
#ResetButton {
  width: 30%;
  height: 30px;
  padding: 5px;
}
#FaceSet {
  color: #00ffff;
  float: left;
  width: 100px;
  height: 32px;
  line-height: 32px;
}
#FaceSetLabel {
  margin-top: 10px;
  font-size: 18px;
  margin-left: 10px;
}
#TrackDisplay {
  width: 80px;
  height: 30px;
  float: right;
  margin-right: 10px;
  margin-top: 2.5px;
  padding: 5px;
}
#Export {
  width: 80px;
  height: 30px;
  margin-top: 2.5px;
  margin-right: 10px;
  float: right;
  padding: 5px;
}
#StartTime {
  display: flex;
  width: 100%;
  height: 50px;
  margin-top: -1px;
}
#EndTime {
  display: flex;
  width: 100%;
  height: 50px;
  // margin-top: -14px;
  // float: left;
}
#Similarity {
  width: 100%;
  height: 50px;
  margin-left: 5px;
  flex-direction: row;
  display: flex;
}
#StartTimeLabel {
  color: #005982;
  margin-left: 5%;
  line-height: 45px;
  font-size: 14px;
  // float: left;
}
#EndTimeLable {
  height: 40px;
  color: #005982;
  line-height: 45px;
  font-size: 14px;
  margin-left: 5%;
  // float: left;
  // margin-left: 29px;
}
#ChooseStarTimeBox {
  margin-left: 20px;
  margin-top: 5px;
}
#ChooseEndTimeBox {
  margin-left: 20px;
  margin-top: 5px;
  // margin-left: 110px;
  // width: 180px;
  // height: 30px;
  // margin-top: -37px;
  // float: left;
}
#SimilarityLable {
  width: 100px;
  height: 40px;
  color: #005982;
  line-height: 45px;
  font-size: 14px;
}
#SimilaritySlider {
  width: 165px;
  height: 40px;
}
#SimilarityScaleValue1 {
  color: #005982;
  margin-left: -3px;
  font-size: 14px;
}
#SimilarityScaleValue2 {
  color: #005982;
  margin-left: 16px;
  font-size: 14px;
}
#SimilarityScaleValue3 {
  color: #005982;
  margin-left: 22px;
  font-size: 14px;
}
#SimilarityScaleValue4 {
  color: #005982;
  margin-left: 22px;
  font-size: 14px;
}
#SimilarityScaleValue5 {
  color: #005982;
  margin-left: 12px;
  font-size: 14px;
}
.outer-container {
  width: 100%;
  height: 100%;
  position: relative;
  overflow: hidden;
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
#Pagination {
  margin-top: 4px;
  /* border: 0.5px solid #005982; */
  height: 40px;
}
#Pagination {
  width: 100%;
  text-align: right;
}
.addCluefoDiaolg_input {
  width: 200px;
}
.bottomButton {
  display: inline-block;
  margin-top: 20px;
}
</style>
<style>
#ChildSearchFaceVideo .vjs-custom-skin > .video-js {
  width: 100%;
  height: 100%;
}
.el-message--warning {
  background-color: #04283f;
  border-color: #faecd8;
}
#addCluefoDiaolg .el-form-item .el-form-item__content {
  float: left !important;
}
#addCluefoDiaolg .el-form-item .el-form-item__label {
  width: 80px;
}
#ChildSearchVehicleByImageView-Left-Bottom-ConditionBox
  .el-input--prefix
  .el-input__inner {
  /* width: 180px !important; */
  height: 30px;
  margin-left: -8px;
}
#ChildSearchVehicleByImageView-Left-Bottom-ConditionBox
  .el-input--prefix
  .el-input__icon {
  height: 100%;
  width: 25px;
  text-align: center;
  -webkit-transition: all 0.3s;
  transition: all 0.3s;
  line-height: 30px;
  margin-left: -10px;
}
</style>

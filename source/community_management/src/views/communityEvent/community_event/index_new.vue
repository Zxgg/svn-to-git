<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="社区名称" prop="communityId">
        <el-select
          v-model="queryParams.communityId"
          filterable
          clearable
          @change="getCommunityChildDictory"
          placeholder="请选择社区"
        >
          <el-option
            v-for="dict in communityOptions"
            :key="dict.id"
            :label="dict.name"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="小区名称" prop="communityChildId">
        <el-select
          v-model="queryParams.communityChildId"
          filterable
          clearable
          placeholder="请选择小区"
        >
          <el-option
            v-for="dict in communityChildOptions"
            :key="dict.id"
            :label="dict.name"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="事件类型" prop="type">
        <!-- <el-select
          v-model="queryParams.eventType"
          placeholder="请选择事件类型"
          clearable
          size="small"
        >
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select> -->
        <treeselect
          v-model="queryParams.eventType"
          :options="eventTypeOptions"
          :disable-branch-nodes="true"
          :show-count="true"
          placeholder="请选择事件类型"
          :searchable="true"
          size="mini"
        />
      </el-form-item>
      <!-- <el-form-item label="告警层级"
                    prop="eventDegree">
        <el-select v-model="queryParams.eventDegree"
                   placeholder="请选择告警层级"
                   clearable
                   size="small">
          <el-option v-for="dict in eventDegreeOptions"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="是否处理"
                    prop="processed">
        <el-input v-model="queryParams.processed"
                  placeholder="请输入是否处理"
                  clearable
                  size="small"
                  @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="eventEntry"
          >事件录入</el-button
        >
        <!-- <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   v-if="false"
                   @click="reportIncident"
                   v-hasPermi="['communityEvent:community_event:Incident']">上报</el-button> -->
        <!-- <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   v-if="false"
                   :disabled="single"
                   @click="handleAssignment"
                   v-hasPermi="['communityEvent:community_event:edit']">修改</el-button> -->
        <!-- <el-button icon="el-icon-refresh"
                   size="mini"
                   @click="resetQuery">重置</el-button> -->
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10"
            class="mb8">
      <el-col :span="1.5">
        <el-button type="primary"
                   icon="el-icon-plus"
                   size="mini"
                   v-if="false"
                   @click="reportIncident"
                   v-hasPermi="['communityEvent:community_event:Incident']">上报</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success"
                   icon="el-icon-edit"
                   size="mini"
                   v-if="false"
                   :disabled="single"
                   @click="handleAssignment"
                   v-hasPermi="['communityEvent:community_event:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger"
                   icon="el-icon-delete"
                   size="mini"
                   :disabled="multiple"
                   @click="handleDel"
                   v-hasPermi="['communityEvent:community_event:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning"
                   icon="el-icon-download"
                   size="mini"
                   @click="handleExport"
                   v-hasPermi="['communityEvent:community_event:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch"
                     @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table
      v-loading="loading"
      stripe
      :height="dataHeight"
      :data="community_eventList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="社区"
        align="center"
        prop="communityId"
        :formatter="communityNameFormat"
      />
      <el-table-column
        label="小区"
        align="center"
        prop="communityChildId"
        :formatter="communityChildNameFormat"
      />
      <el-table-column label="名称" align="center" prop="eventName" />
      <el-table-column
        label="事件类型"
        align="center"
        prop="eventType"
        :formatter="typeFormat"
      />
      <el-table-column
        label="事件程度"
        align="center"
        prop="eventDegree"
        :formatter="eventDegreeFormat"
      />
      <el-table-column label="上报时间" align="center" prop="reportingTime" />

      <!-- <el-table-column label="关联人员"
                       align="center"
                       prop="personid"
                       :formatter="voFormat" />
      <el-table-column label="车辆ID"
                       align="center"
                       prop="vehicleid"
                       :formatter="voFormat" /> -->
      <!-- <el-table-column label="事件具体内容"`
                       align="center"
                       prop="content" /> -->
      <el-table-column label="处理进度" align="center" prop="eventStatus">
        <template slot-scope="scope">
          <span>
            <el-progress
              :percentage="
                scope.row.eventStatus === '0'
                  ? 0
                  : parseInt(scope.row.eventStatus) /
                    ((eventStatusOptionsLength - 1) / 100)
              "
              :show-text="false"
              :color="customColorMethod"
            >
            </el-progress>
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="状态"
        align="center"
        prop="eventStatus"
        :formatter="eventStatusListFormat"
      />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            v-if="scope.row.eventStatus === '0'"
            @click="handleAcceptance(scope.row)"
            v-hasPermi="['communityEvent:community_event:Acceptance']"
            >受理</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            v-if="scope.row.eventStatus === '1'"
            @click="handleAssignment(scope.row)"
            v-hasPermi="['communityEvent:community_event:Assignment']"
            >分派</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            v-if="scope.row.eventStatus === '2'"
            @click="handleDisposal(scope.row)"
            v-hasPermi="['communityEvent:community_event:Disposal']"
            >办结</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            v-if="scope.row.eventStatus === '3'"
            @click="handleReview(scope.row)"
            v-hasPermi="['communityEvent:community_event:Review']"
            >归档</el-button
          >
          <!-- <el-button type="text"
                     icon="el-icon-plus"
                     size="mini"
                     @click="reportIncident(scope.row)"
                     v-hasPermi="['communityEvent:community_event:Incident']">上报</el-button> -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-info"
            @click="handleInfo(scope.row)"
            >详情</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDel(scope.row)"
            v-hasPermi="['communityEvent:community_event:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改社区事件 对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="1020px"
      append-to-body
    >
      <!-- 信息展示 -->
      <el-row :gutter="20" v-if="rowInfoShow">
        <el-form :model="formInfo" label-width="80px">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="社区名称:" prop="communityChildId">
                <!-- <div>{{formInfo.communityId}}</div> -->
                <el-select
                  v-model="formInfo.communityId"
                  filterable
                  clearable
                  :disabled="true"
                  @change="getCommunityChildDictory2"
                  placeholder="请选择社区"
                >
                  <el-option
                    v-for="dict in communityOptions"
                    :key="dict.id"
                    :label="dict.name"
                    :value="dict.id"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="小区名称:" prop="communityChildId">
                <el-select
                  v-model="formInfo.communityChildId"
                  filterable
                  clearable
                  :disabled="true"
                  placeholder="请选择小区"
                >
                  <el-option
                    v-for="dict in communityChildOptions2"
                    :key="dict.id"
                    :label="dict.name"
                    :value="dict.id"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="24">
              <el-form-item label="事件名称:" prop="eventName">
                <div>{{ formInfo.eventName }}</div>
                <!-- <el-input
                  v-model="formInfo.eventName"
                  type="text"
                  :readonly="true"
                  placeholder="请输入内容"
                /> -->
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="事件类型:" prop="eventType">
                <treeselect
                  v-model="formInfo.eventType"
                  disabled
                  :options="eventTypeOptions"
                  :disable-branch-nodes="true"
                  :show-count="true"
                  placeholder="请选择事件类型"
                  :searchable="true"
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="事件程度:" prop="eventDegree">
                <el-select
                  v-model="formInfo.eventDegree"
                  :disabled="true"
                  placeholder="请选择事件程度"
                >
                  <el-option
                    v-for="dict in eventDegreeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="24">
              <el-form-item label="现场照片:" prop="fileId">
                <el-col v-for="pic in formInfo.fileId" :span="4">
                  <el-image
                    v-if="pic"
                    style="width: 100px; height: 80px"
                    :src="pic"
                  ></el-image>
                  <div v-else>无图</div>
                </el-col>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="24">
              <el-form-item label="事件内容:" prop="content">
                <div>{{ formInfo.content }}</div>
                <!-- <el-input
                  v-model="formInfo.content"
                  type="textarea"
                  readonly
                  placeholder="请输入内容"
                /> -->
              </el-form-item>
            </el-col>
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="上报人:" prop="reportBy">
                  <div>{{ formInfo.reportBy || "暂无"}}</div>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item
                  label="上报人手机号:"
                  label-width="100px"
                  prop="createByPhone"
                >
                  <div>{{ formInfo.createByPhone || "暂无"}}</div>
                </el-form-item>
              </el-col>
            </el-row>
          </el-row>
        </el-form>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="formSpan" v-if="fromShow">
          <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-card class="box-card">
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="社区名称:" prop="communityChildId">
                    <el-select
                      v-model="form.communityId"
                      filterable
                      clearable
                      :disabled="true"
                      @change="getCommunityChildDictory2"
                      placeholder="请选择社区"
                    >
                      <el-option
                        v-for="dict in communityOptions"
                        :key="dict.id"
                        :label="dict.name"
                        :value="dict.id"
                      />
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="小区名称:" prop="communityChildId">
                    <el-select
                      v-model="form.communityChildId"
                      filterable
                      clearable
                      :disabled="true"
                      placeholder="请选择小区"
                    >
                      <el-option
                        v-for="dict in communityChildOptions2"
                        :key="dict.id"
                        :label="dict.name"
                        :value="dict.id"
                      />
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="24">
                  <el-form-item label="事件名称:" prop="eventName">
                    <el-input
                      v-model="form.eventName"
                      type="text"
                      :readonly="true"
                      placeholder="请输入内容"
                    />
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="事件类型:" prop="eventType">
                    <treeselect
                      v-model="form.eventType"
                      disabled
                      :options="eventTypeOptions"
                      :disable-branch-nodes="true"
                      :show-count="true"
                      placeholder="请选择事件类型"
                      :searchable="true"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="事件程度:" prop="eventDegree">
                    <el-select
                      v-model="form.eventDegree"
                      :disabled="true"
                      placeholder="请选择事件程度"
                    >
                      <el-option
                        v-for="dict in eventDegreeOptions"
                        :key="dict.dictValue"
                        :label="dict.dictLabel"
                        :value="dict.dictValue"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="24">
                  <el-form-item label="现场照片:" prop="fileId">
                    <el-col v-for="pic in form.eventInfoFileId" :span="4">
                      <el-image
                        v-if="pic"
                        style="width: 100px; height: 80px"
                        :src="pic"
                      ></el-image>
                      <div v-else>无图</div>
                    </el-col>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="24">
                  <el-form-item label="事件内容:" prop="eventContent">
                    <div>{{ form.eventContent }}</div>
                    <!-- <el-input
                      v-model="form.eventContent"
                      type="textarea"
                      readonly
                      placeholder="请输入内容"
                    /> -->
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="上报人:" prop="reportBy">
                    <div>{{ form.reportBy || "暂无" }}</div>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                    <div>{{ form.createByPhone || "暂无" }}</div>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-card>
            <!-- 办结和归档的显示 -->
            <el-card class="box-card" v-if="evemtHandleShow || imageUploadShow">
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item
                    :label="eventStatusLabel"
                    v-if="evemtHandleShow"
                    prop="handleBy"
                  >
                    <el-select
                      v-model="form.handleBy"
                      filterable
                      clearable
                      :disabled="isHandle"
                      :placeholder="eventStatusPH"
                    >
                      <el-option
                        v-for="dict in HandleByList"
                        :key="dict.id"
                        :label="dict.nickName"
                        :value="dict.id"
                      />
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="24">
                  <el-form-item
                    label="整改后图片:"
                    v-if="imageUploadShow"
                    label-width="90px"
                    prop="alertFile"
                  >
                    <el-col
                      v-for="pic in form.fileId"
                      :span="4"
                      v-show="form.eventStatus == '3' && '4'"
                    >
                      <el-image
                        v-if="pic"
                        style="width: 100px; height: 80px"
                        :src="pic"
                      ></el-image>
                      <div v-else>无图</div>
                    </el-col>
                    <imageListUpload
                      :upLoadUrl="fileUpLoadUrl"
                      :key="form.id"
                      :uploadState="uploadState"
                      :filesJson.sync="form.alertFile"
                      v-show="form.eventStatus == '2'"
                      :isDisabled="form.eventStatus == '3'"
                    />
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="24">
                  <el-form-item
                    :label="contentLabel+':'"
                    prop="content"
                    v-if="contenShow"
                  >
                    <el-input
                      v-model="form.content"
                      type="textarea"
                      placeholder="请输入内容"
                    />
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="24">
                  <el-form-item
                    label="回退原因:"
                    prop="remarks"
                    v-if="form.isback == '1'"
                  >
                    <el-input
                      v-model="form.remarks"
                      type="textarea"
                      readonly
                      placeholder="请输入内容"
                    />
                  </el-form-item>
                </el-col>
              </el-row>
            </el-card>
          </el-form>
        </el-col>
        <el-col :span="infoSpan" v-if="infoShow">
          <el-timeline>
            <el-timeline-item
              v-for="(item, index) in recordList"
              :key="index"
              :timestamp="item.createTime"
              placement="top"
              class="infinite-list-item"
            >
              <el-card style="width:750px">
                <el-form :model="item" :rules="rules" label-width="100px">
                  <el-row :gutter="20">
                    <el-form-item label="状态:" prop="eventStatus">
                      {{ eventStatusFormat(item) }}
                    </el-form-item>
                  </el-row>
                  <!-- <el-row :gutter="20">
                    <el-col :span="12">
                      <el-form-item label="上报人"
                                    prop="reportBy">
                        <el-input v-model="item.reportBy"
                                  type="text"
                                  placeholder="请输入内容" />
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="上报时间"
                                    prop="createTime">
                        <el-date-picker clearable
                                        size="small"
                                        style="width: 200px"
                                        v-model="item.createTime"
                                        type="datetime"
                                        value-format="yyyy-MM-dd h:m"></el-date-picker>
                      </el-form-item>
                    </el-col>

                  </el-row> -->
                  <!-- <el-row>
                    <el-form-item label="现场照片"
                                  prop="eventInfoFileId"
                                  v-if="item.eventStatusSon == '1'">
                      <el-col v-for="pic in eventInfoFileId"
                              :span="4">
                        <el-image v-if="pic"
                                  style="width: 100px; height: 80px"
                                  :src="pic"
                                  :preview-src-list="eventInfoFileId"></el-image>
                        <div v-else>无图</div>
                      </el-col>
                    </el-form-item>
                  </el-row> -->
                  <el-row :gutter="20">
                    <!-- <el-form-item label="整改后图片"
                                  prop="fileId"
                                  v-if="item.eventStatus == '3'"
                                  label-width="200">
                      <el-col v-for="pic in from.fileId"
                              :span="4">
                        <el-image v-if="pic"
                                  style="width: 100px; height: 80px"
                                  :src="pic.url"
                                  :preview-src-list="fileId"></el-image>
                        <div v-else>无图</div>
                      </el-col>
                    </el-form-item> -->
                    <el-form-item
                      label="整改后图片:"
                      v-if="item.eventStatus == '3' && '4'"
                      label-width="100px"
                      prop="alertFile"
                    >
                      <!-- <imageListUpload
                        :upLoadUrl="fileUpLoadUrl"
                        :key="item.id"
                        :uploadState="uploadState"
                        :filesJson.sync="formInfo.fileId"
                        :isDisabled="item.eventStatus == '3'"
                      /> -->
                      <el-col
                        v-for="pic in formInfo.alertFile"
                        :span="4"
                        v-show="formInfo.eventStatus == '3'"
                      >
                        <el-image
                          v-if="pic"
                          style="width: 100px; height: 80px"
                          :src="pic"
                        ></el-image>
                        <div v-else>无图</div>
                      </el-col>
                    </el-form-item>
                  </el-row>
                  <el-row :gutter="20">
                    <el-form-item
                      label="办结人:"
                      v-if="item.eventStatus == '2'"
                      prop="handleBy"
                      label-width="100px"
                    >
                      <div>{{ item.handleBy || "暂无" }}</div>
                      <!-- <el-input v-model="item.handleBy" placeholder="办结人" /> -->
                      <!-- <el-select v-model="item.handleBy"
                                 filterable
                                 clearable
                                 :disabled="true"
                                 :placeholder="eventStatusPH">
                        <el-option v-for="dict in HandleByList"
                                   :key="dict.id"
                                   :label="dict.nickName"
                                   :value="dict.id" />
                      </el-select> -->
                    </el-form-item>
                  </el-row>

                  <el-row
                    :gutter="20"
                    v-if="item.eventStatus == '3' || item.eventStatus == '4'"
                  >
                    <el-form-item
                      :label="item.eventStatus == 3 ? '办结描述:' : '归档意见:'"
                      prop="content"
                      label-width="100px"
                    >
                      <div>{{ item.content || "暂无" }}</div>
                      <!-- <el-input
                        v-model="item.content"
                        type="textarea"
                        autosize
                        placeholder="请输入内容"
                      /> -->
                    </el-form-item>
                  </el-row>

                  <el-form-item
                    label="回退原因:"
                    prop="remarks"
                    v-if="item.isback == '1'"
                  >
                    <el-input
                      v-model="item.remarks"
                      type="textarea"
                      placeholder="请输入内容"
                    />
                  </el-form-item>
                </el-form>
              </el-card>
            </el-timeline-item>
          </el-timeline>
        </el-col>
      </el-row>

      <div slot="footer" v-if="buttonShow" class="dialog-footer">
        <!-- <el-button @click="turnDown"
                   v-if="turnDownShow"
                   type="primary"
                   :disabled="isDisabled">驳 回</el-button> -->
        <el-button type="primary" @click="submitForm">{{
          buttonName
        }}</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 事件录入的模态框 -->
    <el-dialog
      title="事件录入"
      :visible.sync="openReport"
      width="1200px"
      append-to-body
    >
      <el-form
        ref="reportForm"
        :model="reportForm"
        :rules="rules"
        label-width="150px"
        size="medium"
      >
        <el-row :gutter="20">
          <!-- <el-col :span="12">
          <el-select placeholder="请选择上报平台" v-model="reportForm">
            <el-option label="全事件指挥平台" value="shanghai"></el-option>
          </el-select>
        </el-col> -->
          <el-col :span="12">
            <el-form-item label="社区名称:" prop="communityChildId">
              <el-select
                v-model="reportForm.communityId"
                filterable
                clearable
                @change="getCommunityChildDictory2"
                placeholder="请选择社区"
              >
                <el-option
                  v-for="dict in communityOptions"
                  :key="dict.id"
                  :label="dict.name"
                  :value="dict.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              label="行政区划:"
              prop="division"
              :rules="[
                { required: true, message: '请输入行政区划名称', trigger: 'blur' }
              ]"
            >
              <el-input
                v-model="reportForm.division"
                placeholder="请输入行政区划"
              />
            </el-form-item>
          </el-col>
          <!-- <el-col :span="12">
            <el-form-item
              label="处置类型"
              prop="dealWithType"
              :rules="[
                { required: true, message: '请输入处置类型', trigger: 'blur' }
              ]"
            >
              <el-input
                v-model="reportForm.dealWithType"
                placeholder="请输入处置类型"
              />
            </el-form-item>
          </el-col> -->
          <el-col :span="12">
            <el-form-item
              label="事件标题:"
              prop="eventName"
              :rules="[
                { required: true, message: '请输入事件标题', trigger: 'blur' }
              ]"
            >
              <el-input
                v-model="reportForm.eventName"
                placeholder="请输入事件标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              label="事件类型:"
              prop="eventType"
              :rules="[
                { required: true, message: '请选择事件类型', trigger: 'change' }
              ]"
            >
              <treeselect
                v-model="reportForm.eventType"
                :options="eventTypeOptions"
                :disable-branch-nodes="true"
                :show-count="true"
                placeholder="请选择事件类型"
                :searchable="true"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="上报人:" prop="handleBy">
              <el-input
                v-model="reportForm.handleBy"
                placeholder="请输入上报人姓名"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="上报人电话:" prop="handleBy">
              <el-input
                v-model="reportForm.createByPhone"
                placeholder="请输入上报人电话"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="严重程度" prop="eventDegree">
              <el-select
                v-model="reportForm.eventDegree"
                placeholder="请选择事件严重程度"
                :rules="[
                  {
                    required: true,
                    message: '请选择事件严重程度',
                    trigger: 'change'
                  }
                ]"
              >
                <el-option
                  v-for="dict in eventDegreeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item
              label="事件内容:"
              prop="content"
              :rules="[
                { required: true, message: '请输入事件内容', trigger: 'blur' }
              ]"
            >
              <el-input
                v-model="reportForm.content"
                type="textarea"
                placeholder="请输入事件内容"
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="现场图片:" prop="fileId">
              <imageListUpload
                :upLoadUrl="fileUpLoadUrl"
                :key="reportForm.id"
                :uploadState="uploadState"
                :filesJson.sync="reportForm.fileId"
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注:" prop="remarks">
              <el-input
                v-model="reportForm.remarks"
                type="textarea"
                placeholder="请输入备注"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" :disabled="isReport" @click="submitFormReport"
          >确 认</el-button
        >
        <el-button @click="cancelReport">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUser } from "@/api/system/user";
import { mapGetters } from "vuex";
import {
  listCommunity_event,
  getCommunity_event,
  delCommunity_event,
  addCommunity_event,
  updateCommunity_event,
  exportCommunity_event
} from "@/api/communityEvent/info";
import {
  listInfo,
  getInfo,
  softDelInfo,
  delInfo,
  addInfo,
  exportInfo,
  updateEventsStatus
} from "@/api/communityEvent/info";
import {
  listRecord,
  addRecord,
  getRecord,
  updateRecord,
  getRecordInfo,
  updateInfo,
  listPersonChuzhi
} from "@/api/communityEvent/record";
import { addCommunity_eventEntry } from "@/api/communityEvent/community_event";
import { listCommunity } from "@/api/communityUnit/community";
import { listCommunity_child } from "@/api/communityUnit/community_child";
import { getInfoByName } from "@/api/communityUnit/personnel_information";
import { listVehicle_information } from "@/api/communityUnit/vehicle_information";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import configInfo from "@/webconfig/configInfo";
import imageListUpload from "@/components/upload/uploadImageList";
export default {
  name: "Community_event",
  components: { Treeselect, imageListUpload },
  data() {
    return {
      isReport: false,
      reportForm: {},
      reportId: null,
      openReport: false,
      isback: false,
      isDisabled: false,
      rowInfoShow: false,
      buttonName: "",
      recordList: [],
      fileId: [],
      FileList: [],
      alertFileList: [],
      eventInfoFileId: [],
      fromShow: true,
      formInfo: {},
      contenShow: null,
      imageUploadShow: true,
      buttonShow: true,
      formSpan: null,
      infoSpan: null,
      infoShow: false,
      //文件上传地址
      turnDownShow: null, // 回退button
      fileUpLoadUrl: null,
      eventTypeOptions: [],
      evemtHandleShow: true,
      //办结人
      HandleByList: [],

      eventStatusPH: null, //   :placeholder="form.eventStatus <= 1 ?'请选择办结人':'请选择归档人'"
      eventStatusLabel: null, //:label="form.eventStatus <= 1 ?'办结人':'归档人'"
      contentLabel: null, // :label="form.eventStatus <=2 ?'办结描述':'归档意见'"
      // 子事件长度
      eventStatusOptionsLength: 0,
      // 进度条

      isHandle: false, // 分派人下拉菜单
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      eventTypeList: [],
      // 社区事件 表格数据
      community_eventList: [],
      // 是否处理字典
      processedOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 事件状态字典
      eventStatusOptions: [],
      // 事件类型字典
      typeOptions: [],
      // 告警层级字典
      eventDegreeOptions: [],
      //新增的关联人员
      targetPerson: [],
      //新增的关联车辆
      targetVehicle: [],
      //远程人员搜索
      loading_remotePersonInfo: false,
      //远程人员搜索的返回结果
      remotePersonSearchResult: [],
      //远程人员车辆
      loading_remoteVehicleInfo: false,
      //远程人员搜索的返回结果
      remoteVehicleSearchResult: [],
      // 社区
      communityOptions: [],
      //小区列表
      communityChildOptions: [],
      //小区列表
      communityChildOptions2: [],
      //关联人员使用的搜索结果列表
      personGroupOptions: [
        {
          label: "已选",
          options: []
        },
        {
          label: "待选",
          options: []
        }
      ],
      //关联车辆使用的搜索结果列表
      vehicleGroupOptions: [
        {
          label: "已选",
          options: []
        },
        {
          label: "待选",
          options: []
        }
      ],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        eventName: null,
        eventType: null,
        personid: null,
        vehicleid: null,
        eventDegree: null,
        content: null,
        eventStatus: null,
        communityId: null,
        communityChildId: null,
        orderByColumn: "createTime",
        isAsc: "desc"
      },
      // 表单参数
      form: {},
      reportForm: {},
      // 表单校验
      rules: {
        // division: [
        //   { required: true, message: "请输入活动名称", trigger: "blur" }
        // ],
        // dealWithType: [
        //   { required: true, message: "请输入活动名称", trigger: "blur" }
        // ],
        // eventType: [
        //   { required: true, message: "请输入活动名称", trigger: "blur" }
        // ],
        // eventName: [
        //   { required: true, message: "请输入事件名称", trigger: "blur" }
        // ],
        // eventDegree: [
        //   { required: true, message: "请输入紧急程度", trigger: "blur" }
        // ],
        // content: [
        //   { required: true, message: "请输入事件内容", trigger: "blur" }
        // ]
        // handleById: [{ required: true, message: "指定办结人不能为空！", trigger: "change" }],
        // fileId: [{ required: true, message: "图片不能为空！", trigger: "change" }],
      },
      // 搜索表单控件高度
      formHeight: 0,
      // 上传图片状态
      uploadState: true
    };
  },
  created() {
    this.getAllCommunityChildDictory();
    this.getCommunityDictory();
    this.getDicts("base_event_status").then(response => {
      this.eventStatusOptions = response.data;
      this.eventStatusOptionsLength = this.eventStatusOptions.length;
    });
    this.getDicts("base_community_event").then(response => {
      this.typeOptions = response.data;
    });
    this.getDicts("base_event_degree_type").then(response => {
      this.eventDegreeOptions = response.data;
    });
    this.getDicts("base_yes_no").then(response => {
      this.processedOptions = response.data;
    });
    this.getDicts("base_event_type").then(response => {
      this.eventTypeList = response.data;
      this.eventTypeOptions = this.commonFunction.getListByTree(response.data);
      // console.log(this.eventTypeOptions,9090);
    });
    // this.getDicts("out/base_event_type").then(response => {
    //   console.log(response,9090);
    // });
  },
  mounted() {
    this.fileUpLoadUrl = configInfo.FileUpLoadUri;

    // this.getHandleByList();
    this.getList();
    this.$nextTick(() => {
      this.formHeight = this.$refs.queryForm.$el.offsetHeight;
    });
  },
  computed: {
    ...mapGetters(["dept"]),
    dataHeight() {
      let searchFormHeight = 0;
      if (this.showSearch) searchFormHeight = this.formHeight;
      return this.$store.getters.dataEleHeight - searchFormHeight - 115;
    }
  },

  methods: {
    // 处理elementUI上传图片的转换
    photoUrlreplace(url) {
      let tupianList = this.$cf.returnJson(url);
      let tupian = [];
      tupianList.forEach((val, index) => {
        tupian.push(val.url);
      });
      return tupian.join(",");
    },
    // 处理图片加地址
    photoUrladd(url) {
      if (url) {
        url = url.split(",");
        let urlList = [];
        urlList = url.map(val => {
          return (val = configInfo.imgEchoAddress + val);
        });
        return urlList;
      }
    },
    // 录入事件的提交按钮
    submitFormReport() {
      this.$refs["reportForm"].validate(valid => {
        if (valid) {
          this.reportForm.fileId = this.photoUrlreplace(this.reportForm.fileId);
          let data = this.reportForm;
          // 测试用，组织架构有了要改为真实的
          data.communityId = "0355931e-a70b-11eb-a21e-0050569ba051";
          data.eventStatus = "1";
          data.dealWithType = "2";
          addCommunity_eventEntry(data).then(res => {
            if (res.code === 200) {
              this.$message({
                message: "恭喜你，这是一条成功消息",
                type: "success"
              });
              this.getList();
            }
          });
          this.openReport = false;
        } else {
          this.$message.error("请填入信息");
        }
      });

      // if (this.reportId != null) {
      //   let data = {};
      //   // data.id = this.reportId;
      //   console.log(this.resetReportForm,900900);
      // //   updateInfo(data).then(response => {
      // //     if (response.code === 200) {
      // //       this.msgSuccess("上报成功");
      // //       this.openReport = false;
      // //       this.reportId = null;
      // //       this.getList();
      // //     }
      // //   });
      // }
    },
    // 事件录入的重置
    resetReportForm() {
      this.reportForm = {
        id: null,
        division: null,
        dealWithType: null,
        eventName: null,
        eventType: null,
        eventDegree: null,
        content: null,
        createBy: null,
        createTime: null,
        createByPhone: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        eventInfoFileId: null,
        remarks: null,
        type: null,
        personid: null,
        alertFile: null,
        fileId: null,
        communityId: null,
        communityChildId: null
      };
      this.buttonName = "";
      this.fileId = [];
      this.resetForm("reportForm");
    },
    // 新增事件录入按钮
    eventEntry() {
      this.resetReportForm();
      this.openReport = true;
    },
    reportIncident(row) {
      // alert("待开发");
      if (row.eventStatus == "7") {
        this.isReport = true;
      } else {
        this.isReport = false;
      }
      this.openReport = true;
      this.reportId = row.id;
    },
    getHandleByList(row) {
      // let deptId = this.dept.id;
      // let queryParams = {
      //   pageNum: 1,
      //   pageSize: -1,
      //   userName: undefined,
      //   phonenumber: undefined,
      //   status: undefined,
      //   deptId: deptId,
      // };

      // listUser(this.queryParams).then(
      //   (response) => {
      //     this.HandleByList = response.rows;
      //   }
      // );
      let communityId = row.communityId;
      listPersonChuzhi(communityId).then(response => {
        this.HandleByList = response.rows;
      });
    },
    customColorMethod(number) {
      // console.log(number)
      let percentage = number == null ? 0 : number;
      if (percentage < 100) {
        return "#00d9ff";
      } else {
        return "#77f358";
      }
    },
    remotePersonInfoMethod(name) {
      this.loading_remotePersonInfo = true;
      let param = {
        name: name,
        communityId: null,
        communityChildId: null
      };
      if (this.form.communityId) {
        param.communityId = this.form.communityId;
      }
      if (this.form.communityChildId) {
        param.communityChildId = this.form.communityChildId;
      }
      getInfoByName(param).then(response => {
        this.remotePersonSearchResult = response.rows;
        //去除已经选中的
        if (
          this.personGroupOptions[0].options &&
          this.personGroupOptions[0].options.length > 0
        ) {
          let filterResult = this.remotePersonSearchResult.filter(item => {
            let te = this.personGroupOptions[0].options.filter(
              selectItem => selectItem.id === item.id
            );
            if (te && te.length > 0) return false;
            else return true;
          });
          this.personGroupOptions[1].options = filterResult;
        } else
          this.personGroupOptions[1].options = this.remotePersonSearchResult;
        this.loading_remotePersonInfo = false;
      });
    },

    getCommunityDictory() {
      this.communityOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000
      };
      listCommunity(param).then(response => {
        this.communityOptions = response.rows;
      });
    },
    getCommunityChildDictory(communityId) {
      this.communityChildOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId
      };

      listCommunity_child(param).then(response => {
        this.communityChildOptions = response.rows;
        // console.log('getCommunityChildDictory', response.rows)
      });
    },
    getCommunityChildDictory2(communityId) {
      this.communityChildOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: communityId
      };

      listCommunity_child(param).then(response => {
        this.communityChildOptions2 = response.rows;
        // console.log('getCommunityChildDictory2', response.rows)
      });
    },

    communityNameFormat(row, column, cellValue) {
      if (this.communityOptions && this.communityOptions.length > 0) {
        let rows = this.communityOptions.filter(item => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          return rows[0].name;
        }
      }
      return "??";
    },
    communityChildNameFormat(row, column, cellValue) {
      if (
        this.allCommunityChildOptions &&
        this.allCommunityChildOptions.length > 0
      ) {
        let rows = this.allCommunityChildOptions.filter(item => {
          return item.id === cellValue;
        });
        if (rows && rows.length > 0) {
          return rows[0].name;
        }
      }
      return "";
    },
    getAllCommunityChildDictory(communityId) {
      this.allCommunityChildOptions = [];
      let param = {
        pageNum: 1,
        pageSize: 100000,
        communityId: null
      };
      listCommunity_child(param).then(response => {
        this.allCommunityChildOptions = response.rows;
        // console.log('communityId', response.rows)
      });
    },
    /** 查询社区事件 列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then(response => {
        this.community_eventList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    //  事件状态字典翻译
    eventStatusFormat(row, column) {
      return this.selectDictLabel(this.eventStatusOptions, row.eventStatus);
    },
    //  事件状态字典翻译
    eventStatusListFormat(row, column) {
      return this.selectDictLabel(this.eventStatusOptions, row.eventStatus);
    },
    // // 事件类型字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.eventTypeList, row.eventType);
    },
    // 告警层级字典翻译
    eventDegreeFormat(row, column) {
      return this.selectDictLabel(this.eventDegreeOptions, row.eventDegree);
    },

    voFormat(row, column, cell) {
      if (!(cell && cell.length > 0)) return "";
      let targetList = this.$cf.getListFromString(cell, ";");
      let result = "";
      targetList.forEach(element => {
        if (element && element.length > 0) {
          let item = this.$cf.getListFromString(element);
          result += item[1] + ",";
        }
      });
      result = result.trim(",");
      return result;
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 取消按钮
    cancelReport() {
      this.openReport = false;
      // this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        eventInfoFileId: null,
        isback: "0",
        remarks: null,
        eventName: null,
        type: null,
        personid: null,
        fileId: null,
        eventDegree: null,
        communityId: null,
        communityChildId: null,
        handleBy: null,
        handleById: null,
        alertFile: null
      };
      this.buttonName = "";
      this.fileId = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 提交按钮 */
    submitForm() {
      let self = this;
      self.$refs["form"].validate(valid => {
        if (valid) {
          if (self.form.id != null) {
            console.log(self.form.eventStatus, "self.form.eventStatu2");
            self.form.id = self.form.eventId;
            let eventStatu = parseInt(self.form.eventStatus) + 1;
            self.form.eventStatus = eventStatu;
            if (self.form.eventStatus == 2) {
              let handleById = self.form.handleById;
              if (handleById != null) {
                let arr = self.HandleByList.filter(
                  item => item.id == handleById
                );
                self.form.handleBy = arr[0].nickName;
              }
            }

            console.log(self.form, "self.form");
            if (self.form.alertFile != null) {
              self.form.alertFile = self.photoUrlreplace(self.form.alertFile);
              // self.eventInfoFileId = self.form.eventInfoFileId.split(",")
            }
            // this.form.handleBy = "";
            // this.form.isback = '0';
            // this.form.eventStatusSon = (parseInt(this.form.eventStatusSon) + 1) + "";

            // this.isDisabled = true;
            updateEventsStatus(self.form).then(response => {
              console.log(response);
              if (response.code === 200) {
                self.msgSuccess("提交成功");
                self.open = false;
                self.getList();
                self.isDisabled = false;
              }
            });
          }
        }
      });
    },
    // turnDown () {
    //   this.$prompt("请输入回退原因", "提示", {
    //     confirmButtonText: "确定",
    //     cancelButtonText: "取消"
    //   })
    //     .then(({ value }) => {
    //       if (value == null) {
    //         value = "空"
    //       }

    //       if (this.form.id != null) {
    //         this.form.eventStatusSon = (parseInt(this.form.eventStatusSon) - 2) + "";
    //         this.form.eventStatus = (parseInt(this.form.eventStatus) - 1) + "";
    //         this.form.isback = '1';
    //         this.isDisabled = true;
    //         this.form.remarks = value;
    //         addRecord(this.form).then(response => {
    //           if (response.code === 200) {
    //             this.msgSuccess("回退成功");
    //             this.open = false;
    //             this.getList();
    //             this.isDisabled = true;
    //           }
    //         });
    //       }

    //     })
    //     .catch(() => {
    //       //取消回退
    //     });
    // },
    // 是否json
    isJSON(str) {
      if (typeof str === "string") {
        try {
          JSON.parse(str);
          return true;
        } catch (e) {
          console.log(e);
          return false;
        }
      }
    },
    // 两种图片处理
    /** 受理按钮操作 */
    handleAcceptance(row) {
      this.uploadState = false;
      this.reset();
      this.open = true;
      // this.getHandleByList(row)
      this.fromShow = true;
      this.rowInfoShow = false;
      this.buttonShow = true;
      this.infoShow = false;
      this.buttonName = "确认受理";
      this.evemtHandleShow = false;
      this.imageUploadShow = false;
      this.title = "受理社区事件 ";
      const id = row.id || this.ids;
      let self = this;
      getRecordInfo(id).then(response => {
        self.form = response.data;
        if (self.form != undefined) {
          self.form.createByPhone = row.createByPhone;
          self.form.content = "";
          if (self.form.eventStatus == "2" && self.form.isback == "1") {
            self.isback = true;
          } else {
            self.isback = false;
          }
          // self.form.eventStatus = "2"
          if (self.form.eventInfoFileId != null) {
            self.form.eventInfoFileId = self.photoUrladd(
              self.form.eventInfoFileId
            );
          }
          if (self.form.eventStatus == "2" && self.form.eventStatusSon == "3") {
            self.isDisabled = false;
          } else {
            self.isDisabled = true;
          }

          self.getCommunityChildDictory2(self.form.communityId);
          self.open = true;
        }
      });
    },
    /** 分派按钮操作 */
    handleAssignment(row) {
      this.uploadState = false;
      this.reset();
      this.getHandleByList(row);
      this.rowInfoShow = false;
      this.isHandle = false;
      this.buttonName = "确认分派";
      this.fromShow = true;
      this.buttonShow = true;
      this.imageUploadShow = false;
      this.formSpan = 24;
      this.contenShow = false;
      this.infoShow = false;
      // console.log(row.eventStatus, "that new is status ==>")
      this.turnDownShow = false;
      this.evemtHandleShow = true;
      this.eventStatusPH = "请选择办结人";
      this.eventStatusLabel = "办结人";
      const id = row.id || this.ids;

      getRecordInfo(id).then(response => {
        this.form = response.data;
        if (this.form != undefined) {
          this.form.createByPhone = row.createByPhone;
          if (this.form.eventStatus == "1" && this.form.isback == "1") {
            this.isback = true;
          } else {
            this.isback = false;
          }
          // this.form.eventStatus = "1"

          if (this.form.eventInfoFileId != null) {
            this.form.eventInfoFileId = this.photoUrladd(
              this.form.eventInfoFileId
            );
          }

          if (this.form.eventStatus == "1" && this.form.eventStatusSon == "1") {
            this.isDisabled = false;
          } else {
            this.isDisabled = true;
          }

          this.getCommunityChildDictory2(this.form.communityId);
          this.open = true;
          this.title = "分派社区事件 ";
        }
      });
    },
    // 办结操作
    handleDisposal(row) {
      this.reset();
      this.uploadState = true;
      this.rowInfoShow = false;
      this.buttonName = "确认办结";
      this.buttonShow = true;
      this.isHandle = true;
      this.formSpan = 24;
      this.fromShow = true;
      this.imageUploadShow = true;
      this.infoShow = false;
      this.contenShow = true;
      // console.log(row.eventStatus, "that new is status ==>")
      this.turnDownShow = true;
      this.evemtHandleShow = false;
      // this.eventStatusPH = "请选择归档人";
      // this.eventStatusLabel = "归档人";
      this.contentLabel = "办结描述";
      const id = row.id || this.ids;
      let self = this;
      getRecordInfo(id).then(response => {
        self.form = response.data;
        if (self.form != undefined) {
          self.form.createByPhone = row.createByPhone;
          self.form.content = "";
          if (self.form.eventStatus == "2" && self.form.isback == "1") {
            self.isback = true;
          } else {
            self.isback = false;
          }
          // self.form.eventStatus = "2"

          if (self.form.eventInfoFileId != null) {
            self.form.eventInfoFileId = self.photoUrladd(
              self.form.eventInfoFileId
            );
          }
          if (self.form.eventStatus == "2" && self.form.eventStatusSon == "3") {
            self.isDisabled = false;
          } else {
            self.isDisabled = true;
          }
          if (self.form.fileId != null) {
            self.fileId = self.form.fileId.split(",");
          } else {
          }

          self.getCommunityChildDictory2(self.form.communityId);
          self.open = true;
          self.title = "办结社区事件";
        }
      });
    },
    /** 归档按钮操作 */
    handleReview(row) {
      // debugger
      this.reset();
      this.uploadState = false;
      this.rowInfoShow = false;
      this.buttonName = "确认归档";
      this.fromShow = true;
      this.buttonShow = true;
      this.isHandle = true;
      this.formSpan = 24;
      this.imageUploadShow = true;
      this.infoShow = false;
      this.contenShow = true;
      // console.log(row.eventStatus, "that new is status ==>")
      this.turnDownShow = true;
      this.evemtHandleShow = true;
      this.eventStatusLabel = "办结人";
      this.contentLabel = "归档意见";
      const id = row.id || this.ids;

      getRecordInfo(id).then(response => {
        this.form = response.data;
        if (this.form != undefined) {
          this.form.createByPhone = row.createByPhone;

          this.form.eventStatus = "3";
          this.form.content = "";

          if (this.form.eventInfoFileId != null) {
            this.form.eventInfoFileId = this.photoUrladd(
              this.form.eventInfoFileId
            );
          }
          if (this.form.fileId != null) {
            this.form.alertFile = this.photoUrladd(this.form.fileId);
          }
          if (this.form.eventStatus == "3" && this.form.eventStatusSon == "5") {
            this.isDisabled = false;
          } else {
            this.isDisabled = true;
          }
          // if (this.form.fileId != null) {
          //   this.fileId = this.form.fileId.split(",");
          // } else {
          // }

          this.getCommunityChildDictory2(this.form.communityId);
          this.open = true;
          this.title = "归档社区事件";
        }
      });
    },
    // 事件详情
    handleInfo(row) {
      this.reset();
      this.formInfo = {};
      this.isHandle = true;
      this.rowInfoShow = true;
      this.buttonShow = false;
      this.infoShow = true;
      this.infoSpan = 12;
      this.fromShow = false;
      this.uploadState = false;
      this.alertFileList = [];

      // this.formSpan = 12;
      const id = row.id || this.ids;
      let queryParams = {
        pageNum: 1,
        pageSize: 99999,
        eventId: id,
        orderByColumn: "createTime",
        isAsc: "desc"
      };
      getInfo(id).then(response => {
        this.formInfo = response.data;
        if (this.formInfo) {
          if (this.formInfo.fileId != null) {
            this.formInfo.fileId = this.photoUrladd(this.formInfo.fileId);
            // if (this.isJSON(this.formInfo.alertFile)) {
            //   this.formInfo.alertFile = JSON.parse(this.formInfo.alertFile);
            //   this.formInfo.alertFile.forEach(v => {
            //     if (v.url.indexOf(configInfo.imgEchoAddress) === -1) {
            //       v.url = configInfo.imgEchoAddress + v.url;
            //       this.alertFileList.push(v.url);
            //     }
            //   });
            // } else {
            //   if (
            //     this.formInfo.alertFile.indexOf(configInfo.imgEchoAddress) ===
            //     -1
            //   ) {
            //     this.formInfo.alertFile =
            //       configInfo.imgEchoAddress + this.formInfo.alertFile;
            //     this.alertFileList.push(this.formInfo.alertFile);
            //   }
            // }
          }
          if (this.formInfo.alertFile) {
            this.formInfo.alertFile = this.photoUrladd(this.formInfo.alertFile);
          }
          this.getCommunityChildDictory2(this.formInfo.communityId);
        }
      });

      // this.eventInfoFileId = ['https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'];
      listRecord(queryParams).then(response => {
        this.recordList = response.rows;
        console.log(this.recordList);
        // this.recordList.forEach(item => {
        //   item.fileId = JSON.parse(item.fileId);
        // });
        this.open = true;
        this.title = "事件进度";
      });
    },
    /** 删除按钮操作 */
    handleDel(row) {
      const ids = row.id || this.ids;
      this.$confirm(
        '是否确认删除社区事件 编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return delInfo(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有社区事件 数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return exportCommunity_event(queryParams);
        })
        .then(response => {
          this.download(response.msg);
        })
        .catch(function() {});
    }
  }
};
</script>

<style lang="scss" scoped>
/* .el-input.is-disabled .el-input__inner {
  background: #124e74;
}
.vue-treeselect--disabled .vue-treeselect__control {
  background-color: #005370;
}
.el-upload-dragger {
  background-color: #00425e;
  border: 1px dashed #00a2c5;
} */
/deep/.vue-treeselect__control {
  width: 200px;
}
</style>

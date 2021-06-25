<template>
  <div>
    <el-form ref="form"
             :model="form"
             :rules="rules"
             size="medium"
             label-width="100px">
      <el-row :gutter="15">
        <el-form-item label-width="280px"
                      class="title"
                      label="关爱人员长期未通行模型">
        </el-form-item>
        <el-form-item label-width="180px"
                      label="开关"
                      prop="caring_long_time_no_going_out_stat"
                      required>
          <el-switch v-model="form.caring_long_time_no_going_out_stat"
                     active-text="已启用"
                     inactive-text="已停用"></el-switch>
        </el-form-item>
        <el-form-item label-width="180px"
                      v-if="form.caring_long_time_no_going_out_stat"
                      label="时间阈值"
                      prop="caring_long_time_no_going_out_hours">
          <el-input-number v-model="form.caring_long_time_no_going_out_hours"
                           placeholder="小时"
                           step-strictly
                           controls-position=right></el-input-number>
        </el-form-item>
        <el-form-item label-width="180px"
                      v-if="form.caring_long_time_no_going_out_stat"
                      label="限定人群"
                      prop="caring_long_time_no_going_out_target">
          <el-checkbox-group v-model="caring_long_time_no_going_out_target"
                             size="medium">
            <el-checkbox v-for="dict in caringLongTimeNoGoingOutTargetOptions"
                         :key="dict.dictValue"
                         :label="dict.dictValue">{{dict.dictLabel}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <!-- <el-form-item label-width="180px"
                      label="时间范围"
                      prop="caring_long_time_no_going_out_timeSpan">
          <el-time-picker v-model="form.caring_long_time_no_going_out_timeSpan"
                          is-range
                          format="HH:mm:ss"
                          value-format="HH:mm:ss"
                          :style="{width: '500px'}"
                          start-placeholder="开始时间"
                          end-placeholder="结束时间"
                          range-separator="至">
          </el-time-picker>
        </el-form-item> -->
      </el-row>
      <el-row :gutter="15">
        <el-form-item label-width="280px"
                      class="title"
                      label="频繁出入模型">
        </el-form-item>
        <el-form-item label-width="180px"
                      label="开关"
                      prop="frequent_access_stat"
                      required>
          <el-switch v-model="form.frequent_access_stat"
                     active-text="已启用"
                     inactive-text="已停用"></el-switch>
        </el-form-item>
        <el-form-item label-width="180px"
                      label="次数阈值"
                      v-if="form.frequent_access_stat"
                      prop="frequent_access_count">
          <el-input-number v-model="form.frequent_access_count"
                           placeholder="计数器"
                           step-strictly
                           controls-position=right></el-input-number>
        </el-form-item>
        <el-form-item label-width="180px"
                      label="限定人群"
                      v-if="form.frequent_access_stat"
                      prop="frequent_access_target">
          <el-checkbox-group v-model="frequent_access_target"
                             size="medium">
            <el-checkbox v-for="(dict, index) in frequentAccessTargetOptions"
                         :key="dict.dictValue"
                         :label="dict.dictValue">{{dict.dictLabel}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label-width="180px"
                      label="时间范围"
                      v-if="form.frequent_access_stat"
                      prop="frequent_access_timeSpan">
          <el-time-picker v-model="frequent_access_timeSpan"
                          is-range
                          format="HH:mm:ss"
                          value-format="HH:mm:ss"
                          :style="{width: '500px'}"
                          start-placeholder="开始时间"
                          end-placeholder="结束时间"
                          range-separator="至">
          </el-time-picker>
        </el-form-item>
        <!-- <el-form-item label-width="180px"
                      label="区域"
                      prop="frequent_access_area">
          <el-cascader v-model="frequent_access_area"
                       :options="frequentAccessAreaOptions"
                       :props="frequentAccessAreaProps"
                       :style="{width: '500px'}"
                       placeholder="请选择检测区域"
                       filterable
                       clearable></el-cascader>
        </el-form-item> -->
      </el-row>
      <el-row :gutter="15">
        <el-form-item label-width="280px"
                      class="title"
                      label="凌晨出行">
        </el-form-item>
        <el-form-item label-width="180px"
                      label="开关"
                      prop="going_in_the_morning_stat"
                      required>
          <el-switch v-model="form.going_in_the_morning_stat"
                     active-text="已启用"
                     inactive-text="已停用"></el-switch>
        </el-form-item>
        <el-form-item label-width="180px"
                      label="限定人群"
                      v-if="form.going_in_the_morning_stat"
                      prop="going_in_the_morning_target">
          <el-checkbox-group v-model="going_in_the_morning_target"
                             size="medium">
            <el-checkbox v-for="(dict, index) in goingInTheMorningTargetOptions"
                         :key="dict.dictValue"
                         :label="dict.dictValue">{{dict.dictLabel}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label-width="180px"
                      label="凌晨时间范围"
                      v-if="form.going_in_the_morning_stat"
                      prop="going_in_the_morning_timeSpan">
          <el-time-picker v-model="going_in_the_morning_timeSpan"
                          is-range
                          format="HH:mm:ss"
                          value-format="HH:mm:ss"
                          :style="{width: '500px'}"
                          start-placeholder="开始时间"
                          end-placeholder="结束时间"
                          range-separator="至">
          </el-time-picker>
        </el-form-item>
      </el-row>
      <el-form-item size="large">
        <el-button type="primary"
                   @click="submitForm">提交修改</el-button>
        <el-button @click="resetForm">恢复默认</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { updateConfigbyKey, updateConfigbyKeys } from "@/api/system/config";
export default {
  components: {},
  props: [],
  data () {
    return {
      form: {
        //关爱人员长期未出现
        caring_long_time_no_going_out_stat: false,
        caring_long_time_no_going_out_hours: 36,
        caring_long_time_no_going_out_target: [],
        //频繁出入
        frequent_access_stat: false,
        frequent_access_count: 6,
        frequent_access_target: [],
        frequent_access_timeSpan: ["00:00:00", "04:00:00"],
        frequent_access_area: [],
        //凌晨出行
        going_in_the_morning_stat: false,
        going_in_the_morning_target: [],
        going_in_the_morning_timeSpan: ["00:00:00", "04:00:00"],
      },
      rules: {
        caring_long_time_no_going_out_hours: [{
          required: true,
          message: '请输入长期为出行策略的时长阈值',
          trigger: 'blur'
        }],
        caring_long_time_no_going_out_target: [{
          required: true,
          // type: 'array',
          message: '请选择长期未出行检测的目标人群',
          trigger: 'change'
        }],
        frequent_access_count: [{
          required: true,
          message: '请输入频繁出入策略的时长阈值',
          trigger: 'blur'
        }],
        frequent_access_target: [{
          required: true,
          // type: 'array',
          message: '请选择频繁出入检测的目标人群',
          trigger: 'change'
        }],
        frequent_access_timeSpan: [{
          required: true,
          // type: 'array',
          message: '请设置频繁出入检测的时间段',
          trigger: 'change'
        }],
        frequent_access_area: [{
          required: true,
          // type: 'array',
          message: '请设置频繁出入检测的区域',
          trigger: 'change'
        }],
        going_in_the_morning_target: [{
          required: true,
          // type: 'array',
          message: '请选择凌晨出行检测的目标人群',
          trigger: 'change'
        }],
        going_in_the_morning_timeSpan: [{
          required: true,
          // type: 'array',
          message: '请设置凌晨出行检测的时间段',
          trigger: 'change'
        }],
      },
      //长期未出行的可选目标
      caringLongTimeNoGoingOutTargetOptions: [],
      //频繁出现的可选目标
      frequentAccessTargetOptions: [],
      //凌晨出行的可选目标
      goingInTheMorningTargetOptions: [],
      //频繁出入监测区域 摄像头列表
      frequentAccessAreaOptions: [],//
      default: {
        //关爱人员长期未出现
        caring_long_time_no_going_out_stat: false,
        caring_long_time_no_going_out_hours: 36,
        caring_long_time_no_going_out_target: [1],
        // caring_long_time_no_going_out_timeSpan: ["00:00:00", "04:00:00"],
        //频繁出入
        frequent_access_stat: false,
        frequent_access_count: 6,
        frequent_access_target: [1],
        frequent_access_timeSpan: ["00:00:00", "04:00:00"],
        frequent_access_area: [],
        //凌晨出行
        going_in_the_morning_stat: false,
        going_in_the_morning_target: [1],
        going_in_the_morning_timeSpan: ["00:00:00", "04:00:00"],
      },
      //中间数组，用于临时承载用户的编辑
      caring_long_time_no_going_out_target: [],
      frequent_access_target: [],
      frequent_access_timeSpan: ["00:00:00", "04:00:00"],
      frequent_access_area: [],
      going_in_the_morning_target: [],
      going_in_the_morning_timeSpan: ["00:00:00", "04:00:00"],
    }
  },
  computed: {
    // caring_long_time_no_going_out_stat () {
    //   if (this.form.caring_long_time_no_going_out_stat) {
    //     return true
    //   } else {
    //     return false
    //   }
    // },
    // frequent_access_stat () {
    //   if (this.form.frequent_access_stat) {
    //     return true
    //   } else {
    //     return false
    //   }
    // },
    // going_in_the_morning_stat () {
    //   if (this.form.going_in_the_morning_stat) {
    //     return true
    //   } else {
    //     return false
    //   }
    // },
  },
  watch: {
    caring_long_time_no_going_out_target: {
      handler: function (newValue) {
        if (newValue && (newValue.constructor == Array)) {
          this.form.caring_long_time_no_going_out_target = this.$cf.getStringFromList(newValue)
        } else {
          this.form.caring_long_time_no_going_out_target = ''
        }
      },
      deep: true,
      immediate: true
    },
    frequent_access_target: {
      handler: function (newValue) {
        if (newValue && (newValue.constructor == Array)) {
          this.form.frequent_access_target = this.$cf.getStringFromList(newValue)
        } else {
          this.form.frequent_access_target = ''
        }
      },
      deep: true,
      immediate: true
    },
    frequent_access_timeSpan: {
      handler: function (newValue) {
        if (newValue && (newValue.constructor == Array)) {
          this.form.frequent_access_timeSpan = this.$cf.getStringFromList(newValue)
        } else {
          this.form.frequent_access_timeSpan = ''
        }
      },
      deep: true,
      immediate: true
    },
    frequent_access_area: {
      handler: function (newValue) {
        if (newValue && (newValue.constructor == Array)) {
          this.form.frequent_access_area = this.$cf.getStringFromList(newValue)
        } else {
          this.form.frequent_access_area = ''
        }
      },
      deep: true,
      immediate: true
    },
    going_in_the_morning_target: {
      handler: function (newValue) {
        if (newValue && (newValue.constructor == Array)) {
          this.form.going_in_the_morning_target = this.$cf.getStringFromList(newValue)
        } else {
          this.form.going_in_the_morning_target = ''
        }
      },
      deep: true,
      immediate: true
    },
    going_in_the_morning_timeSpan: {
      handler: function (newValue) {
        if (newValue && (newValue.constructor == Array)) {
          this.form.going_in_the_morning_timeSpan = this.$cf.getStringFromList(newValue)
        } else {
          this.form.going_in_the_morning_timeSpan = ''
        }
      },
      deep: true,
      immediate: true
    },
  },
  created () {
    let self = this
    self.getConfigKey("default_caring_long_time_no_going_out_stat").then(response => {
      self.default.caring_long_time_no_going_out_stat = response.msg ? response.msg.toUpperCase() === 'TRUE' : false;
    });
    self.getConfigKey("default_caring_long_time_no_going_out_hours").then(response => {
      self.default.caring_long_time_no_going_out_hours = response.msg;
    });
    self.getConfigKey("default_caring_long_time_no_going_out_target").then(response => {
      let list = self.$cf.getListFromString(response.msg)
      if (list && list.length > 0) {
        self.default.caring_long_time_no_going_out_target = list;
      }
    });
    self.getConfigKey("default_frequent_access_stat").then(response => {
      self.default.frequent_access_stat = response.msg ? response.msg.toUpperCase() === 'TRUE' : false;
    });
    self.getConfigKey("default_frequent_access_count").then(response => {
      self.default.frequent_access_count = response.msg;
    });
    self.getConfigKey("default_frequent_access_target").then(response => {
      let list = self.$cf.getListFromString(response.msg)
      if (list && list.length > 0) {
        self.default.frequent_access_target = list;
      }
    });
    self.getConfigKey("default_frequent_access_timeSpan").then(response => {
      let list = self.$cf.getListFromString(response.msg)
      if (list && list.length > 0) {
        self.default.frequent_access_timeSpan = list;
      }
    });
    self.getConfigKey("default_caring_long_time_no_going_out_stat").then(response => {
      self.default.caring_long_time_no_going_out_stat = response.msg ? response.msg.toUpperCase() === 'TRUE' : false;
    });
    self.getConfigKey("default_going_in_the_morning_target").then(response => {
      let list = self.$cf.getListFromString(response.msg)
      if (list && list.length > 0) {
        self.default.going_in_the_morning_target = list;
      }
    });
    self.getConfigKey("default_going_in_the_morning_timeSpan").then(response => {
      let list = self.$cf.getListFromString(response.msg)
      if (list && list.length > 0) {
        self.default.going_in_the_morning_timeSpan = list;
      }
    });

    self.getDicts("base_long_time_no_going_out_target").then(response => {
      self.caringLongTimeNoGoingOutTargetOptions = response.data;
    });
    self.getDicts("base_frequent_access_target").then(response => {
      self.frequentAccessTargetOptions = response.data;
    });
    self.getDicts("base_going_in_the_morning_target").then(response => {
      self.goingInTheMorningTargetOptions = response.data;
    });

    //当前值
    self.getConfigKey("caring_long_time_no_going_out_stat").then(response => {
      self.form.caring_long_time_no_going_out_stat = response.msg ? response.msg.toUpperCase() === 'TRUE' : false;
    });
    self.getConfigKey("caring_long_time_no_going_out_hours").then(response => {
      self.form.caring_long_time_no_going_out_hours = response.msg;
    });
    self.getConfigKey("caring_long_time_no_going_out_target").then(response => {
      let list = self.$cf.getListFromString(response.msg)
      if (list && list.length > 0) {
        self.caring_long_time_no_going_out_target = list;
      }
    });
    self.getConfigKey("frequent_access_stat").then(response => {
      self.form.frequent_access_stat = response.msg ? response.msg.toUpperCase() === 'TRUE' : false;
    });
    self.getConfigKey("frequent_access_count").then(response => {
      self.form.frequent_access_count = response.msg;
    });
    self.getConfigKey("frequent_access_target").then(response => {
      let list = self.$cf.getListFromString(response.msg)
      if (list && list.length > 0) {
        self.frequent_access_target = list;
      }
    });
    self.getConfigKey("frequent_access_timeSpan").then(response => {
      let list = self.$cf.getListFromString(response.msg)
      if (list && list.length > 0) {
        self.frequent_access_timeSpan = list;
      }
    });
    self.getConfigKey("caring_long_time_no_going_out_stat").then(response => {
      self.form.caring_long_time_no_going_out_stat = response.msg ? response.msg.toUpperCase() === 'TRUE' : false;
    });
    self.getConfigKey("going_in_the_morning_stat").then(response => {
      self.form.going_in_the_morning_stat = response.msg ? response.msg.toUpperCase() === 'TRUE' : false;
    });
    self.getConfigKey("going_in_the_morning_target").then(response => {
      let list = self.$cf.getListFromString(response.msg)
      if (list && list.length > 0) {
        self.going_in_the_morning_target = list;
      }
    });
    self.getConfigKey("going_in_the_morning_timeSpan").then(response => {
      let list = self.$cf.getListFromString(response.msg)
      if (list && list.length > 0) {
        self.going_in_the_morning_timeSpan = list;
      }
    });
  },
  mounted () {
  },
  methods: {
    getDBdataFromShow (form) {
      if (this.caring_long_time_no_going_out_target && this.caring_long_time_no_going_out_target.length > 0) {
        form.caring_long_time_no_going_out_target = this.$cf.getStringFromList(this.caring_long_time_no_going_out_target)
      } else {
        form.caring_long_time_no_going_out_target = ''
      }
      if (this.frequent_access_target && this.frequent_access_target.length > 0) {
        form.frequent_access_target = this.$cf.getStringFromList(this.frequent_access_target)
      } else {
        form.frequent_access_target = ''
      }
      if (this.going_in_the_morning_target && this.going_in_the_morning_target.length > 0) {
        form.going_in_the_morning_target = this.$cf.getStringFromList(this.going_in_the_morning_target)
      } else {
        form.going_in_the_morning_target = ''
      }
      if (this.frequent_access_timeSpan && this.frequent_access_timeSpan.length > 0) {
        form.frequent_access_timeSpan = this.$cf.getStringFromList(this.frequent_access_timeSpan)
      } else {
        form.frequent_access_timeSpan = ''
      }
      if (this.frequent_access_area && this.frequent_access_area.length > 0) {
        form.frequent_access_area = this.$cf.getStringFromList(this.frequent_access_area)
      } else {
        form.frequent_access_area = ''
      }
      if (this.going_in_the_morning_timeSpan && this.going_in_the_morning_timeSpan.length > 0) {
        form.going_in_the_morning_timeSpan = this.$cf.getStringFromList(this.going_in_the_morning_timeSpan)
      } else {
        form.going_in_the_morning_timeSpan = ''
      }
    },
    submitForm () {
      let self = this
      // this.getDBdataFromShow(self.form)
      this.$refs['form'].validate(valid => {
        if (valid) {
          // TODO 提交表单
          let param = []
          for (var item in self.form) {
            param.push({
              configKey: item,
              configValue: self.form[item]
            })
          }
          updateConfigbyKeys(param).then(response => {
            if (response.code === 200) {
              self.msgSuccess("修改成功");
            }
          });
        }
        else {
          self.msgInfo("请补全必填项");
        }
      })
    },
    resetForm () {
      // this.$refs['elForm'].resetFields()
      this.set_default()
    },
    set_default (propName) {
      if (propName && propName.length > 0) {
        this.form[propName] = this.default[propName]
      } else {
        this.form.caring_long_time_no_going_out_stat = this.default.caring_long_time_no_going_out_stat
        this.form.caring_long_time_no_going_out_hours = this.default.caring_long_time_no_going_out_hours
        this.caring_long_time_no_going_out_target = this.default.caring_long_time_no_going_out_target
        this.form.frequent_access_stat = this.default.frequent_access_stat
        this.form.frequent_access_count = this.default.frequent_access_count
        this.frequent_access_target = this.default.frequent_access_target
        this.frequent_access_timeSpan = this.default.frequent_access_timeSpan
        this.frequent_access_area = this.default.frequent_access_area
        this.form.going_in_the_morning_stat = this.default.going_in_the_morning_stat
        this.going_in_the_morning_target = this.default.going_in_the_morning_target
        this.going_in_the_morning_timeSpan = this.default.going_in_the_morning_timeSpan
      }
    }
  }
}

</script>
<style lang="scss">
@import "@/assets/styles/variables.scss";
.title {
  .el-form-item__label {
    text-align: left;
    margin-left: 70px;
    font-size: 22px !important;
    // color: $fontC;
    color: rgba($color: $fontC, $alpha: 0.9);
  }
}
.contentWidth {
  width: 500px;
}
</style>

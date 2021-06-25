/**
 * 通用js方法封装处理
 * Copyright (c) 2019 isstech
 */

import configInfo from '@/webconfig/configInfo'
// 日期格式化
export function parseTime (time, pattern) {
  if (arguments.length === 0 || !time) {
    return null
  }
  const format = pattern || '{y}-{m}-{d} {h}:{i}:{s}'
  let date
  if (typeof time === 'object') {
    date = time
  } else {
    if ((typeof time === 'string') && (/^[0-9]+$/.test(time))) {
      time = parseInt(time)
    } else if (typeof time === 'string') {
      time = time.replace(new RegExp(/-/gm), '/');
    }
    if ((typeof time === 'number') && (time.toString().length === 10)) {
      time = time * 1000
    }
    date = new Date(time)
  }
  const formatObj = {
    y: date.getFullYear(),
    m: date.getMonth() + 1,
    d: date.getDate(),
    h: date.getHours(),
    i: date.getMinutes(),
    s: date.getSeconds(),
    a: date.getDay()
  }
  const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
    let value = formatObj[key]
    // Note: getDay() returns 0 on Sunday
    if (key === 'a') { return ['日', '一', '二', '三', '四', '五', '六'][value] }
    if (result.length > 0 && value < 10) {
      value = '0' + value
    }
    return value || 0
  })
  return time_str
}

// 表单重置
export function resetForm (refName) {
  if (this.$refs[refName]) {
    this.$refs[refName].resetFields();
  }
}

// 添加日期范围
export function addDateRange (params, dateRange) {
  var search = params;
  search.beginTime = "";
  search.endTimes = "";
  if (null != dateRange && '' != dateRange) {
    search.beginTime = this.dateRange[0];
    search.endTimes = this.dateRange[1];
  }
  return search;
}

// 回显数据字典
export function selectDictLabel (datas, value) {
  var actions = [];
  Object.keys(datas).some((key) => {
    if (datas[key].dictValue == ('' + value)) {
      actions.push(datas[key].dictLabel);
      return true;
    }
  })
  return actions.join('');
}
// 回显数据(值)字典，即根据值选中
export function selectDictLabelByLable (datas, value) {
  var actions = [];
  Object.keys(datas).some((key) => {
    if (datas[key].dictLabel == ('' + value)) {
      actions.push(datas[key].dictLabel);
      return true;
    }
  })
  return actions.join('');
}
// 回显数据字典（字符串数组）
export function selectDictLabels (datas, value, separator) {
  var actions = [];
  var currentSeparator = undefined === separator ? "," : separator;
  var temp = value.split(currentSeparator);
  Object.keys(value.split(currentSeparator)).some((val) => {
    Object.keys(datas).some((key) => {
      if (datas[key].dictValue == ('' + temp[val])) {
        actions.push(datas[key].dictLabel + currentSeparator);
      }
    })
  })
  return actions.join('').substring(0, actions.join('').length - 1);
}

// 通用下载方法
export function download (fileName) {
  window.location.href = configInfo.DBRestAPIEndPointUri + "/common/download?fileName=" + encodeURI(fileName) + "&delete=" + true;
}

// 字符串格式化(%s )
export function sprintf (str) {
  var args = arguments, flag = true, i = 1;
  str = str.replace(/%s/g, function () {
    var arg = args[i++];
    if (typeof arg === 'undefined') {
      flag = false;
      return '';
    }
    return arg;
  });
  return flag ? str : '';
}

// 转换字符串，undefined,null等转化为""
export function praseStrEmpty (str) {
  if (!str || str == "undefined" || str == "null") {
    return "";
  }
  return str;
}

/**
 * 构造树型结构数据
 * @param {*} data 数据源
 * @param {*} id id字段 默认 'id'
 * @param {*} parentId 父节点字段 默认 'parentId'
 * @param {*} children 孩子节点字段 默认 'children'
 * @param {*} rootId 根Id 默认 0
 */
export function handleTree (data, id, parentId, children, rootId) {
  id = id || 'id'
  parentId = parentId || 'parentId'
  children = children || 'children'
  rootId = rootId || Math.min.apply(Math, data.map(item => { return item[parentId] })) || 0
  rootId = rootId + ''
  //对源数据深度克隆
  const cloneData = JSON.parse(JSON.stringify(data))
  //循环所有项
  const treeData = cloneData.filter(father => {
    let branchArr = cloneData.filter(child => {
      //返回每一项的子级数组
      return father[id] === child[parentId]
    });
    branchArr.length > 0 ? father.children = branchArr : '';
    //返回第一层
    return father[parentId] === rootId;
  });
  return treeData != '' ? treeData : data;
}

export function getFirstUrlFromJson (picJson) {
  if (!(picJson && picJson.length > 0)) return ''
  let list = JSON.parse(picJson)
  if (list && list.length > 0) {
    return list[0].url
  }
  else return ''
}
export function textLimit (text, maxLength = 6) {
  if (!(text && text.length > 0)) return ''
  if (text.length > maxLength) {
    return text.slice(0, maxLength) + '...'
  } else {
    return text
  }
}
/**去除重复项 */
export function unique (arr, propName) {
  for (var i = 0, len = arr.length; i < len; i++) {
    for (var j = i + 1, len = arr.length; j < len; j++) {
      if (arr[i][propName] === arr[j][propName]) {
        arr.splice(j, 1);
        j--;        // 每删除一个数j的值就减1
        len--;      // j值减小时len也要相应减1（减少循环次数，节省性能）   
        // console.log(j,len)
      }
    }
  }
  return arr;
}
export function unique2 (arr, propName1, propName2) {
  for (var i = 0, len = arr.length; i < len; i++) {
    for (var j = i + 1, len = arr.length; j < len; j++) {
      if (arr[i][propName1] === arr[j][propName1] && arr[i][propName2] === arr[j][propName2]) {
        arr.splice(j, 1);
        j--;        // 每删除一个数j的值就减1
        len--;      // j值减小时len也要相应减1（减少循环次数，节省性能）   
        // console.log(j,len)
      }
    }
  }
  return arr;
}
export function unique3 (arr, propName1, propName2, propName3) {
  for (var i = 0, len = arr.length; i < len; i++) {
    for (var j = i + 1, len = arr.length; j < len; j++) {
      if (arr[i][propName1] === arr[j][propName1] && arr[i][propName2] === arr[j][propName2] && arr[i][propName3] === arr[j][propName3]) {
        arr.splice(j, 1);
        j--;        // 每删除一个数j的值就减1
        len--;      // j值减小时len也要相应减1（减少循环次数，节省性能）   
        // console.log(j,len)
      }
    }
  }
  return arr;
}
export function unique4 (arr, propName1, propName2, propName3, propName4) {
  for (var i = 0, len = arr.length; i < len; i++) {
    for (var j = i + 1, len = arr.length; j < len; j++) {
      if (arr[i][propName1] === arr[j][propName1]
        && arr[i][propName2] === arr[j][propName2]
        && arr[i][propName3] === arr[j][propName3]
        && arr[i][propName4] === arr[j][propName4]) {
        arr.splice(j, 1);
        j--;        // 每删除一个数j的值就减1
        len--;      // j值减小时len也要相应减1（减少循环次数，节省性能）   
        // console.log(j,len)
      }
    }
  }
  return arr;
}
export function unique5 (arr, propName1, propName2, propName3, propName4, propName5) {
  for (var i = 0, len = arr.length; i < len; i++) {
    for (var j = i + 1, len = arr.length; j < len; j++) {
      if (arr[i][propName1] === arr[j][propName1]
        && arr[i][propName2] === arr[j][propName2]
        && arr[i][propName3] === arr[j][propName3]
        && arr[i][propName4] === arr[j][propName4]
        && arr[i][propName5] === arr[j][propName5]) {
        arr.splice(j, 1);
        j--;        // 每删除一个数j的值就减1
        len--;      // j值减小时len也要相应减1（减少循环次数，节省性能）   
        // console.log(j,len)
      }
    }
  }
  return arr;
}
/**获取数据的最后修改信息 */
export function getLastEditUserInfo (data, returnType = 'name', createBy = 'createBy', createTime = 'createTime', updateBy = 'updateBy', updateTime = 'updateTime') {
  if (data) {
    let returnProp = returnType === 'time' ? returnType : 'name'// name or time
    if (data[createTime] && data[updateTime]) {
      return returnType === 'time' ? data[updateTime] : data[updateBy]
    } else if (data[createTime]) {
      return returnType === 'time' ? data[createTime] : data[createBy]
    }
    else if (data[updateTime]) {
      return returnType === 'time' ? data[updateTime] : data[updateBy]
    }
    else {
      return ''
    }
  } else {
    return ''
  }
}
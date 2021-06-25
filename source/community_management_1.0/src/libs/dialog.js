import HttpRequest from '@/libs/axios'
import { MessageBox, Message } from 'element-ui'
import axios from 'axios'
axios.defaults.headers.post['Content-Type'] = 'application/json'
axios.defaults.timeout = 15000

// 确认框 type参数 // info success warning error
export const conform = (title, msg, type) => {
  MessageBox({
    title: title,
    message: msg,
    confirmButtonText: '确定',
    type: type
  }).then(() => {
    tips('成功', 'success')
  }).catch(() => {
  });
}

// 提醒信息
export const tips = (msg, type) => {
  Message({
    message: msg,
    type: type,
    duration: 3 * 1000
  })
}

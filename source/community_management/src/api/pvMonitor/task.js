import request from '@/utils/request'

// 查询任务列表
export function getTaskList (query) {
  return request({
    url: '/isstechadmin/task/list',
    method: 'get',
    params: query
  })
}
// 添加任务
export function addTask (data) {
  return request({
    url: '/isstechadmin/task',
    method: 'post',
    data: data
  })
}
// 修改任务
export function editTask (data) {
  return request({
    url: '/isstechadmin/task',
    method: 'put',
    data: data
  })
}
// 删除任务
export function delTask (data) {
  return request({
    url: '/isstechadmin/task/deleteBySuspectId',
    method: 'delete',
    data: data
  })
}

// 更新任务状态
export function updateEnable (data) {
  return request({
    url: '/isstechadmin/task/updateEnable',
    method: 'post',
    data: data
  })
}

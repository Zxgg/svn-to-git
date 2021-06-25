import request from '@/utils/request'

// 查询人员通行列表
export function listPass_personnel (query,caringType='00') {
  let url='/passManage/pass_personnel/list'+'?careType='+caringType

  return request({
    url: '/passManage/pass_personnel/list',
    method: 'get',
    params: query
  })
}

// 查询人员通行详细
export function getPass_personnel (faceId) {
  return request({
    url: '/passManage/pass_personnel/' + faceId,
    method: 'get'
  })
}

// 新增人员通行
export function addPass_personnel (data) {
  return request({
    url: '/passManage/pass_personnel',
    method: 'post',
    data: data
  })
}

// 修改人员通行
export function updatePass_personnel (data) {
  return request({
    url: '/passManage/pass_personnel',
    method: 'put',
    data: data
  })
}

// 删除人员通行
export function delPass_personnel (faceId) {
  return request({
    url: '/passManage/pass_personnel/' + faceId,
    method: 'put'
  })
}

// 导出人员通行
export function exportPass_personnel (query) {
  return request({
    url: '/passManage/pass_personnel/export',
    method: 'get',
    params: query
  })
}

//查询关爱人员通行
export function listCarePass_personnel (query) {
  return request({
    url: '/passManage/pass_personnel/getCarePassList',
    method: 'get',
    params: query
  })
}
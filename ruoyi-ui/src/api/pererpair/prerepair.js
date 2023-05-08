import request from '@/utils/request'

// 查询预检修列表
export function listPrerepair(query) {
  return request({
    url: '/system/prerepair/list',
    method: 'get',
    params: query
  })
}

// 查询预检修详细
export function getPrerepair(overhaulId) {
  return request({
    url: '/system/prerepair/' + overhaulId,
    method: 'get'
  })
}

// 新增预检修
export function addPrerepair(data) {
  return request({
    url: '/system/prerepair',
    method: 'post',
    data: data
  })
}

// 修改预检修
export function updatePrerepair(data) {
  return request({
    url: '/system/prerepair',
    method: 'put',
    data: data
  })
}

// 删除预检修
export function delPrerepair(overhaulId) {
  return request({
    url: '/system/prerepair/' + overhaulId,
    method: 'delete'
  })
}

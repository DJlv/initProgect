import request from '@/utils/request'

// 查询库房备件列表
export function listHouse(query) {
  return request({
    url: '/mapper/house/list',
    method: 'get',
    params: query
  })
}

// 查询库房备件详细
export function getHouse(id) {
  return request({
    url: '/mapper/house/' + id,
    method: 'get'
  })
}

// 新增库房备件
export function addHouse(data) {
  return request({
    url: '/mapper/house',
    method: 'post',
    data: data
  })
}

// 修改库房备件
export function updateHouse(data) {
  return request({
    url: '/mapper/house',
    method: 'put',
    data: data
  })
}

// 删除库房备件
export function delHouse(id) {
  return request({
    url: '/mapper/house/' + id,
    method: 'delete'
  })
}

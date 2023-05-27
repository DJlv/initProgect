import request from '@/utils/request'

// 查询库房备件列表
export function listHouse(query) {
  return request({
    url: '/mapper/house/list',
    method: 'get',
    params: query
  })
}
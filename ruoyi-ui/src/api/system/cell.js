import request from '@/utils/request'

// 查询货架格子列表
export function listCell(query) {
  return request({
    url: '/system/cell/list',
    method: 'get',
    params: query
  })
}

// 查询货架格子详细
export function getCell(id) {
  return request({
    url: '/system/cell/' + id,
    method: 'get'
  })
}

// 新增货架格子
export function addCell(data) {
  return request({
    url: '/system/cell',
    method: 'post',
    data: data
  })
}

// 修改货架格子
export function updateCell(data) {
  return request({
    url: '/system/cell',
    method: 'put',
    data: data
  })
}

// 删除货架格子
export function delCell(id) {
  return request({
    url: '/system/cell/' + id,
    method: 'delete'
  })
}

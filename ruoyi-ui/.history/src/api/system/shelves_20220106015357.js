import request from '@/utils/request'

// 查询货架列表
export function listShelves(query) {
  return request({
    url: '/system/shelves/list',
    method: 'get',
    params: query
  })
}
// 查询货架树
export function treeShelves() {
  return request({
    url: '/system/shelves/tree',
    method: 'get'
  })
}
// 查询货架树
export function treeChildrenShelves() {
  return request({
    url: '/system/shelves/children-tree',
    method: 'get'
  })
}
// 查询货架详细
export function getShelves(id) {
  return request({
    url: '/system/shelves/' + id,
    method: 'get'
  })
}

// 新增货架
export function addShelves(data) {
  return request({
    url: '/system/shelves',
    method: 'post',
    data: data
  })
}

// 修改货架
export function updateShelves(data) {
  return request({
    url: '/system/shelves',
    method: 'put',
    data: data
  })
}

// 删除货架
export function delShelves(id) {
  return request({
    url: '/system/shelves/' + id,
    method: 'delete'
  })
}

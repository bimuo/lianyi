import request from '@/utils/request'

// 查询商品所在货柜的关联列表
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
// 查询商品所在货柜的关联详细
export function getShelves(id) {
  return request({
    url: '/system/shelves/' + id,
    method: 'get'
  })
}

// 新增商品所在货柜的关联
export function addShelves(data) {
  return request({
    url: '/system/shelves',
    method: 'post',
    data: data
  })
}

// 修改商品所在货柜的关联
export function updateShelves(data) {
  return request({
    url: '/system/shelves',
    method: 'put',
    data: data
  })
}

// 删除商品所在货柜的关联
export function delShelves(id) {
  return request({
    url: '/system/shelves/' + id,
    method: 'delete'
  })
}

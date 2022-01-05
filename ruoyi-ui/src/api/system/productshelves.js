import request from '@/utils/request'

// 查询商品所在货柜的关联列表
export function listProductshelves(query) {
  return request({
    url: '/system/productshelves/list',
    method: 'get',
    params: query
  })
}

// 查询商品所在货柜的关联详细
export function getProductshelves(id) {
  return request({
    url: '/system/productshelves/' + id,
    method: 'get'
  })
}

// 新增商品所在货柜的关联
export function addProductshelves(data) {
  return request({
    url: '/system/productshelves',
    method: 'post',
    data: data
  })
}

// 修改商品所在货柜的关联
export function updateProductshelves(data) {
  return request({
    url: '/system/productshelves',
    method: 'put',
    data: data
  })
}

// 删除商品所在货柜的关联
export function delProductshelves(id) {
  return request({
    url: '/system/productshelves/' + id,
    method: 'delete'
  })
}

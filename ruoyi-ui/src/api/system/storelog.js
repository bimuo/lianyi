import request from '@/utils/request'

// 查询出入库记录列表
export function listStorelog(query) {
  return request({
    url: '/system/storelog/list',
    method: 'get',
    params: query
  })
}

// 查询出入库记录详细
export function getStorelog(id) {
  return request({
    url: '/system/storelog/' + id,
    method: 'get'
  })
}

// 新增出入库记录
export function addStorelog(data) {
  return request({
    url: '/system/storelog',
    method: 'post',
    data: data
  })
}

// 修改出入库记录
export function updateStorelog(data) {
  return request({
    url: '/system/storelog',
    method: 'put',
    data: data
  })
}

// 删除出入库记录
export function delStorelog(id) {
  return request({
    url: '/system/storelog/' + id,
    method: 'delete'
  })
}

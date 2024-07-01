import request from '@/utils/request'

// 查询asset列表
export function listAsset(query) {
  return request({
    url: '/asset/asset/list',
    method: 'get',
    params: query
  })
}


// 查询asset列表
export function getSymbol(query) {
  return request({
    url: '/asset/asset/querySymbol',
    method: 'get',
    params: query
  })
}

// 查询asset详细
export function getAsset(id) {
  return request({
    url: '/asset/asset/' + id,
    method: 'get'
  })
}

// 新增asset
export function addAsset(data) {
  return request({
    url: '/asset/asset',
    method: 'post',
    data: data
  })
}

// 修改asset
export function updateAsset(data) {
  return request({
    url: '/asset/asset',
    method: 'put',
    data: data
  })
}

// 删除asset
export function delAsset(id) {
  return request({
    url: '/asset/asset/' + id,
    method: 'delete'
  })
}

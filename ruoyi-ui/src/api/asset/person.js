import request from '@/utils/request'

// 查询person列表
export function listPerson(query) {
  return request({
    url: '/asset/person/list',
    method: 'get',
    params: query
  })
}

// 查询person详细
export function getPerson(id) {
  return request({
    url: '/asset/person/' + id,
    method: 'get'
  })
}

// 新增person
export function addPerson(data) {
  return request({
    url: '/asset/person',
    method: 'post',
    data: data
  })
}

// 修改person
export function updatePerson(data) {
  return request({
    url: '/asset/person',
    method: 'put',
    data: data
  })
}

// 删除person
export function delPerson(id) {
  return request({
    url: '/asset/person/' + id,
    method: 'delete'
  })
}

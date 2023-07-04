import request from '@/utils/request'

export function register(data) {
  return request({
    url: '/register',
    method: 'post',
    data: data
  })
}

export function reqLogin(data) {
  return request({
    url: '/login',
    method: 'post',
    data: data
  })
}
//reset data
export function resPwd(data) {
  return request({
    url: '/requestReset',
    method: 'post',
    params: data
  })
}
//reset password
export function resetPwd(data) {
  return request({
    url: '/requestReset/reset',
    method: 'post',
    params: data
  })
}

export function verifyEmail(data) {
  return request({
    url: '/verifyEmail',
    method: 'post',
    params: data
  })
}

export function getUserInfo(data) {
  return request({
    url: '/getUserByToken',
    method: 'post',
    data
  })
}

export function reqLogout(data) {
  return request({
    url: '/logout',
    method: 'post',
    data
  })
}

export function reqDisconnect(data) {
  return request({
    url: '/disconnect',
    method: 'post',
    params: data
  })
}

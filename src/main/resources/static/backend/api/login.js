function loginApi(data) {
  return $axios({
    'url': '/index/login',
    'method': 'post',
    data
  })
}

function logoutApi(){
  return $axios({
    'url': '/index/logout',
    'method': 'post',
  })
}

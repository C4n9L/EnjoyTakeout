function getUserInfo() {
    return $axios({
        'url': '/user/info',
        'method': 'get',
    })
}
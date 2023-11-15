//获取店铺列表
const getShopPage = (params) =>{
    return $axios({
        url: '/shop/page',
        method: 'get',
        params
    })
}
//添加店铺
const addShop = (params) =>{
    return $axios({
        url: '/shop',
        method: 'post',
        data: { ...params}
    })
}

const queryShopById = (id) =>{
    return $axios({
        url: `/shop/${id}`,
        method: 'get',
        params: {id}
    })
}

//删除店铺
const deleteShopById = (id) =>{
    return $axios({
        url: `/shop/${id}`,
        method: 'delete',
        params: { id }
    })
}

// 查店铺列表的接口
const getShopList = (params) => {
    return $axios({
        url: '/shop/list',
        method: 'get',
        params
    })
}
// 修改接口
const editShop = (params) => {
    return $axios({
        url: '/shop',
        method: 'put',
        data: { ...params }
    })
}
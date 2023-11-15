var web_prefix = '/app'

function imgPath(path){
    return 'http://ruykfrdv7.hn-bkt.clouddn.com/' + path
}

//将url传参转换为数组
function parseUrl(url) {
    // 找到url中的第一个?号
    var parse = url.substring(url.indexOf("?") + 1),
        params = parse.split("&"),
        len = params.length,
        item = [],
        param = {};

    for (var i = 0; i < len; i++) {
        item = params[i].split("=");
        param[item[0]] = item[1];
    }

    return param;
}


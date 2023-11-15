function fetchAreaData() {
    return  fetch('/backend/js/json/data.json')
        .then(response => response.json())
        .then(data => {
            let rs = []
            const provinceList = []
            const cityList = []
            const areaList = []

            for (let i in data) {
                for (let j in data[i]) {
                    let item = {
                        name: data[i][j],
                        value: j + '',
                    }
                    if (i == '86') {
                        provinceList.push(item)
                    } else {
                        item.parent = i
                    }
                    rs.push(item)
                }
            }

            const provinceMap = {};

            // 将 provinceList 转换为对象
            for (let i = 0; i < provinceList.length; i++) {
                const value = provinceList[i].value;
                provinceMap[value] = true;
            }

            for (let i = 0; i < rs.length; i++) {
                let item = rs[i]
                if (provinceMap[item.parent]) {
                    cityList.push(item)
                }
            }

            const cityMap = {};

            // 将 provinceList 转换为对象
            for (let i = 0; i < cityList.length; i++) {
                const value = cityList[i].value;
                cityMap[value] = true;
            }
            for (let i = 0; i < rs.length; i++) {
                let item = rs[i]
                if (cityMap[item.parent]) {
                    areaList.push(item)
                }
            }

            return{
                provinceList,
                cityList,
                areaList
            }
        })
}
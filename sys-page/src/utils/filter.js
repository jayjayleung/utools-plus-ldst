import Vue from 'vue'

/* 格式化日期 */
Vue.filter('dateTime', (date, format = 'yyyy-MM-dd') => {
    if(!date){
        return '';
    }
    date = new Date(date)
    var year = date.getFullYear()
    var month = date.getMonth() + 1
    var day = date.getDate()
    var hour = date.getHours()
    var minute = date.getMinutes()
    var second = date.getSeconds()

    function formatNumber(n) {
        n = n.toString()
        return n[1] ? n : '0' + n
    }
    format = format.replace('yyyy', formatNumber(year))
    format = format.replace('MM', formatNumber(month))
    format = format.replace('dd', formatNumber(day))
    format = format.replace('HH', formatNumber(hour))
    format = format.replace('mm', formatNumber(minute))
    format = format.replace('ss', formatNumber(second))
    return format
})

/* 去除空格 type 1-所有空格 2-前后空格 3-前空格 4-后空格 */
Vue.filter('trim', (value, trim = 1) => {
    switch (trim) {
        case 1:
            return value.replace(/\s+/g, "");
        case 2:
            return value.replace(/(^\s*)|(\s*$)/g, "");
        case 3:
            return value.replace(/(^\s*)/g, "");
        case 4:
            return value.replace(/(\s*$)/g, "");
        default:
            return value;
    }
})

/**
 * 字母大小写切换
 * @param {*} str
 * @param {*} type 1:首字母大写  2：首页母小写 3：大小写转换 4：全部大写 5：全部小写
 */
Vue.filter('changeCase', (str, type = 4) => {
    function ToggleCase(str) {
        var itemText = ""
        str.split("").forEach(
            function (item) {
                if (/^([a-z]+)/.test(item)) {
                    itemText += item.toUpperCase();
                } else if (/^([A-Z]+)/.test(item)) {
                    itemText += item.toLowerCase();
                } else {
                    itemText += item;
                }
            });
        return itemText;
    }
    switch (type) {
        case 1:
            return str.replace(/\b\w+\b/g, function (word) {
                return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            });
        case 2:
            return str.replace(/\b\w+\b/g, function (word) {
                return word.substring(0, 1).toLowerCase() + word.substring(1).toUpperCase();
            });
        case 3:
            return ToggleCase(str);
        case 4:
            return str.toUpperCase();
        case 5:
            return str.toLowerCase();
        default:
            return str;
    }
})

/**
 *字符替换*，隐藏手机号或者身份证号等
 * @param {*} str 需要替换的字符串
 * @param {*} type 1:188*****663  2:***asdas***  3:*****8465asdwq  4:1asd88465as*****
 * @param {*} regArr 替换的格式
 * @param {*} ARepText 替换的符号，默认*
 * @returns
 */
Vue.filter('replaceStr',(str, type=0, regArr=[3,5,3],ARepText)=>{
    var regtext = '',
        Reg = null,
        replaceCount,
        replaceText = ARepText || '*';
    function repeatStr(str, count) {
        var text = '';
        for (var i = 0; i < count; i++) {
        text += str;
        }
        return text;
    }
    if (regArr.length===3 && type === 0) {
        // regArr = regArr || [3,5,3]
        regtext = '(\\w{' + regArr[0] + '})\\w{' + regArr[1] + '}(\\w{' + regArr[2] + '})'
        Reg = new RegExp(regtext);
        replaceCount = repeatStr(replaceText, regArr[1]);
        return str.replace(Reg, '$1' + replaceCount + '$2')
    } else if (regArr.length===3 && type === 1) {
        // regArr = regArr || [3,5,3];
        regtext = '\\w{' + regArr[0] + '}(\\w{' + regArr[1] + '})\\w{' + regArr[2] + '}'
        Reg = new RegExp(regtext);
        var replaceCount1 = repeatStr(replaceText, regArr[0]);
        var replaceCount2 = repeatStr(replaceText, regArr[2]);
        return str.replace(Reg, replaceCount1 + '$1' + replaceCount2)
    } else if (regArr.length===1 && type === 3) {
        // regArr = regArr || [5];
        regtext = '(^\\w{' + regArr[0] + '})'
        Reg = new RegExp(regtext);
        replaceCount = repeatStr(replaceText, regArr[0]);
        return str.replace(Reg, replaceCount)
    } else if (regArr.length===1 && type === 4) {
        // regArr = regArr || [5];
        regtext = '(\\w{' + regArr[0] + '}$)'
        Reg = new RegExp(regtext);
        replaceCount = repeatStr(replaceText, regArr[0]);
        return str.replace(Reg, replaceCount)
    }
})


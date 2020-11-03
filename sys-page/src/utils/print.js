// 打印类属性、方法定义
/* eslint-disable */
const Print = function (dom, options) {
    options = options || {}
    if (!(this instanceof Print)) return new Print(dom, options);
    this.conf = {
        styleStr:'',
        setDomHeightArr: [], // 需要动态获取并设置高度的 元素
        echartDomArr: [], // echart dom
        printBeforeFn: null, // 打印前回调
        printDoneCallBack: null // 打印后回调
    }
    for (const key in this.conf) {
        if (key && options.hasOwnProperty(key)) {
            this.conf[key] = options[key]
        }
    }
    // debugger
    if ((typeof dom) === "string") {
        this.dom = document.querySelector(dom);
    } else {
        this.dom = this.isDOM(dom) ? dom : dom.$el;
    }
    if (this.conf.setDomHeightArr && this.conf.setDomHeightArr.length) {
        this.setDomHeight(this.conf.setDomHeightArr);
    }
    this.init();
};

Print.prototype = {
    /**
     * 初始化
     */
    init: function () {
        var content = this.getStyle() + this.getHtml();
        this.writeIframe(content);
    },
    /**
     * 配置属性扩展
     * @param {Object} obj 
     * @param {Object} obj2 
     */
    extendOptions: function (obj, obj2) {
        for (var k in obj2) {
            obj[k] = obj2[k];
        }
        return obj;
    },
    /**
    	复制原网页所有的样式
    */
    getStyle: function () {
        var str = "",
            styles = document.querySelectorAll('style,link');
        for (var i = 0; i < styles.length; i++) {
            str += styles[i].outerHTML;
        }
        str += `<style>.no-print{display:none;}${this.conf.styleStr}</style>`;
        return str;
    },
    // 表单赋值
    getHtml: function () {
        var inputs = document.querySelectorAll('input');
        var textareas = document.querySelectorAll('textarea');
        var selects = document.querySelectorAll('select');
        // debugger
        for (var k = 0; k < inputs.length; k++) {
            if (inputs[k].type == "checkbox" || inputs[k].type == "radio") {
                if (inputs[k].checked == true) {
                    inputs[k].setAttribute('checked', "checked")
                } else {
                    inputs[k].removeAttribute('checked')
                }
            } else if (inputs[k].type == "text") {
                inputs[k].setAttribute('value', inputs[k].value)
            } else {
                inputs[k].setAttribute('value', inputs[k].value)
            }
        }

        for (var k2 = 0; k2 < textareas.length; k2++) {
            if (textareas[k2].type == 'textarea') {
                textareas[k2].innerHTML = textareas[k2].value
            }
        }

        for (var k3 = 0; k3 < selects.length; k3++) {
            if (selects[k3].type == 'select-one') {
                var child = selects[k3].children;
                for (var i in child) {
                    if (child[i].tagName == 'OPTION') {
                        if (child[i].selected == true) {
                            child[i].setAttribute('selected', "selected")
                        } else {
                            child[i].removeAttribute('selected')
                        }
                    }
                }
            }
        }

        return this.dom.outerHTML;
    },
    
	/**
		创建iframe
	*/
    writeIframe: function (content) {
        // 方法二：
        var w, doc, iframe = document.createElement('iframe'),
            f = document.body.appendChild(iframe);
        console.log('f', f);

        iframe.id = "myIframe";
        iframe.setAttribute('style', 'position:absolute;width:0;height:0;top:-10px;left:-10px;');
        w = f.contentWindow || f.contentDocument;
        doc = f.contentDocument || f.contentWindow.document;
        doc.open();
        doc.write(content);
        doc.close();
        var _this = this
        iframe.onload = function () {
            // 弹出前，回调
            if (_this.conf.printBeforeFn) {
                _this.conf.printBeforeFn({ doc })
            }

            _this.drawEchartImg(doc).then(() => {
                _this.toPrint(w);
                setTimeout(function () {
                    document.body.removeChild(iframe)
                    // 弹出后，回调
                    if (_this.conf.printDoneCallBack) {
                        _this.conf.printDoneCallBack()
                    }
                }, 1000)
            })
        }
    },
    /**
     * 项目用到echarts，需要获取图片，来打印
     * @param {Object} doc iframe window
     */
    drawEchartImg(doc) {
        return new Promise((resolve, reject) => {
            if (this.conf.echartDomArr && this.conf.echartDomArr.length > 0) {
                this.conf.echartDomArr.forEach(e => {
                    const dom = doc.querySelector('#' + e.$el.id)
                    const img = new Image()
                    const w = dom.offsetWidth + 'px'
                    const H = dom.offsetHeight + 'px'

                    img.style.width = w
                    img.style.height = H
                    img.src = e.imgSrc
                    dom.innerHTML = ''
                    dom.appendChild(img)
                })
            }
            resolve()
        })
    },
    /**
		打印
	*/
    toPrint: function (frameWindow) {
        try {
            setTimeout(function () {
                frameWindow.focus();
                try {
                    if (!frameWindow.document.execCommand('print', false, null)) {
                        frameWindow.print();
                    }
                } catch (e) {
                    frameWindow.print();
                }
                frameWindow.close();
            }, 10);
        } catch (err) {
            console.log('err', err);
        }
    },
    isDOM: (typeof HTMLElement === 'object') ?
        function (obj) {
            return obj instanceof HTMLElement;
        } :
        function (obj) {
            return obj && typeof obj === 'object' && obj.nodeType === 1 && typeof obj.nodeName === 'string';
        },
    /**
     * 设置指定dom元素高度，通过获取该dom元素现有高度，并设置
     * @param {Array} arr 
     */
    setDomHeight(arr) {
        if (arr && arr.length) {
            arr.forEach(name => {
                const domArr = document.querySelectorAll(name);
                // debugger
                domArr.forEach(dom => {
                    dom.style.height = dom.offsetHeight + 'px';
                })
            })
        }
    }
};
const MyPlugin = {}
MyPlugin.install = function (Vue, options) {
    // 4. 添加实例方法
    Vue.prototype.$print = Print
};
export default MyPlugin
<template>
    <div>
        <table border="1" cellspacing="0" cellpadding="0" class="common-table">
            <thead>
                <tr>
                    <th>仓库名称</th>
                    <th colspan="3">{{warehouseFrom.name}}</th>
                </tr>
                    <tr>
                    <th>箱号</th>
                    <th>{{warehouseFrom.cartonNo}}</th>
                    <th>总箱数</th>
                    <th>{{warehouseFrom.totalCartons}}</th>
                </tr>
            </thead>
            <thead>
                <tr>
                    <th colspan="2">货物名称</th>
                    <th>数量</th>
                    <th>单位</th>
                </tr>
                    <tr v-for="item in tableDatas" :key="item.id">
                    <th colspan="2">{{item.name}}</th>
                    <th>{{item.num}}</th>
                    <th>{{item.unit}}</th>
                </tr>
            </thead>
            <thead>
                <tr>
                    <th colspan="1">所属单位</th>
                    <th colspan="3">{{warehouseFrom.company}}</th>
                </tr>
                <tr>
                    <th colspan="1">联系人</th>
                    <th colspan="3">{{warehouseFrom.contacts}}</th>
                </tr>
                <tr>
                    <th colspan="1">联系电话</th>
                    <th colspan="3">{{warehouseFrom.phone}}</th>
                </tr>
                    <tr>
                    <th colspan="2">备注</th>
                    <th colspan="2">二维码</th>
                </tr>
                    <tr>
                    <th colspan="2">
                        <div class="tableDiv"></div>
                    </th>
                    <th colspan="2">
                        <div class="tableDiv">
                            <img :src="qrCodeImg" style="height:100px;" alt="" />
                        </div>
                    </th>
                </tr>
            </thead>
        </table>
    </div>
</template>

<script>
export default {
    data(){
        return{
            qrCodeImg:'',
            tableDatas: [],
            dialogFormVisible:false,
            showPrint:true,
             warehouseFrom:{
                name:'',
                cartonNo:'', 
                totalCartons:'', 
                company:'', 
                contacts:'', 
                phone:'', 
            },
            infoForm:{
                name:'',
                num:'',
                unit:'台',
                warehouseId:this.$route.query.id,
            },
        }
    },
    created(){
        this.getWarehouse(this.$route.query.id)
        this.getWarehouseGoods(this.$route.query.id);
        this.getQrCodeImg()
    },
    methods:{
         getQrCodeImg(){
            let baseUrl = "http://"+window.location.host+"/#/mobile/mwarthouseInfo?id="+this.$route.query.id
            console.log(baseUrl)
            this.qrCodeImg = process.env.VUE_APP_BASEURL+"/qr/createCommonQRCode?url="+encodeURIComponent(baseUrl)
        },
        getWarehouse(val){
            this.$api.warehouseApi.getByIdofQrCode({id:val}).then((res) => {
                if(res.success){
                    this.warehouseFrom = res.data
                }
            })
        },
        getWarehouseGoods(val){
            this.$api.warehouseGoodsApi.listOfQrCode({id:val}).then((res) => {
                console.log(res)
                if(res.success){
                    this.tableDatas = res.list
                }else{
                    this.$message.error(res.msg)
                }
            })
        },
    }
}
</script>
<style lang="scss">


    .common-table{
        margin: 15px;
        width: 90%;
        td,th{
            text-align: center;
            // padding: 12px 0;
            line-height: 10rem;
            vertical-align: middle;
        }
        th{
            font-weight: bold;
            white-space: nowrap;
        }
  }
  .tableDiv{
      width: 100%;
    //   margin: 0 auto !important;
      text-align: center;
      height: 150px;
  }
</style>
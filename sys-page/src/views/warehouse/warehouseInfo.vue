<template>
    <div class="contenBox">
        <el-form :model="warehouseFrom" label-width="100px">
            <el-form-item label="仓库名称：" >
                <el-input v-model="warehouseFrom.name"  autocomplete="off" ></el-input>
            </el-form-item>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="箱号：" >
                        <el-input v-model="warehouseFrom.cartonNo"  autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="总箱数：" >
                        <el-input v-model="warehouseFrom.totalCartons"  autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-form-item label="所属单位：" >
                <el-row  type="flex" justify="start" :gutter="20">
                    <el-col :span="8">
                        <el-autocomplete
                            class="inline-input"
                            v-model="warehouseFrom.company"
                            :fetch-suggestions="querySearch1"
                            placeholder="请输入所属单位"
                            :trigger-on-focus="false"
                            @select="handleSelect"
                        >
                        <el-button slot="append" type="primary" @click="addCommonContact()">添加常用单位</el-button>
                        <template slot-scope="{ item }">
                            <div class="name">{{ item.val }}</div>
                        </template>
                        </el-autocomplete>
                    </el-col>
                    <el-col :span="4">
                        <v-common :type="3" @selectValue="selectValue"></v-common>
                    </el-col>
                </el-row>
            </el-form-item>
                <el-row>
                <el-col :span="12">
                    <el-form-item label="联系人：" >
                        <el-input v-model="warehouseFrom.contacts" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="联系电话：" >
                        <el-input v-model="warehouseFrom.phone" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <el-row>
            <el-col>货物明细：</el-col>
        </el-row>
        <el-row>
           <el-col>
                <el-table :data="tableDatas" border style="width: 100%">
                    <el-table-column prop="name" label="货物名称"></el-table-column>
                    <el-table-column prop="num" label="数量" width="150"></el-table-column>
                    <el-table-column prop="unit" label="单位" width="150"></el-table-column>
                </el-table>
            </el-col>
        </el-row>
        <el-row>
            <el-col>
            <el-button type="primary" size="mini" @click="showAddDialog()">新增条目</el-button>
            </el-col>
        </el-row>
        <el-row justify="center">
                <el-col :span="4" :offset="12">
                <el-button type="primary" @click="showPrintDialog">打印装箱标签</el-button>
                </el-col>
        </el-row>

        <el-dialog title="添加货物" :visible.sync="dialogFormVisible" :center="true">
            <el-form :model="infoForm" :rules="rules" ref="infoForm" label-width="100px">
                <el-form-item label="货物名称：" prop="name">
                    <el-input v-model="infoForm.name" placeholder="请输入仓库名称"></el-input>
                </el-form-item>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="数量：" prop="num">
                            <el-input v-model="infoForm.num" placeholder="请输入仓库名称"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="单位：" prop="unit">
                            <el-select v-model="infoForm.unit">
                            <el-option
                                v-for="item in unitArr"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"
                            ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addSubmit('infoForm')">确 定</el-button>
            </div>
        </el-dialog>

        <div>
            <el-dialog  :visible.sync="showPrint" top="1vh">
                <div ref="print">
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
                                        <img :src="qrCodeImg"  alt="" style="height:150px" />
                                    </div>
                                </th>
                            </tr>
                        </thead>
                    </table>
                </div>
                <div style="margin-top:20px;">
                    <el-row>
                        <el-col :span="20">
                            <el-button @click="showPrint = false">返回</el-button>
                        </el-col>
                        <el-col :span="4">
                            <el-button type="primary" @click="print()">打 印</el-button>
                        </el-col>
                    </el-row>
                </div>
            </el-dialog> 
        </div>       
    </div>
</template>

<script>
export default {
    data(){
        return{
            tableDatas: [],
            list1: [],
            qrCodeImg:'',
            dialogFormVisible:false,
            showPrint:false,
             warehouseFrom:{
                name:'',
                cartonNo:'', 
                totalCartons:'', 
                company:'', 
                contacts:'', 
                phone:'', 
            },
             unitArr: [
                { label: "台", value: "台" },
                { label: "盒", value: "盒" },
                { label: "个", value: "个" },
                { label: "瓶", value: "瓶" },
                { label: "箱", value: "箱" }
            ],
            infoForm:{
                name:'',
                num:'',
                unit:'台',
                warehouseId:this.$route.query.id,
            },
            rules: {
                name: [
                    { required: true, message: '请输入货物名称', trigger: 'blur' }
                ],
                num: [
                    { required: true, message: '请输入数量', trigger: 'blur' }
                ],
                unit: [
                    { required: true, message: '请选择单位', trigger: 'blur' }
                ]
             }
        }
    },
    created(){
        this.init();
        this.getWarehouse(this.$route.query.id)
        this.getWarehouseGoods(this.$route.query.id);
        this.getQrCodeImg();
        this.getList1();
    },
    methods:{
        init(){
            this.warehouseFrom.contacts = this.$store.state.userInfo.name
            this.warehouseFrom.phone = this.$store.state.userInfo.phone
        },
        getQrCodeImg(){
            // let baseUrl = "http://"+window.location.host+"/#/mobile/mwarthouseInfo?id="+this.$route.query.id
            let baseUrl = "http://"+window.location.host+"/#/mobile/mwarthouseInfo?id="+this.$route.query.id
            this.qrCodeImg = process.env.VUE_APP_BASEURL+"/qr/createCommonQRCode?url="+encodeURIComponent(baseUrl)
        },
        getWarehouse(val){
            this.$api.warehouseApi.getById({id:val}).then((res) => {
                if(res.success){
                    this.warehouseFrom.name = res.data.name
                    this.warehouseFrom.cartonNo = res.data.cartonNo
                    this.warehouseFrom.totalCartons = res.data.totalCartons
                }
            })
        },
        getWarehouseGoods(val){
            this.$api.warehouseGoodsApi.list({id:val}).then((res) => {
                console.log(res)
                if(res.success){
                    this.tableDatas = res.list
                }else{
                    this.$message.error(res.msg)
                }
            })
        },
        showAddDialog(){
            this.dialogFormVisible = true
        },
        addSubmit(fromName){
             this.$refs[fromName].validate((valid) => {
                 if(valid){
                     this.$api.warehouseGoodsApi.add({...this.infoForm}).then((res) => {
                        if(res.success){
                            this.dialogFormVisible = false
                            this.$message.success("添加成功")
                            this.getWarehouseGoods(this.$route.query.id);
                        }else{
                            this.$message.error(res.msg)
                        }
                    })
                 }else{
                      console.log("error submit!!");
                 }
             })
        },
         showPrintDialog() {
             this.addCommonContact();
             this.showPrint = true
            
        },
        print(){
            let styles = ".th50{width: 50px;}.th100{width: 100px;}.th150{width: 150px;}";
            this.$print(this.$refs.print,{styleStr:styles,printBeforeFn:this.printBeforeFn,printDoneCallBack:this.printDoneCallBack})
        },
         printBeforeFn(){
            console.log("打印前回调");
        },
        printDoneCallBack(){
            console.log("打印后回调");
            this.showPrint = false;
            this.$confirm('打印成功', '提示', {
            confirmButtonText: '确定',
            showCancelButton:false,
            type: 'success',
            center: true
            })
        },
         addCommonContact() {
            let map = {
                val: this.warehouseFrom.company,
                type: 3
            };
            this.$api.commonApi.add(map).then(res => {
                if (res.success) {
                // this.$message.success("添加成功");
                this.getList1();
                }
            });
        },

        selectValue(item){
            this.warehouseFrom.company = item.val;
        },
        getList1() {
            this.$api.commonApi.getList({ type: 3 }).then(res => {
                if (res.success) {
                this.list1 = res.list;
                }
            });
        },
        handleSelect(item){
            this.warehouseFrom.company = item.val;
        },
        querySearch1(queryString, cb) {
            var restaurants = this.list1;
            var results = queryString
                ? restaurants.filter(this.createFilter(queryString))
                : restaurants;

            console.log(results);
            // 调用 callback 返回建议列表的数据
            cb(results);
        },
        createFilter(queryString) {
            console.log(queryString);
            return restaurant => {
                console.log(restaurant);
                return (
                restaurant.val.toLowerCase().indexOf(queryString.toLowerCase()) === 0
                );
            };
        },
    }
}
</script>
<style lang="scss">

    .contenBox{
        margin: 0 20px 0 20px;
    
    }

    .common-table{
        width: 100%;
        td,th{
            text-align: center;
            padding: 12px 0;
            line-height: 23px;
            vertical-align: middle;
        }
        th{
            font-weight: bold;
            white-space: nowrap;
        }
  }
  .tableDiv{
      width: 100%;
      height: 150px;
  }
</style>
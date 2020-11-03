<template>
    <div>
        <el-row>
            <div>
                <!-- <el-button type="primary" size="small" @click="add">添加</el-button> -->
            </div>
            <el-col>
                <el-table :data="tableDatas" border style="width: 100%;text-align: center;">
                    <el-table-column type="index" label="序号" width="50"></el-table-column>
                    <el-table-column prop="name" label="送货仓库">
                           <template slot-scope="scope">
                             <el-link type="primary" @click="detail(scope.row.id)">{{scope.row.name}}</el-link>
                        </template>
                    </el-table-column>
                    <el-table-column prop="createTime" label="创建时间">
                        <template slot-scope="scope">
                            {{scope.row.createTime | dateTime("yyyy-MM-dd HH:mm:ss")}}
                        </template>
                    </el-table-column>
                </el-table>
            </el-col>
        </el-row>

        <el-dialog title="添加仓库" :visible.sync="dialogFormVisible" :center="true">
            <el-form :model="warehouseFrom" :rules="rules" ref="warehouseFrom" label-width="100px">
                <el-form-item label="仓库名称：" prop="name">
                    <el-input v-model="warehouseFrom.name" placeholder="请输入仓库名称"></el-input>
                </el-form-item>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="箱号：" prop="cartonNo">
                            <el-input v-model="warehouseFrom.cartonNo" placeholder="请输入仓库名称"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="总箱数：" prop="totalCartons">
                            <el-input v-model="warehouseFrom.totalCartons" placeholder="请输入总箱数"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-form-item label="所属单位" prop="company">
                    <el-input v-model="warehouseFrom.company" placeholder="所属单位"></el-input>
                </el-form-item>
                 <el-row>
                    <el-col :span="12">
                        <el-form-item label="联系人：" prop="contacts">
                            <el-input v-model="warehouseFrom.contacts" placeholder="请输入联系人"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="联系电话：" prop="phone">
                            <el-input v-model="warehouseFrom.phone" autocomplete="联系电话"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addSubmit('warehouseFrom')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
export default {
    data(){
        return{
            tableDatas: [],
            dialogFormVisible:false,
            warehouseFrom:{
                name:'',
                cartonNo:'', 
                totalCartons:'', 
                company:'', 
                contacts:'', 
                phone:'', 
            },
             rules: {
                name: [
                    { required: true, message: '请输入仓库名称', trigger: 'blur' }
                ],
                cartonNo: [
                    { required: true, message: '请输入仓库编号', trigger: 'blur' }
                ],
                totalCartons: [
                    { required: true, message: '请输入总箱数', trigger: 'blur' }
                ],
                company: [
                    { required: true, message: '请输入所属单位', trigger: 'blur' }
                ],
                contacts: [
                    { required: true, message: '请输入联系人', trigger: 'blur' }
                ],
                phone: [
                    { required: true, message: '请输入电话', trigger: 'blur' },
                    // { pattern: '/^1[3|4|5|7|8][0-9]{9}$/', message: '请输入正确电话', trigger: 'change' },
                ],
             }
        }
    },
    created(){
        this.getWarehouseList();
    },
    methods:{
        getWarehouseList(){
             this.$api.warehouseApi.list({}).then((res) => {
                 if(res.success){
                     this.tableDatas = res.list
                 }
             })
        },
        add(){
            this.dialogFormVisible = true
        },
        addSubmit(fromName){
            this.$refs[fromName].validate((valid) => {
                if(valid){
                    this.$api.warehouseApi.add({...this.warehouseFrom}).then((res) => {
                        if(res.success){
                            this.dialogFormVisible = false
                            this.$message.success("添加成功")
                            this.getWarehouseList();
                        }else{
                            this.$message.error(res.msg)
                        }
                    })
                }else{
                     console.log("error submit!!");
                }
            })

        },
        detail(val){
            this.$router.push({ path: '/home/warehouseInfo',query:{id:val}})
        }
    }
}
</script>
<style scoped>
    .table-btn{
        float: right;

    }
</style>
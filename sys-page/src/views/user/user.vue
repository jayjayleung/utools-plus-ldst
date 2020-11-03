<template>
  <div>
    <v-table
      :apiUrl="apiUrl"
      :controlArray="controlArray1"
      :operators="operators"
      :columns="columns"
      :checkbox='false'
      ref="vtable"
    ></v-table>

    <div>
        <el-dialog title="查看角色" width="605px" :visible.sync="lookVisible">
        <div>
          <el-row>
            <el-col class="item" :span="12">
              <label class="title">帐号</label>
              <p>{{currentRow.account}}</p>
            </el-col>
            
            <el-col class="item" :span="12">
              <label class="title">姓名</label>
              <p>{{currentRow.name}}</p>
            </el-col>
            
          </el-row>
          <el-row>
              
            <el-col class="item" :span="12">
              <label class="title">电话</label>
              <p>{{currentRow.phone}}</p>
            </el-col>
            
            <!-- <el-col class="item" :span="8">
              <label class="title">所属企业</label>
              <p>{{currentRow.company}}</p>
            </el-col> -->
            
            <el-col class="item" :span="12">
              <label class="title">创建时间</label>
              <p>{{currentRow.createTime | dateTime}}</p>
            </el-col>
          </el-row>
        </div>
      </el-dialog>
      <el-dialog :title="addTitle" width="600px" :visible.sync="addVisible" label-position="top">
        <el-form :model="formObj" :rules="formRules" ref="formObj">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="帐号" label-width="auto" prop="account">
                <el-input v-model="formObj.account" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="密码" label-width="auto" prop="password" >
                <el-input v-model="formObj.password" autocomplete="off" type="password"  @input="passwrod"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="姓名" label-width="auto" prop="name">
                <el-input v-model="formObj.name"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="联系方式" label-width="auto" prop="phone">
                <el-input v-model="formObj.phone"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <!-- <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="所属企业" label-width="auto" prop="company">
                <el-input  v-model="formObj.company"></el-input>
              </el-form-item>
            </el-col>
          </el-row> -->
        </el-form>

        <!-- <img src="http://127.0.0.1:9527/sys/bgo-wiki/uploadImg/87b44fce5b00bd2e28b5c85d41ad943d.jpg" class="avatar"> -->

        <div slot="footer" class="dialog-footer">
          <el-button @click="cancle()">取 消</el-button>
          <el-button type="primary" @click="submit()">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      oldData:'',
      currentRow: {},
      addTitle: "",
      lookVisible: false,
      addVisible: false,
      formObj: {},
      formRules: {},
      apiUrl: "/user/list",
      flag: "add",
      controlArray1: [ 
        {
          index: 0,
          text: "帐号",
          type: "input",
          key: "accountlikes",
          value: null,
          show: true
          //   placeholder: "可以多个用户名称，以空格分开。"
          // options: this.transferTypeArr,//下拉选项 格式为数组
        },
        {
          index: 1,
          text: "姓名",
          type: "input",
          key: "namelikes",
          value: null,
          show: true
          //   placeholder: "可以多个用户名称，以空格分开。"
          // options: this.transferTypeArr,//下拉选项 格式为数组
        },
        {
          index: 2,
          text: "联系方式",
          type: "input",
          key: "phonelikes",
          value: null,
          show: true
          //   placeholder: "可以多个用户名称，以空格分开。"
          // options: this.transferTypeArr,//下拉选项 格式为数组
        }
      ],
      operators: [
        {
          type: "primary",
          icon: 'el-icon-plus',
          text: "新增",
          click: item => {
            // console.log("新增");
            this.add();
          }
        }
      ],
      columns: [
        {
          text: "操作",
          width: 150,
          buttons: [
            {
              text: "查看",
              click: item => {
                this.lookView(item);
              }
            },
            {
              text: "编辑",
              click: item => {
                this.edit(item);
              }
            },
            {
              text: "删除",
              click: item => {
                this.remove(item);
              }
            }
          ]
        },
        {
          text: "帐号",
          index: "account",
          iShowSort: true
        },
        {
          text: "姓名",
          index: "name",
          iShowSort: true,
        },
        {
          text: "联系方式",
          index: "phone",
          iShowSort: true,
          //   pipe: this.typeArr
        },
        {
          text: "创建时间",
          width: 160,
          index: "createTime",
          iShowSort: true,
          render: item => {
            // console.log(item);
            let date = new Date(item["createTime"]);
            let day =
              date.getMonth() + 1 > 10
                ? date.getMonth() + 1
                : "0" + (date.getMonth() + 1);
            let month =
              date.getDate() + 1 > 10
                ? date.getDate() + 1
                : "0" + (date.getDate() + 1);
            let hours =
              date.getHours() + 1 > 10
                ? date.getHours() + 1
                : "0" + (date.getHours() + 1);
            let minutes =
              date.getMinutes() + 1 > 10
                ? date.getMinutes() + 1
                : "0" + (date.getMinutes() + 1);
            let seconds =
              date.getSeconds() + 1 > 10
                ? date.getSeconds() + 1
                : "0" + (date.getSeconds() + 1);
            return (
              date.getFullYear() +
              "-" +
              month +
              "-" +
              day +
              " " +
              hours +
              ":" +
              minutes +
              ":" +
              seconds
            );
          }
          //   pipe: this.statusArr
        }
      ],
      roles: [],
      roleIds: []
    };
  },
  methods: {
    passwrod(event){
        console.log(event);
    },
    lookView(item) {
      this.currentRow = item;
      this.lookVisible = true;
    },
    add() {
      this.imageUrl = "";
      this.fileList = [];
      this.flag = "add";
      this.formObj = {};
      this.addVisible = true;
    //   getRoles({}).then(res => {
    //     this.roles = res.data.list;
    //   });
    },
    edit(item) {
        console.log(item);
      this.flag = "edit";
      this.addVisible = true;
      this.formObj = item;
      this.oldData=JSON.parse(JSON.stringify(item)); 
      this.imageUrl = process.env.VUE_APP_BASEURL+'/' + this.formObj.headThumb;
    //   getRoles({}).then(res => {
    //     this.roles = res.data.list;
    //     item.roles.forEach(item => {
    //       this.roleIds.push(item.id);
    //     });
    //   });
    },
    remove(item) {
      this.$confirm("是否删除该记录?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          var params = {
            id: item.id
          };
          this.$api.userApi.remove(params).then(res => {
            this.$refs.vtable.reload();
            this.$message({
              type: "success",
              message: "删除成功!"
            });
          });
        })
        .catch(() => {});
    },
    submitForm(formName) {
      let b = false;
      this.$refs[formName].validate(valid => {
        if (valid) {
          console.log("通过");
          b = true;
        } else {
          console.log("error submit!!");
          b = false;
          return false;
        }
      });
      return b;
    },
    submit() {
      if (this.submitForm("formObj")) {
        if (this.flag == "edit") {
            if(this.oldData.passwrod != this.formObj.passwrod){
                this.formObj.editPassword = true;
            }
          this.$api.userApi.edit(this.formObj).then(res => {
            // console.log(res);
            this.addVisible = false;
            this.$message({
              type: "success",
              message: "编辑成功!"
            });
            this.$refs.vtable.reload();
          });
        } else {
          this.$api.userApi.add(this.formObj).then(res => {
            // console.log(res);
            this.addVisible = false;
            this.$message({
              type: "success",
              message: "新增成功!"
            });

            this.$refs.vtable.reload();
          });
        }
      }
    },
    cancle(){
        this.formObj = this.oldData;
        this.addVisible = false;
    },
    
  },
  created() {
    console.log(process.env.VUE_APP_BASEURL);
    this.formObj = {
      id: "",
      account: "",
      password: "",
      name: "",
      phone: "",
    //   company: "",
      createTime: new Date(),
      isDel: 0,
      isAdmin: 0,
      editPassword: false
    };
    this.formRules = {
      account: [{ required: true, message: "请输入帐号", trigger: "blur" }],
      password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
      phone: [{ required: true, message: "请输入联系方式", trigger: "blur" }],
    //   company: [{ required: true, message: "请输入所属企业", trigger: "blur" }],
    };
  }
};
</script>


<style>
</style>
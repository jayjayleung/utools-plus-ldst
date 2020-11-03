<template>
  <div class="login-body">
    <div class="login-form" style="color:balck">
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="24">
          <h2 style="color:black;">注册帐号</h2>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="24">
          <el-form ref="user" :model="user" status-icon :rules="rules" size="medium ">
            <el-form-item prop="account" label="帐号:">
              <el-col :span="24">
                <el-input v-model="user.account">
                </el-input>
              </el-col>
            </el-form-item>
            <el-form-item prop="password" label="密码:">
              <el-col :span="24">
                <el-input :type="pwdInputType" v-model="user.password">
                  <i
                    slot="suffix"
                    @click="changePwdType"
                    class="elm-iconInput iconfont"
                    :class="pwdInputType == 'password'?'elm-icon-eye-close':'elm-icon-eye'"
                  ></i>
                </el-input>
              </el-col>
            </el-form-item>
             <el-form-item prop="name"  label="姓名:">
              <el-col :span="24">
                <el-input v-model="user.name">
                </el-input>
              </el-col>
            </el-form-item>
             <el-form-item prop="phone" label="联系方式:">
              <el-col :span="24">
                <el-input v-model="user.phone">
                </el-input>
              </el-col>
            </el-form-item>
            <el-form-item size="large">
                <el-button type="primary" @click="back()">返回</el-button>
                <el-button type="primary" @click="register()">注册</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      user: {
        account: "",
        password: "",
        name:"",
        phone:"",
      },
      pwdInputType: "password",
      rules: {
        account: [
          { required: true, message: "帐号不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" },
          { min: 3, message: "长度不能少于3个字符", trigger: "blur" },
        ],
      name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
      phone: [{ required: true, message: "请输入联系方式", trigger: "blur" }],
      }
    };
  },
  methods: {
    changePwdType: function() {
      this.pwdInputType = this.pwdInputType == "password" ? "text" : "password";
    },
    register(){
       this.$refs['user'].validate(valid => {
        if (valid) {
            this.$api.loginApi.register(this.user).then(res => {
            // console.log(res);
            this.$message({
              type: "success",
              message: "注册成功!"
            });
            
            this.$router.push({ path: '/login'});
          });
        } else {
          console.log("error submit!!");
        }
      });
    },
    back(){
      this.$router.push({ path: '/login'});
    }
  },
  created() {
  }
};
</script>
<style lang="scss">
$bg: #303133;
$dark_gray: #889aa4;
.login-body {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  background: url("./../../assets/images/4.jpg"), no-repeat;
  overflow: hidden;
  text-align: center;
}

.login-form {
  border: 1px solid $dark_gray;
  border-radius: 5px;
  width: 20%;
  padding: 5px 35px 0;
  margin: 160px auto;
  background-color: rgba(255, 255, 255, 0.2);
}
.el-form-item__label{
    color: black !important;
}
</style>


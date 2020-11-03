<template>
  <div class="login-body">
    <div class="login-form">
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="24">
          <h2 style="color:black;">系统登录</h2>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="24">
          <el-form ref="user" :model="user" status-icon :rules="rules" size="medium ">
            <el-form-item prop="account">
              <el-col :span="24">
                <el-input v-model="user.account">
                  <i slot="prefix" class="el-input__icon el-icon-user-solid"></i>
                </el-input>
              </el-col>
            </el-form-item>
            <el-form-item prop="password">
              <el-col :span="24">
                <el-input :type="pwdInputType" v-model="user.password">
                  <i slot="prefix" class="elm-iconInput iconfont elm-icon-password"></i>
                  <i
                    slot="suffix"
                    @click="changePwdType"
                    class="elm-iconInput iconfont"
                    :class="pwdInputType == 'password'?'elm-icon-eye-close':'elm-icon-eye'"
                  ></i>
                </el-input>
              </el-col>
            </el-form-item>
            <el-form-item size="large">
              <el-col :span="12">
                <el-button type="primary" @click="register()">注册帐号</el-button>
              </el-col>
              <el-col :span="12">
                <el-button type="primary" @click="submitForm('user')">登录</el-button>
              </el-col>
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
        password: ""
      },
      pwdInputType: "password",
      rules: {
        account: [
          { required: true, message: "帐号不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" },
          { min: 3, message: "长度不能少于3个字符", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    changePwdType: function() {
      this.pwdInputType = this.pwdInputType == "password" ? "text" : "password";
    },
    loginClick() {
        this.$api.loginApi.login(this.user).then(res => {
          console.log(res);
          if(res.success){
            //存储用户信息
            console.log(res.data)
            this.$store.commit("setUserInfo",res.data);
            this.$router.push({ path: '/home'})
          }else{
            this.$message.error(res.msg)
          }
        });
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loginClick();
        } else {
          console.log("error submit!!");
        }
      });
    },
    register(){
      this.$router.push({ path: '/register'});
    }
  },
  created() {
  }
};
</script>
<style lang="scss" scoped>
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
</style>


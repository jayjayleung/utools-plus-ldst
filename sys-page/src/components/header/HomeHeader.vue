<!--
 * @Description: 
 * @Author: jayjay
 * @Github: 
 * @Date: 2020-06-05 21:14:55
 * @LastEditors: jayjay
 * @LastEditTime: 2020-06-06 16:30:41
--> 
<template>
  <el-header class="header">
    <el-row class="header-row" type="flex" justify="start" align="middle">
      <el-col :span="1" class="menuBtn">
        <el-button size="mini" @click="sendCollapse" :icon="iconClass"></el-button>
      </el-col>
      <el-col :span="4" :push="21" style="height:100%">
        <el-dropdown v-if="userInfo">
          <span  class="el-dropdown-link userInfo">
            <span class="tx">
              <img v-if="userInfo.headThumb" :src="url+userInfo.headThumb" alt="admin头像" />
              <img v-else src="@/assets/images/tx.jpg" alt="admin头像" />
            </span>
            {{ userInfo.name}}
            <i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <!-- <el-dropdown-item>用户信息</el-dropdown-item> -->
            <el-dropdown-item>
              <span @click="loginOut">退出登录</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>
      
    </el-row>
  </el-header>
</template>

<script>
export default {
  name: "sys-header",
  props: ["userInfo"],
  data() {
    return {
      isCollapse: false,
      openClass: "el-icon-s-fold",
      closeClass: "el-icon-s-unfold",
      iconClass:'el-icon-s-fold',
      url:this.global.serviceUrl
      
    };
  },
  created() {},
  methods: {
    menuOpenOrClose() {
      this.isCollapse = !this.isCollapse;
      if(this.isCollapse){
        this.iconClass = this.closeClass;
      }else{
        this.iconClass = this.openClass;
      }
    },
    sendCollapse() {
      this.menuOpenOrClose();
      this.$emit("isCollapse", this.isCollapse);
    },
    loginOut() {
        
      this.$api.loginApi.logOut().then(res=>{
        this.$store.commit("LOG_OUT");
        this.$router.push({ path: "/login" });
         this.$message({
                type: "success",
                message: "退出登录成功"
              });
      });
    }
  }
};
</script>

<style lang="scss">
.header{
  border: 1px solid #e1e1e1; 
  background-color: #ffffff !important;
}
.header-row {
  height: 100%;
  min-height: 100%;
}
.menuBtn {
  position: relative;
  right: 20px;
  button {
    border: none !important;
    font-size: 24px;
  }
}
</style>
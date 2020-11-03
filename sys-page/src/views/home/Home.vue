<template>
  <div class="home-body">
    <el-container class="container">
      <home-aside :isCollapse="isCollapse" :userInfo="userInfo" :active="active" @getItem="getItem"></home-aside>
      <el-container direction="vertical" class="container">
        <!-- 头部 -->
        <home-header :userInfo="userInfo" @isCollapse="getIsCollapse" :isCollapse="isCollapse"></home-header>

        <!-- 主体 -->
        <!-- <el-container class="mian-header" style="margin-left:10px;display: block;height:0px"> -->
        <!-- <el-main class="mian-nav"> -->
        <div class="header-nav">
          <el-tabs
            v-model="editableTabsValue"
            type="card"
            closable
            @tab-remove="removeTab"
            @tab-click="tabClick"
          >
            <el-tab-pane
              v-for="item in editableTabs"
              :key="item.name"
              :label="item.title"
              :name="item.name"
            ></el-tab-pane>
          </el-tabs>
        </div>
        <!-- </el-main> -->
        <!-- </el-container> -->
        <el-main class="mian">
          <div class="main-body">
            <router-view />
          </div>
        </el-main>
        <!-- 脚部 -->

      </el-container>
    </el-container>
  </div>
</template>

<script>
// import { userInfo, loginOut } from "@/api/login";
import HomeHeader from "@/components/header/HomeHeader";
import HomeAside from "@/components/aside/HomeAside";
import HomeFooter from "@/components/footer/HomeFooter";
import store from "@/store";
import _ from "lodash";
export default {
  name: "home",
  components: {
    HomeHeader,
    HomeAside,
    HomeFooter
  },
  data() {
    return {
      userInfo: {},
      isCollapse: false,
      editableTabsValue: "index",
      active:"",
      editableTabs: [
        {
          title: "首页",
          name: "index",
          url: "/index"
        }
      ],
      tabIndex: 2
    };
  },
  methods: {
    getIsCollapse(params) {
      console.log(params);
      this.isCollapse = params;
    },
    loginOut() {
      console.log("清除token和用户信息");
      store.commit("loginOut");
    },
    tabClick(tab, event) {
       this.editableTabsValue = tab.name;
       let url = "";
       this.editableTabs.forEach(item=>{
          if(item.name == tab.name){
            url = item.url;
            return;
          }
       });
       if (url == "") {
        url = "/home/test";
      }
      // console.log(url);
      let perUrl = '';
      if(tab.name!='index'){
        perUrl = '/home'
      }
      this.active = tab.name;
      this.$router.push({ path: perUrl+url });
    },
    addTab(targetName) {
      let newTabName = ++this.tabIndex + "";
      this.editableTabs.push({
        title: "New Tab",
        name: newTabName,
        content: "New Tab content"
      });
      this.editableTabsValue = newTabName;
    },
    removeTab(targetName) {
      let tabs = this.editableTabs;
      let activeName = this.editableTabsValue;
      if (activeName === targetName) {
        tabs.forEach((tab, index) => {
          if (tab.name === targetName) {
            let nextTab = tabs[index + 1] || tabs[index - 1];
            if (nextTab) {
              activeName = nextTab.name;
              this.active = nextTab.name;
              console.log(nextTab);
              this.$router.push({ path: '/home'+nextTab.url });
            }
          }
        });
      }

      this.editableTabsValue = activeName;
      this.editableTabs = tabs.filter(tab => tab.name !== targetName);
    },
    getItem(item){
      console.log(item);
      console.log(item.menuUrl);
      console.log(item.menuCode);
      let obj = {
         title: item.menuName,
         name: item.menuCode,
         url: item.menuUrl
      }
      let isHas = false;
      this.editableTabs.forEach(o=>{
        if(item.menuCode == o.name){
          isHas = true;
          return;
        }
      });
      if(!isHas){
        this.editableTabs.push(obj);
      }
      this.editableTabsValue = item.menuCode;
    }
  },
  created() {
    console.log(this.$store.state.userInfo)
    this.userInfo = this.$store.state.userInfo;
    // if(this.userInfo==null || this.userInfo){
    //   this.$router.push({ path: "/home/index" });
    // }
    // if(this.userInfo==null || this.userInfo){
    //   userInfo().then(res=>{
    //     console.log(res);
    //     if(res.data){
    //       store.commit("setUser",res.data.object);
    //       this.userInfo = res.data.object;
    //       console.log(this.userInfo);
    //       if (this.editableTabsValue == "index") {
    //         this.$router.push({ path: "/home/index" });
    //       }
    //     }
    //   });
    // }else{
    //   console.log(this.userInfo);
    //   if (this.editableTabsValue == "index") {
    //     this.$router.push({ path: "/home/index" });
    //   }
    // }
  }
};
</script>

<style lang="scss">
.home-body {
  min-height: 100%;
  height: 100%;
  overflow-x: hidden;
  background-color: #f3f7fa;
}


.el-tabs__header {
  margin: 0 !important;
  padding: 0 !important;
}

.header-nav {
  // margin: 5px 0 5px 0; 
  background-color: #ffffff;
  padding-left: 10px; 
}

.mian {
  margin: 10px;
  background-color: #ffffff;
  color: #333;
  // text-align: center;
  // line-height: 160px;
  // height: 100%;
  min-height: 80% !important;
  padding: 5px;
  overflow-x: hidden;
}

.home-body > .container {
  // margin-bottom: 40px;
  min-height: 100%;
  height: 100%;
}

.tx > img {
  width: 30px;
  height: 30px;
  position: relative;
  top: 9px;
}

// .userInfo {
//   width: 100%;
//   height: 30px;
// }



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

.main-body {
  margin: 2px;
  min-height: 100%;
  height: 100%;
  display: block;
  background-color: #ffffff;
}
</style>
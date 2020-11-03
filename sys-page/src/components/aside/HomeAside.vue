<template>
  <el-aside class="aside" :width="!isCollapse?asideMaxWidth:asideMinWidth">
    <div>
      <el-row type="flex" justify="start" align="middle">
        <el-col :span="2">
          <h3>
            <img width="50px" height="50px" src="@/assets/logo.png" />
          </h3>
        </el-col>
        <el-col v-if="!isCollapse">
          <h3 class="menu-title">系统管理</h3>
        </el-col>
      </el-row>
    </div>
    <el-menu
      v-if="menuInitJson"
      class="sys-menu menuList"
      @open="handleOpen"
      @close="handleClose"
      :collapse="isCollapse"
      :text-color="textColor"
      :background-color="bgColor"
      :default-active="active"
      :collapse-transition="true"
    >
      <!-- 菜单列表，目前最多支持三级 -->
      <template v-for="(item, index) in menuInitJson">
        <el-submenu
          :key="index"
          :index="item.menuCode"
          v-if="item.itemMenus!=null && item.itemMenus.length >0"
        >
          <template slot="title">
            <i v-if="item.menuIcon && item.menuIcon!=''" :class="iconClass(item.menuIcon)"></i>
            <span slot="title">{{item.menuName}}</span>
          </template>
          <el-menu-item-group v-if="item.itemMenus">
            <template v-for="(item2, index2) in item.itemMenus">
              <template v-if="item2.itemMenus && item2.itemMenus.length>0">
                <el-submenu :key="index2" :index="item2.menuCode" @click="gotoUrl(item2)">
                  <i v-if="item2.menuIcon && item2.menuIcon!=''" :class="iconClass(item2.menuIcon)"></i>
                  <span slot="title">{{item2.menuName}}</span>
                  <template v-for="(item3, index3) in item2.itemMenus">
                    <el-menu-item
                      :key="index3"
                      :index="item3.menuCode"
                      @click="gotoUrl(item3)"
                    >
                      <i
                        v-if="item3.menuIcon && item3.menuIcon!=''"
                        :class="iconClass(item3.menuIcon)"
                      ></i>
                      <span slot="title">{{item3.menuName}}</span>
                    </el-menu-item>
                  </template>
                </el-submenu>
              </template>

              <template v-if="!item2.itemMenus || item2.itemMenus.length == 0">
                <el-menu-item
                  :key="index2"
                  :index="item2.menuCode"
                  @click="gotoUrl(item2)"
                >
                  <i v-if="item2.menuIcon && item2.menuIcon!=''" :class="iconClass(item2.menuIcon)"></i>
                  <span slot="title">{{item2.menuName}}</span>
                </el-menu-item>
              </template>
            </template>
          </el-menu-item-group>
          <!-- <el-menu-item-group title="分组2">
                  <el-menu-item index="1-3">选项3</el-menu-item>
                </el-menu-item-group>
                <el-submenu index="1-4">
                  <span slot="title">选项4</span>
                  <el-menu-item index="1-4-1">选项1</el-menu-item>
          </el-submenu>-->
        </el-submenu>
        <el-menu-item v-else :key="index" :index="item.menuCode" @click="gotoUrl(item)">
          <i v-if="item.menuIcon && item.menuIcon!=''" :class="iconClass(item.menuIcon)"></i>
          <span slot="title">{{item.menuName}}</span>
        </el-menu-item>
      </template>
    </el-menu>
  </el-aside>
</template>

<script>
import _ from "lodash";
import menuData from '@/common/menu.js';
export default {
  // props: ["isCollapse"],
  props: {
    isCollapse: Boolean,
    userInfo: Object,
    active:"",
  },
  data() {
    return {
      asideMaxWidth: "220px",
      asideMinWidth: "64px",
      bgColor: "#304156",
      textColor: "#bfcbd9",
      menuInitJson: this.$store.state.userInfo.isAdmin?menuData.admin:menuData.nomal
    };
  },
  methods: {
    //菜单展开
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    //菜单折叠
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
     gotoUrl(item) {
      console.log(item);
      console.log(item.menuUrl);
      this.$emit("getItem", item);
      if (item.url == "") {
        item.url = "/home/test";
      }
      let perUrl = "/home";
      console.log(item.menuUrl);
      if (!_.startsWith(item.menuUrl, "/")) {
        perUrl = "/home/";
      }
      this.$router.push({ path: perUrl + item.menuUrl });
    },
    iconClass(calssStr) {
      let perClass = "iconfont ";
      if (_.startsWith(calssStr, "elm")) {
        return perClass + calssStr;
      }
      return calssStr;
    },
    compare(attr) {
      return function(a, b) {
        var val1 = a[attr];
        var val2 = b[attr];
        return val1 - val2;
      };
    }
  },
  created() {}
};
</script>

<style lang="scss">
$bgColor: #409eff;
$bgColor2: #304156;
.el-aside {
  border: 1px solid #e1e1e1;
  color: #333;
  padding: 0;
  margin: 0;
  overflow: hidden;
  height: 100%;
  background-color: $bgColor2;
  // background-color: #304156;
}
.menu-title {
  color: #bfcbd9;
  text-align: center;
}

.sys-menu:not(.el-menu--collapse) {
  width: 220px;
}
</style>
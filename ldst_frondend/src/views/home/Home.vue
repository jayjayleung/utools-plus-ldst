<template>
  <div class="home-body">
    <el-menu
      :default-active="activeindex"
      class="el-menu-demo"
      mode="horizontal"
    >
      <el-menu-item index="1">最新</el-menu-item>
      <el-menu-item index="2">性感美女</el-menu-item>
      <el-menu-item index="3">cosplay</el-menu-item>
      <el-menu-item index="4">游戏女孩</el-menu-item>
      <el-menu-item index="5">美女空姐</el-menu-item>
    </el-menu>
    <div class="main-body">
      <el-row gutter="20">
        <el-col
          :span="6"
          v-for="item in coverList"
          :key="item.id"
        >
          <el-card :body-style="{ padding: '0px' }" shadow="hover" 
            @click="goInfo(item.id)">
            <img :src="url + item.coverImgUrl" class="image" />
            <div style="padding: 14px">
              <span>{{ item.title }}</span>
              <div class="bottom clearfix">
                <time class="time">{{ item.date }}</time>
                <el-button type="text" class="button" @click="goInfo(item.id)">操作按钮</el-button>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
// import { userInfo, loginOut } from "@/api/login";
export default {
  name: "home",
  components: {},
  data() {
    return {
      activeindex: "1",
      coverList: [],
      url: process.env.VUE_APP_FILEURL,
    };
  },
  methods: {
    getNews() {
      let param = {
        page: 1,
        pageSize: 10,
        sort: "date",
        dir: "desc",
      };
      this.$api.ldstApi.coverList(param).then((res) => {
        console.log(res);
        this.coverList = res.data.collection;
      });
    },
    goInfo(id) {
      console.log("1");
      let path = {
        path: "/info",
        query: {
          id: id,
        },
      };
      this.$router.push(path);
    },
  },
  created() {
    console.log(process.env.VUE_APP_BASEURL);
    // console.log(this.$store.state.userInfo)
    // this.userInfo = this.$store.state.userInfo;
    this.getNews();
  },
};
</script>

<style lang="scss">
.image {
  width: 100%;
  display: block;
}
</style>
<template>
  <div class="home-body">
    <el-menu :default-active="activeindex" class="el-menu-demo" mode="horizontal">
      <el-menu-item index="0" @click="getTypeList('')">最新</el-menu-item>
      <template v-for="(item,index) in types">
        <el-menu-item :key="index" :index="(index+1)+''" @click="getTypeList(item)">{{item}}</el-menu-item>
      </template>

      <!-- <el-menu-item index="3">cosplay</el-menu-item>
      <el-menu-item index="4">游戏女孩</el-menu-item>
      <el-menu-item index="5">美女空姐</el-menu-item>-->
    </el-menu>
    <div class="main-body">
      <el-row :gutter="20">
        <el-col :span="6" v-for="item in coverList" :key="item.id">
          <div @click="goInfo(item)">
            <el-card :body-style="{ padding: '0px' }" shadow="hover">
              <img :src="url + item.coverImgUrl" class="image" />
              <div style="padding: 14px">
                <span>{{ item.title }}</span>
                <div class="bottom clearfix">
                  <time class="time">{{ item.date|dateTime }}</time>
                  <!-- <el-button type="text" class="button" @click="goInfo(item)">操作按钮</el-button> -->
                </div>
              </div>
            </el-card>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-row type="flex" justify="center">
            <el-col :span="6">
              <el-pagination
                background
                :current-page="currentPage"
                :page-sizes="pageSizeSelector"
                layout="total, prev, pager, next,sizes"
                :total="totalSize"
                :page-size="pageSize"
                @size-change="sizeChange"
                @current-change="currentChange"
                @prev-click="prevPage"
                @next-click="nextPage"
              ></el-pagination>
            </el-col>
            <el-col :span="6">
              <div></div>
            </el-col>
          </el-row>
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
      activeindex: "0",
      coverList: [],
      url: process.env.VUE_APP_FILEURL,
      //当前第几页
      currentPage: 1,
      //显示多少条
      pageSize: 8,
      //总条数
      totalSize: 0,
      //每页显示个数选择器的选项设置
      pageSizeSelector: [8, 10, 20, 30, 40, 50, 100],
      type: "",
      types: [],
    };
  },
  methods: {
    getList(page, type) {
      this.type = type;
      let param = {
        page: page,
        pageSize: this.pageSize,
        sort: "date",
        dir: "desc",
        map: {
          type: type,
        },
      };
      this.currentPage = page;
      this.$api.ldstApi.coverList(param).then((res) => {
        console.log(res);
        this.coverList = res.data.collection;
        this.totalSize = res.data.totalSize;
      });
    },
    getTypes() {
      this.$api.ldstApi.getTypes({}).then((res) => {
        console.log(res);
        this.types = res.data;
      });
    },
    getTypeList(item) {
      this.currentPage = 1;
      this.getList(this.currentChange, item);
    },
    goInfo(item) {
      console.log("1");
      let path = {
        path: "/info",
        query: {
          id: item.id,
          title: item.title,
        },
      };
      this.$router.push(path);
    },
    //pageSize 改变时会触发(分页)
    sizeChange(pageSize) {
      console.log(pageSize);
      this.pageSize = pageSize;
      //重新获取数据
      this.getList(1, this.type);
    },
    //currentPage 改变时会触发(分页)
    currentChange(currentPage) {
      console.log(currentPage);
      this.currentPage = currentPage;
      //重新获取数据
      this.getList(this.currentPage, this.type);
    },
    //点击上一页按钮改变当前页后触发(分页)
    prevPage(currentPage) {
      console.log(currentPage);
      this.currentPage = currentPage;
      //重新获取数据
      this.getList(this.currentPage, this.type);
    },
    //点击下一页按钮改变当前页后触发(分页)
    nextPage(currentPage) {
      console.log(currentPage);
      this.currentPage = currentPage;
      //重新获取数据
      this.getList(this.currentPage, this.type);
    },
  },
  created() {
    console.log(process.env.VUE_APP_BASEURL);
    // console.log(this.$store.state.userInfo)
    // this.userInfo = this.$store.state.userInfo;
    this.getList(1);
    this.getTypes();
  },
};
</script>

<style lang="scss">
.image {
  width: 100%;
  display: block;
}
</style>

<template>
  <div class="body">
    <el-row>
      <el-col :span="24">
        <el-page-header @back="goBack" :content="title"></el-page-header>
      </el-col>
    </el-row>
    <el-divider></el-divider>
    <el-carousel type="card" width="890px" height="1000px" :autoplay="false">
      <el-carousel-item v-for="item in list" :key="item.id">
        <img :src="url+item.imgUrl" />
      </el-carousel-item>
    </el-carousel>

    <!-- <div style="width:900px;text-algin:center">
      <el-carousel trigger="click" height="1280px" :autoplay="false">
        <el-carousel-item v-for="item in list" :key="item.id">
          <div style="width:800px;height:1260px">
            <el-image style="width: 100%; height: 100%" :src="url+item.imgUrl" ></el-image>
          </div>
        </el-carousel-item>
      </el-carousel>
    </div>-->

    <!-- <div style="width:900px;height:1000px">
        <div>
          <el-image style="width: 100%; height: 100%" :src="url+list[0].imgUrl" :preview-src-list="urls"></el-image>
        </div>
    </div>-->
  </div>
</template>

<script>
export default {
  data() {
    return {
      url: process.env.VUE_APP_FILEURL,
      list: [],
      urls: [],
      title:""
    };
  },
  mounted() {},
  methods: {
    getList(infoId) {
      console.log(infoId);
      this.$api.ldstApi.imgList({ id: infoId }).then((res) => {
        console.log(res.data);
        this.list = res.data;
        this.list.forEach((element) => {
          this.urls.push(this.url + element.imgUrl);
        });
      });
    },
    goBack() {
        console.log('go back');
        this.$router.back(-1);
      }
  },
  created() {
    // online().then(res => {
    //   if (res.data.list) {
    //     this.online = res.data.list;
    //     this.onlineNum = res.data.list.length;
    //   }
    // });
    // console.log();
    this.title = this.$route.query.title;
    this.getList(this.$route.query.id);
  },
};
</script>

<style lang="scss">
img {
  width: auto;
  height: auto;
  max-width: 100%;
  max-height: 100%;
}
</style>
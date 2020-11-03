<template>
  <div class="body"></div>
</template>

<script>
// import { online } from "@/api/user";
export default {
  data() {
    return {
      value: new Date(),
      online: {},
      onlineNum: 0,
    };
  },
  mounted() {},
  methods: {
    getList(infoId) {
      console.log(infoId);
      this.$api.ldstApi.imgList({ id: infoId }).then((res) => {
        console.log(res.data);
      });
    },
  },
  created() {
    // online().then(res => {
    //   if (res.data.list) {
    //     this.online = res.data.list;
    //     this.onlineNum = res.data.list.length;
    //   }
    // });
    console.log();
    this.getList(this.$route.query.id);
  },
};
</script>

<style lang="scss">
</style>
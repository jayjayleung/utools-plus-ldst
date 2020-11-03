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
    getList(id) {
      this.$Api.ldstApi.imgList({ id: id }).then((res) => {
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
    let id = this.$router.query.id;
    console.log(this.$router.query.id);
    this.getList(id);
  },
};
</script>

<style lang="scss">
</style>
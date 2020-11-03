<template>
  <div class="body">
    <div class="rili">
      <el-row :gutter="12">
        <el-col :span="8">
          <el-card shadow="always">
            <el-link :underline="false">当前登录人数:{{onlineNum}}</el-link>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <!-- <div class="echar1">
      <div id="myChart" :style="{width: '300px', height: '300px'}"></div>
    </div> -->
    <div class="tips">
      <el-row  type="flex" justify="center">
        <el-col :span="8">
          <el-card :body-style="{ padding: '0px' }">
            <img

              src="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3838513137,724605189&fm=26&gp=0.jpg"
              class="image"
            />
            <!-- <div style="padding: 14px;">
              <span>好吃的汉堡</span>
              <div class="bottom clearfix">
                <time class="time">{{ currentDate }}</time>
                <el-button type="text" class="button">操作按钮</el-button>
              </div>
            </div> -->
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
// import { online } from "@/api/user";
export default {
  data() {
    return {
      value: new Date(),
      online: {},
      onlineNum: 0
    };
  },
  mounted() {
    this.drawLine();
  },
  methods: {
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("myChart"));
      // 绘制图表
      myChart.setOption({
        title: { text: "在Vue中使用echarts" },
        tooltip: {},
        xAxis: {
          data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
        },
        yAxis: {},
        series: [
          {
            name: "销量",
            type: "bar",
            data: [5, 20, 36, 10, 10, 20]
          }
        ]
      });
    }
  },
  created() {
    // online().then(res => {
    //   if (res.data.list) {
    //     this.online = res.data.list;
    //     this.onlineNum = res.data.list.length;
    //   }
    // });
  }
};
</script>

<style lang="scss">
.rili {
  width: 500px;
  height: 100px;
}
.image{
  width:100%;
  height: 100%;
}
</style>
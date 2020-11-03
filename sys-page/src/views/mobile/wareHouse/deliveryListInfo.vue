<!--
 * @Description: 
 * @Author: jayjay
 * @Github: 
 * @Date: 2020-06-07 18:52:03
 * @LastEditors: jayjay
 * @LastEditTime: 2020-06-07 19:40:44
--> 
<!--
 * @Description: 
 * @Author: jayjay
 * @Github: 
 * @Date: 2020-06-07 18:52:03
 * @LastEditors: jayjay
 * @LastEditTime: 2020-06-07 19:22:50
--> 
<template>
  <div>
      <!-- <el-row>
          <el-col>{{deliveryListInfo.name}}</el-col>
      </el-row> -->
      <table border="1" cellspacing="0" cellpadding="0" class="common-table">
          <tr>
              <th>器材名称</th>
              <th>{{deliveryListInfo.name}}</th>
          </tr>
          <tr>
              <th>器材编码</th>
              <th>{{deliveryListInfo.code}}</th>
          </tr>
          <tr>
              <th>器材型号</th>
              <th>{{deliveryListInfo.code}}</th>
          </tr>
          
          <tr>
              <th>数量</th>
              <th>{{deliveryListInfo.num}}</th>
          </tr>
          <tr>
              <th>单位</th>
              <th>{{deliveryListInfo.unit}}</th>
          </tr>
          <tr>
              <th>等级</th>
              <th>{{deliveryListInfo.grade}}</th>
          </tr>
          <tr>
              <th>器材型号</th>
              <th>{{deliveryListInfo.type}}</th>
          </tr>
          <tr>
              <th>用途</th>
              <th>{{deliveryListInfo.effect}}</th>
          </tr>
          <tr>
              <th>生产厂家</th>
              <th>{{deliveryListInfo.productionCompany}}</th>
          </tr>
          
          <tr>
              <th>生产日期</th>
              <th>{{deliveryListInfo.productionTime | dateTime}}</th>
          </tr>
          <tr>
              <th>批次号</th>
              <th>{{deliveryListInfo.batchNo}}</th>
          </tr>
          <tr>
              <th>存储年限</th>
              <th>{{deliveryListInfo.storageLifeOf}}</th>
          </tr>
          <tr>
              <th>备注</th>
              <th>{{deliveryListInfo.note}}</th>
          </tr>
      </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
        deliveryListInfo:{}
    };
  },
  created() {
    //   alert("wdnmd");
    this.getById(this.$route.query.id);
  },
  methods: {
    getById(val) {
      let param = {
        id: val
      };
      this.$api.deleveryApi.getInfoById(param).then(res => {
        console.log(res);
        if (res.success) {
          this.deliveryListInfo = res.data;
        }
      });
    }
  }
};
</script>


<style lang="scss">


    
    .common-table{
        margin: 15px;
        width: 90%;
        td,th{
            text-align: center;
            // padding: 12px 0;
            line-height: 10rem;
            vertical-align: middle;
            
        font-size: 5rem !important;
        }
        th{
            font-weight: bold;
            white-space: nowrap;
        }
        
  }
  
  .tableDiv{
      width: 100%;
    //   margin: 0 auto !important;
      text-align: center;
      height: 150px;
  }
</style>
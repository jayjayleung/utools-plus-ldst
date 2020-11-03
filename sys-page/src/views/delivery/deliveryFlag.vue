<!--
 * @Description: 
 * @Author: jayjay
 * @Github: 
 * @Date: 2020-06-07 10:07:27
 * @LastEditors: jayjay
 * @LastEditTime: 2020-06-07 19:28:28
--> 
<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column type="index" label="序号" width="50"></el-table-column>
      <el-table-column label="送货仓库" width="180">
        <template slot-scope="scope">
          <el-button type="text" @click="gotoInfo(scope.row)">{{scope.row.warehouseName}}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="创建日期">
        <template slot-scope="scope">{{scope.row.createTime|dateTime}}</template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: []
    };
  },
  methods: {
    gotoInfo(row) {
      console.log(row);
      let param = {
        path: "/home/deliveryTag",
        query: {
          id: row.id
        }
      };
      this.$router.push(param);
    },
    getList() {
      this.$api.deleveryApi.list({}).then(res => {
        if (res.success) {
          this.tableData = res.list;
        }
      });
    }
  },
  created() {
    this.getList();
  }
};
</script>
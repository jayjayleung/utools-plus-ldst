<template>
  <div class="tableEl">
    <!-- 搜索栏 -->
    <div class="table-serach">
      <v-input :controlArray="controlArray" @search="search"></v-input>
    </div>
    <!-- 工具（操作）栏 -->
    <div class="table-operations">
      <template v-if="operators&& operators.length>0">
        <el-button
          size="small"
          v-for="(operator, index) in operators"
          :key="index"
          :type="operator.type|''"
          @click="operator.click()"
        >
          <i class="el-icon--right" :class="operator.icon"></i>
          {{operator.text}}
        </el-button>
      </template>
    </div>
    <div class="table-template" v-if="columns.length>0">
      <el-table
        v-loading="loading"
        element-loading-text="拼命加载中"
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
        ref="vTable"
        :data="tData"
        style="width: 100%"
        max-height="610"
        @select="select"
        @select-all="selectAll"
        @selection-change="selectionChange"
      >
        <el-table-column v-if="checkbox" type="selection" width="55"></el-table-column>
        <el-table-column v-if="colButtons" :label="colButtons.text" :width="colButtons.width">
          <template slot-scope="scope">
            <el-button
              v-for="(item, index) in colButtons.buttons"
              :key="index"
              @click="item.click(scope.row)"
              type="text"
              size="small"
            >{{item.text}}</el-button>
            <!-- <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
            <el-button type="text" size="small">编辑</el-button>-->
          </template>
        </el-table-column>
        <el-table-column
          v-for="(item, index) in tableCols"
          :key="index"
          :label="item.text"
          :width="item.width"
        >
          <!-- <template v-if="!item.render" slot-scope="scope2">
              {{scope2.row[item.index]}}
          </template>-->
          <template slot-scope="scope1">
            <div v-if="item.render&&item.render!=null" v-html="item.render(scope1.row)"></div>
            <div v-else v-html="scope1.row[item.index]"></div>
            <!-- {{ ?item.render(scope1.row):scope1.row[item.index]}} -->
          </template>
        </el-table-column>

        <!-- <el-table-column prop="index" label="日期" width="180"></el-table-column>
        <el-table-column prop="name" label="姓名" width="180"></el-table-column>
        <el-table-column prop="address" label="地址"></el-table-column>-->
      </el-table>
    </div>
    <div class="table-footer">
      <el-row type="flex" justify="end">
        <el-col :span="10">
          <el-pagination
            background
            :current-page="currentPage"
            :page-sizes="pageSizeSelector"
            layout="total, prev, pager, next,sizes"
            :total="totalSize"
            @size-change="sizeChange"
            @current-change="currentChange"
            @prev-click="prevPage"
            @next-click="nextPage"
          ></el-pagination>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { basicConstans } from "@/utils/basicConstans";
import VInput from "@/components/v-input/vInput";
// import { getList } from "@/api/table";
import _ from "lodash";
export default {
  components: {
    VInput
  },
  props: {
    //请求连接
    apiUrl: String,
    //请求方式
    method: {
      type: String,
      default: "post"
    },
    emap: {
      type: Object,
      default: {}
    },
    //排序方式
    dir: {
      type: String,
      default: "asc"
    },
    //排序字段
    sort: {
      type: String,
      default: "id"
    },
    //是否多选
    checkbox: {
      type: Boolean,
      default: true
    },
    //搜索框参数
    controlArray: {
      type: Array,
      default: () => []
    },
    //表头工具栏参数
    operators: {
      type: Array,
      default: () => [
        // {
        //   type: "primary",
        //   icon: basicConstans.ICON_ADD,
        //   text: "新增",
        //   click: () => {
        //     console.log("新增");
        //     this.add("add");
        //   }
        // },
        // {
        //   type: "default",
        //   icon: basicConstans.ICON_EDIT,
        //   text: "新增",
        //   click: () => {
        //     console.log("新增");
        //     add("add");
        //   }
        // },
        // {
        //   type: "",
        //   icon: basicConstans.ICON_EDIT,
        //   text: "新增",
        //   click: () => {
        //     console.log("新增");
        //     this.add("333");
        //   }
        // },
        // {
        //   type: null,
        //   icon: basicConstans.ICON_EDIT,
        //   text: "新增",
        //   click: () => {
        //     console.log("新增");
        //     this.add("add");
        //   }
        // },
        // {
        //   icon: basicConstans.ICON_EDIT,
        //   text: "新增",
        //   click: () => {
        //     console.log("新增");
        //     this.add("add");
        //   }
        // }
      ]
    },
    columns: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      //当前第几页
      currentPage: 1,
      //显示多少条
      pageSize: 10,
      //总条数
      totalSize: 0,
      //每页显示个数选择器的选项设置
      pageSizeSelector: [10, 20, 30, 40, 50, 100],
      //表格列名
      tableCols: [],
      //表格每行操作列
      colButtons: [],
      //是否加载
      loading: false,
      //请求回来的数据
      resData: {},
      //表格列表数据
      tData: []
    };
  },
  methods: {
    add(value) {
      console.log(value);
      alert(value);
    },
    //点击搜索的时候
    search(value) {
      console.log(value);
      this.$emit("search", value);
      this.currentPage = 1;
      this.getTableList(value);
    },
    //拼接查询条件
    getMap(controlArray) {
      let map = {};
      if (controlArray && controlArray.length > 0) {
        this.controlArray.forEach(item => {
          map[item.key] = item.value;
        });
      }
      return map;
    },
    //请求表格数据
    getTableList(controlArray) {
      let map = this.getMap(this.controlArray);
      if (this.emap) {
        for (const key in this.emap) {
          map[key] = this.emap[key];
        }
      }
      let page = {
        map: map, //请求参数
        page: this.currentPage, //当前页
        pageSize: this.pageSize, //每页显示多少条
        dir: this.dir, //排序方式
        sort: this.sort //排序字段
      };

      this.loading = true;
      //发起请求
      this.$api.tableApi.getList(this.apiUrl, this.method, page).then(res => {
        console.log(res);
        this.resData = res.data;
        if (this.resData) {
          this.currentPage = this.resData.page;
          this.pageSize = this.resData.pageSize;
          this.totalSize = this.resData.totalSize;
          if (this.resData.collection) {
            this.tData = this.resData.collection;
          }
        }
        this.loading = false;
      });
    },
    //当用户手动勾选数据行的 Checkbox 时触发的事件(表格)
    select(selection, row) {
      console.log(selection);
      console.log(row);
    },
    //当用户手动勾选全选 Checkbox 时触发的事件(表格)
    selectAll(selection) {
      console.log(selection);
    },
    //当选择项发生变化时会触发该事件(表格)
    selectionChange(selection) {
      console.log(selection);
    },
    //pageSize 改变时会触发(分页)
    sizeChange(pageSize) {
      console.log(pageSize);
      this.pageSize = pageSize;
      //重新获取数据
      this.getTableList(this.controlArray);
    },
    //currentPage 改变时会触发(分页)
    currentChange(currentPage) {
      console.log(currentPage);
      this.currentPage = currentPage;
      //重新获取数据
      this.getTableList(this.controlArray);
    },
    //点击上一页按钮改变当前页后触发(分页)
    prevPage(currentPage) {
      console.log(currentPage);
      this.currentPage = currentPage;
      //重新获取数据
      this.getTableList(this.controlArray);
    },
    //点击下一页按钮改变当前页后触发(分页)
    nextPage(currentPage) {
      console.log(currentPage);
      this.currentPage = currentPage;
      //重新获取数据
      this.getTableList(this.controlArray);
    },
    reload() {
      this.getTableList(this.controlArray);
    }
  },
  updated() {
    console.log(this.columns);
  },
  created() {
    console.log(this.apiUrl);
    console.log(this.method);
    if (this.columns && this.columns.length > 0) {
      const filterColumns = []; // 过滤隐藏的列
      let columnsWidth = 0;
      let w = 100 / this.columns.length;
      const defaultColumnWidth = null;
      this.columns.forEach(item => {
        if (!item["hidden"] && !item["buttons"]) {
          filterColumns.push(item);
          if (item["width"] == null) {
            item["width"] = defaultColumnWidth;
          }
          columnsWidth += item["width"] * 1;
        } else {
          if (!item["width"] && item["width"] == null) {
            item["width"] = defaultColumnWidth;
          }

          if (item["buttons"]) {
            this.colButtons = item;
            columnsWidth += item["width"] * 1;
          }
        }
      });
      this.tableCols = filterColumns;
    }

    console.log(this.colButtons);
    console.log(this.tableCols);
    this.getTableList(this.controlArray);
    console.log(this.columns);
  }
};
</script>


<style lang="scss">
.tableEl {
  // width:600px;
  background-color: #ffffff;
}
.table-serach {
  padding-left: 20px;
  padding-right: 40px;
}
.table-operations {
  // margin-top: 10px;
  margin-bottom: 5px;
}
.table-footer {
  position: relative;
  right: 0;
}
</style>
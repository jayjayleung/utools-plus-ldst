<template>
  <div>
    <el-button type="primary" @click="open()">选择</el-button>
    <div>
      <el-dialog title="选择" width="900px" :visible.sync="lookVisible">
        <div>
          <v-table
            :apiUrl="apiUrl"
            :controlArray="controlArray1"
            :operators="operators"
            :columns="columns"
            :checkbox="false"
            :emap="{type:type+''}"
            ref="vtable"
          ></v-table>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    type: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      currentRow: {},
      txtArr: ["联系人", "联系方式", "仓库", "单位"],
      lookVisible: false,
      apiUrl: "/common/list",
      controlArray1: [],
      operators: [],
      columns: []
    };
  },
  methods: {
    open() {
      this.lookVisible = true;

      this.getList();
    },
    selectVal(item) {
      this.$emit("selectValue", item);
    },
    getList() {
      this.$refs.vtable.reload();
    }
  },
  created() {
      console.log(this.txtArr[this.type * 1]);
    this.controlArray1 = [
      {
        index: 0,
        text: this.txtArr[this.type * 1],
        type: "input",
        key: "vallikes",
        value: null,
        show: true
      }
    ];
    this.columns = [
      {
        text: "操作",
        width: 150,
        buttons: [
          {
            text: "选择",
            click: item => {
              this.selectVal(item);
              this.lookVisible = false;
            }
          }
        ]
      },
      {
        text: this.txtArr[this.type],
        index: "val",
        iShowSort: true
      }
    ];
  }
};
</script>


<style>
</style>
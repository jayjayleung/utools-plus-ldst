<template>
    <div class="tree">
        <el-row type="flex" justify="space-between">
            <el-col :span="22">
                <el-input
                    :placeholder="placeholder"
                    v-model="filterText">
                </el-input>
            </el-col>
            <el-col :span="1">
                 <el-button id="btn" type="primary" size="mini" icon="el-icon-refresh-right" circle @click="refresh"></el-button>
            </el-col>
        </el-row>
        

        <el-tree
            class="filter-tree"
            :data="data"
            :props="treePorps"
            :filter-node-method="filterNode"
            @node-click="nodeClick"
            ref="tree">
        </el-tree>
    </div>
</template>

<script>
// import { getTree } from "@/api/tree";
  export default {
    watch: {
      filterText(val) {
        this.$refs.tree.filter(val);
      }
    },
    props:{
       placeholder:{
           type: String,
           default: "请输入关键字"
       },
       treeUrl:String, 
       //请求方式
        method: {
            type: String,
            default: "post"
        },
        param:{
            type:Object,
            default:{}
        },
       treePorps:{
           type:Object,
           default:{
               children: 'children',
               label: 'label'
           }
        }
    },

    methods: {
    //筛选
      // filterNode(value, data) {
      //   if (!value) return true;
      //   return data[this.treePorps.label].indexOf(value) !== -1;
      // },
      // getTreeList(){
      //   getTree(this.treeUrl,this.method,this.param).then(res=>{
      //       console.log(res);
      //       this.resData = res.data;
      //       if (this.resData["list"]) {
      //           this.data = this.resData["list"];
      //       }
      //   });
      // },
      // nodeClick(data,node,elemt){
      //   // console.log(data);
      //   // console.log(node);
      //   // console.log(elemt);
      //   this.$emit("nodeClick",data);
      // },
      // refresh(){
      //   this.getTreeList();
      // }
    },

    data() {
      return {
        filterText: '',
        data: [],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      };
    },
    created(){
        // this.getTreeList();
    }
  };
</script>

<style lang="scss">
    #btn{
        position: relative;
        top: 6px;
    }
    .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>
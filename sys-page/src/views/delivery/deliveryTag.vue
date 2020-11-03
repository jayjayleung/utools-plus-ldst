<!--
 * @Description: 
 * @Author: jayjay
 * @Github: 
 * @Date: 2020-06-07 11:17:36
 * @LastEditors: jayjay
 * @LastEditTime: 2020-06-07 19:43:18
--> 
<template>
  <div>
    <el-row>
      <el-col>
        现有清单:
        <el-select v-model="currRow" placeholder="请选择" @change="change">
          <el-option
            v-for="item in selectArr"
            :key="item.value"
            :label="(item.warehouseName +'----'+format(item.createTime))"
            :value="item.id"
          >
            <span style="float: left">{{ item.warehouseName }}</span>
            <span
              style="float: right; color: #8492a6; font-size: 13px"
            >{{ item.createTime|dateTime }}</span>
          </el-option>
        </el-select>
        <el-button type="primary" size="small" icon="el-icon-delete" @click="remove()">删除</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="5" :offset="12">
        <h3>货物打印标签</h3>
      </el-col>
    </el-row>
    <el-row type="flex" justify="end">
      <el-col :span="8">
        <el-radio-group v-model="radio" @change="radioChange">
          <el-radio :label="2">全部显示</el-radio>
          <el-radio :label="0">显示未打印条目</el-radio>
          <el-radio :label="1">显示打印条目</el-radio>
        </el-radio-group>
      </el-col>
    </el-row>

    <el-row style="margin-top:15px">
      <el-col>
        <el-table :data="deliveryListInfoList" border style="width: 100%">
          <el-table-column type="index" label="序号" width="50"></el-table-column>
          <el-table-column prop="name" label="货物名称" width="180"></el-table-column>
          <el-table-column prop="type" label="规格型号" width="180"></el-table-column>
          <el-table-column prop="code" label="器材编码"></el-table-column>
          <el-table-column prop="num" label="数量"></el-table-column>
          <el-table-column prop="unit" label="单位"></el-table-column>
          <el-table-column prop="productionTime" label="生产日期"></el-table-column>
          <el-table-column prop="productionCompany" label="供货方"></el-table-column>
          <el-table-column prop="note" label="备注"></el-table-column>
          <el-table-column label="操作" width="120">
            <template slot-scope="scope">
              <el-button @click.native.prevent="printView(scope.row)" type="primary" size="small">打印</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>

    <div>
      <el-dialog :visible.sync="showPrint" top="1vh">
        <div ref="print">
          <div style="text-align:center">
            <table border="1" cellspacing="0" cellpadding="0" width="100%" style="margin:auto">
              <tbody>
                <tr>
                  <th colspan="2">器材名称</th>
                  <th colspan="6">{{deliveryListInfo.name}}</th>
                </tr>
                <tr>
                  <th colspan="2">器材编码</th>
                  <th colspan="6">{{deliveryListInfo.code}}</th>
                </tr>
                <tr>
                  <th>数量</th>
                  <th colspan="4">{{deliveryListInfo.num}}</th>
                  <th>单位</th>
                  <th colspan="2">{{deliveryListInfo.unit}}</th>
                </tr>
                <tr>
                  <th>规格型号</th>
                  <th colspan="3">{{deliveryListInfo.type}}</th>
                  <th>技术参数</th>
                  <th colspan="3">{{deliveryListInfo.technicalParameters}}</th>
                </tr>
                <tr>
                  <th colspan="2">用途/适用主机</th>
                  <th colspan="6">{{deliveryListInfo.effect}}</th>
                </tr>
                <tr>
                  <th colspan="2">生产厂家</th>
                  <th colspan="6">{{deliveryListInfo.productionCompany}}</th>
                </tr>
                <tr>
                  <th>批次号</th>
                  <th colspan="3">{{deliveryListInfo.batchNo}}</th>
                  <th colspan="4">二维码</th>
                </tr>
                <tr>
                  <th>等级</th>
                  <th colspan="3">{{deliveryListInfo.grade}}</th>
                  <th colspan="4" rowspan="5">
                    <el-image
                      style="width: 100px; height: 100px"
                      :src="deliveryListInfo.qrCode"
                      :preview-src-list="[deliveryListInfo.qrCode]"
                    ></el-image>
                  </th>
                </tr>
                <tr>
                  <th>生产日期</th>
                  <th colspan="3">{{deliveryListInfo.productionTime}}</th>
                </tr>
                <tr>
                  <th>存储年限</th>
                  <th colspan="3">{{deliveryListInfo.storageLifeOf}}</th>
                </tr>
                <tr>
                  <th rowspan="2">备注</th>
                  <th rowspan="2" colspan="3">{{deliveryListInfo.note}}</th>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button @click="showPrint = false">取 消</el-button>
          <el-button type="primary" @click="print()">打 印</el-button>
        </div>
      </el-dialog>
    </div>
  </div>

  <!-- 打印窗口 -->
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      selectArr: [],
      currRow: [],
      currText: "",
      radio: 2,
      printObj: {},
      showPrint: false,
      deliveryListInfoList: [],
      deliveryListInfo: {
        id: "",
        name: "晨光0.5mm自动铅笔",
        code: "123456789",
        type: "trwwtr",
        technicalParameters: "跑光",
        effect: "安卓",
        productionCompany: "晨光有限公司",
        batchNo: "000302",
        storageLifeOf: "12个月",
        note: "备注",
        deliveryListId: "",
        printStatus: "",
        createTime: "",
        num: 500,
        unit: "盒",
        productionTime: "2020.6.7",
        grade: "优",
        qrCodeUrl:
          "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591459952896&di=6c21b70b197626ac989fb2e158de4c93&imgtype=0&src=http%3A%2F%2Fimg.haote.com%2Fupload%2Fqrcode%2F1339%2Fhaote31ca2e8f449f4dc0a601feca11584866.png"
      }
    };
  },
  methods: {
    printView(row) {
      this.showPrint = true;
      this.deliveryListInfo = row;
    },
    print() {
      //   styles='';
      this.$print(this.$refs.print, {
        printBeforeFn: this.printBeforeFn,
        printhoneCallBack: this.printhoneCallBack
      });

      this.$api.deleveryApi
        .setPrint({ id: this.deliveryListInfo.id })
        .then(res => {
          this.showPrint = false;
          this.$confirm("打印成功", "提示", {
            confirmButtonText: "确定",
            showCancelButton: false,
            type: "success",
            center: true
          });
        });
    },
    printBeforeFn() {
      console.log("打印前回调");
    },

    printhoneCallBack() {
      console.log("打印后回调");
      this.showPrint = false;
    },
    findListByMap(param) {
      this.loading = true;
      this.$api.deleveryApi.findListByMap(param).then(res => {
        console.log(res);
        if (res.success) {
          this.deliveryListInfoList = res.list;
          this.loading = false;
        }
      });
    },
    radioChange(val) {
      let param = {
        deliveryListId: this.currRow
      };
      if (val != 2) {
        param.printStatus = val;
      }
      this.findListByMap(param);
    },
    getList() {
      this.$api.deleveryApi.list({}).then(res => {
        if (res.success) {
          this.tableData = res.list;
        }
      });
    },
    init() {
      this.$api.deleveryApi.list({}).then(res => {
        if (res.success) {
          this.selectArr = res.list;
          if (this.selectArr && this.selectArr.length > 0) {
            this.currRow = this.selectArr[0].id;
            let param = {
              deliveryListId: this.currRow
            };
            this.findListByMap(param);
          }
        }
      });
    },
    format(val) {
      return this.$options.filters["dateTime"](val);
    },
    change(id) {
      console.log(id);
      let param = {
        deliveryListId: id
      };
      this.findListByMap(param);
    },
    remove() {
      var that = this;
      this.$confirm("是否删除该清单?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          var params = {
            id: this.currRow
          };
          this.$api.deleveryApi.remove(params).then(res => {
            this.$message({
              type: "success",
              message: "删除成功!"
            });
            this.selectArr = this.selectArr.filter(t => t.id != this.currRow);
            if (this.selectArr && this.selectArr.length > 0) {
              this.currRow = this.selectArr[0].id;
              let param = {
                deliveryListId: this.currRow
              };
              this.findListByMap(param);
            } else {
              this.currRow = "";
              this.deliveryListInfoList = [];
            }

          });
        })
        .catch(() => {});
    }
  },
  created() {
    // console.log(this.$route.query.id);
    // let param = {
    //   deliveryListId: this.$route.query.id
    // };
    // this.findListByMap(param);
    this.init();
  }
};
</script>
<style lang="scss" scoped>
@import "../../assets/css/print.css";
#printhiv {
  text-align: center;
}
#printTable {
  margin: auto;
}
</style>
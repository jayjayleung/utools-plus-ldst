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
    <div :ref="print1">
      <el-row>
        <el-col>送货清单基本信息</el-col>
      </el-row>
      <el-form size="medium " :inline="false" label-width="100px" label-position="left">
        <el-row :gutter="20" align="middle">
          <el-col :span="8">发货联系人：&nbsp;&nbsp;&nbsp;&nbsp;{{delivery.deliveryContact}}</el-col>
          <el-col :span="8">联系电话：&nbsp;&nbsp;&nbsp;&nbsp;{{delivery.deliveryPhone}}</el-col>
        </el-row>
        <el-row :gutter="20" align="middle">
          <el-col>送货仓库：&nbsp;&nbsp;&nbsp;&nbsp;{{delivery.warehouseName}}</el-col>
        </el-row>
        <el-row :gutter="20" align="middle">
          <el-col>货物清单：</el-col>
        </el-row>
      </el-form>
      <el-row style="margin-top:15px">
        <el-col>
          <el-table
            :data="delivery.deliveryListInfoList"
            border
            style="width: 100%"
            v-loading="loading"
          >
            <el-table-column type="index" label="序号" width="50"></el-table-column>
            <el-table-column prop="name" label="货物名称" width="180"></el-table-column>
            <el-table-column prop="type" label="规格型号" width="180"></el-table-column>
            <el-table-column prop="code" label="器材编码"></el-table-column>
            <el-table-column prop="num" label="数量"></el-table-column>
            <el-table-column prop="unit" label="单位"></el-table-column>
            <el-table-column prop="productionTime" label="生产日期"></el-table-column>
            <el-table-column prop="productionCompany" label="供货方"></el-table-column>
            <el-table-column prop="note" label="备注"></el-table-column>
          </el-table>
        </el-col>
      </el-row>
      <el-row class="no-print">
        <el-col>
          <el-button type="primary" size="mini" @click="showAddDialog()">新增条目</el-button>
        </el-col>
      </el-row>
      <el-row class="no-print" justify="center">
        <el-col :span="4" :offset="12">
          <el-button type="primary" @click="print(print1)">打印</el-button>
        </el-col>
      </el-row>
    </div>
    <!-- 新增条目弹窗 -->
    <div>
      <el-dialog title="新增条目" :visible.sync="showAdd" top="1vh">
        <el-form
          size="medium"
          ref="deliveryListInfo"
          :model="deliveryListInfo"
          :rules="deliveryListInfoRules"
          :inline="false"
          label-width="100px"
          label-position="left"
        >
          <el-row>
            <el-col>
              <el-form-item label="器材名称" prop="name">
                <el-input v-model="deliveryListInfo.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20" align="middle">
            <el-col :span="12">
              <el-form-item label="器材编码" prop="code">
                <el-input v-model="deliveryListInfo.code"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="数量" prop="num">
                <el-input v-model="deliveryListInfo.num"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="单位" prop="unit">
                <el-select v-model="deliveryListInfo.unit">
                  <el-option
                    v-for="item in unitArr"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20" align="middle">
            <el-col :span="12">
              <el-form-item label="规格型号:" prop="type">
                <el-input v-model="deliveryListInfo.type"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="技术参数:" prop="technicalParameters">
                <el-input v-model="deliveryListInfo.technicalParameters"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <el-form-item label="用途/使用主机:" label-width="120px" prop="effect">
                <el-input v-model="deliveryListInfo.effect"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col>
              <el-form-item label="生产厂家:" prop="productionCompany">
                <el-input v-model="deliveryListInfo.productionCompany"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20" align="middle">
            <el-col :span="18">
              <el-form-item label="批次号:" prop="batchNo">
                <el-input v-model="deliveryListInfo.batchNo"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="品级:" prop="grade">
                <el-select v-model="deliveryListInfo.grade">
                  <el-option
                    v-for="item in gradeArr"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col>
              <el-form-item label="生产日期:" prop="productionTime">
                <el-date-picker
                  style="width:100%"
                  v-model="deliveryListInfo.productionTime"
                  type="date"
                  value-format="yyyy-MM-dd"
                ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <el-form-item label="存储年限" prop="storageLifeOf">
                <el-select v-model="deliveryListInfo.storageLifeOf">
                  <el-option
                    v-for="item in monthArr"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <el-form-item label="备注" label-width="auto" prop="note">
                <el-input type="textarea" v-model="deliveryListInfo.note"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="showAdd = false">取 消</el-button>
          <el-button type="primary" @click="saveItem()">保 存</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      selectArr: [],
      currRow: [],
      currText: "",
      print1: "print",
      print2: "print2",
      delivery: {
        id: "",
        deliveryContact: "",
        deliveryPhone: "",
        receiptContact: "",
        receiptPhone: "",
        warehouseId: "",
        warehouseName: "",
        createTime: "",
        deliveryListInfoList: []
      },
      deliveryRules: {
        deliveryContact: [
          { required: true, message: "发货联系人不能为空", trigger: "blur" },
          {
            deliveryPhone: true,
            message: "发货联系人电话不能为空",
            trigger: "blur"
          }
        ],
        deliveryPhone: [
          { required: true, message: "发货联系人电话不能为空", trigger: "blur" }
        ],
        receiptContact: [
          { required: true, message: "收货联系人不能为空", trigger: "blur" }
        ],
        receiptPhone: [
          { required: true, message: "收货联系人电话不能为空", trigger: "blur" }
        ],
        warehouseName: [
          { required: true, message: "仓库不能为空", trigger: "blur" }
        ]
      },
      deliveryListInfo: {
        id: "",
        name: "",
        code: "",
        type: "",
        technicalParameters: "",
        effect: "",
        productionCompany: "",
        batchNo: "",
        storageLifeOf: "12个月",
        note: "",
        deliveryListId: "",
        printStatus: 0,
        createTime: "",
        num: 0,
        unit: "台",
        productionTime: new Date(),
        grade: "优"
      },
      deliveryListInfoRules: {
        name: [
          { required: true, message: "器材名字不能为空", trigger: "blur" }
        ],
        code: [
          { required: true, message: "器材编码不能为空", trigger: "blur" }
        ],
        type: [{ required: true, message: "规格型号不能空", trigger: "blur" }],
        technicalParameters: [
          { required: true, message: "技术参数不能为空", trigger: "blur" }
        ],
        effect: [{ required: true, message: "用途不能为空", trigger: "blur" }],
        productionCompany: [
          { required: true, message: "生产厂家不能为空", trigger: "blur" }
        ],
        batchNo: [
          { required: true, message: "批次号不能为空", trigger: "blur" }
        ],
        storageLifeOf: [
          { required: true, message: "存储年限不能为空", trigger: "blur" }
        ],
        note: [{ required: true, message: "备注不能为空", trigger: "blur" }],
        num: [
          { required: true, message: "数量不能为空", trigger: "blur" }
          // { type: "number", message: "必须为数字值" }
        ],
        unit: [{ required: true, message: "单位不能为空", trigger: "blur" }],
        productionTime: [
          { required: true, message: "生产日期不能为空", trigger: "blur" }
        ],
        grade: [{ required: true, message: "品级不能为空", trigger: "blur" }]
      },
      wareHouseList: [],
      unitArr: [
        { label: "台", value: "台" },
        { label: "盒", value: "盒" },
        { label: "个", value: "个" },
        { label: "瓶", value: "瓶" },
        { label: "箱", value: "箱" }
      ],
      monthArr: [
        { label: "1个月", value: "1个月" },
        { label: "2个月", value: "2个月" },
        { label: "3个月", value: "3个月" },
        { label: "4个月", value: "4个月" },
        { label: "5个月", value: "5个月" },
        { label: "6个月", value: "6个月" },
        { label: "7个月", value: "7个月" },
        { label: "8个月", value: "8个月" },
        { label: "9个月", value: "9个月" },
        { label: "10个月", value: "10个月" },
        { label: "11个月", value: "11个月" },
        { label: "12个月", value: "12个月" }
      ],
      gradeArr: [
        { label: "优", value: "优" },
        { label: "良", value: "良" },
        { label: "差", value: "差" }
      ],
      showAdd: false
    };
  },
  methods: {
    print(ref) {
      this.$print(this.$refs[ref], {
        printBeforeFn: this.printBeforeFn,
        printDoneCallBack: this.printDoneCallBack
      });
    },
    printBeforeFn() {
      console.log("打印前回调");
    },

    printDoneCallBack() {
      console.log("打印后回调");
      this.showPrint = false;
      this.$confirm("打印成功", "提示", {
        confirmButtonText: "确定",
        showCancelButton: false,
        type: "success",
        center: true
      });
    },
    getById(val) {
      this.loading = true;
      let param = {
        id: val
      };
      this.$api.deleveryApi.getById(param).then(res => {
        console.log(res);
        if (res.success) {
          this.delivery = res.data;
          this.loading = false;
        }
      });
    },
    getList() {
      this.$api.deleveryApi.list({}).then(res => {
        if (res.success) {
          this.selectArr = res.list;
        }
      });
    },
    init() {
      this.$api.deleveryApi.list({}).then(res => {
        console.log(res);
        if (res.success) {
          this.selectArr = res.list;
          if (this.selectArr && this.selectArr.length > 0) {
            this.currRow = this.selectArr[0].id;
            this.getById(this.currRow);
          }
        }
      });
    },
    format(val) {
      return this.$options.filters["dateTime"](val);
    },
    change(id) {
      console.log(id);
      this.getById(id);
    },
    saveItem() {
      // this.showAdd = false;
      const flag = this.submitForm("deliveryListInfo");
      console.log(this.deliveryListInfo);
      console.log(this.delivery);
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let target = JSON.parse(JSON.stringify(this.deliveryListInfo));
          target.deliveryListId = this.delivery.id;
          this.$api.deleveryApi.addItem(target).then(res => {
            if (res.success) {
              this.$message.success("添加成功");

              this.delivery.deliveryListInfoList.push(res.data);
              this.showAdd = false;
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },

    showAddDialog() {
      this.showAdd = true;
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
              this.getById(this.currRow);
            }else{
              this.currRow = '';
              this.delivery = {};
              this.delivery.deliveryListInfoList = [];
            }
          });
        })
        .catch(() => {});
    }
  },

  created() {
    // console.log(this.$route.query.id);
    // this.getById(this.$route.query.id);
    this.init();
  }
};
</script>
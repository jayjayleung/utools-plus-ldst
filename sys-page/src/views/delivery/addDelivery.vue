<!--
 * @Description: 创建新清单
 * @Author: jayjay
 * @Github: 
 * @Date: 2020-06-06 18:04:40
 * @LastEditors: jayjay
 * @LastEditTime: 2020-06-07 19:38:03
--> 
<template>
  <div>
    <div>
      <el-row>
        <el-col>送货清单基本信息</el-col>
      </el-row>
      <el-form
        size="medium"
        ref="delivery"
        :model="delivery"
        :rules="deliveryRules"
        :inline="false"
        label-width="110px"
        label-position="left"
      >
        <el-row type="flex" justify="start" :gutter="20">
          <el-col :span="8">
            <el-form-item label="发货联系人：" prop="deliveryContact">
              <el-input v-model="delivery.deliveryContact" placeholder="请输入发货联系人"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系电话：" prop="deliveryPhone">
              <el-input v-model="delivery.deliveryPhone" placeholder="请输入联系电话"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" justify="start" :gutter="20">
          <el-col :span="8">
            <el-form-item label="收货联系人：" prop="receiptContact">
              <el-row type="flex" justify="start" :gutter="20">
                <el-col :span="18">
                  <!-- <el-input v-model="delivery.receiptContact" placeholder="请输入收货联系人">
                  <el-button slot="append" type="primary" @click="addCommonContact()">添加常用联系人</el-button>
                  </el-input>-->
                  <el-autocomplete
                    class="inline-input"
                    v-model="delivery.receiptContact"
                    :fetch-suggestions="querySearch1"
                    placeholder="请输入收货联系人"
                    :trigger-on-focus="false"
                    @select="handleSelect1"
                  >
                    <el-button slot="append" type="primary" @click="addCommonContact()">添加常用联系人</el-button>
                    <template slot-scope="{ item }">
                      <div class="name">{{ item.val }}</div>
                    </template>
                  </el-autocomplete>
                </el-col>
                <el-col :span="4">
                  <v-common :type="0" @selectValue="selectValue"></v-common>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联系电话：" prop="receiptPhone">
              <!-- <el-input v-model="delivery.receiptPhone" placeholder="请输入联系电话"></el-input> -->
              <el-row type="flex" justify="start" :gutter="20">
                <el-col :span="18">
                  <el-autocomplete
                    class="inline-input"
                    v-model="delivery.receiptPhone"
                    :fetch-suggestions="querySearch2"
                    placeholder="请输入联系电话"
                    :trigger-on-focus="false"
                    @select="handleSelect2"
                  >
                    <el-button slot="append" type="primary" @click="addCommonPhone()">添加常用联系方式</el-button>
                    <template slot-scope="{ item }">
                      <div class="name">{{ item.val }}</div>
                    </template>
                  </el-autocomplete>
                </el-col>
                <el-col :span="4">
                  <v-common :type="1" @selectValue="selectValue"></v-common>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="送货仓库：" prop="warehouseName">
              <!-- <el-select
                v-model="delivery.warehouseId"
                placeholder="请选择仓库"
                style="width: 64%;"
                @change="selectChange"
              >
                <el-option
                  v-for="item in wareHouseList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>-->
              <el-row type="flex" justify="start" :gutter="20">
                <el-col :span="18">
                  <el-autocomplete
                    class="inline-input"
                    v-model="delivery.warehouseName"
                    :fetch-suggestions="querySearch3"
                    placeholder="请输入仓库"
                    :trigger-on-focus="false"
                    @select="handleSelect3"
                  >
                    <el-button slot="append" type="primary" @click="addCommonWareHourse()">添加常用仓库</el-button>
                    <template slot-scope="{ item }">
                      <div class="name">{{ item.val }}</div>
                    </template>
                  </el-autocomplete>
                </el-col>
                <el-col :span="4">
                  <v-common :type="2" @selectValue="selectValue"></v-common>
                </el-col>
              </el-row>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-row>
        <el-col>货物清单：</el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-table :data="delivery.deliveryListInfoList" border style="width: 100%">
            <el-table-column type="index" label="序号" width="50"></el-table-column>
            <el-table-column prop="name" label="货物名称" width="180"></el-table-column>
            <el-table-column prop="type" label="规格型号" width="180"></el-table-column>
            <el-table-column prop="code" label="器材编码"></el-table-column>
            <el-table-column prop="num" label="数量"></el-table-column>
            <el-table-column prop="unit" label="单位"></el-table-column>
            <el-table-column prop="productionTime" label="生产日期">
              <template slot-scope="scope">{{scope.row.productionTime | dateTime}}</template>
            </el-table-column>
            <el-table-column prop="productionCompany" label="供货方"></el-table-column>
            <el-table-column prop="note" label="备注"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button @click="showEdit(scope.row)" type="text" size="small">编辑</el-button>
                <el-button @click="remove(scope)" type="text" size="small">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-button type="primary" size="mini" @click="showAddDialog()">新增条目</el-button>
        </el-col>
      </el-row>
      <el-row justify="center" :gutter="20">
        <el-col :span="8" :offset="10">
          <el-button type="primary" @click="save()">保存</el-button>
          <el-button type="primary" @click="showPrintDialog()">保存并打印</el-button>
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

    <!-- 打印弹窗 -->
    <div>
      <el-dialog :visible.sync="showPrint" top="1vh">
        <div ref="print">
          <table border="1" cellspacing="0" cellpadding="0">
            <thead>
              <tr>
                <th class="hideBorder" colspan="10">
                  <h3>货物清单</h3>
                </th>
              </tr>
              <tr>
                <th class="hideBorder" colspan="10">创建方：{{printObj.warehouseName}}</th>
              </tr>
              <tr>
                <th class="hideBorder" colspan="10">
                  <span>发货联系人：{{printObj.deliveryContact}}</span>
                  <span style="margin-left:30px">联系电话：{{printObj.deliveryPhone}}</span>
                </th>
              </tr>
              <tr>
                <th class="hideBorder" colspan="10">
                  <span>收货联系人：{{printObj.receiptContact}}</span>
                  <span style="margin-left:30px">联系电话：{{printObj.receiptPhone}}</span>
                  <p>&nbsp;</p>
                </th>
              </tr>
              <tr>
                <th class="th40">序号</th>
                <th class="th150">名称</th>
                <th class="th100">规格型号</th>
                <th class="th100">器材编码</th>
                <th class="th50">数量</th>
                <th class="th50">单位</th>
                <th class="th100">生产日期</th>
                <th class="th100">供货方</th>
                <th class="th100">备注</th>
                <th class="th100">二维码</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item,index) in printObj.deliveryListInfoList">
                <td>{{(index+1)}}</td>
                <td>{{item.name}}</td>
                <td>{{item.type}}</td>
                <td>{{item.code}}</td>
                <td>{{item.num}}</td>
                <td>{{item.unit}}</td>
                <td>{{item.productionTime|dateTime}}</td>
                <td>{{item.productionCompany}}</td>
                <td>{{item.note}}</td>
                <td>
                  <el-image
                    style="width: 100px; height: 100px"
                    :src="item.qrCode"
                    :preview-src-list="[item.qrCode]"
                  ></el-image>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button @click="showPrint = false">取 消</el-button>
          <el-button type="primary" @click="print()">打 印</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      showAdd: false,
      showPrint: false,
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
      saveAndPrint: false,
      printObj: {},
      list1: [],
      list2: [],
      list3: [],
      flag: "add"
    };
  },
  methods: {
    init() {
      this.delivery = {
        id: "",
        deliveryContact: "",
        deliveryPhone: "",
        receiptContact: "",
        receiptPhone: "",
        warehouseId: "",
        warehouseName: "",
        createTime: "",
        deliveryListInfoList: []
      };
    },
    showAddDialog() {
      this.flag = "add";
      this.deliveryListInfo =  {
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
      };
      this.showAdd = true;
    },
    save() {
      // this.showAdd = false;
      this.$refs["delivery"].validate(valid => {
        if (valid) {
          this.$api.deleveryApi.add(this.delivery).then(res => {
            console.log(res);
            if (res.success) {
              this.$message.success("添加成功");
              this.printObj = res.data;
              if (this.saveAndPrint) {
                this.showPrint = true;
              } else {
                this.init();
              }
            } else {
              this.$message.error(res.msg);
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    saveItem() {
      // this.showAdd = false;
      const flag = this.submitForm("deliveryListInfo");
      console.log(this.deliveryListInfo);
      console.log(this.delivery);
    },
    showPrintDialog() {
      this.saveAndPrint = true;
      this.save();
      // this.showPrint = true;
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.flag == "add") {
            let target = JSON.parse(JSON.stringify(this.deliveryListInfo));
            this.delivery.deliveryListInfoList.push(target);
          }
            this.showAdd = false;
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    print() {
      let styles =
        ".th50{width: 50px;}.th100{width: 100px;}.th150{width: 150px;}";
      //   styles='';
      this.$print(this.$refs.print, {
        styleStr: styles,
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
      this.saveAndPrint = false;
      this.$confirm("打印成功", "提示", {
        confirmButtonText: "确定",
        showCancelButton: false,
        type: "success",
        center: true
      });
      this.init();
    },
    getWarehouseList() {
      this.$api.warehouseApi.list({}).then(res => {
        if (res.success) {
          this.wareHouseList = res.list;
        }
      });
    },
    selectChange(value) {
      if (value && value != "") {
        this.wareHouseList.forEach(item => {
          if (item.id == value) {
            this.delivery.warehouseName = item.name;
            return;
          }
        });
      }
      console.log(this.delivery);
    },
    selectValue(item) {
      if (item.type == 0) {
        this.delivery.receiptContact = item.val;
      }
      if (item.type == 1) {
        this.delivery.receiptPhone = item.val;
      }
      if (item.type == 2) {
        this.delivery.warehouseName = item.val;
        this.delivery.warehouseId = item.warehouseId;
      }

      console.log(item);
    },
    addCommonContact() {
      let map = {
        val: this.delivery.receiptContact,
        type: 0
      };
      this.$api.commonApi.add(map).then(res => {
        if (res.success) {
          this.$message.success("添加成功");
          this.getList1();
        }
      });
    },
    addCommonPhone() {
      let map = {
        val: this.delivery.receiptPhone,
        type: 1
      };
      this.$api.commonApi.add(map).then(res => {
        if (res.success) {
          this.$message.success("添加成功");
          this.getList2();
        }
      });
    },
    addCommonWareHourse() {
      let map = {
        val: this.delivery.warehouseName,
        warehouseId: this.delivery.warehouseId,
        type: 2
      };
      this.$api.commonApi.add(map).then(res => {
        if (res.success) {
          this.$message.success("添加成功");
          this.getList3();
        }
      });
    },
    getList1() {
      this.$api.commonApi.getList({ type: 0 }).then(res => {
        if (res.success) {
          this.list1 = res.list;
        }
      });
    },
    getList2() {
      this.$api.commonApi.getList({ type: 1 }).then(res => {
        if (res.success) {
          this.list2 = res.list;
        }
      });
    },
    getList3() {
      this.$api.commonApi.getList({ type: 2 }).then(res => {
        if (res.success) {
          this.list3 = res.list;
        }
      });
    },
    querySearch1(queryString, cb) {
      var restaurants = this.list1;
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants;

      console.log(results);
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      console.log(queryString);
      return restaurant => {
        console.log(restaurant);
        return (
          restaurant.val.toLowerCase().indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    handleSelect1(item) {
      this.delivery.receiptContact = item.val;
    },
    querySearch2(queryString, cb) {
      var restaurants = this.list2;
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants;

      console.log(results);
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    handleSelect2(item) {
      this.delivery.receiptPhone = item.val;
    },
    querySearch3(queryString, cb) {
      var restaurants = this.list3;
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants;

      console.log(results);
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    handleSelect3(item) {
      this.delivery.warehouseName = item.val;
      this.delivery.warehouseId = item.warehouseId;
    },
    showEdit(item) {
      this.flag = "edit";
      this.deliveryListInfo = item;
      this.showAdd = true;
    },
    remove(item) {
      console.log(item);
      this.delivery.deliveryListInfoList.splice(item.$index,1);
      
    }
  },
  created() {
    // this.getWarehouseList();
    this.delivery.deliveryContact = this.$store.state.userInfo.name;
    this.delivery.deliveryPhone = this.$store.state.userInfo.phone;
    this.getList1();
    this.getList2();
    this.getList3();
  }
};
</script>

<style lang="scss">
.th40 {
  width: 50px;
}
.th50 {
  width: 50px;
}
.th100 {
  width: 100px;
}
.th150 {
  width: 150px;
}
.hideBorder {
  border-top: 0;
  border-bottom: 0;
}
</style>

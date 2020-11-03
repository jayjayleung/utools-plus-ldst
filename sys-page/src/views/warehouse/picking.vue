<template>
  <div>
    <div>
      <el-row>
        <el-col>领料清单基本信息</el-col>
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
        <el-row :gutter="20" align="middle">
          <el-col :span="8">
            <el-form-item label="领料联系人：" prop="name">
              <el-input v-model="delivery.name" placeholder="请输入领料联系人"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="领联系电话：" prop="phone">
              <el-input v-model="delivery.phone" placeholder="请输入联系电话"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-row>
        <!-- <el-col>领料清单：</el-col> -->
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
            <el-table-column prop="provide" label="供货方"></el-table-column>
            <el-table-column prop="note" label="备注"></el-table-column>
            <el-table-column label="操作" width="150">
              <template slot-scope="scope">
                    <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button type="primary" size="mini" @click="handledel(scope.row)">删除</el-button>
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
        <el-col :span="6" :offset="10">
          <!-- <el-button type="primary" @click="save()">保存</el-button> -->
          <el-button type="primary" @click="showPrintDialog()">打印</el-button>
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
              <el-form-item label="货物名称" prop="name">
                <el-input v-model="deliveryListInfo.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20" align="middle">
            <el-col :span="12">
              <el-form-item label="规格型号" prop="type">
                <el-input v-model="deliveryListInfo.type"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="器材编码" prop="code">
                <el-input v-model="deliveryListInfo.code"></el-input>
              </el-form-item>
            </el-col>

          </el-row>
          <el-row :gutter="20" align="middle">

            <el-col :span="6">
              <el-form-item label="数量:" prop="num">
                <el-input v-model="deliveryListInfo.num"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="8">
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
          <el-row>
             <el-col>
              <el-form-item label="生产日期:" prop="productionTime">
                <el-date-picker
                  style="width:30%"
                  v-model="deliveryListInfo.productionTime"
                  type="date"
                  value-format="yyyy-MM-dd"
                ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-row>
            <el-col>
              <el-form-item label="供货方:" prop="provide">
                <el-input v-model="deliveryListInfo.provide"></el-input>
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
    <!-- 编辑条目弹窗 -->
    <div>
      <el-dialog title="编辑条目" :visible.sync="showEdit" top="1vh">
        <el-form
          size="medium"
          ref="editListInfo"
          :model="editListInfo"
          :rules="deliveryListInfoRules"
          :inline="false"
          label-width="100px"
          label-position="left"
        >
          <el-row>
            <el-col>
              <el-form-item label="货物名称" prop="name">
                <el-input v-model="editListInfo.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20" align="middle">
            <el-col :span="12">
              <el-form-item label="规格型号" prop="type">
                <el-input v-model="editListInfo.type"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="器材编码" prop="code">
                <el-input v-model="editListInfo.code"></el-input>
              </el-form-item>
            </el-col>

          </el-row>
          <el-row :gutter="20" align="middle">

            <el-col :span="6">
              <el-form-item label="数量:" prop="num">
                <el-input v-model="editListInfo.num"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="单位" prop="unit">
                <el-select v-model="editListInfo.unit">
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
          <el-row>
             <el-col>
              <el-form-item label="生产日期:" prop="productionTime">
                <el-date-picker
                  style="width:30%"
                  v-model="editListInfo.productionTime"
                  type="date"
                  value-format="yyyy-MM-dd"
                ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-row>
            <el-col>
              <el-form-item label="供货方:" prop="provide">
                <el-input v-model="editListInfo.provide"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col>
              <el-form-item label="备注" label-width="auto" prop="note">
                <el-input type="textarea" v-model="editListInfo.note"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="showEdit = false">取 消</el-button>
          <el-button type="primary" @click="showEdit = false">编 辑</el-button>
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
                  <h3>领料清单</h3>
                </th>
              </tr>
              <tr>
                <th class="hideBorder" colspan="10">
                  <span>领料联系人：{{delivery.name}}</span>
                  <span style="margin-left:30px">联系电话：{{delivery.phone}}</span>
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
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item,index) in delivery.deliveryListInfoList">
                <td>{{(index+1)}}</td>
                <td>{{item.name}}</td>
                <td>{{item.type}}</td>
                <td>{{item.code}}</td>
                <td>{{item.num}}</td>
                <td>{{item.unit}}</td>
                <td>{{item.productionTime|dateTime}}</td>
                <td>{{item.productionCompany}}</td>
                <td>{{item.note}}</td>
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
      showEdit: false,
      delivery: {
        id: "",
        name: "",
        phone: "",
        deliveryListInfoList: []
      },
      deliveryRules: {
        name: [
          { required: true, message: "领料联系人不能为空", trigger: "blur" },
          {
            phone: true,
            message: "领料联系人电话不能为空",
            trigger: "blur"
          }
        ]
      },
      deliveryListInfo: {
        id: "",
        name: "",
        code: "",
        type: "",
        note: "",
        num: 0,
        unit: "台",
        provide: "",
        productionTime: new Date(),
      },
      editListInfo:{},
      deliveryListInfoRules: {
        name: [
          { required: true, message: "货物名字不能为空", trigger: "blur" }
        ],
        code: [
          { required: true, message: "器材编码不能为空", trigger: "blur" }
        ],
        type: [{ required: true, message: "规格型号不能空", trigger: "blur" }],
        note: [{ required: true, message: "备注不能为空", trigger: "blur" }],
        provide: [{ required: true, message: "供货方不能为空", trigger: "blur" }],
        num: [
          { required: true, message: "数量不能为空", trigger: "blur" }
        ],
        unit: [{ required: true, message: "单位不能为空", trigger: "blur" }],
        productionTime: [
          { required: true, message: "生产日期不能为空", trigger: "blur" }
        ],
      },
      unitArr: [
        { label: "台", value: "台" },
        { label: "盒", value: "盒" },
        { label: "个", value: "个" },
        { label: "瓶", value: "瓶" },
        { label: "箱", value: "箱" }
      ],
      saveAndPrint:false,
    };
  },
  methods: {
    init(){
      this. delivery= {
        id: "",
        name: "",
        phone: "",
        deliveryListInfoList: []
      };
    },
    showAddDialog() {
      this.showAdd = true;
    },
    saveItem() {
      // this.showAdd = false;
      const flag = this.submitForm("deliveryListInfo");
      console.log(this.deliveryListInfo);
      console.log(this.delivery);
    },
    showPrintDialog() {
      this.saveAndPrint = true;
      this.$refs["delivery"].validate(valid => {
         if (valid) {
           this.showPrint = true;
         }
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.deliveryListInfo.id = Date.parse(new Date());
          let target=JSON.parse(JSON.stringify(this.deliveryListInfo)); 
          this.delivery.deliveryListInfoList.push(target);
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
    handleEdit(item){
      this.editListInfo = item
      this.showEdit = true
    },
    handledel(row){
      console.log(row)
      this.$confirm('确定要删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.delivery.deliveryListInfoList.some((item, i)=>{
          if(item.id== row.id){
            this.delivery.deliveryListInfoList.splice(i, 1)
            return true
          }
        })
　　　　　　　　　　　　　　　　
      }).catch(() => {

      })
    }
  },
  created() {
  
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

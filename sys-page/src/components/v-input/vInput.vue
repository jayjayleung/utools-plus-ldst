<template>
  <div class="table-input" style="100%">
    <el-row :gutter="30">
      <el-form size="medium " :inline="false" label-width="100px" label-position="left">
        <el-col
          :xs="xs"
          :sm="sm"
          :md="md"
          :lg="lg"
          :xl="xl"
          v-for="(control, index) in controlArray"
          :key="index"
          :span="24"
        >
          <!-- 普通输入框 -->
          <el-form-item v-if="control.type=='input'" :label="control.text">
            <el-input
              :id="'control'+index"
              :name="control.key"
              v-model="control.value"
              :placeholder="control.placeholder"
            ></el-input>
          </el-form-item>
          <!-- 日期 -->
          <el-form-item v-if="control.type=='datePicker'" :label="control.text">
            <el-date-picker
              style="width:100%"
              v-model="control.value"
              type="date"
              :placeholder="control.placeholder"
            ></el-date-picker>
          </el-form-item>
          <!-- 时间 -->
          <el-form-item v-if="control.type=='timePicker'" :label="control.text">
            <el-time-picker :placeholder="control.text" v-model="control.value" style="width: 100%;"></el-time-picker>
          </el-form-item>
          <!-- 日期时间 -->
          <el-form-item v-if="control.type=='dateTimePicker'" :label="control.text">
            <el-date-picker
              v-model="control.value"
              type="datetime"
              :placeholder="control.text"
              style="width: 100%;"
            ></el-date-picker>
          </el-form-item>
          <!-- 单选下拉 -->
          <el-form-item v-if="control.type=='selectonly'" :label="control.text">
            <el-select v-model="control.value" :placeholder="control.text" style="width: 100%;">
              <template v-if="control.options">
                <el-option
                  v-for="item in control.options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </template>
            </el-select>
          </el-form-item>
          <!-- 多选下拉 -->
          <el-form-item v-if="control.type=='selectmore'" :label="control.text">
            <el-select
              v-model="control.value"
              multiple
              :placeholder="control.text"
              style="width: 100%;"
            >
              <template v-if="control.options">
                <el-option
                  v-for="item in control.options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </template>
            </el-select>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
    <el-row type="flex" justify="end">
      <el-col :span="8">
        <el-button type="primary" @click="search()">搜索</el-button>
        <el-button @click="reset()">重置</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  props: {
    controlArray: {
      type: Array,
        default: ()=> [
        ]
    }
  },
  data() {
    return {
      xs: 24,
      sm: 12,
      md: 12,
      lg: 8,
      xl: 8
    };
  },
  methods: {
    reset() {
      if (this.controlArray && this.controlArray.length > 0) {
        this.controlArray.forEach(item => {
          item.value = null;
        });
      }
    },
    search() {
      let map = [];
      if (this.controlArray && this.controlArray.length > 0) {
        this.controlArray.forEach(item => {
          map.push({key:item.key,value:item.value})
        });
      }
      this.$emit("search", map);
    }
  }
};
</script>

<style lang="scss">
  .table-input{
    background-color: #ffffff;
  }
</style>

/* 是否身份证号码*/
export function validateIdNo(rule, value,callback) {
    const reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if(value==''||value==undefined||value==null){
      callback();
    }else {
      if ((!reg.test(value)) && value != '') {
        callback(new Error('请输入正确的身份证号码'));
      } else {
        callback();
      }
    }
  }
  /* 是否邮箱*/
  export function validateEMail(rule, value,callback) {
    const reg =/^([a-zA-Z0-9]+[-_\.]?)+@[a-zA-Z0-9]+\.[a-z]+$/;
    if(value==''||value==undefined||value==null){
      callback();
    }else{
      if (!reg.test(value)){
        callback(new Error('请输入正确的邮箱地址'));
      } else {
        callback();
      }
    }
  }

  export function submitForm(formName) {
    this.$refs[formName].validate(valid => {
      if (valid) {
        return true
      } else {
        console.log("error submit!!");
        return false;
      }
    });
  }
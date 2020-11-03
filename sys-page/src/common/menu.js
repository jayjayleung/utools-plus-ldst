/*
 * @Description: 
 * @Author: jayjay
 * @Github: 
 * @Date: 2020-06-06 15:39:14
 * @LastEditors: jayjay
 * @LastEditTime: 2020-06-07 17:01:31
 */
const nomalMenu =[
  {
    menuCode:'delivery',
    menuName: "货物清单",
    menuUrl: "",
    menuIcon: "el-icon-truck",
    itemMenus: [
      {
        menuCode:'addDeliveryList',
        menuName: "新建发货单",
        menuUrl: "/addDeliveryList",
        menuIcon: ""
      },
      {
        menuCode:'deliveryInfo',
        menuName: "现有清单",
        menuUrl: "/deliveryInfo",
        menuIcon: ""
      }
    ]
  },
  {
    menuCode:'deliveryTag',
    menuName: "货物标签",
    menuUrl: "/deliveryTag",
    menuIcon: "el-icon-collection-tag",
    itemMenus: []
  },
  {
    menuCode:'warehouse',
    menuName: "装箱标签",
    menuUrl: "",
    menuIcon: "el-icon-box",
    itemMenus: [
      {
        menuCode:'warehouse',
        menuName: "装箱标签",
        menuUrl: "/warehouseManagement",
        menuIcon: ""
      },
      {
        menuCode:'picking',
        menuName: "新建领料单",
        menuUrl: "/picking",
        menuIcon: ""
      }
    ]
  }
];
const adminMenu = [
  {
    menuCode:'usermananger',
    menuName: "用户管理",
    menuUrl: "/userManagement",
    menuIcon: "el-icon-user-solid",
    itemMenus: []
  },
  // {
  //   menuCode:'delivery',
  //   menuName: "货物清单",
  //   menuUrl: "",
  //   menuIcon: "el-icon-truck",
  //   itemMenus: [
  //     {
  //       menuCode:'addDeliveryList',
  //       menuName: "创建新清单",
  //       menuUrl: "/addDeliveryList",
  //       menuIcon: ""
  //     },
  //     {
  //       menuCode:'deliveryList',
  //       menuName: "现有清单",
  //       menuUrl: "/deliveryList",
  //       menuIcon: ""
  //     }
  //   ]
  // },
  // {
  //   menuCode:'deliveryTag',
  //   menuName: "货物标签",
  //   menuUrl: "/deliveryFlag",
  //   menuIcon: "el-icon-collection-tag",
  //   itemMenus: []
  // },
  // {
  //   menuCode:'warehouse',
  //   menuName: "装箱标签",
  //   menuUrl: "/warehouseManagement",
  //   menuIcon: "el-icon-box",
  //   itemMenus: []
  // }
];  
const menuData = {
  nomal:nomalMenu,
  admin:adminMenu
}

  export default menuData

<template>
  <div>
    <el-col :span="3">
      <el-menu
        :default-active="this.$route.path"
        router
        mode="vertical"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
      >
        <el-menu-item v-for="(item, index) in mineList" :key="index" :index="item.path">
          <template>
            <i :class="item.icon"></i>
            <span slot="title">{{item.name}}</span>
          </template>
        </el-menu-item>
      </el-menu>
    </el-col>

    <keep-alive>
      <router-view class="menu-right" />
    </keep-alive>
  </div>
</template>

<script>
import { getOrder } from "network/order";
export default {
  data() {
    return {
      mineList: [
        {
          name: "我的订单",
          icon: "el-icon-date",
          path: "/center/order"
        },
        {
          name: "我的收藏",
          icon: "el-icon-date",
          path: "/center/collect"
        },
        {
          name: "我的房源",
          icon: "el-icon-edit-outline",
          path: "/center/myhome"
        },
        
        {
          name: "个人资料",
          icon: "el-icon-tickets",
          path: "/center/personal"
        }
      ]
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
      console.log("menu is handleOpen");
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
      console.log("menu is handleClose");
    },
    handleSelect(key, keyPath) {
      // console.log(key, keyPath);
      // if (key == "/center/order") {
      //   console.log("order");
      // }
      // if (key == "/center/collect") {
      //   console.log("collect");
      // }
    }
  }
};
</script>
<style scoped>
.menu-right {
  margin-left: 150px;
}
</style>
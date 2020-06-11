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
        style="width:210px"
      >
        <el-submenu v-for="(item, index) in mineList" :key="index" :index="item.path">
          <template slot="title">
            <i :class="item.icon"></i>
            {{item.name}}
          </template>
          <el-menu-item v-for="(citem, cindex) in item.child" :key="cindex" :index="citem.path">
            <template>
              <i :class="citem.icon"></i>
              <span slot="title">{{citem.name}}</span>
            </template>
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-col>

    <keep-alive>
      <router-view class="menu-right" />
    </keep-alive>
  </div>
</template>

<script>
export default {
  data() {
    return {
      mineList: [
        {
          name: "用户模块",
          icon: "el-icon-date",
          path: "user",
          child: [
            { name: "用户列表", icon: "el-icon-date", path: "/home/userlist" }
          ]
        },
        {
          name: "房源模块",
          icon: "el-icon-date",
          path: "home",
          child: [
            { name: "房屋列表", icon: "el-icon-date", path: "/home/homelist" }
          ]
        },
        {
          name: "订单模块",
          icon: "el-icon-edit-outline",
          path: "order",
          child: [
            { name: "订单列表", icon: "el-icon-date", path: "/home/orderlist" }
          ]
        },
        {
          name: "评价模块",
          icon: "el-icon-edit-outline",
          path: "comment",
          child: [
            { name: "评价列表", icon: "el-icon-date", path: "/home/commentlist" }
          ]
        }
      ]
    };
  },
  activated() {
    console.log(this.$route);
    
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
      console.log(key, keyPath);
    }
  }
};
</script>
<style scoped>
.menu-right {
  margin-left: 210px;
}
</style>
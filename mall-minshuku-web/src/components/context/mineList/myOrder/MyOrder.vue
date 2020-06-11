<template>
  <div>
    <el-tabs :v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane
        v-for="(item, index) in orderStatus"
        :key="index"
        :label="item.label"
        :name="item.name"
        
      >
        <no-data v-if="order == null || order == '' || order=={}" />
        <order-list :data="order" v-if="order != null && order != '' && order !={}" />
        <el-col :span="4" :offset="10">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            @prev-click="prevClick"
            background
            :hide-on-single-page="value"
            layout="total, prev, pager, next"
            :current-page.sync="currentPage"
            :page-size="pageSize"
            :total="total"
            class="pagination"
          ></el-pagination>
        </el-col>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import OrderList from "./OrderList";
import NoData from "components/context/noData/NoData";

import { getOrder } from "network/order";
export default {
  name: "MyOrder",
  data() {
    return {
      activeName: "0",
      orderStatus: [
        { label: "未开始", name: "0" },
        { label: "进行中", name: "1" },
        { label: "已完成", name: "2" }
      ],
      order: [],
      total: null,
      pageSize: null,
      currentPage: null,
      status: null,
      value: true,
      showNoData: false
    };
  },
  created() {
    console.log("MyOrder created...");
    this.getOrder1(localStorage.getItem("userId"));
  },
  activated() {
    console.log("MyOrder activated....");
    this.getOrder1(localStorage.getItem("userId"));
  },
  methods: {
    getOrder1(userId, status, currentPage, pageSize) {
      getOrder(userId, status, currentPage, pageSize).then(res => {
        this.order = res.data.data.list;
        console.log("订单数据 -- ",this.order == "");
        if ((this.order == "")) {
          this.showNoData = true;
        }
        this.total = res.data.data.total;
        this.pageSize = res.data.data.pageSize;
        this.currentPage = res.data.data.pageNum;
        // 如果页数大于1则显示分页
        if (res.data.data.totalPage > 1) {
          this.value = false;
        }
      });
    },

    // 标签页点击方法
    handleClick(tab, event) {
      console.log(tab, event);
      console.log(tab.index);
      this.status = tab.index;
      this.getOrder1(
        localStorage.getItem("userId"),
        tab.index,
        this.currentPage,
        this.pageSize
      );
    },
    // 分页点击方法
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.getOrder1(
        localStorage.getItem("userId"),
        this.status,
        val,
        this.pageSize
      );
    },
    prevClick() {
      console.log("pre");
    }
  },
  components: {
    OrderList,
    NoData
  }
};
</script>

<style scoped>
</style>
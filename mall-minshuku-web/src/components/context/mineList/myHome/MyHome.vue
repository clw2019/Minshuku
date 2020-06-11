<template>
  <div>
    <no-data v-if="myHomeList == null || myHomeList == '' || myHomeList=={}" />
    <home-list :myHome="myHomeList" v-if="myHomeList != null && myHomeList != '' && myHomeList !={}" />
    <div>
      <el-col :span="4" :offset="10">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          @prev-click="prevClick"
          background
          layout="total, prev, pager, next"
          :hide-on-single-page="value"
          :current-page.sync="currentPage"
          :page-size="pageSize"
          :total="total"
          class="pagination"
        ></el-pagination>
      </el-col>
    </div>
  </div>
</template>

<script>
import { getMyHome } from "network/home";

import NoData from "components/context/noData/NoData";
import HomeList from "./HomeList";
export default {
  name: "MyHome",
  data() {
    return {
      myHomeList: [],
      total: null,
      pageSize: null,
      currentPage: null,
      value: true
    };
  },
  activated() {
    // 请求我的房源数据
    this.getMyHome(localStorage.getItem("userId"), this.currentPage, this.pageSize);
  },
  methods: {
    getMyHome(hostId, currentPage, pageSize) {
      getMyHome(hostId, currentPage, pageSize).then(res => {
        this.myHomeList = res.data.data.list;
        this.total = res.data.data.total;
        this.pageSize = res.data.data.pageSize;
        this.currentPage = res.data.data.pageNum;
        // 如果页数大于1则显示分页
        if (res.data.data.totalPage > 1) {
          this.value = false;
        }
      });
    },
    // 分页点击方法
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.getMyHome(localStorage.getItem("userId"), val, this.pageSize);
    },
    prevClick() {
      console.log("pre");
    }
  },
  components: {
    HomeList,
    NoData
  }
};
</script>

<style>
</style>
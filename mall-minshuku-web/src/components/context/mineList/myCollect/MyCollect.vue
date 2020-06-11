<template>
  <div>
    <no-data v-if="collectList == null || collectList == '' || collectList=={}" />
    <collect-list :collect="collectList" v-if="collectList != null && collectList != '' && collectList !={}" />

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
      <!-- :hide-on-single-page="value" 小于一页不显示分页 -->
    </el-col>
  </div>
</template>

<script>
import CollectList from "./CollectList";
import NoData from "components/context/noData/NoData";

import { selectCollectByUserId } from "network/collect";
export default {
  name: "MyCollect",
  data() {
    return {
      collectList: [],
      total: null,
      pageSize: 6,
      currentPage: 1,
      value: true
    };
  },
  created() {
    console.log("MyCollect created...");
    // this.selectCollectByUserId(localStorage.getItem("userId"));
  },
  activated() {
    console.log("MyCollect activated....");
    // 请求收藏数据
    this.selectCollectByUserId(localStorage.getItem("userId"),this.currentPage, this.pageSize);
  },
  methods: {
    selectCollectByUserId(userId, currentPage, pageSize) {
      selectCollectByUserId(userId, currentPage, pageSize).then(res => {
        this.collectList = res.data.data.list;
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
      this.selectCollectByUserId(
        localStorage.getItem("userId"),
        val,
        this.pageSize
      );
    },
    prevClick() {
      console.log("pre");
    }
  },
  components: {
    CollectList,
    NoData
  }
};
</script>

<style>
</style>
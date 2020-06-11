<template>
  <div>
    <div class="search">
      <el-input
        prefix-icon="el-icon-search"
        placeholder="根据订单ID查找"
        v-model="input"
        style="width:150px"
      ></el-input>
      <el-button type="primary" @click="searchByHome">查询</el-button>
    </div>
    <el-table :data="orderList" stripe style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand" align="center">
            <el-form-item label="房东姓名">
              <span>{{ props.row.hostName }}</span>
            </el-form-item>
            <el-form-item label="居住日期">
              <span>{{ props.row.startTime }} - {{props.row.endTime}}</span>
            </el-form-item>
            <el-form-item label="价格">
              <span>￥{{ props.row.price }}</span>
            </el-form-item>
            <el-form-item label="天数">
              <span>{{ props.row.liveDays }}</span>
            </el-form-item>
            <el-form-item label="共支付">
              <span>{{ props.row.countPrice }}</span>
            </el-form-item>
           
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="订单 ID" prop="id" sortable align="center"></el-table-column>
      <el-table-column label="租客名称" prop="guestName" align="center"></el-table-column>
      <el-table-column label="证件类型" prop="type" align="center"></el-table-column>
      <el-table-column label="证件号码" prop="idNum" align="center"></el-table-column>
      <el-table-column label="房屋地址" prop="address" align="center"></el-table-column>
      <el-table-column label="创建时间" prop="createTime" sortable align="center"></el-table-column>
      <!-- <el-table-column label="状态" prop="status" align="center">
        <template slot-scope="scope">
          <el-button
            type="success"
            v-if="scope.row.startTime > new Date().toLocaleDateString()"
            size="small"
          >未开始</el-button>
          <el-button type="danger" v-else-if="scope.row.endTime < new Date().toLocaleDateString()"  size="small">已结束</el-button>
          <el-button type="primary" v-else size="small">进行中</el-button>
        </template>
      </el-table-column> -->
    </el-table>
    <div class="bottom-part">
      <el-pagination
        background
        layout="total, sizes, prev, pager, next, jumper"
        :page-size="pagination.pageSize"
        :page-sizes="[5,10,20,30,50]"
        :total="pagination.total"
        :current-page="pagination.currentPage"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import { getOrder } from "@/network/order";
export default {
  name: "HomeList",
  data() {
    return {
      input: "",
      orderList: [],
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: null
      }
    };
  },
  activated() {
    this.getOrder(this.pagination.currentPage, this.pagination.pageSize);
  },
  methods: {
    // 网络请求方法
    getOrder(currentPage, pageSize) {
      getOrder(currentPage, pageSize).then(res => {
        this.orderList = res.data.data.list;
        this.pagination.currentPage = res.data.data.pageNum;
        this.pagination.pageSize = res.data.data.pageSize;
        this.pagination.total = res.data.data.total;
      });
    },
    searchByHome() {
      getOrder(
        this.pagination.currentPage,
        this.pagination.pageSize,
        this.input
      ).then(res => {
        if (res.data.code == 200) {
          this.orderList = res.data.data.list;
          this.pagination.currentPage = res.data.data.pageNum;
          this.pagination.pageSize = res.data.data.pageSize;
          this.pagination.total = res.data.data.total;
          this.$message({
            message: res.data.message,
            type: "success"
          });
        } else {
          this.$message({
            message: res.data.message,
            type: "success"
          });
        }
      });
    },

    // 事件点击方法
   

    // 分页点击方法
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.getOrder(this.pagination.currentPage, val);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.getOrder(val, this.pagination.pageSize);
    }
  }
};
</script>

<style scoped>
.search {
  padding: 20px;
}

.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  float: left;
  width: 50%;
}

.bottom-part {
  display: flex;
  justify-content: center;
  padding: 20px;
}
</style>
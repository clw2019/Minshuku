<template>
  <div>
    <div class="search">
      <el-input
        prefix-icon="el-icon-search"
        placeholder="根据房屋名查找"
        v-model="input"
        style="width:150px"
      ></el-input>
      <el-button type="primary" @click="searchByLike">查询</el-button>
    </div>
    <!-- <el-table
      ref="multipleTable"
      :data="commentList"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >-->
    <el-table :data="commentList" stripe style="width: 100%">
      <el-table-column prop="id" label="ID" width="80" show-overflow-tooltip align="center"></el-table-column>
      <el-table-column prop="orderId" label="订单ID" show-overflow-tooltip align="center"></el-table-column>
      <el-table-column prop="username" label="用户名" show-overflow-tooltip align="center"></el-table-column>
      <el-table-column prop="homeName" label="房屋名" show-overflow-tooltip align="center"></el-table-column>
      <el-table-column prop="userReview" label="评价" show-overflow-tooltip align="center"></el-table-column>
      <el-table-column prop="hostReview" label="回复" show-overflow-tooltip align="center"></el-table-column>
      <el-table-column prop="rate" label="评分" show-overflow-tooltip align="center"></el-table-column>
      <!-- <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>-->
    </el-table>
    <!-- <div style="margin-top: 20px">
      <el-button @click="toggleSelection([commentList[1], commentList[2]])">切换第二、第三行的选中状态</el-button>
      <el-button @click="toggleSelection()">取消选择</el-button>
    </div>-->
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
import { getComment } from "@/network/comment";
export default {
  name: "CommentList",
  data() {
    return {
      input: "",
      commentList: [],
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: null
      }
    };
  },
  activated() {
    this.getComment(this.pagination.currentPage, this.pagination.pageSize);
  },
  methods: {
    // 网络请求方法
    getComment(currentPage, pageSize, input) {
      getComment(currentPage, pageSize, input).then(res => {
        console.log(res);
        this.commentList = res.data.data.list;
        this.pagination.currentPage = res.data.data.pageNum;
        this.pagination.pageSize = res.data.data.pageSize;
        this.pagination.total = res.data.data.total;
      });
    },

    searchByLike() {
      getComment(
        this.pagination.currentPage,
        this.pagination.pageSize,
        this.input
      ).then(res => {
        if (res.data.code == 200) {
          this.commentList = res.data.data.list;
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
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    changeStatus(row) {
      changeStatus(row.id).then(res => {
        if (res.data.code == 200) {
          row.status = !row.status;
          this.$message({
            message: "状态更新成功",
            type: "success"
          });
        }
      });
    },

    // 分页点击方法
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.getComment(this.pagination.currentPage, val);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.getComment(val, this.pagination.pageSize);
    },

    toggleSelection(rows) {
      console.log("批量选择。。。 ", rows);

      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      console.log("qwer..... ", val);
      // let cList = val
      for (let c of val) {
        console.log("...., ", c.id);
      }
      this.multipleSelection = val;
    }
  }
};
</script>

<style scoped>
.search {
  padding: 20px;
}

.bottom-part {
  display: flex;
  justify-content: center;
  padding: 20px;
}
</style>
<template>
  <div>
    <div class="search">
      <el-input
        prefix-icon="el-icon-search"
        placeholder="根据用户名或者昵称查找"
        v-model="input"
        style="width:200px"
      ></el-input>
      <el-button type="primary" @click="searchUser">查询</el-button>
    </div>
    <el-table
      ref="multipleTable"
      :data="userList"
      tooltip-effect="dark"
      style="width: 100%"
      stripe
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column label="用户 ID" prop="id" sortable align="center" width="180"></el-table-column>
      <el-table-column label="头像" prop="icon" align="center">
        <template slot-scope="scope">
          <el-image
            style="width: 50px; height: 50px"
            :src="scope.row.icon"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column label="用户名称" prop="username" align="center" width="180"></el-table-column>
      <el-table-column label="昵称" prop="nick" align="center" width="180"></el-table-column>
      <el-table-column label="性别" prop="sex" align="center" width="180"></el-table-column>
      <el-table-column label="创建时间" prop="createTime" sortable align="center" width="180"></el-table-column>
      <el-table-column label="状态" prop="status" align="center" width="180">
        <template slot-scope="scope">
          <el-button
            type="primary"
            v-if="scope.row.status"
            @click="changeStatus(scope.row)"
            size="mini"
          >启用</el-button>
          <el-button type="danger" size="mini" v-else @click="changeStatus(scope.row)" >禁用</el-button>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <!-- <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>-->
          <el-button size="mini" type="danger" @click="handleReset(scope.$index, scope.row)">密码重置</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button type="primary" @click="resetPassword" :disabled="usesrIdList == ''">批量重置</el-button>
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
import { getUser, changeStatus, resetPassword } from "@/network/user";
export default {
  name: "UserList",
  data() {
    return {
      input: "",
      userList: [],
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: null
      },
      usesrIdList: []
    };
  },
  activated() {
    // 请求用户数据
    this.getUser(this.pagination.currentPage, this.pagination.pageSize);
    console.log(this.userList);
    
    
  },
  computed: {
    // getStatus() {
    //   if (status == 1) {
    //     this.statusType = "success";
    //     return "启用";
    //   }
    //   if (status == 0) {
    //     this.statusType = "danger";
    //     return "禁用";
    //   }
    // }
  },
  methods: {
    // 网络请求方法
    getUser(currentPage, pageSize, input) {
      getUser(currentPage, pageSize, input).then(res => {
        console.log(res);
        this.userList = res.data.data.list;
        this.pagination.currentPage = res.data.data.pageNum;
        this.pagination.pageSize = res.data.data.pageSize;
        this.pagination.total = res.data.data.total;
      });
    },

    searchUser() {
      getUser(
        this.pagination.currentPage,
        this.pagination.pageSize,
        this.input
      ).then(res => {
        if (res.data.code == 200) {
          this.userList = res.data.data.list;
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
            type: "waring"
          });
        }
      });
    },
    // 事件点击方法
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleReset(index, row) {
      console.log("465  ", index, row.id);
      this.usesrIdList.push(row.id);
      resetPassword(this.usesrIdList).then(res => {
        if (res.data.code == 200) {
          this.$message({
            message: res.data.message,
            type: "success"
          });
        } else {
          this.$message({
            message: res.data.message,
            type: "warning"
          });
        }
      });
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
      this.getUser(this.pagination.currentPage, val);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.getUser(val, this.pagination.pageSize);
    },
    handleSelectionChange(val) {
      console.log("qwer..... ", val);
      this.usesrIdList = [];
      // let cList = val
      for (let c of val) {
        console.log("...., ", c.id);
        this.usesrIdList.push(c.id);
      }
      this.multipleSelection = val;
    },
    resetPassword() {
      console.log("reset is click...", this.usesrIdList);
      resetPassword(this.usesrIdList).then(res => {
        if (res.data.code == 200) {
          this.$message({
            message: res.data.message,
            type: "success"
          });
        } else {
          this.$message({
            message: res.data.message,
            type: "warning"
          });
        }
      });
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
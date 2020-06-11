<template>
  <div>
    <div class="search">
      <el-input
        prefix-icon="el-icon-search"
        placeholder="根据房屋名查找"
        v-model="input"
        style="width:150px"
      ></el-input>
      <el-button type="primary" @click="searchByHome">查询</el-button>
    </div>
    <el-table :data="homeList" stripe style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand" align="center">
            <el-form-item label="房东">
              <span>{{ props.row.hostName }}</span>
            </el-form-item>
            <el-form-item label="面积">
              <span>{{ props.row.area }}㎡</span>
            </el-form-item>
            <el-form-item label="可居住人数">
              <span>{{ props.row.livepeople }}</span>
            </el-form-item>
            <el-form-item label>
              <span>{{ props.row.homeDetail.bedroom }}室{{ props.row.homeDetail.livingroom }}厅</span>
            </el-form-item>
            <el-form-item label="是否带厕所">
              <span v-if="props.row.homeDetail.kitchen">是</span>
              <span v-else>否</span>
            </el-form-item>
            <el-form-item label="是否带厨房">
              <span v-if="props.row.homeDetail.toilet">是</span>
              <span v-else>否</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="房屋 ID" prop="id" sortable align="center"></el-table-column>
      <el-table-column label="房屋名称" prop="title" align="center"></el-table-column>
      <el-table-column label="描述" prop="describe" align="center" show-overflow-tooltip></el-table-column>
      <el-table-column label="价格" prop="price" align="center"></el-table-column>
      <el-table-column label="收藏数" prop="collection" sortable align="center"></el-table-column>
      <el-table-column label="地址" prop="address" align="center" show-overflow-tooltip></el-table-column>
      <el-table-column label="图片" prop="image" align="center">
        <template slot-scope="scope">
          <el-image
            style="width: 50px; height: 50px"
            :src="scope.row.imageList[0]"
            :preview-src-list="scope.row.imageList"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="createTime" sortable align="center"></el-table-column>
      <el-table-column label="轮播" prop="flag" align="center">
        <template slot-scope="scope">
          <el-button
            type="success"
            v-if="scope.row.flag"
            @click="changeFlag(scope.row)"
            size="mini"
          >取消轮播</el-button>
          <el-button type="danger" v-else @click="changeFlag(scope.row)" size="mini">加入轮播</el-button>
        </template>
      </el-table-column>
      <el-table-column label="状态" prop="status" align="center">
        <template slot-scope="scope">
          <el-button
            type="success"
            v-if="scope.row.status"
            @click="changeStatus(scope.row)"
            size="mini"
          >已审核</el-button>
          <el-button type="danger" v-else @click="changeStatus(scope.row)" size="mini">待审核</el-button>
        </template>
      </el-table-column>
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
import {
  getHome,
  getHomeByLike,
  changeHomeStatus,
  changeFlag
} from "@/network/home";
export default {
  name: "HomeList",
  data() {
    return {
      input: "",
      homeList: [],
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: null
      }
    };
  },
  activated() {
    this.getHome(this.pagination.currentPage, this.pagination.pageSize);
  },
  methods: {
    // 网络请求方法
    getHome(currentPage, pageSize) {
      getHome(currentPage, pageSize).then(res => {
        this.homeList = res.data.data.list;
        this.pagination.currentPage = res.data.data.pageNum;
        this.pagination.pageSize = res.data.data.pageSize;
        this.pagination.total = res.data.data.total;
      });
    },
    searchByHome() {
      getHomeByLike(
        this.pagination.currentPage,
        this.pagination.pageSize,
        this.input
      ).then(res => {
        if (res.data.code == 200) {
          this.homeList = res.data.data.list;
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
    changeStatus(row) {
      changeHomeStatus(row.id).then(res => {
        if ((res.data.code = 200)) {
          row.status = !row.status;
        }
        this.$message({
          message: res.data.message,
          type: "success"
        });
      });
    },
    changeFlag(row) {
      console.log("click before status is : ", row.flag);
      console.log("row.imageList[0]  ", row.imageList[0]);
      
      changeFlag(row.id, row.flag, row.imageList[0]).then(res => {
        
        if ((res.data.code = 200)) {
          row.flag = ~row.flag + 2;
          console.log("click after status is : ", row.flag);
        }
        this.$message({
          message: res.data.message,
          type: "success"
        });
      });
    },

    // 分页点击方法
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.getHome(this.pagination.currentPage, val);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.getHome(val, this.pagination.pageSize);
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
  width: 33%;
}

.bottom-part {
  display: flex;
  justify-content: center;
  padding: 20px;
}
</style>
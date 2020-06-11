<template>
  <div class="detail-comment">
    <div>
      <el-rate
        v-model="detailData.avgRate"
        disabled
        show-score
        text-color="#ff9900"
        score-template="{value}"
      ></el-rate>
    </div>
    <el-divider></el-divider>

    <div class="comment-item" v-for="(item, index) in reviewList123" :key="index">
      <div class="userIcon">
        <el-avatar shape="square" :size="56" fit="fill" :src="item.icon"></el-avatar>
      </div>

      <div class="item-content">
        <div class="nick">{{item.nick}}</div>
        <el-rate
          v-model="item.rate"
          disabled
          show-score
          text-color="#ff9900"
          score-template="{value}"
        ></el-rate>
        <span class="time">{{item.createTime}}</span>
        <el-divider></el-divider>
        <div class="user-comment">{{item.userReview}}</div>
        <el-divider></el-divider>
        <div
          class="host-review"
          v-if="item.hostReview != null && item.hostReview != '' && item.hostReview !={}"
        >
          <h5>房东回复：</h5>
          {{item.hostReview}}
        </div>
      </div>
    </div>
    <div class="bottom-part">
      <el-pagination
        background
        layout="total, sizes, prev, pager, next, jumper"
        :page-size="pagination.pageSize"
        :page-sizes="[1,2,4,8,30,50]"
        :total="pagination.total"
        :current-page="pagination.currentPage"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "DetailComment",
  data() {
    return {
      isSuite: {},
      avgValue: null,
      pagination: {
        currentPage: 1,
        pageSize: 4,
        total: this.detailData.reviewList.length
      },
      reviewList123: this.detailData.reviewList
    };
  },
  methods: {
    // 前端分页数据处理方法
    handleData(currentPage, pageSize) {
      this.reviewList123 = this.detailData.reviewList.slice(
        (currentPage - 1) * pageSize,
        currentPage * pageSize
      );
    },
    // 分页点击方法
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pagination.pageSize = val;
      this.handleData(this.pagination.currentPage, val);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.handleData(val, this.pagination.pageSize);
    }
  },
  props: {
    detailData: {
      type: Object,
      default() {
        return {};
      }
    }
  }
};
</script>

<style scoped>
.detail-comment {
  padding: 20px 32px;
  background-color: #ffffff;
}

.comment-item {
  display: flex;
  margin-bottom: 20px;
}

.userIcon {
  width: 10%;
}

.item-content {
  width: 90%;
}

.nick {
  font-size: 14px;
  font-weight: bold;
  color: #f05b72;
}

.time {
  font-size: 14px;
}

.host-review h5 {
  font-size: 14px;
  color: #959ea7;
  margin-bottom: 10px;
}

.bottom-part {
  display: flex;
  justify-content: center;
  padding: 20px;
}
</style>
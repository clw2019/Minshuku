<template>
  <div class="detail-comment">
    <div>
      <el-rate v-model="avgRate" disabled show-score text-color="#ff9900" score-template="{value}"></el-rate>
    </div>
    <el-divider></el-divider>
    <div v-if="reviewList != null && reviewList != '' && reviewList !={}">
      <div class="comment-item" v-for="(item, index) in reviewList" :key="index">
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
          <div class="review-btn">
            <el-button
              size="mini"
              disabled
              v-if="item.hostReview != null && item.hostReview != '' && item.hostReview !={}"
            >已回复</el-button>
            <el-button size="mini" v-else @click="reviewBtn(item.id)">待回复</el-button>
          </div>
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
    <no-data v-if="reviewList == null || reviewList == '' || reviewList=={}" />
    <el-dialog title="提示" :visible.sync="centerDialogVisible" width="30%" center>
      <!-- :before-close="clearContent" -->
      <span>
        <el-input type="textarea" :rows="2" placeholder="请输入回复内容" v-model="reviewContent"></el-input>
      </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="review">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getDetail } from "network/detail";
import { review } from "network/comment";

import NoData from "components/context/noData/NoData";
export default {
  name: "EditReview",
  data() {
    return {
      reviewList: [],
      reviewList1: [],
      reviewList101: [],
      avgValue: 0,
      centerDialogVisible: false,
      reviewContent: "",
      reviewId: null,
      pagination: {
        currentPage: 1,
        pageSize: 4,
        total: null
      },
      avgRate: 0
    };
  },
  activated() {
    this.getDetail(this.$route.params.id);

    console.log(
      "动态路由传参",
      this.$route.params.id,
      this.reviewList,
      this.pagination.total
    );
  },
  methods: {
    getDetail(homeId) {
      getDetail(homeId).then(res => {
        console.log(".........", res.data.data[0]);
        this.reviewList1 = res.data.data[0].reviewList;
        this.reviewList101 = res.data.data[0].reviewList;
        this.avgRate = res.data.data[0].avgRate;
        // this.total = res.data.data[0].reviewList.length;
        console.log("````````````````", this.reviewList.length);
        this.pagination.total = res.data.data[0].reviewList.length;
        this.handleData(this.pagination.currentPage, this.pagination.pageSize);
      });
    },
    // 前端分页数据处理方法
    handleData(currentPage, pageSize) {
      this.reviewList = this.reviewList1.slice(
        (currentPage - 1) * pageSize,
        currentPage * pageSize
      );
      this.reviewList1 = [];
      this.reviewList1 = this.reviewList101;
    },
    // 分页点击方法
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pagination.pageSize = val;
      this.handleData(this.pagination.currentPage, val);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`, this.pagination.pageSize);
      this.handleData(val, this.pagination.pageSize);
    },
    reviewBtn(id) {
      console.log("reviewBtn is click...", id);
      this.reviewId = id;
      this.centerDialogVisible = true;
    },
    review() {
      console.log("reviewId === ", this.reviewId);

      review(parseInt(this.reviewId), this.reviewContent).then(res => {
        if (res.data.code == 200) {
          this.centerDialogVisible = false;

          this.getDetail(this.$route.params.id);
          this.$message({
            message: res.data.message,
            type: "success"
          });
        }
      });
    },
    // 关闭Dialog，清楚内容
    cleanContent(done) {
      this.$nextTick(() => {
        this.$refs["reviewForm"].resetFields();
        done();
      });
    }
  },
  components: {
    NoData
  }
  // computed: {
  //   avgRate() {
  //     let avgRate = 0;
  //     if (this.reviewList == "") {
  //       return avgRate;
  //     }
  //     for (let item of this.reviewList) {
  //       avgRate += item.rate;
  //     }
  //     avgRate = avgRate / this.reviewList.length;
  //     this.avgValue = Math.floor(avgRate * 10) / 10; //把计算的结果放到data中, 结果是3.5,保留一位小数
  //     //   return avgRate;
  //   }
  // }
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

.review-btn {
  padding-left: 790px;
  padding-top: 5px;
  /* 这个会导致页面变宽 */
  /* position: relative;
  left: 790px;
  top: 15px; */
}

.bottom-part {
  display: flex;
  justify-content: center;
  padding: 20px;
}
</style>
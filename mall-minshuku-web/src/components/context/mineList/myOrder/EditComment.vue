<template>
  <div class="edit-comment">
    <p>房屋名称：{{this.$route.params.title}} {{this.$route.params.homeId}}</p>
    <div class="home-img">
      <img :src="this.$route.params.pic" alt />
    </div>
    <p>评分：</p>
    <el-rate v-model="commentInfo.rate" show-score text-color="#ff9900" score-template="{value}"></el-rate>
    <el-input
      type="textarea"
      placeholder="请输入内容"
      v-model="commentInfo.userReview"
      maxlength="100"
      show-word-limit
    ></el-input>
    <p>
      <el-button type="primary" @click="commitClick">评价</el-button>
    </p>
  </div>
</template>

<script>
import { editComment } from "network/comment";
export default {
  name: "EditComment",
  data() {
    return {
      commentInfo: {
        userId: localStorage.getItem("userId"),
        orderId: this.$route.params.id,
        homeId: this.$route.params.homeId,
        userReview: "",
        rate: 5
      }
    };
  },
  activated() {
    console.log("orderId... " + this.$route.params.id);
    
  },
  methods: {
    commitClick() {
      console.log("提交评论...", this.commentInfo);
      editComment(this.commentInfo).then(res => {
        if (res.data.code == 200) {
          this.$message({
            message: "评论成功",
            type: "success"
          });
          // this.$router.push("/center/order")
          this.$router.go(-1)
        } else {
          this.$message({
            message: "评论失败，请重新尝试",
            type: "warning"
          });
        }
      });
    }
  }
};
</script>

<style scoped>
.edit-comment {
  font-family: "楷体";
  padding: 20px 80px;
  background-color: #ffffff;
  padding-top: 20px;
  height: 600px;
}

.edit-comment p {
  padding-top: 10px;
  padding-bottom: 10px;
}

.home-img img {
  width: 150px;
  height: 100px;
}
</style>
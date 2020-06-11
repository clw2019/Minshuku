<template>
  <div class="detail-comment">
    <div>
      <!--{{avgRate}} 不写这个数据就是1 暂未解决 -->
      {{avgRate}}
      <el-rate v-model="avgValue" disabled show-score text-color="#ff9900" score-template="{value}"></el-rate>
    </div>
    <el-divider></el-divider>

    <div class="comment-item" v-for="(item, index) in detailData.reviewList" :key="index">
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
  </div>
</template>

<script>
export default {
  name: "MyReview",
  data() {
    return {
      isSuite: {},
      avgValue: 0
    };
  },
  // activated() {
  //   console.log("this.$route.params.reviewList  ",this.$route.params.reviewList);

  //   this.re = this.$route.params.reviewList;
  // },
  computed: {
    avgRate() {
      let avgRate = 0;
      if (this.detailData.reviewList == "") {
        return avgRate;
      }
      for (let item of this.detailData.reviewList) {
        avgRate += item.rate;
      }
      avgRate = avgRate / this.detailData.reviewList.length;
      this.avgValue = Math.floor(avgRate * 10) / 10; //把计算的结果放到data中, 结果是3.5,保留一位小数
      //   return avgRate;
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
</style>
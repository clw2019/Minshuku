<template>
  <div class="order-item" @click="orderItemClick">
    <div class="order-item-container">
      <div class="imgDiv">
        <img :src="orderItem.picList[0].imgurl" alt />
      </div>
      <div class="order-info">
        <p>订单编号：{{orderItem.id}}</p>
        <h4>
          <p>房源名称：{{orderItem.title}}</p>
        </h4>
        <p>房源地址：{{orderItem.address}}</p>
        <p>居住日期：{{orderItem.startTime}} - {{orderItem.endTime}}</p>
        <p>房屋价格：￥{{orderItem.price}} / 晚 天数：{{orderItem.liveDays}} 天</p>
        <p>创建时间：{{orderItem.createTime}}</p>
        <h3>
          <span class="price">总价：￥{{orderItem.countPrice}}</span>
        </h3>
        <div class="comment">
          <el-button
            size="mini"
            v-if="orderItem.reviewList[0] != null && orderItem.reviewList[0].userReview != null"
            disabled
          >已评价</el-button>
          <div v-else>
            <el-button
              type="primary"
              v-if="new Date().getTime() > new Date(orderItem.endTime).getTime()"
              @click.stop="commentCilck(orderItem.id)"
            >评价</el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "OrderItem",
  data() {
    return {
      id: this.orderItem.id,
      homeId: this.orderItem.homeId,
      pic: this.orderItem.picList[0].imgurl,
      title: this.orderItem.title,
      showButton: null
    };
  },
  props: {
    orderItem: {
      type: Object,
      default() {
        return {};
      }
    }
  },
  methods: {
    orderItemClick() {
      this.$router.push("/orderdetail/" + this.orderItem.id); //动态路由
    },
    commentCilck(orderId) {
      console.log("orderId", orderId);
      // this.$router.push("/editcomment/" + this.orderItem.id);
      this.$router.push({
        name: "EditComment",
        params: {
          id: this.id,
          homeId: this.homeId,
          pic: this.pic,
          title: this.title
        }
      });
    }
  },
  computed: {
    // 判断是否可以评价 ，在这里写会有问题，直接写在html里
    // isShowButton() {
    //   let currentTime = new Date().getTime();
    //   let endTime = new Date(
    //     Date.parse(this.orderItem.endTime.replace("///g", "/"))
    //   ).getTime();
    //   return (this.showButton = endTime > currentTime);
    // }
  }
};
</script>

<style scoped>
.order-item {
  width: 950px;
  height: 180px;
  padding: 10px 8px;
  margin: 5px;
}

.order-item-container {
  width: 950px;
  height: 170px;
  padding: 4px;
  background-color: #ffffff;
  border-radius: 6px;
}

.order-item img {
  border-radius: 6px;
  width: 250px;
  height: 150px;
}

/* 使文字图片实现顶部对齐 */
.imgDiv {
  width: 254px;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  float: left;
}

.order-info {
  font-family: "楷体";
  font-size: 16px;
}

.order-info p {
  overflow: hidden; /**溢出隐藏 */
  text-overflow: ellipsis; /**ellipsis : 　当对象内文本溢出时显示省略标记（...） */
  white-space: nowrap; /**规定段落中的文本不进行换行： */
  /* margin-bottom: 10px; */
  margin: 4px;
}

.order-info .price {
  color: var(--color-high-text);

  /* margin-right: 20px; */
}

.comment {
  float: right;
  position: relative;
  top: -40px;
}
</style>
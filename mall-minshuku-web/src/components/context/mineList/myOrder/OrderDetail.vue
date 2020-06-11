<template>
  <div class="detail-order">
    <main-bar>
      <div slot="center">
        <div class="center">
          <div class="home-detail">
            <h3>房屋详情</h3>
            <carousel :carousel="carousel" type="card" />
            <p>
              名称：
              <span>
                <el-button type="text" @click="changDetail" style=" color: red;font-size: 18px">{{orderDetail.title}}</el-button>
              </span>
            </p>
            <p>
              地址：
              <span>{{orderDetail.address}}</span>
            </p>
            <p>
              价格：
              <span>￥{{orderDetail.price}} / 晚</span>
            </p>
          </div>
          <div class="host-detail">
            <h3>房东详情</h3>
            <p>
              <img :src="user.icon" alt />
            </p>
            <p>
              姓名：
              <span>{{user.nick}}</span>
            </p>
            <p>
              性别：
              <span>{{user.sex}}</span>
            </p>
            <p>
              联系方式：
              <span>￥{{user.phonenumber}}</span>
            </p>
          </div>
          <div class="book-detail">
            <h3>预订人详情</h3>
            <p>
              姓名：
              <span>{{orderDetail.liveName}}</span>
            </p>
            <p>
              证件类型：
              <span>{{orderDetail.type}}</span>
            </p>
            <p>
              证件号码：
              <span>￥{{orderDetail.idNum}}</span>
            </p>
            <p>
              联系方式：
              <span>{{orderDetail.phoneNum}}</span>
            </p>
          </div>
          <div class="order-detail">
            <h3>订单详情</h3>
            <p>
              订单编号：
              <span>{{orderDetail.id}}</span>
            </p>
            <p>
              起始日期：
              <span>{{orderDetail.startTime}}--{{orderDetail.endTime}}</span>
            </p>
            <p>
              订单价格：
              <span>￥{{orderDetail.countPrice}}</span>
            </p>
            <p>
              创建时间：
              <span>{{orderDetail.createTime}}</span>
            </p>
          </div>
        </div>
      </div>
    </main-bar>
  </div>
</template>

<script>
import MainBar from "components/context/mainBar/MainBar";

import Carousel from "components/common/carousel/Carousel";

import { getOrderDetail } from "network/order";
export default {
  name: "OrderDetail",
  data() {
    return {
      orderId: null,
      carousel: [],
      orderDetail: {},
      user: {}
    };
  },
  activated() {
    // 保存传入的订单id
    this.orderId = this.$route.params.id;
    // 根据id请求详细数据
    this.getOrderDetail();
  },
  methods: {
    getOrderDetail() {
      getOrderDetail(this.orderId).then(res => {
        console.log("detail-order", res);
        this.carousel = res.data.data.picList;
        this.orderDetail = res.data.data;
        this.user = res.data.data.user;
      });
    },


    changDetail() {
      console.log("1987");
      this.$router.push("/detail/" + this.orderDetail.homeId)
    }
  },
  components: {
    MainBar,
    Carousel
  }
};
</script>

<style scoped>
.center {
  margin: 0 auto;
  width: 77%;
}
.center h3 {
  margin-bottom: 20px;
}

.home-detail,
.host-detail,
.book-detail,
.order-detail {
  padding: 40px 0px 40px 0px;
}
.home-detail img {
  width: 200px;
  height: 120px;
  background-color: #ffffff;
}

.home-detail span {
  font-family: "楷体";
  font-size: 18px;
}

.host-detail img {
  width: 50px;
  height: 50px;
}

.host-detail,
.book-detail,
span {
  font-family: "楷体";
  font-size: 18px;
  padding-right: 40px;
}
</style>
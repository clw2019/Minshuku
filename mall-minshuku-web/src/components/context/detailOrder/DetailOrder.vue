<template>
  <div class="detail-order">
    <el-steps :active="active" finish-status="success" align-center>
      <el-step title="基本信息"></el-step>
      <el-step title="租客信息"></el-step>
      <el-step title="支付"></el-step>
    </el-steps>
    <div class="basicInfo" v-if="active===0">
      <el-form label-width="80px" :model="userInfo">
        <el-form-item label="入住时间">
          <el-date-picker
            v-model="userInfo.date"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd"
            size="large"
            style="width: 100%;"
            :picker-options="pickerOptions"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="入住天数">
          <el-input v-model="userInfo.countDay" disabled>{{dateDiff}}</el-input>
        </el-form-item>
        <el-form-item label="房屋价格">
          <el-input v-model="userInfo.price" disabled>{{userInfo.price}}</el-input>
        </el-form-item>
        <el-form-item label="入住人数">
          <el-select v-model="userInfo.liveNum" style="width: 100%;">
            <li v-for="(item, index) in userInfo.enableLive" :key="index">
              <el-option :key="index" :label="item" :value="item"></el-option>
            </li>
          </el-select>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="userInfo.phoneNum" placeholder="请输入您的手机号" clearable></el-input>
        </el-form-item>
      </el-form>
    </div>
    <div class="userInfo" v-if="active===2">
      <el-form label-width="80px" :model="userInfo">
        <el-form-item label="姓名">
          <el-input v-model="userInfo.name" placeholder="请输入您的真实姓名" clearable></el-input>
        </el-form-item>
        <el-form-item label="证件类型">
          <el-select v-model="userInfo.type" placeholder="请选择您的证件号码" style="width: 100%;">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="证件号码">
          <el-input v-model="userInfo.idCard" placeholder="请输入您的证件号码" clearable></el-input>
        </el-form-item>
      </el-form>
    </div>
    <div class="payInfo" v-if="active===4">
      应该支付：{{countPay}}
      <!-- @onConfirm="bookClick" @onCancel="" 气泡确认框的点击事件 -->
      <el-popconfirm title="确定预订么？" @onConfirm="bookClick">
        <el-button slot="reference" type="primary" style="width: 100%;margin: 200px 0;">立即预订</el-button>
      </el-popconfirm>
    </div>
    <div class="btn">
      <el-button
        class
        style="margin-top: 12px;"
        @click="prev"
        :disabled="btnStatus1"
      >上一步{{this.active}}</el-button>
      <el-button style="margin-top: 12px;" @click="next" :disabled="btnStatus2">下一步</el-button>
    </div>
  </div>
</template>

<script>
import { addOrder } from "network/order";
export default {
  name: "DetailOrder",
  data() {
    return {
      active: 0,
      btnStatus1: true,
      btnStatus2: false,
      pickerOptions: {},
      banDate: [],
      userInfo: {
        name: "",
        type: "",
        idCard: "",
        phoneNum: "",
        date: "",
        // startTime: "",
        // endTime: "",
        liveNum: "",
        address: "",
        enableLive: "",
        price: "",
        countDay: "",
        countPay: "",
        userId: "",
        homeId: ""
      },

      options: [
        {
          value: "身份证",
          label: "身份证"
        },
        {
          value: "港澳居民往来内地通行证",
          label: "港澳居民往来内地通行证"
        },
        {
          value: "台湾居民往来大陆通行证",
          label: "台湾居民往来大陆通行证"
        },
        {
          value: "外国护照",
          label: "外国护照"
        }
      ]
      // value: ""
    };
  },
  props: {
    bookInfo: {
      type: Object,
      default() {}
    }
  },
  activated() {
    // this作用域不同
    var _self = this;

    this.pickerOptions = {
      disabledDate(time) {
        if (time.getTime() < Date.now() - 8.64e7) {
          return true;
        }
        for (let item of _self.banDate) {
          if (
            time.getTime() >= new Date(item.start_time).getTime() &&
            time.getTime() <= new Date(item.end_time).getTime()
          ) {
            return true;
          }
        }
        return false;
      }
    };

    // 路由传参
    // this.userInfo.date = this.$route.params.date;
    // this.userInfo.liveNum = this.$route.params.peopleNum;
    // this.userInfo.enableLive = this.$route.params.allData.livepeople;
    // this.userInfo.address = this.$route.params.allData.address;
    // this.userInfo.price = this.$route.params.allData.price;
    // this.userInfo.userId = localStorage.getItem("userId");
    // this.userInfo.homeId = this.$route.params.allData.id;
    // this.banDate = this.$route.params.allData.timeList;
  
    
    // //localStorage
    // let allData = JSON.parse(localStorage.getItem("allData"));
    // // console.log(JSON.parse(allData));
    // console.log(localStorage.getItem("date"));

    console.log("basicInfo",this.$store.state.basicInfo);
    
    this.userInfo.date = this.$store.state.bookDate;
    this.userInfo.liveNum = this.$store.state.orderPeople;
    this.userInfo.enableLive = this.$store.state.basicInfo.livepeople;
    this.userInfo.address = this.$store.state.basicInfo.address;
    this.userInfo.price = this.$store.state.basicInfo.price;
    this.userInfo.userId = localStorage.getItem("userId");
    this.userInfo.homeId = this.$store.state.basicInfo.id;
    this.banDate = this.$store.state.basicInfo.timeList;

    console.log("123--", this.bookInfo);
  },

  methods: {
    prev() {
      this.active = this.active - 2;
      if (this.active >= 2) {
        this.btnStatus2 = false;
      }
      if (this.active <= 0) {
        this.btnStatus1 = true;
      }
    },
    next() {
      this.active = this.active + 2;
      if (this.active > 0) {
        this.btnStatus1 = false;
      }
      if (this.active >= 4) {
        this.btnStatus2 = true;
      }
    },
    bookClick() {
      // 将userInfo对象转换成Json字符串
      // let data = qs.Stringify({
      //   userInfo: JSON.stringify(this.userInfo);
      // })
      console.log("wwww", this.userInfo);
      addOrder(this.userInfo).then(res => {
        console.log(res);
        if (res.data.code == "200") {
          this.$message({
            message: "恭喜你，预订成功",
            type: "success"
          });
        } else {
          this.$message({
            message: "很抱歉，预订失败",
            type: "warning"
          });
        }
      }),
        console.log("jieguo -> ", this.userInfo);
      console.log("bookInfo : ", this.bookInfo);
    }
  },
  computed: {
    dateDiff() {
      let Date_start, Date_end, oDate1, oDate2, iDays, DateParam;
      DateParam = this.userInfo.date;
      if (DateParam || "") {
        Date_start = DateParam[0].split("-");
        Date_end = DateParam[1].split("-");
        oDate1 = new Date(Date_start[0], Date_start[1], Date_start[2]);
        oDate2 = new Date(Date_end[0], Date_end[1], Date_end[2]);
        iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24);
        this.userInfo.countDay = iDays;
        // return iDays;
      }
    },
    countPay() {
      this.userInfo.countPay =

        // this.userInfo.countDay * this.bookInfo.allData.price;  使用bookInfo的话会导致页面刷新数据丢失
        this.userInfo.countDay * this.userInfo.price;
      return "￥" + this.userInfo.countPay;
    }
  }
};
</script>

<style scoped>
.detail-order {
  padding-top: 50px;
  height: 500px;
}

.basicInfo,
.userInfo,
.payInfo {
  width: 70%;
  height: 300px;
  margin: 0 auto;
}

.date,
.pnum,
title {
  margin-top: 20px;
}
.btn {
  text-align: center;
}
</style>
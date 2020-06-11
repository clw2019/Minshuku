<template>
  <div class="detail-order" ref="aaa">
    <div class="order-title">
      <span style="color:red">￥{{detailData.price}}</span> / 晚
    </div>
    <div class="order-content">
      <el-form ref="form" :model="form" style="text-align:center">
        <el-form-item>
          <el-date-picker
            v-model="form.date"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd"
            size="large"
            :picker-options="pickerOptions"
          ></el-date-picker>
        </el-form-item>

        <el-form-item>
          <el-select v-model="form.peopleNum" style="width: 90%;">
            <li v-for="(item, index) in detailData.livepeople" :key="index">
              <el-option :key="index" :label="item" :value="item"></el-option>
            </li>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit" style="width: 90%;">立即预订</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "BookMain",
  data() {
    return {
      pickerOptions: {},
      id: "1",
      form: {
        date: "",
        peopleNum: ""
      },
      dateList: null
    };
  },
  created() {
    // this作用域不同
    var _self = this;
    // this.dateList = this.detailData.timeList;
    this.pickerOptions = {
      disabledDate(time) {
        if (time.getTime() < Date.now() - 8.64e7) {
          return true;
        }
        for (let item of _self.detailData.timeList) {
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
  },
  props: {
    detailData: {
      type: Object,
      default() {
        return {};
      }
    }
  },
  methods: {
    onSubmit() {
      console.log("eee", this.detailData);

      if (
        localStorage.getItem("username") == null ||
        localStorage.getItem("username") == ""
      ) {
        this.$message({
          message: "未登陆，请先登录",
          type: "warning"
        });
      } else if (this.form.date != "" && this.form.peopleNum != "") {
        // 改用vuex
        console.log("before ", this.form.date);
        
        this.$store.commit('updateBookDate', this.form.date)
        this.$store.commit('updateOrderPeople', this.form.peopleNum)
        this.$store.commit('updateBasicInfo', this.detailData)
        this.$router.replace({
          name: "Order",
          params: {
            date: this.form.date,
            peopleNum: this.form.peopleNum,
            allData: this.detailData
          }
        });
      } else {
        this.$message({
          message: "请选择入住日期或入住人数",
          type: "warning"
        });
      }
    }
  }
};
</script>

<style scoped>
.detail-order {
  background-color: #ffffff;
  width: 390px;
  height: 200px;
  position: relative;
  left: 10px;
}

.order-title {
  padding: 5px 0px 5px 20px;
}
</style>
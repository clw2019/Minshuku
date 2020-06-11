<template>
  <div>
    <top-bar />
    <div class="detailOrder">
      <detail-order :bookInfo="bookInfo" />
    </div>
    <insurance />
    <bottom-bar />
  </div>
</template>

<script>
import DetailOrder from "components/context/detailOrder/DetailOrder";
import TopBar from "components/context/topbar/TopBar";
import Insurance from "components/context/insurance/Insurance";
import BottomBar from "components/context/bottomBar/BottomBar";
export default {
  name: "Order",
  data() {
    return {
      bookInfo: {
        // bookDate: "",
        // peopleNum: "",
        // homeTitle: "",
        // livepeople: ""
        type: Object,
        default() {
          return {};
        }
      }
    };
  },
  components: {
    TopBar,
    BottomBar,
    Insurance,
    DetailOrder
  },
  activated() {
    console.log("参数->", this.$route.params);
    // console.log("fghj--", this.bookInfo);

    // this.bookInfor.bookDate = this.$route.params.date;
    // this.bookInfor.peopleNum = this.$route.params.peopleNum;
    // this.bookInfor.homeTitle = this.$route.params.homeTitle;
    // this.bookInfor.livepeople = this.$route.params.livepeople;
    this.bookInfo = this.$route.params;
  }
};
</script>

<style scoped>
.detailOrder {
  margin: 0 auto;
  width: 80%;

  background-color: #ffffff;
}
</style>
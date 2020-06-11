<template>
  <div class="detail">
    <top-bar />
    <pic-show :imgData1="carouselList1" :imgData2="carouselList2" />
    <div class="detail-container">
      <detail-container :detailData="detailData" />
    </div>
    <insurance />
    <bottom-bar />
  </div>
</template>


<script>
import TopBar from "components/context/topbar/TopBar";
import PicShow from "components/context/imgDisplay/PicShow";
import DetailContainer from "components/context/detailContainer/DetailContainer";
import Insurance from "components/context/insurance/Insurance";
import BottomBar from "components/context/bottomBar/BottomBar";

import { getDetail } from "network/detail";
export default {
  name: "Detail",
  data() {
    return {
      id: null,
      carouselList1: [],
      carouselList2: [],
      detailData: {},
      carouselList: []
    };
  },
  components: {
    TopBar,
    PicShow,
    DetailContainer,
    Insurance,
    BottomBar
  },
  created() {
    // kepp-alive钩子的触发顺序created-> mounted-> activated，退出时触发deactivated。当再次进入（前进或者后退）时，只触发。
    // // 1. 保存传入的id
    // this.id = this.$route.params.id;
    // // 2. 根据id请求详细数据
    // this.getDetail(this.id);
  },
 
  activated() {
    // 1. 保存传入的id
    this.id = this.$route.params.id;

    // 2. 根据id请求详细数据
    this.getDetail(this.id);
  },
 
  methods: {
    // 事件相关的方法

    // 网络请求相关的方法
    getDetail() {
      getDetail(this.id).then(res => {
        // 1. 把第一张图片传过去
        this.carouselList1 = res.data.data[0].urlList.splice(0, 1);
        // 2. 把剩余的图片传过去
        this.carouselList2 = res.data.data[0].urlList;
        // 3. 把所有数据都传过去
        this.detailData = res.data.data[0];
      });
    }
  }
};
</script>

<style scoped>
.detail {
  background-color: #f5f6fa;
  /* position: relative;
  top: 800px; */
}

.detail-container {
  margin: 0 auto;
  width: 87%;
}

</style>
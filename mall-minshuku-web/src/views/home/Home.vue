<template>
  <div id="home">
    <top-bar />
    <carousel :carousel="carousel" class="carousel" />
    <search class="search" :district="district" @home="getHomeByAddress" />
    <goods-header />
    <div class="container">
      <goods-list :goods="goods.list" />
      <!-- <div class="bottom-part">
        <el-pagination
          background
          layout="total, sizes, prev, pager, next, jumper"
          :page-size="pagination.pageSize"
          :page-sizes="[6,9,12,15,18]"
          :total="pagination.total"
          :current-page="pagination.currentPage"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        ></el-pagination>
      </div> -->
      <load-more />
      <insurance />
      <bottom-bar />
    </div>
  </div>
</template>

<script>
import Carousel from "components/common/carousel/Carousel";

import Search from "components/context/search/Search";
import TopBar from "components/context/topbar/TopBar";
import GoodsList from "components/context/goods/GoodsList";
import GoodsHeader from "components/context/goods/GoodsHeader";
import LoadMore from "components/context/loadMore/LoadMore";
import Insurance from "components/context/insurance/Insurance";
import BottomBar from "components/context/bottomBar/BottomBar";

import { getCarousel, getGoods, getDistrict } from "network/home";

export default {
  name: "Home",
  data() {
    return {
      carousel: [],
      goods: {
        page: 1,
        list: []
      },
      district: [],

      pagination: {
        currentPage: 1,
        pageSize: 6,
        total: null
      }
    };
  },
  components: {
    TopBar,
    Carousel,
    Search,
    GoodsList,
    GoodsHeader,
    Insurance,
    LoadMore,
    BottomBar,
    GoodsList
  },
  activated() {
    console.log("home is activated...");

    // 请求轮播图数据
    this.getCarousel();
    // 请求商品信息
    this.getGoods(this.pagination.currentPage, this.pagination.pageSize);
    // 请求省市县信息
    this.getDistrict();
  },
  methods: {
    /**
     * 事件相关的方法
     */

    /**
     * 网络请求相关的方法
     */
    getCarousel() {
      getCarousel().then(res => {
        this.carousel = res.data.data;
      });
    },

    getGoods(currentPage, pageSize) {
      getGoods(currentPage, pageSize).then(res => {
        this.goods.list = res.data.data.list;

        this.pagination.currentPage = res.data.data.pageNum;
        this.pagination.pageSize = res.data.data.pageSize;
        this.pagination.total = res.data.data.total;

        console.log(this.goods.list);
      });
    },
    // 分页点击方法
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.getGoods(this.pagination.currentPage, val);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.getGoods(val, this.pagination.pageSize);
    },

    getDistrict() {
      getDistrict().then(res => {
        this.district = res.data.data;
      });
    },
    getHomeByAddress(data) {
      this.goods.list = data;
    }
  }
};
</script>

<style scoped>
.container {
  margin: 0 auto;
  width: 77%;
}

.search {
  text-align: center;
  position: relative;
  top: -100px;
  z-index: 10;
}
.bottom-part {
  display: flex;
  justify-content: center;
  padding: 20px;

  padding: 16px 0px;
  background-color: #ffffff;
  border-radius: 6px;
  text-align: center;
  margin: 10px 0px 40px 0px;
  font-family: SF UI Text, Helvetica Neue, Helvetica, Ubuntu, Arial,
    X-LocaleSpecific-Sans, DroidSansFallbackFull, sans-serif;
  font-weight: 700;
  font-size: 16px;
}
</style>
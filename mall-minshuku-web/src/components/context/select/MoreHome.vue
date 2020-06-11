<template>
  <div class="more-home">
    <top-bar />

    <div class="container">
      <div class="search">
        <search :district="district" @home="getHomeByAddress" />
        <div class="detail">
          <div class="tags">
            <el-tag>厨房</el-tag>
            <el-tag type="success">独卫</el-tag>
            <el-tag type="info">标签三</el-tag>
            <el-tag type="warning">标签四</el-tag>
            <el-tag type="danger">标签五</el-tag>
          </div>
          <div class="price">
            价格
            <el-slider
              v-model="priceRange"
              range
              show-stops
              :marks="marks"
              :step="100"
              @change="priceChange"
              :max="1000"
            ></el-slider>
          </div>
        </div>
      </div>
      <goods-list :goods="goodsList" v-if="goodsList != null && goodsList != '' && goodsList !={}" />
      <no-data v-if="goodsList == null || goodsList == '' || goodsList=={}" />
      <div class="bottom-part">
        <el-pagination
          background
          layout="total, sizes, prev, pager, next, jumper"
          :page-size="pagination.pageSize"
          :page-sizes="[2,12,24,48,96]"
          :total="pagination.total"
          :hide-on-single-page="value"
          :current-page="pagination.currentPage"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        ></el-pagination>
      </div>
    </div>
    <insurance />
    <bottom-bar />
  </div>
</template>

<script>
import TopBar from "components/context/topbar/TopBar";
import Search from "components/context/search/Search";
import GoodsList from "components/context/goods/GoodsList";
import Insurance from "components/context/insurance/Insurance";
import BottomBar from "components/context/bottomBar/BottomBar";
import NoData from "components/context/noData/NoData";

import { getGoods, getDistrict } from "network/home";
export default {
  name: "MoreHome",
  data() {
    return {
      priceRange: [0, 1000],
      marks: {
        100: "100",
        200: "200",
        300: "300",
        400: "400",
        500: "500",
        600: "600",
        700: "700",
        800: "800",
        900: "900",
        1000: "1000",
      },
      value: null,
      goodsList: [],
      goodsList123: [],
      newList: [],
      district: [],
      pagination: {
        currentPage: 1,
        pageSize: 12,
        total: null
      },
      detailAddress: this.$route.params.address
    };
  },
  components: {
    TopBar,
    Search,
    GoodsList,
    Insurance,
    BottomBar,
    NoData
  },
  activated() {
    // 请求商品信息
    console.log("address --> ", this.$route.params.address);

    this.getGoods(
      this.pagination.currentPage,
      this.pagination.pageSize,
      this.$route.params.address
    );
    // 请求省市县信息
    this.getDistrict();
  },
  methods: {
    getGoods(currentPage, pageSize, address) {
      getGoods(currentPage, pageSize, address).then(res => {
        this.goodsList = res.data.data.list;
        this.goodsList123 = res.data.data.list;
        this.pagination.currentPage = res.data.data.pageNum;
        this.pagination.pageSize = res.data.data.pageSize;
        this.pagination.total = res.data.data.total;
        // 如果页数大于1则显示分页
        if (res.data.data.totalPage > 1) {
          this.value = false;
        }
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

    getHomeByAddress(res) {
      this.goodsList = res.data.data.list;
      this.goodsList123 = res.data.data.list;
      this.pagination.currentPage = res.data.data.pageNum;
      this.pagination.pageSize = res.data.data.pageSize;
      this.pagination.total = res.data.data.total;
    },

    priceChange() {
      console.log("price is changed", this.priceRange);
      // 每次调用此方法之前，先将newList清空，否则会出bug
      this.newList = [];
      // this.goodsList1 = [];
      this.goodsList = this.goodsList123;

      for (let item of this.goodsList) {
        console.log("............... ", item.price);
        if (
          item.price >= this.priceRange[0] &&
          item.price <= this.priceRange[1]
        ) {
          this.newList.push(item);
        }
      }
      this.goodsList = this.newList;
      this.pagination.total = this.goodsList.length;
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
  background-color: #ffffff;
  padding: 20px;
  text-align: center;
}
.bottom-part {
  display: flex;
  justify-content: center;
  padding: 20px;
}
.detail {
  position: flex;
  justify-content: center;
}
.tags {
  width: 400px;
  flex: 1;
}
.price {
  width: 500px;
  /* position: relative; */
  left: 500px;
  flex: 1;
}
</style>
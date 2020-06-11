<template>
  <!-- 2、原因：
     异步显示的数据先显示vuex中的初始数据，再显示请求的数据，一开始在vuex中state中的初始数据为空，报错是因为在显示初始数据的时候报错
     3、解决：避免在没有数据的时候显示解析（有数据才解析）
  有数据的时候div才存在，用v-if判断detailData.hostList是否存在-->
  <div class="detail-container" v-if="detailData.hostList">
    <div class="left-content">
      <detail-title :detailData="detailData" />

      <h3>房东</h3>
      <detail-host :detailData="detailData" />

      <h3>房源位置</h3>
      <map-view />

      <h3>评价</h3>
      <detail-comment :detailData="detailData" />
    </div>
    <div class="right-content">
      <book-main :detailData="detailData" />
    </div>
  </div>
</template>

<script>
import MapView from "components/common/mapView/MapView";
import DetailTitle from "./detailTitle/DetailTitle";
import DetailHost from "./detailHost/DetailHost";
import DetailComment from "./detailComment/DetailComment";
import BookMain from "./bookMain/BookMain";
export default {
  name: "DetailContainer",
  props: {
    detailData: {
      type: Object,
      default() {
        return {};
      }
    }
  },
  components: {
    MapView,
    DetailTitle,
    DetailHost,
    DetailComment,
    BookMain
  }
};
</script>

<style scoped>
.detail-container {
  padding: 16px;
  display: flex;
}

.left-content h2,
h3 {
  font-size: 20px;
  color: #252626;
  margin: 40px 0px 12px;
}

/* .left-content,
.right-content {
  width: 718px;
  flex: 1;
} */

.left-content {
  width: 63%;
}

.right-content {
  width: 37%;
  height: 460px;
}
</style>
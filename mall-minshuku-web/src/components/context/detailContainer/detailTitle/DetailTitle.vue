<template>
  <div class="detail-title">
    <h2>{{detailData.title}}</h2>
    <div class="detail-room">
      <i class="el-icon-office-building">{{suite}}{{detailData.area}}㎡</i>
      <i
        class="el-icon-table-lamp"
      >{{detailData.bedroom}}室{{detailData.livingroom}}厅{{detailData.toilet}}卫{{detailData.kitchen}}厨</i>
      <i class="el-icon-moon">宜住{{detailData.livepeople}}人</i>
    </div>
  </div>
</template>

<script>
export default {
  name: "DetailTitle",
  props: {
    detailData: {
      type: Object,
      default() {
        return {};
      }
    }
  },
  computed: {
    suite() {
      if (this.detailData.suite == 1) {
        return "整套";
      } else {
        return "单间";
      }
    }
  }
};
</script>

<style scoped>
.detail-title h2 {
  font-size: 20px;
  color: #252626;
  margin: 40px 0px 12px;
}

.detail-room {
  padding: 20px 32px;
  background-color: #ffffff;
}

.detail-room {
  display: flex;
}

.detail-room i {
  flex: 1;
}
</style>
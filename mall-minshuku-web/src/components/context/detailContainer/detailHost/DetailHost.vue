<template>
  <div class="detail-host">
    <div class="host-info">
      <el-avatar shape="square" :size="56" fit="fill" :src="detailData.hostList[0].icon"></el-avatar>
      <span>{{detailData.hostList[0].nick}}</span>
    </div>
    <div class="host-desc">{{detailData.describe}}</div>
  </div>
</template>

<script>
export default {
  name: "DetailHost",
  props: {
    detailData: {
      type: Object,
      default() {
        return {};
      }
    }
  }
};
</script>

<style scoped>
.detail-host {
  padding: 20px 32px;
  background-color: #ffffff;
}

.host-desc {
  margin-top: 20px;
}
</style>
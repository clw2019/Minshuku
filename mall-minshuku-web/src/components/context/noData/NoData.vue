<template>
  <div class="no-data">
    <img src="~assets/img/context/nodata.png" alt />
    <p>暂无数据</p>
  </div>
</template>

<script>
export default {
  name: "NoData"
};
</script>

<style scoped>
.no-data {
  height: 450px;
  background-color: #ffffff;
}
.no-data img {
  width: 250px;
  height: 250px;
  position: relative;
  left: 37.5%;
}
.no-data p {
  font-family: "楷体";
  font-size: 16px;
  font-weight: bold;
  text-align: center;
}
</style>
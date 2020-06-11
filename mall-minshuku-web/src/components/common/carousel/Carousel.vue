<template>
  <el-carousel :interval="5000" arrow="always" :type="type">
    <el-carousel-item v-for="(item, index) in carousel" :key="index">
      <a :href="item.link"><img :src="item.imgurl" alt /></a>
    </el-carousel-item>
  </el-carousel>
</template>

<script>
export default {
  name: "Carousel",
  data() {
    return {};
  },
  created() {
    console.log(".......", this.carousel);
  },
  props: {
    carousel: {
      type: Array,
      default() {}
    },
    type: ""
  }
};
</script>

<style scoped>
img {
  width: 100%;
  height: 100%;
}
</style>
<template>
  <div class="home-list">
    <home-item v-for="(item, index) in myHome" :home-item="item" :key="index" />
  </div>
</template>

<script>
import HomeItem from "./HomeItem"
export default {
  name: "HomeList",
  props: {
    myHome: {
      type: Array,
      default() {
        return [];
      }
    }
  },
  components: {
    HomeItem
  }
};
</script>

<style scoped>
.home-list {
  display: flex;
  flex-wrap: wrap; /**默认不包裹 nowrap往一行塞*/
  /* justify-content: space-around; *均等分 */
  justify-content: flex-start;
}
</style>
<template>
  <div class="collect-list">
    <collect-item v-for="(item, index) in collect" :collect-item="item" :key="index" />
  </div>
</template>

<script>
import CollectItem from "./CollectItem";
export default {
  name: "CollectList",
  props: {
    collect: {
      type: Array,
      default() {
        return [];
      }
    }
  },
  components: {
    CollectItem
  }
};
</script>

<style scoped>
.collect-list {
  display: flex;
  flex-wrap: wrap; /**默认不包裹 nowrap往一行塞*/
  /* justify-content: space-around; *均等分 */
  justify-content: flex-start;
}
</style>
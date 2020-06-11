<template>
  <div class="order-list">
    <order-item v-for="(item, index) in data" :order-item="item" :key="index" />
  </div>
</template>

<script>
import OrderItem from "./OrderItem";
export default {
  name: "OrderList",
  props: {
    data: {
      type: Array,
      default() {
        return [];
      }
    }
  },
  components: {
    OrderItem
  }
};
</script>

<style scoped>
.order-list {
  display: flex;
  flex-wrap: wrap; /**默认不包裹 nowrap往一行塞*/
  /* justify-content: space-around; *均等分 */
  justify-content: center;
}
</style>
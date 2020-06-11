<template>
  <div class="bottom">
    <div v-for="(item, index) in popData" :key="index" class="bottom-item">
      <div v-for="(item, index) in item" :key="index">
        <a :href="item.href">{{item.name}}</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "BottomBar",
  data() {
    return {
      popData: {
        popCity: [
          { name: "北京的民宿", href: "#" },
          { name: "上海的民宿", href: "#" },
          { name: "南京的民宿", href: "#" },
          { name: "杭州的民宿", href: "#" },
          { name: "厦门的民宿", href: "#" },
          { name: "武汉的民宿", href: "#" },
          { name: "长沙的民宿", href: "#" },
          { name: "广州的民宿", href: "#" },
          { name: "深圳的民宿", href: "#" },
          { name: "三亚的民宿", href: "#" }
        ],
        popHome: [
          { name: "有厨具的民宿", href: "#" },
          { name: "有洗衣机的民宿", href: "#" },
          { name: "有今夜特价的民宿", href: "#" },
          { name: "有限时折扣的民宿", href: "#" },
          { name: "适合情侣的民宿", href: "#" },
          { name: "视野开阔的民宿", href: "#" },
          { name: "别墅", href: "#" },
          { name: "复式loft", href: "#" },
          { name: "度假民宿", href: "#" }
        ]
      }
    };
  }
};
</script>

<style scoped>
.bottom-item {
  display: flex;
  flex-wrap: wrap; /**默认不包裹 nowrap往一行塞*/
  justify-content: space-around; /**均等分 */
  padding: 0 0 20px;
}
</style>
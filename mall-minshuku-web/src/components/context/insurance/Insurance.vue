<template>
  <div class="insurance">
    <div v-for="(item, index) in insurance" :key="index" class="insurance-item">
      <a :href="item.href">
        <img :src="item.img" alt />
        <h5>{{item.title}}</h5>
        <p>{{item.desc}}</p>
      </a>
    </div>
  </div>
</template>

<script>
export default {
  name: "Insurance",
  activated() {},
  data() {
    return {
      insurance: [
        {
          href: "https://minsu.meituan.com/help/trust/#ensure",
          img: require("assets/img/context/ensure.png"),
          title: "放心入住",
          desc: "千万保障先行赔付"
        },
        {
          href: "https://minsu.meituan.com/help/trust/#service",
          img: require("assets/img/context/service.png"),
          title: "贴心服务",
          desc: "7*24小时服务"
        },
        {
          href: "https://minsu.meituan.com/help/trust/#verify",
          img: require("assets/img/context/verify.png"),
          title: "信用认证",
          desc: "实名认证更放心"
        },
        {
          href: "https://minsu.meituan.com/help/trust/#insurance",
          img: require("assets/img/context/insurance.png"),
          title: "合作无忧",
          desc: "房东保险"
        }
      ]
    };
  }
};
</script>

<style scoped>
.insurance {
  display: flex;
  flex-wrap: wrap; /**默认不包裹 nowrap往一行塞*/
  justify-content: space-around; /**均等分 */
  padding: 0px 0px 40px;
  text-align: center;
}

.insurance img {
  width: 34px;
  height: 32px;
}

.insurance h5 {
  font-size: 18px;
  margin: 8px 0px;
}

.insurance p {
  font-size: 14px;
  color: #999;
}
</style>
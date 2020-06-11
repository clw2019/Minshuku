<template>
  <div class="load-more">
    <!-- <a href="#">查看更多民宿</a> -->
    <a type="text" @click="jumpTo">查看更多民宿</a>
  </div>
</template>

<script>
export default {
  name: "LoadMore",
  methods: {
    jumpTo() {
      this.$router.push("/morehome")
    }
  }
};
</script>

<style scoped>
.load-more {
  /* width: 1150px; */
  padding: 16px 0px;
  background-color: #ffffff;
  border-radius: 6px;
  text-align: center;
  margin: 10px 0px 40px 0px;
  font-family: SF UI Text, Helvetica Neue, Helvetica, Ubuntu, Arial,
    X-LocaleSpecific-Sans, DroidSansFallbackFull, sans-serif;
  font-weight: 700;
  font-size: 16px;
}
</style>
<template>
  <div class="nav-bar">
    <div class="left">
      <slot name="left"></slot>
    </div>
    <div class="center">
      <slot name="center"></slot>
    </div>
    <div class="right">
      <slot name="right"></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: "NavBar"
};
</script>

<style scoped>
.nav-bar {
  display: flex;
  height: 70px;
  line-height: 70px;
  text-align: center;
}

.left,
.right {
  width: 30%;

  text-align: center;
  padding: 0 16px;
  margin: 0px 170px;
  color: #585a5a;
}

.center {
  flex: 1;
}
</style>
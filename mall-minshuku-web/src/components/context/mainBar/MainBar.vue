<template>
  <div class="main-bar">
    <div class="top">
      <top-bar />
    </div>
    <div class="center">
      <slot name="center"></slot>
    </div>
    <div class="bottom">
      <insurance />
      <bottom-bar />
    </div>
  </div>
</template>

<script>
import TopBar from "components/context/topbar/TopBar";
import Insurance from "components/context/insurance/Insurance";
import BottomBar from "components/context/bottomBar/BottomBar";
export default {
  name: "MainBar",
  components: {
    TopBar,
    Insurance,
    BottomBar
  }
};
</script>

<style scoped>

</style>
<template>
  <div class="center">
    <top-bar />
    <div class="container">
      <left-menu class="left-menu"></left-menu>
      <insurance class="ins" />
      <bottom-bar />
    </div>
  </div>
</template>

<script>
import TopBar from "components/context/topbar/TopBar";
import LeftMenu from "./LeftMenu";
import Insurance from "components/context/insurance/Insurance";
import BottomBar from "components/context/bottomBar/BottomBar";

export default {
  name: "Center",
  data() {
    return {};
  },
  methods: {},
  components: {
    LeftMenu,
    TopBar,
    Insurance,
    BottomBar
  }
};
</script>

<style scoped>
.container {
  margin: 0 auto;
  width: 77%;
}

.ins {
  margin-top: 50px;
}
/* .left-menu {
  margin: 0px 0px 200px 0px;
} */
</style>

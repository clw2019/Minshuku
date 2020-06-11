<template>
  <div class="home">
    <!-- <nav-bar class="top-nav">
      <div slot="left">
        <img src="@/assets/img/logo.png" alt />
        <span>Minshuku</span>
      </div>
      <div slot="right">
        <span>欢迎您，<span style="color: red">{{getUsername}}</span></span>
      </div>
    </nav-bar> -->
    <top-nav/>
    <left-menu />

  </div>
</template>

<script>
import NavBar from "@/components/common/navbar/NavBar";
import TopNav from "@/components/context/topnav/TopNav";
import LeftMenu from "@/components/context/leftmenu/LeftMenu";

export default {
  name: "Home",
  computed: {
    getUsername() {
      return localStorage.getItem("username")
    }
  },
  components: {
    // NavBar,
    TopNav,
    LeftMenu
  }
};
</script>

<style scoped>
.top-nav {
  height: 60px;
  background: black;
}

.left img {
  position: relative;
  top: 9px;
  left: -10px;
}

.left,
.right,
span {
  color: white;
}
</style>

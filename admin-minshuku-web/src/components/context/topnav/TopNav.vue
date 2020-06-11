<template>
  <div class="top-nav">
    <nav-bar>
      <div slot="left">
        <img src="@/assets/img/logo.png" alt />
        <span>Minshuku</span>
      </div>
      <div slot="right" :v-show="isShow">
        <span>
          欢迎您，
          <span style="color: red">{{getUsername}}</span>
          <span @click="lgout">
            <img src="~@/assets/img/lg-out.png" alt class="lg-out" />登出
          </span>
        </span>
      </div>
    </nav-bar>
  </div>
</template>

<script>
import NavBar from "@/components/common/navbar/NavBar";
export default {
  name: "TopNav",
  data() {
    return {
      isShow: false
    };
  },
  methods: {
    showRight() {
      if (localStorage.getItem("username")) {
        return (this.isShow = true);
      }
    },
    lgout() {
      console.log("lgout is click....");
      this.$router.replace("/")
    }
  },
  computed: {
    getUsername() {
      return localStorage.getItem("username");
    }
  },
  components: {
    NavBar
  },
  activated() {
    this.showRight();
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

.lg-out {
  width: 20px;
  height: 20px;
  position: relative;
  left: 10px;
  top: 5px;
  margin-right: 15px;
}
</style>
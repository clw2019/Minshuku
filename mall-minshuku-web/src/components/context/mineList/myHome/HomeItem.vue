<template>
  <div class="home-item" @click="homeItemClick">
    <div class="home-item-container">
      <img :src="homeItem.imgList[0].imgurl" alt />
      <div class="collect-info">
        <p>{{homeItem.title}}</p>
        <p>{{suite}} {{homeItem.bedroom}} 居室 可住 {{homeItem.livepeople}} 人</p>
        <span class="price">￥{{homeItem.price}}</span>
      </div>
      <!-- <div class="collect1" @click.stop="homeClick">
        <span :class="{white: !isShow, red: isShow}">{{collectNum}}</span>
      </div>-->
      <div class="collect1">
        <span :class="{white: true}">{{collectNum}}</span>
      </div>

      <div class="btn">
        <el-button size="mini" type="info" plain @click.stop="editReviewClick">回复</el-button>
        <el-button size="mini" type="info" plain @click.stop="editHomeClick">编辑</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { collect, updateCollection } from "network/collect";
export default {
  name: "HomeItem",
  data() {
    return {
      // 判断点赞List存的userId是否包含当前用户
      isShow: true,
      collectNum: this.homeItem.collection
    };
  },
  props: {
    homeItem: {
      type: Object,
      default() {
        return {};
      }
    }
  },
  methods: {
    homeItemClick() {
      this.$router.push("/detail/" + this.homeItem.id); //动态路由
    },
    homeClick() {
      console.log("收藏被点击了", this.homeItem.id);
      console.log("用户Id：", localStorage.getItem("userId"));

      // 我的房源这里不需要收藏方法
      // if (localStorage.getItem("userId")) {
      //   collect(this.homeItem.id, localStorage.getItem("userId")).then(
      //     res => {
      //       console.log("collect", res);
      //       if (res.data.code == 200) {
      //         console.log("this.collectNum += 1被执行了..");

      //         // isShow = true 红色，已收藏
      //         if (this.isShow) {
      //           this.collectNum--;
      //           // 跟新数据库收藏数
      //           updateCollection(this.homeItem.id, -1).then(res => {});
      //         } else {
      //           this.collectNum++;
      //           // 跟新数据库收藏数
      //           updateCollection(this.homeItem.id, 1).then(res => {});
      //         }
      //         this.isShow = !this.isShow;
      //         console.log("collectNum....", this.collectNum);
      //       }
      //     }
      //   );
      // }
    },
    editHomeClick() {
      console.log("编辑按钮被点击了..", this.homeItem);
      this.$store.commit("updateHomeItem", this.homeItem);
      let id = this.homeItem.id
      this.$router.push("/edithome/" + id);
      //   this.$router.replace({
      //     name: "Publish",
      //     params: {
      //       homeItem: this.homeItem
      //     }
      //   });
    },
    editReviewClick() {
      console.log("reviewBtn is click...", this);
      let id = this.homeItem.id
      this.$router.push("/editreview/" + id);
    }
  },
  computed: {
    suite() {
      if (this.homeItem.suite == 1) {
        return "整套";
      } else {
        return "单间";
      }
      this.$router.push("/detail/" + this.homeItem.id); //动态路由
    }
  }
};
</script>

<style scoped>
.home-item {
  /* width: 389px;
  height: 360px;
  padding: 10px 8px; */
  width: 309px;
  height: 270px;
  padding: 10px 8px;
}

.home-item-container {
  /* width: 372px;
  height: 339px;
  padding: 8px 8px 12px;
  background-color: #ffffff;
  border-radius: 6px; */
  width: 292px;
  height: 259px;
  padding: 8px 8px 12px;
  background-color: #ffffff;
  border-radius: 6px;
}

.home-item img {
  /* border-radius: 6px;
  width: 356px;
  height: 200px; */
  border-radius: 6px;
  width: 276px;
  height: 150px;
}

.collect-info {
  font-family: "楷体";
  font-size: 16px;
  margin: 12px;
  /* text-align: center; */
}

.collect-info p {
  overflow: hidden; /**溢出隐藏 */
  text-overflow: ellipsis; /**ellipsis : 　当对象内文本溢出时显示省略标记（...） */
  white-space: nowrap; /**规定段落中的文本不进行换行： */
  margin-bottom: 10px;
}

.collect-info .price {
  color: var(--color-high-text);
}

.collect1 {
  width: 45px;
  border-radius: 4px;
  background-color: #ffffff;
  position: relative;
  text-align: center;
  top: -248px;
  left: 225px;
  /* color: red; */
  z-index: 10;
}

.white {
  margin-left: 10px;
}
.white::before {
  content: "";
  position: absolute;
  left: 0px;
  top: 0px;
  width: 16px;
  height: 16px;
  background: url("~assets/img/common/collect_white.png") 0 0/16px 16px;
  /* background: url("~assets/img/common/collect_red.png") 0 0/16px 16px; */
}

.red {
  margin-left: 10px;
}
.red::before {
  content: "";
  position: absolute;
  left: 0px;
  top: 0px;
  width: 16px;
  height: 16px;
  /* background: url("~assets/img/common/collect_white.png") 0 0/16px 16px; */
  background: url("~assets/img/common/collect_red.png") 0 0/16px 16px;
}

.btn {
  position: relative;
  top: -55px;
  left: 155px;
}
</style>
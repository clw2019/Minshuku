<template>
  <div class="goods-item" @click="goodsItemClick">
    <div class="goods-item-container">
      <img :src="goodsItem.urlList[0].imgurl" alt />
      <div class="goods-info">
        <p>{{goodsItem.title}}</p>
        <p>{{suite}} {{goodsItem.bedroom}} 居室 可住 {{goodsItem.livepeople}} 人</p>
        <span class="price">￥{{goodsItem.price}}</span>
        <br />
      </div>
      <div class="collect1" @click.stop="collectClick">
        <span :class="{white: !isShow, red: isShow}">{{collectNum}}</span>
      </div>
    </div>
  </div>
</template>

<script>
import { collect, updateCollection } from "network/collect";
export default {
  name: "GoodsListItem",
  data() {
    return {
      // 判断点赞List存的userId是否包含当前用户
      isShow: this.goodsItem.collectList.includes(
        parseInt(localStorage.getItem("userId"))
      ),
      collectNum: this.goodsItem.collection
    };
  },
  props: {
    goodsItem: {
      type: Object,
      default() {
        return {};
      }
    }
  },
  methods: {
    goodsItemClick() {
      this.$router.push("/detail/" + this.goodsItem.id); //动态路由
    },

    collectClick() {
      console.log("收藏被点击了", this.goodsItem.id);
      console.log("用户Id：", localStorage.getItem("userId"));

      if (localStorage.getItem("userId")) {
        collect(this.goodsItem.id, localStorage.getItem("userId")).then(
          res => {
            console.log("collect", res);
            if (res.data.code == 200) {
              console.log("this.collectNum += 1被执行了..");

              // isShow = true 红色，已收藏
              if (this.isShow) {
                this.collectNum--;
                // 跟新数据库收藏数
                updateCollection(this.goodsItem.id, -1).then(res => {});
                this.$message({
                  message: "取消收藏",
                  type: "success"
                });
              } else {
                this.collectNum++;
                // 跟新数据库收藏数
                updateCollection(this.goodsItem.id, 1).then(res => {});
                this.$message({
                  message: "收藏成功",
                  type: "success"
                });
              }
              this.isShow = !this.isShow;
              console.log("collectNum....", this.collectNum);
            }
          }
        );
      } else {
        this.$message({
          message: "未登陆，请先登录",
          type: "warning"
        });
      }
    }
  },
  computed: {
    suite() {
      if (this.goodsItem.suite == 1) {
        return "整套";
      } else {
        return "单间";
      }
      this.$router.push("/detail/" + this.goodsItem.id); //动态路由
    }
  }
};
</script>

<style>
/* .goods-item {
  padding-bottom: 40px;
  position: relative;
  width: 372px;
  height: 340px;
  text-align: center;
  background-color: #ffffff;
  border-radius: 6px;
} */

.goods-item {
  /* position: relative; */
  width: 389px;
  height: 360px;
  padding: 10px 8px;
}

.goods-item-container {
  /* position: absolute; */
  width: 372px;
  height: 339px;
  /* margin: 15px; */
  padding: 8px 8px 12px;
  background-color: #ffffff;
  border-radius: 6px;
}

.goods-item img {
  border-radius: 6px;
  width: 356px;
  height: 200px;
}

.goods-info {
  font-family: "楷体";
  font-size: 18px;
  margin: 12px;
  text-align: center;
}

.goods-info p {
  overflow: hidden; /**溢出隐藏 */
  text-overflow: ellipsis; /**ellipsis : 　当对象内文本溢出时显示省略标记（...） */
  white-space: nowrap; /**规定段落中的文本不进行换行： */
  margin-bottom: 12px;
}

.goods-info .price {
  color: var(--color-high-text);
  /* margin-right: 20px; */
}

.collect1 {
  width: 45px;
  border-radius: 4px;
  background-color: #ffffff;
  position: relative;
  text-align: center;
  top: -307px;
  left: 300px;
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

.goods-item-container img {
  position: relative;
}

/* .goods-item-container .img::before {
  content: "";
  position: absolute;
  left: -15px;
  top: -1px;
  width: 14px;
  height: 14px;
  background: url("~assets/img/common/collect.svg") 0 0/14px 14px;
} */
</style>
<template>
  <div class="pic-show">
    <div class="left">
      <div v-for="(item,index) in imgData1" :key="index" class="left-item">
        <img
          v-if="index == 0"
          v-preview="item.imgurl"
          :src="item.imgurl"
          :alt="item.title"
          preview-title-enable="true"
          preview-nav-enable="true"
        />
      </div>
    </div>
    <div class="right">
      <div v-for="(item,index) in imgData2" :key="index">
        <div class="right-item">
          <img
            v-if="index < 4"
            v-preview="item.imgurl"
            :src="item.imgurl"
            :alt="item.title"
            preview-title-enable="true"
            preview-nav-enable="true"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PicShow",
  props: {
    imgData1: {
      type: Array,
      default() {
        return [];
      }
    },
    imgData2: {
      type: Array,
      default() {
        return [];
      }
    }
  }
};
</script>

<style scoped>
.pic-show {
  height: 404px;
}

.left {
  width: 50%; /**768px */
  height: 404px;
  /* padding: 4px 4px 4px 0px; */
}

.left-item {
  /* border-radius: 6px; */
  width: 760px;
  height: 400px;
  margin: 2px 0px;

  overflow: hidden;
}

.left-item img {
  width: 760px;
  height: 400px;
}

.right {
  width: 50%;
  height: 404px;
  display: flex;
  flex-wrap: wrap; /**默认不包裹 nowrap往一行塞*/
  justify-content: center; /**均等分 */
}

.right-item {
  flex-grow: 1;
  width: 374px;
  height: 198px;
  margin: 2px;
  overflow: hidden;
}

.right-item img {
  width: 374px;
  height: 198px;
}

.pic-show img {
  transition: all 0.45s;
}

.pic-show img:hover {
  transform: scale(1.1);
}
</style>
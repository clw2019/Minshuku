<template>
  <div class="search">
    <el-cascader
      v-model="address"
      :options="district"
      @change="handleChange"
      :props="defaultParams"
      slot="prepend"
    ></el-cascader>
    <el-input slot="prepend" v-model="detailAddress" style="width: 500px" placeholder="详细地址"></el-input>
    <el-button slot="append" icon="el-icon-search" @click="selectHome"></el-button>
  </div>
</template>

<script>
import { getGoods } from "network/home";
export default {
  name: "Search",
  data() {
    return {
      pickerOpions: {
        disabledDate(time) {
          return time.getTime() < Date.now() - 8.64e7;
        }
      },
      address: [],
      detailAddress: "",
      defaultParams: {
        label: "districtName",
        value: "districtName",
        children: "districtList",
        expandTrigger: "hover"
      },
      selectHomeList: [],
      resData: {}
    };
  },
  props: {
    district: {
      type: Array,
      default() {
        return [];
      }
    }
  },
  methods: {
    handleChange(value) {
      
    },
    selectHome() {
   
      let address =
        this.address[0] +
        this.address[1] +
        this.address[2] +
        this.detailAddress;
     
      if (this.$route.path == "/Home") {
        console.log("if");
        
        this.$router.push({
          name: "MoreHome",
          params: {
            address: address
          }
        });
      } else {
        console.log("else");
        
        getGoods(1, 12, address).then(res => {
          console.log(res);
          this.resData = res;
          this.$emit("home", this.resData);
        });
      }

      // console.log("当前裸游", this.$route.path);

      // selectHome(address).then(res => {
      //   console.log("select ", res);
      //   this.selectHomeList = res.data.data.list;
      //   this.$emit('home', this.selectHomeList)
      // });
    }
  }
};
</script>

<style scoped>
</style>
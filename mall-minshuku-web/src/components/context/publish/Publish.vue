<template>
  <div>
    <top-bar />
    <div class="container">
      <el-steps :active="active" finish-status="success" simple>
        <el-step title="步骤 1"></el-step>
        <el-step title="步骤 2"></el-step>
        <el-step title="步骤 3"></el-step>
      </el-steps>
      <div class="homeInfo" v-if="active===0">
        <el-form label-width="80px" :model="homeInfo" :rules="homeInfoRules">
          <el-form-item label="房屋标题" prop="title">
            <el-input v-model="homeInfo.title" placeholder="请输入房屋标题" clearable></el-input>
          </el-form-item>
          <el-form-item label="房屋描述">
            <el-input type="textarea" v-model="homeInfo.describe" placeholder="请输入房屋描述" clearable></el-input>
          </el-form-item>
          <el-form-item label="房屋价格" prop="price">
            <el-input v-model="homeInfo.price" placeholder="请输入价格￥/晚" clearable></el-input>
          </el-form-item>
          <el-form-item label="房屋面积">
            <el-input v-model="homeInfo.area" placeholder="请输入房屋面积" clearable></el-input>
          </el-form-item>
          <el-form-item label="可住人数">
            <el-input v-model="homeInfo.livepeople" placeholder="请输入可住人数" clearable></el-input>
          </el-form-item>
          <el-form-item label="房屋地址">
            <el-cascader
              v-model="districtValue"
              :options="district"
              @change="handleChange"
              :props="defaultParams"
              style="width:25%"
            ></el-cascader>
            <el-input
              v-model="homeInfo.detailAddress"
              style="width:75%"
              placeholder="请输入详细地址"
              clearable
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="homeDetailInfo" v-if="active===1">
        <el-form label-width="80px" :model="homeInfo">
          <el-form-item label="房屋类型">
            <el-select v-model="homeInfo.suite" placeholder="请选择您的房屋类型" style="width: 100%;">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="卧室数量">
            <el-input v-model="homeInfo.bedroom" placeholder="请输入" clearable></el-input>
          </el-form-item>
          <el-form-item label="客厅数量">
            <el-input v-model="homeInfo.livingroom" placeholder="请输入" clearable></el-input>
          </el-form-item>
          <el-form-item label="是否厨房">
            <el-radio v-model="homeInfo.kitchen" label="1">是</el-radio>
            <el-radio v-model="homeInfo.kitchen" label="0">否</el-radio>
          </el-form-item>
          <el-form-item label="是否独卫">
            <el-radio v-model="homeInfo.toilet" label="1">是</el-radio>
            <el-radio v-model="homeInfo.toilet" label="0">否</el-radio>
          </el-form-item>
        </el-form>
      </div>
      <div class="uploadInfo" v-if="active===2">
        <div class="block">
          <!-- E:/学习/Vue/后端/minshuku/src/main/resources/static/upload/img/94f2d431144044fabbbbbc43d40c1309.jpg -->
          <span class="demonstration">默认</span>
          <!-- <el-image src="http://localhost:8000/minshuku/img/bb7c62dbc57e4e198b0df088db30a3b7.jpg"></el-image> -->
        </div>
        <el-upload
          action
          list-type="picture-card"
          :on-preview="handlePictureCardPreview"
          :on-remove="handleRemove"
          :auto-upload="false"
          :http-request="uploadFile"
          :multiple="true"
          ref="upload"
        >
          <i class="el-icon-plus"></i>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt />
          </el-dialog>
      
          <!-- <i class="el-icon-upload"></i> -->
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" :src="dialogImageUrl" alt />
        </el-dialog>
        <el-button @click="subPicForm">发布</el-button>
      </div>
      <div class="success" v-if="active===3">恭喜您发布成功</div>
      <div class="btn" v-if="this.active  < 3">
        <el-button style="margin-top: 12px;" @click="prev" :disabled="btnStatus1">上一步{{this.active}}</el-button>
        <el-button style="margin-top: 12px;" @click="next" :disabled="btnStatus2">下一步</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import TopBar from "components/context/topbar/TopBar";

import { getDistrict, publishHome } from "network/home";
import { upload } from "network/upload";
export default {
  name: "Publish",
  data() {
    return {
      active: 0,
      btnStatus1: true,
      btnStatus2: false,
      homeInfo: {
        homeId: "",
        hostId: localStorage.getItem("userId"),
        title: "",
        describe: "",
        price: "",
        area: "",
        livepeople: "",
        address: "",
        detailAddress: "",
        suite: "",
        bedroom: "",
        livingroom: "",
        kitchen: "1",
        toilet: "1",
        img: ""
      },
      options: [
        {
          value: "0",
          label: "单间"
        },
        {
          value: "1",
          label: "整套"
        }
      ],
      districtValue: [],
      district: [],
      defaultParams: {
        label: "districtName",
        value: "districtName",
        children: "districtList",
        expandTrigger: "hover"
      },

      dialogImageUrl: "",
      dialogVisible: false,

      // 文件上传
      formDate: "",
      homeInfoRules: {
        title: [
          { required: true, message: "请输入房屋标题", trigger: "blur" },
          { min: 3, max: 10, message: "长度在 3 到 10 个字符", trigger: "blur" }
        ]
        // price: [
        //   { required: true, message: "请输入房屋价格", trigger: "blur" },
        //   { type: 'String', message: "必须为数字", trigger: "blur" }
        // ],
      }
    };
  },
  activated() {
    // 请求地址
    this.getDistrict();
  },

  methods: {
    getDistrict() {
      getDistrict().then(res => {
        this.district = res.data.data;
      });
    },

    handleChange(value) {
      console.log(value);
      console.log(value[0]);
      console.log(this.districtValue);
      this.homeInfo.address = value[0] + value[1] + value[2];
    },

    // 文件上传点击事件
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    prev() {
      this.active = this.active - 1;
      if (this.active < 2) {
        this.btnStatus2 = false;
      }
      if (this.active <= 0) {
        this.btnStatus1 = true;
      }
    },
    next() {
      this.active = this.active + 1;
      if (this.active > 0) {
        this.btnStatus1 = false;
      }
      if (this.active >= 2) {
        this.btnStatus2 = true;
      }
    },

    // 文件上传相关
    uploadFile(file) {
      console.log("1111");
      this.formDate.append("file", file.file);
    },
    subPicForm() {
      console.log("2222");
      this.formDate = new FormData(); //formdata格式
      console.log("3333");
      this.$refs.upload.submit();
      console.log("4444");
      upload(this.formDate).then(res => {
        console.log("5555");
        console.log(res);
        if (res.data.code == 200) {
          this.homeInfo.img = res.data.data;
          // 上传成功后，清除文件列表
          this.$refs.upload.clearFiles();

          publishHome(this.homeInfo).then(res => {
            console.log("发布房源publish", res);
            if (res.data.code == 200) {
              this.active += 1;
              this.$message({
                message: "发布成功",
                type: "success"
              });
            } else {
              this.$message({
                message: "发布失败",
                type: "warning"
              });
            }
          });
        }
      });
    }
  },
  components: {
    TopBar
  }
};
</script>

<style scoped>
.container {
  width: 77%;
  margin: 0 auto;
  background-color: #ffffff;
  padding: 20px 80px;
}

.homeInfo,
.homeDetailInfo,
.uploadInfo,
.success {
  padding-top: 20px;
  height: 400px;
}

.btn {
  text-align: center;
}
</style>
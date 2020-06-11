<template>
  <div class="personal">
    <div>
      <h1>个人资料</h1>
      <div class="person-container">
        <p>
          账号：
          <el-input v-model="user.username" disabled style="width:200px"></el-input>
        </p>
        <p>
          昵称：
          <el-input v-model="user.nick" style="width:200px"></el-input>
        </p>
        <p>
          头像：
          <el-upload
            class="avatar-uploader"
            action
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            :http-request="uploadFile"
            ref="uploadIcon"
          >
            <el-avatar :size="50" :src="user.icon"></el-avatar>
          </el-upload>
        </p>
        <p>
          性别：
          <el-radio-group v-model="user.sex">
            <el-radio-button label="男"></el-radio-button>
            <el-radio-button label="女"></el-radio-button>
          </el-radio-group>
        </p>
        <p>
          电话：
          <el-input v-model="user.phonenumber" style="width:200px"></el-input>
        </p>

        <p>
          <el-button type="primary" @click="updateUser">修改</el-button>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import { selectUser, updateUser } from "network/user";
import { upload } from "network/upload";
export default {
  name: "MyPersonal",
  data() {
    return {
      imageUrl: "",
      formData: "",
      user: {
        id: localStorage.getItem("userId"),
        username: null,
        nick: null,
        sex: "男",
        phonenumber: null,
        icon: ""
      }
    };
  },
  activated() {
    this.selectUser(localStorage.getItem("userId"));
  },
  methods: {
    // 通过userId查找用户信息
    selectUser(userId) {
      selectUser(userId).then(res => {
        this.user.username = res.data.data.username;
        this.user.nick = res.data.data.nick;
        this.user.sex = res.data.data.sex;
        this.user.phonenumber = res.data.data.phonenumber;
        this.user.icon = res.data.data.icon;
      });
    },
    // 通过userId修改用户信息
    // updateUser() {
    //   console.log(this.user);
    //   updateUser(this.user).then(res => {
    //     if (res.data.code == 200) {
    //       localStorage.setItem("username", this.user.nick);
    //       location.reload();
    //       this.$message({
    //         message: "修改成功",
    //         type: "success"
    //       });
    //     }
    //   });
    // },

    // 上传
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    // 文件上传相关
    uploadFile(file) {
      console.log("1111", this.formData);
      this.formData = new FormData();
      this.formData.append("file", file.file);
      upload(this.formData).then(res => {
        if (res.data.code == 200) {
          this.user.icon = res.data.data;
        } else {
          console.log("upload failed");
          
        }
      });
      // this.formData.append("file", file.file);
      console.log("2222");
    },
    updateUser() {
      console.log("3333", this);
      // this.formData = new FormData(); //formdata格式
      console.log("66666666");

      // this.$refs.uploadIcon.submit();
      console.log("4444");
      updateUser(this.user).then(res => {
        if (res.data.code == 200) {
          localStorage.setItem("username", this.user.nick);
          localStorage.setItem("icon", this.user.icon);
          this.selectUser(localStorage.getItem("userId"));
          location.reload();
          this.$message({
            message: "修改成功",
            type: "success"
          });
        }
      });
    }
  }
};
</script>

<style scoped>
.personal {
  background-color: #ffffff;
  padding: 40px;
}
.personal h1 {
  text-align: center;
  font-family: "楷体";
  margin: 0;
  padding-top: 20px;
}
.personal p {
  padding: 5px;
}
.person-container {
  padding: 0px 330px 0px 330px;
}

.avatar-uploader {
  position: relative;
  left: 50px;
  top: -20px;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
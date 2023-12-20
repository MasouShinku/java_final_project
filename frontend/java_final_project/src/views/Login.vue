<template>
  <div class="login-container">
    <div class="login-box">
      <h1>登录</h1>
      <el-input class="name-input" v-model="username" placeholder="请输入用户名" />
      <el-input class="pwd-input" v-model="password" placeholder="请输入密码" />
      <el-button class="login-btn" type="danger" @click=onLogin>登录</el-button>
      <div class="footer">

      </div>
    </div>
  </div>
</template>



<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
  /* background: linear-gradient(to bottom, rgba(255, 255, 255, 0.555) 0%, rgba(194, 194, 194, 0.822) 100%); */
  /* background: linear-gradient(to right, rgba(255, 192, 203, 0.5), rgba(0, 0, 255, 0.5)); */
  background: linear-gradient(to right, rgba(255, 182, 193, 1), rgba(135, 206, 250, 1));

}

.login-box {
  padding: 20px;
  background: white;
  border-radius: 10px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 300px;
  height: 350px;
}

h1 {
  margin-bottom: 1.5rem;
}

.name-input {
  background-color: white;
  padding-left: 30px;
  padding-right: 30px;
  height: 40px;
  margin-top: 10px;

}

.pwd-input {
  background-color: white;
  padding-left: 30px;
  padding-right: 30px;
  height: 40px;
  margin-top: 40px;

}


.login-btn {
  padding-left: 30px;
  padding-right: 30px;
  margin-top: 40px;
  border-radius: 5px;
  background-color: #ff85a2;
  color: white;
  cursor: pointer;
}

.login-btn:hover {
  background-color: #ff6781;
}

.footer {
  margin-top: 1rem;
}

.footer a {
  text-decoration: none;
  color: #ff85a2;
  margin: 0 0.5rem;
}

.footer a:hover {
  text-decoration: underline;
}
</style>


<script>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

export default {
  setup() {
    const router = useRouter();
    const username = ref('');
    const password = ref('');
    // const userData = ref();




    const onLogin = async () => {

      const userData = {
        name: username.value,
        password: password.value
      }

      axios.post('http://101.43.181.83:8080/api/user/login', userData)
        .then(response => {
          // 处理一下逻辑
          console.log(response.data);

          if (response.data.status == true) {
            console.log(response.data);
            localStorage.setItem("satoken", response.data.token)
            console.log(response.data.token)
            router.push('/problemset')

          }

        })
        .catch(error => {
          console.log(error);
        });

    }

    return {
      username,
      password,
      axios,
      onLogin
    };
  }
};
</script>
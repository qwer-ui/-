<template>
   <el-form :model="loginForm" :rules="fieldRules" ref="loginForm"
     label-position="left" label-width="0px" class="demo-ruleForm login-container"
   >
      <h2 class="title">系统登录</h2>
      <el-form-item prop="account">
         <el-input type="text" v-model="loginForm.account" auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
     <el-form-item prop="password">
       <el-input type="password" v-model="loginForm.password" auto-complete="off"  placeholder="密码"></el-input>
     </el-form-item>
     <el-form-item >
       <el-col :span="12">
         <el-form-item>
           <el-input type="test" v-model="loginForm.captcha" auto-complete="off" placeholder="验证码, 单击图片刷新"/>
         </el-form-item>
       </el-col>
       <el-col :span="12">
         <el-form-item>
           <img style="width: 100%;" class="pointer" :src="loginForm.src" @click="refreshCaptcha">
         </el-form-item>
       </el-col>
     </el-form-item>
     <el-form-item style="width:100%;">
       <span><el-button type="primary" style="width:48%;" @click.native.prevent="reset">重 置</el-button></span>&nbsp;&nbsp;
       <span><el-button type="primary" style="width:48%;" @click.native.prevent="login" :loading="loading">登 录</el-button></span>
     </el-form-item>
   </el-form>
</template>
<script>
    import {logins} from '../network/modules/login'
    import Cookies   from 'js-cookie'
    export default {
        name: "Login",
        data(){
            return{
                loading:false,
                loginForm:{
                    account:'luxun',
                    password:'123',
                    captcha:'',
                    src:''
                },
                fieldRules:{
                    account:[
                        {required:true,message:'请输入账号',trigger:'blur'}
                    ],
                    password:[
                        {required:true,message:'请输入密码',trigger:'blur'}
                    ]
                },
                checked:true
            }
        },
        methods:{
            login(){
                this.loading=true;
                let name=this.loginForm.account;
                let password=this.loginForm.password;
                let captcha=this.loginForm.captcha;

                //发送登录请求
                logins(name,password,captcha).then(res=>{
                    if(res.data.msg!=null){
                       return;
                    }else{
                        //Cookies.set('token',res.data.token)
                       console.log(res.data.data.id)
                        sessionStorage.setItem('userId',res.data.data.id)//保存用户名到本地会话
                       this.$router.push('/home')//登录成功，跳转到主页
                    }
                })
            },
            reset(){
                this.$refs.loginForm.resetFields();
            },
            refreshCaptcha(){
               this.loginForm.src='http://127.0.0.1:6001/login/captcha.jpg?t='+new Date().getTime()
            }
        },
        mounted() {
             this.refreshCaptcha()
        }
    }
</script>
<style scoped>
  .login-container{
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .title{
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
</style>

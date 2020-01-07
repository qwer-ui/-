<template>
    <div class="container">
      <!--导航栏菜单-->
      <div class="narbar">
        <NavBar/>
      </div>
      <!--头部区域-->
      <div class="headbar">
        <HeadBar/>
      </div>
       <!--主内容-->
        <div class="maincontent">
          <MainContent/>
        </div>
    </div>
</template>

<script>
    import {HeadBar,MainContent,NavBar} from './index'
    export default {
        name: "Home",
        components: {
            HeadBar,
            MainContent,
            NavBar
        },
    }
</script>

<style scoped>
 .container{
     position: absolute;
     top: 0px;
     left: 0px;
     right: 0px;
     bottom: 0px;
 }
  .narbar{
    position: absolute;
    top: 60px;
    width: 200px;
    height: calc(100% - 60px);
  }
  .headbar{
    position: absolute;
    top: 0px;
    width: 100%;
    height: 60px;
    background-color: cadetblue;
  }
  .maincontent{
    position: absolute;
    top: 60px;
    left: 200px;
    width: calc(100% - 200px);
    height:calc(100% - 60px);
  }
</style>

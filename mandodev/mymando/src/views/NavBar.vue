<template>
     <div class="">
        <div>
           <el-menu class="el-menu"
               unique-opened
               router
              >
              <el-submenu v-for="(item,index) in this.routes" :key="index" :index="index+''">
                <template slot="title">
                  <i :class="item.iconcls"></i>
                  <span>{{item.name}}</span>
                </template>
                <el-menu-item v-for="(item2) in item.children" :key="item2.path" :index="item2.path">
                  <i :class="item2.iconcls"></i>
                  <span>{{item2.name}}</span>
                </el-menu-item>
              </el-submenu>
           </el-menu>
        </div>
     </div>
</template>

<script>
    export default {
        name: "NavBar",
        computed:{
            routes(){
                return this.$store.state.routes
            }
        }
    }
</script>

<style scoped>

</style>

<template>
  <div class="userComponent">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size">
        <el-form-item>
          <el-input v-model="filters.name" placeholder="昵称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="add">新增</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="roles"  >
      <el-table-column prop="id" label="ID"/>
      <el-table-column prop="name" label="昵称"/>
      <el-table-column prop="parent_id" label="父级ID"/>
      <el-table-column prop="path" label="访问路径"/>
      <el-table-column prop="component" label="名称"/>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" @click="compile(scope.row)" plain></el-button>
          <el-button type="danger" icon="el-icon-delete" @click="remove(scope.row.id)" plain></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      layou="prev,pager,next"
      :total="page.totalCount"
      @current-change="change"
      small
    >
    </el-pagination>

    <!--新增编辑块-->
    <el-dialog :title="operation?'新增':'编辑'" width="40%" :visible.sync="dialogVisible" @close="closes">
      <el-form :model="dataForm" label-width="80px" :rules="dataFormRules" :size="size">
        <el-form-item label="ID" prop="id" v-if="false">
          <el-input v-model="dataForm.id"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="name">
          <el-input placeholder="请输入昵称" v-model="dataForm.name"></el-input>
        </el-form-item>
        <el-form-item label="访问路径" prop="name">
          <el-input placeholder="请输入访问路径" v-model="dataForm.path"></el-input>
        </el-form-item>
        <el-form-item label="页面名称" prop="name">
          <el-input placeholder="请输入页面名称" v-model="dataForm.component"></el-input>
        </el-form-item>
        <el-form-item label="菜单级别" prop="jgvalue">
          <el-cascader
            v-model="jgvalue"
            :options="MenuLists"
            @change="handleChange"></el-cascader>
          (不选择为一级)
        </el-form-item>
        <el-form-item label="访问角色" prop="role_id">
          <el-checkbox-group
            v-model="dataForm.role_id">
            <el-checkbox  v-for="city in RoleList" v-model="role_idzhi" :label="city" :key="city">{{city.name}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item>
          <el-button @click="newly">提交</el-button><el-button @click="reset">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import {GetMenu} from '../../network/modules/menus/getMenu'
  import {PutMenu} from '../../network/modules/menus/putMenu'
  import {DelMenu} from '../../network/modules/menus/delMenu'
  import {PostMenu} from '../../network/modules/menus/postMenu'
  export default {
    name: "Menu",
    data(){
      return{
        role_idzhi:0,
        roles:[],
        jgvalue:'',
        size:'small',
        filters:{
          name:''
        },
        page:{
          totalCount:0
        },
        operation:false,//true:新增，false:编辑
        dialogVisible:false, //新增编辑界面是否显示
        dataFormRules:{
          name:[
            {required: true, message: '昵称', trigger: 'blur'}
          ],
          path:[
            {required: true, message: '路径', trigger: 'blur'}
          ],
          component:[
            {required: true, message: '页面名称', trigger: 'blur'}
          ]
        },
        //新增编辑界面数据
        checkList:[],
        MenuLists:[],
        MenuList:[],
        RoleList:[],
        checkLis:[],
        dataForm:{
          id:'',
          name:'',
          parent_id:0,
          path:'',
          component:'',
          role_id:[]
        },
      }
    },
    mounted() {
      //查询所有用户
      this.getUser(1,10,this.filters.name)
    },
    methods:{
      remove(id){
        this.$confirm('此操作将删除该菜单, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          DelMenu("/menu/delMenu/"+id).then(res=>{
            if(res.data.state==200){
              this.getUser(1,10,this.filters.name);
              this.$message({
                type: 'success',
                message: res.data.mag
              });
            }else{
              this.$message({
                type: 'info',
                message: res.data.mag
              });
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      compile(row){
        this.operation=false;
        this.dataForm = row;
        this.dialogVisible=true
      },
      handleChange(value) {
        this.dataForm.parent_id=value[value.length-1];
      },
      newly(){

      /*  let reg=new RegExp('/','g')//g代表全部
        this.dataForm.path=JSON.stringify(this.dataForm.path).replace(reg,'%2F');*/
        if(this.dataForm.id!=''){
          let  menu= this.assignment()
          PutMenu("/menu/putMenu",menu).then(res=>{
            this.closes()
            if(res.data.state==200){
              this.getUser(index,10,this.filters.name)
              this.$message({
                type: 'success',
                message: res.data.mag
              });
            }else{
              this.$message({
                type: 'info',
                message: res.data.mag
              });
            }
          }).catch(err=>{
            console.log("出现错误!")
          })
        }else {
          let  menu= this.assignment()
          PostMenu("/menu/insMenu",menu).then(res=>{
            this.closes()
            if(res.data.state==200){
              this.getUser(index,10,this.filters.name)
              this.$message({
                type: 'success',
                message: res.data.mag
              });
            }else{
              this.$message({
                type: 'info',
                message: res.data.mag
              });
            }
          }).catch(err=>{
            console.log("出现错误!")
          })
        }
      },
      reset(){
        this.checkLis=[];
        this.dataForm={
          id:'',
          name:'',
          parent_id:0,
          path:'',
          component:'',
          role_id:[]
        }
      },
      search(){
        //根据名称查询用户
        this.getUser(1,10,this.filters.name)
      },
      change(index){
        //分页查询用户
        this.getUser(index,10,this.filters.name)
      },
      getUser(pageNo,pageSize,name){
        if(!pageNo){
          pageNo=1
        }
        GetMenu('/menu/getMenuList',pageNo,pageSize,name).then(res=>{
          console.log(res.data.data.MenuLists)
          this.roles=res.data.data.MenuList.records
          this.page.totalCount=res.data.data.MenuList.totalRecords
          this.MenuLists = res.data.data.MenuLists
          this.RoleList = res.data.data.RoleList
        }).catch(err=>{
          console.log(err.toString())
        })
      },
      deptTreeHandle(){

      },
      assignment(){
        console.log(this.dataForm)
        let  menu={
          id:this.dataForm.id,
          name:this.dataForm.name,
          parent_id:this.dataForm.parent_id,
          path:this.dataForm.path,
          role_id:this.dataForm.role_id,
          component:this.dataForm.component
        }
        for (var i=0;i<this.dataForm.role_id.length;i++) {
          console.log(this.dataForm.role_id[i].id)
          menu.role_id[i] = this.dataForm.role_id[i].id;
        }
        return menu;
      }
    ,
      add(){
        this.operation=true;
        this.dialogVisible=true
      },
      closes(){
        this.dialogVisible=false
        this.dataForm={
          id:'',
          name:'',
          parent_id:0,
          path:'',
          component:'',
          role_id:[]
        }
      }
    }
  }
</script>

<style scoped>

</style>

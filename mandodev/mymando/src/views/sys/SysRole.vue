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
      <el-table-column prop="remark" label="备注"/>
      <el-table-column prop="create_by" label="创建者"/>
      <el-table-column prop="create_time" label="创建日期"/>
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
        <el-form-item label="备注" prop="nick_name">
          <el-input placeholder="请输入备注" v-model="dataForm.remark"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="newly">提交</el-button><el-button @click="reset">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import {GetRole} from '../../network/modules/role/getRole'
  import {PutRole} from '../../network/modules/role/putRole'
  import {DelRole} from '../../network/modules/role/delRole'
  import {PostRole} from '../../network/modules/role/postRole'
  export default {
    name: "Role",
    data(){
      return{
        roles:[],
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
          remark:[
            {required: true, message: '备注', trigger: 'blur'}
          ]
        },
        //新增编辑界面数据
        password:'',
        dataForm:{
          id:'',
          name:'',
          remark:'',
        },
      }
    },
    mounted() {
      //查询所有用户
      this.getUser(1,10,this.filters.name)
    },
    methods:{
      remove(id){
        this.$confirm('此操作将删除该角色, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          DelRole("/role/delRole/"+id).then(res=>{
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
      newly(){
        if(this.dataForm.id!=''){
          PutRole("/role/putDept.js",this.dataForm).then(res=>{
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
          PostRole("/role/insRole",this.dataForm).then(res=>{
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
        this.dataForm={
          id:'',
          name:'',
          remark:'',
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
        GetRole('/role/getRoleList',pageNo,pageSize,name).then(res=>{
          this.roles=res.data.data.RoleList
          this.page.totalCount=res.data.data.totalRecords
        }).catch(err=>{
          console.log("出现错误!")
        })
      },
      deptTreeHandle(){

      },
      add(){
        this.operation=true;
        this.dialogVisible=true
      },
      closes(){
        this.dialogVisible=false
        this.dataForm={
          id:'',
          name:'',
          remark:''
        }
      }
    }
  }
</script>

<style scoped>

</style>

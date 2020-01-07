<template>
  <div class="userComponent">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size">
        <el-form-item>
          <el-input v-model="filters.name" placeholder="机构名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="add">新增</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="users"  >
      <el-table-column prop="id" label="ID"/>
      <el-table-column prop="name" label="机构名"/>
      <el-table-column prop="parent_id" label="父级ID"/>
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
        <el-form-item label="机构名" prop="name">
          <el-input placeholder="请输入机构名" v-model="dataForm.name"></el-input>
        </el-form-item>
        <el-form-item label="机构" prop="jgvalue">
          <el-cascader
            v-model="jgvalue"
            :options="MenuList"
            @change="handleChange"></el-cascader>
        </el-form-item>
        <el-form-item>
          <el-button @click="newly">提交</el-button><el-button @click="reset">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import {GetDept} from '../../network/modules/dept/getDept'
  import {PutDept} from '../../network/modules/dept/putDept'
  import {DelDept} from '../../network/modules/dept/delDept'
  import {PostDept} from '../../network/modules/dept/postDept'
  export default {
    name: "Dept",
    data(){
      return{
        jgvalue:'',
        options:[],
        users:[],
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
            {required: true, message: '请输入机构名', trigger: 'blur'}
          ]
        },
        MenuList:[],
        //新增编辑界面数据0
        dataForm:{
          id:'',
          name:'',
          parent_id:0,
        },
      }
    },
    mounted() {
      //查询所有用户
      this.getUser(1,10,this.filters.name)
    },
    methods:{
      remove(id){
        this.$confirm('此操作将删除该机构, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          DelDept("/dept/delDept/"+id).then(res=>{
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
        this.operation=false,
          this.dataForm = row;
        //this.dataForm.id=row.id;
        //this.dataForm.email=row.email;
        //this.dataForm.mobile=row.mobile
        this.dialogVisible=true
      },
      newly(){
        if(this.dataForm.id!=''){
          PutDept("/dept/putDept",this.dataForm).then(res=>{
            this.closes()
            this.getUser(index,10,this.filters.name)
            if(res.data.state==200){
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
          PostDept("/dept/insDept",this.dataForm).then(res=>{
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
          parent_id:0,
        }
      },
      handleChange(value) {
        this.dataForm.parent_id=value[value.length-1];
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
        GetDept('/dept/getDeptList',pageNo,pageSize,name).then(res=>{
          this.users=res.data.data.DeptList.records
          this.page.totalCount=res.data.data.DeptList.totalRecords
          this.MenuList = res.data.data.DeptLists
        }).catch(err=>{
          console.log("出现错误!")
        })
      },
      deptTreeHandle(){

      },
      add(){
        this.operation=true,
          this.dialogVisible=true
      },
      closes(){
        this.dialogVisible=false
        this.dataForm={
          id:'',
          name:'',
          parent_id:0,
        }
      }
    }
  }
</script>

<style scoped>

</style>

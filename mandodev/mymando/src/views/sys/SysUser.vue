<template>
    <div class="userComponent">
       <!--工具栏-->
       <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
         <el-form :inline="true" :model="filters" :size="size">
            <el-form-item>
              <el-input v-model="filters.name" placeholder="用户名"></el-input>
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
        <el-table-column prop="name" label="用户名"/>
        <el-table-column prop="nick_name" label="昵称"/>
        <el-table-column prop="deptname" label="机构"/>
        <el-table-column prop="rolename" label="角色"/>
        <el-table-column prop="status" label="状态"/>
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
          <el-form-item label="用户名" prop="name">
            <el-input placeholder="请输入用户名" v-model="dataForm.name"></el-input>
          </el-form-item>
          <el-form-item label="昵称" prop="nick_name">
            <el-input placeholder="请输入昵称" v-model="dataForm.nick_name"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input placeholder="请输入密码" v-model="dataForm.password" show-password></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="password2">
            <el-input placeholder="请再次输入密码" v-model="password2" show-password></el-input>
          </el-form-item>
          <el-form-item label="email" prop="email">
            <el-input placeholder="请输入邮箱" v-model="dataForm.email"></el-input>
          </el-form-item>
          <el-form-item label="手机" prop="mobile">
            <el-input placeholder="请输入手机号" v-model="dataForm.mobile"></el-input>
          </el-form-item>
          <el-form-item label="机构" prop="jgvalue">
            <el-cascader
              v-model="jgvalue"
              :options="options"
              @change="handleChange"></el-cascader>
          </el-form-item>
          <el-form-item label="角色" prop="role_id">
            <el-select v-model="dataForm.role_id" placeholder="请选择">
              <el-option
                v-for="item in jsoptions"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button @click="newly">提交</el-button><el-button @click="reset">重置</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
</template>

<script>
    import {getAllUser} from '../../network/modules/user/getUser'
    import {PutUser} from '../../network/modules/user/putUser'
    import {DelUser} from '../../network/modules/user/delUser'
    import {PostUser} from '../../network/modules/user/postUser'
    export default {
        name: "User",
        data(){
            return{
              password2:'',
              jgvalue:[],
              options:[],
              jsoptions:[],
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
                       {required: true, message: '请输入用户名', trigger: 'blur'}
                   ],
                  nick_name:[
                    {required: true, message: '请输入昵称', trigger: 'blur'}
                  ],
                  password:[
                    {required: true, message: '请输入密码', trigger: 'blur'}
                  ],
                  password2:[
                    {required: true, message: '请再次输入密码', trigger: 'blur'}
                  ],
                  email:[
                    {required: true, message: '请输入邮箱', trigger: 'blur'}
                  ],
                  mobile:[
                    {required: true, message: '请输入手机号', trigger: 'blur'}
                  ]
                },
                //新增编辑界面数据
              password:'',
                dataForm:{
                  id:'',
                  name:'',
                  nick_name:'',
                  password:'',
                  email:'',
                  mobile:'',
                  dept_id:'',
                  role_id:'',
                },
            }
        },
        mounted() {
             //查询所有用户
             this.getUser(1,10,this.filters.name)
        },
        methods:{
          remove(id){
            this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              DelUser("/user/delUser/"+id).then(res=>{
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
            this.dataForm.password=''
            //this.dataForm.id=row.id;
            //this.dataForm.email=row.email;
            //this.dataForm.mobile=row.mobile
            this.dialogVisible=true
          },
          newly(){
            if(this.dataForm.id!=''){
              PutUser("/user/putUser",this.dataForm).then(res=>{
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
            PostUser("/user/insUser",this.dataForm).then(res=>{
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
            this.password2='';
            this.dataForm={
              id:'',
              name:'',
              nick_name:'',
              password:'',
              email:'',
              mobile:'',
              dept_id:'',
              role_id:'',
            }
          },
          handleChange(value) {
            this.dataForm.dept_id=value[value.length-1];
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
                getAllUser('/user/getUserList',pageNo,pageSize,name).then(res=>{
                    this.users=res.data.data.UserList.records
                    this.page.totalCount=res.data.data.UserList.totalRecords
                    this.options = res.data.data.DeptList
                    this.jsoptions = res.data.data.RoleList
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
                this.password2='';
                this.dataForm={
                  id:'',
                  name:'',
                  nick_name:'',
                  password:'',
                  email:'',
                  mobile:'',
                  dept_id:'',
                  role_id:'',
              }
            }
        }
    }
</script>

<style scoped>

</style>

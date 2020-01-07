package cn.sang.mapper;

import cn.sang.pojo.LoginUser;
import cn.sang.pojo.Sys_Role;
import cn.sang.pojo.Sys_User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    Sys_User loadUserByUsername(String name);
    List<Sys_Role> getUserRolesByUid(Integer id);
    Integer loadUserAuth(@Param("name")String name,@Param("password")String Password);
    LoginUser loadUserAuths(@Param("name")String name);
    List<Sys_Role> getRoleByUserId(@Param("UserId") Integer UserId);
    List<Sys_User> getSysUserList(@Param("PageNo")Integer PageNo,@Param("PageSize") Integer PageSize,@Param("name") String name);
    Integer getSysUserCount(@Param("name") String name);
    Integer InsUser(Sys_User sys_user);
    Integer UpdUser(Sys_User sys_user);
    Integer DelUser(Integer id);
    Integer Sys_User_Role(@Param("userid")Integer userid,@Param("roleid")Integer roleid);
}

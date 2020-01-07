package cn.sang.service;

import cn.sang.pojo.Page;
import cn.sang.pojo.Sys_Role;
import cn.sang.pojo.Sys_User;


import java.util.List;

public interface UserService {
    Sys_User loadUserByUsername(String name);
    List<Sys_Role> getUserRolesByUid(Integer id);
    Integer loadUserAuth(String name,String Password);
    Page getSysUserList(Integer PageNo, Integer PageSize, String name);
    boolean InsUser(Sys_User sys_user);
    boolean UpdUser(Sys_User sys_user);
    boolean DelUser(Integer id);
}

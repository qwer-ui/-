package cn.sang.service;

import cn.sang.pojo.Sys_Role;

import java.util.List;

public interface RoleService {
    List<Sys_Role> getRoleList();
    List<Sys_Role> getRoleListByName(String name,Integer PageNo,Integer PageSize);
    boolean DelRole(Integer id);
    boolean UpdRole(Sys_Role sys_role);
    boolean InsRole(Sys_Role sys_role);
    int getRoleCountByName(String name);
}

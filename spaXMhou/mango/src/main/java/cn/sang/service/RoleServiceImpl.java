package cn.sang.service;

import cn.sang.mapper.RoleMapper;
import cn.sang.pojo.Sys_Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Sys_Role> getRoleList() {
        return roleMapper.getRoleList();
    }

    @Override
    public List<Sys_Role> getRoleListByName(String name, Integer PageNo, Integer PageSize) {
        return roleMapper.getRoleListByName(name,(PageNo-1)*PageSize,PageSize);
    }

    @Override
    public boolean DelRole(Integer id) {
        return roleMapper.DelRole(id)>0?true:false;
    }

    @Override
    public boolean UpdRole(Sys_Role sys_role) {
        sys_role.setLast_update_time(new Date());
        sys_role.setLast_update_by("admin");
        return roleMapper.UpdRole(sys_role)>0?true:false;
    }

    @Override
    public boolean InsRole(Sys_Role sys_role) {
        sys_role.setCreate_time(new Date());
        sys_role.setCreate_by("admin");
        return roleMapper.InsRole(sys_role)>0?true:false;
    }

    @Override
    public int getRoleCountByName(String name) {
        return roleMapper.getRoleCountByName(name);
    }


}

package cn.sang.service;

import cn.sang.mapper.UserMapper;
import cn.sang.pojo.Page;
import cn.sang.pojo.Sys_Role;
import cn.sang.pojo.Sys_User;
import cn.sang.utlis.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Sys_User loadUserByUsername(String name) {
        return userMapper.loadUserByUsername(name);
    }

    @Override
    public List<Sys_Role> getUserRolesByUid(Integer id) {
        return userMapper.getUserRolesByUid(id);
    }

    @Override
    public Integer loadUserAuth(String name, String Password) {
        return userMapper.loadUserAuth(name,Password);
    }

    @Override
    public Page getSysUserList(Integer PageNo, Integer PageSize, String name) {
        Page page = new Page();
        page.setTotalRecords(userMapper.getSysUserCount(name));
        page.setRecords(userMapper.getSysUserList((PageNo-1)*PageSize,PageSize,name));
        return page;
    }

    @Override
    public boolean InsUser(Sys_User sys_user) {
        sys_user.setCreate_time(new Date());
        sys_user.setCreate_by("admin");
        sys_user.setSalt(PasswordUtils.getSalt());
        sys_user.setPassword(PasswordUtils.encode(sys_user.getPassword(),sys_user.getSalt()));
        sys_user.setStatus(1);
        sys_user.setDel_flag(0);
        Integer inshs = userMapper.InsUser(sys_user);
        if(inshs>0){
            Integer urhs = userMapper.Sys_User_Role(sys_user.getId(),sys_user.getRole_id());
            if(urhs>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean UpdUser(Sys_User sys_user) {
        sys_user.setLast_update_time(new Date());
        sys_user.setLast_update_by("admin");
        sys_user.setSalt(PasswordUtils.getSalt());
        sys_user.setPassword(PasswordUtils.encode(sys_user.getPassword(),sys_user.getSalt()));
        sys_user.setStatus(1);
        sys_user.setDel_flag(0);
        Integer hs = userMapper.UpdUser(sys_user);
        if(hs>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean DelUser(Integer id) {
        Integer hs = userMapper.DelUser(id);
        if(hs>0){
            return true;
        }
        return false;
    }
}

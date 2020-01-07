//package cn.sang.service;
//
//import cn.sang.mapper.UserMapper;
//import cn.sang.pojo.LoginUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LoginUserService implements UserDetailsService {
//    @Autowired
//    UserMapper userMapper;
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        LoginUser sys_user = userMapper.loadUserAuths(s);
//        if(sys_user==null){
//            throw new UsernameNotFoundException("账号不存在!");
//        }
//        sys_user.setRoles(userMapper.getRoleByUserId(sys_user.getId()));
//        return sys_user;
//    }
//}

//package cn.sang.config;
//
//import cn.sang.mapper.MenuMapper;
//import cn.sang.pojo.Sys_Menu;
//import cn.sang.pojo.Sys_Role;
//import cn.sang.pojo.VO.MenuVO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//import org.springframework.util.AntPathMatcher;
//
//import java.util.Collection;
//import java.util.List;
//
//public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
//
//    AntPathMatcher antPathMatcher = new AntPathMatcher();
//    @Autowired
//    MenuMapper menuMapper;
//
//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
//        String requestUrl = ((FilterInvocation) o).getRequestUrl();
//        List<MenuVO> allMenu = menuMapper.getMenuListAll();
//        for(MenuVO menuVO:allMenu){
//            if(antPathMatcher.match(menuVO.getPath(),requestUrl)){
//                List<Sys_Role> roles = menuMapper.getRoleByMenuId(menuVO.getId());
//                String[] roleAll = new String[roles.size()];
//                for (int i=0;i<roles.size();i++){
//                    roleAll[i] = roles.get(i).getName();
//                }
//                return SecurityConfig.createList(roleAll);
//            }
//        }
//        return SecurityConfig.createList("ROLE_LOGIN");
//    }
//
//    @Override
//    public Collection<ConfigAttribute> getAllConfigAttributes() {
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return FilterInvocation.class.isAssignableFrom(aClass);
//    }
//}

package cn.sang.service;

import cn.sang.mapper.MenuMapper;
import cn.sang.pojo.Page;
import cn.sang.pojo.Sys_Menu;
import cn.sang.pojo.VO.MenuVO;
import cn.sang.utlis.Menu_Down;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Service
public class MenuServiceImpl implements MenuService{

    @Resource
    MenuMapper menuMapper;

    @Override
    public List<MenuVO> getMenuByUserId(Integer UserId) {
        Integer RoleId = menuMapper.getRoleIdByUserId(UserId);
        List<MenuVO> list = menuMapper.getMenuParentByRoleId(RoleId);
        List<MenuVO> lists = Menu_Down.getDeptList(list);
//        for (int i=0;i<list.size();i++){
//            list.get(i).setChildren(menuMapper.getMenuByParentId(RoleId,list.get(i).getId()));
//        }
        return lists;
    }

    @Override
    public boolean UpdMenu(Sys_Menu menu) {
        menu.setLast_update_by("admin");
        menu.setLast_update_time(new Date());
        if(menuMapper.UpdMenu(menu)>0){
          menuMapper.DelSys_Role_Menu(menu.getId());
               Integer[] roles = menu.getRole_id();
               for (int i=0;i<roles.length;i++){
                   if(menuMapper.Sys_Role_Menu(roles[i],menu.getId(),"admin",new Date())>0){

                   } else{
                       System.out.println("菜单角色关联失败!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                   }
               }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean DelMenu(Integer id) {
        if(menuMapper.DelMenu(id)>0){
            if(menuMapper.DelSys_Role_Menu(id)>0){
                return true;
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean InsMenu(Sys_Menu menu){
        menu.setCreate_by("admin");
        menu.setCreate_time(new Date());
        int hs = menuMapper.InsMenu(menu);
        if(hs>0){
            Integer[] roles = menu.getRole_id();
            for (int i=0;i<roles.length;i++){
                if(menuMapper.Sys_Role_Menu(roles[i],menu.getId(),"admin",new Date())>0){

                } else{
                    System.out.println("菜单角色关联失败!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                }
            }
            return true;
        }else {
            return false;
        }

    }

    @Override
    public int getMenuCount(String name) {
        return menuMapper.getMenuCount(name);
    }

    @Override
    public List<MenuVO> getMenuListAll() {
        return menuMapper.getMenuListAll();
    }

    @Override
    public Page getMenuListByName(String name, Integer PageNo, Integer PageSize) {
        Page page = new Page();
        page.setTotalRecords(menuMapper.getMenuCount(name));
        List<Sys_Menu> list = menuMapper.getMenuListByName(name,(PageNo-1)*PageSize,PageSize);
        for(int i=0;i<list.size();i++){
            list.get(i).setRole_id(menuMapper.getRole_idByMenuId(list.get(i).getId()));
        }
        page.setRecords(list);
        return page;
    }
}

package cn.sang.utlis;

import cn.sang.pojo.Dept;
import cn.sang.pojo.Sys_Menu;
import cn.sang.pojo.VO.DeptVO;
import cn.sang.pojo.VO.MenuVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu_Down {


    public static List<MenuVO> getDeptList(List<MenuVO> allMenu){
        Map<String,MenuVO> data = new HashMap<String,MenuVO>();
        //根节点
        List<MenuVO> rootMenu = new ArrayList<MenuVO>();
        for (MenuVO nav : allMenu) {
            if(nav.getParent_id()==0){//父节点是0的，为根节点。
                rootMenu.add(nav);
            }
        }
        /* 根据Menu类的order排序 */
        //Collections.sort(rootMenu, order());
        //为根菜单设置子菜单，getClild是递归调用的
        for (MenuVO nav : rootMenu) {
            /* 获取根节点下的所有子节点 使用getChild方法*/
            List<MenuVO> childList = Menu_Down.getChild(nav.getId(), allMenu);
            if(childList.size()==0){
                nav.setChildren(null);
            }else{
                nav.setChildren(childList);//给根节点设置子节点
            }
        }
        /**
         * 输出构建好的菜单数据。
         *
         */
        return rootMenu;
    }




    public static List<MenuVO> getChild(Integer id, List<MenuVO> allMenu){
        //子菜单
        List<MenuVO> childList = new ArrayList<MenuVO>();
        for (MenuVO nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if(nav.getParent_id()==id){
                childList.add(nav);
            }
        }
        //递归
        for (MenuVO nav : childList) {
            if(getChild(nav.getId(), allMenu).size()!=0){
                nav.setChildren(getChild(nav.getId(), allMenu));
            }
        }
        // Collections.sort(childList,order());//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if(childList.size() == 0){
            return new ArrayList<MenuVO>();
        }
        return childList;
    }
}

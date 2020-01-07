package cn.sang.utlis;

import cn.sang.pojo.Dept;
import cn.sang.pojo.VO.DeptVO;

import java.util.*;

public class Pull_Down {


    public static List<Object> getDeptList(List<DeptVO> allMenu){
        Map<String,Object> data = new HashMap<String,Object>();
        //根节点
        List<Object> rootMenu = new ArrayList<Object>();
        for (DeptVO nav : allMenu) {
            if(nav.getParent_id()==0){//父节点是0的，为根节点。
                rootMenu.add(nav);
            }
        }
        /* 根据Menu类的order排序 */
        //Collections.sort(rootMenu, order());
        //为根菜单设置子菜单，getClild是递归调用的
        for (Object nav : rootMenu) {
            DeptVO deptVO = (DeptVO)nav;
            /* 获取根节点下的所有子节点 使用getChild方法*/
            List<Object> childList = Pull_Down.getChild(deptVO.getValue(), allMenu);
            if(childList.size()==0){
                nav = new Dept(deptVO.getValue(),deptVO.getLabel());
            }else{
                deptVO.setChildren(childList);//给根节点设置子节点
            }
        }
        /**
         * 输出构建好的菜单数据。
         *
         */
        return rootMenu;
    }


    /**
     * 获取子节点
     * @param id 父节点id
     * @param allMenu 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    public static List<Object> getChild(Integer id, List<DeptVO> allMenu){
        //子菜单
        List<Object> childList = new ArrayList<Object>();
        for (DeptVO nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if(nav.getParent_id()==id){
                childList.add(nav);
            }
        }
        //递归
        for (Object nav : childList) {
            DeptVO deptVO = (DeptVO)nav;
            if(getChild(deptVO.getValue(), allMenu).size()!=0){
                deptVO.setChildren(getChild(deptVO.getValue(), allMenu));
            }
        }
       // Collections.sort(childList,order());//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if(childList.size() == 0){
            return new ArrayList<Object>();
        }
        return childList;
    }
}

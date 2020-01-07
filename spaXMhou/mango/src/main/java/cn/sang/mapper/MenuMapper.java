package cn.sang.mapper;

import cn.sang.pojo.Sys_Menu;
import cn.sang.pojo.Sys_Role;
import cn.sang.pojo.VO.MenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Mapper
public interface MenuMapper {
    List<MenuVO> getMenuParentByRoleId(@Param("RoleId")Integer RoleId);
    Integer getRoleIdByUserId(@Param("UserId") Integer UserId);
    List<MenuVO> getMenuByParentId(@Param("RoleId") Integer RoleId,@Param("ParentId") Integer ParentId);
    List<MenuVO> getMenuListAll();
    int UpdMenu(Sys_Menu menu);
    int DelMenu(@Param("id")Integer id);
    int InsMenu(Sys_Menu menu);
    int DelSys_Role_Menu(@Param("menuid") Integer menuid);
    int getMenuCount(@Param("name") String name);
    Integer[] getRole_idByMenuId(@Param("id") Integer id);
    List<Sys_Role> getRoleByMenuId(@Param("menuid") Integer muntid);
    List<Sys_Menu> getMenuListByName(@Param("name") String name,@Param("PageNo") Integer PageNo,@Param("PageSize") Integer PageSize);
    int Sys_Role_Menu(@Param("role_id") Integer role_id,@Param("menu_id") Integer menu_id,@Param("create_by") String create_by,@Param("create_time") Date create_time);
}

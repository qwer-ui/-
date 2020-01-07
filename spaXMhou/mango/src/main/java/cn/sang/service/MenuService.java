package cn.sang.service;

import cn.sang.pojo.Page;
import cn.sang.pojo.Sys_Menu;
import cn.sang.pojo.VO.MenuVO;

import java.util.Date;
import java.util.List;

public interface MenuService {
    List<MenuVO> getMenuByUserId(Integer UserId);
    boolean UpdMenu(Sys_Menu menu);
    boolean DelMenu(Integer id);
    boolean InsMenu(Sys_Menu menu);
    int getMenuCount(String name);
    List<MenuVO> getMenuListAll();
    Page getMenuListByName(String name, Integer PageNo, Integer PageSize);
}

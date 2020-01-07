package cn.sang.service;

import cn.sang.pojo.Dept;
import cn.sang.pojo.Page;
import cn.sang.pojo.Sys_Role;
import cn.sang.pojo.VO.DeptVO;

import java.util.List;

public interface DeptService {
    List<DeptVO> getDeptVoList();
    Page getDeptListByName(String name, Integer PageNo, Integer PageSize);
    int getDeptCount(String name);
    boolean DelDept(Integer id);
    boolean UpdDept(Dept dept);
    boolean InsDept(Dept Dept);
}

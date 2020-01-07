package cn.sang.service;

import cn.sang.mapper.DeptMapper;
import cn.sang.pojo.Dept;
import cn.sang.pojo.Page;
import cn.sang.pojo.VO.DeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    DeptMapper deptMapper;

    @Override
    public List<DeptVO> getDeptVoList() {
        return deptMapper.getDeptVoList();
    }

    @Override
    public Page getDeptListByName(String name, Integer PageNo, Integer PageSize) {
        Page page = new Page();
        page.setTotalRecords(deptMapper.getDeptCount(name));
        page.setRecords(deptMapper.getDeptListByName(name,(PageNo-1)*PageSize,PageSize));
        return page;
    }

    @Override
    public int getDeptCount(String name) {
        return deptMapper.getDeptCount(name);
    }

    @Override
    public boolean DelDept(Integer id) {
        if(deptMapper.DelDept(id)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean UpdDept(Dept dept) {
        dept.setLast_update_by("admin");
        dept.setLast_update_time(new Date());
        if(deptMapper.UpdDept(dept)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean InsDept(Dept dept) {
        dept.setCreate_by("admin");
        dept.setCreate_time(new Date());
        if(deptMapper.InsDept(dept)>0){
            return true;
        }else {
            return false;
        }
    }
}

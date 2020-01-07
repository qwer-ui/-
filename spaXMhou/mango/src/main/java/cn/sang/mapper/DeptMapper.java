package cn.sang.mapper;

import cn.sang.pojo.Dept;
import cn.sang.pojo.Sys_Role;
import cn.sang.pojo.VO.DeptVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface DeptMapper {
    List<DeptVO> getDeptVoList();
    List<Dept> getDeptListByName(@Param("name") String name,@Param("PageNo") Integer PageNo,@Param("PageSize") Integer PageSize);
    int getDeptCount(@Param("name") String name);
    int DelDept(@Param("id") Integer id);
    int UpdDept(Dept dept);
    int InsDept(Dept dept);
}

package cn.sang.mapper;

import cn.sang.pojo.Sys_Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<Sys_Role> getRoleList();
    List<Sys_Role> getRoleListByName(@Param("name")String name,@Param("PageNo") Integer PageNo,@Param("PageSize") Integer PageSize);
    int DelRole(@Param("id") Integer id);
    int UpdRole(Sys_Role sys_role);
    int InsRole(Sys_Role sys_role);
    int getRoleCountByName(@Param("name")String name);
}

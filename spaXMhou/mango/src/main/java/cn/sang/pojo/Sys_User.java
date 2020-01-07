package cn.sang.pojo;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class Sys_User{
    private Integer id;
    private String name;
    private String nick_name;
    private String avatar;
    private String password;
    private String salt;
    private String email;
    private String mobile;
    private Integer status;
    private Integer dept_id;
    private String create_by;
    private Date create_time;
    private String last_update_by;
    private Date last_update_time;
    private Integer del_flag;
    private String deptname;
    private String rolename;
    private Integer role_id;

    private List<Sys_Role> roles;

}

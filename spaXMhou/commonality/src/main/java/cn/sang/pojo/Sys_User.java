package cn.sang.pojo;

import lombok.Data;

import java.util.Date;

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
    private Byte status;
    private Integer dept_id;
    private String create_by;
    private Date create_time;
    private String last_update_by;
    private Date last_update_time;
    private Byte del_flag;


}

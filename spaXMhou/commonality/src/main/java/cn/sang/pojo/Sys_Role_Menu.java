package cn.sang.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Sys_Role_Menu {
    private Integer id;
    private Integer role_id;
    private Integer menu_id;
    private String create_by;
    private Date create_time;
    private String last_update_by;
    private Date last_update_time;
}

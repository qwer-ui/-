package cn.sang.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Sys_Menu implements Serializable {
    private Integer[] role_id;
    private Integer id;
    private String name;
    private Integer parent_id;
    private String url;
    private String perms;
    private Integer type;
    private String icon;
    private Integer order_num;
    private String order_by;
    private String create_by;
    private Date create_time;
    private String last_update_by;
    private Date last_update_time;
    private Byte del_flag;
    private String component;
    private String path;

}

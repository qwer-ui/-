package cn.sang.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Sys_Role {
    private Integer bigint;
    private String name;
    private String remark;
    private String create_by;
    private Date create_time;
    private String last_update_by;
    private Date last_update_time;
    private Byte del_flag;
}

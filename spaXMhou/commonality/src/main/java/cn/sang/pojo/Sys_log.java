package cn.sang.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Sys_log {
    private Integer id;
    private String user_name;
    private String operation;
    private String method;
    private String params;
    private Integer time;
    private String ip;
    private String create_by;
    private Date create_time;
    private String last_update_by;
    private Date last_update_time;
}

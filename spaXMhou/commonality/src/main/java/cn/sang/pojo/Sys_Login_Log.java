package cn.sang.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Sys_Login_Log {
    private Integer id;
    private String user_name;
    private String status;
    private String ip;
    private String create_by;
    private Date create_time;
    private String last_update_by;
    private Date last_update_time;

}

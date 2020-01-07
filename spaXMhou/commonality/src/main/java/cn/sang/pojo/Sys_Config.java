package cn.sang.pojo;

import lombok.Data;

@Data
public class Sys_Config {
private int id;
private String value;
private String lable;
private String type;
private String description;
private Integer sort;
private String create_by;
private String create_time;
private String last_update_by;
private Integer last_update_time;
private String remarks;
private Byte del_flag;

}

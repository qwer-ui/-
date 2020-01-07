package cn.sang.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Sys_Dict {
private Integer id;
private String value;
private String lable;
private String type;
private String description;
private Integer sort;
private String create_by;
private Date create_time;
private String last_update_by;
private Date last_update_time;
private String remarks;
private Byte del_flag;
}

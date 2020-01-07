package cn.sang.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Sys_Dept {
    private Integer id;
    private String name;
    private Integer parent_id;
    private Integer order_num;
    private String create_by;
    private Date create_time;
    private String last_update_by;
    private Date last_update_time;
    private Byte del_flag;


}

package cn.sang.pojo;

import cn.sang.pojo.VO.DeptVO;
import lombok.Data;

import java.util.Date;

@Data
public class Dept {
    private Integer value;
    private String label;
    private Integer id;
    private String name;
    private Integer parent_id;
    private String create_by;
    private Date create_time;
    private String last_update_by;
    private Date last_update_time;

    public Dept(Integer value,String label){
        this.value = value;
        this.label = label;
    }
    public Dept(){

    }
}

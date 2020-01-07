package cn.sang.pojo.VO;

import lombok.Data;

import java.util.List;

@Data
public class DeptVO {
    private Integer value;
    private String label;
    private Object children;
    private Integer parent_id;
}

package cn.sang.pojo.VO;

import cn.sang.pojo.Sys_Menu;
import lombok.Data;

import java.util.List;

@Data
public class MenuVO {
    private Integer id;
    private Integer value;
    private String label;
    private String path;
    private String name;
    private String icon;
    private List<MenuVO> children;
    private String component;
    private Integer parent_id;
}

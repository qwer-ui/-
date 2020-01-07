package cn.sang.pojo;

import lombok.Data;

@Data
public class Returned {
    private int state;
    private String mag;
    private Object data;

    public static Returned ok(String mag){
        Returned returned = new Returned();
        returned.setData("null");
        returned.setMag(mag);
        returned.setState(200);
        return returned;
    }
    public static Returned ok(Object data){
        Returned returned = new Returned();
        returned.setData(data);
        returned.setMag("null");
        returned.setState(200);
        return returned;
    }
    public static Returned err(String mag){
        Returned returned = new Returned();
        returned.setData("null");
        returned.setMag(mag);
        returned.setState(500);
        return returned;
    }
    public static Returned err(int state,Object data,String mag){
        Returned returned = new Returned();
        returned.setData(data);
        returned.setMag(mag);
        returned.setState(state);
        return returned;
    }
    public static Returned err(Object data){
        Returned returned = new Returned();
        returned.setData(data);
        returned.setMag("null");
        returned.setState(500);
        return returned;
    }

}

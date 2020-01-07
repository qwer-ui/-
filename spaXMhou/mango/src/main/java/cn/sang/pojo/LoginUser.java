package cn.sang.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class LoginUser implements UserDetails {
    private Integer id;
    private String name;
    private String nick_name;
    private String avatar;
    private String password;
    private String salt;
    private String email;
    private String mobile;
    private Integer status;
    private Integer dept_id;
    private String create_by;
    private Date create_time;
    private String last_update_by;
    private Date last_update_time;
    private Integer del_flag;
    private String deptname;
    private String rolename;
    private Integer role_id;

    private List<Sys_Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Sys_Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

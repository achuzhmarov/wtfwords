package wtf.wtfgames.wtfwords.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import wtf.wtfgames.wtfwords.model.User;

import java.util.*;

public class JwtUser implements UserDetails {
    //all users now have only USER authority
    private final static List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));

    private final String login;
    private final String password;

    public JwtUser(User user) {
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

    public JwtUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

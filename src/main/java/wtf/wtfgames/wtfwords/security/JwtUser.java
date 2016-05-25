package wtf.wtfgames.wtfwords.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import wtf.wtfgames.wtfwords.model.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtUser implements UserDetails {
    //all users now have only USER authority
    private final static List<GrantedAuthority> authorities =  Arrays.asList(new SimpleGrantedAuthority("USER"));

    private final Long id;
    private final String login;
    private final String password;

    public JwtUser(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

    @JsonIgnore
    public Long getId() {
        return id;
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

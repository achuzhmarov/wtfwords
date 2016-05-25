package wtf.wtfgames.wtfwords.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wtf.wtfgames.wtfwords.dao.UserDao;
import wtf.wtfgames.wtfwords.dao.exception.UserNotFoundException;
import wtf.wtfgames.wtfwords.model.User;
import wtf.wtfgames.wtfwords.security.JwtUser;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userDao.getByLogin(username);
            return new JwtUser(user);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
    }
}

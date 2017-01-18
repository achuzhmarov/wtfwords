package wtf.wtfgames.wtfwords.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtf.wtfgames.wtfwords.dao.UserDao;
import wtf.wtfgames.wtfwords.dao.exception.UserNotFoundException;
import wtf.wtfgames.wtfwords.model.User;
import wtf.wtfgames.wtfwords.security.model.JwtUser;

@Service
@Transactional
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //authorize every user by login
        return new JwtUser(username, passwordEncoder.encode(""));

        /*try {
            User user = userDao.getByLogin(username);
            return new JwtUser(user);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }*/
    }
}

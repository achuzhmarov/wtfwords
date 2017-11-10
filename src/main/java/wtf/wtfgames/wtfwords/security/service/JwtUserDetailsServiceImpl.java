package wtf.wtfgames.wtfwords.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtf.wtfgames.wtfwords.model.User;
import wtf.wtfgames.wtfwords.repository.UserRepository;
import wtf.wtfgames.wtfwords.security.model.JwtUser;

import java.util.Optional;

@Service
@Transactional
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //authorize every user by login
        return new JwtUser(username, passwordEncoder.encode(""));

        /*Optional<User> user = userRepository.findByLogin(username);

        if (user.isPresent()) {
            return new JwtUser(user.get());
        } else {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }*/
    }
}

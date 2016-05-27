package wtf.wtfgames.wtfwords.integration.security.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import wtf.wtfgames.wtfwords.dao.UserDao;
import wtf.wtfgames.wtfwords.integration.BaseIT;
import wtf.wtfgames.wtfwords.model.User;
import wtf.wtfgames.wtfwords.security.model.JwtAuthenticationRequest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class AuthenticationRestControllerIT extends BaseIT {

    @Autowired
    private UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void testLogin() throws Exception {
        runInTransaction(() -> {
            User user = new User("test_user", passwordEncoder.encode("password"));
            userDao.save(user);
        });

        String message = post("login", new JwtAuthenticationRequest("test_user", "password"));

        assertThat(message, containsString("token"));
    }
}

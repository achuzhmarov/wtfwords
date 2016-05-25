package wtf.wtfgames.wtfwords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wtf.wtfgames.wtfwords.dao.UserDao;
import wtf.wtfgames.wtfwords.model.User;

@RestController
public class HelloController {

    private static final String template = "Hello, %s!";

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("/hello")
    @Transactional
    public User greeting(@RequestParam(value="name", defaultValue="World") String name) {
        //User user = new User(String.format(template, name));

        User user = new User();
        user.setLogin("tigra");
        user.setPassword(passwordEncoder.encode("tigrik"));

        userDao.save(user);

        return user;
    }
}

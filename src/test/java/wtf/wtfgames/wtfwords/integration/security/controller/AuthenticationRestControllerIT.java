package wtf.wtfgames.wtfwords.integration.security.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import wtf.wtfgames.wtfwords.dao.UserDao;
import wtf.wtfgames.wtfwords.integration.BaseIT;
import wtf.wtfgames.wtfwords.model.User;
import wtf.wtfgames.wtfwords.security.model.JwtAuthenticationRequest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

//@Transactional
public class AuthenticationRestControllerIT extends BaseIT {

    @Autowired
    private UserDao userDao;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void testLogin() throws Exception {

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    User user = new User("test_user", passwordEncoder.encode("password"));
                    userDao.save(user);
                } catch (Exception e) {
                    e.printStackTrace();
                    transactionStatus.setRollbackOnly();
                }
            }
        });

        /*transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    User user = new User("test_user1", "password");
                    userDao.save(user);
                } catch (Exception e) {
                    e.printStackTrace();
                    transactionStatus.setRollbackOnly();
                }
            }
        });*/

        //User user = new User("test_user", "password");
        //userDao.save(user);

        String message = post("login", new JwtAuthenticationRequest("test_user", "password"));

        //assertThat(message, containsString("token"));
    }
}

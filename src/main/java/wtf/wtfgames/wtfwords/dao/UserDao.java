package wtf.wtfgames.wtfwords.dao;

import com.googlecode.genericdao.search.Search;
import org.springframework.stereotype.Repository;
import wtf.wtfgames.wtfwords.dao.exception.UserNotFoundException;
import wtf.wtfgames.wtfwords.model.User;

import java.util.List;

@Repository
public class UserDao extends BaseDao<User> {
    public User getByLogin(String login) throws UserNotFoundException {
        Search search = new Search().addFilterEqual("login", login);
        List<User> users = search(search);

        if (!users.isEmpty()) {
            return users.get(0);
        } else {
            throw new UserNotFoundException(login);
        }
    }
}
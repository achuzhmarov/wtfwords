package wtf.wtfgames.wtfwords.dao;

import com.googlecode.genericdao.search.Search;
import org.springframework.stereotype.Repository;
import wtf.wtfgames.wtfwords.dao.exception.UserNotFoundException;
import wtf.wtfgames.wtfwords.model.PersonalReward;
import wtf.wtfgames.wtfwords.model.User;

import java.util.List;

@Repository
public class PersonalRewardDao extends BaseDao<PersonalReward> {
    public List<PersonalReward> getByUserId(String userId) {
        Search search = new Search()
                .addFilterEqual("userId", userId)
                .addFilterNotEqual("acquired", true);

        return search(search);
    }
}
package wtf.wtfgames.wtfwords.dao;

import org.springframework.stereotype.Repository;
import wtf.wtfgames.wtfwords.model.PersonalReward;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonalRewardDao extends BaseDao<PersonalReward> {
    public List<PersonalReward> getByUserId(String userId) {
        /*Search search = new Search()
                .addFilterEqual("userId", userId)
                .addFilterNotEqual("acquired", true);

        return search(search);*/
        return new ArrayList<PersonalReward>();
    }
}
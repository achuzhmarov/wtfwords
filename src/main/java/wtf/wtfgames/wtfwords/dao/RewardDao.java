package wtf.wtfgames.wtfwords.dao;

import org.springframework.stereotype.Repository;
import wtf.wtfgames.wtfwords.model.Reward;

import java.util.List;

@Repository
public class RewardDao extends BaseDao<Reward> {
    public Reward getByCode(String code) {
        /*Search search = new Search()
                .addFilterEqual("code", code);

        List<Reward> rewards = search(search);

        if (!rewards.isEmpty()) {
            return rewards.get(0);
        } else {
            return null;
        }
        */

        return null;
    }
}

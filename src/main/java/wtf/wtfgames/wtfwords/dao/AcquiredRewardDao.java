package wtf.wtfgames.wtfwords.dao;

import org.springframework.stereotype.Repository;
import wtf.wtfgames.wtfwords.model.AcquiredReward;
import wtf.wtfgames.wtfwords.model.Reward;

import java.util.List;

@Repository
public class AcquiredRewardDao extends BaseDao<AcquiredReward> {
    public boolean isRewardAquired(Reward reward, String userId) {
        /*Search search = new Search()
                .addFilterEqual("userId", userId)
                .addFilterEqual("reward", reward);

        List<AcquiredReward> acquiredRewards = search(search);

        if (!acquiredRewards.isEmpty()) {
            return true;
        } else {
            return false;
        }
        */

        return false;
    }
}

package wtf.wtfgames.wtfwords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtf.wtfgames.wtfwords.dao.AcquiredRewardDao;
import wtf.wtfgames.wtfwords.dao.RewardDao;
import wtf.wtfgames.wtfwords.model.AcquiredReward;
import wtf.wtfgames.wtfwords.model.Reward;

@Service
public class RewardService {
    @Autowired
    RewardDao rewardDao;

    @Autowired
    AcquiredRewardDao acquiredRewardDao;

    @Transactional
    public Reward getReward(String code) {
        return rewardDao.getByCode(code.toUpperCase());
    }

    @Transactional
    public boolean isAlreadyClaimed(Reward reward, String userId) {
        if (reward == null) {
            return false;
        }

        return acquiredRewardDao.isRewardAquired(reward, userId);
    }

    @Transactional
    public void claimReward(Reward reward, String userId) {
        AcquiredReward acquiredReward = new AcquiredReward(userId, reward);
        //acquiredRewardDao.save(acquiredReward);

        if (reward.getUsesLimit() != null) {
            reward.setUsesLimit(reward.getUsesLimit() - 1);
            //rewardDao.save(reward);
        }
    }
}

package wtf.wtfgames.wtfwords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtf.wtfgames.wtfwords.repository.AcquiredRewardRepository;
import wtf.wtfgames.wtfwords.repository.RewardRepository;
import wtf.wtfgames.wtfwords.model.AcquiredReward;
import wtf.wtfgames.wtfwords.model.Reward;

import java.util.Optional;

@Service
public class RewardService {
    @Autowired
    RewardRepository rewardRepository;

    @Autowired
    AcquiredRewardRepository acquiredRewardRepository;

    @Transactional
    public Optional<Reward> getReward(String code) {
        return rewardRepository.findByCode(code.toUpperCase());
    }

    @Transactional
    public boolean isAlreadyClaimed(Reward reward, String userId) {
        if (reward == null) {
            return false;
        }

        return acquiredRewardRepository.existsByRewardAndUserId(reward, userId);
    }

    @Transactional
    public void claimReward(Reward reward, String userId) {
        AcquiredReward acquiredReward = new AcquiredReward(userId, reward);
        acquiredRewardRepository.save(acquiredReward);

        if (reward.getUsesLimit() != null) {
            reward.setUsesLimit(reward.getUsesLimit() - 1);
            rewardRepository.save(reward);
        }
    }
}

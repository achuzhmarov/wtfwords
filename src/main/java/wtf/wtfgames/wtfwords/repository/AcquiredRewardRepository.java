package wtf.wtfgames.wtfwords.repository;

import wtf.wtfgames.wtfwords.model.AcquiredReward;
import wtf.wtfgames.wtfwords.model.Reward;

public interface AcquiredRewardRepository extends BaseRepository<AcquiredReward> {
    boolean existsByRewardAndUserId(Reward reward, String userId);
}

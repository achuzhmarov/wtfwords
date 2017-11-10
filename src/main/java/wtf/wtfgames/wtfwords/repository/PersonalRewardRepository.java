package wtf.wtfgames.wtfwords.repository;

import wtf.wtfgames.wtfwords.model.PersonalReward;

import java.util.List;

public interface PersonalRewardRepository extends BaseRepository<PersonalReward> {
    List<PersonalReward> findByUserIdAndAcquiredIsFalse(String userId);
}
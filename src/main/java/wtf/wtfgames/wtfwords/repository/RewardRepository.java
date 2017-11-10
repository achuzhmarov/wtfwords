package wtf.wtfgames.wtfwords.repository;

import wtf.wtfgames.wtfwords.model.Reward;

import java.util.Optional;

public interface RewardRepository extends BaseRepository<Reward> {
    Optional<Reward> findByCode(String code);
}

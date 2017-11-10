package wtf.wtfgames.wtfwords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtf.wtfgames.wtfwords.repository.PersonalRewardRepository;
import wtf.wtfgames.wtfwords.model.PersonalReward;

import java.util.List;

@Service
public class PersonalRewardService {
    @Autowired
    PersonalRewardRepository personalRewardRepository;

    @Transactional
    public PersonalReward getPersonalReward(String userId) {
        List<PersonalReward> personalRewards = personalRewardRepository.findByUserIdAndAcquiredIsFalse(userId);

        if (!personalRewards.isEmpty()) {
            PersonalReward personalReward = personalRewards.get(0);
            personalReward.setAcquired(true);
            personalRewardRepository.save(personalReward);

            return personalReward;
        } else {
            return null;
        }
    }
}

package wtf.wtfgames.wtfwords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtf.wtfgames.wtfwords.dao.PersonalRewardDao;
import wtf.wtfgames.wtfwords.model.PersonalReward;
import wtf.wtfgames.wtfwords.service.basic.MailService;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class PersonalRewardService {
    @Autowired
    PersonalRewardDao personalRewardDao;

    @Transactional
    public PersonalReward getPersonalReward(String userId) {
        List<PersonalReward> personalRewards = personalRewardDao.getByUserId(userId);

        if (!personalRewards.isEmpty()) {
            PersonalReward personalReward = personalRewards.get(0);
            personalReward.setAcquired(true);
            //personalRewardDao.save(personalReward);

            return personalReward;
        } else {
            return null;
        }
    }
}

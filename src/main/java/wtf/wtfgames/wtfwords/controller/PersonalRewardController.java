package wtf.wtfgames.wtfwords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wtf.wtfgames.wtfwords.controller.type.BaseIdRequest;
import wtf.wtfgames.wtfwords.controller.type.RewardResponse;
import wtf.wtfgames.wtfwords.model.PersonalReward;
import wtf.wtfgames.wtfwords.service.PersonalRewardService;

@RestController
public class PersonalRewardController {
    @Autowired
    private PersonalRewardService personalRewardService;

    @RequestMapping(value = "personal_reward", method = RequestMethod.POST)
    public RewardResponse checkPersonalReward(@RequestBody BaseIdRequest request) {
        try {
            PersonalReward personalReward = personalRewardService.getPersonalReward(request.getId());

            if (personalReward != null) {
                return new RewardResponse(personalReward.getMessage(), personalReward.getWtfs());
            } else {
                return new RewardResponse();
            }


        } catch (Exception e) {
            e.printStackTrace();
            return new RewardResponse();
        }
    }
}

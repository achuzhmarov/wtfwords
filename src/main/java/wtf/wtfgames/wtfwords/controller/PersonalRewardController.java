package wtf.wtfgames.wtfwords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wtf.wtfgames.wtfwords.controller.type.BaseIdRequest;
import wtf.wtfgames.wtfwords.controller.type.FeedbackRequest;
import wtf.wtfgames.wtfwords.controller.type.PersonalRewardResponse;
import wtf.wtfgames.wtfwords.controller.type.StatusResponse;
import wtf.wtfgames.wtfwords.model.PersonalReward;
import wtf.wtfgames.wtfwords.service.FeedbackService;
import wtf.wtfgames.wtfwords.service.PersonalRewardService;

@RestController
public class PersonalRewardController {
    @Autowired
    private PersonalRewardService personalRewardService;

    @RequestMapping(value = "personal_reward", method = RequestMethod.POST)
    public PersonalRewardResponse checkPersonalReward(@RequestBody BaseIdRequest request) {
        try {
            PersonalReward personalReward = personalRewardService.getPersonalReward(request.getId());

            if (personalReward != null) {
                return new PersonalRewardResponse(personalReward.getMessage(), personalReward.getWtfs());
            } else {
                return new PersonalRewardResponse();
            }


        } catch (Exception e) {
            e.printStackTrace();
            return new PersonalRewardResponse();
        }
    }
}

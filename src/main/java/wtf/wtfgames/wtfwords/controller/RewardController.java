package wtf.wtfgames.wtfwords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wtf.wtfgames.wtfwords.controller.type.RewardRequest;
import wtf.wtfgames.wtfwords.controller.type.RewardResponse;
import wtf.wtfgames.wtfwords.model.Reward;
import wtf.wtfgames.wtfwords.service.RewardService;

@RestController
public class RewardController {
    @Autowired
    private RewardService rewardService;

    @RequestMapping(value = "reward_code", method = RequestMethod.POST)
    public RewardResponse getRewardByCode(@RequestBody RewardRequest request) {
        try {
            Reward reward = rewardService.getReward(request.getCode());

            if (reward == null) {
                return new RewardResponse();
            } else if (reward.isExpired()) {
                return RewardResponse.getExpiredResonse();
            } else if (rewardService.isAlreadyClaimed(reward, request.getId())) {
                return RewardResponse.getAlreadyClaimedResponse();
            } else {
                rewardService.claimReward(reward, request.getId());
                return new RewardResponse(reward.getMessage(), reward.getWtfs());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new RewardResponse();
        }
    }
}

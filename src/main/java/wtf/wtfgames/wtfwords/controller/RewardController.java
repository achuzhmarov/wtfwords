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

import java.util.Optional;

@RestController
public class RewardController {
    @Autowired
    private RewardService rewardService;

    @RequestMapping(value = "reward_code", method = RequestMethod.POST)
    public RewardResponse getRewardByCode(@RequestBody RewardRequest request) {
        try {
            Optional<Reward> reward = rewardService.getReward(request.getCode());

            if (reward.isPresent()) {
                return generateRewardResponse(reward.get(), request.getId());
            } else {
                return new RewardResponse();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new RewardResponse();
        }
    }

    private RewardResponse generateRewardResponse(Reward reward, String userId) {
        if (reward.isExpired()) {
            return RewardResponse.getExpiredResonse();
        } else if (rewardService.isAlreadyClaimed(reward, userId)) {
            return RewardResponse.getAlreadyClaimedResponse();
        } else {
            rewardService.claimReward(reward, userId);
            return new RewardResponse(reward.getMessage(), reward.getWtfs());
        }
    }
}

package wtf.wtfgames.wtfwords.controller.type;

import lombok.Data;

@Data
public class PersonalRewardResponse {
    private boolean hasReward;
    private String message;
    private int wtfs;

    public PersonalRewardResponse() {
        this.hasReward = false;
    }

    public PersonalRewardResponse(String message, int wtfs) {
        this.hasReward = true;
        this.message = message;
        this.wtfs = wtfs;
    }
}

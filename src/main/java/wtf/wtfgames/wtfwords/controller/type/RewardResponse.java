package wtf.wtfgames.wtfwords.controller.type;

import lombok.Data;

@Data
public class RewardResponse {
    private boolean hasReward;
    private String message;
    private int wtfs;
    private boolean isExpired = false;
    private boolean isAlreadyClaimed = false;

    public RewardResponse() {
        this.hasReward = false;
    }

    public RewardResponse(String message, int wtfs) {
        this.hasReward = true;
        this.message = message;
        this.wtfs = wtfs;
    }

    static public RewardResponse getExpiredResonse() {
        RewardResponse response = new RewardResponse();
        response.isExpired = true;
        return response;
    }

    static public RewardResponse getAlreadyClaimedResponse() {
        RewardResponse response = new RewardResponse();
        response.isAlreadyClaimed = true;
        return response;
    }
}

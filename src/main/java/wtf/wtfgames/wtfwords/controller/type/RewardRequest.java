package wtf.wtfgames.wtfwords.controller.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class RewardRequest extends BaseIdRequest {
    private String code;

    public RewardRequest(String userId, String code) {
        this.setId(userId);
        this.code = code;
    }
}

package wtf.wtfgames.wtfwords.controller.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackRequest extends BaseIdRequest {
    private String fromEmail;
    private String text;
}

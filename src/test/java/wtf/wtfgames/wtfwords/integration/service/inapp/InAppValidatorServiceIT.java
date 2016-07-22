package wtf.wtfgames.wtfwords.integration.service.inapp;

import org.junit.Test;
import wtf.wtfgames.wtfwords.integration.BaseIT;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class InAppValidatorServiceIT extends BaseIT {

    @Test
    public void validateInApp_InvalidReceipt_GetError() throws Exception {
        String result = post("ios_inapp", "test_receipt");

        assertThat(result, containsString("false"));
    }
}

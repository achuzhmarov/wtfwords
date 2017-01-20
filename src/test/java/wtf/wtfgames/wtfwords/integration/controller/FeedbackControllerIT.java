package wtf.wtfgames.wtfwords.integration.controller;

import org.junit.Test;
import wtf.wtfgames.wtfwords.integration.BaseIT;
import wtf.wtfgames.wtfwords.controller.type.FeedbackRequest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class FeedbackControllerIT extends BaseIT {
    /*@Test
    public void sendFeedback_WithEmail_Success() throws Exception {
        FeedbackRequest request = new FeedbackRequest("tiggrand@gmail.com", "It is very good indeed! full email!");
        request.setId("FULL-FEEDBACK");


        String result = post("feedback", request);


        assertThat(result, containsString("true"));
    }

    @Test
    public void sendFeedback_NullEmail_Success() throws Exception {
        FeedbackRequest request = new FeedbackRequest(null, "Beautiful game! Null email!");
        request.setId("NULL-EMAIL-FEEDBACK");


        String result = post("feedback", request);


        assertThat(result, containsString("true"));
    }

    @Test
    public void sendFeedback_EmptyEmail_Success() throws Exception {
        FeedbackRequest request = new FeedbackRequest("", "Wow! Just Wow! Empty Email");
        request.setId("EMPTY-EMAIL-FEEDBACK");


        String result = post("feedback", request);


        assertThat(result, containsString("true"));
    }

    @Test
    public void sendFeedback_RussianText_Success() throws Exception {
        FeedbackRequest request = new FeedbackRequest("tiggrand@gmail.com", "Текст на русском!");
        request.setId("FULL-FEEDBACK");


        String result = post("feedback", request);


        assertThat(result, containsString("true"));
    }*/
}

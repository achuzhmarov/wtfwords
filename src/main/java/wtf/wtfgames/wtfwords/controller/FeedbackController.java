package wtf.wtfgames.wtfwords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wtf.wtfgames.wtfwords.controller.type.FeedbackRequest;
import wtf.wtfgames.wtfwords.controller.type.StatusResponse;
import wtf.wtfgames.wtfwords.service.FeedbackService;

@RestController
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value = "feedback", method = RequestMethod.POST)
    public StatusResponse sendFeedback(@RequestBody FeedbackRequest request) {
        try {
            feedbackService.sendFeedback(request.getId(), request.getFromEmail(), request.getText());
            return new StatusResponse(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new StatusResponse(false);
        }
    }
}

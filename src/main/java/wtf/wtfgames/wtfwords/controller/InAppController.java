package wtf.wtfgames.wtfwords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wtf.wtfgames.wtfwords.controller.type.InAppRequest;
import wtf.wtfgames.wtfwords.controller.type.InAppResponse;
import wtf.wtfgames.wtfwords.service.inapp.InAppValidatorService;

@RestController
public class InAppController {
    @Autowired
    private InAppValidatorService validatorService;

    @RequestMapping(value = "ios_inapp", method = RequestMethod.POST)
    public InAppResponse validate(@RequestBody InAppRequest request) {
        return new InAppResponse(validatorService.validateInAppPurchase(request.getReceipt()));
    }
}

package wtf.wtfgames.wtfwords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wtf.wtfgames.wtfwords.service.inapp.InAppValidatorService;

@RestController
public class InAppRestController {

    @Autowired
    private InAppValidatorService validatorService;

    @RequestMapping(value = "ios_inapp", method = RequestMethod.POST)
    public boolean validate(@RequestBody String receipt) {
        return validatorService.validateInAppPurchase(receipt);
    }
}

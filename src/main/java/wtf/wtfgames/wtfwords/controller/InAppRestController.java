package wtf.wtfgames.wtfwords.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wtf.wtfgames.wtfwords.service.inapp.InAppValidatorService;

@RestController
public class InAppRestController {
    private class InAppValidateResponse {
        private boolean valid;

        InAppValidateResponse(boolean valid) {
            this.valid = valid;
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }
    }

    @Autowired
    private InAppValidatorService validatorService;

    @RequestMapping(value = "ios_inapp", method = RequestMethod.POST)
    public InAppValidateResponse validate(@RequestBody String receipt) {
        return new InAppValidateResponse(validatorService.validateInAppPurchase(receipt));
    }
}

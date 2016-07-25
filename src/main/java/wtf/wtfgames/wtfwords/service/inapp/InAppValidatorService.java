package wtf.wtfgames.wtfwords.service.inapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wtf.wtfgames.wtfwords.service.inapp.type.InAppAppleRequest;
import wtf.wtfgames.wtfwords.service.inapp.type.InAppAppleResponse;

@Service
public class InAppValidatorService {
    private final static String SANDBOX_URI = "https://sandbox.itunes.apple.com/verifyReceipt";
    private final static String PROD_URI = "https://buy.itunes.apple.com/verifyReceipt";

    @Autowired @Qualifier("restTemplate")
    private RestTemplate rest;

    public boolean validateInAppPurchase(String receiptData) {
        System.out.println("validating incoming receipt");
        System.out.println(receiptData);

        int errorCode = validateInAppPurchaseByUrl(receiptData, PROD_URI);

        if (errorCode == 0) {
            return true;
        } else if (errorCode == 21007) {
            int devErrorCode = validateInAppPurchaseByUrl(receiptData, SANDBOX_URI);
            return devErrorCode == 0;
        } else {
            return false;
        }
    }

    private int validateInAppPurchaseByUrl(String receiptData, String url) {
        InAppAppleRequest request = new InAppAppleRequest(receiptData);

        try {
            String resultString = rest.postForObject(url, request, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            InAppAppleResponse result = objectMapper.readValue(resultString, InAppAppleResponse.class);

            if (result.getStatus() == 0) {
                return 0;
            } else {
                String error = getErrorByStatus(result.getStatus());

                //TODO - log error
                System.err.println(error);

                return result.getStatus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }

    private String getErrorByStatus(int status) {
        switch (status) {
            case 21000:
                return status + ": The App Store could not read the JSON object you provided";
            case 21002:
                return status + ": The data in the receipt-data property was malformed.";
            case 21003:
                return status + ": The data in the receipt-data property was malformed.";
            case 21004:
                return status + ": The shared secret you provided does not match the shared secret on file for your account.";
            case 21005:
                return status + ": The receipt server is not currently available.";
            case 21006:
                return status + ": This receipt is valid but the subscription has expired. When this status code is returned to your server, the receipt data is also decoded and returned as part of the response.";
            case 21007:
                return status + ": This receipt is a sandbox receipt, but it was sent to the production service for verification.";
            case 21008:
                return status + ": This receipt is a production receipt, but it was sent to the sandbox service for verification.";
            default:
                return status + ": Unknown error";
        }
    }
}

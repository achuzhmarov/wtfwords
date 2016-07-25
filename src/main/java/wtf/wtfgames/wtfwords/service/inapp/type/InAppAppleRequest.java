package wtf.wtfgames.wtfwords.service.inapp.type;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InAppAppleRequest {
    private String receiptData;

    public InAppAppleRequest() {

    }

    public InAppAppleRequest(String receiptData) {
        this.receiptData = receiptData;
    }

    @JsonGetter("receipt-data")
    public String getReceiptData() {
        return receiptData;
    }

    public void setReceiptData(String receiptData) {
        this.receiptData = receiptData;
    }
}

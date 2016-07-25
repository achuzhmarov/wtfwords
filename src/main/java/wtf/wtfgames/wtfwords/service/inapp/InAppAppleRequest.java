package wtf.wtfgames.wtfwords.service.inapp;

import com.fasterxml.jackson.annotation.JsonGetter;

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

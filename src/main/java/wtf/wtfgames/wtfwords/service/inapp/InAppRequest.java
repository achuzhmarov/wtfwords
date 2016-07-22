package wtf.wtfgames.wtfwords.service.inapp;

import com.fasterxml.jackson.annotation.JsonGetter;

public class InAppRequest {
    private String receiptData;

    public InAppRequest() {

    }

    public InAppRequest(String receiptData) {
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

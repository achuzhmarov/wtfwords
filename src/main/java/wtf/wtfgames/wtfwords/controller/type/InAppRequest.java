package wtf.wtfgames.wtfwords.controller.type;

public class InAppRequest {
    private String receipt;

    public InAppRequest() {

    }

    public InAppRequest(String receipt) {
        this.receipt = receipt;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
}

package wtf.wtfgames.wtfwords.service.inapp;

public class InAppResponse {
    private int status;
    private InAppReceipt receipt;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public InAppReceipt getReceipt() {
        return receipt;
    }

    public void setReceipt(InAppReceipt receipt) {
        this.receipt = receipt;
    }
}
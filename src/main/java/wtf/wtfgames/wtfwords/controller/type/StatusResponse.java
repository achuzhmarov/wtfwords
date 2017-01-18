package wtf.wtfgames.wtfwords.controller.type;

public class StatusResponse {
    private boolean success;

    public StatusResponse() {

    }

    public StatusResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
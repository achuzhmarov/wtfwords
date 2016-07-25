package wtf.wtfgames.wtfwords.controller.type;

public class InAppResponse {
    private boolean valid;

    public InAppResponse() {

    }

    public InAppResponse(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}

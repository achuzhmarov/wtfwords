package wtf.wtfgames.wtfwords.controller.type;

public abstract class BaseRequest {
    private String id;

    BaseRequest() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

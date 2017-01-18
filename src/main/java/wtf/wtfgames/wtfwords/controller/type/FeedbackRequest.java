package wtf.wtfgames.wtfwords.controller.type;

public class FeedbackRequest extends BaseRequest{
    private String fromEmail;
    private String text;

    public FeedbackRequest() {}

    public FeedbackRequest(String fromEmail, String text) {
        this.fromEmail = fromEmail;
        this.text = text;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

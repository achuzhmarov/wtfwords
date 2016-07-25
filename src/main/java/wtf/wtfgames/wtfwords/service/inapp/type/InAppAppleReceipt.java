package wtf.wtfgames.wtfwords.service.inapp.type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InAppAppleReceipt {
    private String bundleId;
    private String applicationVersion;
    private List<InAppApple> inApp;
    private String originalApplicationVersion;

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public List<InAppApple> getInApp() {
        return inApp;
    }

    public void setInApp(List<InAppApple> inApp) {
        this.inApp = inApp;
    }

    public String getOriginalApplicationVersion() {
        return originalApplicationVersion;
    }

    public void setOriginalApplicationVersion(String originalApplicationVersion) {
        this.originalApplicationVersion = originalApplicationVersion;
    }

    /*type Receipt struct {
        BundleId                   string            `json:"bundle_id"`
        ApplicationVersion         string            `json:"application_version"`
        InAppApple                      []PurchaseReceipt `json:"in_app"`
        OriginalApplicationVersion string            `json:"original_application_version"`
    }*/
}

package wtf.wtfgames.wtfwords.service.inapp;

import java.util.List;

public class InAppReceipt {
    private String bundleId;
    private String applicationVersion;
    private List<InApp> inApp;
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

    public List<InApp> getInApp() {
        return inApp;
    }

    public void setInApp(List<InApp> inApp) {
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
        InApp                      []PurchaseReceipt `json:"in_app"`
        OriginalApplicationVersion string            `json:"original_application_version"`
    }*/
}

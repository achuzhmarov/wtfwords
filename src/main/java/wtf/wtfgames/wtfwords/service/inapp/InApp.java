package wtf.wtfgames.wtfwords.service.inapp;

public class InApp {
    private String quantity;
    private String productId;
    private String transactionId;
    private String originalTransactionId;
    private String purchaseDate;
    private String originalPurchaseDate;
    private String expiresDate;
    private String appItemId;
    private String versionExternalIdentifier;
    private String webOrderLineItemId;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOriginalTransactionId() {
        return originalTransactionId;
    }

    public void setOriginalTransactionId(String originalTransactionId) {
        this.originalTransactionId = originalTransactionId;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getOriginalPurchaseDate() {
        return originalPurchaseDate;
    }

    public void setOriginalPurchaseDate(String originalPurchaseDate) {
        this.originalPurchaseDate = originalPurchaseDate;
    }

    public String getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(String expiresDate) {
        this.expiresDate = expiresDate;
    }

    public String getAppItemId() {
        return appItemId;
    }

    public void setAppItemId(String appItemId) {
        this.appItemId = appItemId;
    }

    public String getVersionExternalIdentifier() {
        return versionExternalIdentifier;
    }

    public void setVersionExternalIdentifier(String versionExternalIdentifier) {
        this.versionExternalIdentifier = versionExternalIdentifier;
    }

    public String getWebOrderLineItemId() {
        return webOrderLineItemId;
    }

    public void setWebOrderLineItemId(String webOrderLineItemId) {
        this.webOrderLineItemId = webOrderLineItemId;
    }
}

    /*type PurchaseReceipt struct {
        Quantity                  string `json:"quantity"`
        ProductId                 string `json:"product_id"`
        TransactionId             string `json:"transaction_id"`
        OriginalTransactionId     string `json:"original_transaction_id"`
        PurchaseDate              string `json:"purchase_date"`
        OriginalPurchaseDate      string `json:"original_purchase_date"`
        ExpiresDate               string `json:"expires_date"`
        AppItemId                 string `json:"app_item_id"`
        VersionExternalIdentifier string `json:"version_external_identifier"`
        WebOrderLineItemId        string `json:"web_order_line_item_id"`
    }*/
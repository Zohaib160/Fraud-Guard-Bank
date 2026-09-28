package zohaib.fraudguard;

import java.time.LocalDateTime;

public class Transaction {

    private long transactionId;
    private long customerId;
    private LocalDateTime timestamp;
    private double amount;
    private String transactionType;
    private String merchantName;
    private String merchantCategory;
    private String city;
    private String state;
    private double latitude;
    private double longitude;
    private String deviceId;
    private String ipAddress;
    private String status;

    public Transaction(
            long transactionId,
            long customerId,
            LocalDateTime timestamp,
            double amount,
            String transactionType,
            String merchantName,
            String merchantCategory,
            String city,
            String state,
            double latitude,
            double longitude,
            String deviceId,
            String ipAddress,
            String status) {

        this.transactionId = transactionId;
        this.customerId = customerId;
        this.timestamp = timestamp;
        this.amount = amount;
        this.transactionType = transactionType;
        this.merchantName = merchantName;
        this.merchantCategory = merchantCategory;
        this.city = city;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
        this.deviceId = deviceId;
        this.ipAddress = ipAddress;
        this.status = status;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getMerchantCategory() {
        return merchantCategory;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getStatus() {
        return status;
    }
}
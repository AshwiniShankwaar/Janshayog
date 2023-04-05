package com.BitGeekTalks.JanShayog.wallet.dataPlayload;

public class TransactionRequest {
    private String accountId;
    private String amount;
    private String reason;
    private boolean isDebit;

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "accountId='" + accountId + '\'' +
                ", amount='" + amount + '\'' +
                ", reason='" + reason + '\'' +
                ", isDebit=" + isDebit +
                '}';
    }

    public TransactionRequest() {
    }

    public TransactionRequest(String accountId, String amount, String reason, boolean isDebit) {
        this.accountId = accountId;
        this.amount = amount;
        this.reason = reason;
        this.isDebit = isDebit;
    }

    // getters and setters
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public boolean getIsDebit() {
        return isDebit;
    }
    public void setDebit(boolean debit) {
        this.isDebit = debit;
    }

}

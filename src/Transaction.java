public class Transaction {
    private String id;
    private String receiverBankAccount;
    private String senderBankAccount;
    private int amount;
    private String details;

    public Transaction(String id, String receiverBankAccount, String senderBankAccount, int amount, String details) {
        this.id = id;
        this.receiverBankAccount = receiverBankAccount;
        this.senderBankAccount = senderBankAccount;
        this.amount = amount;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceiverBankAccount() {
        return receiverBankAccount;
    }

    public void setReceiverBankAccount(String receiverBankAccount) {
        this.receiverBankAccount = receiverBankAccount;
    }

    public String getSenderBankAccount() {
        return senderBankAccount;
    }

    public void setSenderBankAccount(String senderBankAccount) {
        this.senderBankAccount = senderBankAccount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

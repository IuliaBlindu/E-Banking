public class Transaction {

    private String id;
    private String secondAccount;
    private Type type;
    private Category category;
    private int amount;
    private String details;

    public Transaction(String id, String secondAccount, Type type, Category category, int amount, String details) {
        this.id = id;
        this.secondAccount = secondAccount;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecondAccount() {
        return secondAccount;
    }

    public void setSecondAccount(String secondAccount) {
        this.secondAccount = secondAccount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

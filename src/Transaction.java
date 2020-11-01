import java.io.Serializable;

public class Transaction implements Serializable {

    private String secondAccount;
    private Type type;
    private Category category;
    private double amount;
    private String details;

    public Transaction (){
        this.secondAccount = null;
        this.type = null;
        this.category = null;
        this.amount = 0;
        this.details = null;
    }

    public Transaction(String secondAccount, Type type, Category category, double amount, String details) {
        this.secondAccount = secondAccount;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.details = details;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return secondAccount + " - " + amount + " Category: " + category + " Details:" + details;
    }
}

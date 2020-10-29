import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BankAccount implements Serializable {
    private Bank bank;
    private User accountOwner;
    private String accountNumber;
    private String cardNumber;
    private Date cardExpiryDate;
    private String CVV;
    private int balance;
    private Currency currency;
    private List<Transaction> transactions;

    public BankAccount(Bank bank, User accountOwner, String accountNumber, String cardNumber,
                       Date cardExpiryDate, String CVV, int balance, Currency currency) {
        this.bank = bank;
        this.accountOwner = accountOwner;
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.CVV = CVV;
        this.balance = balance;
        this.currency = currency;
        this.transactions = new ArrayList<>();;
    }

    public BankAccount(Bank bank, User accountOwner, String accountNumber, String cardNumber, Date cardExpiryDate,
                       String CVV, int balance, Currency currency, List<Transaction> transactions) {
        this.bank = bank;
        this.accountOwner = accountOwner;
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.CVV = CVV;
        this.balance = balance;
        this.currency = currency;
        this.transactions = transactions;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public User getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(User accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(Date cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransactions( Transaction transaction) {
        this.transactions.add(transaction);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "bank=" + bank +
                ", accountOwner=" + accountOwner +
                ", accountNumber='" + accountNumber + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardExpiryDate=" + cardExpiryDate +
                ", CVV='" + CVV + '\'' +
                ", balance=" + balance +
                '}';
    }
}

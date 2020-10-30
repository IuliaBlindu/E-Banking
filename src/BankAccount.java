import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BankAccount implements Serializable {
    private Bank bank;
    private User accountOwner;
    private String name;
    private String accountNumber;
    private String cardNumber;
    private Date cardExpiryDate;
    private String CVV;
    private double balance;
    private Currency currency;
    private List<Transaction> transactions;

    public BankAccount(){
        this.bank = null;
        this.accountOwner = null;
        this.name = null;
        this.accountNumber = null;
        this.cardNumber = null;
        this.cardExpiryDate = null;
        this.CVV = null;
        this.balance = 0;
        this.currency = null;
        this.transactions = new ArrayList<>();;
    }
    public BankAccount(Bank bank, User accountOwner, String name, String accountNumber, String cardNumber,
                       Date cardExpiryDate, String CVV, double balance, Currency currency) {
        this.bank = bank;
        this.accountOwner = accountOwner;
        this.name = name;
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.CVV = CVV;
        this.balance = balance;
        this.currency = currency;
        this.transactions = new ArrayList<>();;
    }

    public BankAccount(Bank bank, User accountOwner, String name, String accountNumber, String cardNumber, Date cardExpiryDate,
                       String CVV, double balance, Currency currency, List<Transaction> transactions) {
        this.bank = bank;
        this.accountOwner = accountOwner;
        this.name = name;
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.CVV = CVV;
        this.balance = balance;
        this.currency = currency;
        this.transactions = transactions;
    }

    public BankAccount(BankAccount account) {
        this.bank = account.bank;
        this.accountOwner = account.accountOwner;
        this.name = account.name;
        this.accountNumber = account.accountNumber;
        this.cardNumber = account.cardNumber;
        this.cardExpiryDate = account.cardExpiryDate;
        this.CVV = account.CVV;
        this.balance = account.balance;
        this.currency = account.currency;
        this.transactions = account.transactions;
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

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
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

    public double currency(Currency c){
        if(c == currency.RON){
            return MyConstants.RON;
        }
        else if (c == currency.EUR){
            return MyConstants.EUR;
        }
        else {
            return MyConstants.USD;
        }
    }

    public double convert (double amount, Currency c){
        double multiply = currency(c);
        double divide = currency(this.currency);

        amount = amount*multiply/divide;

        return amount;
    }


    public boolean verifyBalance (double amount){

        if (amount>this.balance){
            System.out.println(MyConstants.INSUFFICIENT_BALANCE);
            return false;
        }
        else {
            return true;
        }

    }

    public void changeBalance(double amount, Type t){

        if (t == Type.RECEIVE) {
            this.balance = this.balance + amount;
        }
        else {
            this.balance = this.balance - amount;
        }
    }


    @Override
    public String toString() {
        return "BankAccount{" +
                "bank=" + bank +
                ", accountOwner=" + accountOwner.getUsername() +
                ", name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardExpiryDate=" + cardExpiryDate +
                ", CVV='" + CVV + '\'' +
                ", balance=" + balance +
                ", currency=" + currency +
                ", transactions=" + transactions +
                '}';
    }
}

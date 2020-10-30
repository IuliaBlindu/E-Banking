import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// create default constructors
// exceptions

public class Application implements Serializable {

    public static int i = 0;
    public static int userId = 0;
    public static int currentUserId = -1;
    public static User[] users = new User[50];
    public static BankAccount[][] accounts = new BankAccount[50][10];
    public static List<Bank> banks = new ArrayList<>();
    public static Hashtable<BankAccount, List<Transaction>> transactions =
            new Hashtable<>();


    private static void load() {

        try(FileInputStream in = new FileInputStream(MyConstants.USER_FILE);
            ObjectInputStream s = new ObjectInputStream(in)) {
            users = (User[]) s.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(FileInputStream in = new FileInputStream(MyConstants.BANKS_FILE);
            ObjectInputStream s = new ObjectInputStream(in)) {
            banks = (ArrayList<Bank>) s.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(FileInputStream in = new FileInputStream(MyConstants.ACCOUNTS_FILE);
            ObjectInputStream s = new ObjectInputStream(in)) {
            accounts = (BankAccount[][]) s.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(FileInputStream in = new FileInputStream(MyConstants.TRANSACTIONS_FILE);
            ObjectInputStream s = new ObjectInputStream(in)) {
            transactions = (Hashtable<BankAccount, List<Transaction>>) s.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        for (User user : users) {
            if(user != null) {
                i++;
                userId=user.getId() + 1;
            }
        }
    }


    private static void save() {

        try(FileOutputStream f = new FileOutputStream(MyConstants.USER_FILE);
            ObjectOutput s = new ObjectOutputStream(f)) {
            s.writeObject(users);
            s.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try(FileOutputStream f = new FileOutputStream(MyConstants.BANKS_FILE);
            ObjectOutput s = new ObjectOutputStream(f)) {
            s.writeObject(banks);
            s.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try(FileOutputStream f = new FileOutputStream(MyConstants.ACCOUNTS_FILE);
            ObjectOutput s = new ObjectOutputStream(f)) {
            s.writeObject(accounts);
            s.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try(FileOutputStream f = new FileOutputStream(MyConstants.TRANSACTIONS_FILE);
            ObjectOutput s = new ObjectOutputStream(f)) {
            s.writeObject(transactions);

            s.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void admin() throws ParseException {

        do {
            Scanner scan = new Scanner(System.in);
            System.out.println(MyConstants.ADMIN_MENU);

            System.out.print(MyConstants.R);
            String adminChoice = scan.nextLine();

            switch(adminChoice){
                case "1":
                    addNewUser();
                    break;
                case "2":
                    viewUsers();
                    break;
                case "3":
                    addNewBank();
                    break;
                case "4":
                    viewBanks();
                    break;
                case "5":
                    addNewBankAccount();
                    break;
                case "6":
                    viewBankAccounts();
                    break;
                case "7":
                    deleteUser();
                    break;
                case "8":
                    deleteAccount();
                    break;
                case "9":
                    return;
                case "10":
                    save();
                    System.out.println(MyConstants.EXIT);
                    System.exit(0);
                    break;
                default :
                    System.out.println(MyConstants.INVALID_OPTION);
                    break;
            }
        }while (true);
    }


    private static void addNewUser() throws ParseException {

        System.out.println(MyConstants.ADD_USER);

        Scanner scanner = new Scanner(System.in);
        System.out.print(MyConstants.USERNAME);
        String username= scanner.nextLine();
        System.out.print(MyConstants.PASSWORD);
        String password= scanner.nextLine();
        System.out.print(MyConstants.LAST_NAME);
        String lastName= scanner.nextLine();
        System.out.print(MyConstants.FIRST_NAME);
        String firstName= scanner.nextLine();
        System.out.println(MyConstants.GENDER);
        System.out.print(MyConstants.R);
        int g= scanner.nextInt();
        scanner.nextLine();
        Gender gender;
        if(g == 1){
            gender = Gender.FEMALE;
        }
        else if(g == 2){
            gender = Gender.MALE;
        }
        else{
            gender = Gender.UNSPECIFIED;
        }
        System.out.print(MyConstants.BIRTH);
        String dateScan = scanner.nextLine();
        Date date = new SimpleDateFormat(MyConstants.PATTERN).parse(dateScan);
        System.out.print(MyConstants.CNP);
        String CNP= scanner.nextLine();
        System.out.print(MyConstants.IDSN);
        String ID= scanner.nextLine();
        System.out.print(MyConstants.ADDRESS);
        String address= scanner.nextLine();
        System.out.print(MyConstants.EMAIL);
        String email= scanner.nextLine();
        System.out.print(MyConstants.PHONE);
        String phoneNumber=scanner.nextLine();

        User u = new User( username, password, userId, lastName, firstName, gender, date, CNP, ID,
                           address, email, phoneNumber);

        users[i]=new User(u);
        i++;
    }


    private static void viewUsers() {

        System.out.println(MyConstants.VIEW_USER);

        for (User user : users) {
            if(user != null)
                System.out.println(user);
        }
    }


    private static void addNewBank() {

        System.out.println(MyConstants.ADD_BANK);

        Scanner scanner = new Scanner(System.in);
        System.out.print(MyConstants.ID);
        String id= scanner.nextLine();
        System.out.print(MyConstants.NAME);
        String name= scanner.nextLine();
        System.out.print(MyConstants.COUNTRY);
        String country= scanner.nextLine();

        Bank b = new Bank( id, name, country);

        banks.add(b);
    }


    private static void viewBanks() {

        System.out.println(MyConstants.VIEW_BANKS);

        for (Bank bank : banks)
            System.out.println(bank);
    }

    private static void addNewBankAccount() throws ParseException {

        System.out.println(MyConstants.ADD_BANK_ACCOUNT);

        Scanner scanner = new Scanner(System.in);
        System.out.println(MyConstants.CHOOSE_BANK);
        int iterator = 0;
        for (Bank bank: banks){
            System.out.println(" "+ iterator + "->" + bank.getName());
            iterator++;
        }
        System.out.print(MyConstants.R);
        int choice= scanner.nextInt();
        scanner.nextLine();
        Bank bank = banks.get(choice);
        System.out.println(MyConstants.ADD_USER_OPTIONS);
        System.out.print(MyConstants.R);
        choice = scanner.nextInt();
        scanner.nextLine();
        int id = 0;
        User accountOwner = new User();
        switch (choice){
            case 1:
                System.out.print(MyConstants.ID);
                id = scanner.nextInt();
                scanner.nextLine();
                accountOwner = new User(users[id]);
                break;
            case 2:
                System.out.println(MyConstants.CHOICES);
                iterator = 0;
                for (User u: users)
                    if(u!=null) {
                        System.out.println(iterator + "-> " + u.getUsername());
                        iterator++;
                    }
                System.out.print(MyConstants.R);
                id = scanner.nextInt();
                scanner.nextLine();
                accountOwner = new User(users[id]);
                break;
            default:
                System.out.println(MyConstants.INVALID_OPTION);
                break;
        }
        System.out.print(MyConstants.ACCOUNT_NUMBER);
        String accountNumber= scanner.nextLine();
        System.out.print(MyConstants.ACCOUNT_NAME);
        String accountName= scanner.nextLine();
        System.out.print(MyConstants.CARD_NUMBER);
        String cardNumber= scanner.nextLine();
        System.out.print(MyConstants.EXPIRY_DATE);
        String dateScan= scanner.nextLine();
        Date cardExpiryDate = new SimpleDateFormat(MyConstants.PATTERN).parse(dateScan);
        System.out.print(MyConstants.CVV);
        String CVV= scanner.nextLine();
        System.out.println(MyConstants.CURRENCY);
        System.out.print(MyConstants.R);
        String c= scanner.nextLine();
        Currency currency = null;
        do {
            if (c.equals("1")) {
                currency = Currency.RON;
            } else if (c.equals("2")){
                currency = Currency.EUR;
            } else if (c.equals("3")) {
                currency = Currency.USD;
            } else {
                System.out.println(MyConstants.INVALID_OPTION);
            }
        } while ( currency == null);

        System.out.println(MyConstants.INITIAL_BALANCE);
        double balance = scanner.nextDouble();
        scanner.nextLine();

        BankAccount ba = new BankAccount(bank,accountOwner, accountName, accountNumber,cardNumber,cardExpiryDate,CVV, balance, currency);

        for(iterator = 0; iterator<accounts[id].length; iterator ++){
            if(accounts[id][iterator] == null) {
                accounts[id][iterator] = ba;
                break;
            }
        }
    }


    private static void viewBankAccounts() {

        System.out.println(MyConstants.VIEW_ACCOUNTS);

        for (int i = 0; i<accounts.length; i++)
            for (int j = 0; j<accounts[0].length; j++){
                if(accounts[i][j] != null)
                    System.out.println(accounts[i][j]);
            }
    }


    private static void deleteUser() {
        System.out.println(MyConstants.DELETE_USER);
        System.out.println(MyConstants.CHOICES);

        Scanner scanner = new Scanner(System.in);
        int iterator = 0;
        for (User u: users)
            if(u!=null) {
                System.out.println(iterator + "-> " + u.getUsername());
                iterator++;
            }
        System.out.print(MyConstants.R);

        int id = scanner.nextInt();
        scanner.nextLine();
        for (int i = id; i < users.length - 1; i++) {
            users[i] = users[i + 1];
        }
        viewUsers();
        BankAccount[][] accountsCopy =  new BankAccount[50][10];
        int k=0;
        for (int i = 0; i<accounts.length; i++){
                if(i!=id){
                    accountsCopy[k]=accounts[i];
                }
            k++;
        }
        accounts = accountsCopy;
        viewBankAccounts();

    }


    private static void deleteAccount() {
        System.out.println(MyConstants.DELETE_ACCOUNT);
        System.out.println(MyConstants.CHOOSE_OWNER);

        Scanner scanner = new Scanner(System.in);
        int iterator = 0;
        for (User u: users)
            if(u!=null) {
                System.out.println(iterator + "-> " + u.getUsername());
                iterator++;
            }
        System.out.print(MyConstants.R);

        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.println(MyConstants.CHOOSE_ACCOUNT);
        for (int i = 0; i<accounts[userId].length; i++){
          if(accounts[userId][i]!=null){
              System.out.println(" " + i + "-> " + accounts[userId][i].getName()+ " - " + accounts[userId][i].getAccountNumber() );
          }
        }
        int accountId = scanner.nextInt();
        scanner.nextLine();
        for (int i = accountId; i<accounts[userId].length-1; i++){
            accounts[userId][i] = accounts[userId][i+1];
        }
        viewBankAccounts();
    }


    public static void client(){
        Scanner scan = new Scanner(System.in);
        boolean correctUser = false;
        String correctPassword = null;
        int id = 0;
        System.out.print(MyConstants.USERNAME);
        String username = scan.nextLine();
        for(int i=0; i<users.length; i++){
            if(users[i] != null && users[i] .getUsername().equals(username)) {
                correctUser = true;
                id = i;
                correctPassword = users[i] .getPassword();
            }
        }
        if(correctUser){
            System.out.print(MyConstants.PASSWORD);
            String password = scan.nextLine();
            if(password.equals(correctPassword)){
                currentUserId=id;
                do {
                    System.out.println(MyConstants.CLIENT_MENU);

                    System.out.print(MyConstants.R);
                    String clientChoice = scan.nextLine();

                    switch(clientChoice){
                        case "1":
                            seeBalance();
                            break;
                        case "2":
                            addTransaction();
                            break;
                        case "3":
                            seeTransactions();
                            break;
                        case "4":
                            addMoney();
                            break;
                        case "5":
                            withdrawMoney();
                            break;
                        case "6":
                            createAccount();
                            break;
                        case "7":
                            seeStatistics();
                            break;
                        case "8":
                            return;
                        case "9":
                            save();
                            System.out.println(MyConstants.EXIT);
                            System.exit(0);
                            break;
                        default :
                            System.out.println(MyConstants.INVALID_OPTION);
                            break;
                    }
                }while (true);
            }
            else{
                System.out.println(MyConstants.WRONG_PASSWORD);
            }
        }
        else System.out.println(MyConstants.NO_USER);
    }


    private static void seeBalance() {
    }


    private static void addTransaction() {

        System.out.println(MyConstants.ADD_TRANSACTION);
        Scanner scanner = new Scanner(System.in);

        System.out.println(MyConstants.CHOOSE_TYPE);
        System.out.print(MyConstants.R);
        String t = scanner.nextLine();
        Type type = null;
        do {
            switch (t) {
                case "1":
                    type = Type.SEND;
                    break;
                case "2":
                    type = Type.RECEIVE;
                    break;
                default:
                    System.out.println(MyConstants.INVALID_OPTION);
                    break;
            }
        } while (type == null);

        System.out.println(MyConstants.CHOOSE_YOUR_ACCOUNT);
        for (int iterator = 0; iterator < accounts[currentUserId].length; iterator ++){
            if(accounts[currentUserId][iterator] != null){
                System.out.println(" " + iterator + "-> " + accounts[currentUserId][iterator].getName() + " - " +
                        accounts[currentUserId][iterator].getAccountNumber() + ": " +
                        accounts[currentUserId][iterator].getBalance() + accounts[currentUserId][iterator].getCurrency());
            }
        }
        System.out.print(MyConstants.R);
        int myA = scanner.nextInt();
        scanner.nextLine();
        BankAccount myAccount = accounts[currentUserId][myA];

        BankAccount otherAccount = new BankAccount();
        boolean okAccount = false;
        String secondAccount;
        int otherI = 0;
        int otherJ = 0;
        do{
            System.out.print(MyConstants.INTRODUCE_OTHER_ACCOUNT);
            secondAccount = scanner.nextLine();

            for(int i=0; i<accounts.length;i++){
                for(int j=0; j<accounts[i].length; j++){
                    if(accounts[i][j] != null && secondAccount.equals(accounts[i][j].getAccountNumber()))
                    {
                        okAccount = true;
                        otherAccount = accounts[i][j];
                        otherI = i;
                        otherJ = j;
                        break;
                    }
                }
                if(okAccount){
                    break;
                }
            }
            if(!okAccount){
                System.out.println(MyConstants.WRONG_ACCOUNT);

            }
        }while (!okAccount);

        System.out.print(MyConstants.AMOUNT);
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println(MyConstants.CHOOSE_CATEGORY);
        System.out.print(MyConstants.R);
        String c = scanner.nextLine();
        Category category = null;
        do {
            switch (c) {
                case "1":
                    category = Category.BILLS;
                    break;
                case "2":
                    category = Category.GROCERIES;
                    break;
                case "3":
                    category = Category.SHOPPING;
                    break;
                case "4":
                    category = Category.TRANSPORT;
                    break;
                case "5":
                    category = Category.HEALTH;
                    break;
                case "6":
                    category = Category.ENTERTAINMENT;
                    break;
                case "7":
                    category = Category.OTHER;
                    break;
                default:
                    System.out.println(MyConstants.INVALID_OPTION);
                    break;
            }
        } while (category==null);

        System.out.print(MyConstants.DETAILS);
        String details = scanner.nextLine();

        String tryAgain = "1";

        int receiverI, senderI, receiverJ, senderJ;
        double sentAmount = 0;
        double receivedAmount = 0;
        BankAccount receiver;
        BankAccount sender;
        Transaction receiverTransaction;
        Transaction senderTransaction;
        List<Transaction> receiverTransactions = new ArrayList<>();
        List<Transaction> senderTransactions = new ArrayList<>();

        while(tryAgain.equals("1")){
            boolean ok;
            if(type == Type.RECEIVE){
                receiverI = currentUserId;
                receiverJ = myA;
                senderI = otherI;
                senderJ = otherJ;
                receiver = myAccount;
                sender = otherAccount;
                receivedAmount = amount;
                sentAmount = sender.convert(amount,receiver.getCurrency());
                System.out.println("SENT AMOUNT: " + sentAmount);
            }
            else {
                receiverI = otherI;
                receiverJ = otherJ;
                senderI = currentUserId;
                senderJ = myA;
                receiver = otherAccount;
                sender = myAccount;
                sentAmount = amount;
                receivedAmount = receiver.convert(amount,sender.getCurrency());
                System.out.println("RECEIVED AMOUNT: " + receivedAmount);
            }

            ok = sender.verifyBalance(sentAmount);

            if(ok){
                tryAgain = "0";

                accounts[senderI][senderJ].changeBalance(sentAmount, type.SEND);
                accounts[receiverI][receiverJ].changeBalance(receivedAmount, type.RECEIVE);

                receiverTransaction = new Transaction(secondAccount, type.RECEIVE, category, receivedAmount, details);
                senderTransaction = new Transaction(myAccount.getAccountNumber(), Type.SEND, category, sentAmount, details);

                if(transactions.containsKey(receiver)){
                    receiverTransactions = transactions.get(receiver);
                    transactions.remove(receiver);
                }
                receiverTransactions.add(receiverTransaction);
                transactions.put(accounts[receiverI][receiverJ],receiverTransactions);

                if(transactions.containsKey(sender)){
                    senderTransactions = transactions.get(sender);
                    transactions.remove(sender);
                }
                senderTransactions.add(senderTransaction);
                transactions.put(accounts[senderI][senderJ],senderTransactions);
            }
            else {
                System.out.println(MyConstants.TRY_AGAIN);
                tryAgain = scanner.nextLine();
                if(tryAgain.equals("1")){
                    System.out.println(MyConstants.AMOUNT);
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                }
            }
        }

    }


    private static void seeTransactions() {
        System.out.println(MyConstants.SEE_TRANSACTIONS);
        Set<BankAccount> bankAccounts = transactions.keySet();
        for(BankAccount ba: bankAccounts){
            if(ba.getAccountOwner().getUsername().equals(users[currentUserId].getUsername()))
                System.out.println(ba.getAccountNumber() + " - " + ba.getName() + ": " + ba.getBalance() + " " +
                        ba.getCurrency());
                for(Transaction t:transactions.get(ba))
                {
                    if(t.getType().equals(Type.SEND)){
                        System.out.println(MyConstants.RED + "---"+ t + MyConstants.RESET);
                    }
                    else{
                        System.out.println(MyConstants.GREEN+ "---"+ t + MyConstants.RESET);
                    }

                }
        }
    }


    private static void addMoney() {
    }


    private static void withdrawMoney() {
    }


    private static void createAccount() {
    }


    private static void seeStatistics() {
    }


    public static void main(String[] args) throws ParseException {

        Scanner scan = new Scanner(System.in);

        //verify if file exists

        load();

        while(true) {
            System.out.println(MyConstants.E_BANKING);
            System.out.println(MyConstants.MAIN_MENU);

            System.out.print(MyConstants.R);
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    System.out.print(MyConstants.PASSWORD);
                    String password = scan.nextLine();
                    if (password.equals(MyConstants.ADMIN_PASSWORD)) {
                        admin();
                    } else {
                        System.out.println(MyConstants.WRONG_PASSWORD);
                    }
                    break;
                case "2":
                    client();
                    break;
                case "3":
                    save();
                    System.out.println(MyConstants.EXIT);
                    System.exit(0);
                    break;
                default :
                    System.out.println(MyConstants.INVALID_OPTION);
                    break;

            }
        }


    }
}

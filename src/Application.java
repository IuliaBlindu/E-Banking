import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.text.DecimalFormat;
import java.util.concurrent.ConcurrentHashMap;

//NICE TO HAVE
// -- bold la tranzactii
// -- exit wherever
// -- delete transactions too


public class Application implements Serializable {

    public static int i = 0;
    public static int userId = 0;
    public static int currentUserId = -1;
    public static User[] users = new User[50];
    public static BankAccount[][] accounts = new BankAccount[50][10];
    public static List<Bank> banks = new ArrayList<>();
    public static ConcurrentHashMap<BankAccount, List<Transaction>> transactions =  new ConcurrentHashMap<>();

    private static final DecimalFormat df2 = new DecimalFormat("#.##");

    /**
     * function to load all data from files in their specific data structures
     * there is one file for each category of data saved: accounts, banks, transactions
     * and users
     */
    private static void load() {

        try(FileInputStream in = new FileInputStream(MyConstants.USER_FILE);
            ObjectInputStream s = new ObjectInputStream(in)) {
            users = (User[]) s.readObject();
        } catch (IOException e) {
            System.out.println(MyConstants.IO_EXCEPTION + e);
        } catch (ClassNotFoundException e) {
            System.out.println(MyConstants.CLASS_NOT_FOUND_EXCEPTION + e);
        } catch (ClassCastException e) {
            System.out.println(MyConstants.CLASS_CAST_EXCEPTION + e);
        }

        try(FileInputStream in = new FileInputStream(MyConstants.BANKS_FILE);
            ObjectInputStream s = new ObjectInputStream(in)) {
            banks = (ArrayList<Bank>) s.readObject();
        } catch (IOException  e) {
            System.out.println(MyConstants.IO_EXCEPTION + e);
        } catch (ClassNotFoundException e) {
            System.out.println(MyConstants.CLASS_NOT_FOUND_EXCEPTION + e);
        } catch (ClassCastException e) {
            System.out.println(MyConstants.CLASS_CAST_EXCEPTION + e);
        }

        try(FileInputStream in = new FileInputStream(MyConstants.ACCOUNTS_FILE);
            ObjectInputStream s = new ObjectInputStream(in)) {
            accounts = (BankAccount[][]) s.readObject();
        } catch (IOException  e) {
            System.out.println(MyConstants.IO_EXCEPTION + e);
        } catch (ClassNotFoundException e) {
            System.out.println(MyConstants.CLASS_NOT_FOUND_EXCEPTION + e);
        } catch (ClassCastException e) {
            System.out.println(MyConstants.CLASS_CAST_EXCEPTION + e);
        }

        try(FileInputStream in = new FileInputStream(MyConstants.TRANSACTIONS_FILE);
            ObjectInputStream s = new ObjectInputStream(in)) {
            transactions = (ConcurrentHashMap<BankAccount, List<Transaction>>) s.readObject();
        } catch (IOException  e) {
            System.out.println(MyConstants.IO_EXCEPTION + e);
        } catch (ClassNotFoundException e) {
            System.out.println(MyConstants.CLASS_NOT_FOUND_EXCEPTION + e);
        } catch (ClassCastException e) {
            System.out.println(MyConstants.CLASS_CAST_EXCEPTION + e);
        }

        for (User user : users) {
            if(user != null) {
                i++;
                userId=user.getId() + 1;
            }
        }
    }

    /**
     * function to save all data from  specific data structures into their files
     * there is one file for each category of data saved: accounts, banks, transactions
     * and users
     */
    private static void save() {

        try(FileOutputStream f = new FileOutputStream(MyConstants.USER_FILE);
            ObjectOutput s = new ObjectOutputStream(f)) {
            s.writeObject(users);
            s.flush();
        } catch (IOException  e) {
            System.out.println(MyConstants.IO_EXCEPTION + e);
        }

        try(FileOutputStream f = new FileOutputStream(MyConstants.BANKS_FILE);
            ObjectOutput s = new ObjectOutputStream(f)) {
            s.writeObject(banks);
            s.flush();
        } catch (IOException  e) {
            System.out.println(MyConstants.IO_EXCEPTION + e);
        }
        
        try(FileOutputStream f = new FileOutputStream(MyConstants.ACCOUNTS_FILE);
            ObjectOutput s = new ObjectOutputStream(f)) {
            s.writeObject(accounts);
            s.flush();
        } catch (IOException  e) {
            System.out.println(MyConstants.IO_EXCEPTION + e);
        }
        
        try(FileOutputStream f = new FileOutputStream(MyConstants.TRANSACTIONS_FILE);
            ObjectOutput s = new ObjectOutputStream(f)) {
            s.writeObject(transactions);
            s.flush();
        } catch (IOException  e) {
            System.out.println(MyConstants.IO_EXCEPTION + e);
        }

    }

    /**
     * function that generates the menu for admin role and calls the function
     * that does what user asked
     */
    public static void admin(){

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
                    addNewBankAccount("admin");
                    break;
                case "6":
                    viewBankAccounts();
                    break;
                case "7":
                    deleteUser();
                    break;
                case "8":
                    deleteBankAccount();
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

    /**
     * function that generates the menu for client role and calls the function
     * that does what user asked
     */
    public static void client() {
        Scanner scan = new Scanner(System.in);
        boolean correctUser = false;
        String correctPassword = null;
        int id = 0;
        System.out.print(MyConstants.USERNAME);
        String username = scan.nextLine();

        //verify the user is correct
        for(int i=0; i<users.length; i++){
            if(users[i] != null && users[i].getUsername().equals(username)) {
                correctUser = true;
                id = i;
                correctPassword = users[i] .getPassword();
            }
        }
        //verify the password is correct and generate the menu
        if(correctUser){
            System.out.print(MyConstants.PASSWORD);
            String password = scan.nextLine();
            if(password.equals(correctPassword)){
                currentUserId=id;
                users[currentUserId].welcome(username);
                do {
                    System.out.println(MyConstants.CLIENT_MENU);

                    System.out.print(MyConstants.R);
                    String clientChoice = scan.nextLine();

                    switch(clientChoice){
                        case "1":
                            seeTransactions();
                            break;
                        case "2":
                            addTransaction();
                            break;
                        case "3":
                            changeBalance("add");
                            break;
                        case "4":
                            changeBalance("withdraw");
                            break;
                        case "5":
                            addNewBankAccount("client");
                            break;
                        case "6":
                            return;
                        case "7":
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

    /**
     * function to add a new user
     */
    private static void addNewUser() {

        System.out.println(MyConstants.ADD_USER);

        //get username and verify if it already exists
        Scanner scanner = new Scanner(System.in);
        boolean usernameOk;
        String username;
        do {
            System.out.print(MyConstants.USERNAME);
            username = scanner.nextLine();
            usernameOk = true;
            for (User user : users) {
                if (user!= null && user.getUsername().equals(username)) {
                    usernameOk = false;
                    break;
                }
            }
            if(!usernameOk){
                System.out.println(MyConstants.USER_EXISTS);
            }
        }while(!usernameOk);

        System.out.print(MyConstants.PASSWORD);
        String password= scanner.nextLine();
        System.out.print(MyConstants.LAST_NAME);
        String lastName= scanner.nextLine();
        System.out.print(MyConstants.FIRST_NAME);
        String firstName= scanner.nextLine();
        System.out.println(MyConstants.GENDER);
        System.out.print(MyConstants.R);


        String g= scanner.nextLine();
        Gender gender;
        if(g.equals("1")){
            gender = Gender.FEMALE;
        }
        else if(g.equals("2")){
            gender = Gender.MALE;
        }
        else{
            gender = Gender.UNSPECIFIED;
        }

        String dateScan;
        Date date = null;

        //verify the date was introduced correctly: format and age properly
        do {
            System.out.print(MyConstants.BIRTH);
            dateScan = scanner.nextLine();
            try {
                date = new SimpleDateFormat(MyConstants.PATTERN).parse(dateScan);
            } catch (ParseException e) {
                System.out.println(MyConstants.INCORRECT_DATE);
            }
            if(date != null){
                try {
                    validateAge(date);
                }
                catch (InvalidAgeException e){
                    System.out.println(e.getMessage());
                    date = null;
                }
            }
        }while (date == null);

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

        //create new object and add it to data
        User u = new User( username, password, userId, lastName, firstName, gender, date, CNP, ID,
                           address, email, phoneNumber);

        users[i]=new User(u);
        i++;
    }

    /**
     * function to see the list of users
     */
    private static void viewUsers() {

        System.out.println(MyConstants.VIEW_USER);
        for (User user : users) {
            if(user != null){
                System.out.println(user);
            }
        }
    }

    /**
     * function to add a new bank
     */
    private static void addNewBank() {

        System.out.println(MyConstants.ADD_BANK);

        //verify if bank id already exists
        Scanner scanner = new Scanner(System.in);
        boolean idOk;
        String tryagain;
        String id;
        do {
            System.out.print(MyConstants.ID);
            id= scanner.nextLine();
            idOk = true;
            for (Bank bank : banks) {
                if (bank.getId().equals(id)) {
                    idOk = false;
                    break;
                }
            }
            if(!idOk){
                //check if users wants to continue his action
                System.out.println(MyConstants.ID_EXISTS);
                System.out.println(MyConstants.TRY_AGAIN);
                tryagain = scanner.nextLine();
                if("0".equals(tryagain)){
                    return;
                }
            }
        }while(!idOk);

        //verify if bank name already exists
        boolean nameOk;
        String name;
        do {
            System.out.print(MyConstants.NAME);
            name= scanner.nextLine();
            nameOk = true;
            for (Bank bank : banks) {
                if (bank.getName().equals(name)) {
                    nameOk = false;
                    break;
                }
            }
            if(!nameOk){

                //check if users wants to continue his action
                System.out.println(MyConstants.BANK_EXISTS);
                System.out.println(MyConstants.TRY_AGAIN);
                tryagain = scanner.nextLine();
                if(tryagain.equals("0")){
                    return;
                }
            }
        }while(!nameOk);

        System.out.print(MyConstants.COUNTRY);
        String country= scanner.nextLine();

        Bank b = new Bank( id, name, country);

        banks.add(b);
    }

    /**
     * function view all banks
     */
    private static void viewBanks() {

        System.out.println(MyConstants.VIEW_BANKS);

        for (Bank bank : banks)
            System.out.println(bank);
    }

    /**
     * function to add a bank account
     * @param  role  role of user(admin, client) since admin can create accounts for any user
     *               while user just for himself
     */
    private static void addNewBankAccount(String role) {

        System.out.println(MyConstants.ADD_BANK_ACCOUNT);

        Scanner scanner = new Scanner(System.in);

        //generate list of banks
        System.out.println(MyConstants.CHOOSE_BANK);
        int iterator = 0;
        for (Bank bank: banks){
            System.out.println(" "+ iterator + "->" + bank.getName());
            iterator++;
        }

        boolean okInt;
        boolean okIndex;
        int bankChoice = 0;
        Bank bank = null;

        //verify if user input is correct
        do {
            okIndex = true;
            do {
                okInt = true;
                System.out.print(MyConstants.R);
                try {
                    bankChoice = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println(MyConstants.WRONG_INPUT);
                    okInt = false;
                }
                scanner.nextLine();
            } while (!okInt);
            try{
                bank = banks.get(bankChoice);
            }catch (IndexOutOfBoundsException e) {
                System.out.println(MyConstants.WRONG_INPUT);
                okIndex=false;
            }
        }while(!okIndex);

        User accountOwner = new User();
        int id = 0;

        //verify the role of user to decide who the accountOwner is
        if(role.equals("admin")){

            //let admin decide if it wants to write the id of user or select
            System.out.println(MyConstants.ADD_USER_OPTIONS);
            boolean okChoice;
            do {
                okChoice = true;
                System.out.print(MyConstants.R);
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        do {
                            okIndex = true;

                            //verify user input to pe in correct format
                            do {
                                okInt = true;
                                System.out.print(MyConstants.ID);
                                try {
                                    id = scanner.nextInt();
                                } catch (Exception e) {
                                    System.out.println(MyConstants.WRONG_INPUT);
                                    okInt = false;
                                }
                                scanner.nextLine();
                            } while (!okInt);

                            //verify if user exists
                            try {
                                accountOwner = new User(users[id]);
                            } catch (IndexOutOfBoundsException | NullPointerException e) {
                                System.out.println(MyConstants.USER_NOT_EXIST);
                                okIndex = false;
                            }
                        } while (!okIndex);
                        break;

                    case "2":
                        System.out.println(MyConstants.CHOICES);
                        iterator = 0;

                        //generate list of users to choose from
                        for (User u : users)
                            if (u != null) {
                                System.out.println(iterator + "-> " + u.getUsername());
                                iterator++;
                            }
                        do {


                            //verify user input to pe in correct format
                            do {
                                okInt = true;
                                System.out.print(MyConstants.R);
                                try {
                                    id = scanner.nextInt();
                                } catch (Exception e) {
                                    System.out.println(MyConstants.WRONG_INPUT);
                                    okInt = false;
                                }
                                scanner.nextLine();
                            } while (!okInt);

                            //verify if user exists

                            try {
                                accountOwner = new User(users[id]);
                            } catch (IndexOutOfBoundsException | NullPointerException e) {
                                System.out.println(MyConstants.WRONG_INPUT);
                                okIndex = false;
                            }
                        } while (!okIndex);
                        break;
                    default:
                        System.out.println(MyConstants.INVALID_OPTION);
                        okChoice = false;
                        break;
                }
            }while(!okChoice);
        }
        else {
            accountOwner =  new User(users[currentUserId]);
            id = currentUserId;
        }

        System.out.print(MyConstants.ACCOUNT_NAME);
        String accountName= scanner.nextLine();


        String c;
        Currency currency = null;
        do {
            System.out.println(MyConstants.CURRENCY);
            System.out.print(MyConstants.R);
            c= scanner.nextLine();
            switch (c) {
                case "1":
                    currency = Currency.RON;
                    break;
                case "2":
                    currency = Currency.EUR;
                    break;
                case "3":
                    currency = Currency.USD;
                    break;
                default:
                    System.out.println(MyConstants.INVALID_OPTION);
                    break;
            }
        } while ( currency == null);

        boolean okDouble;
        double balance = 0;

        //just admin can put an initial balance

        if(role.equals("admin")) {
            do {
                okDouble = true;
                System.out.print(MyConstants.INITIAL_BALANCE);
                try {
                    balance = scanner.nextDouble();
                } catch (Exception e) {
                    System.out.println(MyConstants.WRONG_INPUT);
                    okDouble = false;
                }
                scanner.nextLine();
            }while(!okDouble);
        }

        String accountNumber;
        String cardNumber;

        boolean ok;

        //generate a unique account number

        do {
            accountNumber = generateRandomDigits(10);
            ok = true;
            for (int i = 0; i<accounts.length; i++){
                for (int j = 0; j<accounts[0].length; j++){
                    if(accounts[i][j] != null && accounts[i][j].getAccountNumber().equals(accountNumber)) {
                        ok = false;
                        break;
                    }
                }
            }
        }while(!ok);

        //generate a unique card number
        do {
            cardNumber = generateRandomDigits(10);
            ok = true;
            for (int i = 0; i<accounts.length; i++)
                for (int j = 0; j<accounts[0].length; j++){
                    if(accounts[i][j] != null && accounts[i][j].getCardNumber().equals(cardNumber)){
                        ok = false;
                        break;
                    }
                }
        }while(!ok);

        //generate CVV
        String CVV = generateRandomDigits(3);

        //set date to be after four years from current date
        Date date = new Date(System.currentTimeMillis());
        Calendar cDate = Calendar.getInstance();
        cDate.setTime(date);
        cDate.add(Calendar.YEAR, 4);
        Date cardExpiryDate = cDate.getTime();

        //generate object and add it to data
        BankAccount ba = new BankAccount(bank,accountOwner, accountName, accountNumber,cardNumber,cardExpiryDate,CVV, balance, currency);

        for(iterator = 0; iterator<accounts[id].length; iterator ++){
            if(accounts[id][iterator] == null) {
                accounts[id][iterator] = ba;
                break;
            }
        }

        //initialize transactions entry for this bank account
        List<Transaction> transaction = new ArrayList<>();
        transactions.put(ba,transaction);
    }

    /**
     * function to view all bank accounts
     */
    private static void viewBankAccounts() {

        System.out.println(MyConstants.VIEW_ACCOUNTS);

        for (int i = 0; i<accounts.length; i++) {
            for (int j = 0; j < accounts[0].length; j++) {
                if (accounts[i][j] != null){
                    System.out.println(accounts[i][j]);
                }
            }
        }
    }

    /**
     * function to delete an user
     */
    private static void deleteUser() {
        System.out.println(MyConstants.DELETE_USER);
        System.out.println(MyConstants.CHOICES);

        Scanner scanner = new Scanner(System.in);
        int iterator = 0;

        //generate list of users to choose from
        for (User u: users)
            if(u!=null) {
                System.out.println(iterator + "-> " + u.getUsername());
                iterator++;
            }
        boolean okIndex;
        boolean okInt;
        int id = 0;
        do {
            okIndex = true;
            //verify user input
            do {
                okInt = true;
                System.out.print(MyConstants.R);
                try {
                    id = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println(MyConstants.WRONG_INPUT);
                    okInt = false;
                }
                scanner.nextLine();
            } while (!okInt);

            //verify user exists
            if(id>(iterator-1)){
                System.out.println(MyConstants.USER_NOT_EXIST);
                okIndex=false;
            }
        }while(!okIndex);

        //delete user from data
        for (int i = id; i < users.length - 1; i++) {
            users[i] = users[i + 1];
        }

       //delete user's accounts too
        BankAccount[][] accountsCopy =  new BankAccount[50][10];
        int k=0;
        for (int i = 0; i<accounts.length; i++){
                if(i!=id){
                    accountsCopy[k]=accounts[i];
                    k++;
                }

        }
        accounts = accountsCopy;

        viewUsers();
        viewBankAccounts();

    }

    /**
     * function to delete a bank account
     */
    private static void deleteBankAccount() {
        System.out.println(MyConstants.DELETE_ACCOUNT);
        System.out.println(MyConstants.CHOOSE_OWNER);

        // choose user
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
        iterator = 0;
        // generate list of accounts
        for (int i = 0; i<accounts[userId].length; i++){
          if(accounts[userId][i]!=null){
              System.out.println(" " + i + "-> " + accounts[userId][i].getName()+ " - " + accounts[userId][i].getAccountNumber() );
              iterator++;
          }
        }

        // verify if user has accounts
        if (iterator == 0){
            System.out.println(MyConstants.NO_ACCOUNTS);
        }
        else {
            int accountId = scanner.nextInt();
            scanner.nextLine();
            for (int i = accountId; i<accounts[userId].length-1; i++){
                accounts[userId][i] = accounts[userId][i+1];
            }
            viewBankAccounts();
        }

    }

    /**
     * function to see client's accounts with its transactions
     */
    private static void seeTransactions() {
        System.out.println(MyConstants.SEE_TRANSACTIONS);
        Set<BankAccount> bankAccounts = transactions.keySet();
        for(BankAccount ba: bankAccounts){
            if(ba.getAccountOwner().getUsername().equals(users[currentUserId].getUsername())) {
                System.out.println(ba.getAccountNumber() + " - " + ba.getName() + ": " + df2.format(ba.getBalance()) + " " +
                        ba.getCurrency());
                for (Transaction t : transactions.get(ba)) {
                    if (t.getType().equals(Type.SEND)) {
                        System.out.println(MyConstants.RED + "---" + t + MyConstants.RESET);
                    } else {
                        System.out.println(MyConstants.BLUE + "---" + t + MyConstants.RESET);
                    }

                }
                System.out.println(MyConstants.SEPARATOR);
            }
        }
    }

    /**
     * function to add a new transaction
     */
    private static void addTransaction() {

        System.out.println(MyConstants.ADD_TRANSACTION);
        Scanner scanner = new Scanner(System.in);

        System.out.println(MyConstants.CHOOSE_TYPE);

        String t;
        Type type = null;
        do {
            System.out.print(MyConstants.R);
            t = scanner.nextLine();
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


        //choose account from which the transaction should be made
        System.out.println(MyConstants.CHOOSE_YOUR_ACCOUNT);
        for (int iterator = 0; iterator < accounts[currentUserId].length; iterator ++){
            if(accounts[currentUserId][iterator] != null){
                System.out.println(" " + iterator + "-> " + accounts[currentUserId][iterator].getName() + " - " +
                        accounts[currentUserId][iterator].getAccountNumber() + ": " +
                        df2.format(accounts[currentUserId][iterator].getBalance()) + " " + accounts[currentUserId][iterator].getCurrency());
            }
        }

        boolean okInt;
        boolean okIndex;
        int myA = 0;
        BankAccount myAccount = null;
        do {
            okIndex = true;

            //verify user input to be in the correct format
            do {
                okInt = true;
                System.out.print(MyConstants.R);
                try {
                    myA = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println(MyConstants.WRONG_INPUT);
                    okInt = false;
                }
                scanner.nextLine();
            } while (!okInt);

            //verify the account exists
            try {
                myAccount = accounts[currentUserId][myA];
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                System.out.println(MyConstants.WRONG_INPUT);
                okIndex = false;
            }
        }while(!okIndex);

        BankAccount otherAccount = new BankAccount();
        boolean ok = false;
        String secondAccount;
        int otherI = 0;
        int otherJ = 0;
        do{
            System.out.print(MyConstants.INTRODUCE_OTHER_ACCOUNT);
            secondAccount = scanner.nextLine();

            //verify the other account exists
            for(int i=0; i<accounts.length;i++){
                for(int j=0; j<accounts[i].length; j++){
                    if(accounts[i][j] != null && secondAccount.equals(accounts[i][j].getAccountNumber()))
                    {
                        ok = true;
                        otherAccount = accounts[i][j];
                        otherI = i;
                        otherJ = j;
                        break;
                    }
                }
                if(ok){
                    break;
                }
            }
            if(!ok){
                System.out.println(MyConstants.WRONG_ACCOUNT);
                System.out.println(MyConstants.TRY_AGAIN);
                String tryAgain = scanner.nextLine();
                if(tryAgain.equals("0")){
                    return;
                }
            }

        }while (!ok);


        double amount=0;
        boolean okDouble;

        //verify user input to be in the correct format
        do {
            okDouble = true;
            System.out.print(MyConstants.AMOUNT);
            try {
                amount = scanner.nextDouble();
            } catch (Exception e) {
                System.out.println(MyConstants.WRONG_INPUT);
                okDouble = false;
            }
            scanner.nextLine();
        }while(!okDouble);


        System.out.println(MyConstants.CHOOSE_CATEGORY);
        String c;
        Category category = null;
        do {
            System.out.print(MyConstants.R);
            c = scanner.nextLine();
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


        int receiverI, senderI, receiverJ, senderJ;
        double sentAmount;
        double receivedAmount;
        BankAccount receiver;
        BankAccount sender;
        Transaction receiverTransaction;
        Transaction senderTransaction;
        List<Transaction> receiverTransactions = new ArrayList<>();
        List<Transaction> senderTransactions = new ArrayList<>();

        String tryAgain = "1";
        while(tryAgain.equals("1")){

            //determine the receiver and sender
            if(type == Type.RECEIVE){
                receiverI = currentUserId;
                receiverJ = myA;
                senderI = otherI;
                senderJ = otherJ;
                receiver = new BankAccount(myAccount);
                sender = new BankAccount(otherAccount);
                receivedAmount = amount;
                //convert amount to sender currency
                sentAmount = sender.convert(amount,receiver.getCurrency());
                System.out.println("SENT AMOUNT: " + sentAmount);
            }
            else {
                receiverI = otherI;
                receiverJ = otherJ;
                senderI = currentUserId;
                senderJ = myA;
                receiver = new BankAccount(otherAccount);
                sender = new BankAccount(myAccount);
                sentAmount = amount;
                //convert amount to receiver currency
                receivedAmount = receiver.convert(amount,sender.getCurrency());
            }

            //verify sender has enough money
            ok = sender.verifyBalance(sentAmount);


            if(ok){
                tryAgain = "0";

                Set<BankAccount> bankAccounts = transactions.keySet();
                BankAccount senderBA = null;
                BankAccount receiverBA = null;

                //verify if transactions for this accounts already exists in order to add
                //a new transaction to list
                for(BankAccount ba: bankAccounts)
                {
                    if(ba.getAccountNumber().equals(sender.getAccountNumber())){
                        senderTransactions = transactions.get(ba);
                        senderBA = ba;
                    }
                    else if(ba.getAccountNumber().equals(receiver.getAccountNumber())){
                        receiverTransactions = transactions.get(ba);
                        receiverBA = ba;
                    }
                }
                if(senderBA != null){
                    transactions.remove(senderBA);
                }
                if(receiverBA != null){
                    transactions.remove(receiverBA);
                }

                accounts[senderI][senderJ].changeBalance(sentAmount, Type.SEND);
                accounts[receiverI][receiverJ].changeBalance(receivedAmount, Type.RECEIVE);

                receiverTransaction = new Transaction(secondAccount, Type.RECEIVE, category, receivedAmount, details);
                senderTransaction = new Transaction(myAccount.getAccountNumber(), Type.SEND, category, sentAmount, details);

                receiverTransactions.add(receiverTransaction);
                transactions.put(accounts[receiverI][receiverJ],receiverTransactions);

                senderTransactions.add(senderTransaction);
                transactions.put(accounts[senderI][senderJ],senderTransactions);
            }
            else {
                //ask user if he wants to add another amount if sender does not have enough money
                System.out.println(MyConstants.NOT_ENOUGH_MONEY);
                System.out.println(MyConstants.TRY_AGAIN);
                tryAgain = scanner.nextLine();
                if(tryAgain.equals("1")){
                    do {
                        okDouble = true;
                        System.out.print(MyConstants.AMOUNT);
                        try {
                            amount = scanner.nextDouble();
                        } catch (Exception e) {
                            System.out.println(MyConstants.WRONG_INPUT);
                            okDouble = false;
                        }
                        scanner.nextLine();
                    }while(!okDouble);
                }
            }
        }

    }

    /**
     * function to add or withdraw money from account
     * @param   method - add or witdraw money
     */
    private static void changeBalance(String method) {

        Scanner scanner = new Scanner(System.in);
        if (method.equals("add")){
            System.out.println(MyConstants.ADD_MONEY);
        }
        else {
            System.out.println(MyConstants.WITHDRAW_MONEY);
        }
        System.out.println(MyConstants.CHOOSE_YOUR_ACCOUNT);

        int counter = 0;
        // generate list of accounts
        for (int iterator = 0; iterator < accounts[currentUserId].length; iterator ++){
            if(accounts[currentUserId][iterator] != null){
                System.out.println(" " + iterator + "-> " + accounts[currentUserId][iterator].getName() + " - " +
                        accounts[currentUserId][iterator].getAccountNumber() + ": " +
                        df2.format(accounts[currentUserId][iterator].getBalance())  + " " + accounts[currentUserId][iterator].getCurrency());
                counter ++;
            }
        }

        boolean okInt;
        boolean okIndex;
        int myA = 0;
        do {
            okIndex = true;
            do {
                //verify user input is in correct format
                okInt = true;
                System.out.print(MyConstants.R);
                try {
                    myA = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println(MyConstants.WRONG_INPUT);
                    okInt = false;
                }
                scanner.nextLine();
            } while (!okInt);
            // verify account exists
            if(myA>(counter-1)) {
                System.out.println(MyConstants.WRONG_INPUT);
                okIndex=false;
            }
        }while(!okIndex);

        double amount=0;
        boolean okDouble;
        //verify user input is in correct format
        do {
            okDouble = true;
            System.out.print(MyConstants.AMOUNT);
            try {
                amount = scanner.nextDouble();
            } catch (Exception e) {
                System.out.println(MyConstants.WRONG_INPUT);
                okDouble = false;
            }
            scanner.nextLine();
        }while(!okDouble);

        Transaction transaction;
        List<Transaction> transactionsList = new ArrayList<>();

        Set<BankAccount> bankAccounts = transactions.keySet();


        if(method.equals("add")){

            //verify if there are already transactions for this account to add a new one
            for(BankAccount ba: bankAccounts)
            {
                if(ba.getAccountNumber().equals(accounts[currentUserId][myA].getAccountNumber())){
                    transactionsList = transactions.get(ba);
                    transactions.remove(ba);
                }
            }

            //change balance
            accounts[currentUserId][myA].changeBalance(amount,Type.RECEIVE);
            transaction = new Transaction(MyConstants.ADDED_YOU,Type.RECEIVE,Category.OTHER,amount,MyConstants.ADDED_YOU);
            transactionsList.add(transaction);
            transactions.put(accounts[currentUserId][myA],transactionsList);
        }
        else {
            String tryAgain = "1";
            boolean ok;
            do {
                //verify if the balance is sufficient to withdraw money
                ok = accounts[currentUserId][myA].verifyBalance(amount);

               if(ok) {
                   //verify if there are already transactions for this account to add a new one
                   for(BankAccount ba: bankAccounts)
                   {
                       if(ba.getAccountNumber().equals(accounts[currentUserId][myA].getAccountNumber())){
                           transactionsList = transactions.get(ba);
                           transactions.remove(ba);
                       }
                   }

                   //change balance
                   accounts[currentUserId][myA].changeBalance(amount,Type.SEND);
                   transaction = new Transaction(MyConstants.WITHDRAW_MONEY,Type.SEND,Category.OTHER,amount,MyConstants.WITHDRAW_MONEY);
                   transactionsList.add(transaction);
                   transactions.put(accounts[currentUserId][myA],transactionsList);
               }
               else{
                   System.out.println(MyConstants.TRY_AGAIN);
                   tryAgain = scanner.nextLine();
                   if(tryAgain.equals("1")){
                       //verify if user wants to add another amount if there are not enough money into account
                       do {
                           okDouble = true;
                           System.out.print(MyConstants.AMOUNT);
                           try {
                               amount = scanner.nextDouble();
                           } catch (Exception e) {
                               System.out.println(MyConstants.WRONG_INPUT);
                               okDouble = false;
                           }
                           scanner.nextLine();
                       }while(!okDouble);
                   }
               }
            }while(tryAgain.equals("1"));
        }
    }

    /**
     * function generate string of digits
     * @param   n - number of digits to be generated
     * @return  String with the required number of digits
     */
    public static String generateRandomDigits(int n) {

        int m = (int) Math.pow(10, n - 1);
        int result =  m + new Random().nextInt(9 * m);
        return String.valueOf(result);
    }

    /**
     * function to validate age
     * @param   date - date of birth of user
     * @throws  InvalidAgeException
     */
    public static void validateAge(Date date) throws InvalidAgeException{

        LocalDate birthday = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        Period p = Period.between(birthday, today);
        if(p.getYears() < 18){
            throw new InvalidAgeException(MyConstants.AGE_NOT_VALID);
        }
    }

    public static void main(String[] args)  {

        Scanner scan = new Scanner(System.in);

        //load data from files
        load();

        //generate main menu
        while(true) {
            System.out.println(MyConstants.E_BANKING);
            System.out.println(MyConstants.MAIN_MENU);

            System.out.print(MyConstants.R);
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    //verify password for admin
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

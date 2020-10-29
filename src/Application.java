import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// create default constructors

public class Application implements Serializable {

    public static int i = 0;
    public static int currentUserId = -1;
    public static User[] users = new User[50];
    public static BankAccount[][] accounts = new BankAccount[50][10];
    public static List<Bank> banks = new ArrayList<>();
    public static Hashtable<BankAccount, List<Transaction>> transactions =
            new Hashtable<BankAccount, List<Transaction>>();


    public static boolean admin() throws ParseException {

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
                    return false;
                case "8":
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


    private static void load() {

        try(FileInputStream in = new FileInputStream(MyConstants.FILE);
            ObjectInputStream s = new ObjectInputStream(in)) {
            users = (User[]) s.readObject();
            banks = (ArrayList<Bank>) s.readObject();
            accounts = (BankAccount[][]) s.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (User user : users) {
            if(user != null) {
                i++;
            }
        }
    }


    private static void save() {

        try(FileOutputStream f = new FileOutputStream(MyConstants.FILE);
            ObjectOutput s = new ObjectOutputStream(f)) {
            s.writeObject(users);
            s.writeObject(banks);
            s.writeObject(accounts);

            s.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
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

        User u = new User( username, password, i, lastName, firstName, gender, date, CNP, ID,
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


    private static boolean addNewBankAccount() throws ParseException {

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
        System.out.print(MyConstants.CARD_NUMBER);
        String cardNumber= scanner.nextLine();
        System.out.print(MyConstants.EXPIRY_DATE);
        String dateScan= scanner.nextLine();
        Date cardExpiryDate = new SimpleDateFormat(MyConstants.PATTERN).parse(dateScan);
        System.out.print(MyConstants.CVV);
        String CVV= scanner.nextLine();
        System.out.print(MyConstants.CURRENCY);
        System.out.print(MyConstants.R);
        int c= scanner.nextInt();
        scanner.nextLine();
        Currency currency;
        if(c == 1){
            currency = Currency.RON;
        }
        else if(c == 2){
            currency = Currency.EUR;
        }
        else if(c == 3){
            currency = Currency.GBP;
        }
        else if(c == 4){
            currency = Currency.USD;
        }
        else {
            System.out.println(MyConstants.INVALID_OPTION);
            return false;
        }

        BankAccount ba = new BankAccount(bank,accountOwner,accountNumber,cardNumber,cardExpiryDate,CVV, 0, currency);

        for(iterator = 0; iterator<accounts[id].length; iterator ++){
            if(accounts[id][iterator] == null) {
                accounts[id][iterator] = ba;
                break;
            }
        }
        return true;
    }


    private static void viewBankAccounts() {

        System.out.println(MyConstants.VIEW_ACCOUNTS);

        for (int i = 0; i<accounts.length; i++)
            for (int j = 0; j<accounts[0].length; j++){
                if(accounts[i][j] != null)
                    System.out.println(accounts[i][j]);
            }
    }


    public static boolean client(){

        Scanner scan = new Scanner(System.in);
        Boolean correctUser = false;
        String correctPassword = null;
        int id = 0;
        System.out.print(MyConstants.USERNAME);
        String username = scan.nextLine();
        for(User user:users){
            if(user != null && user.getUsername().equals(username)) {
                correctUser = true;
                id = user.getId();
                correctPassword = user.getPassword();
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
                            seeTransaction();
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
                            return false;
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
            };
        }
        else System.out.println(MyConstants.NO_USER);
        return false;
    }


    private static void seeBalance() {
    }


    private static void addTransaction() {
    }


    private static void seeTransaction() {
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

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Application implements Serializable {

    public static int i = 0;
    public static int currentUserId = -1;
    public static User[] users = new User[50];
    public static BankAccount[][] accounts = new BankAccount[50][10];
    public static List<Bank> banks = new ArrayList<>();


    public static boolean admin() throws ParseException {

        do {
            Scanner scan = new Scanner(System.in);
            System.out.println(MyConstants.ADMIN_MENU);

            System.out.print(MyConstants.R);
            int adminChoice = scan.nextInt();

            switch(adminChoice){
                case 1:
                    addNewUser();
                    break;
                case 2:
                    viewUsers();
                    break;
                case 3:
                    addNewBank();
                    break;
                case 4:
                    viewBanks();
                    break;
                case 5:
                    addNewBankAccount();
                    break;
                case 6:
                    viewBankAccounts();
                    break;
                case 7:
                    return false;
                case 8:
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

        Scanner userScan = new Scanner(System.in);
        System.out.print(MyConstants.USERNAME);
        String username= userScan.nextLine();
        System.out.print(MyConstants.PASSWORD);
        String password= userScan.nextLine();
        System.out.print(MyConstants.LAST_NAME);
        String lastName= userScan.nextLine();
        System.out.print(MyConstants.FIRST_NAME);
        String firstName= userScan.nextLine();
        System.out.println(MyConstants.GENDER);
        System.out.print(MyConstants.R);
        int sex= userScan.nextInt();
        Gender gender;
        if(sex == 1){
            gender = Gender.FEMALE;
        }
        else if(sex == 2){
            gender = Gender.MALE;
        }
        else{
            gender = Gender.UNSPECIFIED;
        }
        System.out.print(MyConstants.BIRTH);
        String dateScan = userScan.nextLine();
        Date date = new SimpleDateFormat(MyConstants.PATTERN).parse(dateScan);
        System.out.print(MyConstants.CNP);
        String CNP= userScan.nextLine();
        System.out.print(MyConstants.IDSN);
        String ID= userScan.nextLine();
        System.out.print(MyConstants.ADDRESS);
        String address= userScan.nextLine();
        System.out.print(MyConstants.EMAIL);
        String email= userScan.nextLine();
        System.out.print(MyConstants.PHONE);
        String phoneNumber=userScan.nextLine();

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

        Scanner userScan = new Scanner(System.in);
        System.out.print(MyConstants.ID);
        String id= userScan.nextLine();
        System.out.print(MyConstants.NAME);
        String name= userScan.nextLine();
        System.out.print(MyConstants.COUNTRY);
        String country= userScan.nextLine();

        Bank b = new Bank( id, name, country);

        banks.add(b);
    }


    private static void viewBanks() {

        System.out.println(MyConstants.VIEW_BANKS);

        for (Bank bank : banks)
            System.out.println(bank);
    }


    private static void addNewBankAccount() throws ParseException {

        System.out.println("\n-------------------------\nAdd Bank Account\n-------------------------");

        Scanner userScan = new Scanner(System.in);
        System.out.println("Choose Bank:");
        int iterator = 0;
        for (Bank bank: banks){
            System.out.println(" "+ iterator + "->" + bank.getName());
            iterator++;
        }
        System.out.print("R:");
        int choice= userScan.nextInt();
        Bank bank = banks.get(choice);
        System.out.println(bank);
        System.out.println("Introduce the id: \n 1-> Introduce id \n 2-> See list of users");
        System.out.print("R:");
        choice = userScan.nextInt();
        int id = 0;
        User accountOwner = new User();
        switch (choice){
            case 1:
                System.out.print("Introduce id:");
                id = userScan.nextInt();
                accountOwner = new User(users[id]);
                break;
            case 2:
                System.out.println("Choose from the list:");
                iterator = 0;
                for (User u: users)
                    if(u!=null) {
                        System.out.println(iterator + "-> " + u.getUsername());
                        iterator++;
                    }
                System.out.print("R:");
                id = userScan.nextInt();
                accountOwner = new User(users[id]);
                break;
            default:
                System.out.println("Not a valid option!");
                break;
        }
        System.out.print(" Account Number:");
        String accountNumber= userScan.nextLine();
        System.out.print(" Card Number:");
        String cardNumber= userScan.nextLine();
        System.out.print(" Expiry date(dd/mm/yyyy):");
        String dateScan= userScan.nextLine();
        Date cardExpiryDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateScan);
        System.out.print(" CVV:");
        String CVV= userScan.nextLine();
        System.out.print(" Initial balance:");
        int balance= userScan.nextInt();

        BankAccount ba = new BankAccount(bank,accountOwner,accountNumber,cardNumber,cardExpiryDate,CVV, balance);

        for(iterator = 0; iterator<accounts[id].length; iterator ++){
            if(accounts[id][iterator] == null) {
                accounts[id][iterator] = ba;
                break;
            }
        }
    }


    private static void viewBankAccounts() {

        System.out.println("\n-------------------------\nView Bank Accounts\n-------------------------");

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
                    int clientChoice = scan.nextInt();

                    switch(clientChoice){
                        case 1:
                            seeBalance();
                            break;
                        case 2:
                            addTransaction();
                            break;
                        case 3:
                            seeTransaction();
                            break;
                        case 4:
                            addMoney();
                            break;
                        case 5:
                            withdrawMoney();
                            break;
                        case 6:
                            createAccount();
                            break;
                        case 7:
                            seeStatistics();
                            break;
                        case 8:
                            return false;
                        case 9:
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
                System.out.println("\nIncorrect password!");
            };
        }
        else System.out.println("\nUser does not exist!");
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
            System.out.println("-------------------------");
            System.out.println("------- E-BANKING -------");
            System.out.println("-------------------------");
            System.out.println("\nWhat is your role? \n 1-> Admin \n 2-> Client \n 3-> Exit");

            System.out.print("\nR:");
            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter password: ");
                    String password = scan.nextLine();
                    if (password.equals(MyConstants.ADMIN_PASSWORD)) {
                        admin();
                    } else {
                        System.out.println("Wrong Password!!");
                    }
                    break;
                case "2":
                    client();
                    break;
                case "3":
                    save();
                    System.out.println("\nSaving Changes...");
                    System.out.println("Exiting Program...");
                    System.exit(0);
                    break;
                default :
                    System.out.println("This is not a valid Menu Option! Please Select Another");
                    break;

            }
        }


    }
}

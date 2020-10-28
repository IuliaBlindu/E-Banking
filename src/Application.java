import java.io.*;
import java.sql.Array;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Application implements Serializable {
    private static final String PASSWORD = "adminadmin";
    public static int i = 0;
    public static User users[] = new User[50];
    public static BankAccount accounts[][] = new BankAccount[50][10];
    public static List<Bank> banks = new ArrayList<Bank>();


    public static void admin() throws ParseException, FileNotFoundException {

        do {
        Scanner scan = new Scanner(System.in);
        System.out.println("Admin Options: \n 1-> Add User \n 2-> View Users \n 3-> Add Bank \n 4-> View Banks" +
                "\n 5-> Add Bank Accounts \n 6-> View Bank Accounts \n 7-> Exit ");
        String adminChoice = scan.nextLine();

        switch(adminChoice){
            case "1":
                addNewUser();
                break;
            case"2":
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
                save();
                System.out.println("Exiting Program...");
                System.exit(0);
                break;
            default :
                System.out.println("This is not a valid Menu Option! Please Select Another");
                break;
        }
        }while (true);
    }

    private static void load() {

        try(FileInputStream in = new FileInputStream("file.txt");
            ObjectInputStream s = new ObjectInputStream(in)) {
            users = (User[]) s.readObject();
            banks = (ArrayList<Bank>) s.readObject();
            accounts = (BankAccount[][]) s.readObject();

            s.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (User user : users) {
            if(user != null)
            {
                i++;
                System.out.println(user);
            }
        }

        for(Bank bank : banks){
            System.out.println(bank);
        }


    }

    private static void save() {

        try(FileOutputStream f = new FileOutputStream("file.txt");
            ObjectOutput s = new ObjectOutputStream(f)) {
            s.writeObject(users);
            s.writeObject(banks);
            s.writeObject(accounts);

            s.flush();
            s.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addNewUser() throws ParseException {
        Scanner userScan = new Scanner(System.in);
        System.out.println(" username:");
        String username= userScan.nextLine();
        System.out.println(" password:");
        String password= userScan.nextLine();
        System.out.println(" Last Name:");
        String lastName= userScan.nextLine();
        System.out.println(" First Name:");
        String firstName= userScan.nextLine();
        System.out.println(" Choose gender:\n1.Female \n2.Male \n3.Unspecified");
        String sex= userScan.nextLine();
        Gender gender;
        if(sex.equals("1")){
            gender = Gender.FEMALE;
        }
        else if(sex.equals("2")){
            gender = Gender.MALE;
        }
        else{
            gender = Gender.UNSPECIFIED;
        }
        System.out.println(" Date of Birth(dd/mm/yyyy):");
        String dateScan = userScan.nextLine();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateScan);
        System.out.println(" CNP:");
        String CNP= userScan.nextLine();
        System.out.println(" ID (Series+Number):");
        String ID= userScan.nextLine();
        System.out.println(" Address:");
        String address= userScan.nextLine();
        System.out.println(" Email:");
        String email= userScan.nextLine();
        System.out.println(" Phone Number:");
        String phoneNumber=userScan.nextLine();

        User u = new User( username, password, i, lastName, firstName, gender, date, CNP, ID,
                           address, email, phoneNumber);

        users[i]=new User(u);
        i++;
    }

    private static void viewUsers() {
        for (User user : users) {
            if(user != null)
                System.out.println(user);
        }
    }

    private static void addNewBank() {
        Scanner userScan = new Scanner(System.in);
        System.out.println(" Id:");
        String id= userScan.nextLine();
        System.out.println(" Name:");
        String name= userScan.nextLine();
        System.out.println(" Country:");
        String country= userScan.nextLine();

        Bank b = new Bank( id, name, country);

        banks.add(b);

    }

    private static void viewBanks() {
        for (Bank bank : banks)
            System.out.println(bank);
    }

    private static void addNewBankAccount() throws ParseException {

        Scanner userScan = new Scanner(System.in);
        System.out.println(" Choose Bank:");
        int iterator = 0;
        for (Bank bank: banks){
            System.out.println(iterator + "->" + bank.getName());
            iterator++;
        }
        int choice= userScan.nextInt();
        Bank bank = banks.get(choice);
        System.out.println(bank);
        System.out.println("Introduce the id: \n 1-> Introduce id \n 2-> See list of users");
        choice = userScan.nextInt();
        int id = 0;
        User accountOwner = new User();
        switch (choice){
            case 1:
                System.out.println("Introduce id:");
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
                id = userScan.nextInt();
                accountOwner = new User(users[id]);
                break;
            default:
                System.out.println("Not a valid option!");
                break;
        }
        System.out.println(" Account Number:");
        String accountNumber= userScan.nextLine();
        System.out.println(" Card Number:");
        String cardNumber= userScan.nextLine();
        System.out.println(" Expiry date(dd/mm/yyyy):");
        String dateScan= userScan.nextLine();
        Date cardExpiryDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateScan);
        System.out.println(" CVV:");
        String CVV= userScan.nextLine();
        System.out.println(" Initial balance:");
        int balance= userScan.nextInt();

        BankAccount ba = new BankAccount(bank,accountOwner,accountNumber,cardNumber,cardExpiryDate,CVV, balance);

        System.out.println(ba);

        for(iterator = 0; iterator<accounts[id].length; iterator ++){
            if(accounts[id][iterator] == null) {
                accounts[id][iterator] = ba;
                break;
            }
        }

    }

    private static void viewBankAccounts() {
        for (int i = 0; i<accounts.length; i++)
            for (int j = 0; j<accounts[0].length; j++){
                if(accounts[i][j] != null)
                    System.out.println(accounts[i][j]);
            }
    }


    public static void client(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Client Options: ");
    }

    public static void main(String[] args) throws ParseException, FileNotFoundException {


        Scanner scan = new Scanner(System.in);

        //verify if file exists
        load();

        while(true) {
            System.out.println("What is your role? \n 1-> Admin \n 2-> Client \n 3-> Exit");

            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Enter password: ");
                    String password = scan.nextLine();
                    if (password.equals(PASSWORD)) {
                        admin();
                    } else {
                        System.out.println("Wrong Password");
                    }
                    break;
                case "2":
                    client();
                    break;
                case "3":
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

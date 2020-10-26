import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Application implements Serializable {
    public static int i = 0;
    public static User users[] = new User[50];
    public static BankAccount accounts[] = new BankAccount[50];

    public static void admin() throws ParseException, FileNotFoundException {

        do {
        Scanner scan = new Scanner(System.in);
        System.out.println("Admin Options: \n1.Add new user \n2.View existing user \n3.Exit");
        String adminChoice = scan.nextLine();

        switch(adminChoice){
            case "1":
                addNewUser();
                break;
            case"2":
                viewUsers();
                break;
            case "3":
                save(users);
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
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("file2.txt"));
//            String line;
//            while ((line = reader.readLine()) != null) {
////                System.out.println(line);
//                String delims = "['=,]+";
//                String[] tokens = line.split(delims);
//                String username = tokens[1];
//                String password = tokens[3];
//                String lastName = tokens[5];
//                String firstName = tokens[7];
//                Gender gender;
//                if (tokens[9].equals("FEMALE"))
//                    gender = Gender.FEMALE;
//                else if (tokens[9].equals("MALE"))
//                    gender = Gender.MALE;
//                else
//                    gender = Gender.UNSPECIFIED;
//                Date dateOfBirth;
//                SimpleDateFormat formatter = new SimpleDateFormat("E MMM d HH:mm:ss z yyyy");
//                dateOfBirth = formatter.parse(tokens[11]);
//                String cnp = tokens[13];
//                String series = tokens[15];
//                String number = tokens[17];
//                String city = tokens[19];
//                String street = tokens[21];
//                String streetNumber = tokens[23];
//                String email = tokens[25];
//                String phoneNumber = tokens[27];
//
//                User newUser = new User(username, password, lastName, firstName, gender, dateOfBirth, cnp, series, number, city, street, streetNumber, email, phoneNumber);
//                System.out.println(newUser);
//                users[i] = newUser;
//                i++;
//            }
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
        System.out.println("hello");
        try(FileInputStream in = new FileInputStream("file.txt");
            ObjectInputStream s = new ObjectInputStream(in)) {
            users = (User[]) s.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(users));
        for (User user : users) {
            if(user != null)
            {
                i++;
                System.out.println(user);
            }

        }

    }

    private static void save(User[] users) {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter("file2.txt"));
//            for(User user : users) {
//                if (user != null) {
//                    writer.write(user.toString() + "\n");
//                    System.out.println(user.toString() + "\n");
//                }
//            }
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try(FileOutputStream f = new FileOutputStream("file.txt");
            ObjectOutput s = new ObjectOutputStream(f)) {
            s.writeObject(users);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addNewUser() throws ParseException {
        Scanner userScan = new Scanner(System.in);
        System.out.println("username:");
        String username= userScan.nextLine();
        System.out.println("password:");
        String password= userScan.nextLine();
        System.out.println("Last Name:");
        String lastName= userScan.nextLine();
        System.out.println("First Name:");
        String firstName= userScan.nextLine();
        System.out.println("Choose gender:\n1.Female \n2.Male \n3.Unspecified");
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
        System.out.println("Date of Birth(dd/mm/yyyy):");
        String dateScan = userScan.nextLine();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateScan);
        System.out.println("CNP:");
        String CNP= userScan.nextLine();
        System.out.println("ID Series:");
        String series= userScan.nextLine();
        System.out.println("ID Number:");
        String number= userScan.nextLine();
        System.out.println("City:");
        String city= userScan.nextLine();
        System.out.println("Street:");
        String street= userScan.nextLine();
        System.out.println("Street Number:");
        String streetNumber= userScan.nextLine();
        System.out.println("Email:");
        String email= userScan.nextLine();
        System.out.println("Phone Number:");
        String phoneNumber=userScan.nextLine();

        User u = new User(username, password, lastName, firstName, gender, date, CNP, series,
                            number, city, street, streetNumber, email, phoneNumber);


        // i should get the number of existing elements in file - do this in load
        System.out.println(u);
        users[i]=u;
        i=i+1;
        System.out.println(i);
        for(int j=0; j<i;j++){
            System.out.println(users[j]);
        }


    }
    private static void addNewBankAccount(){

    }

    private static void viewUsers() {
        for (User user : users) {
            if(user != null)
            System.out.println(user.toString());
        }
    }

    public static void client(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Client Options: ");
    }

    public static void main(String[] args) throws ParseException, FileNotFoundException {

        String PASSWORD = "adminadmin";
        Scanner scan = new Scanner(System.in);

        //verify if file exists
        load();

        while(true) {
            System.out.println("What is your role? \n 1. Admin \n 2. Client \n 3. Exit");

            String choice = scan.nextLine();


            switch (choice) {
                case "1":
                    System.out.println("Enter password: ");
                    String password = scan.nextLine();
                    System.out.println(password + PASSWORD);
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

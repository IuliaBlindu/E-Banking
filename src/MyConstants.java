public final class MyConstants {
    public static final String USER_FILE = "users.txt";
    public static final String ACCOUNTS_FILE = "accounts.txt";
    public static final String BANKS_FILE = "banks.txt";
    public static final String TRANSACTIONS_FILE = "transactions.txt";

    public static final String E_BANKING = "-------------------------\n------- E-BANKING -------\n-------------------------";

    public static final String ADMIN_MENU= "\n-------------------------\nAdmin Options: \n 1-> Add User \n 2-> View Users " +
                                           "\n 3-> Add Bank \n 4-> View Banks \n 5-> Add Bank Account \n" +
                                           " 6-> View Bank Accounts \n 7-> Delete User \n 8-> Delete Account" +
                                           " \n 9-> Exit to Main Menu\n10-> Exit Application " +
                                           "\n------------------------- ";

    public static final String CLIENT_MENU = "\n-------------------------\nUser Options: \n 1-> See Balances and Transactions" +
                                             "\n 2-> Add Transaction \n 3-> Add Money \n 4-> Withdraw Money \n 5-> Create Account \n " +
                                             "6-> Exit to Main Menu \n 7-> Exit Application\n------------------------- ";

    public static final String MAIN_MENU = "\nWhat is your role? \n 1-> Admin \n 2-> Client \n 3-> Exit";
    public static final String ADMIN_PASSWORD = "adminadmin";
    public static final String WRONG_PASSWORD = "Wrong password!";

    public static final String ADD_USER = "\n-------------------------\nAdd User\n-------------------------";
    public static final String VIEW_BANKS = "\n-------------------------\nView Banks\n-------------------------";
    public static final String ADD_BANK_ACCOUNT = "\n-------------------------\nAdd Bank Account\n-------------------------";
    public static final String VIEW_ACCOUNTS = "\n-------------------------\nView Bank Accounts\n-------------------------";
    public static final String DELETE_USER = "\n-------------------------\nDelete User\n-------------------------";
    public static final String DELETE_ACCOUNT = "\n-------------------------\nDelete Account\n-------------------------";
    public static final String ADD_TRANSACTION = "\n-------------------------\nNew Transactions\n-------------------------";
    public static final String SEE_TRANSACTIONS = "\n-------------------------\nSee Balances and Transactions\n-------------------------";
    public static final String ADD_MONEY ="\n-------------------------\nAdd Money\n-------------------------";
    public static final String WITHDRAW_MONEY ="\n-------------------------\nWithdraw Money\n-------------------------";
    public static final String VIEW_USER = "\n-------------------------\nView Users\n-------------------------";
    public static final String ADD_BANK = "\n-------------------------\nAdd Bank\n-------------------------";

    public static final String USERNAME = "Username:";
    public static final String PASSWORD = "Password:";
    public static final String LAST_NAME = "Last Name:";
    public static final String FIRST_NAME = "First Name:";
    public static final String GENDER = "Choose gender:\n 1-> Female \n 2-> Male \n 3-> Unspecified";
    public static final String BIRTH = "Date of Birth(dd/mm/yyyy):";
    public static final String PATTERN = "dd/MM/yyyy";
    public static final String CNP = "CNP:";
    public static final String IDSN = "ID (Series+Number):";
    public static final String ADDRESS = "Address:";
    public static final String EMAIL = "Email:";
    public static final String PHONE = "Phone Number:";
    public static final String ID = "Id:";
    public static final String NAME = "Name:";
    public static final String COUNTRY = "Country:";

    public static final String CHOOSE_BANK = "Choose Bank:";
    public static final String ADD_USER_OPTIONS = "Introduce the id: \n 1-> Introduce id \n 2-> See list of users";
    public static final String CHOICES = "Choose from the list:";
    public static final String ACCOUNT_NAME = "Account Name:";
    public static final String CURRENCY = "Choose Currency\n 1-> RON \n 2-> EUR \n 3-> USD";

    public static final String NO_USER = "\nUser does not exist!";

    public static final String CHOOSE_ACCOUNT = "Choose Account:";
    public static final String CHOOSE_YOUR_ACCOUNT = "Choose from Your Accounts:";
    public static final String INTRODUCE_OTHER_ACCOUNT = "Introduce the Other Account Number:";

    public static final double RON = 1;
    public static final double USD =  4.11;
    public static final double EUR = 4.87;

    public static final String CHOOSE_OWNER = "Choose the Owner";
    public static final String WRONG_ACCOUNT = "Account Does Not Exist!";
    public static final String CHOOSE_TYPE = "Choose Type\n 1-> SEND \n 2-> REQUEST";
    public static final String CHOOSE_CATEGORY = "Choose Category\n 1-> BILLS \n 2-> GROCERIES \n 3-> SHOPPING \n 4-> TRANSPORT" +
                                                 "\n 5-> HEALTH \n 6-> ENTERTAINMENT \n 7-> OTHER";
    public static final String AMOUNT = "Amount:";
    public static final String INITIAL_BALANCE = "Initial Balance:";
    public static final String DETAILS = "Details:";
    public static final String INSUFFICIENT_BALANCE ="Not Enough Money in Sender Account!";

    public static final String R= "R:";
    public static final String EXIT = "\nSaving Changes...\nExiting Application";
    public static final String INVALID_OPTION = "This is not a valid option!";
    public static final String TRY_AGAIN = "Try Again? \n 0-> NO \n 1-> YES";

    public static final String RED = "\033[0;31m";
    public static final String BLUE = "\033[0;34m";
    public static final String RESET = "\033[0m";

    public static final String SEPARATOR = "----------------------------------------------------------------------";

    public static final String IO_EXCEPTION = "FILE NOT FOUND! NO RECORDS! ";
    public static final String CLASS_NOT_FOUND_EXCEPTION = "FILE NOT FOUND: ";
    public static final String WRONG_INPUT = "WRONG INPUT! TRY AGAIN!";
    public static final String CLASS_CAST_EXCEPTION = "FILE DOES NOT CONTAIN THE REQUIRED CLASS: ";
    public static final String USER_EXISTS = "User already exists!";
    public static final String USER_NOT_EXIST = "User does not exist!";
    public static final String INCORRECT_DATE = "Incorrect date format!";
    public static final String ID_EXISTS = "Id already exists!";
    public static final String BANK_EXISTS = "Bank already exists!";
    public static final String NO_ACCOUNTS = "User has no accounts!";
    public static final String ADDED_YOU = "Added by you";
    public static final String WITHDRAWN_YOU = "Withdrawn by you";

    public static final String AGE_NOT_VALID = "Age not valid! User has to have at least 18 years old!";

}

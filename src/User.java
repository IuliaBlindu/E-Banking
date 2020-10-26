import java.io.Serializable;
import java.util.Date;

public class User extends Person implements Serializable {

    private String username;
    private String password;

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String lastName, String firstName, Gender gender, Date dateOfBirth, String CNP, String series, String number, String city, String street, String streetNumber, String email, String phoneNumber) {
        super(lastName, firstName, gender, dateOfBirth, CNP, series, number, city, street, streetNumber, email, phoneNumber);
        this.username = username;
        this.password = password;
    }

    public User(User that){
        super(that.getLastName(),that.getFirstName(),that.getGender(),that.getDateOfBirth(),that.getCNP(),that.getSeries(),
                that.getNumber(), that.getCity(),that.getStreet(),that.getStreetNumber(),that.getEmail(),that.getPhoneNumber());
        this.username=that.getUsername();
        this.password=that.getPassword();
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void helloUser(String username) {
        System.out.println("Welcome" + username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' + super.toString();
    }
}


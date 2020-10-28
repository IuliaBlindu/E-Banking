import java.io.Serializable;
import java.util.Date;

public class User extends Person implements Serializable {

    private String username;
    private String password;

    public User() {
        super();
        this.username = null;
        this.password = null;
    }

    public User(String username, String password, int id, String lastName, String firstName, Gender gender, Date dateOfBirth, String CNP, String ID, String address, String email, String phoneNumber) {
        super(id, lastName, firstName, gender, dateOfBirth, CNP, ID, address, email, phoneNumber);
        this.username = username;
        this.password = password;
    }

    public User(User that){
        super(that.getId(),that.getLastName(),that.getFirstName(),that.getGender(),that.getDateOfBirth(),that.getCNP(),that.getID(),
                that.getAddress(), that.getEmail(),that.getPhoneNumber());
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
    public void welcome(String username) {
        System.out.println("Welcome" + username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' + super.toString();
    }
}


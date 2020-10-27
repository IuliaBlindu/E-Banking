import java.io.Serializable;
import java.util.Date;

public abstract class Person implements Serializable {

    private String lastName;
    private String firstName;
    private Gender gender;
    private Date dateOfBirth;
    private String CNP;
    private String ID;
    private String address;
    private String email;
    private String phoneNumber;

    public Person(){
        super();
        this.lastName = "lastName";
        this.firstName = "firstName";
        this.gender = Gender.UNSPECIFIED;
        this.dateOfBirth = null;
        this.CNP = null;
        this.ID = null;
        this.address = null;
        this.email=null;
        this.phoneNumber= null;
    }

    public Person(String lastName, String firstName, Gender gender, Date dateOfBirth, String CNP,
                  String ID, String address, String email, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.CNP = CNP;
        this.ID = ID;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void welcome(String username){

    }

    @Override
    public String toString() {
        return  ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", CNP='" + CNP + '\'' +
                ", ID='" + ID + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

import java.io.Serializable;
import java.util.Date;

public abstract class Person implements Serializable {

    protected String lastName;
    private String firstName;
    private Gender gender;
    private Date dateOfBirth;
    private String CNP;
    private String series;
    private String number;
    private String city;
    private String street;
    private String streetNumber;
    private String email;
    private String phoneNumber;

    public Person()
    {
        super();
        this.lastName = "lastName";
        this.firstName = "firstName";
        this.gender = Gender.UNSPECIFIED;
        this.dateOfBirth = null;
        this.CNP = null;
        this.series = null;
        this.number = null;
        this.city = null;
        this.street = null;
        this.streetNumber = null;
        this.email=null;
        this.phoneNumber= null;
    }

    public Person(String lastName, String firstName, Gender gender, Date dateOfBirth, String CNP, String series,
                  String number, String city, String street, String streetNumber, String email, String phoneNumber)
    {
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.CNP = CNP;
        this.series = series;
        this.number = number;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
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

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
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

    public abstract void helloUser(String username);

    @Override
    public String toString() {
        return  ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", CNP='" + CNP + '\'' +
                ", series='" + series + '\'' +
                ", number='" + number + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zohaib.fraudguard;

/**
 *
 * @author zohaibakram
 */
import java.time.LocalDate;

public class Customer {

    private long customerId;

    private String firstName;
    private String middleName;
    private String lastName;

    private LocalDate dateOfBirth;

    private String ssnToken;

    private String accountNumber;
    private String accountType;

    private String phone;
    private String email;

    private String street;
    private String city;
    private String state;
    private String zip;

    private LocalDate accountOpeningDate;

    public Customer(
            long customerId,
            String firstName,
            String middleName,
            String lastName,
            LocalDate dateOfBirth,
            String ssnToken,
            String accountNumber,
            String accountType,
            String phone,
            String email,
            String street,
            String city,
            String state,
            String zip,
            LocalDate accountOpeningDate) {

        this.customerId = customerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.ssnToken = ssnToken;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.phone = phone;
        this.email = email;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.accountOpeningDate = accountOpeningDate;
    }

    public long getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSsnToken() {
        return ssnToken;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public LocalDate getAccountOpeningDate() {
        return accountOpeningDate;
    }
}
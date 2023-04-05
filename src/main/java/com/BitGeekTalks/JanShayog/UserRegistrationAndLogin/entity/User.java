package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
@Table(name="Users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "user_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq"
    )
    private long id;

    @Column(name = "AccountId")
    private long accountId;
    @Column(name = "FirstName")
    private String Firstname;
    @Column(name = "MiddleName")
    private String MiddleName;
    @Column(name = "LastName")
    private String Lastname;
    @Column(name = "Gender")
    private String Gender;
    @Column(name = "Dob")
    private String Dob;
    @Column(name = "AltEmail")
    private String Alternative_email;
    @Column(name = "AltPhoneNumber")
    private String Alternative_PhoneNumber;
    @Column(name = "PhoneNumber")
    private String PhoneNumber;
    @OneToOne(mappedBy = "AccountId", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private PermanentAddress permanentAddress;

    @OneToOne(mappedBy = "AccountId", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private CurrentAddress currentAddress;

    @OneToOne(mappedBy = "AccountId", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private GovermentId govermentId;
    @OneToOne(mappedBy = "AccountId", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private AddressProve addressProve;
    @OneToOne(mappedBy = "AccountId", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private Relation relation;
    @OneToOne(mappedBy = "AccountId", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private PhoneNumberVerification phoneNumberVerification;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", AccountId=" + accountId +
                ", Firstname='" + Firstname + '\'' +
                ", MiddleName='" + MiddleName + '\'' +
                ", Lastname='" + Lastname + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Dob='" + Dob + '\'' +
                ", Alternative_email='" + Alternative_email + '\'' +
                ", Alternative_PhoneNumber='" + Alternative_PhoneNumber + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", permanentAddress=" + permanentAddress +
                ", currentAddress=" + currentAddress +
                ", govermentId=" + govermentId +
                ", addressProve=" + addressProve +
                ", relation=" + relation +
                ", phoneNumberVerification=" + phoneNumberVerification +
                '}';
    }

    public PhoneNumberVerification getPhoneNumberVerification() {
        return phoneNumberVerification;
    }

    public void setPhoneNumberVerification(PhoneNumberVerification phoneNumberVerification) {
        this.phoneNumberVerification = phoneNumberVerification;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getAlternative_email() {
        return Alternative_email;
    }

    public void setAlternative_email(String alternative_email) {
        Alternative_email = alternative_email;
    }

    public String getAlternative_PhoneNumber() {
        return Alternative_PhoneNumber;
    }

    public void setAlternative_PhoneNumber(String alternative_PhoneNumber) {
        Alternative_PhoneNumber = alternative_PhoneNumber;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public PermanentAddress getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(PermanentAddress permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public CurrentAddress getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(CurrentAddress currentAddress) {
        this.currentAddress = currentAddress;
    }

    public GovermentId getGovermentId() {
        return govermentId;
    }

    public void setGovermentId(GovermentId govermentId) {
        this.govermentId = govermentId;
    }

    public AddressProve getAddressProve() {
        return addressProve;
    }

    public void setAddressProve(AddressProve addressProve) {
        this.addressProve = addressProve;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public User() {
    }

    public User(long id, long accountId, String firstname, String middleName, String lastname, String gender, String dob, String alternative_email, String alternative_PhoneNumber, String phoneNumber, PermanentAddress permanentAddress, CurrentAddress currentAddress, GovermentId govermentId, AddressProve addressProve, Relation relation, PhoneNumberVerification phoneNumberVerification) {
        this.id = id;
        accountId = accountId;
        Firstname = firstname;
        MiddleName = middleName;
        Lastname = lastname;
        Gender = gender;
        Dob = dob;
        Alternative_email = alternative_email;
        Alternative_PhoneNumber = alternative_PhoneNumber;
        PhoneNumber = phoneNumber;
        this.permanentAddress = permanentAddress;
        this.currentAddress = currentAddress;
        this.govermentId = govermentId;
        this.addressProve = addressProve;
        this.relation = relation;
        this.phoneNumberVerification = phoneNumberVerification;
    }
}

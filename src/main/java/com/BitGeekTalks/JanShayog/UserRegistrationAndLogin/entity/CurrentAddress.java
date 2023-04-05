package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "CurrentAddress")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class CurrentAddress {
    @Id
    @SequenceGenerator(
            name = "CurrentAddress_seq",
            sequenceName = "CurrentAddress_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "CurrentAddress_seq"
    )
    private long id;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "AccountID")
//    @JsonBackReference
    private User AccountId;
    @Column(name = "Area")
    private String Area;
    @Column(name = "Locality")
    private String Locality;
    @Column(name = "District")
    private String District;
    @Column(name = "State")
    private String State;
    @Column(name = "Country")
    private String Country;
    @Column(name = "Pincode")
    private long Pincode;

    @Override
    public String toString() {
        return "CurrentAddress{" +
                "id=" + id +
                ", Area='" + Area + '\'' +
                ", Locality='" + Locality + '\'' +
                ", District='" + District + '\'' +
                ", State='" + State + '\'' +
                ", Country='" + Country + '\'' +
                ", Pincode=" + Pincode +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAccountId() {
        return AccountId;
    }

    public void setAccountId(User accountId) {
        AccountId = accountId;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getLocality() {
        return Locality;
    }

    public void setLocality(String locality) {
        Locality = locality;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public long getPincode() {
        return Pincode;
    }

    public void setPincode(long pincode) {
        Pincode = pincode;
    }

    public CurrentAddress() {
    }

    public CurrentAddress(long id, User accountId, String area, String locality, String district, String state, String country, long pincode) {
        this.id = id;
        AccountId = accountId;
        Area = area;
        Locality = locality;
        District = district;
        State = state;
        Country = country;
        Pincode = pincode;
    }
}

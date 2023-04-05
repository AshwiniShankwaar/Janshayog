package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name="AddressProve")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class AddressProve {

    @Id
    @SequenceGenerator(
            name = "Address_id_seq",
            sequenceName = "Address_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Address_id_seq"
    )
    private long id;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "AccountID")
//    @JsonBackReference
    private User AccountId;

    private long idSerialNo;
    private String idNumber;
    @Column(name = "IdType")
    private String idProveType;

    @Override
    public String toString() {
        return "AddressProve{" +
                "id=" + id +
                ", idSerialNo=" + idSerialNo +
                ", idNumber='" + idNumber + '\'' +
                ", idProveType='" + idProveType + '\'' +
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

    public long getIdSerialNo() {
        return idSerialNo;
    }

    public void setIdSerialNo(long idSerialNo) {
        this.idSerialNo = idSerialNo;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdProveType() {
        return idProveType;
    }

    public void setIdProveType(String idProveType) {
        this.idProveType = idProveType;
    }

    public AddressProve() {
    }

    public AddressProve(long id, User accountId, long idSerialNo, String idNumber, String idProveType) {
        this.id = id;
        AccountId = accountId;
        this.idSerialNo = idSerialNo;
        this.idNumber = idNumber;
        this.idProveType = idProveType;
    }
}

package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "GovermentId")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class GovermentId {
    @Id
    @SequenceGenerator(
            name = "Goverment_id_seq",
            sequenceName = "Goverment_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Goverment_id_seq"
    )
    private long id;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "AccountID")
//    @JsonBackReference
    private User AccountId;

    @Column(name = "IdSerialNo")
    private long idSerialNo;
    @Column(name="IdNumber")
    private String idNumber;
    @Column(name = "IdType")
    private String idProveType;

    @Override
    public String toString() {
        return "GovermentId{" +
                "id=" + id +
                ", idSerialNo=" + idSerialNo +
                ", idNumber='" + idNumber + '\'' +
                ", IdProveType='" + idProveType + '\'' +
                '}';
    }

    public GovermentId() {
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

    public GovermentId(long id, User accountId, long idSerialNo, String idNumber, String idProveType) {
        this.id = id;
        AccountId = accountId;
        this.idSerialNo = idSerialNo;
        this.idNumber = idNumber;
        this.idProveType = idProveType;
    }
}

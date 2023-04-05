package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "RelationBtw")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Relation {
    @Id
    @SequenceGenerator(
            name="Relation_seq",
            sequenceName = "Relation_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Relation_seq"
    )
    private long id;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "AccountID")
//    @JsonBackReference
    private User AccountId;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="emergencyContactId")
    private EmergencyContact emergencyContact;
    @Column(name = "RelationType")
    private String RelationType;

    @Override
    public String toString() {
        return "Relation{" +
                "id=" + id +
                ", emergencyContact=" + emergencyContact +
                ", RelationType='" + RelationType + '\'' +
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

    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getRelationType() {
        return RelationType;
    }

    public void setRelationType(String relationType) {
        RelationType = relationType;
    }

    public Relation() {
    }

    public Relation(long id, User accountId, EmergencyContact emergencyContact, String relationType) {
        this.id = id;
        AccountId = accountId;
        this.emergencyContact = emergencyContact;
        RelationType = relationType;
    }
}


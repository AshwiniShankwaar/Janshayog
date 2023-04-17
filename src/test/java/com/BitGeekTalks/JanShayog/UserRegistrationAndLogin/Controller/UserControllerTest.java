package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Controller;

import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserControllerTest {
    @Autowired
    UserController userController;
    @Test
    void testUserDetailsSave(){
        //ResponseEntity<User> r = userController.createUser(getUserData());
        //System.out.println(r.getBody());
    }
    User getUserData(){
        PermanentAddress permanentAddress = new PermanentAddress();
        permanentAddress.setArea("testArea");
        permanentAddress.setLocality("testLocality");
        permanentAddress.setDistrict("testDistrict");
        permanentAddress.setState("testState");
        permanentAddress.setCountry("testCountry");
        permanentAddress.setPincode(0000000);

        CurrentAddress currentAddress = new CurrentAddress();
        currentAddress.setArea("testArea");
        currentAddress.setLocality("testLocality");
        currentAddress.setDistrict("testDistrict");
        currentAddress.setState("testState");
        currentAddress.setCountry("testCountry");
        currentAddress.setPincode(812009);


        EmergencyContact emergencyContact = new EmergencyContact();
        emergencyContact.setFirstName("testFirstName");
        emergencyContact.setMiddleName("testMiddleName");
        emergencyContact.setLastName("testLastName");
        emergencyContact.setEmailAddress("testEmail");
        emergencyContact.setPhoneNumber("testPhoneNumber");
        emergencyContact.setGender("test");
        emergencyContact.setAddress("testAddress");

        Relation relation = new Relation();
        relation.setRelationType("testrelation");
        relation.setEmergencyContact(emergencyContact);

        GovermentId govermentId = new GovermentId();
        govermentId.setIdSerialNo(1);

        AddressProve addressProve = new AddressProve();
        addressProve.setIdSerialNo(1);

        PhoneNumberVerification phoneNumberVerification = new PhoneNumberVerification();

        User user = new User();
        user.setAccountId(20);
        user.setFirstname("testFirstname");
        user.setMiddleName("testMiddle");
        user.setLastname("testLastname");
        user.setGender("testGender");
        user.setDob("testD");
        user.setPhoneNumber("0");
        user.setAlternative_email("test@example.com");
        user.setAlternative_PhoneNumber("1234567890");

        permanentAddress.setAccountId(user);
        currentAddress.setAccountId(user);
        govermentId.setAccountId(user);
        addressProve.setAccountId(user);
        relation.setAccountId(user);
        phoneNumberVerification.setAccountId(user);
        user.setPermanentAddress(permanentAddress);
        user.setCurrentAddress(currentAddress);
        user.setGovermentId(govermentId);
        user.setAddressProve(addressProve);
        user.setRelation(relation);
        user.setPhoneNumberVerification(phoneNumberVerification);
        return user;
    }

    @Test
    void getUser(){
        System.out.println(userController.getUser("12345"));
    }
}
package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.service;

import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Repo.UserRepo;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

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
    void addUser() {
        User user = getUserData();
        System.out.println(user.toString());
//        User extingUser = userRepo.findByAccountId(user.getAccountId());
//        if(extingUser != null) {
//            User savedUser = updateUserData(user,extingUser);
//
//            System.out.println(userRepo.save(savedUser));
//        }else {
//            System.out.println(userRepo.save(user));
//        }
        //System.out.println(userService.addUser(user));
    }

    private User updateUserData(User user, User extingUser) {
        extingUser.setMiddleName(user.getMiddleName());
        extingUser.setLastname(user.getLastname());
        extingUser.setGender(user.getGender());
        extingUser.setAlternative_email(user.getAlternative_email());
        extingUser.setAlternative_PhoneNumber(user.getAlternative_PhoneNumber());

        if(!extingUser.getPhoneNumber().equals(user.getPhoneNumber())){
            extingUser.setPhoneNumber(user.getPhoneNumber());

            PhoneNumberVerification phoneNumberVerification = new PhoneNumberVerification();
            phoneNumberVerification.setAccountId(extingUser);
            extingUser.setPhoneNumberVerification(phoneNumberVerification);
        }

        extingUser.getPermanentAddress().setArea(user.getPermanentAddress().getArea());
        extingUser.getPermanentAddress().setCountry(user.getPermanentAddress().getCountry());
        extingUser.getPermanentAddress().setDistrict(user.getPermanentAddress().getDistrict());
        extingUser.getPermanentAddress().setLocality(user.getPermanentAddress().getLocality());
        extingUser.getPermanentAddress().setState(user.getPermanentAddress().getState());
        extingUser.getPermanentAddress().setPincode(user.getPermanentAddress().getPincode());

        extingUser.getCurrentAddress().setArea(user.getCurrentAddress().getArea());
        extingUser.getCurrentAddress().setCountry(user.getCurrentAddress().getCountry());
        extingUser.getCurrentAddress().setDistrict(user.getCurrentAddress().getDistrict());
        extingUser.getCurrentAddress().setLocality(user.getCurrentAddress().getLocality());
        extingUser.getCurrentAddress().setState(user.getCurrentAddress().getState());
        extingUser.getCurrentAddress().setPincode(user.getCurrentAddress().getPincode());

        extingUser.getGovermentId().setIdSerialNo(user.getGovermentId().getIdSerialNo());

        extingUser.getAddressProve().setIdSerialNo(user.getAddressProve().getIdSerialNo());

        extingUser.getRelation().getEmergencyContact()
                .setFirstName(user.getRelation().getEmergencyContact().getFirstName());
        extingUser.getRelation().getEmergencyContact()
                .setMiddleName(user.getRelation().getEmergencyContact().getMiddleName());
        extingUser.getRelation().getEmergencyContact()
                .setLastName(user.getRelation().getEmergencyContact().getLastName());
        extingUser.getRelation().getEmergencyContact()
                .setGender(user.getRelation().getEmergencyContact().getGender());
        extingUser.getRelation().getEmergencyContact()
                .setPhoneNumber(user.getRelation().getEmergencyContact().getPhoneNumber());
        extingUser.getRelation().getEmergencyContact()
                .setEmailAddress(user.getRelation().getEmergencyContact().getEmailAddress());
        extingUser.getRelation().getEmergencyContact()
                .setAddress(user.getRelation().getEmergencyContact().getAddress());

        extingUser.getRelation().setRelationType(user.getRelation().getRelationType());

        return extingUser;

    }

    @Test
    void getUser() {
        System.out.println(userService.getUser(16));
    }

    @Test
    void testIdProve(){
        System.out.println(userService.getIdProve(4));
    }
}
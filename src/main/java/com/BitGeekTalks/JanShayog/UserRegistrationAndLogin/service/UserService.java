package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.service;

import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Repo.IdProveRepo;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.Repo.UserRepo;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.IdProve;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.PhoneNumberVerification;
import com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    IdProveRepo idProveRepo;
    public User addUser(User user) {
        User extingUser = userRepo.findByAccountId(user.getAccountId());
//        System.out.println(extingUser);
//        System.out.println(user);
//        System.out.println(user.getCurrentAddress());
//        System.out.println(user.getPermanentAddress());
//        System.out.println(user.getAddressProve());
//        System.out.println(user.getGovermentId());
//        System.out.println(user.getRelation());

//        System.out.println(user);
        if(extingUser != null) {
            User savedUser = updateUserData(user,extingUser);
            System.out.println(savedUser);
            return userRepo.save(savedUser);
        }else {
            user.getPermanentAddress().setAccountId(user);
            user.getCurrentAddress().setAccountId(user);
            user.getAddressProve().setAccountId(user);
            user.getGovermentId().setAccountId(user);
            user.getRelation().setAccountId(user);
            user.getPhoneNumberVerification().setAccountId(user);
            return userRepo.save(user);
        }
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
        extingUser.getGovermentId().setIdNumber(user.getGovermentId().getIdNumber());
        extingUser.getGovermentId().setIdProveType(user.getGovermentId().getIdProveType());


        extingUser.getAddressProve().setIdSerialNo(user.getAddressProve().getIdSerialNo());
        extingUser.getAddressProve().setIdNumber(user.getAddressProve().getIdNumber());
        extingUser.getAddressProve().setIdProveType(user.getAddressProve().getIdProveType());


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
    public User getUser(long id) {
        User user = userRepo.findByAccountId(id);
        return user;
    }

    public long saveIdProve(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        byte[] fileData = file.getBytes();
        System.out.println(fileName + "\n " + fileType+"\n "+fileData);
        IdProve idProve = new IdProve();
        idProve.setFileType(fileType);
        idProve.setFileData(fileData);
        idProve = idProveRepo.save(idProve);
        if(idProve==null){
            return 0;
        }
        return idProve.getIdSerialNo();
    }

    public IdProve getIdProve(long id){
        Optional<IdProve> op = idProveRepo.findById(id);
        IdProve idProve = op.get();
        return idProve;
    }
}

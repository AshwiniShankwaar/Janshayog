package com.BitGeekTalks.JanShayog.Request.service;

import com.BitGeekTalks.JanShayog.Request.datapayload.RequestData;
import com.BitGeekTalks.JanShayog.Request.entity.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
class RequestServiceTest {
    @Autowired
    RequestService requestService;
    @Test
    void testRequestSave(){
        RequestData requestData = new RequestData();
        requestData.setAccountId(1L);
        requestData.setTitle("Cleaning");
        requestData.setDate(LocalDate.of(2023, 4, 6));
        requestData.setTime(LocalTime.of(10, 0));
        requestData.setAddress("123 Main St");
        requestData.setTimePeriod("2 hours");
        requestData.setNoPeople(2L);
        requestData.setSkills("Cleaning");
        requestData.setPayableAmount(50.0);
        requestData.setDescription("Clean my house");
        requestData.setLegal(false);
        requestData.setAgree(true);

        Request request = requestService.addRequest(requestData);
        System.out.println(request);
    }

}
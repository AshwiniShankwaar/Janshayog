package com.BitGeekTalks.JanShayog.Request.service;

import com.BitGeekTalks.JanShayog.Request.datapayload.RequestData;
import com.BitGeekTalks.JanShayog.Request.entity.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class RequestServiceTest {
    @Autowired
    RequestService requestService;
    @Test
    void testRequestSave(){
        RequestData requestData = new RequestData();
        requestData.setAccountId(3L);
        requestData.setTitle("testing");
        requestData.setDate(LocalDate.of(2023, 4, 6));
        requestData.setTime(LocalTime.of(10, 0));
        requestData.setAddress("123 Main St");
        requestData.setTimePeriod("2 hours");
        requestData.setNoPeople(2L);
        requestData.setSkills("Cleaning,learning,writing,singing");
        requestData.setPayableAmount(50.0);
        requestData.setDescription("Clean my house");
        requestData.setLegal(false);
        requestData.setAgree(true);

        Request request = requestService.addRequest(requestData);
        System.out.println(request);
    }

    @Test
    void testFilter(){
        List<Request> r = requestService.getRequestBySkills("learning");
        Iterator<Request> it = r.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

    @Test
    void testGetRequestByAccountIdAndState(){
        List<Request> r = requestService.getRequestByAccountIdAndState(1,"complete");
        Iterator<Request> it = r.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
    @Test
    void testGetRequestById(){
        Request request = requestService.getRequestByRequestId(3);
        System.out.println(request);
    }
}
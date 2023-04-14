package com.BitGeekTalks.JanShayog.Request.service;

import com.BitGeekTalks.JanShayog.Request.datapayload.RequestData;
import com.BitGeekTalks.JanShayog.Request.entity.Payment;
import com.BitGeekTalks.JanShayog.Request.entity.Request;
import com.BitGeekTalks.JanShayog.Request.entity.Task;
import com.BitGeekTalks.JanShayog.Request.repo.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RequestService {
    @Autowired
    private RequestRepo requestRepo;

    public Request addRequest(RequestData requestData){
        Task task = new Task();
        task.setTitle(requestData.getTitle());
        task.setDate(requestData.getDate());
        task.setTime(requestData.getTime());
        task.setAddress(requestData.getAddress());
        task.setTimePeriod(requestData.getTimePeriod());
        task.setNumberOfPeople(requestData.getNoPeople());
        task.setSkills(requestData.getSkills());
        task.setAmount(requestData.getPayableAmount());
        task.setDescription(requestData.getDescription());
        task.setAgreed(requestData.getAgree());
        task.setLegal(requestData.getLegal());


        Payment payment = new Payment();
        payment.setAmount(task.getAmount());

        Request request = new Request();
        request.setAccountId(requestData.getAccountId());
        request.setRequestStatus("helperAssignment");
        request.setTaskId(task);
        payment.setRequestId(request);
        request.setPayment(payment);

        return requestRepo.save(request);
        //return SavedRequest;
    }
}

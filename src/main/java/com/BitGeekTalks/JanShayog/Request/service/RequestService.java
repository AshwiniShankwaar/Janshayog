package com.BitGeekTalks.JanShayog.Request.service;

import com.BitGeekTalks.JanShayog.Request.datapayload.RequestData;
import com.BitGeekTalks.JanShayog.Request.entity.*;
import com.BitGeekTalks.JanShayog.Request.repo.RequestCompleteRepo;
import com.BitGeekTalks.JanShayog.Request.repo.RequestRepo;
import com.BitGeekTalks.JanShayog.Request.repo.TaskRepo;
import com.BitGeekTalks.JanShayog.wallet.entity.Wallet;
import com.BitGeekTalks.JanShayog.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


@Service
public class RequestService {
    @Autowired
    private RequestRepo requestRepo;
    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private WalletService walletService;
    @Autowired
    private RequestCompleteRepo requestCompleteRepo;

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

        //save the payment amount in the server account
        Wallet wallet = walletService.walletByAccountId(request.getAccountId());
        System.out.println(wallet);
        wallet = walletService.performTransaction(
                wallet,request.getPayment().getAmount(),
                "Amount for request id: " + request.getId(),true);
        System.out.println(wallet);
        if(wallet==null){
            return null;
        }
        return requestRepo.save(request);
        //return SavedRequest;
    }

    public List<Request> getRequestBySkills(String skills) {
        List<Task> tasks = taskRepo.findBySkillsContaining(skills);
        Iterator<Task> iterator = tasks.iterator();
        List<Request> request = new ArrayList<Request>();
        while (iterator.hasNext()) {
            Request requestData = requestRepo.findByTaskId(iterator.next());
            request.add(requestData);
        }
        return request;
    }

    //get helperassignment state request by id
    //get underprocessing state request by id
    //get completed state request by id
    public List<Request> getRequestByAccountIdAndState(long accountId, String state){
        List<Request> requests = requestRepo
                .findByAccountIdAndRequestStatus(accountId,state);
        return requests;
    }

    //get all requests under helperassignment state
    public List<Request> getRequestInHelperAssignmentState(){
        return requestRepo.findByRequestStatus("helperAssignment");
    }
    //get request by request id
    public Request getRequestByRequestId(long requestId){
        return requestRepo.findById(requestId).orElse(null);
    }
    //delete a request by id till no helper assigned
    public String deleteRequestByRequestId(long requestId){
        Request request = requestRepo.findById(requestId).orElse(null);
        if(request != null){
            if(request.getHelperAssignments().size() <= 0){
                request.setRequestStatus("deleted");
                requestRepo.save(request);
                return "Deleted";
            }else{
                return "can not delete";
            }
        }
        return "request not found";
    }
    //update a request only till any helper is not joined
    public String updateRequestStatus(RequestData requestData,long requestId){
//        System.out.println(requestData);
//        System.out.println(requestId);
        Request request = requestRepo.findById(requestId).orElse(null);
        if(request != null){
            if(request.getHelperAssignments().size() <= 0 && !request.getRequestStatus().equals("deleted")){
                String message = "";
                request.getTaskId().setSkills(requestData.getSkills());
                request.getTaskId().setTime(requestData.getTime());
                request.getTaskId().setDate(requestData.getDate());
                request.getTaskId().setAddress(requestData.getAddress());
                request.getTaskId().setNumberOfPeople(requestData.getNoPeople());
                request.getTaskId().setTimePeriod(requestData.getTimePeriod());
                request.getTaskId().setTitle(requestData.getTitle());
                request.getTaskId().setDescription(requestData.getDescription());
                if(request.getTaskId().getAmount()!=requestData.getPayableAmount()){
                    if(request.getTaskId().getAmount()<requestData.getPayableAmount()){
//                        System.out.println("1"+(request.getTaskId().getAmount()<requestData.getPayableAmount()));
                        Wallet wallet = walletService.walletByAccountId(request.getAccountId());
//                        System.out.println(wallet);
                        wallet = walletService.performTransaction(
                                wallet, requestData.getPayableAmount()-request.getTaskId().getAmount(),
                                "Amount for request id: " + request.getId(),true);
//                        System.out.println(wallet);
                        if(wallet==null){
                            message = "amount not detected for updated request id: " + request.getId();
                        }else{
                            request.getTaskId().setAmount(requestData.getPayableAmount());
                            request.getPayment().setAmount(requestData.getPayableAmount());
                        }
                    }else if(request.getTaskId().getAmount()>requestData.getPayableAmount()){
//                        System.out.println("2"+(request.getTaskId().getAmount()>requestData.getPayableAmount()));
                        Wallet wallet = walletService.walletByAccountId(request.getAccountId());
//                        System.out.println(wallet);
                        wallet = walletService.performTransaction(
                                wallet, request.getTaskId().getAmount()-requestData.getPayableAmount(),
                                "Amount for request id: " + request.getId(),false);
//                        System.out.println(wallet);
                        if(wallet==null){
                            message = "amount not detected for updated request id: " + request.getId();
                        }else{
                            request.getTaskId().setAmount(requestData.getPayableAmount());
                            request.getPayment().setAmount(requestData.getPayableAmount());
                        }
                    }
                }

                if(!message.equals("")){
                    return message;
                }else{
                    requestRepo.save(request);
                }
                return "updated";
            }else{
                return "can not update";
            }
        }
        return "request not found";
    }
    //request get completed
    public Request UpdateRequestCompleteStatus(Request request){
        if(request != null){
            request.setRequestStatus("completed");
            return requestRepo.save(request);
        }
        return null;
    }
    //change request state
    //request get into processing state one the limit of people is closed
    public String checkHelperAlreadyAssignedInRequestById(Request request,long accountId){
        for (HelperAssign h:request.getHelperAssignments()) {
            if(h.getAccountId()==accountId){
                return "available";
            }else{
                return "notAvailable";
            }
        }
        return "notAvailable";
    }
    public String addHelperToRequest(long requestId,long accountId){
        Request request = requestRepo.findById(requestId).orElse(null);
        if(request!=null &&
                (!request.getRequestStatus().equals("deleted")
                        &&!request.getRequestStatus().equals("completed"))){
            if(checkHelperAlreadyAssignedInRequestById(request,accountId).equals("notAvailable")){
                if(request.getHelperAssignments().size()<request.getTaskId().getNumberOfPeople()){
                    HelperAssign helperAssign = new HelperAssign();
                    helperAssign.setAccountId(accountId);
                    helperAssign.setRequestId(request);
                    request.addHelper(helperAssign);
                    if(request.getHelperAssignments().size()==request.getTaskId().getNumberOfPeople()-1){
                        request.setRequestStatus("underProcessing");
                    }
                    requestRepo.save(request);
                    return "helper assigned";
                }else{
                    return "request is full";
                }
            }else{
                return "Helper already assigned to this Task";
            }
        }
        return "request not found";
    }
    //request get into complete state
    //conform request complete by generating otp

    public String generateOTP() {
        // Define the length of the OTP
        int length = 6;

        // Generate a random 6-digit number
        int randomNum = (int) (Math.random() * Math.pow(10, length));

        // Convert the random number to a string with leading zeros if necessary
        String otp = String.format("%0" + length + "d", randomNum);

        // Return the OTP
        return otp;
    }

    public RequestComplete checkOtpGeneration(long requestId){
        return requestCompleteRepo.findByRequestId(requestId);
    }
    //send a request for completing the request a otp will be send back to the user
    public String requestCompleteOtpGenerator(long requestId){
        Request request = requestRepo.findById(requestId).orElse(null);
        if(request!=null){
            //generate otp
            RequestComplete rc = checkOtpGeneration(requestId);
            if(rc!=null){
                requestCompleteRepo.delete(rc);
            }
            rc = new RequestComplete();
            rc.setRequestId(requestId);
            rc.setOtp(generateOTP());
            rc = requestCompleteRepo.save(rc);
            return rc.getOtp();
        }
        return "error while generating otp";
    }
    //this will happen to the helper side as they will enter the otp generated on the end of the requester
    //to make the request completed.
    public String requestComplete(long requestId, String Otp){
        RequestComplete request = requestCompleteRepo.findByRequestId(requestId);
        if(request!=null){
            //generate otp
            if(request.getOtp().equals(Otp)){
                Request r = requestRepo.findById(requestId).orElse(null);
                if(r!=null){
                    r = UpdateRequestCompleteStatus(r);
                    if(r!=null){
                        //delete otp
                        requestCompleteRepo.delete(request);

                        //perform payment
                        if(performPayment(r).equals("error")){
                            return "error while processing payment";
                        }
                        return performPayment(r);
                    }else{
                        return "error while verifying otp";
                    }
                }else{
                    return "error while getting request";
                }

            }else{
                return "Invalid OTP";
            }
        }
        return "Otp not generated";
    }
    //gt complain and send complaint email to the agent at that district or city

    //perform payment
    //change complain state
    public String performPayment(Request request){
        List<HelperAssign> helperAssigns = request.getHelperAssignments();
        double amount = request.getPayment().getAmount();
        double amountPerPerson = amount/request.getTaskId().getNumberOfPeople();
        Iterator<HelperAssign> i = helperAssigns.iterator();
        while (i.hasNext()){
            HelperAssign assign = i.next();
            long accountId = assign.getAccountId();
            Wallet wallet = walletService.getWalletByAccountId(accountId);
            wallet = walletService.performTransaction(
                    wallet,amountPerPerson,"payment for request id: "+request.getId(),false);
            if(wallet==null){
                System.out.println("Error paying account id "+accountId+" amount "+amountPerPerson);
                request.getPayment().setAmount(amount);
                request.setHelperAssignments(helperAssigns);
                requestRepo.save(request);
                return "error";
            }else{
                amount = amount-amountPerPerson;
                i.remove();
            }
        }
        request.getPayment().setStatus("PAID");
        request = requestRepo.save(request);
        System.out.println("Payment for request id: "+request.getId()+" completed");
        return "paid";
    }
}

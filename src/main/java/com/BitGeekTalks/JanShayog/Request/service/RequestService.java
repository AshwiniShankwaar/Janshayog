package com.BitGeekTalks.JanShayog.Request.service;

import com.BitGeekTalks.JanShayog.Request.datapayload.RequestData;
import com.BitGeekTalks.JanShayog.Request.entity.*;
import com.BitGeekTalks.JanShayog.Request.repo.HelperAssignRepo;
import com.BitGeekTalks.JanShayog.Request.repo.RequestCompleteRepo;
import com.BitGeekTalks.JanShayog.Request.repo.RequestRepo;
import com.BitGeekTalks.JanShayog.Request.repo.TaskRepo;
import com.BitGeekTalks.JanShayog.wallet.entity.Wallet;
import com.BitGeekTalks.JanShayog.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


@Service
public class RequestService {
    @Autowired
    private RequestRepo requestRepo;
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private HelperAssignRepo helperAssignRepo;
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
            LocalDateTime taskDateTime = LocalDateTime.of(requestData.getTaskId().getDate(), requestData.getTaskId().getTime());
            if (taskDateTime.isAfter(LocalDateTime.now())&&!requestData.getRequestStatus().equals("deleted")) {
                // task is before current date and time
                request.add(requestData);
            }

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
    public Request getLatestRequestByAccountId(long accountId){
        List<Request> requests = requestRepo.findByAccountId(accountId);
        if(requests.size()==0){
            return null;
        }else{
            //send the request that state is not deleted
            Request latestRequest = null;
            for (int i = requests.size()-1; i >= 0; i--) {
                Request request = requests.get(i);
                if (!request.getRequestStatus().equals("deleted")) {
                    latestRequest = request;
                    break;
                }
            }
            return latestRequest;
        }
    }
    //get all requests under helperassignment state
    public List<Request> getRequestInHelperAssignmentState(){
        List<Request> requests = requestRepo.findByRequestStatus("helperAssignment");
        Iterator<Request> iterator = requests.iterator();
        List<Request> requestData = new ArrayList<Request>();
        while (iterator.hasNext()) {
            Request request = iterator.next();
            LocalDateTime taskDateTime = LocalDateTime.of(request.getTaskId().getDate(),
                    request.getTaskId().getTime());
            if (taskDateTime.isAfter(LocalDateTime.now())&&
                    !request.getRequestStatus().equals("deleted")) {
                // task is before current date and time
                requestData.add(request);
            }
        }
        return requestData;
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
//            System.out.println(request);
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
//                    System.out.println(request);
                    System.out.println(requestRepo.save(request));
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
            request.setRequestStatus("Completed");
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
            System.out.println("ok to go");
            if(checkHelperAlreadyAssignedInRequestById(request,accountId).equals("notAvailable")){
                System.out.println("not available");
                if(request.getHelperAssignments().size()<request.getTaskId().getNumberOfPeople()){
                    System.out.println("open");
                    HelperAssign helperAssign = new HelperAssign();
                    helperAssign.setAccountId(accountId);
                    helperAssign.setRequestId(request);
                    request.addHelper(helperAssign);
                    if(request.getHelperAssignments().size()==request.getTaskId().getNumberOfPeople()){
                        request.setRequestStatus("underProcessing");
                    }
                    System.out.println(request.getHelperAssignments().size()+" "+(request.getTaskId().getNumberOfPeople()));
                    System.out.println(requestRepo.save(request));
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

    public String removeHelperFromRequest(long requestId, long accountId) {
        Request request = getRequestByRequestId(requestId);
        List<HelperAssign> list = request.getHelperAssignments();
        Iterator<HelperAssign> i = list.iterator();
        while (i.hasNext()) {
            HelperAssign helperAssign = i.next();
            if (helperAssign.getAccountId() == accountId) {
                i.remove();
                request.setHelperAssignments(list);
                requestRepo.save(request);
                helperAssignRepo.deleteById(helperAssign.getId());

                return "helper removed";
            }
        }
        return "helper not joined";
    }

    public List<Request> getRequestForHelper(long accountId,String state){
//        List<HelperAssign> l = helperAssignRepo.findByAccountId(accountId);
        List<Request> l = helperAssignRepo.findRequestsByHelperId(accountId);
        Iterator<Request> i = l.iterator();
        List<Request> requests = new ArrayList<Request>();
        while (i.hasNext()){
            Request request = i.next();
            if(request.getRequestStatus().equals(state)){
                requests.add(request);
            }
        }
        return requests;
    }

    public Request getLatestRequestForHelper(long accountId){
        List<Request> l = helperAssignRepo.findRequestsByHelperId(accountId);
        Iterator<Request> i = l.iterator();
        Request request = null;
        while (i.hasNext()){
            request = i.next();
            if(!request.getRequestStatus().equals("deleted")){
                break;
            }
        }
        return request;
    }
//get complete request by helperid
//public List<Request> getCompletedRequestForHelper(long accountId){
////        List<HelperAssign> l = helperAssignRepo.findByAccountId(accountId);
//    List<Request> l = helperAssignRepo.findRequestsByHelperId(accountId);
//    Iterator<Request> i = l.iterator();
//    List<Request> requests = new ArrayList<Request>();
//    while (i.hasNext()){
//        Request request = i.next();
//        if(request.getRequestStatus().equals("Completed")){
//            requests.add(request);
//        }
//    }
//    return requests;
//}
    //get latest request by helperid
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
        System.out.println(
                requestId+" "+Otp
        );
        RequestComplete request = requestCompleteRepo.findByRequestId(requestId);
        if(request!=null){
            //generate otp
            System.out.println(request.toString());
            if(request.getOtp().equals(Otp)){
                Request r = requestRepo.findById(requestId).orElse(null);
                System.out.println(r.toString());
                if(r!=null){
                    r = UpdateRequestCompleteStatus(r);
                    System.out.println(r);
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

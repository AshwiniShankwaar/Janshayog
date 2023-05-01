package com.BitGeekTalks.JanShayog.Request.controller;

import com.BitGeekTalks.JanShayog.Request.datapayload.RequestData;
import com.BitGeekTalks.JanShayog.Request.entity.Request;
import com.BitGeekTalks.JanShayog.Request.service.RequestService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/request")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class RequestController {
    @Autowired
    RequestService requestService;

    @PostMapping("/createRequest")
    public ResponseEntity<Map<String,Object>> createRequest(@RequestBody RequestData requestData){
        System.out.println(requestData.toString());
        Request request = requestService.addRequest(requestData);
//        //System.out.println(request);
        if(request!=null){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("status","created");
            map.put("request",request);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("status","error"));
    }

    @GetMapping("/latestRequest")
    public ResponseEntity<Map<String,Object>> getLatestRequest(@RequestParam("id") long id){
        Request request = requestService.getLatestRequestByAccountId(id);
        if(request == null || request.getRequestStatus().equals("deleted")){
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("request","no request"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("request",request));
    }
//    //http://localhost:8080/api/request/cleaning
    @GetMapping("/{skill}")
    public ResponseEntity<List<Request>> getRequestSkill(@PathVariable String skill){
        List<Request> requests = requestService.getRequestBySkills(skill);
        return ResponseEntity.status(HttpStatus.OK).body(requests);
    }
//
    //http://localhost:8080/api/request/getRequest?accountId=1&&state=completed
    @GetMapping("/getRequest")
    public ResponseEntity<List<Request>> getCompleteRequest(
            @RequestParam("accountId") long accountId,
            @RequestParam("state") String state){
        List<Request> r = requestService.getRequestByAccountIdAndState(accountId,state);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }
//    //http://localhost:8080/api/request/requestData?requestId=1
    @GetMapping("/requestData")
    public ResponseEntity<Map<String,Object>> getRequestById(@RequestParam("requestId") long requestId){
        Request request = requestService.getRequestByRequestId(requestId);
        if(request!=null && !request.getRequestStatus().equals("deleted")){
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("request",request));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("request","invalid request id"));
    }
//    //http://localhost:8080/api/request/openRequest
    @GetMapping("/openRequest")
    public ResponseEntity<List<Request>> getOpenRequest(){
        List<Request> r = requestService.getRequestInHelperAssignmentState();
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }
    @DeleteMapping("/deleteRequest")
    public ResponseEntity<Map<String,Object>> deleteRequestById(@RequestParam("requestId") long requestId){
        String status = requestService.deleteRequestByRequestId(requestId);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message",status));
    }
    @PostMapping("/updateRequest")
    public ResponseEntity<Map<String,Object>> updateRequestById(
            @RequestBody RequestData requestData,
            @RequestParam("requestId") long requestId){
//        System.out.println(requestData);
//        System.out.println(requestId);
        String message = requestService.updateRequestStatus(requestData,requestId);
        if(message.equals("updated")){
            Request request = requestService.getRequestByRequestId(requestId);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("message",message);
            map.put("request",request);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message",message));
    }
    @GetMapping("/addHelper")
    public ResponseEntity<Map<String,Object>> addHelper(
            @RequestParam("requestId") long requestId,
            @RequestParam("accountId") long accountId){
        System.out.println(requestId+" "+accountId);
        String helperAssignment = requestService.addHelperToRequest(requestId,accountId);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message",helperAssignment));
    }

    @GetMapping("/removeHelper")
    public ResponseEntity<Map<String,Object>> removeHelper(
            @RequestParam("requestId") long requestId,
            @RequestParam("accountId") long accountId){
        System.out.println(requestId+" "+accountId);
        String helperAssignment = requestService.removeHelperFromRequest(requestId,accountId);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message",helperAssignment));
    }
    @GetMapping("/getRequestHelper")
    public ResponseEntity<List<Request>> getRequestHelper(
            @RequestParam("accountId") long accountId,@RequestParam("state") String state){
        List<Request> requests = requestService.getRequestForHelper(accountId,state);
        return ResponseEntity.status(HttpStatus.OK).body(requests);
    }
    @GetMapping("/getLatestRequestHelper")
    public ResponseEntity<Map<String,Object>> getLatestRequestHelper(
            @RequestParam("accountId") long accountId){
        Request requests = requestService.getLatestRequestForHelper(accountId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap("request",requests));
    }
//
    @GetMapping("/requestComplete")
    public ResponseEntity<Map<String,Object>> requestCompleteOtpGeneration(@RequestParam("requestId") long requestId){
        String requestCompleteOtp = requestService.requestCompleteOtpGenerator(requestId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap("message",requestCompleteOtp));
    }
    @GetMapping("/verifyRequestOtp")
    public ResponseEntity<Map<String,Object>> verifyRequestOtp(
            @RequestParam("requestId") long requestId,
            @RequestParam("otp") String otp){
        String requestComplete = requestService.requestComplete(requestId, otp);
        System.out.println(requestComplete);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message",requestComplete));
    }
}

package com.email.emailapi.controllers;


import com.email.emailapi.model.EmailRequest;
import com.email.emailapi.model.EmailResponse;
import com.email.emailapi.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/welcome") //you didn't specify which method, there it is get method
    public String welcome(){
        return "this is my email api";

    }

    //api to send email
    @RequestMapping(value="/sendemail",method = RequestMethod.POST)
    public ResponseEntity<?> sendemail(@RequestBody EmailRequest request){
        System.out.println(request);
        boolean result=this.emailService.sendEmail(request.getSubject(),request.getMessage(),request.getTo());
        System.out.println("result is");
        System.out.println(result);
        if(result) return ResponseEntity.ok(new EmailResponse("Email is sent successfully..."));
        else {
            System.out.println("yess");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("email not sent..."));
        }
    }
}

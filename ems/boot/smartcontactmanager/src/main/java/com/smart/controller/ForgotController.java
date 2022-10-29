package com.smart.controller;

import com.smart.dao.ContactRepository;
import com.smart.entities.Contact;
import com.smart.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class ForgotController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder ;
    Random random=new Random(1000);
    @GetMapping("/forgot")
    public String openEmailForm(Model m)
    {
        m.addAttribute("title","Employee forgot password");
        return "employee/forgot_email_form";
    }

    @PostMapping("/send-otp")
    public String sendOTP(@RequestParam("email") String email, HttpSession session)
    {
        System.out.println(email);

        int otp=random.nextInt(9999);
        System.out.print(otp);


        String subject="OTP From EMS";
        String message="<div style='boder:1px solid #e2e2e2; padding:20px'>"
                + "<h1>"
                + "OTP is"
                +"<b>"+otp
                +"</n>"
                +"</b>"
                +"</h1>"
                + "</div>";
        String to=email;
        boolean flag=this.emailService.sendEmail(subject,message,to);
        if(flag)
        {
            session.setAttribute("myotp", otp);
            session.setAttribute("email", email);
            return "employee/varify_otp";
        }
        else
        {
            session.setAttribute("message", "Check your email id");
            return "employee/forgot_email_form";
        }
//        return "employee/varify_otp";
    }



    //verifo otp
    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam("otp") int otp,HttpSession session)
    {

        int myOtp=(int)session.getAttribute("myotp");
        String email=(String) session.getAttribute("email");
        if(myOtp==otp)
        {
            //password change
           Contact user=this.contactRepository.getEmployeeByUserName(email);
            if(user==null)
            {
                session.setAttribute("message", "your does not exist with this email");
                return "employee/forgot_email_form";
            }
            else
            {
                return "employee/password_change_form";
            }

        }
        else
        {
            session.setAttribute("message", " You have enterted Wrong OTP");
            return "varify_otp";
        }

    }


    @PostMapping("/change-password")
    public String changePassword(@RequestParam("newpassword") String newpassword,HttpSession session)
    {
        String email=(String) session.getAttribute("email");
        Contact user=	this.contactRepository.getEmployeeByUserName(email);
        user.setPassword(this.bCryptPasswordEncoder.encode(newpassword));
        this.contactRepository.save(user);

        return "redirect:/sign?change=password change successfully";
    }



}

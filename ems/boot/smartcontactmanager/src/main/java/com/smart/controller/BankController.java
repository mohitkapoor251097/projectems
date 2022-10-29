package com.smart.controller;

import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.helper.Message;
import com.smart.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/employee")
public class BankController {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;


    @ModelAttribute
    public void addCommonData(Model model,Principal principal)
    {
        String userName=principal.getName();
        System.out.println(userName);
        //get the employee from userName
        Contact contact=this.contactRepository.getEmployeeByUserName(userName);
        System.out.print(contact);

        String userName1=principal.getName();
        System.out.println(userName1);
        //get the user from userName
        User user1=this.userRepository.getuserByUserName(userName);
        contact.getUser();
        System.out.print("user"+contact);

        model.addAttribute("user",user1);
        model.addAttribute("emp",contact);
        model.addAttribute("title","Employee DashBoard");
    }
    @GetMapping("/bank")
    public String Bank(Model model, Principal principal)
    {
//        String userName=principal.getName();
//        System.out.println(userName);
//        //get the employee from userName
//        Contact contact=this.contactRepository.getEmployeeByUserName(userName);
//        System.out.print(contact);
//
//        String userName1=principal.getName();
//        System.out.println(userName1);
//        //get the user from userName
//        User user1=this.userRepository.getuserByUserName(userName);
//        contact.getUser();
//        System.out.print("user"+contact);
//
//        model.addAttribute("user",user1);
//        model.addAttribute("emp",contact);
//        model.addAttribute("title","Employee DashBoard");
        return "employee/bank";
    }
    @GetMapping("/hrteam")
    public String HrTeam()
    {
        return "employee/hrteam";
    }
    @GetMapping("/mydata")
    public String MyData()
    {
        return "employee/mydata";
    }


    @GetMapping("/feedback")
    public String FeedBack()
    {
        return "employee/feedback";
    }

    @PostMapping("/feedback-done")
    public String SubmitFeedBack(@RequestParam("name") String name, @RequestParam("code") String code, @RequestParam("feedback") String feedback, HttpSession session )
    {
        System.out.println("Name"+name);
        System.out.println("code"+code);
        System.out.println("desc"+feedback);
        String subject=name+ " "+"Your Response have been submitted !! ";
        String message="<div style='boder:1px solid #e2e2e2; padding:20px'>"
                + "<h1>"
                + ""
                +"<b>"+code
                +"<br>"
                +"<br>"
                +"</n>"
                +"</n>"
                +"<b>"
                +"</n>"
                +"<b>"+feedback
                +"</b>"
                +"</h1>"
                + "</div>";
        String to="kapoorm570@gmail.com";
        boolean flag=this.emailService.sendEmail(subject,message,to);
        if(flag)
        {
            session.setAttribute("message", new Message("Thanks for your response","success"));

            return "redirect:/employee/feedback";
        }
        else
        {
            session.setAttribute("message", new Message("something went wrong on server","danger"));
            return "redirect:/employee/feedback";
        }

//        return "employee/feedback";
    }


}

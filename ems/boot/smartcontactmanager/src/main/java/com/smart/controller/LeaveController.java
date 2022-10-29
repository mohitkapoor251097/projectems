package com.smart.controller;

import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.helper.Message;
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
import java.security.Principal;

@Controller
@RequestMapping("/employee")
public class LeaveController {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;
//    @GetMapping("/leave")
//    public String openEmailForm()
//    {
//        return "employee/leave_form";
//    }

    @GetMapping("/leave")
    public String getEmp(Model model, Principal principal)
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
        return "employee/leave_form";
    }

    @PostMapping("/leave-done")
        public String leaveSubmit(@RequestParam("doa") String doa,@RequestParam("sub") String sub,@RequestParam("mess")  String mess, HttpSession session)
        {
            System.out.println(sub);
        System.out.println(mess);

        String subject=sub+"  "+doa;
        String message="<div style='boder:1px solid #e2e2e2; padding:20px'>"
                + "<h1>"
                + ""
                +"<b>"+mess
                +"</n>"
                +"</b>"
                +"</h1>"
                + "</div>";
        String to="kapoorm570@gmail.com";
        boolean flag=this.emailService.sendEmail(subject,message,to);
        if(flag)
        {
            session.setAttribute("message", new Message("Leave Submiited Succesfully  !! Please wait for approval....","success"));

            return "redirect:/employee/leave";
        }
        else
        {
            session.setAttribute("message", new Message("something went wrong on server","danger"));
            return "redirect:/employee/leave";
        }
        //return "";
    }
}

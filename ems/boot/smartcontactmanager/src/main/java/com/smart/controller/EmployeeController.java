package com.smart.controller;

import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.helper.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

//    @ModelAttribute
//    public void addCommonData(Model model,Principal principal1)
//    {
//        String userName=principal1.getName();
//        System.out.println(userName);
//        //get the user from userName
//        User user1=this.userRepository.getuserByUserName(userName);
//        System.out.print("user"+user1);
//
//        model.addAttribute("user",user1);
//    }S

    @GetMapping("/index")
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
            return "employee/index";
    }

//    @PostMapping("/emplogin")
//    public String empLogin()
//    {
//
//        return "";
//    }

//    @GetMapping("/attendence")
//    public String AttensdenceForm()
//    {
//        return "employee/attendence";
//    }




    //open Setting handler
    @GetMapping("/settings")
    public String openSettings()
    {
        return "employee/settings";
    }

    //change password handler
    @PostMapping("/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, Principal principal, HttpSession session)
    {
        System.out.println(oldPassword+newPassword);
        String userName=principal.getName();
        System.out.println(userName);
        //get the employee from userName
        Contact currentEmployee=this.contactRepository.getEmployeeByUserName(userName);
        System.out.print(currentEmployee.getPassword());
        if(this.bCryptPasswordEncoder.matches(oldPassword, currentEmployee.getPassword()))
        {
            //change password
            currentEmployee.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
            this.contactRepository.save(currentEmployee);
            session.setAttribute("message", new Message("Your Password is Successfully Changed !! ","success"));
        }
        else
        {
            session.setAttribute("message", new Message("Wrong old password","danger"));
            return "redirect:/employee/settings";
        }
        return "redirect:/sign";
    }
}

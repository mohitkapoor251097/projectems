package com.smart.controller;

import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void addCommonData(Model model,Principal principal)
    {
        String userName=principal.getName();
        System.out.println(userName);
        //get the user from userName
        User user=this.userRepository.getuserByUserName(userName);
        System.out.print(user);

        model.addAttribute("user",user);
    }

    @GetMapping("/home")
    public String getEmp(Model model, Principal principal)
    {
        	String userName=principal.getName();
	System.out.println(userName);
    //get the employee from userName
	Contact contact=this.contactRepository.getEmployeeByUserName(userName);
System.out.print(contact);
        model.addAttribute("emp",contact);
        model.addAttribute("title","Employee DashBoard");
            return "employee/home";
    }


}

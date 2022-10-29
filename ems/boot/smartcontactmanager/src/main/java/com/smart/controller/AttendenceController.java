package com.smart.controller;

import com.smart.dao.AttendenceRepository;
import com.smart.dao.ContactRepository;
import com.smart.dao.UserRepository;
import com.smart.entities.Attendence;
import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.excel.UserExcelExporter;
import com.smart.helper.Message;
import com.smart.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class AttendenceController {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AttendenceRepository attendenceRepository;

    @ModelAttribute
    public void addCommonData(Model model,Principal principal)
    {
        String userName=principal.getName();
        System.out.println(userName);
        //get the user from userName
        User user=this.userRepository.getuserByUserName(userName);
      //  System.out.print(user);

        model.addAttribute("user",user);
    }
    @GetMapping("/attendence")
    public String AttensdenceForm(Model model, Principal principal)
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
        return "employee/attendence";
    }



    @PostMapping("/process-attendence")
    public String processContact(@ModelAttribute Attendence attendence, Principal principal, HttpSession session)
    {
        System.out.println(attendence.getName());
        System.out.println(attendence.getCode());
        System.out.println(attendence.getDoa());
        try {

            String name=principal.getName();

           Contact contact=this.contactRepository.getEmployeeByUserName(name);
            System.out.println(contact);
            attendence.setContact(contact);
//            user.getContacts().add(contact);
//            this.userRepository.save(user);
//            System.out.print("Contact Added");
            LocalDateTime current = LocalDateTime.now();
            System.out.println("current date and time : "+
                    current);
            attendence.setTime(current.toString());
            contact.getAttendence().add(attendence);
            this.contactRepository.save(contact);





            session.setAttribute("message", new Message("Your Attendence  has been Submitted Suceessfully !! ","success"));
            return "redirect:/employee/attendence";
        }
        catch(Exception e)
        {
            e.getMessage();
            //message erorr...
            session.setAttribute("message", new Message("something went wrong try again","danger"));
            return "redirect:/employee/attendence";
        }

    }


    @GetMapping("/show-attendence")
    public String showAttendence(Model m,Principal principal)
    {
       // String userName=principal.getName();
//			User user=this.userRepository.getuserByUserName(userName);
//		    List<Contact> contacts=	this.contactRepository.findContactsByUser(user.getId());
        	String userName=principal.getName();
	Contact contact=this.contactRepository.getEmployeeByUserName(userName);
        System.out.println(contact.getcId());
	List<Attendence> attendences=this.attendenceRepository.findAttendenceByEmployee(contact.getcId());

        System.out.println(attendences);
        for(Attendence at:attendences)
        {
            System.out.println(at.getDoa());
        //    System.out.println();
        }
        m.addAttribute("title","Employee DashBoard");
        m.addAttribute("emp",contact);
        m.addAttribute("attendence",attendences);
        return "employee/show-attendence";
    }


//    @GetMapping("/show-attendence")
//    public String showAttendenceExcel(Model m,Principal principal)
//    {
//        // String userName=principal.getName();
////			User user=this.userRepository.getuserByUserName(userName);
////		    List<Contact> contacts=	this.contactRepository.findContactsByUser(user.getId());
//        String userName=principal.getName();
//        Contact contact=this.contactRepository.getEmployeeByUserName(userName);
//        System.out.println(contact.getcId());
//        List<Attendence> attendences=this.attendenceRepository.findAttendenceByEmployee(contact.getcId());
//
//        System.out.println(attendences);
//        for(Attendence at:attendences)
//        {
//            System.out.println(at.getDoa());
//            //    System.out.println();
//        }
//        m.addAttribute("emp",contact);
//        m.addAttribute("attendence",attendences);
//        return "employee/show-attendence";
//    }



    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response,Model m,Principal principal) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headervalue = "attachment; filename=Employee_Attendence.xlsx";

        response.setHeader(headerKey, headervalue);
        String userName=principal.getName();
        Contact contact=this.contactRepository.getEmployeeByUserName(userName);
        System.out.println(contact.getcId());
        List<Attendence> attendences=this.attendenceRepository.findAttendenceByEmployee(contact.getcId());

        // List<Student> listStudent = studentRepo.findAll();
        UserExcelExporter exp = new UserExcelExporter(attendences);
        exp.export(response);

    }

    @GetMapping("/timesheet")
    public String timeSheet(Model model,Principal principal)
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
        return "employee/timeSheet";
    }

    @PostMapping("/card")
    public  String Card(HttpSession session)
    {
   String subject="Timesheet has been submitted to you for approval";
        String message="<div style='boder:1px solid #e2e2e2; padding:20px'>"
                + "<h1>"
                + ""
                +"<b>"+"Your Timesheet has been submitted "
                +"</n>"
                +"</b>"
                +"</h1>"
                + "</div>";
        String to="kapoorm570@gmail.com";
        boolean flag=this.emailService.sendEmail(subject,message,to);
        if(flag)
        {
            session.setAttribute("message", new Message("Timesheet submitted Succesfully  !! Please wait for approval....","success"));

            return "redirect:/employee/timesheet";
        }
        else
        {
            session.setAttribute("message", new Message("something went wrong on server","danger"));
            return "redirect:/employee/timesheet";
        }

    }
}

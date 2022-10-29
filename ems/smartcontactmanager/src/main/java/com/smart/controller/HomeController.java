package com.smart.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.AbstractPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.helper.Message;

@Controller
public class HomeController {
	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@GetMapping("/test")
//	@ResponseBody
//	public String test()
//	{
//		User user=new User();
//		user.setName("mohiyt");
//		user.setEmail("kapoorm570@gmaul");
//		
//		Contact contact=new Contact();
//		user.getContacts().add(contact);
//		
//		userRepository.save(user);
//		
//		
//		return "working";
//	}
	
	@Autowired
	private  BCryptPasswordEncoder  passwordEncoder;
	@Autowired
 private UserRepository userRepository;
	
	@RequestMapping("/")
	public String home(Model model)
	{
		model.addAttribute("title","Home Page");
		return "home";
	}
	
	@RequestMapping("/about")
	public String about(Model model)
	{
		model.addAttribute("title","About Page");
		return "about";
	}
	
	@RequestMapping("/signup")
	public String signup(Model model)
	{
		model.addAttribute("title","SignUp Page");
		model.addAttribute("user",new User());
		return "signup";
	}
	
	@PostMapping("/do_register")
	public String register(@Valid @ModelAttribute("user") User user,BindingResult result1,@RequestParam(value="aggrement",defaultValue="false")boolean aggrement,Model model,HttpSession session)
	{
		try
		{
			System.out.print(aggrement);
			System.out.print(user);
			if(!aggrement)
			{
				System.out.print("not check");
				throw new Exception("Please Check Terms & Condition");
			}
			if(result1.hasErrors())
			{
				System.out.print(result1.toString());
				model.addAttribute("user",user);
				return "signup";
			}
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User result=this.userRepository.save(user);
			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Successfully Register !!","alert-success"));
			return "signup";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("Something went wrong"+e.getMessage(),"alert-danger"));
			return "signup";
		}
		//return "signup";
		
		
		
	}
	
	
	
	//custom login
	@GetMapping("/signin")
	public String customLogin(Model model)
	{
		model.addAttribute("title","Login Page");
		return "login";
	}

	@GetMapping("/sign")
	public String getEmp(Model m)
	{

		m.addAttribute("title","Employee Login");
		return "employee/empPanel";
	}

}

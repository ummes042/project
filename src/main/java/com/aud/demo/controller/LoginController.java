 package com.aud.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.aud.demo.model.Reviewer;
import com.aud.demo.model.Role;
import com.aud.demo.model.User;
import com.aud.demo.repository.RoleRepository;
import com.aud.demo.service.AuthorService;





@Controller
public  class LoginController {
	
	
	
	@Autowired
	private AuthorService authorService;
	
	
	@Autowired
    private RoleRepository roleRepository;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(HttpSession session){
		
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println("Authname"+auth.getName());
		
		User author = authorService.findAuthorByEmail(auth.getName());
		logger.info("User details:{}",author);
		session.setAttribute("author", author);
		if(author!=null){
			org.springframework.security.core.userdetails.User principal =(org.springframework.security.core.userdetails.User) auth.getPrincipal();
			logger.info("Logger in user details are:{}",principal.toString());
			modelAndView.addObject("userName", "Logged in as:" + author.getFname() + " " + author.getLname() );
			
			
			
			Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
			 
			        List<String> roles = new ArrayList<String>();
			 
			        for (GrantedAuthority a : authorities) {
			            roles.add(a.getAuthority());
			        }
			        System.out.println(roles);    
			
			if(!author.isVerified()){
			modelAndView.addObject("successMessage", "User has been registered successfully");
			
			modelAndView.setViewName("verifyOTP");
			return modelAndView;
				
			}else {
				if(roles.contains("AUTHOR")) {
					System.out.println("reached USER ROle");
					modelAndView.setViewName("redirect:/author");
					return modelAndView;
				}else if(roles.contains("ADMIN")) {
					System.out.println("reached Admin ROle");
					modelAndView.setViewName("redirect:/officer");
					return modelAndView;
				}
				else if(roles.contains("REVIEWER")) {
					System.out.println("reached REVIEWER ROle");
					modelAndView.setViewName("redirect:reviewer");
					return modelAndView;
			}
			
			}
		} 
//			
	  else {
			System.out.println("reached new author");
			modelAndView.addObject("userName", "" );
			modelAndView.addObject("successMessage", "");
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		
		return modelAndView;
		

	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User author = authorService.findAuthorByEmail(auth.getName());
		ModelAndView modelAndView = new ModelAndView();
		if(author==null) {   //new author
		
		User author1 = new User();
		modelAndView.addObject("user", author1);
		modelAndView.setViewName("registration");
		return modelAndView;
		}
		
		modelAndView.addObject("userName", "Logged in as:" + author.getFname() + " " + author.getLname() );
		modelAndView.setViewName("login");
		return modelAndView;
		
	}
	
	@Transactional
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewAuthor(@Valid User author, BindingResult bindingResult,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		User authorExits = authorService.findAuthorByEmail(author.getEmail());
		if (authorExits != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			
			author.setVerified(false);
			author.setActive(1);
			Random rnd = new Random();
			int otp = 100000 + rnd.nextInt(900000);
			author.setOtp(otp);
			
			Role author_role = new Role(2,"AUTHOR");
		   
			
			
			author.setPassword( new BCryptPasswordEncoder().encode(author.getPassword()));
			long id = authorService.saveAuthor(author);
			author.setId(id);
			author.setRoles(new HashSet<Role>(Arrays.asList(author_role)));
			authorService.saveAuthor(author);
			
			
//			Mail mail = new Mail();
//			mail.sendMail(author);
			
			modelAndView.addObject("successMessage", "OTP sent to your registered email, Login to verify email");
			
//			modelAndView.addObject("user", new User());
//			autoLogin(user,request );
			
			modelAndView.setViewName("login");
			
		}
		return modelAndView;
	}
	
<<<<<<< HEAD
=======
	@RequestMapping(value="/reviewer/registration", method = RequestMethod.GET)
	public ModelAndView reviewerregistration(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Reviewer reviewer = reviewerservice.findReviewerByEmail(auth.getName());
		ModelAndView modelAndView = new ModelAndView();
		if(reviewer==null) {
		
		Reviewer author1 = new Reviewer();
		modelAndView.addObject("reviewer", author1);
		modelAndView.setViewName("reviewerregistration");
		return modelAndView;
		}
		
		modelAndView.addObject("userName", "Logged in as:" + reviewer.getFname() + " " + reviewer.getLname() );
		modelAndView.setViewName("login");
		return modelAndView;
		
	}
	
	@Transactional
	@RequestMapping(value = "/reviewer/registration", method = RequestMethod.POST)
	public ModelAndView createNewReviewer(@Valid Reviewer reviewer, BindingResult bindingResult,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Reviewer reviewerExits = reviewerservice.findReviewerByEmail(reviewer.getEmail());
		if (reviewerExits != null) {
			bindingResult
					.rejectValue("email", "error.reviewer",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("reviewerregistration");
		} else {
			
			reviewer.setVerified(false);
			reviewer.setActive(1);
			Random rnd = new Random();
			int otp = 100000 + rnd.nextInt(900000);
			reviewer.setOtp(otp);
			
			Role author_role = new Role(3,"REVIEWER");
		   
			
			
			reviewer.setPassword( new BCryptPasswordEncoder().encode(reviewer.getPassword()));
			long id = reviewerservice.saveReviewer(reviewer);
			reviewer.setId(id);
			//reviewer.setRoles(new HashSet<Role>(Arrays.asList(author_role)));
			reviewerservice.saveReviewer(reviewer);
			
			
//			Mail mail = new Mail();
//			mail.sendMail(author);
			
			modelAndView.addObject("successMessage", "OTP sent to your registered email, Login to verify email");
			
//			modelAndView.addObject("user", new User());
//			autoLogin(user,request );
			
			modelAndView.setViewName("login");
			
		}
		return modelAndView;
	}
	
>>>>>>> 11c2d1ac685f9edabb6ca776650aa4f409fa9cc4
	
	
	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
	public ModelAndView validateNewAuthor(@RequestParam("otp") int otp) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User author = authorService.findAuthorByEmail(auth.getName());
				
		if (author != null) {
		 if(author.getOtp()==otp) {
			author.setVerified(true);
			Role author_role = new Role(2,"AUTHOR");
			
			
			author.setRoles(new HashSet<Role>(Arrays.asList(author_role)));
			
	
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
			authorities.add(new SimpleGrantedAuthority("USER"));
			Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),auth.getCredentials(),authorities);
			SecurityContextHolder.getContext().setAuthentication(newAuth);
			
			authorService.updateAuthor(author);
						
			//check if some data is available pertaining to the student and return view accordingly.
			
			modelAndView.setViewName("redirect:/author");
			
		 }else {
			
			 modelAndView.addObject("successMessage", "Wrong OTP, Kindly verify.");
			 modelAndView.setViewName("verifyOTP");
			
				
		 }
		 
		}
		
		return modelAndView;
	}
	
//	@RequestMapping(value = "/validateReviewer", method = RequestMethod.POST)
//	public ModelAndView validateNewReviewer(@RequestParam("otp") int otp) {
//		ModelAndView modelAndView = new ModelAndView();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User reviewer = reviewerservice.findReviewerByEmail(auth.getName());
//		
//		if (reviewer != null) {
//		 if(reviewer.getOtp()==otp) {
//			 reviewer.setVerified(true);
//			Role author_role = new Role(3,"REVIEWER");
//		  
//			
//			reviewer.setRoles(new HashSet<Role>(Arrays.asList(author_role)));
//			
//	
//			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
//			authorities.add(new SimpleGrantedAuthority("USER"));
//			Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),auth.getCredentials(),authorities);
//			SecurityContextHolder.getContext().setAuthentication(newAuth);
//			
//			reviewerservice.updateReviewer(reviewer);
//						
//			//check if some data is available pertaining to the student and return view accordingly.
//			
//			modelAndView.setViewName("redirect:/reviewer");
//			
//			
//		 }else {
//			
//			 modelAndView.addObject("successMessage", "Wrong OTP, Kindly verify.");
//			 modelAndView.setViewName("verifyotpreviewer");
//				
//		 }
//		 
//	}
//		
//		return modelAndView;
//	}
	
	

	
	
	
	public void logout() {
	    HttpServletRequest request =
	        ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
	            .getRequest();
	    new SecurityContextLogoutHandler().logout(request, null, null);
	}
	
	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User user = userService.findUserByEmail(auth.getName());
//		modelAndView.addObject("userName", "Welcome Admin" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
//		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("result");
		return modelAndView;
	}
	
	
	
	
	@RequestMapping(value="/forgotPassword", method = RequestMethod.GET)
	public ModelAndView forgotPassword(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = authorService.findAuthorByEmail(auth.getName());
		ModelAndView modelAndView = new ModelAndView();
		if(user==null) {
		
		User user1 = new User();
		modelAndView.addObject("user", user1);
		modelAndView.setViewName("forgotPassword");
		return modelAndView;
		}
		
		modelAndView.addObject("userName", "Logged in as:" + user.getFname() + " " + user.getLname() );
		modelAndView.setViewName("login");
		return modelAndView;
		
			
		
		
		
	}
	
	@RequestMapping(value="/forgotPassword", method = RequestMethod.POST)
	public ModelAndView forgotPasswordEmail(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = authorService.findAuthorByEmail(auth.getName());
		ModelAndView modelAndView = new ModelAndView();
		if(user==null) {
		
		User user1 = new User();
		modelAndView.addObject("user", user1);
		modelAndView.setViewName("forgotPassword");
		return modelAndView;
		}
		
		modelAndView.addObject("userName", "Logged in as:" + user.getFname() + " " + user.getLname() );
		modelAndView.setViewName("login");
		return modelAndView;
				
	}
	
	
}
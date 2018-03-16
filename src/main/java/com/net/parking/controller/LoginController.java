package com.net.parking.controller;

import java.util.Properties;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.net.parking.model.User;
//import com.net.parking.service.EmailService;
import com.net.parking.service.JWTKey;
import com.net.parking.service.UserDaoService;

@Controller
public class LoginController {
	
	@Autowired private UserDaoService userDaoService;
	@Autowired private MessageSource messageSource;
//	@Autowired private EmailService emailService;
	private @Autowired JWTKey jwtKey;
	private final static Logger logger = Logger.getLogger(LoginController.class);
	private Properties properties;
	
	@RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
		try{
			model.addAttribute("name", name);
			properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("wsapi.properties"));
        	User user = userDaoService.findUserByEmail(properties.getProperty("find.user.by.userid", ""), "rgrajeshu");
        	logger.info("inside operation hello service : --------------------------"+user.getEmail());
		}catch(Exception exception){	exception.printStackTrace();	}
        return "hello";
    }
	
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = null;
		try{
			modelAndView = new ModelAndView();
			modelAndView.setViewName("unprotected/login");
		}catch(Exception exception){	exception.printStackTrace();	}
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = null;
		try{
			modelAndView = new ModelAndView();
			User user = new User();
			modelAndView.addObject("user", user);
			modelAndView.setViewName("unprotected/registration");
		}catch(Exception exception){	exception.printStackTrace();	}
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		try{
			properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("wsapi.properties"));
			modelAndView.setViewName("unprotected/registration");
			jwtKey.config();// token generation
        	User userExists = userDaoService.findUserByEmail(properties.getProperty("find.user.by.userid", ""), user.getEmail());
			if (userExists != null) {
				bindingResult.rejectValue("email", "error.user",messageSource.getMessage("error.user.msg", null, null));
			}
			if (bindingResult.hasErrors()) {
				modelAndView.setViewName("unprotected/registration");
			} else {
				user.setStatus(true);
				userDaoService.saveUser(properties.getProperty("user.save", ""), user);
				//Properties propertiesemail = new Properties();
				//propertiesemail.load(getClass().getClassLoader().getResourceAsStream("email.properties"));
				//emailService.sentEmail(user, properties.getProperty("email.confirm", ""), propertiesemail);
				
				modelAndView.addObject("successMessage", messageSource.getMessage("register.msg", null, null));
				modelAndView.addObject("user", new User());
				modelAndView.setViewName("unprotected/registration");
			}
		}catch(Exception exception){	exception.printStackTrace();	}
		return modelAndView;
	}
	
	
	
	@RequestMapping(value="/parking/panelgeneral", method = RequestMethod.GET)
	public ModelAndView adminhome(){
		ModelAndView modelAndView = null;
		try{
			jwtKey.config();
			modelAndView = new ModelAndView();
			modelAndView.setViewName("/protected/panelgeneral");
			
		}catch(Exception exception){	exception.printStackTrace();	}
		return modelAndView;
	}
	
}

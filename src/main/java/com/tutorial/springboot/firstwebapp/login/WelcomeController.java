package com.tutorial.springboot.firstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

//	Logger logger= LoggerFactory.getLogger(getClass());
			
//	@RequestMapping("login")
	//public String gotoLoginPage(@RequestParam String name, ModelMap model) {
		
//		model.put("name", name);
//		logger.debug("Request param is {}",name);
//		logger.info("info level");
//		logger.warn("warn level");
//		return "login";
		
//	private AuthenticationService as;
//	
//	public LoginController(AuthenticationService as) {
//		this.as = as;
//	}

	@RequestMapping(value="/", method= RequestMethod.GET)
		public String gotoWelcomePage(ModelMap mp) {
		mp.put("name", getLoggedUsername());
			return "welcome";
		}
	
	
	private String getLoggedUsername() {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		
		return authentication.getName();
	}
//	@RequestMapping(value="login", method= RequestMethod.POST)
//	public String gotoWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap mp ) {
//		
//		if(as.authenticate(name, password)) {
//			mp.put("name", name);
//			mp.put("password", password);
//			return "welcome";
//		}
//		
//		mp.put("errormsg", "Invalid ! Please try again");
//		return "login";
//	}
}

package com.tutorial.springboot.firstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SayHelloController {

	@RequestMapping("say-hello")
	@ResponseBody
	public String sayHello() {
		return "hello, how r u?";
	}
	
	@RequestMapping("say-hello-html")
	@ResponseBody
	public String sayHelloHtml() {
		
		StringBuffer sb= new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>First HTML page</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("First HTML page");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
	
		
	@RequestMapping("say-hello-jsp")
	public String sayHelloJSP() {
		return "sayHello";
		
	}
		
	
		
		
}

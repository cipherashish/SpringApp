package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WelcomeController {
	//@Autowired 
	//private welcomeservice service;
			
	@RequestMapping("/")
	public String index() {
		System.out.println("AppCon-WEB");
		return "index";
	}
	

}
@Component
class welcomeservice
{
	public String welcomeretrieve()
	{
		return "index";
	}
}
package alltime.login.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import alltime.common.common.CommandMap;
import alltime.login.service.LoginService; 

@Controller
public class LoginController { 
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	@RequestMapping(value="/loginForm.do")
    public ModelAndView loginForm(CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/login/loginForm"); 
    	return mv;
    }
	@RequestMapping(value="/login_redirect.do")
    public ModelAndView login_redirect(@RequestParam(value="status",required=false) String status) throws Exception{
    	ModelAndView mv = new ModelAndView("/login/login_redirect");
    	if(status!=null)
    		mv.addObject("status",status);
    	else
    		mv.addObject("status","-99");
    	return mv;
    }
	@RequestMapping(value="/signUpForm.do")
    public ModelAndView signUpForm() throws Exception{
    	ModelAndView mv = new ModelAndView("/login/signUpForm"); 
    	return mv;
    } 
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/exhibitList.do";
	}
	
	@RequestMapping(value="/login.do")
    public ModelAndView login(RedirectAttributes redirectAttribute, 
    						  CommandMap commandMap,HttpServletRequest request) throws Exception{
		/**
		 *  status : 1 login success
		 *  		 0 wrong pw
		 *  		-1 wrong id
		 */
    	ModelAndView mv = new ModelAndView("redirect:/exhibitList.do");
    	String input_pw = commandMap.get("login_pw").toString();
    	
    	String status = "-1";
    	HttpSession session = request.getSession();
    	session.setAttribute("user", null);
    	Map<String,Object> result = loginService.login(commandMap.getMap());
     
    	if(result!=null) {
	    	if(result.get("login_pw").toString().equalsIgnoreCase(input_pw)) {
	    		status = "1";
	        	session.setAttribute("user", result);
	    	}else {
	    		status = "0";
	    		mv = new ModelAndView("redirect:/login_redirect.do");
	    	}
    	}else {

    		mv = new ModelAndView("redirect:/login_redirect.do");
    	}
    	redirectAttribute.addAttribute("status", status); 
    	return mv;
    } 
	 
	
	@RequestMapping(value="/signup.do")
    public ModelAndView signup(RedirectAttributes redirectAttribute,CommandMap commandMap) throws Exception{ 
		
    	ModelAndView mv =  new ModelAndView("redirect:/login_redirect.do");
    	loginService.signup(commandMap.getMap()); 
    	String status = "2";
    	redirectAttribute.addAttribute("status", status);
    	return mv;
    } 
}

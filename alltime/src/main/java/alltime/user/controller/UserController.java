package alltime.user.controller;

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
import alltime.user.service.UserService; 

@Controller
public class UserController { 
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(value="/admin/userList.do")
    public ModelAndView userList(Map<String,Object> commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/user/userList"); 
    	int numOfPage = 15;
    	String page = "0";
    	commandMap.put("numOfPage", numOfPage);
    	if(commandMap.get("page") != null)
    		page = commandMap.get("page").toString();
    		commandMap.put("page", Integer.parseInt(page)*numOfPage); 
    	
    		Map<String,Object> pagination = userService.total(); 
    	List<Map<String,Object>> list = userService.userList(commandMap);
    	pagination.put("page", page);
    	
    	mv.addObject("list", list);
    	mv.addObject("pagination", pagination);
    	
    	return mv;
    }
	
	@RequestMapping(value="/infoEdit.do")
    public ModelAndView infoEdit(CommandMap commandMap,HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("/user/userUpdateForm");   
		HttpSession session = request.getSession(); 
		mv.addObject("map",session.getAttribute("user")); 
	  
		return mv;
    }
	@RequestMapping(value="/admin/userUpdateForm.do",produces="text/plain;charset=UTF-8")
	public ModelAndView userUpdateForm(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/user/userUpdateForm");  
		Map<String,Object> map = userService.userUpdateForm(commandMap.getMap());  
		mv.addObject("map",map); 
	  
		return mv;
	}
	@RequestMapping(value="/admin/userUpdateForm-admin.do",produces="text/plain;charset=UTF-8")
	public ModelAndView userUpdateForm_admin(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/user/userUpdateForm-admin");  
		Map<String,Object> map = userService.userUpdateForm(commandMap.getMap());  
		mv.addObject("map",map); 
	  
		return mv;
	}
	@RequestMapping(value="/admin/userUpdate.do",produces="text/plain;charset=UTF-8")
	public ModelAndView userUpdate(CommandMap commandMap,RedirectAttributes redirectAttribute) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/login_redirect.do");
		userService.userUpdate(commandMap.getMap()); 
		redirectAttribute.addAttribute("status","100");
		return mv;
	}
	@RequestMapping(value="/admin/userUpdate-admin.do",produces="text/plain;charset=UTF-8")
	public ModelAndView userUpdate_admin(CommandMap commandMap,RedirectAttributes redirectAttribute) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/login_redirect.do");
		userService.userUpdate(commandMap.getMap()); 
		redirectAttribute.addAttribute("status","105");
		return mv;
	}
	
	@RequestMapping(value="/admin/userDelete.do",produces="text/plain;charset=UTF-8")
	public ModelAndView userDelete(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/admin/userList.do");
		userService.userDelete(commandMap.getMap()); 
		
		return mv;
	}
}

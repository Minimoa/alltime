package alltime.shopping.controller;
 
import java.util.List;
import java.util.Map; 

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import alltime.common.common.CommandMap;
import alltime.shopping.service.ShoppingService; 

@Controller
public class ShoppingController { 
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="shoppingService")
	private ShoppingService shoppingService;
	
	@RequestMapping(value="/shoppingList.do")
    public ModelAndView shoppingList(Map<String,Object> commandMap,HttpServletRequest request) throws Exception{
    	ModelAndView mv = new ModelAndView("/shopping/shoppingList"); 
    	HttpSession session = request.getSession(); 
    	Map<String,Object> user = (Map<String, Object>) session.getAttribute("user");
    	commandMap.put("login_id", user.get("login_id").toString());
    	List<Map<String,Object>> list = shoppingService.shoppingList(commandMap);
    	mv.addObject("list", list); 
    	return mv;
    } 
	@RequestMapping(value="/shoppingItemInsert.do")
    public ModelAndView shoppingItemInsert(CommandMap commandMap) throws Exception{  
    	ModelAndView mv =  new ModelAndView("redirect:/shoppingList.do");
    	log.debug(commandMap);
    	shoppingService.shoppingItemInsert(commandMap.getMap());  
    	return mv;
    } 
	@RequestMapping(value="/shoppingModify.do",produces="text/plain;charset=UTF-8")
	public ModelAndView shoppingModify(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:shoppingList.do");
		shoppingService.shoppingModify(commandMap.getMap()); 
		
		return mv;
	}
	@RequestMapping(value="/shoppingDelete.do",produces="text/plain;charset=UTF-8")
	public ModelAndView shoppingDelete(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:shoppingList.do");
		shoppingService.shoppingDelete(commandMap.getMap()); 
		
		return mv;
	}
}
                                                                                                                                                                                                                                                                                                                             
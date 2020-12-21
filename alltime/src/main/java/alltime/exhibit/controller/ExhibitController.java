package alltime.exhibit.controller;

import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import alltime.common.common.CommandMap;
import alltime.exhibit.service.ExhibitService;

@Controller
public class ExhibitController {
	/**
	 * __action : view page 
	 * action__ : logic page
	 */
	Logger log = Logger.getLogger(this.getClass());
	HttpServletRequest request; 
	
	@Resource(name="exhibitService")
	private ExhibitService exhibitService;
	
	@RequestMapping(value="/reviewList.do")
    public ModelAndView reviewList(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("/exhibit/reviewListAll");
		
    	int numOfPage = 15;
    	String page = "0";
    	
    	commandMap.put("numOfPage", numOfPage);
    	if(commandMap.get("page") != null)
    		page = commandMap.get("page").toString();
    	HttpSession session = request.getSession();  
    	Map<String,Object> user = (Map<String, Object>) session.getAttribute("user");
    	
		commandMap.put("page", Integer.parseInt(page)*numOfPage);
    	List<Map<String,Object>> reviews = exhibitService.reviewList(commandMap.getMap()); 
    	Map<String,Object> etc =  new HashMap<String,Object>();

    	if(user != null) {
    		etc.put("login_id", user.get("login_id").toString());
        	etc.put("id", commandMap.get("id").toString());
    	}   
    	Map<String,Object> pagination = exhibitService.total();
    	pagination.put("page", page); 
    	
    	mv.addObject("pagination", pagination ); 
    	mv.addObject("etc", etc ); 
    	mv.addObject("reviews", reviews ); 
    	return mv;
    } 
	@RequestMapping(value="/reviewInsert.do")
    public ModelAndView reviewInsert(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/reviewList.do?id="+commandMap.get("id").toString());  
		exhibitService.reviewInsert(commandMap.getMap()); 
    	return mv;
    }
	@RequestMapping(value="/exhibitList.do")
    public ModelAndView exhibitList(CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/exhibit/exhibitList"); 
    	int numOfPage = 6;
    	String page = "0";
    	commandMap.put("numOfPage", numOfPage);
    	
    	//pagination
    	if(commandMap.get("page") != null)
    		page = commandMap.get("page").toString();
		commandMap.put("page", Integer.parseInt(page)*numOfPage);
		List<Map<String,Object>> list = null;
		
		//search 
		if(commandMap.get("keyword") != null) {
			list = exhibitService.exhibitSearch(commandMap.getMap());
			
		}else { 
			list = exhibitService.exhibitList(commandMap.getMap());
		}
		
    	Map<String,Object> pagination = exhibitService.total();
    	pagination.put("page", page);
    	mv.addObject("list", list);
    	mv.addObject("pagination", pagination );
    	
    	return mv;
    }
	
	@RequestMapping(value="/admin/exhibitWrite.do")
	public ModelAndView exhibitWrite(Map<String,Object> commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/exhibit/exhibitForm");
		mv.addObject("type","write");
		
		return mv;
	}
	
	@RequestMapping(value="/exhibit/testMapArgumentResolver.do")
	public ModelAndView testMapArgumentResolver(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("");
		
		if(commandMap.isEmpty() == false){
			Iterator<Entry<String,Object>> iterator = commandMap.getMap().entrySet().iterator();
			Entry<String,Object> entry = null;
			while(iterator.hasNext()){
				entry = iterator.next();
				log.debug("key : "+entry.getKey()+", value : "+entry.getValue());
			}
		}
		return mv;
	}
	
	@RequestMapping(value="/admin/insertExhibit.do",produces="text/plain;charset=UTF-8")
	public ModelAndView insertExhibit(CommandMap commandMap, HttpServletRequest request) throws Exception{
		
		
		ModelAndView mv = new ModelAndView("redirect:/exhibitList.do");
		String root_path = request.getSession().getServletContext().getRealPath("/");   
		
		commandMap.put("root_path", root_path); 
		
		exhibitService.insertExhibit(commandMap.getMap(),request);
		
		return mv;
	}
	
	@RequestMapping(value="/admin/updateExhibit.do",produces="text/plain;charset=UTF-8")
	public ModelAndView updateExhibit(CommandMap commandMap,HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/exhibitList.do");
		String root_path = request.getSession().getServletContext().getRealPath("/");  
		
		
		commandMap.put("root_path", root_path); 
		exhibitService.updateExhibit(commandMap.getMap(),request);
	 
		//mv.addObject("list",map.get("list"));
		
		return mv;
	}
	
	@RequestMapping(value="/detailExhibit.do",produces="text/plain;charset=UTF-8")
	public ModelAndView detailExhibit(CommandMap commandMap,HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("/exhibit/exhibitDetail");	
		int numOfPage = 3;
    	String page = "0";
    	commandMap.put("numOfPage", numOfPage);
    	if(commandMap.get("page") != null)
    		page = commandMap.get("page").toString();
		commandMap.put("page", Integer.parseInt(page)*numOfPage);
		Map<String,Object> map = exhibitService.selectExhibitDetail(commandMap.getMap()); 
    	List<Map<String,Object>> reviews = exhibitService.reviewList(commandMap.getMap());
		mv.addObject("map",map);
    	mv.addObject("reviews", reviews ); 
		
		return mv;
	}
	
	
	@RequestMapping(value="/admin/exhibitUpdate.do",produces="text/plain;charset=UTF-8")
	public ModelAndView updateExhibitForm(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/exhibit/exhibitForm");  
		
		Map<String,Object> map = exhibitService.selectExhibitDetail(commandMap.getMap());
		 
		mv.addObject("map",map);
		mv.addObject("type","edit");
	  
		return mv;
	}
	
	
	
	@RequestMapping(value="/admin/deleteExhibit.do",produces="text/plain;charset=UTF-8")
	public ModelAndView deleteExhibit(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/exhibitList.do");
		exhibitService.deleteExhibit(commandMap.getMap()); 
		
		return mv;
	}
	
 
}

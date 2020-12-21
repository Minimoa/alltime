package alltime.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtils")
public class FileUtils {
	
	Logger log = Logger.getLogger(this.getClass());
	//private static final String filePath = "C:\\dev\\file\\";
	private static String attach_path = "resources\\upload\\"; 
	
	public Map<String,Object> updateFileInfo(Map<String,Object> map, HttpServletRequest request) throws Exception{ 
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
    	Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
    	
    	MultipartFile multipartFile = null;
    	String originalFileName = null;
    	String originalFileExtension = null;
    	String storedFileName = null;
    	String root_path = map.get("root_path").toString(); 

    	String id = null;
    	String title = null;
    	String price = null;
    	String descript = null;
    	String start_date = null;
    	String end_date = null;
    	String updated_url = null;
    	int available = (multipartHttpServletRequest.getParameter("available")!=null)?1:0;
    	 
        Map<String, Object> listMap = null; 
         
        
        File file = new File(root_path+attach_path);
	        if(file.exists() == false){
	        	file.mkdirs();
        }
         
    	multipartFile = multipartHttpServletRequest.getFile(iterator.next());

		id = multipartHttpServletRequest.getParameter("id");
		title = multipartHttpServletRequest.getParameter("title");
		price = multipartHttpServletRequest.getParameter("price");
		descript = multipartHttpServletRequest.getParameter("descript");
		start_date = multipartHttpServletRequest.getParameter("start_date");
		end_date = multipartHttpServletRequest.getParameter("end_date");
		
    	if(multipartFile.isEmpty() == true){
    		updated_url = multipartHttpServletRequest.getParameter("original_file");
    	}else{  
    		originalFileName = multipartFile.getOriginalFilename();
    		originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
    		storedFileName = CommonUtils.getRandomString() + originalFileExtension;
    		
    		file = new File(root_path + attach_path + storedFileName);
    		multipartFile.transferTo(file);
    		updated_url = attach_path + storedFileName; 
    	}
    	
		
		log.debug("------------- file start -------------");
		log.debug("title : "+ title);
		log.debug("price : "+ price);
		log.debug("available : "+ available);
		log.debug("name : "+multipartFile.getName());
		log.debug("start_date : "+start_date);
		log.debug("end_date : "+end_date);
		log.debug("real_path : "+root_path+updated_url); 
		log.debug("updated_url : "+updated_url); 
		log.debug("-------------- file end --------------\n");
		
		listMap = new HashMap<String,Object>(); 
		listMap.put("id", id);
		listMap.put("available", available);
		listMap.put("title", title);
		listMap.put("price", price);
		listMap.put("descript", descript);
		listMap.put("start_date", start_date);
		listMap.put("end_date", end_date);
		listMap.put("STORED_FILE_PATH", "/"+updated_url);  
    		
		return listMap;
	}
	
	public List<Map<String,Object>> parseInsertFileInfo(Map<String,Object> map, HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
    	Iterator<String> iterator = multipartHttpServletRequest.getFileNames(); 
    	
    	MultipartFile multipartFile = null;
    	String originalFileName = null;
    	String originalFileExtension = null;
    	String storedFileName = null;
    	String root_path = map.get("root_path").toString();
 
    	
    	String title = null;
    	String price = null;
    	String descript = null;
    	String start_date = null;
    	String end_date = null;
    	int available = (multipartHttpServletRequest.getParameter("available")!=null)?1:0;
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> listMap = null; 
         
        
        File file = new File(root_path+attach_path);
        if(file.exists() == false){
        	file.mkdirs();
        }
        
        while(iterator.hasNext()){
        	multipartFile = multipartHttpServletRequest.getFile(iterator.next());
        	if(multipartFile.isEmpty() == false){
        		
				
        		originalFileName = multipartFile.getOriginalFilename();
        		originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        		storedFileName = CommonUtils.getRandomString() + originalFileExtension;
        		
        		file = new File(root_path + attach_path + storedFileName);
        		multipartFile.transferTo(file);
        		title = multipartHttpServletRequest.getParameter("title");
        		price = multipartHttpServletRequest.getParameter("price");
				descript = multipartHttpServletRequest.getParameter("descript");
				start_date = multipartHttpServletRequest.getParameter("start_date");
				end_date = multipartHttpServletRequest.getParameter("end_date");
				
        		log.debug("------------- file start -------------");
				log.debug("title : "+ title);
				log.debug("price : "+ price);
				log.debug("available : "+available);
				log.debug("name : "+multipartFile.getName());
				log.debug("STORED_FILE_PATH : "+attach_path + storedFileName);
				log.debug("filename : "+multipartFile.getOriginalFilename());
				log.debug("real_path : "+root_path+attach_path+storedFileName); 
				log.debug("size : "+multipartFile.getSize());
				log.debug("-------------- file end --------------\n");
				
        		listMap = new HashMap<String,Object>(); 
        		listMap.put("available", available);
        		listMap.put("title", title);
        		listMap.put("price", price);
        		listMap.put("descript", descript);
        		listMap.put("start_date", start_date);
        		listMap.put("end_date", end_date);
        		listMap.put("STORED_FILE_PATH", "/"+attach_path + storedFileName);
//        		listMap.put("ORIGINAL_FILE_NAME", originalFileName);
//        		listMap.put("STORED_FILE_NAME", storedFileName);
//        		listMap.put("FILE_SIZE", multipartFile.getSize());
        		list.add(listMap);
        	}
        }
		return list;
	}
}
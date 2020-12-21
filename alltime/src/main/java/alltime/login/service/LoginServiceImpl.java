package alltime.login.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import alltime.common.util.FileUtils;
import alltime.login.dao.LoginDAO; 
 
@Service("loginService")
public class LoginServiceImpl implements LoginService{
	Logger log = Logger.getLogger(this.getClass()); 
	
	@Resource(name="loginDAO")
	private LoginDAO loginDAO; 

	@Override
	public Map<String, Object> login(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub 
		return loginDAO.login(map);
	}

	@Override
	public void signup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		loginDAO.signup(map);
	}
 
	 
 
  
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
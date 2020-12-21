package alltime.user.service;

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
import alltime.user.dao.UserDAO; 
 
@Service("userService")
public class UserServiceImpl implements UserService{
	Logger log = Logger.getLogger(this.getClass()); 
	
	@Resource(name="userDAO")
	private UserDAO userDAO;

	@Override
	public List<Map<String, Object>> userList(Map<String, Object> map) throws Exception {
		return userDAO.userList(map);
		
	}

	@Override
	public Map<String, Object> userUpdateForm(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDAO.user(map);
	}

	@Override
	public void userDelete(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userDAO.userDelete(map);
	}

	@Override
	public void userUpdate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userDAO.userUpdate(map);
		
	}

	@Override
	public Map<String, Object> total() {
		// TODO Auto-generated method stub
		return userDAO.total();
	}


	 
 
  
}

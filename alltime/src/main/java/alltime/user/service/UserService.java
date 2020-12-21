package alltime.user.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface UserService { 

	List<Map<String, Object>> userList(Map<String, Object> commandMap) throws Exception; 
	Map<String, Object> userUpdateForm(Map<String, Object> map); 
	void userDelete(Map<String, Object> map);
	void userUpdate(Map<String, Object> map);
	Map<String, Object> total(); 
 

}

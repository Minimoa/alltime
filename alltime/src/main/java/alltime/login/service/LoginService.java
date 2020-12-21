package alltime.login.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

	Map<String, Object> login(Map<String, Object> commandMap) throws Exception;

	void signup(Map<String, Object> map);
 

}
                                                                                                                                                                                                                                                                                                                                                                                                           
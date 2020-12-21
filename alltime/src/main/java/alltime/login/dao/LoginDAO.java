package alltime.login.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import alltime.common.dao.AbstractDAO;

@Repository("loginDAO")
public class LoginDAO extends AbstractDAO{

 
	
//	@SuppressWarnings("unchecked")
//	public Map<String, Object> selectUserById(Map<String, Object> map) throws Exception  {
//		// TODO Auto-generated method stub
//		return (Map<String, Object>)selectOne("login.selectloginDetail", map);
//	}
	 
	 
	public Map<String, Object> login(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Map<String, Object>)selectOne("login.login", map);
	}

	public void signup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		insert("login.signup",map);
	}

	
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
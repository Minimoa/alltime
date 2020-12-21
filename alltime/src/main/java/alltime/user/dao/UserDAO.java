package alltime.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import alltime.common.dao.AbstractDAO;

@Repository("userDAO")
public class UserDAO extends AbstractDAO{

 
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> userList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("user.userList", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> user(Map<String, Object> map) { 
		return (Map<String, Object>)selectOne("user.user",map);
	}

	public void userDelete(Map<String, Object> map) { 
		update("user.userDelete",map);
	}

	public void userUpdate(Map<String, Object> map) {  
		map.put("admin",  (map.get("admin")!=null)? 1:0 );
		update("user.userUpdate",map);
		
	}

	public Map<String, Object> total() { 
		return (Map<String, Object>)selectOne("user.total");
	}
	
}

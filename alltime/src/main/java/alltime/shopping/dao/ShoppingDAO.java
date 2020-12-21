package alltime.shopping.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import alltime.common.dao.AbstractDAO;

@Repository("shoppingDAO")
public class ShoppingDAO extends AbstractDAO{

 
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> shoppingList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("shopping.shoppingList", map);
	}
 
	public void shoppingDelete(Map<String, Object> map) { 
		delete("shopping.shoppingDelete",map);
	}

	public void shoppingItemInsert(Map<String, Object> map) { 
		insert("shopping.shoppingItemInsert",map);
	}

	public void shoppingModify(Map<String, Object> map) {
		// TODO Auto-generated method stub
		update("shopping.shoppingModify",map);
	}
 
	
}
                     
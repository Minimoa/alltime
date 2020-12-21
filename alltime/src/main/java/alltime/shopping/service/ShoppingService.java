package alltime.shopping.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ShoppingService { 

	List<Map<String, Object>> shoppingList(Map<String, Object> commandMap) throws Exception;  
	void shoppingDelete(Map<String, Object> map);
	void shoppingItemInsert(Map<String, Object> map);
	void shoppingModify(Map<String, Object> map); 
 

}

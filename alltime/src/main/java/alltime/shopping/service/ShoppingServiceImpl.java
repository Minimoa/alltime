package alltime.shopping.service;

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
import alltime.shopping.dao.ShoppingDAO;  
 
@Service("shoppingService")
public class ShoppingServiceImpl implements ShoppingService{
	Logger log = Logger.getLogger(this.getClass()); 
	
	@Resource(name="shoppingDAO")
	private ShoppingDAO shoppingDAO;

	@Override
	public List<Map<String, Object>> shoppingList(Map<String, Object> map) throws Exception {
		return shoppingDAO.shoppingList(map);
		
	}
  
	@Override
	public void shoppingDelete(Map<String, Object> map) {
		// TODO Auto-generated method stub
		shoppingDAO.shoppingDelete(map);
	}

	@Override
	public void shoppingItemInsert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		shoppingDAO.shoppingItemInsert(map);
	}

	@Override
	public void shoppingModify(Map<String, Object> map) {
		// TODO Auto-generated method stub
		shoppingDAO.shoppingModify(map);
	}
  
  
}

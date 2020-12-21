package alltime.exhibit.service;

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
import alltime.exhibit.dao.ExhibitDAO;
 
@Service("exhibitService")
public class ExhibitServiceImpl implements ExhibitService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Resource(name="exhibitDAO")
	private ExhibitDAO exhibitDAO;
	
	@Override
	public Map<String,Object> total() throws Exception {
		// TODO Auto-generated method stub
		return exhibitDAO.total();
	}
	@Override
	public List<Map<String, Object>> exhibitList(Map<String, Object> map) throws Exception {
		return exhibitDAO.exhibitList(map);
	}

	@Override
	public void insertExhibit(Map<String, Object> map, HttpServletRequest request) throws Exception{
		// TODO Auto-generated method stub   
		
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map, request);
		for(int i=0, size=list.size(); i<size;i++) {
			exhibitDAO.insertExhibit(list.get(i));
		}
	}
 
	@Override
	public void updateExhibit(Map<String, Object> map,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> parma_map = fileUtils.updateFileInfo(map, request); 
		exhibitDAO.updateExhibit(parma_map); 
	}
	
	@Override
	public Map<String, Object> selectExhibitDetail(Map<String, Object> map) throws Exception {
		exhibitDAO.updateHitCnt(map);
		Map<String, Object> resultMap = exhibitDAO.selectExhibitDetail(map); 
		return resultMap;
	}

	

	@Override
	public void deleteExhibit(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		exhibitDAO.deleteExhibit(map);
		
	}
	@Override
	public List<Map<String, Object>> reviewList(Map<String, Object> map) throws Exception{
		// TODO Auto-generated method stub
		return exhibitDAO.reviewList(map);
	}
	@Override
	public void reviewInsert(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		exhibitDAO.reviewInsert(map);
	}
	@Override
	public List<Map<String, Object>> exhibitSearch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return exhibitDAO.exhibitSearch(map);
	}



 
  
}

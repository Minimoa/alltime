package alltime.exhibit.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import alltime.common.dao.AbstractDAO;

@Repository("exhibitDAO")
public class ExhibitDAO extends AbstractDAO{

	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> exhibitList(Map<String, Object> map) throws Exception { 
		return (List<Map<String, Object>>)selectList("exhibit.exhibitList", map);
	}
	@SuppressWarnings("unchecked")
	public Map<String, Object> total() throws Exception  {
		// TODO Auto-generated method stub
		return (Map<String, Object>)selectOne("exhibit.total");
	}
	public void insertExhibit(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		insert("exhibit.insertExhibit",map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectExhibitDetail(Map<String, Object> map) throws Exception  {
		// TODO Auto-generated method stub
		return (Map<String, Object>)selectOne("exhibit.selectExhibitDetail", map);
	}
	public void updateExhibit(Map<String, Object> map) {
		// TODO Auto-generated method stub
		update("exhibit.updateExhibit",map);
		
	}
	public void deleteExhibit(Map<String, Object> map) {
		// TODO Auto-generated method stub
		delete("exhibit.deleteExhibit",map);
		
	}
	public void updateHitCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		update("exhibit.updateHITCNT",map);
	}
	public List<Map<String, Object>> reviewList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>)selectList("exhibit.reviewList", map);
	}
	public void reviewInsert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		insert("exhibit.reviewInsert",map);
	}
	public List<Map<String, Object>> exhibitSearch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>)selectList("exhibit.exhibitSearch", map);
	}

	
}

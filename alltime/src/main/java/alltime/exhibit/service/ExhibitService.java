package alltime.exhibit.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ExhibitService {


	List<Map<String, Object>> exhibitList(Map<String, Object> map) throws Exception;

	void insertExhibit(Map<String, Object> map, HttpServletRequest request) throws Exception;

	Map<String, Object> selectExhibitDetail(Map<String, Object> map) throws Exception;
 

	void deleteExhibit(Map<String, Object> map) throws Exception;

	void updateExhibit(Map<String, Object> map, HttpServletRequest request) throws Exception;

	Map<String, Object> total() throws Exception;

	List<Map<String, Object>> reviewList(Map<String, Object> map) throws Exception;

	void reviewInsert(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> exhibitSearch(Map<String, Object> map);

}

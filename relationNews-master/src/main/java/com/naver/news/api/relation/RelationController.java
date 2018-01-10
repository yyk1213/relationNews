package com.naver.news.api.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.naver.news.batch.relation.dao.RelatedPeopleMapper;
import com.naver.news.batch.relation.dbmodel.Related_People;

/**
 * @author yongseog.yun
 * 
 */
@RestController
@RequestMapping("/api/relation")
public class RelationController {

	@RequestMapping(value = "/index", produces = "application/json; charset=utf8", method = RequestMethod.GET)
	public String index() {
		final String str = "API 구성";
		System.out.println(str);
		return str;
	}
	@Autowired
	private RelatedPeopleMapper related_personMapper;
	
	@RequestMapping(value="/index2",produces="application/json; charset=utf8", method=RequestMethod.GET)
	@ResponseBody
	public String relationPeople(@RequestParam int person_id) {
		
		//List<Related_person> related_list=new ArrayList<Related_person>();
		
		Related_People[] related_list = related_personMapper.selectRelationPeople(person_id);
		
		for(Related_People a : related_list)
			System.out.println(a);
		return related_list.toString();
	}
}

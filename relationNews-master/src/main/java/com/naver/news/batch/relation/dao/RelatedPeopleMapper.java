package com.naver.news.batch.relation.dao;

import com.naver.news.batch.relation.dbmodel.Related_People;

public interface RelatedPeopleMapper {

	Related_People[] selectRelationPeople(int person_id);
}

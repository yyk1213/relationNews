package com.naver.news.batch.relation.dao;

import java.util.List;

import com.naver.news.batch.relation.dbmodel.Test;

/**
 * @author yongseog.yun
 * 
 */
public interface TestMapper {
	int deleteByPrimaryKey(String name);

	int insert(Test record);

	int insertSelective(Test record);

	Test selectByPrimaryKey(String name);

	int updateByPrimaryKeySelective(Test record);

	int updateByPrimaryKey(Test record);
}
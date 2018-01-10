package com.naver.news.batch.relation;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.naver.news.batch.relation.dao.TestMapper;
import com.naver.news.batch.relation.dbmodel.Test;

/**
 * @author yongseog.yun
 * 
 */
public class TestTasklet implements Tasklet {

	private Logger LOGGER = LoggerFactory.getLogger(TestTasklet.class);

	@Autowired
	private TestMapper testMapper;

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("BATCH 구성");

		Test record = new Test();
		record.setName("firstman");
		record.setRegTime(Calendar.getInstance().getTime());

		testMapper.insert(record);
		return RepeatStatus.FINISHED;
	}
	
}
package com.naver.news.batch.relation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.naver.news.batch.relation.dao.RelatedPeopleMapper;
import com.naver.news.batch.relation.dbmodel.Related_People;

public class RelationPeopleTasklet implements Tasklet {
	private Logger LOGGER = LoggerFactory.getLogger(RelationPeopleTasklet.class);

	@Autowired
	private RelatedPeopleMapper relatedMapper;

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.out.println("BATCH 구성");

		Related_People people = new Related_People();
		return RepeatStatus.FINISHED;
	}
}

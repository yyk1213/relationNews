package com.naver.news.batch.relation.dbmodel;

public class Related_People {
	private int person_id;

	private int related_person_id;
	
	private int count;

	public int getPeron_id() {
		return person_id;
	}

	public void setPeron_id(int person_id) {
		this.person_id = person_id;
	}

	public int getRelated_person_id() {
		return related_person_id;
	}

	public void setRelated_person_id(int related_person_id) {
		this.related_person_id = related_person_id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
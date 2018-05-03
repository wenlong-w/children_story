package com.wenyansoft.ibatis.entry;

import java.io.Serializable;
import java.util.Date;

public class Diary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6901199341588282860L;
	
	private Integer id;
	private String content;
	private String gender;
	private Date dt;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	
}

package com.wancheda.sortList;

import cn.bmob.v3.BmobObject;

public class CarEntity  extends BmobObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer parent_id;
	private String name;         //车型
	private String sortLetters;  //显示数据拼音的首字母
	
	public CarEntity(){
		this.setTableName("tb_carType");
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
}

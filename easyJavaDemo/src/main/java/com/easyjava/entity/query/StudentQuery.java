package com.easyjava.entity.query;
public class StudentQuery extends BaseParam{
/**
 * @Description: 学生信息查询对象
 * @Author: 张鑫
 * @Date: 2024/09/26
*/
	private Integer sid;

	private String sname;

	private String snameFuzzy;

	private Integer grade;

	 public void setSid(Integer sid){
		this.sid=sid;
	 }
	 public Integer getSid(){
		return this.sid;
	 }
	 public void setSname(String sname){
		this.sname=sname;
	 }
	 public String getSname(){
		return this.sname;
	 }
	 public void setGrade(Integer grade){
		this.grade=grade;
	 }
	 public Integer getGrade(){
		return this.grade;
	 }
	 public void setSnameFuzzy(String snameFuzzy){
		this.snameFuzzy=snameFuzzy;
	 }
	 public String getSnameFuzzy(){
		return this.snameFuzzy;
	 }
}
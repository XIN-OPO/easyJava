package com.easyjava.entity.po;
import java.io.Serializable;
public class Student implements Serializable {
/**
*@Description: 学生信息
*@Author: 张鑫
*@Date: 2024/09/23
*/
	private Integer sid;

	private String sname;

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
	@Override
	 public String toString(){
		return "sid:" + (sid == null ? "空" : sid)+",sname:" + (sname == null ? "空" : sname)+",grade:" + (grade == null ? "空" : grade);
	}
}
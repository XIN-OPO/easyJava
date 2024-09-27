package com.easyjava.entity.query;
import java.util.Date;

public class BorrowQuery extends BaseParam{
/**
 * @Description: 借阅信息查询对象
 * @Author: 张鑫
 * @Date: 2024/09/27
*/
	private Integer id;

	private Integer sid;

	private Integer bid;

	private Date time;

	private String timeStart;

	private String timeEnd;

	 public void setId(Integer id){
		this.id=id;
	 }
	 public Integer getId(){
		return this.id;
	 }
	 public void setSid(Integer sid){
		this.sid=sid;
	 }
	 public Integer getSid(){
		return this.sid;
	 }
	 public void setBid(Integer bid){
		this.bid=bid;
	 }
	 public Integer getBid(){
		return this.bid;
	 }
	 public void setTime(Date time){
		this.time=time;
	 }
	 public Date getTime(){
		return this.time;
	 }
	 public void setTimeStart(String timeStart){
		this.timeStart=timeStart;
	 }
	 public String getTimeStart(){
		return this.timeStart;
	 }
	 public void setTimeEnd(String timeEnd){
		this.timeEnd=timeEnd;
	 }
	 public String getTimeEnd(){
		return this.timeEnd;
	 }
}
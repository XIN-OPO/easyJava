package com.easyjava.entity.po;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.easyjava.enums.DateTimePatternEnum;
import com.easyjava.utils.DateUtils;

public class Borrow implements Serializable {
/**
 * @Description: 借阅信息
 * @Author: 张鑫
 * @Date: 2024/09/30
*/
	private Integer id;

	private Integer sid;

	private Integer bid;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date time;

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
	@Override
	 public String toString(){
		return "id:"+(id == null ? "空" : id)+",sid:"+(sid == null ? "空" : sid)+",bid:"+(bid == null ? "空" : bid)+",time:"+(DateUtils.format(time, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()) == null ? "空" : time);
	}
}
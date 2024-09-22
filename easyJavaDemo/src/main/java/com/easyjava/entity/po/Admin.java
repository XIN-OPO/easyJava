package com.easyjava.entity.po;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
public class Admin implements Serializable {
/**
*@Description: 管理员

*@Author: 张鑫
*@Date: 2024/09/23
*/
	private Integer id;

	private String username;

	@JsonIgnore
	private String nickname;

	private String password;

	 public void setId(Integer id){
		this.id=id;
	 }
	 public Integer getId(){
		return this.id;
	 }
	 public void setUsername(String username){
		this.username=username;
	 }
	 public String getUsername(){
		return this.username;
	 }
	 public void setNickname(String nickname){
		this.nickname=nickname;
	 }
	 public String getNickname(){
		return this.nickname;
	 }
	 public void setPassword(String password){
		this.password=password;
	 }
	 public String getPassword(){
		return this.password;
	 }
	@Override
	 public String toString(){
		return "id:" + (id == null ? "空" : id)+",username:" + (username == null ? "空" : username)+",nickname:" + (nickname == null ? "空" : nickname)+",password:" + (password == null ? "空" : password);
	}
}
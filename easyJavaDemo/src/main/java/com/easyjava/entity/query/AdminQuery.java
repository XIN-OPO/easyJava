package com.easyjava.entity.query;
public class AdminQuery extends BaseParam{
/**
 * @Description: 管理员
查询对象
 * @Author: 张鑫
 * @Date: 2024/09/30
*/
	private Integer id;

	private String username;

	private String usernameFuzzy;

	private String nickname;

	private String nicknameFuzzy;

	private String password;

	private String passwordFuzzy;

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
	 public void setUsernameFuzzy(String usernameFuzzy){
		this.usernameFuzzy=usernameFuzzy;
	 }
	 public String getUsernameFuzzy(){
		return this.usernameFuzzy;
	 }
	 public void setNicknameFuzzy(String nicknameFuzzy){
		this.nicknameFuzzy=nicknameFuzzy;
	 }
	 public String getNicknameFuzzy(){
		return this.nicknameFuzzy;
	 }
	 public void setPasswordFuzzy(String passwordFuzzy){
		this.passwordFuzzy=passwordFuzzy;
	 }
	 public String getPasswordFuzzy(){
		return this.passwordFuzzy;
	 }
}
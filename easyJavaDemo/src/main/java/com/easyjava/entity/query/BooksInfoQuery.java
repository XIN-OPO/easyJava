package com.easyjava.entity.query;
import java.math.BigDecimal;
public class BooksInfoQuery extends BaseParam{
/**
 * @Description: 书籍信息
查询对象
 * @Author: 张鑫
 * @Date: 2024/09/29
*/
/**
 *书籍id
*/
	private Integer bid;

/**
 *书名
*/
	private String bookTitle;

	private String bookTitleFuzzy;

/**
 *书籍简介
*/
	private String descr;

	private String descrFuzzy;

/**
 *书籍价格
*/
	private BigDecimal price;

	 public void setBid(Integer bid){
		this.bid=bid;
	 }
	 public Integer getBid(){
		return this.bid;
	 }
	 public void setBookTitle(String bookTitle){
		this.bookTitle=bookTitle;
	 }
	 public String getBookTitle(){
		return this.bookTitle;
	 }
	 public void setDescr(String descr){
		this.descr=descr;
	 }
	 public String getDescr(){
		return this.descr;
	 }
	 public void setPrice(BigDecimal price){
		this.price=price;
	 }
	 public BigDecimal getPrice(){
		return this.price;
	 }
	 public void setBookTitleFuzzy(String bookTitleFuzzy){
		this.bookTitleFuzzy=bookTitleFuzzy;
	 }
	 public String getBookTitleFuzzy(){
		return this.bookTitleFuzzy;
	 }
	 public void setDescrFuzzy(String descrFuzzy){
		this.descrFuzzy=descrFuzzy;
	 }
	 public String getDescrFuzzy(){
		return this.descrFuzzy;
	 }
}
package com.easyjava.entity.po;
import java.io.Serializable;
import java.math.BigDecimal;
public class BooksInfo implements Serializable {
/**
 * @Description: 书籍信息

 * @Author: 张鑫
 * @Date: 2024/09/26
*/
/**
 *书籍id
*/
	private Integer bid;

/**
 *书名
*/
	private String bookTitle;

/**
 *书籍简介
*/
	private String descr;

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
	@Override
	 public String toString(){
		return "bid:"+(bid == null ? "空" : bid)+",book_title:"+(bookTitle == null ? "空" : bookTitle)+",descr:"+(descr == null ? "空" : descr)+",price:"+(price == null ? "空" : price);
	}
}
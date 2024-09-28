package com.easyjava.service;
import com.easyjava.entity.vo.PaginationResultVO;
import com.easyjava.entity.po.BooksInfo;
import com.easyjava.entity.query.BooksInfoQuery;
import java.util.List;

/**
 * @Description: 书籍信息
Service
 * @Author: 张鑫
 * @Date: 2024/09/29
*/
public interface BooksInfoService{

/**
 *根据条件查询列表
*/
	List<BooksInfo> findListByParam(BooksInfoQuery query);

/**
 *根据条件查询数量
*/
	Long findCountByParam(BooksInfoQuery query);

/**
 *分页查询
*/
	PaginationResultVO<BooksInfo> findListByPage(BooksInfoQuery query);

/**
 *新增
*/
	Long add(BooksInfo bean);

/**
 *批量新增
*/
	Long addBatch(List<BooksInfo> listBean);

/**
 *批量新增或修改
*/
	Long addOrUpdateBatch(List<BooksInfo> listBean);


/**
 *根据Bid查询
*/
	BooksInfo getByBid(Integer bid);

/**
 *根据Bid更新
*/
	Long updateByBid( BooksInfo bean , Integer bid);

/**
 *根据Bid删除
*/
	Long deleteByBid(Integer bid);
}

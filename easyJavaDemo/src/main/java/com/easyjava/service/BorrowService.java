package com.easyjava.service;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.easyjava.enums.DateTimePatternEnum;
import com.easyjava.utils.DateUtils;

import com.easyjava.entity.vo.PaginationResultVO;
import com.easyjava.entity.po.Borrow;
import com.easyjava.entity.query.BorrowQuery;
import java.util.List;

/**
 * @Description: 借阅信息Service
 * @Author: 张鑫
 * @Date: 2024/09/29
*/
public interface BorrowService{

/**
 *根据条件查询列表
*/
	List<Borrow> findListByParam(BorrowQuery query);

/**
 *根据条件查询数量
*/
	Integer findCountByParam(BorrowQuery query);

/**
 *分页查询
*/
	PaginationResultVO<Borrow> findListByPage(BorrowQuery query);

/**
 *新增
*/
	Integer add(Borrow bean);

/**
 *批量新增
*/
	Integer addBatch(List<Borrow> listBean);

/**
 *批量新增或修改
*/
	Integer addOrUpdateBatch(List<Borrow> listBean);


/**
 *根据Id查询
*/
	Borrow getBorrowById(Integer id);

/**
 *根据Id更新
*/
	Integer updateBorrowById( Borrow bean , Integer id);

/**
 *根据Id删除
*/
	Integer deleteBorrowById(Integer id);

/**
 *根据SidAndBid查询
*/
	Borrow getBorrowBySidAndBid(Integer sid, Integer bid);

/**
 *根据SidAndBid更新
*/
	Integer updateBorrowBySidAndBid( Borrow bean , Integer sid, Integer bid);

/**
 *根据SidAndBid删除
*/
	Integer deleteBorrowBySidAndBid(Integer sid, Integer bid);
}

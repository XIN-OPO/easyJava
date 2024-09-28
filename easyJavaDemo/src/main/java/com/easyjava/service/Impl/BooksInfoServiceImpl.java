package com.easyjava.service.Impl;
import com.easyjava.entity.vo.PaginationResultVO;
import com.easyjava.entity.po.BooksInfo;
import com.easyjava.entity.query.BooksInfoQuery;
import com.easyjava.service.BooksInfoService;
import org.springframework.stereotype.Service
import java.util.List;

/**
 * @Description: 书籍信息
ServiceImpl
 * @Author: 张鑫
 * @Date: 2024/09/29
*/
@Service("booksInfoService")
public class BooksInfoServiceImpl implements BooksInfoService {

/**
 *根据条件查询列表
*/
	public List<BooksInfo> findListByParam(BooksInfoQuery query) {

	}
/**
 *根据条件查询数量
*/
	public Long findCountByParam(BooksInfoQuery query) {

	}
/**
 *分页查询
*/
	public PaginationResultVO<BooksInfo> findListByPage(BooksInfoQuery query) {

	}
/**
 *新增
*/
	public Long add(BooksInfo bean) {

	}
/**
 *批量新增
*/
	public Long addBatch(List<BooksInfo> listBean) {

	}
/**
 *批量新增或修改
*/
	public Long addOrUpdateBatch(List<BooksInfo> listBean) {

	}

/**
 *根据Bid查询
*/
	public BooksInfo getByBid(Integer bid) {

	}
/**
 *根据Bid更新
*/
	public Long updateByBid( BooksInfo bean , Integer bid) {

	}

/**
 *根据Bid删除
*/
	public Long deleteByBid(Integer bid) {

	}
}

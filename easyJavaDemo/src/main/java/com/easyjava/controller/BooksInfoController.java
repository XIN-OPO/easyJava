package com.easyjava.controller;

import com.easyjava.entity.po.BooksInfo;
import com.easyjava.entity.query.BooksInfoQuery;
import javax.annotation.Resource;
import com.easyjava.service.BooksInfoService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.easyjava.entity.vo.ResponseVO;
import java.util.List;

/**
 * @Description: 书籍信息
Controller
 * @Author: 张鑫
 * @Date: 2024/09/30
*/
@RestController
@RequestMapping("booksInfo")
public class BooksInfoController extends ABaseController {

	@Resource
	private BooksInfoService booksInfoService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(BooksInfoQuery query) {
		return getSuccessResponseVO(booksInfoService.findListByPage(query));
	}
/**
 *新增
*/

	@RequestMapping("add")
	public ResponseVO add(BooksInfo bean) {
		this.booksInfoService.add(bean);
		return getSuccessResponseVO(null);
	}
/**
 *批量新增
*/

	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<BooksInfo> listBean) {
		this.booksInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}
/**
 *批量新增或修改
*/

	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<BooksInfo> listBean) {
		this.booksInfoService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

/**
 *根据Bid查询
*/

	@RequestMapping("getBooksInfoByBid")
	public ResponseVO getBooksInfoByBid(Integer bid) {
		return getSuccessResponseVO(this.booksInfoService.getBooksInfoByBid(bid));
	}
/**
 *根据Bid更新
*/

	@RequestMapping("updateBooksInfoByBid")
	public ResponseVO updateBooksInfoByBid( BooksInfo bean , Integer bid) {
		this.booksInfoService.updateBooksInfoByBid(bean,bid);
		return getSuccessResponseVO(null);
	}

/**
 *根据Bid删除
*/
	@RequestMapping("deleteBooksInfoByBid")
	public ResponseVO deleteBooksInfoByBid(Integer bid) {
		this.booksInfoService.deleteBooksInfoByBid(bid);
		return getSuccessResponseVO(null);
	}
}

package com.easyjava.controller;

import com.easyjava.entity.po.Borrow;
import com.easyjava.entity.query.BorrowQuery;
import javax.annotation.Resource;
import com.easyjava.service.BorrowService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.easyjava.entity.vo.ResponseVO;
import java.util.List;

/**
 * @Description: 借阅信息Controller
 * @Author: 张鑫
 * @Date: 2024/09/30
*/
@RestController
@RequestMapping("borrow")
public class BorrowController extends ABaseController {

	@Resource
	private BorrowService borrowService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(BorrowQuery query) {
		return getSuccessResponseVO(borrowService.findListByPage(query));
	}
/**
 *新增
*/

	@RequestMapping("add")
	public ResponseVO add(Borrow bean) {
		this.borrowService.add(bean);
		return getSuccessResponseVO(null);
	}
/**
 *批量新增
*/

	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<Borrow> listBean) {
		this.borrowService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}
/**
 *批量新增或修改
*/

	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<Borrow> listBean) {
		this.borrowService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

/**
 *根据Id查询
*/

	@RequestMapping("getBorrowById")
	public ResponseVO getBorrowById(Integer id) {
		return getSuccessResponseVO(this.borrowService.getBorrowById(id));
	}
/**
 *根据Id更新
*/

	@RequestMapping("updateBorrowById")
	public ResponseVO updateBorrowById( Borrow bean , Integer id) {
		this.borrowService.updateBorrowById(bean,id);
		return getSuccessResponseVO(null);
	}

/**
 *根据Id删除
*/
	@RequestMapping("deleteBorrowById")
	public ResponseVO deleteBorrowById(Integer id) {
		this.borrowService.deleteBorrowById(id);
		return getSuccessResponseVO(null);
	}

/**
 *根据SidAndBid查询
*/

	@RequestMapping("getBorrowBySidAndBid")
	public ResponseVO getBorrowBySidAndBid(Integer sid, Integer bid) {
		return getSuccessResponseVO(this.borrowService.getBorrowBySidAndBid(sid, bid));
	}
/**
 *根据SidAndBid更新
*/

	@RequestMapping("updateBorrowBySidAndBid")
	public ResponseVO updateBorrowBySidAndBid( Borrow bean , Integer sid, Integer bid) {
		this.borrowService.updateBorrowBySidAndBid(bean,sid, bid);
		return getSuccessResponseVO(null);
	}

/**
 *根据SidAndBid删除
*/
	@RequestMapping("deleteBorrowBySidAndBid")
	public ResponseVO deleteBorrowBySidAndBid(Integer sid, Integer bid) {
		this.borrowService.deleteBorrowBySidAndBid(sid, bid);
		return getSuccessResponseVO(null);
	}
}

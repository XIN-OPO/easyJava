package com.easyjava.service.Impl;
import com.easyjava.entity.vo.PaginationResultVO;
import com.easyjava.entity.po.Borrow;
import com.easyjava.entity.query.BorrowQuery;
import com.easyjava.service.BorrowService;
import org.springframework.stereotype.Service
import java.util.List;

/**
 * @Description: 借阅信息ServiceImpl
 * @Author: 张鑫
 * @Date: 2024/09/29
*/
@Service("borrowService")
public class BorrowServiceImpl implements BorrowService {

/**
 *根据条件查询列表
*/
	public List<Borrow> findListByParam(BorrowQuery query) {

	}
/**
 *根据条件查询数量
*/
	public Long findCountByParam(BorrowQuery query) {

	}
/**
 *分页查询
*/
	public PaginationResultVO<Borrow> findListByPage(BorrowQuery query) {

	}
/**
 *新增
*/
	public Long add(Borrow bean) {

	}
/**
 *批量新增
*/
	public Long addBatch(List<Borrow> listBean) {

	}
/**
 *批量新增或修改
*/
	public Long addOrUpdateBatch(List<Borrow> listBean) {

	}

/**
 *根据Id查询
*/
	public Borrow getById(Integer id) {

	}
/**
 *根据Id更新
*/
	public Long updateById( Borrow bean , Integer id) {

	}

/**
 *根据Id删除
*/
	public Long deleteById(Integer id) {

	}

/**
 *根据SidAndBid查询
*/
	public Borrow getBySidAndBid(Integer sid, Integer bid) {

	}
/**
 *根据SidAndBid更新
*/
	public Long updateBySidAndBid( Borrow bean , Integer sid, Integer bid) {

	}

/**
 *根据SidAndBid删除
*/
	public Long deleteBySidAndBid(Integer sid, Integer bid) {

	}
}

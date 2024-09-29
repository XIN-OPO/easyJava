package com.easyjava.service.Impl;
import com.easyjava.entity.vo.PaginationResultVO;
import com.easyjava.entity.query.SimplePage;
import com.easyjava.enums.PageSize;
import com.easyjava.entity.po.Borrow;
import com.easyjava.entity.query.BorrowQuery;
import javax.annotation.Resource;
import com.easyjava.mappers.BorrowMapper;
import com.easyjava.service.BorrowService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description: 借阅信息ServiceImpl
 * @Author: 张鑫
 * @Date: 2024/09/30
*/
@Service("borrowService")
public class BorrowServiceImpl implements BorrowService {

	@Resource
	private BorrowMapper<Borrow,BorrowQuery> borrowMapper;

/**
 *根据条件查询列表
*/
	public List<Borrow> findListByParam(BorrowQuery query) {
		return this.borrowMapper.selectList(query);
	}
/**
 *根据条件查询数量
*/
	public Integer findCountByParam(BorrowQuery query) {
		return this.borrowMapper.selectCount(query);
	}
/**
 *分页查询
*/
	public PaginationResultVO<Borrow> findListByPage(BorrowQuery query) {
		Integer count=this.findCountByParam(query);
		Integer pageSize=query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page=new SimplePage(query.getPageNo(),count,pageSize);
		query.setSimplePage(page);
		List<Borrow> list=this.findListByParam(query);
		PaginationResultVO<Borrow> result=new PaginationResultVO(count,page.getPageSize(),page.getPageNo(),page.getPageTotal(),list);
		return result;
	}
/**
 *新增
*/
	public Integer add(Borrow bean) {
		return this.borrowMapper.insert(bean);
	}
/**
 *批量新增
*/
	public Integer addBatch(List<Borrow> listBean) {
		if (listBean==null || listBean.isEmpty()) {
			return 0;
		}
		return this.borrowMapper.insertBatch(listBean);
	}
/**
 *批量新增或修改
*/
	public Integer addOrUpdateBatch(List<Borrow> listBean) {
		if (listBean==null || listBean.isEmpty()) {
			return 0;
		}
		return this.borrowMapper.insertOrUpdateBatch(listBean);
	}

/**
 *根据Id查询
*/
	public Borrow getBorrowById(Integer id) {
		return this.borrowMapper.selectById(id);
	}
/**
 *根据Id更新
*/
	public Integer updateBorrowById( Borrow bean , Integer id) {
		return this.borrowMapper.updateById(bean,id);
	}

/**
 *根据Id删除
*/
	public Integer deleteBorrowById(Integer id) {
		return this.borrowMapper.deleteById(id);
	}

/**
 *根据SidAndBid查询
*/
	public Borrow getBorrowBySidAndBid(Integer sid, Integer bid) {
		return this.borrowMapper.selectBySidAndBid(sid, bid);
	}
/**
 *根据SidAndBid更新
*/
	public Integer updateBorrowBySidAndBid( Borrow bean , Integer sid, Integer bid) {
		return this.borrowMapper.updateBySidAndBid(bean,sid, bid);
	}

/**
 *根据SidAndBid删除
*/
	public Integer deleteBorrowBySidAndBid(Integer sid, Integer bid) {
		return this.borrowMapper.deleteBySidAndBid(sid, bid);
	}
}

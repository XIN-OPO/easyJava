package com.easyjava.service.Impl;
import com.easyjava.entity.vo.PaginationResultVO;
import com.easyjava.entity.query.SimplePage;
import com.easyjava.enums.PageSize;
import com.easyjava.entity.po.BooksInfo;
import com.easyjava.entity.query.BooksInfoQuery;
import javax.annotation.Resource;
import com.easyjava.mappers.BooksInfoMapper;
import com.easyjava.service.BooksInfoService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description: 书籍信息
ServiceImpl
 * @Author: 张鑫
 * @Date: 2024/09/29
*/
@Service("booksInfoService")
public class BooksInfoServiceImpl implements BooksInfoService {

	@Resource
	private BooksInfoMapper<BooksInfo,BooksInfoQuery> booksInfoMapper;

/**
 *根据条件查询列表
*/
	public List<BooksInfo> findListByParam(BooksInfoQuery query) {
		return this.booksInfoMapper.selectList(query);
	}
/**
 *根据条件查询数量
*/
	public Integer findCountByParam(BooksInfoQuery query) {
		return this.booksInfoMapper.selectCount(query);
	}
/**
 *分页查询
*/
	public PaginationResultVO<BooksInfo> findListByPage(BooksInfoQuery query) {
		Integer count=this.findCountByParam(query);
		Integer pageSize=query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page=new SimplePage(query.getPageNo(),count,pageSize);
		query.setSimplePage(page);
		List<BooksInfo> list=this.findListByParam(query);
		PaginationResultVO<BooksInfo> result=new PaginationResultVO(count,page.getPageSize(),page.getPageNo(),page.getPageTotal(),list);
		return result;
	}
/**
 *新增
*/
	public Integer add(BooksInfo bean) {
		return this.booksInfoMapper.insert(bean);
	}
/**
 *批量新增
*/
	public Integer addBatch(List<BooksInfo> listBean) {
		if (listBean==null || listBean.isEmpty()) {
			return 0;
		}
		return this.booksInfoMapper.insertBatch(listBean);
	}
/**
 *批量新增或修改
*/
	public Integer addOrUpdateBatch(List<BooksInfo> listBean) {
		if (listBean==null || listBean.isEmpty()) {
			return 0;
		}
		return this.booksInfoMapper.insertOrUpdateBatch(listBean);
	}

/**
 *根据Bid查询
*/
	public BooksInfo getBooksInfoByBid(Integer bid) {
		return this.booksInfoMapper.selectByBid(bid);
	}
/**
 *根据Bid更新
*/
	public Integer updateBooksInfoByBid( BooksInfo bean , Integer bid) {
		return this.booksInfoMapper.updateByBid(bean,bid);
	}

/**
 *根据Bid删除
*/
	public Integer deleteBooksInfoByBid(Integer bid) {
		return this.booksInfoMapper.deleteByBid(bid);
	}
}

package com.easyjava.service.Impl;
import com.easyjava.entity.vo.PaginationResultVO;
import com.easyjava.entity.query.SimplePage;
import com.easyjava.enums.PageSize;
import com.easyjava.entity.po.Admin;
import com.easyjava.entity.query.AdminQuery;
import javax.annotation.Resource;
import com.easyjava.mappers.AdminMapper;
import com.easyjava.service.AdminService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description: 管理员
ServiceImpl
 * @Author: 张鑫
 * @Date: 2024/09/29
*/
@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminMapper<Admin,AdminQuery> adminMapper;

/**
 *根据条件查询列表
*/
	public List<Admin> findListByParam(AdminQuery query) {
		return this.adminMapper.selectList(query);
	}
/**
 *根据条件查询数量
*/
	public Integer findCountByParam(AdminQuery query) {
		return this.adminMapper.selectCount(query);
	}
/**
 *分页查询
*/
	public PaginationResultVO<Admin> findListByPage(AdminQuery query) {
		Integer count=this.findCountByParam(query);
		Integer pageSize=query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page=new SimplePage(query.getPageNo(),count,pageSize);
		query.setSimplePage(page);
		List<Admin> list=this.findListByParam(query);
		PaginationResultVO<Admin> result=new PaginationResultVO(count,page.getPageSize(),page.getPageNo(),page.getPageTotal(),list);
		return result;
	}
/**
 *新增
*/
	public Integer add(Admin bean) {
		return this.adminMapper.insert(bean);
	}
/**
 *批量新增
*/
	public Integer addBatch(List<Admin> listBean) {
		if (listBean==null || listBean.isEmpty()) {
			return 0;
		}
		return this.adminMapper.insertBatch(listBean);
	}
/**
 *批量新增或修改
*/
	public Integer addOrUpdateBatch(List<Admin> listBean) {
		if (listBean==null || listBean.isEmpty()) {
			return 0;
		}
		return this.adminMapper.insertOrUpdateBatch(listBean);
	}

/**
 *根据Id查询
*/
	public Admin getAdminById(Integer id) {
		return this.adminMapper.selectById(id);
	}
/**
 *根据Id更新
*/
	public Integer updateAdminById( Admin bean , Integer id) {
		return this.adminMapper.updateById(bean,id);
	}

/**
 *根据Id删除
*/
	public Integer deleteAdminById(Integer id) {
		return this.adminMapper.deleteById(id);
	}
}

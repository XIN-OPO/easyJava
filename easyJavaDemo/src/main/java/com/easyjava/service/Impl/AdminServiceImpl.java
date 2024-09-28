package com.easyjava.service.Impl;
import com.easyjava.entity.vo.PaginationResultVO;
import com.easyjava.entity.po.Admin;
import com.easyjava.entity.query.AdminQuery;
import com.easyjava.service.AdminService;
import org.springframework.stereotype.Service
import java.util.List;

/**
 * @Description: 管理员
ServiceImpl
 * @Author: 张鑫
 * @Date: 2024/09/29
*/
@Service("adminService")
public class AdminServiceImpl implements AdminService {

/**
 *根据条件查询列表
*/
	public List<Admin> findListByParam(AdminQuery query) {

	}
/**
 *根据条件查询数量
*/
	public Long findCountByParam(AdminQuery query) {

	}
/**
 *分页查询
*/
	public PaginationResultVO<Admin> findListByPage(AdminQuery query) {

	}
/**
 *新增
*/
	public Long add(Admin bean) {

	}
/**
 *批量新增
*/
	public Long addBatch(List<Admin> listBean) {

	}
/**
 *批量新增或修改
*/
	public Long addOrUpdateBatch(List<Admin> listBean) {

	}

/**
 *根据Id查询
*/
	public Admin getById(Integer id) {

	}
/**
 *根据Id更新
*/
	public Long updateById( Admin bean , Integer id) {

	}

/**
 *根据Id删除
*/
	public Long deleteById(Integer id) {

	}
}

package com.easyjava.service;
import com.easyjava.entity.vo.PaginationResultVO;
import com.easyjava.entity.po.Admin;
import com.easyjava.entity.query.AdminQuery;
import java.util.List;

/**
 * @Description: 管理员
Service
 * @Author: 张鑫
 * @Date: 2024/09/28
*/
public interface AdminService{

/**
 *根据条件查询列表
*/
	List<Admin> findListByParam(AdminQuery query);

/**
 *根据条件查询数量
*/
	Long findCountByParam(AdminQuery query);

/**
 *分页查询
*/
	PaginationResultVO<Admin> findListByPage(AdminQuery query);

/**
 *新增
*/
	Long add(Admin bean);

/**
 *批量新增
*/
	Long addBatch(List<Admin> listBean);

/**
 *批量新增或修改
*/
	Long addOrUpdateBatch(List<Admin> listBean);


/**
 *根据Id查询
*/
	Admin getById(Integer id);

/**
 *根据Id更新
*/
	Long updateById( Admin bean , Integer id);

/**
 *根据Id删除
*/
	Long deleteById(Integer id);
}

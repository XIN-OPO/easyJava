package com.easyjava.controller;

import com.easyjava.entity.po.Admin;
import com.easyjava.entity.query.AdminQuery;
import javax.annotation.Resource;
import com.easyjava.service.AdminService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.easyjava.entity.vo.ResponseVO;
import java.util.List;

/**
 * @Description: 管理员
Controller
 * @Author: 张鑫
 * @Date: 2024/09/30
*/
@RestController
@RequestMapping("admin")
public class AdminController extends ABaseController {

	@Resource
	private AdminService adminService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(AdminQuery query) {
		return getSuccessResponseVO(adminService.findListByPage(query));
	}
/**
 *新增
*/
	public ResponseVO add(Admin bean) {
		this.adminService.add(bean);
		return getSuccessResponseVO(null);
	}
/**
 *批量新增
*/
	public ResponseVO addBatch(@RequestBody List<Admin> listBean) {
		this.adminService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}
/**
 *批量新增或修改
*/
	public ResponseVO addOrUpdateBatch(@RequestBody List<Admin> listBean) {
		this.adminService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

/**
 *根据Id查询
*/
	public ResponseVO getAdminById(Integer id) {
		return getSuccessResponseVO(this.adminService.getAdminById(id));
	}
/**
 *根据Id更新
*/
	public ResponseVO updateAdminById( Admin bean , Integer id) {
		this.adminService.updateAdminById(bean,id);
		return getSuccessResponseVO(null);
	}

/**
 *根据Id删除
*/
	public ResponseVO deleteAdminById(Integer id) {
		this.adminService.deleteAdminById(id);
		return getSuccessResponseVO(null);
	}
}

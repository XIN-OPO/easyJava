package com.easyjava.controller;

import com.easyjava.entity.po.Student;
import com.easyjava.entity.query.StudentQuery;
import javax.annotation.Resource;
import com.easyjava.service.StudentService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.easyjava.entity.vo.ResponseVO;
import java.util.List;

/**
 * @Description: 学生信息Controller
 * @Author: 张鑫
 * @Date: 2024/09/30
*/
@RestController
@RequestMapping("student")
public class StudentController extends ABaseController {

	@Resource
	private StudentService studentService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(StudentQuery query) {
		return getSuccessResponseVO(studentService.findListByPage(query));
	}
/**
 *新增
*/
	public ResponseVO add(Student bean) {
		this.studentService.add(bean);
		return getSuccessResponseVO(null);
	}
/**
 *批量新增
*/
	public ResponseVO addBatch(@RequestBody List<Student> listBean) {
		this.studentService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}
/**
 *批量新增或修改
*/
	public ResponseVO addOrUpdateBatch(@RequestBody List<Student> listBean) {
		this.studentService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

/**
 *根据Sid查询
*/
	public ResponseVO getStudentBySid(Integer sid) {
		return getSuccessResponseVO(this.studentService.getStudentBySid(sid));
	}
/**
 *根据Sid更新
*/
	public ResponseVO updateStudentBySid( Student bean , Integer sid) {
		this.studentService.updateStudentBySid(bean,sid);
		return getSuccessResponseVO(null);
	}

/**
 *根据Sid删除
*/
	public ResponseVO deleteStudentBySid(Integer sid) {
		this.studentService.deleteStudentBySid(sid);
		return getSuccessResponseVO(null);
	}

/**
 *根据Sname查询
*/
	public ResponseVO getStudentBySname(String sname) {
		return getSuccessResponseVO(this.studentService.getStudentBySname(sname));
	}
/**
 *根据Sname更新
*/
	public ResponseVO updateStudentBySname( Student bean , String sname) {
		this.studentService.updateStudentBySname(bean,sname);
		return getSuccessResponseVO(null);
	}

/**
 *根据Sname删除
*/
	public ResponseVO deleteStudentBySname(String sname) {
		this.studentService.deleteStudentBySname(sname);
		return getSuccessResponseVO(null);
	}
}

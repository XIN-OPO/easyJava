package com.easyjava.service;
import com.easyjava.entity.vo.PaginationResultVO;
import com.easyjava.entity.po.Student;
import com.easyjava.entity.query.StudentQuery;
import java.util.List;

/**
 * @Description: 学生信息Service
 * @Author: 张鑫
 * @Date: 2024/09/29
*/
public interface StudentService{

/**
 *根据条件查询列表
*/
	List<Student> findListByParam(StudentQuery query);

/**
 *根据条件查询数量
*/
	Integer findCountByParam(StudentQuery query);

/**
 *分页查询
*/
	PaginationResultVO<Student> findListByPage(StudentQuery query);

/**
 *新增
*/
	Integer add(Student bean);

/**
 *批量新增
*/
	Integer addBatch(List<Student> listBean);

/**
 *批量新增或修改
*/
	Integer addOrUpdateBatch(List<Student> listBean);


/**
 *根据Sid查询
*/
	Student getStudentBySid(Integer sid);

/**
 *根据Sid更新
*/
	Integer updateStudentBySid( Student bean , Integer sid);

/**
 *根据Sid删除
*/
	Integer deleteStudentBySid(Integer sid);

/**
 *根据Sname查询
*/
	Student getStudentBySname(String sname);

/**
 *根据Sname更新
*/
	Integer updateStudentBySname( Student bean , String sname);

/**
 *根据Sname删除
*/
	Integer deleteStudentBySname(String sname);
}

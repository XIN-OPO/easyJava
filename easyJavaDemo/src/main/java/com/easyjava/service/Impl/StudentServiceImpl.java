package com.easyjava.service.Impl;
import com.easyjava.entity.vo.PaginationResultVO;
import com.easyjava.entity.po.Student;
import com.easyjava.entity.query.StudentQuery;
import com.easyjava.service.StudentService;
import org.springframework.stereotype.Service
import java.util.List;

/**
 * @Description: 学生信息ServiceImpl
 * @Author: 张鑫
 * @Date: 2024/09/29
*/
@Service("studentService")
public class StudentServiceImpl implements StudentService {

/**
 *根据条件查询列表
*/
	public List<Student> findListByParam(StudentQuery query) {

	}
/**
 *根据条件查询数量
*/
	public Long findCountByParam(StudentQuery query) {

	}
/**
 *分页查询
*/
	public PaginationResultVO<Student> findListByPage(StudentQuery query) {

	}
/**
 *新增
*/
	public Long add(Student bean) {

	}
/**
 *批量新增
*/
	public Long addBatch(List<Student> listBean) {

	}
/**
 *批量新增或修改
*/
	public Long addOrUpdateBatch(List<Student> listBean) {

	}

/**
 *根据Sid查询
*/
	public Student getBySid(Integer sid) {

	}
/**
 *根据Sid更新
*/
	public Long updateBySid( Student bean , Integer sid) {

	}

/**
 *根据Sid删除
*/
	public Long deleteBySid(Integer sid) {

	}

/**
 *根据Sname查询
*/
	public Student getBySname(String sname) {

	}
/**
 *根据Sname更新
*/
	public Long updateBySname( Student bean , String sname) {

	}

/**
 *根据Sname删除
*/
	public Long deleteBySname(String sname) {

	}
}

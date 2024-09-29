package com.easyjava.service.Impl;
import com.easyjava.entity.vo.PaginationResultVO;
import com.easyjava.entity.query.SimplePage;
import com.easyjava.enums.PageSize;
import com.easyjava.entity.po.Student;
import com.easyjava.entity.query.StudentQuery;
import javax.annotation.Resource;
import com.easyjava.mappers.StudentMapper;
import com.easyjava.service.StudentService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description: 学生信息ServiceImpl
 * @Author: 张鑫
 * @Date: 2024/09/30
*/
@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentMapper<Student,StudentQuery> studentMapper;

/**
 *根据条件查询列表
*/
	public List<Student> findListByParam(StudentQuery query) {
		return this.studentMapper.selectList(query);
	}
/**
 *根据条件查询数量
*/
	public Integer findCountByParam(StudentQuery query) {
		return this.studentMapper.selectCount(query);
	}
/**
 *分页查询
*/
	public PaginationResultVO<Student> findListByPage(StudentQuery query) {
		Integer count=this.findCountByParam(query);
		Integer pageSize=query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page=new SimplePage(query.getPageNo(),count,pageSize);
		query.setSimplePage(page);
		List<Student> list=this.findListByParam(query);
		PaginationResultVO<Student> result=new PaginationResultVO(count,page.getPageSize(),page.getPageNo(),page.getPageTotal(),list);
		return result;
	}
/**
 *新增
*/
	public Integer add(Student bean) {
		return this.studentMapper.insert(bean);
	}
/**
 *批量新增
*/
	public Integer addBatch(List<Student> listBean) {
		if (listBean==null || listBean.isEmpty()) {
			return 0;
		}
		return this.studentMapper.insertBatch(listBean);
	}
/**
 *批量新增或修改
*/
	public Integer addOrUpdateBatch(List<Student> listBean) {
		if (listBean==null || listBean.isEmpty()) {
			return 0;
		}
		return this.studentMapper.insertOrUpdateBatch(listBean);
	}

/**
 *根据Sid查询
*/
	public Student getStudentBySid(Integer sid) {
		return this.studentMapper.selectBySid(sid);
	}
/**
 *根据Sid更新
*/
	public Integer updateStudentBySid( Student bean , Integer sid) {
		return this.studentMapper.updateBySid(bean,sid);
	}

/**
 *根据Sid删除
*/
	public Integer deleteStudentBySid(Integer sid) {
		return this.studentMapper.deleteBySid(sid);
	}

/**
 *根据Sname查询
*/
	public Student getStudentBySname(String sname) {
		return this.studentMapper.selectBySname(sname);
	}
/**
 *根据Sname更新
*/
	public Integer updateStudentBySname( Student bean , String sname) {
		return this.studentMapper.updateBySname(bean,sname);
	}

/**
 *根据Sname删除
*/
	public Integer deleteStudentBySname(String sname) {
		return this.studentMapper.deleteBySname(sname);
	}
}

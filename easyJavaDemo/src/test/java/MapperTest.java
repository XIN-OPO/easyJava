import com.easyjava.RunApplication;
import com.easyjava.entity.po.Borrow;
import com.easyjava.entity.po.Student;
import com.easyjava.entity.query.BorrowQuery;
import com.easyjava.entity.query.StudentQuery;
import com.easyjava.mappers.BorrowMapper;
import com.easyjava.mappers.StudentMapper;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=RunApplication.class)
public class MapperTest {
    @Resource
    private StudentMapper<Student, StudentQuery> queryStudentMapper;
    @Resource
    private BorrowMapper<Borrow,BorrowQuery> borrowQueryBorrowMapper;

    @Test
    public void selectList(){
//        BorrowQuery borrowQuery=new BorrowQuery();
//        StudentQuery studentQuery=new StudentQuery();
//        borrowQuery.setId(4);
//        borrowQuery.setTimeStart("2024-09-08");
//        borrowQuery
//        List<Borrow> data=queryBorrowMapper.selectList(borrowQuery);
//        for (Borrow borrow:data){
//            System.out.println(borrow);
//        }
//        System.out.println(data.toString());
//        Long count=queryBorrowMapper.selectCount(borrowQuery);
//        System.out.println(count);

//        studentQuery.setSnameFuzzy("k");
//        List<Student> students=queryStudentMapper.selectList(studentQuery);
//        List<Borrow> data=queryBorrowMapper.selectList(borrowQuery);
//        for (Student student:students){
//            System.out.println(student);
//        }
    }

    @Test
    public void insert() throws Exception{
//        Student student=new Student();
//        student.setSname("kido");
//        student.setGrade(2019);
//        this.queryStudentMapper.insert(student);
//        System.out.println(student.getSid());
        Borrow borrow=new Borrow();
        borrow.setSid(1);
        borrow.setBid(2);
        borrow.setTime(new Date());
        borrowQueryBorrowMapper.insert(borrow);
        System.out.println(borrow.getId());
    }
    @Test
    public void insertOrUpdate(){
        Student student=new Student();
        student.setGrade(2017);
        student.setSname("steven");
        student.setSid(3);
        this.queryStudentMapper.insertOrUpdate(student);
        System.out.println(student.getSid());
    }
    @Test
    public void insertBatch(){
        List<Student> studentList=new ArrayList();
        Student student=new Student();
        student.setSname("kimi");
        student.setGrade(2008);
        studentList.add(student);

        student=new Student();
        student.setSname("jimmy");
        student.setGrade(2007);
        studentList.add(student);
        this.queryStudentMapper.insertBatch(studentList);
    }

    @Test
    public void insertOrUpdateBatch(){
        List<Student> studentList=new ArrayList();
        Student student=new Student();
        student.setSname("kido");
        student.setGrade(2008);
        studentList.add(student);

        student=new Student();
        student.setSname("jimmy");
        student.setGrade(2005);
        studentList.add(student);
        this.queryStudentMapper.insertOrUpdateBatch(studentList);
    }
    @Test
    public void selectByKey(){
        Student student2=new Student();
        student2=queryStudentMapper.selectBySname("jimmy");
        System.out.println(student2);
    }
    @Test
    public void updateByKey(){
        Student student1=new Student();
        student1.setGrade(2014);
        Long count=queryStudentMapper.updateBySid(student1,1);
        System.out.println(queryStudentMapper.selectBySid(1));
        System.out.println(count);
    }
    @Test
    public void deleteByKey(){
        queryStudentMapper.deleteBySid(26);
        queryStudentMapper.deleteBySname("jimmy");
    }
}

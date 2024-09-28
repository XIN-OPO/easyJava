package com.easyjava.mappers;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper<T,P> extends BaseMapper {
/**
 * @Description: 学生信息
 * @Author: 张鑫
 * @Date: 2024/09/28
*/

/**
 *根据Sid查询
*/
	T selectBySid(@Param("sid") Integer sid);

/**
 *根据Sid更新
*/
	Integer updateBySid(@Param("bean") T t , @Param("sid") Integer sid);

/**
 *根据Sid删除
*/
	Integer deleteBySid(@Param("sid") Integer sid);

/**
 *根据Sname查询
*/
	T selectBySname(@Param("sname") String sname);

/**
 *根据Sname更新
*/
	Integer updateBySname(@Param("bean") T t , @Param("sname") String sname);

/**
 *根据Sname删除
*/
	Integer deleteBySname(@Param("sname") String sname);

}
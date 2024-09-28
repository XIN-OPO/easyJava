package com.easyjava.mappers;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper<T,P> extends BaseMapper {
/**
 * @Description: 学生信息
 * @Author: 张鑫
 * @Date: 2024/09/29
*/

/**
 *根据Sid查询
*/
	T selectBySid(@Param("sid") Integer sid);

/**
 *根据Sid更新
*/
	Long updateBySid(@Param("bean") T t , @Param("sid") Integer sid);

/**
 *根据Sid删除
*/
	Long deleteBySid(@Param("sid") Integer sid);

/**
 *根据Sname查询
*/
	T selectBySname(@Param("sname") String sname);

/**
 *根据Sname更新
*/
	Long updateBySname(@Param("bean") T t , @Param("sname") String sname);

/**
 *根据Sname删除
*/
	Long deleteBySname(@Param("sname") String sname);

}
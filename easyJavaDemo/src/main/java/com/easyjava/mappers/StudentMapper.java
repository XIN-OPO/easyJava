package com.easyjava.mappers;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper<T,P> extends BaseMapper {
/**
 * @Description: 学生信息
 * @Author: 张鑫
 * @Date: 2024/09/27
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

}
package com.easyjava.mappers;
import org.apache.ibatis.annotations.Param;

public interface BorrowMapper<T,P> extends BaseMapper {
/**
 * @Description: 借阅信息
 * @Author: 张鑫
 * @Date: 2024/09/23
*/

/**
 *根据IdAnd,查询
*/
	T selectByIdAnd,(@Param("id") Integer id);

/**
 *根据IdAnd,更新
*/
	Integer updateByIdAnd,(@Param("bean") T t,@Param("id") Integer id);

/**
 *根据IdAnd,删除
*/
	Integer deleteByIdAnd,(@Param("id") Integer id);

/**
 *根据SidAnd,Bid查询
*/
	T selectBySidAnd,Bid(@Param("sid") Integer sid@Param("bid") Integer bid);

/**
 *根据SidAnd,Bid更新
*/
	Integer updateBySidAnd,Bid(@Param("bean") T t,@Param("sid") Integer sid@Param("bid") Integer bid);

/**
 *根据SidAnd,Bid删除
*/
	Integer deleteBySidAnd,Bid(@Param("sid") Integer sid@Param("bid") Integer bid);

}
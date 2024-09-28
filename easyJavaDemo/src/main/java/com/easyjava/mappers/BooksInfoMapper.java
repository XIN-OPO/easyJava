package com.easyjava.mappers;
import org.apache.ibatis.annotations.Param;

public interface BooksInfoMapper<T,P> extends BaseMapper {
/**
 * @Description: 书籍信息

 * @Author: 张鑫
 * @Date: 2024/09/29
*/

/**
 *根据Bid查询
*/
	T selectByBid(@Param("bid") Integer bid);

/**
 *根据Bid更新
*/
	Integer updateByBid(@Param("bean") T t , @Param("bid") Integer bid);

/**
 *根据Bid删除
*/
	Integer deleteByBid(@Param("bid") Integer bid);

}
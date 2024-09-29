package com.easyjava.mappers;
import org.apache.ibatis.annotations.Param;

public interface BorrowMapper<T,P> extends BaseMapper {
/**
 * @Description: 借阅信息
 * @Author: 张鑫
 * @Date: 2024/09/30
*/

/**
 *根据Id查询
*/
	T selectById(@Param("id") Integer id);

/**
 *根据Id更新
*/
	Integer updateById(@Param("bean") T t , @Param("id") Integer id);

/**
 *根据Id删除
*/
	Integer deleteById(@Param("id") Integer id);

/**
 *根据SidAndBid查询
*/
	T selectBySidAndBid(@Param("sid") Integer sid, @Param("bid") Integer bid);

/**
 *根据SidAndBid更新
*/
	Integer updateBySidAndBid(@Param("bean") T t , @Param("sid") Integer sid, @Param("bid") Integer bid);

/**
 *根据SidAndBid删除
*/
	Integer deleteBySidAndBid(@Param("sid") Integer sid, @Param("bid") Integer bid);

}
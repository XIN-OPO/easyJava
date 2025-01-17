package com.easyjava.mappers;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper<T,P> extends BaseMapper {
/**
 * @Description: 管理员

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

}
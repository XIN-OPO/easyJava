package com.easyjava.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface BaseMapper<T,P>{
    /*
    insert:插入
     */
    Long insert(@Param("bean") T t);
    /*
    插入或者更新
     */
    Long insertOrUpdate(@Param("bean") T t);
    /*
    批量插入
     */
    Long insertBatch(@Param("list") List<T> list);
    /*
    批量插入或者更新
     */
    Long insertOrUpdateBatch(@Param("list") List<T> list);
    /*
    根据参数查询集合
     */
    List<T> selectList(@Param("query") P p);
    /*
    根据集合查询数量
     */
    Long selectCount(@Param("query") P p);
}

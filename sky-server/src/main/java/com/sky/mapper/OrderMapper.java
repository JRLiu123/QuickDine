package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: OrderMapper
 * Package: com.sky.mapper
 * Description:
 *
 * @Author Jingran Liu
 * @Create 2025/1/24 17:37
 * @Version 1.0
 */
@Mapper
public interface OrderMapper {
    /**
     * insert a record of order into order table
     * @param orders
     */
    void insert(Orders orders);
}

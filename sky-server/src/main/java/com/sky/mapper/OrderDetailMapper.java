package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
public interface OrderDetailMapper {
    /**
     * insert a batch of order details into db
     * @param orderDetailList
     */
    void insertBatch(List<OrderDetail> orderDetailList);
}
